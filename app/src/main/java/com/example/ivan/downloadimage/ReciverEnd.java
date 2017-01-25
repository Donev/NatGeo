package com.example.ivan.downloadimage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Ivan on 24.1.2017.
 */
public class ReciverEnd extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent background = new Intent(context, BackgroundService.class);
        context.stopService(background);
    }
}
