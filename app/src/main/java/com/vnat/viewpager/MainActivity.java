package com.vnat.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    LinearLayout llIndicatorBanner;
    ViewPager vpBanner;
    ArrayList<String> urlBanner;
    BannerAdapter bannerAdapter;

    int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        for (int i = 0; i < urlBanner.size(); i++) {
            View view = createDot(this, i == 0 ? Color.BLUE : Color.WHITE);
            llIndicatorBanner.addView(view);
        }

        vpBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                for (int i = 0; i < urlBanner.size(); i++) {
                    llIndicatorBanner.getChildAt(i).getBackground().mutate().setTint(i == position ? Color.BLUE : Color.WHITE);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        bannerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(MainActivity.this, "Long Click "+position, Toast.LENGTH_SHORT).show();

            }

        });


        autoScrollViewPager();
    }

    private void autoScrollViewPager() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (currentPage >= urlBanner.size()) {
                            currentPage = 0;
                        }
                        vpBanner.setCurrentItem(currentPage++, true);
                    }
                });
            }
        }, 500, 3000);
    }


    private void init() {
        vpBanner = findViewById(R.id.vpBanner);
        llIndicatorBanner = findViewById(R.id.llIndicatorBanner);

        urlBanner = new ArrayList<>();
        urlBanner.add("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/5298bac0-b8bf-4c80-af67-725c1272dbb0/dd81df5-04364350-282a-441a-ac71-ff7674d70f6d.jpg/v1/fill/w_1192,h_670,q_70,strp/aladdin__2019__wallpaper_by_thekingblader995_dd81df5-pre.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOiIsImlzcyI6InVybjphcHA6Iiwib2JqIjpbW3siaGVpZ2h0IjoiPD0xMDgwIiwicGF0aCI6IlwvZlwvNTI5OGJhYzAtYjhiZi00YzgwLWFmNjctNzI1YzEyNzJkYmIwXC9kZDgxZGY1LTA0MzY0MzUwLTI4MmEtNDQxYS1hYzcxLWZmNzY3NGQ3MGY2ZC5qcGciLCJ3aWR0aCI6Ijw9MTkyMCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl19.C88sWzniR-3M8ep9SI3zEGhvrB8jlDMNb0XuTDHD3Co");
        urlBanner.add("https://lh3.googleusercontent.com/proxy/pAL37LPDPZ_t8owVQ5a8VjSbP8X0kHq356CvT9cD0NGHcqlzhASwoldZm-2arnUOjEIUVr_k60FRaq0NA_zxRvR09b0XgRhnlkbtpis7naYRUlCwJOHMZdaFezfCW6VJA_6TSWIckOtYdapnow6ePV1TgDtDDI6w8k-oIhfBz33KtdIO");
        urlBanner.add("https://cdn.collider.com/wp-content/uploads/2010/06/inception_movie_poster_banner_01.jpg");
        urlBanner.add("https://cdn.collider.com/wp-content/uploads/2010/06/inception_movie_poster_banner_04.jpg");

        bannerAdapter = new BannerAdapter(urlBanner);
        vpBanner.setAdapter(bannerAdapter);

    }

    View createDot(Context context, int color) {
        View dot = new View(context);
        ViewGroup.MarginLayoutParams dotParams = new ViewGroup.MarginLayoutParams(20, 20);
        dotParams.setMargins(20, 10, 20, 10);
        dot.setLayoutParams(dotParams);
        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.setTint(color);
        dot.setBackground(drawable);
        return dot;
    }

}