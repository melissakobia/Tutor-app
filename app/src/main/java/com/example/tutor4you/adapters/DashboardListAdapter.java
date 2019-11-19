package com.example.tutor4you.adapters;

import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor4you.R;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DashboardListAdapter extends RecyclerView.Adapter<DashboardListAdapter.DashboardViewHolder> {

    List<String> stringList;

    public DashboardListAdapter(List<String> stringList) {
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_list_item, parent, false);
        DashboardViewHolder viewHolder = new DashboardViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {
        holder.mTextview.setText(stringList.get(position));
        //holder.mImageView.setImageDrawable();

    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class DashboardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.textViewList) TextView mTextview;
        @BindView(R.id.listImageView) ImageView mImageView;

        public DashboardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (stringList.get(getAdapterPosition()).equals("My Calendar")) {
                Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(2012, 0, 19, 7, 30);
                Calendar endTime = Calendar.getInstance();
                endTime.set(2012, 0, 19, 10, 30);
                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
                calendarIntent.putExtra(CalendarContract.Events.TITLE, "Social Development Class");
                calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Online");

            }




        }
    }
}
