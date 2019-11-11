package com.example.tutor4you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.buttonStudent) Button mButtonStudent;
    @BindView(R.id.buttonTutor) Button mButtonTutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mButtonTutor.setOnClickListener(this);
        mButtonStudent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mButtonTutor) {
            Intent intent =new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
        else if (v == mButtonStudent) {
            Intent intent = new Intent(MainActivity.this, CategoryListActivity.class);
            startActivity(intent);
        }

    }
}
