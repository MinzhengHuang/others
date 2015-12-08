package com.others.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.others.CircleIndicatorActivity;
import com.others.QuantityViewActivity;
import com.others.R;
import com.others.countdowntimer.CountDownTimerActivity;

public class FirstFragment extends Fragment implements OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        view.findViewById(R.id.btn_CountDownTimer).setOnClickListener(this);
        view.findViewById(R.id.btn_CircleIndicator).setOnClickListener(this);
        view.findViewById(R.id.btn_QuantityView).setOnClickListener(this);
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
            default:
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }

    }
}
