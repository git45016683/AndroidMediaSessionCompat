package com.tutsplus.backgroundaudio;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;

public class BackgroundAudioService  {
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

    private Application app = null;
    public void initApp(Application app) {
        this.app = app;
        initMediaSession();
    }

    public void initMediaSession() {
        ComponentName mediaButtonReceiver = new ComponentName(app, MediaButtonReceiver.class);
        mMediaSessionCompat = new MediaSessionCompat(app, "Tag", mediaButtonReceiver, null);
        mMediaSessionCompat.setCallback(mMediaSessionCallback);
        mMediaSessionCompat.setFlags( MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS | MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS );
    }
}
