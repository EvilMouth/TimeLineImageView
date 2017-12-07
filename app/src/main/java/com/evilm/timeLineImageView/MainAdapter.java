package com.evilm.timeLineImageView;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import library.evilm.timeLineImageView.TimeLineImageView;

/**
 * ProjectName:TimeLineImageView
 * Description:
 * Created by evilM on 2016/10/21.17:57
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.Holder> {
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if (position == 0) {
            holder.tliv.setLineVisibility(false, true);
            holder.tliv.setLineColor(Color.BLUE, Color.RED);
        } else if (position == getItemCount() - 1) {
            holder.tliv.setLineVisibility(true, false);
            holder.tliv.setLineColor(Color.BLUE);
        } else {
            holder.tliv.setLineVisibility(true, true);
            holder.tliv.setLineColor(Color.BLUE);
        }

//        switch (position) {
//            case 1:
//                holder.tliv.setLineDash(20, 5);
//                break;
//            case 2:
//                holder.tliv.setLineSize(5);
//                break;
//            case 3:
//                holder.tliv.setLineColor(Color.RED);
//                break;
//            case 4:
//                break;
//            case 5:
//                break;
//        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TimeLineImageView tliv;
        private TextView tv;

        Holder(View itemView) {
            super(itemView);
            tliv = (TimeLineImageView) itemView.findViewById(R.id.main_tliv);
            tv = (TextView) itemView.findViewById(R.id.main_tv);
        }
    }
}
