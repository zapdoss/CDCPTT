package com.example.skirmish.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by skirmish on 25/8/17.
 */

public class ConfirmationGridAdapter extends BaseAdapter {
    ArrayList<Integer> qua,fre,imageId;
    Context context;
    //int [] imageId;
    private static LayoutInflater inflater=null;
    RadioButton slctd=null;

    public ConfirmationGridAdapter(Context mainActivity, ArrayList<Integer> List1, ArrayList<Integer> List2, ArrayList<Integer> prgmImages) {
        // TODO Auto-generated constructor stub
        qua=List1;
        fre=List2;
        context=mainActivity;
        imageId=prgmImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return fre.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView q,f;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ConfirmationGridAdapter.Holder holder=new ConfirmationGridAdapter.Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.single_row_item_3, null);
        holder.q=(TextView) rowView.findViewById(R.id.tv0_adapter3);
        holder.img=(ImageView) rowView.findViewById(R.id.iv_adapter3);
        holder.f=(TextView) rowView.findViewById(R.id.tv1_adapter3);
        //holder.sel=(RadioButton) rowView.findViewById(R.id.rb_adapter2);

        /*holder.sel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(selected != null)
                {
                    selected.setChecked(false);
                }

                holder.sel.setChecked(true);
                selected = holder.sel;
            }
        });*/

        holder.q.setText("Quantity: "+qua.get(position).toString());
        holder.f.setText("Frequency: "+fre.get(position).toString());
        holder.img.setImageResource(imageId.get(position));

        return rowView;
    }
}
