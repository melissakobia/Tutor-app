package com.example.tutor4you.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor4you.Constants;
import com.example.tutor4you.R;
import com.example.tutor4you.models.Profile;
import com.example.tutor4you.ui.ViewProfileActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseProfileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private View mView;
    private Context mContext;

    TextView tutorNameTextView ;
    TextView rateTextView ;
    TextView educationLevelTextView;

    public FirebaseProfileViewHolder(@NonNull View itemView) {
        super(itemView);
       mView = itemView;
       mContext = itemView.getContext();
       tutorNameTextView =  itemView.findViewById(R.id.textViewTutorName1);
       educationLevelTextView  = itemView.findViewById(R.id.textViewEducationLevel1);
       rateTextView  = itemView.findViewById(R.id.textViewRate1);

       itemView.setOnClickListener(this);
    }


    public void setTutorName(String tutorName) {
        tutorNameTextView.setText(tutorName);
    }

    public void setEducationLevel(String educationLevel) {
        educationLevelTextView.setText(educationLevel);
    }

    public void setRate(int rate) {
        rateTextView.setText(Integer.toString(rate));
    }

    @Override
    public void onClick(View v) {
        final ArrayList<Profile> profiles = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PROFILE);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    profiles.add(snapshot.getValue(Profile.class));

                }

//                int itemPosition = getLayoutPosition();
//                Intent intent = new Intent(mContext, ViewProfileActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("profiles", Parcels.wrap(profiles));
//                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
