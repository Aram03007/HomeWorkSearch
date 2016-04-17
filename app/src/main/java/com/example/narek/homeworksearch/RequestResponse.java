package com.example.narek.homeworksearch;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Narek on 4/17/16.
 */
public class RequestResponse extends AsyncTask<String, ProgressDialog, String> {
    Context context;
    TextView textView;
    ProgressDialog progress;

    public RequestResponse(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;

    }

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setTitle("Loading");
        progress.show();

    }

    @Override
    protected String doInBackground(String... urls) {

        try {
            HttpURLConnection httpURLConnection;
            URL url = new URL(urls[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            InputStreamReader isr = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();


            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            sb.setLength(0);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                sb.append("URL: ").append(json.getString("link")).append("\n");
                sb.append("TITLE: ").append(json.getString("title")).append("\n");
                sb.append("CONTENT: ").append(json.getString("snippet")).append("\n");
                sb.append("____________________________________________").append("\n\n");
            }

            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(String output) {
        progress.dismiss();
        textView.setText(output);
    }
}