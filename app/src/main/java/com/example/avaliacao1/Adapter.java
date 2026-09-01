package com.example.avaliacao1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class Adapter extends BaseAdapter {
    private Context context;
    private int[] lista;

    public Adapter(Context context, int[] lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.length;
    }

    @Override
    public Object getItem(int position) {
        return lista[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv = new ImageView(context);
        iv.setImageResource(lista[position]);
        iv.setLayoutParams(new ViewGroup.LayoutParams(250,250));
        iv.setPadding(5,5,5,5);
        return iv;
    }
}
