package com.example.tutor4you.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tutor4you.R;
import com.example.tutor4you.adapters.EducationListAdapter;
import com.example.tutor4you.models.EducationLevel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentHomeFragment extends Fragment {

    RecyclerView recyclerView;

    List<EducationLevel> levelData;


    public StudentHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =   inflater.inflate(R.layout.fragment_student_home, container, false);
       recyclerView = view.findViewById(R.id.educationLevelRecyclerView) ;

        levelData = new ArrayList<>();

        levelData.add(new EducationLevel("Kindergarten", R.drawable.ic_iconfinder_board));
        levelData.add(new EducationLevel("Primary", R.drawable.ic_iconfinder_set_of_three_books));
        levelData.add(new EducationLevel("Secondary", R.drawable.ic_city_highschool));
        levelData.add(new EducationLevel("University", R.drawable.ic_building_education_school));


        EducationListAdapter myAdapter = new EducationListAdapter( levelData, view.getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        recyclerView.setAdapter(myAdapter);


        return view;


    }

}
