package com.example.ivan.downloadimage;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;


/**
 * Created by Stefan on 23.1.2017.
 */

public class Reciever extends BroadcastReceiver{


    public  JSONObject readJsonFromUrl(String url) throws IOException, JSONException {    //static?
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
    public void onReceive(Context context, Intent intent) {
        Bitmap bitmap = null;


        WallpaperManager myWallpaperManager = WallpaperManager.getInstance(context);
        try{
<<<<<<< HEAD
=======
            Toast.makeText(context,"TEKST",Toast.LENGTH_LONG).show();
>>>>>>> b6f7f544fc430fcc10828d01b02ea0ab8487be9c
            Intent background = new Intent(context, BackgroundService.class);
            context.startService(background);


          /*  JSONObject jobject = readJsonFromUrl(
                    "http://www.nationalgeographic.com/photography/photo-of-the-day/_jcr_content/.gallery.json");
            JSONObject article = jobject.getJSONArray("items").getJSONObject(0);
            //String urlJson = article.getString("url") + article.getString("originalUrl");
            String urlJson = article.getString("url") + article.getJSONObject("sizes").getString("2048");*/




            //Download Image from URL
          /*  InputStream input = new java.net.URL(urlJson).openStream(); //imgSrc
            //Decode Bitmap
           bitmap = BitmapFactory.decodeStream(input);

            myWallpaperManager.setBitmap(bitmap); //ja stava slikata kako background
            */
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
