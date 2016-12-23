package com.koderz.asyncandwebservice.asyncs;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.koderz.asyncandwebservice.CallBackInterfaces.GetContentCallBack;


import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetContentAsync extends AsyncTask<Void, Void, Void> {
    String response;

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    String url;
    Context mContext;
    public GetContentAsync(String url,Context mContext){
        this.url=url;
        this.mContext=mContext;

        initializeCallBack();
    }

    GetContentCallBack getContentCallBack;
    private void initializeCallBack() {
        getContentCallBack= (GetContentCallBack) mContext;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i("TAG", "printLog preexecute");
    }

    @Override
    protected Void doInBackground(Void... voids) {




        try {
            String response = run(url);
            //    Log.i("TAG", "doInBackground: "+response);

            this.response = response;
        } catch (IOException e) {
            e.printStackTrace();
        }








        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);


            getContentCallBack.onContentRecieved(response);

    }
}

