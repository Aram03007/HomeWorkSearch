package com.example.narek.homeworksearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = (EditText) findViewById(R.id.editText);
        final TextView searchResult = (TextView) findViewById(R.id.result);
        findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestResponse requestResponse = new RequestResponse(MainActivity.this, searchResult);
                String query = editText.getText().toString();
                query = query.replaceAll(" ", "+");
                String url = "https://www.googleapis.com/customsearch/v1?q=" + query +
                        "&cx=001121747010064086049:xwke6c1btya&key=AIzaSyCzwYJdfd_8W1BbTMCQggMFA8edh-7MPRw";
                requestResponse.execute(url);
            }
        });


    }
}
