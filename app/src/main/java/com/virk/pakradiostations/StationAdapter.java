package com.virk.pakradiostations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationViewHolder> {

    private final List<RadioStation> stations;
    private final StationClickListener clickListener;

    public StationAdapter(List<RadioStation> stations, StationClickListener clickListener) {
        this.stations = stations;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_station, parent, false);
        return new StationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {
        RadioStation station = stations.get(position);
        holder.nameView.setText(station.getName());
        holder.itemView.setOnClickListener(v -> clickListener.onStationClick(position));
    }

    @Override
    public int getItemCount() {
        return stations.size();
    }

    static class StationViewHolder extends RecyclerView.ViewHolder {
        TextView nameView;

        public StationViewHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.stationName);
        }
    }

    public interface StationClickListener {
        void onStationClick(int position);
    }
}
