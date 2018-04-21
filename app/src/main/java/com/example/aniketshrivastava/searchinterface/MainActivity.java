package com.example.aniketshrivastava.searchinterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private EditText InputCode1,InputCode2,InputCode3;
    private Button SearchCode;
    private TextView OutputAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputCode1 = findViewById(R.id.CodeInput1);
        InputCode2 = findViewById(R.id.CodeInput2);
        InputCode3 = findViewById(R.id.CodeInput3);

        SearchCode=findViewById(R.id.SearchButton);
        OutputAddress=findViewById(R.id.GetLocation);
        SearchCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onSearch();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

private void onSearch() throws JSONException{
            JSONObject code=new JSONObject();
            String Code=InputCode1.getText().toString().trim() + "-" + InputCode2.getText().toString().trim() + "-" +InputCode3.getText().toString().trim();
            code.put("pin",Code);
            String type="Code";
            System.out.println(Code);
            OutputAddress.setVisibility(View.VISIBLE);
            SearchBackground ObjBackgroundWorker = new SearchBackground(getApplicationContext());
            ObjBackgroundWorker.textview(OutputAddress);
            ObjBackgroundWorker.execute(type ,code.toString());

    }

public void Click(View view )
{


    Intent i = new Intent(MainActivity.this, LocationDisplay.class);
    startActivity(i);

}





}
