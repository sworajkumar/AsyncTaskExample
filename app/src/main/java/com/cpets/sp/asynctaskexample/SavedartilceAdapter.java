package com.cpets.sp.asynctaskexample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class SavedartilceAdapter extends BaseAdapter {

    private ArrayList<Savedartilcemodel> appItmList;
    private Activity activity;
    private LayoutInflater inflater;
    private TextView savedarticlename,savedarticleauthor,savedarticledate,savedarticlecatagory;
    private LinearLayout mainlayout;
    String articleid;
    private ListView articlesaved;
    SavedartilceAdapter adapter;

    public SavedartilceAdapter(Activity activity, ArrayList<Savedartilcemodel> articleList, ListView articlesaved) {
        this.activity=activity;
        this.appItmList=articleList;
        this.articlesaved=articlesaved;
    }
    @Override
    public int getCount() {
        return appItmList.size();
    }

    @Override
    public Object getItem(int position) {
        return appItmList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater==null)
            inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
            convertView=inflater.inflate(R.layout.save_articles_lists,null);
        if (position % 2 == 1) {
            convertView.setBackground(ContextCompat.getDrawable(activity, R.drawable.orangeedittextbox));
            //convertView.setBackground(ContextCompat.getDrawable(activity, R.drawable.lightgrayedittextbox));
        } else {
            //convertView.setBackground(ContextCompat.getDrawable(activity, R.drawable.lightgrayedittextbox));
            convertView.setBackground(ContextCompat.getDrawable(activity, R.drawable.orangeedittextbox));
        }

        savedarticlename=(TextView)convertView.findViewById(R.id.savedarticlename);
        savedarticleauthor=(TextView)convertView.findViewById(R.id.savedarticleauthor);
        savedarticledate=(TextView)convertView.findViewById(R.id.savedarticledate);
        savedarticlecatagory=(TextView)convertView.findViewById(R.id.savedarticlecatagory);
        mainlayout=(LinearLayout) convertView.findViewById(R.id.savedlayout);

        Savedartilcemodel savedartilcemodel=appItmList.get(position);
        savedarticlename.setText(savedartilcemodel.getArticleName());
        savedarticleauthor.setText(savedartilcemodel.getAuthors());
        //savedarticledate.setText(AppHelper.ConvertDateFormatYYYYMMDD2DDMMYYYY(savedartilcemodel.getPublishDate()));
        savedarticledate.setText(savedartilcemodel.getPublishDate());
        savedarticlecatagory.setText(savedartilcemodel.getCategories());
        articleid=savedartilcemodel.getArticleid();

        return convertView;
    }
}
