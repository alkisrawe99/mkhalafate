package org.codeforiraq.autheticationcrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.codeforiraq.autheticationcrud.UI.AddData;
import org.codeforiraq.autheticationcrud.addviolation;
import org.codeforiraq.autheticationcrud.UI.Editcar;

import androidx.appcompat.app.AppCompatActivity;




public class ViewAdmin extends AppCompatActivity {
    private Button viewuser,buttonorder,buttonviewcar,buttonviewviolations
            ,buttonaddviolations,search,addviolation;
    private EditText se;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewadmin);
        viewuser = findViewById(R.id.viewuser);

        buttonviewviolations = findViewById(R.id.buttonviewviolations);
        buttonviewcar = findViewById(R.id.buttonviewcar);
        buttonaddviolations = findViewById(R.id.buttonaddviolations);
       addviolation = findViewById(R.id.addviolation);

        search = findViewById(R.id.buttonsearch);
         se=(EditText) findViewById(R.id.search);

        viewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listuser();
            }
        });
        buttonaddviolations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adduser();
            }
        });
        buttonviewcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewcar();
            }
        });
        buttonviewviolations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 viewvio();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewsearch();
            }
        });
        addviolation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addviolation();
            }
        });
    }
    private void listuser() {
        finish();
        startActivity(new Intent(getApplicationContext(),
        viewalluser.class));

    }
    private void addviolation() {
        finish();
        startActivity(new Intent(getApplicationContext(),
                org.codeforiraq.autheticationcrud.addviolation.class));


    }
    private void adduser() {
        finish();
        startActivity(new Intent(getApplicationContext(),
                AddData.class));

    }
    private void viewcar() {
        finish();
        startActivity(new Intent(getApplicationContext(),
                viewallcar.class));

    }

    private void viewvio() {
        finish();
        startActivity(new Intent(getApplicationContext(),
                viewallvio.class));

    }
    private void viewsearch() {
        finish();
        String value = se.getText().toString();
        Intent intent = new Intent(getApplicationContext(), viewSearch.class);

        intent.putExtra("value",value);

        startActivity(intent);



    }
}



