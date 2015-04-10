package cn.seabornlee.toastmastertimer;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.google.inject.Inject;

import static java.lang.String.format;

public class ToastMasterTimer {

    @Inject
    private TextView tv_countDownTimer;

    private int minutes;
    private CountDownTimer timer;

    public ToastMasterTimer(TextView tv_countDownTimer) {
        this.tv_countDownTimer = tv_countDownTimer;
    }

    public boolean isRunning() {
        return timer != null;
    }

    public void cancel() {
        timer.cancel();
        timer = null;
        tv_countDownTimer.setText(formatTime(getMillis()));
    }

    public void start() {
        timer = new CountDownTimer(getMillis(), 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                tv_countDownTimer.setText(formatTime(millisUntilFinished));
            }

            @Override
            public void onFinish() {
            }
        };
        timer.start();
    }

    private String formatTime(long millisUntilFinished) {
        int minutes = (int) (millisUntilFinished / 60 / 1000);
        int seconds = (int) (millisUntilFinished / 1000 % 60);
        return format("%02d:%02d", minutes, seconds);
    }

    private int getMillis() {
        return minutes * 60 * 1000;
    }

    public void setTimeInMinutes(int minutes) {
        this.minutes = minutes;
    }
}
