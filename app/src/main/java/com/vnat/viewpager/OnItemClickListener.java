package com.vnat.viewpager;

import android.view.View;

public interface OnItemClickListener {
    void onItemClickListener(View view, int position);

    void onItemLongClickListener(View view, int position);
}