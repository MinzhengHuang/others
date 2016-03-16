package com.others.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.others.R;
import com.others.activity.AndroidCheckBoxActivity;
import com.others.activity.AndroidJSActivity;
import com.others.activity.CircleIndicatorActivity;
import com.others.activity.CircleProgressActivity;
import com.others.activity.DateWheelActivity;
import com.others.activity.IndexableListViewActivity;
import com.others.activity.JSBridgeActivity;
import com.others.activity.QuantityViewActivity;
import com.others.activity.SmsValidateCodeActivity;
import com.others.countdowntimer.CountDownTimerActivity;

public class FirstFragment extends Fragment implements OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        view.findViewById(R.id.btn_CountDownTimer).setOnClickListener(this);
        view.findViewById(R.id.btn_CircleIndicator).setOnClickListener(this);
        view.findViewById(R.id.btn_QuantityView).setOnClickListener(this);
        view.findViewById(R.id.btn_CircleProgress).setOnClickListener(this);
        view.findViewById(R.id.btn_JSBridge).setOnClickListener(this);
        view.findViewById(R.id.btn_AndroidJS).setOnClickListener(this);
        view.findViewById(R.id.btn_IndexableListView).setOnClickListener(this);
        view.findViewById(R.id.btn_AndroidCheckBox).setOnClickListener(this);
        view.findViewById(R.id.btn_DateWheelActivity).setOnClickListener(this);
        view.findViewById(R.id.btn_SmsValidateCode).setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_CountDownTimer:
                intent = new Intent(getActivity(), CountDownTimerActivity.class);
                break;
            case R.id.btn_CircleIndicator:
                intent = new Intent(getActivity(), CircleIndicatorActivity.class);
                break;
            case R.id.btn_QuantityView:
                intent = new Intent(getActivity(), QuantityViewActivity.class);
                break;
            case R.id.btn_CircleProgress:
                intent = new Intent(getActivity(), CircleProgressActivity.class);
                break;
            case R.id.btn_JSBridge:
                intent = new Intent(getActivity(), JSBridgeActivity.class);
                break;
            case R.id.btn_AndroidJS:
                intent = new Intent(getActivity(), AndroidJSActivity.class);
                break;
            case R.id.btn_IndexableListView:
                intent = new Intent(getActivity(), IndexableListViewActivity.class);
                break;
            case R.id.btn_AndroidCheckBox:
                intent = new Intent(getActivity(), AndroidCheckBoxActivity.class);
                break;
            case R.id.btn_DateWheelActivity:
                intent = new Intent(getActivity(), DateWheelActivity.class);
                break;
            case R.id.btn_SmsValidateCode:
                intent = new Intent(getActivity(), SmsValidateCodeActivity.class);
                break;
            default:
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }

    }
}
