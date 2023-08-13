package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.content.ContextCompat;

public final class SpyService extends ForegroundService {
	public static void startService(Context context) {
		stopService(context);

		Log.d("Spy","starting service...");

		Intent serviceIntent = new Intent(context, SpyService.class);
		serviceIntent.putExtra(context.getString(R.string.extra_foreground_service_desc), context.getString(R.string.foreground_service_desc));
		ContextCompat.startForegroundService(context, serviceIntent);
	}

	public static void stopService(Context context) {
		Log.d("Spy","stopping service...");

		Intent serviceIntent = new Intent(context, SpyService.class);
		context.stopService(serviceIntent);
	}

	@Override
	protected void doInBackground() {
		Log.d("Spy", "hee hee");
		try {
			long spySleepTime = 1000;
			Thread.sleep(spySleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
