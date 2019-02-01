package com.example.sethu.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b,b1;
    EditText e1, e2;
    String s1, s2;
    databasehelp database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button) findViewById(R.id.submit);
        b1=(Button)findViewById(R.id.search);
        e1 = (EditText) findViewById(R.id.name);
        e2 = (EditText) findViewById(R.id.email);
        database = new databasehelp(this);
        database.getWritableDatabase();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = e1.getText().toString();
                s2 = e2.getText().toString();
                Log.d("name", s1);
                Log.d("email", s2);

                boolean status = database.insertData(s1, s2);
                if (status == true) {
                    Toast.makeText(getApplicationContext(), "Successfully insert", Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),SEARCH.class );
                startActivity(i);
            }
        });


    }
}
