package com.example.tgirardot.tetris_girardot;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.net.ContentHandler;
import java.util.ArrayList;

/**
 * Created by tgirardot on 27/06/17.
 */

public class ImageAdapterBitmap extends BaseAdapter {

    private Context mContext;
    private ArrayList<Bitmap> mThumbIds;

    public ImageAdapterBitmap(Context c, ArrayList<Bitmap> items) {
        mThumbIds = items;
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(0, 0, 0,0);
           // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(mThumbIds.get(position));
        return imageView;
    }
}
