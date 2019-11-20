package com.example.tutor4you.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutor4you.R;
import com.shantanudeshmukh.linkedinsdk.helpers.LinkedInUser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TutorDashboardActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.first_name_textView) TextView firstName_tv;
    @BindView(R.id.email_textView) TextView email_tv;
    @BindView(R.id.tutorImageView) ImageView profilePic;
    @BindView(R.id.tutorCalendarImageView) ImageView calendar;
    @BindView(R.id.tutorLogoutImageView) ImageView logout;
    @BindView(R.id.tutorProfileImageView) ImageView profile;


    List<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_dashboard);
        ButterKnife.bind(this);

        LinkedInUser user = new LinkedInUser();
        setUserData(user);


        stringList = new ArrayList<>();

        stringList.add("My Subjects");
        stringList.add("My Students");
        stringList.add("My Calendar");
        stringList.add("My Requests");

        profile.setOnClickListener(this);
        logout.setOnClickListener(this);
        calendar.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v == calendar) {
            Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
            Calendar beginTime = Calendar.getInstance();
            beginTime.set(2012, 0, 19, 7, 30);
            Calendar endTime = Calendar.getInstance();
            endTime.set(2012, 0, 19, 10, 30);
            calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
            calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
            calendarIntent.putExtra(CalendarContract.Events.TITLE, "Social Development Class");
            calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Online");
            startActivity(calendarIntent);

        }

        else if (v == profile) {
            Intent intent = new Intent(TutorDashboardActivity.this,ProfileActivity.class);
            startActivity(intent);
        }

        else if (v == logout ) {
            Intent intent = new Intent(TutorDashboardActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }

    private void setUserData(LinkedInUser user) {
        Intent intent = getIntent();

        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        firstName_tv.setText(firstName + " " + lastName);
        String email = intent.getStringExtra("email");
        email_tv.setText(email);
        String url =intent.getStringExtra("url");


        if(url!= null && !url.isEmpty()){
            new TutorDashboardActivity.ImageLoadTask(url, profilePic).execute();
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
