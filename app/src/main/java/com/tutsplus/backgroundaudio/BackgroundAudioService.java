package com.tutsplus.backgroundaudio;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

public class BackgroundAudioService  {
//public class BackgroundAudioService extends MediaBrowserServiceCompat  {
    public static final String TAG = "BackgroundAudioService";

    private MediaSessionCompat mMediaSessionCompat;
    private MediaSessionCompat.Callback mMediaSessionCallback = new MediaSessionCompat.Callback() {
        @Override
        public boolean onMediaButtonEvent(Intent mediaButtonEvent) {
            Log.v(TAG, mediaButtonEvent.getAction());
            Log.v(TAG, mediaButtonEvent.getParcelableExtra(Intent.EXTRA_KEY_EVENT).toString());
            return super.onMediaButtonEvent(mediaButtonEvent);
        }
        @Override
        public void onPlay() {
            Log.v(TAG, "onPlay");
            super.onPlay();
            mMediaSessionCompat.setActive(true);
        }
        @Override
        public void onPause() {
            Log.v(TAG, "onPause");
            super.onPause();
        }
    };

//    @Override
//    public void onCreate() {
//        super.onCreate();
//        initMediaSession();
//    }

    private Application app = null;
    public void initApp(Application app) {
        this.app = app;
        initMediaSession();
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mMediaSessionCompat.release();
//    }

    public void initMediaSession() {
//        ComponentName mediaButtonReceiver = new ComponentName(getApplicationContext(), MediaButtonReceiver.class);
        ComponentName mediaButtonReceiver = new ComponentName(app, MediaButtonReceiver.class);
//        mMediaSessionCompat = new MediaSessionCompat(getApplicatigetApplicationContext(), MediaButtonReceiver.class);
        mMediaSessionCompat = new MediaSessionCompat(app, "Tag", mediaButtonReceiver, null);

        mMediaSessionCompat.setCallback(mMediaSessionCallback);
        mMediaSessionCompat.setFlags( MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS | MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS );

//        Intent mediaButtonIntent = new Intent(Intent.ACTION_MEDIA_BUTTON);
//        mediaButtonIntent.setClass(this, MediaButtonReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, mediaButtonIntent, 0);
//        mMediaSessionCompat.setMediaButtonReceiver(pendingIntent);
//
//        setSessionToken(mMediaSessionCompat.getSessionToken());
    }

//    //Not important for general audio service, required for class
//    @Nullable
//    @Override
//    public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
//        if(TextUtils.equals(clientPackageName, getPackageName())) {
//            return new BrowserRoot(getString(R.string.app_name), null);
//        }
//
//        return null;
//    }
//
//    //Not important for general audio service, required for class
//    @Override
//    public void onLoadChildren(@NonNull String parentId, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {
////        result.sendResult(null);
//    }
}
