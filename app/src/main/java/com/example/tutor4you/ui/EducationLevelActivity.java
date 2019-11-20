package com.example.tutor4you.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tutor4you.R;
import com.example.tutor4you.adapters.EducationListAdapter;
import com.example.tutor4you.models.EducationLevel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EducationLevelActivity extends AppCompatActivity {

    @BindView(R.id.educationLevelRecyclerView) RecyclerView recyclerView;

    List<EducationLevel> levelData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_level);
        ButterKnife.bind(this);

        levelData = new ArrayList<>();

        levelData.add(new EducationLevel("Kindergarten", R.drawable.ic_iconfinder_board));
        levelData.add(new EducationLevel("Primary", R.drawable.ic_iconfinder_set_of_three_books));
        levelData.add(new EducationLevel("Secondary", R.drawable.ic_city_highschool));
        levelData.add(new EducationLevel("University", R.drawable.ic_building_education_school));


        EducationListAdapter myAdapter = new EducationListAdapter( levelData, EducationLevelActivity.this);
        recyclerView.setLayoutManager(new GridLayoutManager(EducationLevelActivity.this, 2));
        recyclerView.setAdapter(myAdapter);







    }
}
