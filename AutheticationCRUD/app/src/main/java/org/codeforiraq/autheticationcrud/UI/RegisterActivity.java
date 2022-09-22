package org.codeforiraq.autheticationcrud.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.codeforiraq.autheticationcrud.Controller.SessionManager;
import org.codeforiraq.autheticationcrud.Controller.VolleySingleton;
import org.codeforiraq.autheticationcrud.MainActivity;
import org.codeforiraq.autheticationcrud.Model.User;
import org.codeforiraq.autheticationcrud.R;
import org.codeforiraq.autheticationcrud.SERVER.URLs;
import org.codeforiraq.autheticationcrud.ViewAdmin;
import org.codeforiraq.autheticationcrud.driverviolation;
import org.codeforiraq.autheticationcrud.viewSearch;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullname , email, password,carnam,carnum;
    private Button buttonRegister;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullname = findViewById(R.id.registerNametext);
        email = findViewById(R.id.registerEmailText);
        password = findViewById(R.id.registerPasswordText);
        buttonRegister = findViewById(R.id.buttonRegister);
        carnam = findViewById(R.id.registercarnam);
        carnum = findViewById(R.id.registercarnum);
        progressBar = findViewById(R.id.progressBarRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

            }
        });
    }




    public  void  registerUser(){
        final String myName = fullname.getText().toString().trim();
        final String myEmail = email.getText().toString().trim();
        final String myPassword = password.getText().toString().trim();
        final String carname = carnam.getText().toString().trim();
        final String carnumber = carnum.getText().toString().trim();
        if(TextUtils.isEmpty(myEmail)){
            email.setError("Enter you email please");
            email.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(myName)){
            fullname.setError("Enter you name please");
            fullname.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(myPassword)){
            password.setError("Enter you password please");
            password.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(carname)){
            password.setError("Enter you car name please");
            password.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(carnumber)){
            password.setError("Enter you car number please");
            password.requestFocus();
            return;
        }


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLs.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.VISIBLE);


                try {
                    JSONObject  obj = new JSONObject(response);
                    if (obj.getBoolean("RA")) {
                        progressBar.setVisibility(View.INVISIBLE);


                        JSONObject userJson = obj.getJSONObject("data");

                        User user = new User(userJson.getString("token"));

                        SessionManager.getInstance(getApplicationContext()).userLogin(user);
                        String vv=userJson.getString("car_num");
                        Toast.makeText(getApplicationContext(),
                                vv, Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);

                        intent.putExtra("value",vv);

                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Register failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        )

        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{

                Map<String,String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("name", myName);
                params.put("email", myEmail);
                params.put("car_num", carnumber);
                params.put("car_name", carname);
                params.put("password", myPassword);
                params.put("c_password", myPassword);
                return  params;

            }
        }   ;


        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }










}
