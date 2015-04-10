package cn.seabornlee.toastmastertimer;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.google.inject.Inject;

public class ToastMasterTimer {

    @Inject
    private TextView tv_countDownTimer;

    private int minutes;
    private CountDownTimer timer;
    private TimerListener timerListener;

    public ToastMasterTimer(TextView tv_countDownTimer, TimerListener timerListener) {
        this.tv_countDownTimer = tv_countDownTimer;
        this.timerListener = timerListener;
    }

    public boolean isRunning() {
        return timer != null;
    }

    public void cancel() {
        timer.cancel();
        timerListener.onStop();
        timer = null;
        timerListener.showTimer(getMillis());
    }

    public void start() {
        timer = new CountDownTimer(getMillis(), 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (isTimeToShowGreenCard(millisUntilFinished)) {
                    timerListener.showGreenCard();
                }
                timerListener.showTimer(millisUntilFinished);
            }

            @Override
            public void onFinish() {
            }
        };
        timer.start();
        timerListener.onStart();
    }

    private boolean isTimeToShowGreenCard(long millisUntilFinished) {
        return millisUntilFinished <= 30 * 1000;
    }

    private int getMillis() {
        return minutes * 60 * 1000;
    }

    public void setTimeInMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void toggle() {
        if (isRunning()) {
            cancel();
        } else {
            start();
        }
    }
}
