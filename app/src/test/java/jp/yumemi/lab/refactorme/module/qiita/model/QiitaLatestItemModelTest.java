package jp.yumemi.lab.refactorme.module.qiita.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

/**
 * Created by kaoru on 15/08/19.
 */
@RunWith(RobolectricTestRunner.class)
public class QiitaLatestItemModelTest {
    private QiitaLatestItemModel mTestTarget;

    @Before
    public void setUp() {
        mTestTarget = new QiitaLatestItemModel();
    }
}
