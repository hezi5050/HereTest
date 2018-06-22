package com.apps.heretest.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.heretest.R;
import com.apps.heretest.data.Taxi;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.Collections;

public class TaxisAdapter extends RecyclerView.Adapter<TaxisAdapter.ViewHolder> {

    private ArrayList<Taxi> mTaxisArrayList;

    public TaxisAdapter(ArrayList<Taxi> taxisArrayList) {
        mTaxisArrayList = taxisArrayList;
        sortByEta();
    }

    public void updateTaxisArrayList(ArrayList<Taxi> taxisArrayList) {
        mTaxisArrayList = taxisArrayList;
        sortByEta();
        notifyDataSetChanged();
    }

    private void sortByEta() {
        Collections.sort(mTaxisArrayList);
    }

    @Override
    public TaxisAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.taxi_item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Taxi taxi = mTaxisArrayList.get(position);
        holder.mCabStationTextView.setText(taxi.getName());
        holder.mEtaTextView.setText(holder.itemView.getContext().getString(R.string.eta_row, String.valueOf(taxi.getEta())));

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.place_holder);
        requestOptions.centerCrop();

        Glide.with(holder.itemView.getContext())
                .load(taxi.getIconUrl())
                .apply(requestOptions)
                .into(holder.mCabStationIcon);
    }

    @Override
    public int getItemCount() {
        return mTaxisArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mCabStationIcon;
        private TextView mEtaTextView;
        public TextView mCabStationTextView;

        public ViewHolder(View v) {
            super(v);
            mCabStationIcon = v.findViewById(R.id.cab_icon);
            mCabStationTextView = v.findViewById(R.id.cab_station_tv);
            mEtaTextView = v.findViewById(R.id.eta_tv);
        }
    }
}