package cn.seabornlee.toastmastertimer;

public interface TimerListener {
    void showTimer(long millisUntilFinished);
    void showGreenCard();
    void showYellowCard();
    void showRedCard();
    void ringTheBell();
    void onStart();
    void onStop();
}
