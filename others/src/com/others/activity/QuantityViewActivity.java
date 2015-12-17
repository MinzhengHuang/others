package com.others.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.others.R;
import com.others.view.QuantityView;


public class QuantityViewActivity extends Activity implements QuantityView.OnQuantityChangeListener {

    private int pricePerProduct = 180;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantityview);

        final QuantityView quantityViewDefault = (QuantityView) findViewById(R.id.quantityView_default);
        quantityViewDefault.setOnQuantityChangeListener(this);
        quantityViewDefault.setQuantityClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QuantityViewActivity.this);
                builder.setTitle("Change Quantity");

                View inflate = LayoutInflater.from(QuantityViewActivity.this).inflate(R.layout.custom_dialog_change_quantity, null, false);
                final EditText et = (EditText) inflate.findViewById(R.id.et_qty);
                final TextView tvPrice = (TextView) inflate.findViewById(R.id.tv_price);
                final TextView tvTotal = (TextView) inflate.findViewById(R.id.tv_total);

                et.setText(String.valueOf(quantityViewDefault.getQuantity()));
                tvPrice.setText("$ " + pricePerProduct);
                tvTotal.setText("$ " + quantityViewDefault.getQuantity() * pricePerProduct);


                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (TextUtils.isEmpty(s)) return;

                        int intNewQuantity = Integer.parseInt(s.toString());
                        tvTotal.setText("$ " + intNewQuantity * pricePerProduct);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                builder.setView(inflate);
                builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newQuantity = et.getText().toString();
                        if (TextUtils.isEmpty(newQuantity)) return;

                        int intNewQuantity = Integer.parseInt(newQuantity);

                        quantityViewDefault.setQuantity(intNewQuantity);
                    }
                }).setNegativeButton("Cancel", null);
                builder.show();
            }
        });

        QuantityView quantityViewCustom1 = (QuantityView) findViewById(R.id.quantityView_custom_1);
        quantityViewCustom1.setOnQuantityChangeListener(this);

        QuantityView quantityViewCustom2 = (QuantityView) findViewById(R.id.quantityView_custom_2);
        quantityViewCustom2.setOnQuantityChangeListener(this);

    }

    @Override
    public void onQuantityChanged(int newQuantity, boolean programmatically) {
        Toast.makeText(QuantityViewActivity.this, "Quantity: " + newQuantity, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLimitReached() {
        Log.d(getClass().getSimpleName(), "Limit reached");
    }

}
