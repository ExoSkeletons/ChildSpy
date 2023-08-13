package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
	Button btnStartService, btnStopService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnStartService = findViewById(R.id.buttonStartService);
		btnStopService = findViewById(R.id.buttonStopService);
		btnStartService.setOnClickListener(v -> SpyService.startService(v.getContext()));
		btnStopService.setOnClickListener(v -> SpyService.stopService(v.getContext()));

		SpyService.startService(getApplicationContext());
	}
}
