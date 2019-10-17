package com.am1ne.airbnb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.am1ne.airbnb.adapter.photosAdapter;
import maes.tech.intentanim.CustomIntent;

import java.util.ArrayList;
public class MoreDetail extends AppCompatActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.rv_photos)
    RecyclerView rv_photos;
    photosAdapter photosAdapter;
    ArrayList<Integer> photos_urls;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Func.getInstance().hideStatusBar(getWindow());
        setContentView(R.layout.activity_more_detail);
        //customType(MoreDetail.this,"left-to-right");

        ButterKnife.bind(this);

        init();
    }

    private void init() {
        context = MoreDetail.this;
        photos_urls = new ArrayList<>();
        photos_urls.add(R.drawable.image1);
        photos_urls.add(R.drawable.image2);
        photos_urls.add(R.drawable.image3);
        photos_urls.add(R.drawable.image4);
        photos_urls.add(R.drawable.image5);

        Log.d("tagini", "init: " + photos_urls.size());

        photosAdapter = new photosAdapter(context, photos_urls);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rv_photos.setLayoutManager(layoutManager);
        rv_photos.setAdapter(photosAdapter);
    }

    @OnClick(R.id.back)
    public void back() {
        Intent intent=new Intent(MoreDetail.this,MainActivity.class);
        startActivity(intent);
        CustomIntent.customType(this,"right-to-left");
    }
}
