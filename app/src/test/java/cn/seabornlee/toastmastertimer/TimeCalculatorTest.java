package cn.seabornlee.toastmastertimer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, emulateSdk = 21)
public class TimeCalculatorTest {
    @Test
    public void should_show_green_card_at_1_minutes_remaining_when_speech_is_less_than_3_minutes() {
        assertTrue(new TimeCalculator(2).isTimeToShowGreenCard(60 * 1000));
        assertTrue(new TimeCalculator(3).isTimeToShowGreenCard(60 * 1000));
    }

    @Test
    public void should_show_green_card_at_2_minutes_remaining_when_speech_is_more_than_3_minutes() {
        assertTrue(new TimeCalculator(5).isTimeToShowGreenCard(2 * 60 * 1000));
        assertTrue(new TimeCalculator(8).isTimeToShowGreenCard(2 * 60 * 1000));
        assertTrue(new TimeCalculator(10).isTimeToShowGreenCard(2 * 60 * 1000));
        assertTrue(new TimeCalculator(12).isTimeToShowGreenCard(2 * 60 * 1000));
    }
}