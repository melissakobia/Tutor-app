package com.example.tutor4you.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tutor4you.EducationLevelActivity;
import com.example.tutor4you.LoginActivity;
import com.example.tutor4you.R;
import com.shantanudeshmukh.linkedinsdk.LinkedInBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int LINKEDIN_REQUEST = 100;
    public static String clientID;
    public static String clientSecret;
    public static String redirectUrl;



    @BindView(R.id.buttonStudent) Button mButtonStudent;
    @BindView(R.id.buttonTutor) Button mButtonTutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getCredentials();

        mButtonTutor.setOnClickListener(this);
        mButtonStudent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mButtonTutor) {
            LinkedInBuilder.getInstance(MainActivity.this)
                    .setClientID(clientID)
                    .setClientSecret(clientSecret)
                    .setRedirectURI(redirectUrl)
                    .authenticate(LINKEDIN_REQUEST);


            Intent intent =new Intent(MainActivity.this, TutorDashboardActivity.class);
            startActivity(intent);
        }
        else if (v == mButtonStudent) {
            Intent intent = new Intent(MainActivity.this, EducationLevelActivity.class);
            startActivity(intent);
        }

    }



    private void getCredentials() {
        try {

            InputStream is = getAssets().open("linkedin-credentials.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            JSONObject linkedinCred = new JSONObject(json);
            clientID = linkedinCred.getString("client_id");
            clientSecret = linkedinCred.getString("client_secret");
            redirectUrl = linkedinCred.getString("redirect_url");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
