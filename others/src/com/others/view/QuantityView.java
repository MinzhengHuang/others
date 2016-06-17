package com.others.view;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.others.R;

/**
 * Quantity view to add and remove quantities
 */
public class QuantityView extends LinearLayout implements View.OnClickListener {

    private Drawable quantityBackground, addButtonBackground, removeButtonBackground;
    private String addButtonText, removeButtonText;
    private int quantity, maxQuantity, minQuantity;
    private int quantityPadding;
    private int quantityTextColor, addButtonTextColor, removeButtonTextColor;
    private Button mbtnAdd, mbtnRemove;
    private TextView mtvQuantity;

    public interface OnQuantityChangeListener {
        void onQuantityChanged(int newQuantity, boolean programmatically);

        void onLimitReached();
    }

    private OnQuantityChangeListener onQuantityChangeListener;
    private OnClickListener mTextViewClickListener;

    public QuantityView(Context context) {
        super(context);
        init(null, 0);
    }

    public QuantityView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public QuantityView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.QuantityView, defStyle, 0);

        addButtonText = getResources().getString(R.string.qv_add);
        if (a.hasValue(R.styleable.QuantityView_qv_addButtonText)) {
            addButtonText = a.getString(R.styleable.QuantityView_qv_addButtonText);
        }
        addButtonBackground = getResources().getDrawable(R.drawable.qv_btn_selector);
        if (a.hasValue(R.styleable.QuantityView_qv_addButtonBackground)) {
            addButtonBackground = a.getDrawable(R.styleable.QuantityView_qv_addButtonBackground);
        }
        addButtonTextColor = a.getColor(R.styleable.QuantityView_qv_addButtonTextColor, Color.BLACK);

        removeButtonText = getResources().getString(R.string.qv_remove);
        if (a.hasValue(R.styleable.QuantityView_qv_removeButtonText)) {
            removeButtonText = a.getString(R.styleable.QuantityView_qv_removeButtonText);
        }
        removeButtonBackground = getResources().getDrawable(R.drawable.qv_btn_selector);
        if (a.hasValue(R.styleable.QuantityView_qv_removeButtonBackground)) {
            removeButtonBackground = a.getDrawable(R.styleable.QuantityView_qv_removeButtonBackground);
        }
        removeButtonTextColor = a.getColor(R.styleable.QuantityView_qv_removeButtonTextColor, Color.BLACK);

        quantity = a.getInt(R.styleable.QuantityView_qv_quantity, 0);
        maxQuantity = a.getInt(R.styleable.QuantityView_qv_maxQuantity, Integer.MAX_VALUE);
        minQuantity = a.getInt(R.styleable.QuantityView_qv_minQuantity, 0);

        quantityPadding = (int) a.getDimension(R.styleable.QuantityView_qv_quantityPadding, pxFromDp(24));
        quantityTextColor = a.getColor(R.styleable.QuantityView_qv_quantityTextColor, Color.BLACK);
        quantityBackground = getResources().getDrawable(R.drawable.qv_bg_selector);
        if (a.hasValue(R.styleable.QuantityView_qv_quantityBackground)) {
            quantityBackground = a.getDrawable(R.styleable.QuantityView_qv_quantityBackground);
        }

        a.recycle();
        int dp10 = pxFromDp(10);

        mbtnAdd = new Button(getContext());
        mbtnAdd.setGravity(Gravity.CENTER);
        mbtnAdd.setPadding(dp10, dp10, dp10, dp10);
        mbtnAdd.setMinimumHeight(0);
        mbtnAdd.setMinimumWidth(0);
        mbtnAdd.setMinHeight(0);
        mbtnAdd.setMinWidth(0);
        setAddButtonBackground(addButtonBackground);
        setAddButtonText(addButtonText);
        setAddButtonTextColor(addButtonTextColor);

        mbtnRemove = new Button(getContext());
        mbtnRemove.setGravity(Gravity.CENTER);
        mbtnRemove.setPadding(dp10, dp10, dp10, dp10);
        mbtnRemove.setMinimumHeight(0);
        mbtnRemove.setMinimumWidth(0);
        mbtnRemove.setMinHeight(0);
        mbtnRemove.setMinWidth(0);
        setRemoveButtonBackground(removeButtonBackground);
        setRemoveButtonText(removeButtonText);
        setRemoveButtonTextColor(removeButtonTextColor);

        mtvQuantity = new TextView(getContext());
        mtvQuantity.setGravity(Gravity.CENTER);
        setQuantityTextColor(quantityTextColor);
        setQuantity(quantity);
        setQuantityBackground(quantityBackground);
        setQuantityPadding(quantityPadding);

        setOrientation(HORIZONTAL);

        addView(mbtnRemove, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(mtvQuantity, LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        addView(mbtnAdd, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        mbtnAdd.setOnClickListener(this);
        mbtnRemove.setOnClickListener(this);
        mtvQuantity.setOnClickListener(this);
    }


    public void setQuantityClickListener(OnClickListener ocl) {
        mTextViewClickListener = ocl;
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnAdd) {
            if (quantity + 1 > maxQuantity) {
                if (onQuantityChangeListener != null) onQuantityChangeListener.onLimitReached();
            } else {
                quantity += 1;
                mtvQuantity.setText(String.valueOf(quantity));
                if (onQuantityChangeListener != null)
                    onQuantityChangeListener.onQuantityChanged(quantity, false);
            }
        } else if (v == mbtnRemove) {
            if (quantity - 1 < minQuantity) {
                if (onQuantityChangeListener != null) onQuantityChangeListener.onLimitReached();
            } else {
                quantity -= 1;
                mtvQuantity.setText(String.valueOf(quantity));
                if (onQuantityChangeListener != null)
                    onQuantityChangeListener.onQuantityChanged(quantity, false);
            }
        } else if (v == mtvQuantity) {
            if (mTextViewClickListener != null) {
                mTextViewClickListener.onClick(v);
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Change Quantity");

            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.qv_dialog_changequantity, null, false);
            final EditText et = (EditText) inflate.findViewById(R.id.qv_et_change_qty);
            et.setText(String.valueOf(quantity));

            builder.setView(inflate);
            builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String newQuantity = et.getText().toString();
                    if (isNumber(newQuantity)) {
                        int intNewQuantity = Integer.parseInt(newQuantity);
                        setQuantity(intNewQuantity);
                    }
                }
            }).setNegativeButton("Cancel", null);
            builder.show();
        }
    }


    public OnQuantityChangeListener getOnQuantityChangeListener() {
        return onQuantityChangeListener;
    }

    public void setOnQuantityChangeListener(OnQuantityChangeListener onQuantityChangeListener) {
        this.onQuantityChangeListener = onQuantityChangeListener;
    }

    public Drawable getQuantityBackground() {
        return quantityBackground;
    }

    public void setQuantityBackground(Drawable quantityBackground) {
        this.quantityBackground = quantityBackground;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mtvQuantity.setBackground(quantityBackground);
        } else {
            mtvQuantity.setBackgroundDrawable(quantityBackground);
        }
    }

    public Drawable getAddButtonBackground() {
        return addButtonBackground;
    }

    public void setAddButtonBackground(Drawable addButtonBackground) {
        this.addButtonBackground = addButtonBackground;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mbtnAdd.setBackground(addButtonBackground);
        } else {
            mbtnAdd.setBackgroundDrawable(addButtonBackground);
        }
    }

    public Drawable getRemoveButtonBackground() {
        return removeButtonBackground;
    }

    public void setRemoveButtonBackground(Drawable removeButtonBackground) {
        this.removeButtonBackground = removeButtonBackground;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mbtnRemove.setBackground(removeButtonBackground);
        } else {
            mbtnRemove.setBackgroundDrawable(removeButtonBackground);
        }
    }

    public String getAddButtonText() {
        return addButtonText;
    }

    public void setAddButtonText(String addButtonText) {
        this.addButtonText = addButtonText;
        mbtnAdd.setText(addButtonText);
    }

    public String getRemoveButtonText() {
        return removeButtonText;
    }

    public void setRemoveButtonText(String removeButtonText) {
        this.removeButtonText = removeButtonText;
        mbtnRemove.setText(removeButtonText);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        boolean limitReached = false;

        if (quantity > maxQuantity) {
            quantity = maxQuantity;
            limitReached = true;
            if (onQuantityChangeListener != null) onQuantityChangeListener.onLimitReached();
        }
        if (quantity < minQuantity) {
            quantity = minQuantity;
            limitReached = true;
            if (onQuantityChangeListener != null) onQuantityChangeListener.onLimitReached();
        }


        if (!limitReached && onQuantityChangeListener != null)
            onQuantityChangeListener.onQuantityChanged(quantity, true);

        this.quantity = quantity;
        mtvQuantity.setText(String.valueOf(this.quantity));
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public int getQuantityPadding() {
        return quantityPadding;
    }

    public void setQuantityPadding(int quantityPadding) {
        this.quantityPadding = quantityPadding;
        mtvQuantity.setPadding(quantityPadding, 0, quantityPadding, 0);
    }

    public int getQuantityTextColor() {
        return quantityTextColor;
    }

    public void setQuantityTextColor(int quantityTextColor) {
        this.quantityTextColor = quantityTextColor;
        mtvQuantity.setTextColor(quantityTextColor);
    }

    public void setQuantityTextColorRes(int quantityTextColorRes) {
        this.quantityTextColor = getResources().getColor(quantityTextColorRes);
        mtvQuantity.setTextColor(quantityTextColor);
    }

    public int getAddButtonTextColor() {
        return addButtonTextColor;
    }

    public void setAddButtonTextColor(int addButtonTextColor) {
        this.addButtonTextColor = addButtonTextColor;
        mbtnAdd.setTextColor(addButtonTextColor);
    }

    public void setAddButtonTextColorRes(int addButtonTextColorRes) {
        this.addButtonTextColor = getResources().getColor(addButtonTextColorRes);
        mbtnAdd.setTextColor(addButtonTextColor);
    }

    public int getRemoveButtonTextColor() {
        return removeButtonTextColor;
    }

    public void setRemoveButtonTextColor(int removeButtonTextColor) {
        this.removeButtonTextColor = removeButtonTextColor;
        mbtnRemove.setTextColor(removeButtonTextColor);
    }

    public void setRemoveButtonTextColorRes(int removeButtonTextColorRes) {
        this.removeButtonTextColor = getResources().getColor(removeButtonTextColorRes);
        mbtnRemove.setTextColor(removeButtonTextColor);
    }

    private int dpFromPx(final float px) {
        return (int) (px / getResources().getDisplayMetrics().density);
    }

    private int pxFromDp(final float dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

    private boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
