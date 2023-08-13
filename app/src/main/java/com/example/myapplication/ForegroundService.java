package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationCompat;

public abstract class ForegroundService extends Service {
	public static final String CHANNEL_ID = "ForegroundServiceChannel";
	private boolean serviceAlive = false;

	protected ForegroundService() {
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		startForeground(1337, buildServiceNotification(intent.getStringExtra(getString(R.string.extra_foreground_service_desc))));
		serviceAlive = true;

		startLooperThread();

		return START_NOT_STICKY;
	}

	private Notification buildServiceNotification(String contentText) {
		NotificationChannel serviceChannel = new NotificationChannel(
				CHANNEL_ID,
				"Foreground Service Channel",
				NotificationManager.IMPORTANCE_LOW
		);
		NotificationManager manager = getSystemService(NotificationManager.class);
		manager.createNotificationChannel(serviceChannel);
		Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this,
				0, notificationIntent, 0);

		return new NotificationCompat.Builder(this, CHANNEL_ID)
				.setContentTitle(getString(R.string.foreground_service_title))
				.setContentText(contentText)
				.setSmallIcon(R.drawable.ic_launcher_foreground)
				.setContentIntent(pendingIntent)
				.build();
	}

	private void startLooperThread() {
		Thread mThread = new Thread() {
			@Override
			public void run() {
				Log.d("looping thread", "started");
				while (serviceAlive) {
					Log.d("looping thread", "alive");
					doInBackground();
				}
				Log.d("looping thread", "service no longer alive, exited loop");
			}
		};
		mThread.start();
	}

	@WorkerThread
	protected abstract void doInBackground();

	@Override
	public void onDestroy() {
		super.onDestroy();
		serviceAlive = false;
	}

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
