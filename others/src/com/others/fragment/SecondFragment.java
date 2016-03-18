package com.others.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.others.R;
import com.others.pulldownlistview.PulldownListviewActivity;

public class SecondFragment extends Fragment implements OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        view.findViewById(R.id.btn_PullDownListView).setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_PullDownListView:
                intent = new Intent(getActivity(), PulldownListviewActivity.class);
                break;

            default:
                break;
        }
        startActivity(intent);
    }
}
