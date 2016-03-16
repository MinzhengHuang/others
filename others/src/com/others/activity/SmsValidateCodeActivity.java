package com.others.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;

import com.others.R;
import com.others.service.SmsObserver;

/**
 * 短信验证码自动填写功能的实现
 *
 * Created by huangminzheng on 16/3/15.
 */
public class SmsValidateCodeActivity extends Activity {

    public static final int MSG_RECEIVED_CODE = 1;
    private EditText metValidateCode = null;
    private SmsObserver mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsvalidatecode);

        metValidateCode = (EditText) findViewById(R.id.et_validateCode);

        mObserver = new SmsObserver(SmsValidateCodeActivity.this, mHandler);
        Uri uri = Uri.parse("content://sms");
        //注册短信的监听
        getContentResolver().registerContentObserver(uri, true, mObserver);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //解除注册短信的监听
        getContentResolver().unregisterContentObserver(mObserver);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG_RECEIVED_CODE) {
                String code = (String) msg.obj;
                metValidateCode.setText(code);
            }
        }
    };

}
