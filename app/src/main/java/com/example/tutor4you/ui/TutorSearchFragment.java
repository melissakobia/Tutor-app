package com.example.tutor4you.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tutor4you.Constants;
import com.example.tutor4you.R;
import com.example.tutor4you.adapters.FirebaseProfileViewHolder;
import com.example.tutor4you.models.Profile;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * A simple {@link Fragment} subclass.
 */
public class TutorSearchFragment extends Fragment {
    RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter<Profile, FirebaseProfileViewHolder> mFirebaseAdapter;
    protected static final Query sProfileQuery =
            FirebaseDatabase.getInstance().getReference().child("profiles").limitToLast(50);
    View view;


    public TutorSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().setTitle("Tutors");
        view =  inflater.inflate(R.layout.fragment_tutor_search, container, false);
        recyclerView = view.findViewById(R.id.tutorListRecyclerView) ;



        databaseReference = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_PROFILE);

        setUpFirebaseAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mFirebaseAdapter);

        return view;
    }



    private void setUpFirebaseAdapter (){
        FirebaseRecyclerOptions<Profile> options = new FirebaseRecyclerOptions.Builder<Profile>()
                .setQuery(sProfileQuery, new SnapshotParser<Profile>() {
                    @NonNull
                    @Override
                    public Profile parseSnapshot(@NonNull DataSnapshot snapshot) {
                        return new Profile(snapshot.child("tutorName").getValue().toString(),
                                Integer.parseInt(snapshot.child("age").getValue().toString()),
                                snapshot.child("gender").getValue().toString(),
                                snapshot.child("specialization").getValue().toString(),
                                snapshot.child("educationLevel").getValue().toString(),
                                Integer.parseInt(snapshot.child("rate").getValue().toString()),
                                snapshot.child("tutorProfileUrl").getValue().toString());
                    }
                }).build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Profile, FirebaseProfileViewHolder>(options) {
            @NonNull
            @Override
            public FirebaseProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_list_item, parent, false);
                return new FirebaseProfileViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull FirebaseProfileViewHolder holder, int position, @NonNull Profile profile) {

                holder.setTutorName(profile.getTutorName());
                holder.setSpecialization(profile.getSpecialization());
                holder.setRate(profile.getRate());
                holder.setProfileImage(profile.getTutorProfileUrl());

            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        DividerItemDecoration divider = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(getContext().getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(divider);
        recyclerView.setAdapter(mFirebaseAdapter);





    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }
}
