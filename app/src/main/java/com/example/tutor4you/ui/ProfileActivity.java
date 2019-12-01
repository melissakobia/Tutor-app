package com.example.tutor4you.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tutor4you.Constants;
import com.example.tutor4you.R;
import com.example.tutor4you.models.Profile;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shantanudeshmukh.linkedinsdk.helpers.LinkedInUser;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.nameEditText) EditText mTutorNameEditText;
    @BindView(R.id.ageEditText) EditText mAgeEditText ;
    //@BindView(R.id.genderEditText) EditText mGenderEditText;
    //@BindView(R.id.specializationEditText) EditText mSpecializationEditText;
    @BindView(R.id.educationLevelEditText) EditText mEducationLevelEditText;
    @BindView(R.id.rateEditText) EditText mRateEditText;
    @BindView(R.id.createProfileButton) Button mButtonCreateProfile;
    private Profile mProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        mButtonCreateProfile.setOnClickListener(this);

//        String[] specialization_area = new String[] {"Mathematics", "Languages", "Music", "Simple Science", "Accounting", "Biology", "Physics", "Chemistry", "Religious Education", "Social Studies", "Computer Programming", "Arts and Crafts"};
//
//        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), R.layout.education_level_popup_item, specialization_area);
//
//        AutoCompleteTextView editTextFilledExposedDropdown =
//               findViewById(R.id.filled_exposed_dropdown);
//        editTextFilledExposedDropdown.setAdapter(adapter);
//


    }

    @Override
    public void onClick(View v) {
        if (v == mButtonCreateProfile) {
            String tutorName = mTutorNameEditText.getText().toString();
            int age = Integer.parseInt(mAgeEditText.getText().toString());
            String gender = "male";
            String specialization = "math";

            String educationLevel = mEducationLevelEditText.getText().toString();
            int rate = Integer.parseInt(mRateEditText.getText().toString());
            String tutorProfileUrl = "profile";

            if(TextUtils.isEmpty (tutorName)){
                Toast.makeText(getApplicationContext(), "Add Name!!", Toast.LENGTH_LONG).show();
            }
//            else if(TextUtils.isEmpty()){
//                Toast.makeText(getApplicationContext(), "Enter Age!!", Toast.LENGTH_LONG).show();
//            }
//            else if(TextUtils.isEmpty(gender)){
//                Toast.makeText(getApplicationContext(), "Enter Gender!", Toast.LENGTH_LONG).show();
//            }
//            else if(TextUtils.isEmpty(specialization)){
//                Toast.makeText(getApplicationContext(), "Enter area of specialization!!", Toast.LENGTH_LONG).show();
//            }
            else if(TextUtils.isEmpty(educationLevel)){
                Toast.makeText(getApplicationContext(), "Enter Education Level!!", Toast.LENGTH_LONG).show();
            }
//            else if(TextUtils.isEmpty(rate)){
//                Toast.makeText(getApplicationContext(), "Enter hourly rate!!", Toast.LENGTH_LONG).show();
//            }
            else{


                DatabaseReference profileReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PROFILE);
                mProfile = new Profile(tutorName, age, gender, specialization, educationLevel, rate, tutorProfileUrl );
                profileReference.push().setValue(mProfile);

                Toast.makeText(ProfileActivity.this, "Created Successfully", Toast.LENGTH_LONG).show();



//                Intent intent = new Intent(ProfileActivity.this, ViewProfileActivity.class);
//
//                Intent prof_intent = getIntent();
//                String tutorProfileUrl = prof_intent.getStringExtra("tutorProfileUrl");
//                Toast.makeText(this, tutorProfileUrl, Toast.LENGTH_LONG).show();
//
//                intent.putExtra("tutorProfileUrl", tutorProfileUrl);
//
//                intent.putExtra("tutorName", tutorName);
//                intent.putExtra("age", age);
//                //intent.putExtra("gender", gender);
//                //intent.putExtra("specialization", specialization);
//                intent.putExtra("educationLevel", educationLevel);
//                intent.putExtra("rate", rate);
//                startActivity(intent);
                ClearEditTextAfterDoneTask();

            }

        }

    }

    public void ClearEditTextAfterDoneTask() {
        mTutorNameEditText.getText().clear();
        mAgeEditText.getText().clear();
        //mGenderEditText.getText().clear();
        //mSpecializationEditText.getText().clear();
        mEducationLevelEditText.getText().clear();
        mRateEditText.getText().clear();

    }

}
