package com.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first, container, false);

        //Get the birds data
        ArrayList<Bird> birds = DataSource.createBirdList();
        BirdsRecViewAdapter adapter = new BirdsRecViewAdapter(v.getContext());
        adapter.setBirds(birds);

        RecyclerView birdsRecView = v.findViewById(R.id.birdsRecView);
        birdsRecView.setAdapter(adapter);
        birdsRecView.setLayoutManager(new GridLayoutManager(v.getContext(), 2));

        NavController navController = Navigation.findNavController(container);
        FloatingActionButton fab = v.findViewById(R.id.fab);

        fab.setOnClickListener(view -> {
            Subscriber.init();
            MainActivity.RX.onNext("Info button clicked");

            if (Bird.getChosenBird() == null) return;

            Bird bird = Bird.getChosenBird();

            NavDirections action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(bird);

            navController.navigate(action);
        });

        Subscriber.init();
        MainActivity.RX.onNext("First Fragment created");

        return v;
    }
}