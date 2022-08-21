package com.example.youtubletest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<Video> myList;
    public CustomAdapter(Context context, int layout, List<Video> list)
    {
        myContext=context;
        myLayout=layout;
        myList=list;
    }
    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(myLayout,null);
        TextView tvTitle=view.findViewById(R.id.tvTitle);
        tvTitle.setText(myList.get(i).getName());
        TextView tvAuthor=view.findViewById(R.id.tvChanel);
        tvAuthor.setText(myList.get(i).getAuthor());
        ImageView imageView=view.findViewById(R.id.imageView);
        Glide.with(view).load(myList.get(i).getImg()).into(imageView);
        TextView tvDesc=view.findViewById(R.id.tvDesc);
        tvDesc.setText(myList.get(i).getDesc());
        return view;
    }
}
