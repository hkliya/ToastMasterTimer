package cn.seabornlee.toastmastertimer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, emulateSdk = 21)
public class MainActivityTest {

    @Before
    public void setUp() throws Exception {
        // setup
    }

    @Test
    public void testSomething() throws Exception {
        assertTrue(true);
    }
}
