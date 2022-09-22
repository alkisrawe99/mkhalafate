package org.codeforiraq.autheticationcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.codeforiraq.autheticationcrud.Controller.SessionManager;
import org.codeforiraq.autheticationcrud.Controller.VolleySingleton;
import org.codeforiraq.autheticationcrud.MainActivity;
import org.codeforiraq.autheticationcrud.R;
import org.codeforiraq.autheticationcrud.SERVER.URLs;
import org.codeforiraq.autheticationcrud.UI.Editcar;
import org.codeforiraq.autheticationcrud.Editviolation;
import org.json.JSONException;
import org.json.JSONObject;
import org.codeforiraq.autheticationcrud.viewalluser;
import java.util.HashMap;
import java.util.Map;

public class Editviolation extends AppCompatActivity {




    private EditText car_name, name_of_driver,name_vio,location ,car_num,price;
    private Bundle extras;
    private Button editButton;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_violation);


        car_name = findViewById(R.id.nameEditpage);
        price = findViewById(R.id.vioedit);

        location = findViewById(R.id.location);
        name_of_driver = findViewById(R.id.name_driver);

        name_vio = findViewById(R.id.price_edit);
        car_num = findViewById(R.id.authorEditpage);





        editButton = findViewById(R.id.buttonEditpage);

        extras = getIntent().getExtras();

        if(extras != null){
            car_name.setText(extras.getString("car_name"));
            car_num.setText(extras.getString("car_num"));
            price.setText(extras.getString("price"));
            location.setText(extras.getString("location"));
            name_vio.setText(extras.getString("name_vio"));
            name_of_driver.setText(extras.getString("name_of_driver"));
            id = extras.getInt("id");
        }
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editData(id);

                finish();
                startActivity(new Intent(Editviolation.this, viewallvio.class));
            }
        });
    }
    private void editData(int id){
        final String token = SessionManager.getInstance(this).getToken().getToken();

        final String namecar = car_name.getText().toString().trim();
        final String numbercar = car_num.getText().toString().trim();
        final String locations = location.getText().toString().trim();
        final String name_driver = name_of_driver.getText().toString().trim();
        final String pricee = price.getText().toString().trim();
        final String vv = name_vio.getText().toString().trim();




        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        JSONObject postparams = new JSONObject();
        try {
            postparams.put("car_name",namecar);
            postparams.put("car_num",numbercar);

            postparams.put("location",locations);
            postparams.put("name_of_driver",name_driver);

            postparams.put("price",pricee);
            postparams.put("name_vio",vv);
        }catch (JSONException e){
            e.getMessage();
        }



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( Request.Method.PUT,
                URLs.update_vio +id , postparams, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {

                    if(response.getBoolean("success")){
                        Toast.makeText(getApplicationContext(),response.getString("message")
                                ,Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();


                    }else{
                        Toast.makeText(getApplicationContext(),"success"
                                ,Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }


                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } )

        {
            public Map<String,String> getHeaders(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("Accept","application/json");
                params.put("Authorization","Bearer  "+ "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMWY4YzE4ODkwM2UzOTJjMGFkNDBjZTg0NzY4ZTk3MzczYTE2MTFmMTcxNzI3OWMxYzZiY2JkNTVkNTNiMTBkZjc5YTFlZTBkNmNjZWZmNTciLCJpYXQiOjE2NDA5MjQzMDMsIm5iZiI6MTY0MDkyNDMwMywiZXhwIjoxNjcyNDYwMzAzLCJzdWIiOiIzNCIsInNjb3BlcyI6W119.Us8ybzq9v-7VBpfdHtItb1v9cRVEjAzEmMUKXEi_1t_KaGldsvjjIiqiz0XWZJSa7lsmPoRvUe_gON1U6CW2oxnD7Wllwup79LbMcmi_L68fD8AMc1NlhQIbJafYu1ajDWmDUxg2ZKfixOBufkTE9mciDwuDCJVzhPVZ-pfKuw-CnnOVxImrmnsI2EEorNwxhJ_9B2qpGBYZCrA3WzG8hNiy-OMFHrkKlHqfZFUfXSMxbll-wg2aLS2DlyrcMimJKAbABkJt3UY-dfw15e9SCrmbUR6ojaAF6K9Yaf8lGv2wnlXVLHHWs2i7AU8JwAW1bJ-ehHyfbaNphvhZ3vil0Uqm65ME_7inJbnNiZjlDzkhXX15U3ds3s7RMCGSWMqMcqwwlESBodgpC5Fgq3tBZEGyeYDEYD6EblZe4BgvyA0Eh9qZwSdirfGwrQgCyFKmFGSydatlQKMwfV83mRd1UKJNyTjAOfi2hzzOnaTVKSPbJG8hIz-sxYO40domW9mDY-cxVqmUczOhQ8QnVAtuy0KQ80-RAm9eKwuYB8_7POvqSoacX-BnkzLIMUw0ITGIIUR87nFzmCEJaBttiWuNbm0bdMEjLoXoRBPTJRnuomemWWeZWibcuzarDMtayJopsEQ0XgK8F3o3FYsuluZ2-hEj1t7Frg4ddW1m4fryQHY");
                return params;
            }
        }

                ;

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);










    }














}

