package jp.yumemi.lab.refactorme.module.qiita.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import de.greenrobot.event.EventBus;
import jp.yumemi.lab.refactorme.android_mvc_sample.R;
import jp.yumemi.lab.refactorme.base.Animation;
import jp.yumemi.lab.refactorme.base.FragmentRouter;
import jp.yumemi.lab.refactorme.base.ModelContainer;
import jp.yumemi.lab.refactorme.module.qiita.entity.QiitaItem;
import jp.yumemi.lab.refactorme.module.qiita.model.ModelTag;
import jp.yumemi.lab.refactorme.module.qiita.model.QiitaLatestItemModel;

public class LatestItemFragment extends Fragment {

    @InjectView(R.id.itemCountTextView)
    TextView mItemCountTextView;
    @InjectView(R.id.qiitaItemListView)
    ListView mQiitaItemListView;

    private final List<QiitaItem> mQiitaItemList = new ArrayList<>();
    private QiitaItemListAdapter mQiitaItemListAdapter;

    public LatestItemFragment() {
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
                mQiitaItemList
        );
        mQiitaItemListView.setAdapter(mQiitaItemListAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((QiitaLatestItemModel) ModelContainer.get(ModelTag.QIITA_ITEM)).load();
        updateView();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this); // EventBus
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this); // EventBus
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
        args.putString(DetailFragment.URL, mQiitaItemList.get(position).url);
        FragmentRouter.instance.replace(getFragmentManager(), R.id.container, FragmentTag.DETAIL, args, Animation.SLIDE_IN_BOTTOM);
    }

    // EventBus からの通知
    @SuppressWarnings("unused")
    public void onEventMainThread(QiitaLatestItemModel.LoadedEvent event) {
        if (event.isSuccess()) {
            updateView();
        }
    }

    // Viewの表示を更新するプライベートメソッド
    private void updateView() {
        // ここでは通信が伴うような時間がかかる処理はしない。Model上の変数をアクセスするに留める
        mItemCountTextView.setText(((QiitaLatestItemModel) ModelContainer.get(ModelTag.QIITA_ITEM)).getItemCount() + " 件");
        mQiitaItemList.clear();
        mQiitaItemList.addAll(((QiitaLatestItemModel) ModelContainer.get(ModelTag.QIITA_ITEM)).getItemList());
        mQiitaItemListAdapter.notifyDataSetChanged();
    }
}
