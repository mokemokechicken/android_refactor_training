package jp.yumemi.lab.refactorme.module.qiita.presentation;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.yumemi.lab.refactorme.android_mvc_sample.R;
import jp.yumemi.lab.refactorme.base.Animation;
import jp.yumemi.lab.refactorme.base.FragmentRouter;
import jp.yumemi.lab.refactorme.module.qiita.entity.QiitaItem;

public class MenuFragment extends Fragment {
    public static List<QiitaItem> sAndroidItemList = new ArrayList<>();
    public static int sAndroidItemCount = 0;

    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qiita_menu, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick(R.id.button_latest)
    public void onButtonLatestClick() {
        FragmentRouter.instance.replace(getFragmentManager(), R.id.container, FragmentTag.LATEST_LIST, null, Animation.SLIDE_IN_RIGHT);
    }

    @OnClick(R.id.button_android)
    public void onButtonAndroidClick() {
        FragmentRouter.instance.replace(getFragmentManager(), R.id.container, FragmentTag.ANDROID_LIST, null, Animation.SLIDE_IN_RIGHT);
    }

}
