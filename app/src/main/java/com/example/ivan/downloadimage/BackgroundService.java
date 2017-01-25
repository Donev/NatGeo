package com.example.ivan.downloadimage;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import static android.R.attr.bitmap;

/**
 * Created by Ivan on 24.1.2017.
 */
public class BackgroundService extends Service {

    boolean isRunning;
    private Context context;
    private Thread backgroundThread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        this.context = this;
        this.isRunning = false;
        this.backgroundThread = new Thread(myTask);
    }

    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {    //static?
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private  String readAll(Reader rd) throws IOException { // static??
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Service Started", Toast.LENGTH_LONG).show();
        if(!this.isRunning)
        {
            this.isRunning = true;
            this.backgroundThread.start();
        }

    return START_STICKY;
    }

    private Runnable myTask = new Runnable() {
        @Override
        public void run() {

            WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
            Bitmap bitmap = null;


        try{

                JSONObject jobject = readJsonFromUrl(
                        "http://www.nationalgeographic.com/photography/photo-of-the-day/_jcr_content/.gallery.json");
                JSONObject article = jobject.getJSONArray("items").getJSONObject(0);
                //String urlJson = article.getString("url") + article.getString("originalUrl");
                String urlJson = article.getString("url") + article.getJSONObject("sizes").getString("2048");

                //Download Image from URL
                InputStream input = new java.net.URL(urlJson).openStream(); //imgSrc
                //Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            myWallpaperManager.setBitmap(bitmap);
            System.out.println("Background service is running");

            }
            catch (Exception e){
                e.printStackTrace();
            }

            stopSelf();
        }
    };

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Stoped", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    //???????
   //WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
    //Bitmap bitmap = null;

}
