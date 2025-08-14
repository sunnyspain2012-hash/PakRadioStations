package com.virk.pakradiostations;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationViewHolder> {
    private List<RadioStation> stations;

    public static class StationViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public StationViewHolder(TextView v) {
            super(v);
            nameText = v;
        }
    }

    public StationAdapter(List<RadioStation> stations) {
        this.stations = stations;
    }

    @Override
    public StationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new StationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StationViewHolder holder, int position) {
        holder.nameText.setText(stations.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return stations.size();
    }
}
