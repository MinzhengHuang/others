package com.others.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.others.R;
import com.others.view.DownLoadBar;
import com.others.view.LoadingBar;

import java.util.Random;

/**
 * Created by huangminzheng on 16/4/18.
 */
public class LoadingBarActivity extends Activity {
    DownLoadBar dlb;
    int current = 0;
    private LoadingBar lb;
    private LoadingBar lbFailed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_loading);

        lb = (LoadingBar) findViewById(R.id.lb_loading);
        lbFailed = (LoadingBar) findViewById(R.id.lb_loadingfailed);
        findViewById(R.id.failed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lbFailed.loadingComplete(false);
            }
        });

        findViewById(R.id.success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lb.loadingComplete(true);
            }
        });

        dlb = (DownLoadBar) findViewById(R.id.dlb);
        findViewById(R.id.start_success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current = 0;
                handler.post(runnable);
            }
        });
        dlb.setListener(new DownLoadBar.ClickListener() {
            @Override
            public void pause() {
                handler.removeCallbacks(runnable);
            }

            @Override
            public void restart() {
                handler.post(runnable);
            }
        });
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (current < 100) {
                current += new Random().nextInt(5);
            }else {
                handler.removeCallbacks(runnable);
            }
            dlb.setmCurrentProgress(current);

            handler.postDelayed(runnable, 100);
        }
    };
}
