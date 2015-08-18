環境やConfiguration別のBuildをソースを書き換えないで行う
==========================
はじめに
-------
今回は「環境やConfiguration別のBuildをソースを書き換えないで行う」方法について説明します。

開発時には「自分のPC内の環境」「共有の開発環境」「Staging環境」「Production環境」など
様々な環境があると思います。
環境が異なる場合に、アプリケーションが利用する外部環境の情報が異なる場合があります。

代表的なのは、「APIなどのEndpoint URL」「PATHやDIR」「API KeyやSecret」などがあります。

また、DebugやRelease等のConfigurationによっても変わる値があります。
例えば、「LogのLevel」「保存・表示するデータの件数」「Debug機能のON/OFF」などです。

これをリリースするときになってソースコードを書き換えるようにしていると、うっかり異なる設定をリリースしてしまう事故が起こります。
リリース間際は色々気忙しくてこういうヒューマンエラーがよく起こります。
こういうのは、そもそも仕組み化しておくのが良いやり方です。

それらをどのように記述するかを説明します。

問題
--------

以下のことを「ビルド時にソースコードを書き換えないで」できるようにしてください。

### Debug/Release で表示件数を変更する

QiitaLatestItemModelで表示件数を以下のように直接指定していますが、
これを「Debugビルドの時は10件」「Releaseビルドの時は20件」となるようにリファクタリングしてください。

```java:QiitaLatestItemModel.java
...
  // 非同期で API を発行
  new AsyncTask<Void, Void, List<QiitaItem>>() {
      @Override
      protected List<QiitaItem> doInBackground(Void... voids) {
          return ApiManager.getService().getItems(10);
      }
...
```

### 環境別のAPI Keyを指定する
現時点の実装では、Qiita Viewer の ApiKeyが `Environment.getQiitaApiKey()` で取得できますが、
これを「Debug」「Staging」「Release」の３つの環境で異なる値を指定できるようにした上で、アプリケーションをビルドしてください。

### 挑戦：動的に API Key を指定する
テスト用にAPI KeyやURLをビルド時に動的に指定したいことがあります。
つまり、ビルドコマンドを打つタイミングでURLなどを指定したいということです。
「環境別のAPI Keyを指定する」の応用で実現可能ですので、挑戦してみてください。
