package com.example.tutor4you.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tutor4you.R;
import com.shantanudeshmukh.linkedinsdk.LinkedInBuilder;
import com.shantanudeshmukh.linkedinsdk.helpers.LinkedInUser;

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

        }
        else if (v == mButtonStudent) {
            Intent intent = new Intent(MainActivity.this, EducationLevelActivity.class);
            startActivity(intent);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LINKEDIN_REQUEST && data != null) {
            if (resultCode == RESULT_OK) {

                //Successfully signed in and retrieved data
                LinkedInUser user = data.getParcelableExtra("social_login");
                String firstName = user.getFirstName();
                String lastName = user.getLastName();
                String email = user.getEmail();
                String url = user.getProfileUrl();

                Toast.makeText(MainActivity.this,firstName + lastName,Toast.LENGTH_LONG).show();

                Intent intent =new Intent(MainActivity.this, TutorDashboardActivity.class);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                intent.putExtra("email", email);
                intent.putExtra("url", url);
                startActivity(intent);

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
