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
 * @author hexiaohui
 */
public class DateWheelActivity extends Activity {
    TextView date_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datewheel);
        date_textview = (TextView) findViewById(R.id.date_textview);
        Button selectdate_button = (Button) findViewById(R.id.selectdate_button);

        Calendar c = Calendar.getInstance();
        date_textview.setText(c.get(Calendar.YEAR) + "-"
                + (c.get(Calendar.MONTH) + 1) + "-"
                + c.get(Calendar.DAY_OF_MONTH));
        selectdate_button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showDataView();
            }
        });
    }

    private DatePopupWindow window;

    private void showDataView() {
        window = new DatePopupWindow(this, "", new DatePopupWindow.OnDateSelectListener() {

            @Override
            public void onDateSelect(String value) {
                date_textview.setText(value);
            }
        });
        window.showWindow(date_textview);
    }
}
