package com.example.lukasz.productscanandcompare;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Lukasz on 2017-10-25.
 */

public class ProduktAdapter extends ArrayAdapter<Produkt>
{
    Context context;
    int layoutResourceId;
    Produkt data[] = null;

    public ProduktAdapter(Context context, int layoutResourceId, Produkt[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RowBeanHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RowBeanHolder();
            holder.txtId = (TextView)row.findViewById(R.id.txtId);
            holder.txtNazwa = (TextView)row.findViewById(R.id.txtNazwa);
            holder.txtKod = (TextView)row.findViewById(R.id.txtKod);

            row.setTag(holder);
        }
        else
        {
            holder = (RowBeanHolder)row.getTag();
        }

        Produkt object = data[position];
        holder.txtId.setText(String.valueOf(object.getId()));
        holder.txtNazwa.setText(object.getNazwa());
        holder.txtKod.setText(object.getKod());

        return row;
    }

    static class RowBeanHolder
    {
        TextView txtId;
        TextView txtNazwa;
        TextView txtKod;
    }
}
