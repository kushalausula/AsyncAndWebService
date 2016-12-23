package com.koderz.asyncandwebservice;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.koderz.asyncandwebservice.CallBackInterfaces.GetContentCallBack;
import com.koderz.asyncandwebservice.asyncs.GetContentAsync;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements GetContentCallBack{

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        Button btn = (Button) findViewById(R.id.btn);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "http://www.tollycinenews.com/webservices/category-videos.php?category=comedy";


                new GetContentAsync(url, mContext).execute();
            }
        });
    }

    private void printLog() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Log.i("TAG", "printLog ");
            }
        }
    }


    ArrayList<SingleArticle> singleArticles;

    private void parseJson(String response) throws JSONException {

        singleArticles = new ArrayList<>();

        JSONObject jObj = new JSONObject(response);
        JSONArray jsonArray = new JSONArray();
        jsonArray = jObj.getJSONArray("info");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jSubObj = new JSONObject(jsonArray.getJSONObject(i).toString());
            SingleArticle singleArticle = new SingleArticle();
            Log.i("TAG", "parseJson: title " + jSubObj.getString("title"));
            Log.i("TAG", "parseJson: link " + jSubObj.getString("link"));
            singleArticle.link = jSubObj.getString("link");
            singleArticle.title = jSubObj.getString("title");
            singleArticles.add(singleArticle);
        }


        loadRecycler(singleArticles);

    }

    private void loadRecycler(ArrayList<SingleArticle> singleArticles) {


        RecyclerView rvRvData = (RecyclerView) findViewById(R.id.rvData);
        Context mContext;
        mContext = this;

        RecyclerView.LayoutManager manager;
        manager = new LinearLayoutManager(mContext);
        // manager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
//        manager = new GridLayoutManager(mContext,2);


        // manager = new StaggeredGridLayoutManager(3,LinearLayoutManager.VERTICAL);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(mContext, singleArticles);


        rvRvData.setLayoutManager(manager);
        rvRvData.setAdapter(recyclerAdapter);
    }

    @Override
    public void onContentRecieved(String data) {
        try {
            parseJson(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
