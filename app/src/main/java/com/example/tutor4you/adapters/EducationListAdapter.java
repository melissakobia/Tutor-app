package com.example.tutor4you.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor4you.R;
import com.example.tutor4you.models.EducationLevel;
import com.example.tutor4you.ui.CategoryListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EducationListAdapter extends RecyclerView.Adapter<EducationListAdapter.DashboardViewHolder> {

    List<EducationLevel> educationLevelList;
    private Context mContext;

    public EducationListAdapter(List<EducationLevel> educationLevelList, Context mContext) {
        this.educationLevelList = educationLevelList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View  view ;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.cardview_level_item, parent,false);
        return new  DashboardViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {
        holder.mTextview.setText(educationLevelList.get(position).getEducationLevelName());
        holder.mImageView.setImageResource(educationLevelList.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CategoryListActivity.class);
                mContext.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return educationLevelList.size();
    }

    public class DashboardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.textViewList) TextView mTextview;
        @BindView(R.id.listImageView) ImageView mImageView;
        @BindView(R.id.cardView_id) CardView cardView;

        public DashboardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
