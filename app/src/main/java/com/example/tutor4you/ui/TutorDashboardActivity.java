package com.example.tutor4you.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutor4you.LoginActivity;
import com.example.tutor4you.R;
import com.example.tutor4you.adapters.DashboardListAdapter;
import com.shantanudeshmukh.linkedinsdk.LinkedInBuilder;
import com.shantanudeshmukh.linkedinsdk.helpers.LinkedInUser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TutorDashboardActivity extends AppCompatActivity {
    @BindView(R.id.dashboardRecyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.first_name_textView) TextView firstName;
    @BindView(R.id.last_name_textView) TextView lastName;
    @BindView(R.id.email_textView) TextView email;
    @BindView(R.id.tutorImageView) ImageView profilePic;

    public static final int LINKEDIN_REQUEST = 100;
    private String accessToken;
    private long accessTokenExpiry;

    List<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_dashboard);
        ButterKnife.bind(this);

        stringList = new ArrayList<>();


        DashboardListAdapter adapter = new DashboardListAdapter(stringList);
        mRecyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        stringList.add("My Subjects");
        stringList.add("My Students");
        stringList.add("My Calendar");
        stringList.add("My Requests");

    }

    private void setUserData(LinkedInUser user) {
        accessToken = user.getAccessToken();
        accessTokenExpiry = user.getAccessTokenExpiry();

        Log.wtf("LINKEDIN ID", user.getId());

        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());


        if(user.getProfileUrl()!= null && !user.getProfileUrl().isEmpty()){
            new TutorDashboardActivity.ImageLoadTask(user.getProfileUrl(), profilePic).execute();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LINKEDIN_REQUEST && data != null) {
            if (resultCode == RESULT_OK) {

                //Successfully signed in and retrieved data
                LinkedInUser user = data.getParcelableExtra("social_login");
                setUserData(user);

            } else {


                //print the error
                Log.wtf("LINKEDIN ERR", data.getStringExtra("err_message"));

                if (data.getIntExtra("err_code", 0) == LinkedInBuilder.ERROR_USER_DENIED) {
                    //user denied access to account
                    Toast.makeText(this, "User Denied Access", Toast.LENGTH_SHORT).show();
                } else if (data.getIntExtra("err_code", 0) == LinkedInBuilder.ERROR_USER_DENIED) {
                    //some error occured
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                }


            }
        }

    }


    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                return BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }

    }
}
