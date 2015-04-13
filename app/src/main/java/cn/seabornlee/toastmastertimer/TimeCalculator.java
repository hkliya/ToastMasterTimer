package cn.seabornlee.toastmastertimer;

public class TimeCalculator {
    private int minutes;

    public TimeCalculator(int minutes) {
        this.minutes = minutes;
    }

    public int getLeeway() {
        int leeway = 15;
        if (minutes > 3) {
            leeway = 30;
        }

        return leeway;
    }

    public boolean isTimeToShowYellowCard(long millisUntilFinished) {
        int threshold = 30;
        if (minutes > 3) {
            threshold = 60;
        }

        return millisUntilFinished <= threshold * 1000;
    }

    public boolean isTimeToShowGreenCard(long millisUntilFinished) {
        int threshold = 60;
        if (minutes > 3) {
            threshold = 2 * 60;
        }

        return millisUntilFinished <= threshold * 1000;
    }
}
