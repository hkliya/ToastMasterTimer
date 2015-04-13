package cn.seabornlee.toastmastertimer;

import android.os.CountDownTimer;

public class ToastMasterTimer {
    private CountDownTimer timer;
    private TimerListener timerListener;

    public ToastMasterTimer(TimerListener timerListener) {
        this.timerListener = timerListener;
    }

    public void cancel() {
        timer.cancel();
        timerListener.onStop();
        timer = null;
    }

    public void start(final int minutes) {

        if (timer != null) {
            timer.cancel();
        }

        final TimeCalculator timeCalculator = new TimeCalculator(minutes);

        timer = new CountDownTimer(minutes * 60 * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerListener.showTimer(millisUntilFinished);

                if (timeCalculator.isTimeToShowYellowCard(millisUntilFinished)) {
                    timerListener.showYellowCard();
                    return;
                }

                if (timeCalculator.isTimeToShowGreenCard(millisUntilFinished)) {
                    timerListener.showGreenCard();
                }
            }

            @Override
            public void onFinish() {
                timerListener.showRedCard();
                timer = new CountDownTimer(timeCalculator.getLeeway() * 1000, 1000) {
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
}