package com.others.activity;

import android.app.Activity;
import android.os.Bundle;

import com.explosionfield.ExplosionField;
import com.explosionfield.factory.ExplodeParticleFactory;
import com.explosionfield.factory.FallingParticleFactory;
import com.explosionfield.factory.FlyawayFactory;
import com.explosionfield.factory.VerticalAscentFactory;
import com.others.R;

/**
 * Android制作粒子爆炸特效
 *
 * http://blog.csdn.net/crazy__chen/article/details/50149619
 */
public class ExplosionFieldActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explosionfield);
       
        ExplosionField explosionField = new ExplosionField(this,new FallingParticleFactory());
        explosionField.addListener(findViewById(R.id.text));
        explosionField.addListener(findViewById(R.id.layout1));

        ExplosionField explosionField2 = new ExplosionField(this,new FlyawayFactory());
        explosionField2.addListener(findViewById(R.id.text2));
        explosionField2.addListener(findViewById(R.id.layout2));
        
        ExplosionField explosionField4 = new ExplosionField(this,new ExplodeParticleFactory());
        explosionField4.addListener(findViewById(R.id.text3));
        explosionField4.addListener(findViewById(R.id.layout3));
        
        ExplosionField explosionField5 = new ExplosionField(this,new VerticalAscentFactory());
        explosionField5.addListener(findViewById(R.id.text4));
        explosionField5.addListener(findViewById(R.id.layout4));
    }


}
