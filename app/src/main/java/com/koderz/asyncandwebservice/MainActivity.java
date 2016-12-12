package com.koderz.asyncandwebservice;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn= (Button) findViewById(R.id.btn);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new MyAsync().execute();
            }
        });
    }

    private void printLog() {

        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                Log.i("TAG", "printLog ");
            }
        }
    }

    class MyAsync extends AsyncTask<Void,Void,Void>{
        String response;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("TAG", "printLog preexecute");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String url="http://www.tollycinenews.com/webservices/category-videos.php?category=comedy";
            try {
                String response=run(url);
                Log.i("TAG", "doInBackground: "+response);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);



        }
    }

}
