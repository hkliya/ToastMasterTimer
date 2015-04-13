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

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;
import static java.lang.String.format;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {

    @InjectView(R.id.tv_timer)
    private TextView tv_countDownTimer;

    @InjectView(R.id.btn_stop)
    private Button btn_stop;

    @InjectView(R.id.btn_time_2mins)
    private Button btn_2_mins;

    @InjectView(R.id.btn_time_3mins)
    private Button btn_3_mins;

    @InjectView(R.id.btn_time_5mins)
    private Button btn_5_mins;

    @InjectView(R.id.btn_time_7mins)
    private Button btn_7_mins;

    @InjectView(R.id.btn_time_8mins)
    private Button btn_8_mins;

    @InjectView(R.id.btn_time_10mins)
    private Button btn_10_mins;

    private ToastMasterTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindEvents();
        timer = new ToastMasterTimer(new TimerListener() {
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
                tv_countDownTimer.setText(getString(R.string.ring_the_bell));
                onStop();
            }

            @Override
            public void onStart() {
                setBackgroundColor(BLACK);
                btn_stop.setEnabled(true);
            }

            @Override
            public void onStop() {
                btn_stop.setEnabled(false);
            }

            private String formatTime(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / 60 / 1000);
                int seconds = (int) (millisUntilFinished / 1000 % 60);
                return format("%02d:%02d", minutes, seconds);
            }
        });

    }

    private void bindEvents() {
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
            }
        });

        btn_2_mins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start(2);
            }
        });

        btn_3_mins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start(3);
            }
        });

        btn_5_mins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start(5);
            }
        });

        btn_7_mins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start(7);
            }
        });

        btn_8_mins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start(8);
            }
        });

        btn_10_mins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start(10);
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
