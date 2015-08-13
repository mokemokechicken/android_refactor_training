package jp.yumemi.lab.refactorme.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by k_morishita on 15/08/13.
 */
public interface FragmentRouterProtocol {

    public void register(FragmentTag tag, Class c);

    public void replace(FragmentManager fragmentManager, @IdRes int container, FragmentTag tag, Bundle args, Animation animation);

    public void replace(FragmentManager fragmentManager, @IdRes int container, FragmentTag tag, Bundle args, Animation animation, boolean addToBackStack);

    public Fragment newInstance(FragmentTag tag, Bundle args);

}
