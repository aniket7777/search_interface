package com.example.aniketshrivastava.searchinterface;

import android.content.Context;
import android.os.AsyncTask;
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
    private String code = "";
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

//                JSONArray myListsAll= new JSONArray(myjsonstring);
//                for(int i=0;i<myListsAll.length();i++){
//                    JSONObject jsonobject= (JSONObject) myListsAll.get(i);
//                    String id=jsonobject("nid");
//
                JSONArray jsonarray = new JSONArray(data);
                JSONObject jsonobject = (JSONObject) jsonarray.get(0);
                id = jsonobject.optString("pin");


                dataOutputStream.close();
                inputStream.close();


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
          tv.setText(s);


    }
}

