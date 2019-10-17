package com.am1ne.airbnb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.blurry.Blurry;
import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rl_overview)
    RelativeLayout rl_overview;
    @BindView(R.id.rl_blur_container)
    RelativeLayout rl_blur_container;

    @BindView(R.id.cv_overview)
    CardView cv_overview;
    @BindView(R.id.cv_booknow)
    CardView cv_booknow;

    @BindView(R.id.img_blured)
    ImageView img_blured;
    @BindView(R.id.favourite)
    ImageView favourite;

    boolean favouriteChecked = false;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Func.getInstance().hideStatusBar(getWindow());
        setContentView(R.layout.activity_main);
        //customType(MainActivity.this,"right-to-left");
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        context = MainActivity.this;

        //StatusBarUtil.setTransparent(MainActivity.this);
        cv_booknow.setVisibility(View.INVISIBLE);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fadein);
        Animation animation2 = AnimationUtils.loadAnimation(context, R.anim.fadein);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Blurry.with(MainActivity.this).sampling(2).capture(rl_blur_container).into(img_blured);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cv_booknow.setVisibility(View.VISIBLE);
                cv_booknow.setAnimation(animation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        cv_overview.setAnimation(animation);
    }

    @OnClick(R.id.book_now)
    public void book_now() {
        Intent intent=new Intent(MainActivity.this,MoreDetail.class);
        startActivity(intent);
        CustomIntent.customType(this,"left-to-right");

    }

    @OnClick(R.id.favourite)
    public void favourite() {
        if (favouriteChecked) {
            favourite.setImageResource(R.drawable.ic_heart_icon);
            favouriteChecked=false;
        } else {
            favourite.setImageResource(R.drawable.ic_heart_icon_filled);
            favouriteChecked=true;
        }
    }
}
