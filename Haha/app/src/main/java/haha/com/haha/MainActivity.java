package haha.com.haha;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in=new Intent();
                in.setClass(MainActivity.this,Main2Activity.class);
                startActivity(in);
                MainActivity.this.finish();
            }
        },4000);
    }

}
