package com.example.tutor4you.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tutor4you.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewProfileActivity extends AppCompatActivity {

    @BindView(R.id.textViewTutorName) TextView mTutorNameTextView;
    @BindView(R.id.textViewAge) TextView mAgeTextview;
    @BindView(R.id.textViewGender) TextView mGenderTextView;
    @BindView(R.id.textViewSpecialization) TextView mSpecializationTextView;
    @BindView(R.id.textViewEducationLevel) TextView mEducationLevelTextView;
    @BindView(R.id.textViewRate) TextView mRateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        String tutorName = intent.getStringExtra("tutorName");
        mTutorNameTextView.setText("Name : " + tutorName);

        String age = intent.getStringExtra("age");
        mAgeTextview.setText("Age : " + age);

        String gender = intent.getStringExtra("gender");
        mGenderTextView.setText("Gender : " + gender);

        String specialization = intent.getStringExtra("specilalization");
        mSpecializationTextView.setText("Area of Specializtion : " +specialization);

        String educationLevel = intent.getStringExtra("educationLevel");
        mEducationLevelTextView.setText("Highest Level of Education : " +educationLevel);

        String rate = intent.getStringExtra("rate");
        mRateTextView.setText("Rate Per Hour : " + rate);

    }
}
