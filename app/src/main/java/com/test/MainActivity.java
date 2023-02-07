package com.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.BehaviorSubject;

public class MainActivity extends AppCompatActivity {
    public static final BehaviorSubject<String> RX = BehaviorSubject.createDefault("Starting");
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView logV = findViewById(R.id.logView);
        logV.setLayoutManager(new LinearLayoutManager(this));
        Subscriber.init(logV);
    }

    @Override
    protected void onDestroy() {
        Subscriber.done();
        super.onDestroy();
        disposables.clear();
    }
}