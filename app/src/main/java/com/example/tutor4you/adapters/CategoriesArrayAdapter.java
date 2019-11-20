package com.example.tutor4you.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;


public class CategoriesArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] categories;

    public CategoriesArrayAdapter(Context mContext, int resource, String[] categories) {
        super(mContext, resource);
        this.mContext = mContext;
        this.categories = categories;

    }

    @Override
    public Object getItem(int position) {
        String category= categories[position];
        return String.format("%s", category);
    }

    @Override
    public int getCount() {
        return categories.length;
    }

}
