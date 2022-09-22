package org.codeforiraq.autheticationcrud.UI;

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
import org.json.JSONException;
import org.json.JSONObject;
import org.codeforiraq.autheticationcrud.viewalluser;
import java.util.HashMap;
import java.util.Map;

public class EditData extends AppCompatActivity {




    private EditText nameEdit , emailEdit,status;
    private Bundle extras;
    private Button editButton;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);


        nameEdit = findViewById(R.id.nameEditpage);
        emailEdit = findViewById(R.id.authorEditpage);
        status=findViewById(R.id.statusEditpage);
        editButton = findViewById(R.id.buttonEditpage);

        extras = getIntent().getExtras();

        if(extras != null){
            nameEdit.setText(extras.getString("name"));
            emailEdit.setText(extras.getString("email"));
            status.setText(extras.getString("status"));
            id = extras.getInt("id");
        }



        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editData(id);

                finish();
                startActivity(new Intent(EditData.this, viewalluser.class));
            }
        });
    }



    private void editData(int id){
        final String token = SessionManager.getInstance(this).getToken().getToken();

        final String myName = nameEdit.getText().toString().trim();
        final String email = emailEdit.getText().toString().trim();
        final String mystatus = status.getText().toString().trim();

        if(TextUtils.isEmpty(myName)){
            nameEdit.setError("Enter you email please");
            nameEdit.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(email)){
            emailEdit.setError("Enter you password please");
            emailEdit.requestFocus();
            return;
        }


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        JSONObject postparams = new JSONObject();
        try {
            postparams.put("name",myName);
            postparams.put("email",email);
            postparams.put("status",mystatus);
        }catch (JSONException e){
            e.getMessage();
        }



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( Request.Method.PUT,
                URLs.update +id , postparams, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {

                    if(response.getBoolean("success")){
                        Toast.makeText(getApplicationContext(),response.getString("message")
                                ,Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();


                    }else{
                        Toast.makeText(getApplicationContext(),"error"
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

