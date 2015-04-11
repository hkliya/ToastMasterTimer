package cn.seabornlee.toastmastertimer;

import android.os.CountDownTimer;

public class ToastMasterTimer {
    private CountDownTimer timer;
    private TimerListener timerListener;
    private int minutes;

    public ToastMasterTimer(TimerListener timerListener) {
        this.timerListener = timerListener;
    }

    public void cancel() {
        timer.cancel();
        timerListener.onStop();
        timer = null;
    }

    public void start(final int minutes) {
        this.minutes = minutes;

        if (timer != null) {
            timer.cancel();
        }

        timer = new CountDownTimer(minutes * 60 * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerListener.showTimer(millisUntilFinished);

                if (isTimeToShowYellowCard(millisUntilFinished)) {
                    timerListener.showYellowCard();
                    return;
                }

                if (isTimeToShowGreenCard(millisUntilFinished)) {
                    timerListener.showGreenCard();
                }
            }

            @Override
            public void onFinish() {
                timerListener.showRedCard();
                timer = new CountDownTimer(getLeeway() * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timerListener.showTimer(millisUntilFinished);
                    }

                    @Override
                    public void onFinish() {
                        timerListener.ringTheBell();
                        timer = null;
                    }
                }.start();
            }
        };
        timer.start();
        timerListener.onStart();
    }

    private int getLeeway() {
        int leeway = 15;
        if (minutes > 3) {
            leeway = 30;
        }

        return leeway;
    }

    private boolean isTimeToShowYellowCard(long millisUntilFinished) {
        int threshold = 30;
        if (minutes > 3) {
            threshold = 60;
        }

        return millisUntilFinished <= threshold * 1000;
    }

    private boolean isTimeToShowGreenCard(long millisUntilFinished) {
        int threshold = 60;
        if (minutes > 3) {
            threshold = 2 * 60;
        }

        return millisUntilFinished <= threshold * 1000;
    }
}
