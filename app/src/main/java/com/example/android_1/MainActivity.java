package com.example.android_1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String TAGLifecycle = "ActivityLifecycle";
    private long onCreateTime, onStartTime, onResumeTime, onPauseTime, onStopTime, onDestroyTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        onCreateTime = System.currentTimeMillis();
        Log.d(TAGLifecycle, "onCreate() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        onStartTime = System.currentTimeMillis();
        Log.d(TAGLifecycle, "onStart() called");
        Log.d(TAGLifecycle, "Time difference between onCreate and onStart: " + (onStartTime - onCreateTime) + " ms");
    }

    @Override
    protected void onResume() {
        super.onResume();
        onResumeTime = System.currentTimeMillis();
        Log.d(TAGLifecycle, "onResume() called");
        Log.d(TAGLifecycle, "Time difference between onStart and onResume: " + (onResumeTime - onStartTime) + " ms");
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPauseTime = System.currentTimeMillis();
        Log.d(TAGLifecycle, "onPause() called");
        Log.d(TAGLifecycle, "Time difference between onResume and onPause: " + (onPauseTime - onResumeTime) + " ms");
    }

    @Override
    protected void onStop() {
        super.onStop();
        onStopTime = System.currentTimeMillis();
        Log.d(TAGLifecycle, "onStop() called");
        Log.d(TAGLifecycle, "Time difference between onPause and onStop: " + (onStopTime - onPauseTime) + " ms");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAGLifecycle, "onRestart() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroyTime = System.currentTimeMillis();
        Log.d(TAGLifecycle, "onDestroy() called");
        Log.d(TAGLifecycle, "Time difference between onStop and onDestroy: " + (onDestroyTime - onStopTime) + " ms");
    }

    public void onButtonClick(View view) {
        Log.d(TAG, "Button was clicked");
    }
}