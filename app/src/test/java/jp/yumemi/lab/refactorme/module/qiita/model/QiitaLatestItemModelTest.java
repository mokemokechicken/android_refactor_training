package jp.yumemi.lab.refactorme.module.qiita.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
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

    @Before
    public void pauseLooper() {
        Robolectric.getBackgroundThreadScheduler().pause();
        Robolectric.getForegroundThreadScheduler().pause();
    }

    private void Load() {
        mTestTarget.load();
    }

    private void BusyStateFalse() {
        assertFalse(mTestTarget.isBusy());
    }

    private void BusyStateTrue() {
        assertTrue(mTestTarget.isBusy());
    }

    private void FlushBackgroundTasks() {
        Robolectric.flushBackgroundThreadScheduler();
    }

    private void FlushForegroundTasks() {
        Robolectric.flushForegroundThreadScheduler();
    }

// %%

//
// 以下の行は自動生成されているので直接編集しないでください。
//

    @Test
    public void test_BusyStateFalse_Load_BusyStateTrue_FlushBackgroundTasks_FlushForegroundTasks_BusyStateFalse() {
        BusyStateFalse();
        Load();
        BusyStateTrue();
        FlushBackgroundTasks();
        FlushForegroundTasks();
        BusyStateFalse();
    }

    private interface Actions {
        void BusyStateFalse();
        void Load();
        void BusyStateTrue();
        void FlushBackgroundTasks();
        void FlushForegroundTasks();
    }
}
