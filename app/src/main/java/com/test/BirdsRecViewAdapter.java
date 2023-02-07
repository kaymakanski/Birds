package com.test;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BirdsRecViewAdapter extends RecyclerView.Adapter<BirdsRecViewAdapter.ViewHolder> {

    private ArrayList<Bird> birds = new ArrayList<>();
    private final Context context;
    private int selectedPos = RecyclerView.NO_POSITION;

    public BirdsRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // using the layout inflater to generate the view object
        // and attach the view object to a ViewGroup (R.layout.fragment_first is RelativeLayout that extends ViewGroup)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bird_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.birdName.setText(birds.get(position).getName());

        holder.itemView.setBackgroundColor(selectedPos == position ? Color.CYAN : Color.TRANSPARENT);

        //Using Glide library to generate my images
        Glide.with(context)
                .asBitmap()
                .load(birds.get(position).getImgPath())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return birds.size();
    }

    public void setBirds(ArrayList<Bird> birds) {
        this.birds = birds;
        //To refresh the data inside the recycler view
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView birdName;
        private final ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            birdName = itemView.findViewById(R.id.birdName);
            image = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Below line is just like a safety check, because sometimes holder could be null,
            // in that case, getAdapterPosition() will return RecyclerView.NO_POSITION
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;

            // Updating old as well as new positions
            notifyItemChanged(selectedPos);
            selectedPos = getAdapterPosition();
            notifyItemChanged(selectedPos);

            for (Bird bird : birds) {
                bird.setSelected(false);
            }
            Bird selectedBird = birds.get(getAdapterPosition());
            selectedBird.setSelected(true);
            selectedBird.iChooseYou(selectedBird);

            Subscriber.init();
            MainActivity.RX.onNext(birds.get(getAdapterPosition()).getName() + " selected");
        }
    }
}
