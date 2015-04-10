package cn.seabornlee.toastmastertimer;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;
import static java.lang.String.format;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {

    @InjectView(R.id.tv_timer)
    private TextView tv_countDownTimer;

    @InjectView(R.id.btn_start)
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ToastMasterTimer timer = new ToastMasterTimer(new TimerListener() {
            @Override
            public void showTimer(long millisUntilFinished) {
                tv_countDownTimer.setText(formatTime(millisUntilFinished));
            }

            @Override
            public void showGreenCard() {
                setBackgroundColor(GREEN);
            }

            @Override
            public void showYellowCard() {
                setBackgroundColor(YELLOW);
            }

            @Override
            public void showRedCard() {
                setBackgroundColor(RED);
            }

            @Override
            public void ringTheBell() {

            }

            @Override
            public void onStart() {
                btn_start.setText("Stop");
            }

            @Override
            public void onStop() {
                btn_start.setText("Start");
            }

            private String formatTime(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / 60 / 1000);
                int seconds = (int) (millisUntilFinished / 1000 % 60);
                return format("%02d:%02d", minutes, seconds);
            }
        });

        timer.setTimeInMinutes(1);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.toggle();
            }
        });
    }

    private void setBackgroundColor(int color) {
        tv_countDownTimer.getRootView().setBackgroundColor(color);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
