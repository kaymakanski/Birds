package com.test;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_second, container, false);

        Bird bird = SecondFragmentArgs.fromBundle(getArguments()).getBird();

        ImageView imageView = v.findViewById(R.id.birdImage);
        TextView textView = v.findViewById(R.id.txtViewBirdName);
        WebView webView = v.findViewById(R.id.webView);

        Glide.with(v).load(bird.getImgPath()).into(imageView);

        textView.setText(bird.getName());

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(bird.getWikiUrl());

        FloatingActionButton backFab = v.findViewById(R.id.backFab);

        NavController navController = Navigation.findNavController(container);

        backFab.setOnClickListener(view -> {
            Subscriber.init();
            MainActivity.RX.onNext("Back button clicked");
            bird.iDeChooseYou();

            navController.navigate(R.id.action_secondFragment_to_FirstFragment);
        });

        Subscriber.init();
        MainActivity.RX.onNext("Second Fragment created");
        return v;
    }
}