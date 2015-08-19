package jp.yumemi.lab.refactorme.module.qiita.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import de.greenrobot.event.EventBus;

import static org.junit.Assert.*;

/**
 * Created by kaoru on 15/08/19.
 */
@RunWith(RobolectricTestRunner.class)
public class QiitaLatestItemModelTest {
    private EventBus mEventBus;
    private QiitaLatestItemModel mTestTarget;
    private int mEventReceived = 0;

    @Before
    public void setUp() {
        mEventBus = new EventBus();
        mEventReceived = 0;

        mTestTarget = new QiitaLatestItemModel(mEventBus);

        mEventBus.register(this);
    }

    @After
    public void tearDown() {
        mEventBus.unregister(this);
    }

    @Before
    public void pauseLooper() {
        Robolectric.getBackgroundThreadScheduler().pause();
        Robolectric.getForegroundThreadScheduler().pause();
    }

    @SuppressWarnings("unused")
    public void onEvent(QiitaLatestItemModel.LoadedEvent event) {
        mEventReceived ++;
    }

    private void Load() {
        mTestTarget.load();
    }

    private void andFlushTasks() {
        Robolectric.flushBackgroundThreadScheduler();
        Robolectric.flushForegroundThreadScheduler();
    }
    
    private void shouldReceiveLoadedEvent() {
        assertEquals(1, mEventReceived);
    }


// %%

//
// 以下の行は自動生成されているので直接編集しないでください。
//

    @Test
    public void test_Load_andFlushTasks_shouldReceiveLoadedEvent() {
        Load();
        andFlushTasks();
        shouldReceiveLoadedEvent();
    }

    private interface Actions {
        void Load();
        void andFlushTasks();
        void shouldReceiveLoadedEvent();
    }
}
