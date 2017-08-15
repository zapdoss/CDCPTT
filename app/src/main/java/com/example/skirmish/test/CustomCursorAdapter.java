package com.example.skirmish.test;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;


public class CustomCursorAdapter extends CursorAdapter {

    @SuppressWarnings("deprecation")
    public CustomCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // when the view will be created for first time,
        // we need to tell the adapters, how each item will look
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.single_row_item, parent, false);

        return retView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // here we are setting our data
        // that means, take the data from the cursor and put it in views

        TextView textViewPersonName = (TextView) view.findViewById(R.id.tv_person_name);
        textViewPersonName.setText(cursor.getString(1));
        TextView textViewUserName = (TextView) view.findViewById(R.id.tv_person_user);
        textViewUserName.setText(cursor.getString(2));
        if(!cursor.isNull(4)) {
            byte[] outImage = cursor.getBlob(4);
            ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            ImageView i = (ImageView)view.findViewById(R.id.imageView);
            i.setImageBitmap(theImage);
        }
    }
}