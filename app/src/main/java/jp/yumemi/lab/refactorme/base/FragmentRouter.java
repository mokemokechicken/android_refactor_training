package jp.yumemi.lab.refactorme.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;

import jp.yumemi.lab.refactorme.android_mvc_sample.R;

// Fragment の切り替えを行うクラス

public class FragmentRouter implements FragmentRouterProtocol {
    public static FragmentRouterProtocol instance;

    private final int ANIM_RES_SLIDE_IN_RIGHT = R.anim.slide_in_right;
    private final int ANIM_RES_SLIDE_OUT_RIGHT = R.anim.slide_out_right;
    private final int ANIM_RES_SLIDE_IN_BOTTOM = R.anim.slide_in_bottom;
    private final int ANIM_RES_SLIDE_OUT_BOTTOM = R.anim.slide_out_bottom;
    private final int ANIM_RES_FADE_IN = R.anim.fade_in;
    private final int ANIM_RES_FADE_OUT = R.anim.fade_out;

    private Map<FragmentTag, Class> showcase = new HashMap<>();

    public void register(FragmentTag tag, Class c) {
        showcase.put(tag, c);
    }

    private Class get(FragmentTag tag) {
        return showcase.get(tag);
    }

    public void replace(FragmentManager fragmentManager, @IdRes int container, FragmentTag tag, Bundle args, Animation animation) {
        replace(fragmentManager, container, tag, args, animation, true);
    }

    public void replace(FragmentManager fragmentManager, @IdRes int container, FragmentTag tag, Bundle args, Animation animation, boolean addToBackStack) {
        Fragment fragment = getInstance(fragmentManager, tag);
        if (fragment == null) {
            fragment = newInstance(tag, args);
        }

        int enterAnim;
        int exitAnim;
        int popEnterAnim;
        int popExitAnim;
        switch (animation) {
            case SLIDE_IN_RIGHT:
                enterAnim = ANIM_RES_SLIDE_IN_RIGHT;
                exitAnim = ANIM_RES_FADE_OUT;
                popEnterAnim = ANIM_RES_FADE_IN;
                popExitAnim = ANIM_RES_SLIDE_OUT_RIGHT;
                break;
            case SLIDE_IN_BOTTOM:
                enterAnim = ANIM_RES_SLIDE_IN_BOTTOM;
                exitAnim = ANIM_RES_FADE_OUT;
                popEnterAnim = ANIM_RES_FADE_IN;
                popExitAnim = ANIM_RES_SLIDE_OUT_BOTTOM;
                break;
            case FADE_IN:
            case NON:
                enterAnim = ANIM_RES_FADE_IN;
                exitAnim = ANIM_RES_FADE_OUT;
                popEnterAnim = ANIM_RES_FADE_IN;
                popExitAnim = ANIM_RES_FADE_OUT;
                break;
            default:
                return;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (animation != Animation.NON) { fragmentTransaction.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim); }
        fragmentTransaction.replace(container, fragment, getUniqueString(tag));
        if (addToBackStack) { fragmentTransaction.addToBackStack(null); }
        fragmentTransaction.commit();
    }

    public Fragment newInstance(FragmentTag tag, Bundle args) {
        Class c = get(tag);
        Fragment fragment = null;
        try {
            fragment = (Fragment)c.newInstance();
            fragment.setArguments(args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FragmentCannotInstantiateException();
        }
        return fragment;
    }

    public Fragment getInstance(FragmentManager fragmentManager, FragmentTag tag) {
        return fragmentManager.findFragmentByTag(getUniqueString(tag));
    }

    private String getUniqueString(FragmentTag tag) {
        return tag.getClass().getCanonicalName() + "." + String.valueOf(tag);
    }
}

class FragmentCannotInstantiateException extends RuntimeException {}