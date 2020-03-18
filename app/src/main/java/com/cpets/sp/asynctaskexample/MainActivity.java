package com.cpets.sp.asynctaskexample;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ListView articlesaved;
    private RelativeLayout rlsuggestlist;
    String session;
    public SavedartilceAdapter adapter;
    private BroadcastReceiver mNetworkReceiver;
    static TextView tv_check_connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articlesaved=(ListView)findViewById(R.id.articlesaved);
        rlsuggestlist=(RelativeLayout)findViewById(R.id.rlsuggestlist);
        tv_check_connection=(TextView)findViewById(R.id.tv_check_connection);

        ConnectivityManager cm = (ConnectivityManager)MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            SavedArticleAsynctask runner3=new SavedArticleAsynctask();
            runner3.execute();
        }else {
            Toast.makeText(MainActivity.this, "No Internet Connection..", Toast.LENGTH_SHORT).show();
        }
    }

    private class SavedArticleAsynctask extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;
        private ArrayList<Savedartilcemodel> articleList;
        private  String date;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,"Please wait","loading...");
            date=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        }
        @Override
        protected String doInBackground(String... params) {
            String content= AsyncTaskHelper.makeServiceCall("http://cpetsol.com/pp/userRest/showSavedArticles/900000050501","GET",null);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            articleList = new ArrayList<Savedartilcemodel>();
            try {
                JSONObject jsonObject=new JSONObject(result);
                JSONArray jsonArray=jsonObject.getJSONArray("savedArtiList");
                if (jsonArray.length()>0){
                    rlsuggestlist.setVisibility(View.GONE);
                    for (int i=0;i<jsonArray.length();i++){
                        Savedartilcemodel savedartilcemodel=new Savedartilcemodel();
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        savedartilcemodel.setArticleid(jsonObject1.getString("id"));
                        savedartilcemodel.setAuthors(jsonObject1.getString("authors"));
                        savedartilcemodel.setPublishDate(jsonObject1.getString("publishDate"));
                        savedartilcemodel.setArticleName(jsonObject1.getString("articleName"));
                        savedartilcemodel.setCategories(jsonObject1.getString("categories"));
                        savedartilcemodel.setViewStatus(jsonObject1.getString("viewStatus"));
                        articleList.add(savedartilcemodel);
                        //GetListData(session);
                    }
                }else {
                    rlsuggestlist.setVisibility(View.VISIBLE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter=new SavedartilceAdapter(MainActivity.this,articleList,articlesaved);
            articlesaved.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            progressDialog.dismiss();

            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }//onPostExecute
    }//AsuncTask class

}
