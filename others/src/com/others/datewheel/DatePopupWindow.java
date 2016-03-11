package com.others.datewheel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.others.R;

import java.util.Calendar;

/**
 *
 * @author he xiaohui
 * 2015-2-8 下午4:06:12
 */
@SuppressLint("ViewConstructor")
public class DatePopupWindow extends PopupWindow {

    private View mMenuView;
    private WheelView yearView, monthView, dayView;
    private NumericWheelAdapter yearAdapter, monthAdapter, dayAdapter;
    private OnDateSelectListener selectListener;
    private String title;

    public DatePopupWindow(Activity context, String title,
                           OnDateSelectListener selectListener) {
        super(context);
        this.title = title;
        this.selectListener = selectListener;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.dateselect_dialog, null);
        yearView = (WheelView) mMenuView.findViewById(R.id.year_view);
        monthView = (WheelView) mMenuView.findViewById(R.id.month_view);
        dayView = (WheelView) mMenuView.findViewById(R.id.day_view);
        TextView tvTitle = (TextView) mMenuView
                .findViewById(R.id.title_textview);
        tvTitle.setText(title);

        initListener();
        initView();

        // 设置按钮监听
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    private void initView() {
        int curYears = 0;
        int curMonth = 0;
        int day = 0;
        Calendar c = Calendar.getInstance();
        curYears = c.get(Calendar.YEAR);
        curMonth = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        yearAdapter = new NumericWheelAdapter(curYears - 50, curYears + 10);
        yearView.setAdapter(yearAdapter);
        yearView.setLabel("year");

        monthAdapter = new NumericWheelAdapter(1, 12);
        monthView.setAdapter(monthAdapter);
        monthView.setLabel("month");
        monthView.setCyclic(true);

        dayAdapter = new NumericWheelAdapter(1,
                c.getActualMaximum(Calendar.DAY_OF_MONTH));
        dayView.setAdapter(dayAdapter);
        dayView.setLabel("day");
        dayView.setCyclic(true);

        yearView.setCurrentYearItem(curYears);
        monthView.setCurrentItem(curMonth);
        dayView.setCurrentItem(day - 1);
    }

    protected void initListener() {
        yearView.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                refreshDay(newValue, monthView.getCurrentItem());
            }
        });
        monthView.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                refreshDay(yearView.getCurrentItem(), newValue);
            }
        });
        View btnClose = mMenuView.findViewById(R.id.close_button);
        btnClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        View btnOK = mMenuView.findViewById(R.id.ok_button);
        btnOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String value = changeTime(yearView.getCurrentYearValue())
                        + "-" + changeTime(monthView.getCurrentValue()) + "-"
                        + changeTime(dayView.getCurrentValue());
                if (null != selectListener) {
                    selectListener.onDateSelect(value);
                }
                dismiss();
            }
        });
    }

    public static String changeTime(int value) {
        return value < 10 ? ("0" + value) : (value + "");
    }

    private void refreshDay(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        dayAdapter.setMaxValue(c.getActualMaximum(Calendar.DAY_OF_MONTH));
        dayView.setAdapter(dayAdapter);
    }

    public void showWindow(View view) {
        showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    public interface OnDateSelectListener {
        void onDateSelect(String value);
    }
}
