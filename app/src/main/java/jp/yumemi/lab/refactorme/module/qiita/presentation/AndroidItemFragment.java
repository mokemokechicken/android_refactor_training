package jp.yumemi.lab.refactorme.module.qiita.presentation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import jp.yumemi.lab.refactorme.android_mvc_sample.R;
import jp.yumemi.lab.refactorme.base.Animation;
import jp.yumemi.lab.refactorme.base.FragmentRouter;
import jp.yumemi.lab.refactorme.module.qiita.api.ApiManager;
import jp.yumemi.lab.refactorme.module.qiita.entity.QiitaItem;

public class AndroidItemFragment extends Fragment {

    @InjectView(R.id.itemCountTextView)
    TextView mItemCountTextView;
    @InjectView(R.id.qiitaItemListView)
    ListView mQiitaItemListView;

    private QiitaItemListAdapter mQiitaItemListAdapter;

    public AndroidItemFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.inject(this, view); // ButterKnife
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mQiitaItemListAdapter = new QiitaItemListAdapter(
                getActivity(),
                R.layout.adapter_qiita_item_list,
                MenuFragment.sAndroidItemList
        );
        mQiitaItemListView.setAdapter(mQiitaItemListAdapter);
    }

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
                mItemCountTextView.setText(msg);
                mQiitaItemListAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this); // ButterKnife
    }

    // ListView 中の項目選択
    @SuppressWarnings("unused")
    @OnItemClick(R.id.qiitaItemListView)
    public void onItemClickQiitaItemListView(int position) {
        Bundle args = new Bundle();
        args.putString(DetailFragment.URL, MenuFragment.sAndroidItemList.get(position).url);
        FragmentRouter.instance.replace(getFragmentManager(), R.id.container, FragmentTag.DETAIL, args, Animation.SLIDE_IN_BOTTOM);
    }

}
