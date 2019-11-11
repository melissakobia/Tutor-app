package com.example.tutor4you;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryListActivity extends AppCompatActivity {
    @BindView(R.id.listView) ListView mListView;
    private String[] categories = new String[] {"Math", "Simple Science",
            "Accounting", "Biology", "Physics", "Chemistry",
            "Religous Education", "Social Studies", "Languages", "Computer Progrmamming",
            "Music", "Arts and Crafts"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        ButterKnife.bind(this);
        CategoriesArrayAdapter arrayAdapter = new CategoriesArrayAdapter(this, android.R.layout.simple_list_item_1, categories);
        mListView.setAdapter(arrayAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String category = ((TextView)view).getText().toString();
                Toast.makeText(CategoryListActivity.this,category,Toast.LENGTH_LONG).show();
            }
        });

    }
}
