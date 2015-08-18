Presentation層の非同期イベントとデータ管理
==========================

はじめに
--------
今回説明したいのは、以下のことです。

* (a) Presentation層は非同期イベントをcallbackで受け取るのは悪い習慣
* (b) データを保持・共有したいときにActivityやFragmentに置くのは悪い習慣

この悪い習慣を解消するために、「Model」のような役割を導入します。
Qiita記事の簡単なViewerを例に説明します。

問題
----------
### 非同期イベントの受け取り方

Menu画面
<img src="image/menu.png" width="482" />

において「Android関連記事ボタンをTap」「すぐにBack」を繰り返すとアプリケーションがCrashします。
※ 再現しない場合は、遅い通信環境で試してみてください。

原因は、 AndroidItemFragment にあります。

```java:AndroidItemFragment.java
@Override
public void onStart() {
    super.onStart();

    // 非同期で API を発行
    new AsyncTask<Void, Void, List<QiitaItem>>() {
        @Override
        protected List<QiitaItem> doInBackground(Void... voids) {
            return ApiManager.getService().getItemsByTag("Android");
        }

        @Override
        protected void onPostExecute(List<QiitaItem> result) {
            super.onPostExecute(result);
            // データを更新
            MenuFragment.sAndroidItemList.clear();
            MenuFragment.sAndroidItemList.addAll(result);
            // 件数を更新
            MenuFragment.sAndroidItemCount = result.size();
            String msg = MenuFragment.sAndroidItemCount + " 件";
            mItemCountTextView.setText(msg);  # ここが Null Pointer Exception
            mQiitaItemListAdapter.notifyDataSetChanged();
        }
    }.execute();
}
```

AsyncTaskが終わった時に、このFragmentのViewが既に解放されている場合に Null Pointer Exception が発生します。
もちろん、単にnull checkをするという方法もありますが、
そもそもこのAsyncTaskの結果をFragmentが受け取る必要がないのに処理することになるのが、
構造的にバグが潜みやすいものと言えます。

### データをstatic publicで保持している部分

「各記事Listは2回目以降は前回のを表示しつつ、新しい記事を取得する」という仕様がある場合、
よく MenuFragment.sAndroidItemListのような static public にデータを保持する実装をみることがあります。

これは、いわゆる「Global変数」みたいなものであり、あまりよい習慣ではありません。

解決方法
---------

「最新記事」のListを表示している LatestItemFragment + QiitaLatestItemModel のような実装にします。

* QiitaLatestItemModel から LatestItemFragment にはデータの更新があることを通知(EventBus経由)で知らせています。
* 前回Fetchしたデータは QiitaLatestItemModel が保持しています。

具体的には、 QiitaAndroidItemModel のようなものを作って、AndroidItemFragmentと連携させると良いでしょう。

議論
-------
### QiitaAndroidItemModel と QiitaLatestItemModel って分ける必要があるのか？
ここは考えてしまいますが、わける理由の一つとして、 それぞれのModelが持つ「状態」があります。

今回それぞれ、 「isBusy」「itemList」という状態があります。
isBusyは「通信中にFetchの要求をもらっても、再度通信を行わないための状態管理」をしています。
これは、「最新記事」「Android記事」と中身が違えば、Fetchしないわけにはいかないので、分ける必要があります。
また、前回記事のCacheも同様です。

もちろん、 `if (isBusy[type]) {...}` のようにMapなどで管理すれば１つのModelクラスでも実現可能です。
ただ、このTypeによって、やりたい処理が分かれてきた場合、分岐が増えていって見通しが悪くなることがあります。
（ちょっとだけよ・・・ が拡大するのはよくあること）。
「分岐が発生したときには、クラスを分離する」というポリシーでも良いですが、大抵そうしてくれません。
なんとなくですが、費用対効果的に最初から分離しておいても良いように思います。
※ UseCaseベースな感じですかね。

### このリファクタリングそのものが必要なのか

Global変数部分を除去するというのは少なくとも必須だと思います。
こういう変数が１０〜２０もあると、何がなんだかわからなくなってきますし。

前述したようにnull checkすれば良いじゃん、という話はしばしばありますが、
私達の方針としてはこの構造で実装することを基本としています。
詳しくは、  [iOS/Androidアプリエンジニアが理解すべき「Model」の振る舞い](http://www.slideshare.net/mokemokechicken/iosandroidmodel) も参考にしてください。
