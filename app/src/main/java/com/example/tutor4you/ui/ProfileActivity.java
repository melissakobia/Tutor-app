package com.example.tutor4you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tutorImage) ImageView tutorImg;
    @BindView(R.id.nameEditText) EditText mTutorNameEditText;
    @BindView(R.id.ageEditText) EditText mAgeEditText ;
    @BindView(R.id.genderEditText) EditText mGenderEditText;
    @BindView(R.id.specializationEditText) EditText mSpecializationEditText;
    @BindView(R.id.educationLevelEditText) EditText mEducationLevelEditText;
    @BindView(R.id.rateEditText) EditText mRateEditText;
    @BindView(R.id.createProfileButton) Button mButtonCreateProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        mButtonCreateProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mButtonCreateProfile) {
            String tutorName = mTutorNameEditText.getText().toString();
            String age = mAgeEditText.getText().toString();
            String gender = mGenderEditText.getText().toString();
            String specialization = mSpecializationEditText .getText().toString();
            String educationLevel = mEducationLevelEditText.getText().toString();
            String rate = mRateEditText.getText().toString();

            if(TextUtils.isEmpty (tutorName)){
                Toast.makeText(getApplicationContext(), "Add Name!!", Toast.LENGTH_LONG).show();
            }
            else if(TextUtils.isEmpty(age)){
                Toast.makeText(getApplicationContext(), "Enter Age!!", Toast.LENGTH_LONG).show();
            }
            else if(TextUtils.isEmpty(gender)){
                Toast.makeText(getApplicationContext(), "Enter Gender!", Toast.LENGTH_LONG).show();
            }
            else if(TextUtils.isEmpty(specialization)){
                Toast.makeText(getApplicationContext(), "Enter area of specialization!!", Toast.LENGTH_LONG).show();
            }
            else if(TextUtils.isEmpty(educationLevel)){
                Toast.makeText(getApplicationContext(), "Enter Education Level!!", Toast.LENGTH_LONG).show();
            }
            else if(TextUtils.isEmpty(rate)){
                Toast.makeText(getApplicationContext(), "Enter hourly rate!!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(ProfileActivity.this, "Created Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ProfileActivity.this, ViewProfileActivity.class);
                intent.putExtra("tutorName", tutorName);
                intent.putExtra("age", age);
                intent.putExtra("gender", gender);
                intent.putExtra("specialization", specialization);
                intent.putExtra("educationLevel", educationLevel);
                intent.putExtra("rate", rate);
                startActivity(intent);
                ClearEditTextAfterDoneTask();

            }

        }

    }

    public void ClearEditTextAfterDoneTask() {
        mTutorNameEditText.getText().clear();
        mAgeEditText.getText().clear();
        mGenderEditText.getText().clear();
        mSpecializationEditText.getText().clear();
        mEducationLevelEditText.getText().clear();
        mRateEditText.getText().clear();

    }

}
