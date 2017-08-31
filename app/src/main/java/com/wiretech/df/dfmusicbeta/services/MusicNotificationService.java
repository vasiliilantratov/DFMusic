package com.wiretech.df.dfmusicbeta.services;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MusicNotificationService extends Service {

    private static int PAUSE_ACTION = 0;
    private static int PLAY_ACTION = 1;
    private static int UPDATE_ACTION = 2;

    //public static Context context;
    private Notification notification;

    private void showNotification(int pos) {

//        RemoteViews views = new RemoteViews(getPackageName(), R.layout.notification);
//        RemoteViews bigViews = new RemoteViews(getPackageName(), R.layout.notification_big);
//
//        // надод попроваить!!!!!!!!11
//
//        Intent notificationIntent = new Intent(this, PlayActivity.class);
//        notificationIntent.putExtra(PlayActivity.EXTRA_FROM_NOTIFICATION_FLAG, true);
//        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        views.setOnClickPendingIntent(R.id.notification_base, pendingIntent);
//        bigViews.setOnClickPendingIntent(R.id.notification_base, pendingIntent);
//
//        Intent playIntent = new Intent(this, MusicNotificationService.class);
//        playIntent.setAction(Const.ACTION.PLAY_ACTION);
//        PendingIntent pplayIntent = PendingIntent.getService(this, 0, playIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Intent closeIntent = new Intent(this, MusicNotificationService.class);
//        closeIntent.setAction(Const.ACTION.STOPFOREGROUND_ACTION);
//        PendingIntent pcloseIntent = PendingIntent.getService(this, 0, closeIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Intent previousIntent = new Intent(this, MusicNotificationService.class);
//        previousIntent.setAction(Const.ACTION.PREVIOUSFOREGROUND_ACTION);
//        PendingIntent ppreviousIntent = PendingIntent.getService(this, 0, previousIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Intent nextIntent = new Intent(this, MusicNotificationService.class);
//        nextIntent.setAction(Const.ACTION.NEXTFOREGROUND_ACTION);
//        PendingIntent pnextIntent = PendingIntent.getService(this, 0, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        views.setOnClickPendingIntent(R.id.notification_play, pplayIntent);
//        views.setOnClickPendingIntent(R.id.notification_close, pcloseIntent);
//        views.setOnClickPendingIntent(R.id.notification_previous, ppreviousIntent);
//        views.setOnClickPendingIntent(R.id.notification_next, pnextIntent);
//
//        views.setTextViewText(R.id.notification_line_one, Player.instance.getSongName());
//        views.setTextViewText(R.id.notification_line_two, Player.instance.getSingerName());
//
//        bigViews.setOnClickPendingIntent(R.id.notification_play, pplayIntent);
//        bigViews.setOnClickPendingIntent(R.id.notification_close, pcloseIntent);
//        bigViews.setOnClickPendingIntent(R.id.notification_previous, ppreviousIntent);
//        bigViews.setOnClickPendingIntent(R.id.notification_next, pnextIntent);
//
//        bigViews.setTextViewText(R.id.notification_line_one, Player.instance.getSongName());
//        bigViews.setTextViewText(R.id.notification_line_two, Player.instance.getSingerName());
//
//        if (pos == PAUSE_ACTION) {
//            views.setImageViewResource(R.id.notification_play, R.drawable.ic_play);
//            bigViews.setImageViewResource(R.id.notification_play, R.drawable.ic_play);
//        }
//        if (pos == PLAY_ACTION) {
//            views.setImageViewResource(R.id.notification_play, R.drawable.ic_pause);
//            bigViews.setImageViewResource(R.id.notification_play, R.drawable.ic_pause);
//        }
//        if (pos == UPDATE_ACTION) {
//            views.setImageViewResource(R.id.notification_play, R.drawable.ic_play);
//            bigViews.setImageViewResource(R.id.notification_play, R.drawable.ic_play);
//        }
//        notification = new Notification.Builder(this)
//                .setPriority(Notification.PRIORITY_MAX)
//                .build();
//        notification.contentView = views;
//        notification.bigContentView = bigViews;
//        notification.flags = Notification.FLAG_ONGOING_EVENT;
//        notification.icon = R.mipmap.ic_launcher;
//        notification.contentIntent = pendingIntent;
//        startForeground(Const.NOTIFICATION_ID, notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //context = this;

//        if (intent.getAction().equals(Const.ACTION.PLAYFOREGROUND_ACTION)) {
//            showNotification(PLAY_ACTION);
//            //Player.start(this, radioChannels.mLinks[radioChannels.mIds.indexOf(radioChannels.mPlayRadioWithId)]);
//            Player.instance.play(this);
//        } else if (intent.getAction().equals(Const.ACTION.PLAY_ACTION)) {
//            if (Player.instance.getIsPlaying()) {
//                showNotification(PAUSE_ACTION);
//                Player.instance.pause();
//            } else {
//                if (Player.instance.getIsInterrupt()) {
//                    showNotification(PAUSE_ACTION);
//                    Player.instance.pause();
//                } else {
//                    if (Player.instance.getIsPreparing()) {
//                        showNotification(PAUSE_ACTION);
//                        Player.instance.pause();
//                    } else {
//                        showNotification(PLAY_ACTION);
//                        Player.instance.play(this);
//                    }
//                }
//            }
//        } else if (intent.getAction().equals(Const.ACTION.PLAYNEWFOREGROUND_ACTION)) {
//            showNotification(PLAY_ACTION);
//            Player.instance.play(this);
//        } else if (intent.getAction().equals(Const.ACTION.STOPFOREGROUND_ACTION)) {
//            Player.instance.stop();
//            stopForeground(true);
//            stopSelf();
//        } else if (intent.getAction().equals(Const.ACTION.AUDIOFOCUS_LOSS_ACTION)) {
//            Player.instance.pause();
//            showNotification(PAUSE_ACTION);
//        } else if (intent.getAction().equals(Const.ACTION.UPDATE_NOTIFICATION_ACTION)) {
//            showNotification(UPDATE_ACTION);
//        } else if (intent.getAction().equals(Const.ACTION.PREVIOUSFOREGROUND_ACTION)) {
//            MusicState.instance.setPreviousSong();
//            Player.instance.initNewSong();
//            if (Player.instance.getIsPlaying()) {
//                showNotification(PLAY_ACTION);
//                Player.instance.play(this);
//            } else {
//                showNotification(UPDATE_ACTION);
//            }
//        } else if (intent.getAction().equals(Const.ACTION.NEXTFOREGROUND_ACTION)) {
//            MusicState.instance.setNextSong();
//            Player.instance.initNewSong();
//            if (Player.instance.getIsPlaying()) {
//                showNotification(PLAY_ACTION);
//                Player.instance.play(this);
//            } else {
//                showNotification(UPDATE_ACTION);
//            }
//        }

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
