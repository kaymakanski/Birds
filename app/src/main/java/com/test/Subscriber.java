package com.test;

import android.os.Handler;
import android.os.Looper;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import io.reactivex.disposables.Disposable;

public class Subscriber {
    private static RecyclerView v;
    private static Disposable subscriber;
    private static final List<String> LOGS = new CopyOnWriteArrayList<>();
    private static final AtomicInteger REF_COUNT = new AtomicInteger(0);
    private static final ReentrantLock LOCK = new ReentrantLock();

    static void init() {
        REF_COUNT.incrementAndGet();
    }

    static void init(RecyclerView v) {
        LOCK.lock();

        try {
            Subscriber.v = v;

            if (subscriber == null) {
                subscriber = MainActivity.RX.subscribe(s -> {
                    if (v != null) {
                        LOGS.add(s);
                        new Handler(Looper.getMainLooper()).post(() -> {
                            v.setAdapter(new LogViewHolderAdapter(v.getContext(), LOGS));
                            v.smoothScrollToPosition(LOGS.size() - 1);
                        });
                    }
                });

                REF_COUNT.set(0);
            } else {
                REF_COUNT.incrementAndGet();
            }
        } finally {
            LOCK.unlock();
        }
    }

    static void done() {
        LOCK.lock();

        try {
            if (REF_COUNT.decrementAndGet() == 0) {
                subscriber.dispose();
                subscriber = null;
            }
        } finally {
            LOCK.unlock();
        }
    }
}
