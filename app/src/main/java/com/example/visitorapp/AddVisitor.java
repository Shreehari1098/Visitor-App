package com.example.visitorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddVisitor extends AppCompatActivity {

    Button bt1,bt2;
    EditText et1,et2,et3,et4;
    String s1,s2,s3,s4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_visitor);
        bt1=(Button) findViewById(R.id.submit);
        bt2=(Button) findViewById(R.id.gotologin);

        et1=(EditText)findViewById(R.id.fname);
        et2=(EditText)findViewById(R.id.lname);
        et3=(EditText)findViewById(R.id.purpose);
        et4=(EditText)findViewById(R.id.wtm);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=et1.getText().toString();
                s2=et2.getText().toString();
                s3=et3.getText().toString();
                s4=et4.getText().toString();

                if(s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Fields must not be empty",Toast.LENGTH_LONG).show();
                }
                else{

                    /*Toast.makeText(getApplicationContext(),s1,Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),s2,Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),s3,Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),s4,Toast.LENGTH_LONG).show();
                    */

                    callApi();

                }
            }

            private void callApi() {

                String apiUrl="https://log-app-demo-api.onrender.com/addvisitor";
                JSONObject data =new JSONObject();
                try {
                    data.put("firstname",s1);
                    data.put("lastname",s2);
                    data.put("purpose",s3);
                    data.put("whomToMeet",s4);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                JsonObjectRequest request=new JsonObjectRequest(
                        Request.Method.POST,
                        apiUrl,
                        data,
                        response -> Toast.makeText(getApplicationContext(),"Succesfully added",Toast.LENGTH_LONG).show(),
                        Error -> Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show()
                );
                RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
                queue.add(request);
            }
        });


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}