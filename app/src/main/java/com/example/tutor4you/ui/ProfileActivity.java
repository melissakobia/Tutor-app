package com.example.tutor4you.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tutor4you.Constants;
import com.example.tutor4you.R;
import com.example.tutor4you.models.Profile;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    @BindView(R.id.nameEditText) EditText mTutorNameEditText;
    @BindView(R.id.ageEditText) EditText mAgeEditText ;
    @BindView(R.id.educationLevelEditText) EditText mEducationLevelEditText;
    @BindView(R.id.rateEditText) EditText mRateEditText;
    @BindView(R.id.createProfileButton) Button mButtonCreateProfile;
    @BindView(R.id.spnGender) Spinner mGenderSpinner;
    @BindView(R.id.spinner2) Spinner mSpecialization;
    @BindView(R.id.tutor_ImageView) ImageView tutorImageView;
    @BindView(R.id.floating_action_buttonCamera) FloatingActionButton cameraBtn;
    private Profile mProfile;
    Bitmap imageBitmap;

    static final int REQUEST_IMAGE_CAPTURE = 111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        mButtonCreateProfile.setOnClickListener(this);
        cameraBtn.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mGenderSpinner.setAdapter(adapter);
        mGenderSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterSpecialization = ArrayAdapter.createFromResource(this, R.array.specialization_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mSpecialization.setAdapter(adapterSpecialization);
        mSpecialization.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mButtonCreateProfile) {
            String tutorName = mTutorNameEditText.getText().toString();
            int age = Integer.parseInt(mAgeEditText.getText().toString());
            String gender = String.valueOf(mGenderSpinner.getSelectedItem());
            String specialization = String.valueOf(mSpecialization.getSelectedItem());

            String educationLevel = mEducationLevelEditText.getText().toString();
            int rate = Integer.parseInt(mRateEditText.getText().toString());
            String tutorProfileUrl = "profile";

            Toast.makeText(getApplicationContext(), gender, Toast.LENGTH_LONG).show();

            if(TextUtils.isEmpty (tutorName)){
                Toast.makeText(getApplicationContext(), "Add Name!!", Toast.LENGTH_LONG).show();
            }

            else if(TextUtils.isEmpty(educationLevel)){
                Toast.makeText(getApplicationContext(), "Enter Education Level!!", Toast.LENGTH_LONG).show();
            }
            else if(TextUtils.isEmpty(Integer.toString(rate))){
                Toast.makeText(getApplicationContext(), "Enter hourly rate!!", Toast.LENGTH_LONG).show();
            }
            else{

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                final DatabaseReference restaurantRef = FirebaseDatabase
                        .getInstance()
                        .getReference(Constants.FIREBASE_CHILD_PROFILE)
                        .child(uid);


                DatabaseReference profileReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PROFILE);
                mProfile = new Profile(tutorName, age, gender, specialization, educationLevel, rate, encodeBitmapAndSaveToFirebase(imageBitmap) );
                profileReference.push().setValue(mProfile);


                String name = mProfile.getTutorName();


                Toast.makeText(ProfileActivity.this, "Created Successfully", Toast.LENGTH_LONG).show();



                Intent intent = new Intent(ProfileActivity.this, ViewProfileActivity.class);

                //Intent prof_intent = getIntent();
                //String tutorProfileUrl = prof_intent.getStringExtra("tutorProfileUrl");
                //Toast.makeText(this, tutorProfileUrl, Toast.LENGTH_LONG).show();

                intent.putExtra("tutorProfileUrl", tutorProfileUrl);
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

        if (v == cameraBtn) {
            onLaunchCamera();
        }

    }

    public void ClearEditTextAfterDoneTask() {
        mTutorNameEditText.getText().clear();
        mAgeEditText.getText().clear();
        mEducationLevelEditText.getText().clear();
        mRateEditText.getText().clear();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (view == mGenderSpinner) {
            String selectedGender = parent.getItemAtPosition(position).toString();

            Toast.makeText(getApplicationContext(),selectedGender , Toast.LENGTH_LONG).show();
        }

        if (view == mSpecialization ) {

            String selectedSpecialization= parent.getItemAtPosition(position).toString();

            Toast.makeText(getApplicationContext(),selectedSpecialization , Toast.LENGTH_LONG).show();

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        }

    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            tutorImageView.setImageBitmap(imageBitmap);

        }
    }

    public String encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), android.util.Base64.DEFAULT);

        return imageEncoded;
//

    }



}
