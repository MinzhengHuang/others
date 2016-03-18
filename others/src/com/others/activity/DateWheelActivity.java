package com.others.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.others.R;
import com.others.datewheel.DatePopupWindow;

import java.util.Calendar;

/**
 * 仿IOS日期选择
 *
 * @author hexiaohui
 */
public class DateWheelActivity extends Activity {
    private TextView tvDate;
    private DatePopupWindow window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datewheel);
        tvDate = (TextView) findViewById(R.id.tv_date);
        Button btnSelectdate = (Button) findViewById(R.id.selectdate_button);

        Calendar c = Calendar.getInstance();
        tvDate.setText(c.get(Calendar.YEAR) + "-"
                + (c.get(Calendar.MONTH) + 1) + "-"
                + c.get(Calendar.DAY_OF_MONTH));
        btnSelectdate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showDataView();
            }
        });
    }

    private void showDataView() {
        window = new DatePopupWindow(this, "", new DatePopupWindow.OnDateSelectListener() {

            @Override
            public void onDateSelect(String value) {
                tvDate.setText(value);
            }
        });
        window.showWindow(tvDate);
    }
}
