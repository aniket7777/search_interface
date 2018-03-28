package com.example.aniketshrivastava.searchinterface;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by ANIKET SHRIVASTAVA on 13-03-2018.
 */

public class SearchBackground extends AsyncTask<String ,Void, String> {
    protected Context context;
    private TextView tv;

    private String code;

    {
        code = null;
    }

    void textview(TextView t){
        this.tv=t;
    }
    SearchBackground(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... strings) {
        String data = null;
        String id = null;
        try {
            String Search_URL = "https://eureka18.000webhostapp.com/search.php";
            URL url = new URL(Search_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            httpURLConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            httpURLConnection.connect();
            DataOutputStream dataOutputStream
                    = new DataOutputStream(httpURLConnection.getOutputStream());

            dataOutputStream.writeBytes(strings[1]);
            dataOutputStream.flush();


            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int inputStreamData = inputStreamReader.read();
            Log.d("inputstream", String.valueOf(inputStreamData));
            while (inputStreamData != -1) {
                char current = (char) inputStreamData;
                inputStreamData = inputStreamReader.read();
                data += current;




                dataOutputStream.close();
                inputStream.close();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onPostExecute(String s) {

        try { String s1="Name : ";
              String s2="Address : ";
              String s3="Geo Coordinates : ";
              SpannableString ss1=  new SpannableString(s1);
              SpannableString ss2=  new SpannableString(s2);
              SpannableString ss3=  new SpannableString(s3);

            ss1.setSpan(new RelativeSizeSpan(2f), 0,5, 0); // set size
            ss2.setSpan(new RelativeSizeSpan(2f), 0,5, 0); // set size
            ss3.setSpan(new RelativeSizeSpan(2f), 0,5, 0); // set size

            String crappyPrefix = "null";

            if(s.startsWith(crappyPrefix)){
                s = s.substring(crappyPrefix.length(), s.length());
            }
            JSONObject obj = new  JSONObject(s);


            String add = obj.getString("address");
            float latiii = Float.parseFloat(obj.getString("latitude"));
            float longiii = Float.parseFloat(obj.getString("longitude"));
            String user=(obj.getString("name"));

            LocationDisplay obj1=new LocationDisplay();
            obj1.Coordinate(latiii,longiii);

            tv.setText(ss1 + add + "\n" + ss3 + "%.3f" + latiii + "%.3f" + longiii + "\n" + ss2 + user);
        }
          catch (JSONException e) {
            e.printStackTrace();
        }

    }


}

