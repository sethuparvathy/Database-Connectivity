package com.example.sethu.sqlite;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SEARCH extends AppCompatActivity {
    EditText e1,e2;
    Button b,b1,b2;
    String s1,s2,newvalue,id;
    databasehelp database;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        builder=new AlertDialog.Builder(this);
        builder.setTitle("confirm");
        builder.setMessage("Are you sure want to delete ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"yes clicked",Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();
                boolean status=database.deletedata(id);
                       if (status==true) {
                            Toast.makeText(getApplicationContext(), "deleted successfully", Toast.LENGTH_LONG).show();

                        }

                       else {
                            Toast.makeText(getApplicationContext(),"error in delete",Toast.LENGTH_LONG).show();
                        }
            }

        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"no clicked",Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();

            }
        });


        database = new databasehelp(this);
        database.getWritableDatabase();

        b = (Button) findViewById(R.id.search);
        b1=(Button)findViewById(R.id.update);
        b2=(Button)findViewById(R.id.del);
        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.up);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=e1.getText().toString();
                Log.d("name",s1);
                 Cursor cur=database.searchdata(s1);
                if (cur.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(),"NO NAME FOUND",Toast.LENGTH_LONG).show();
                }
                else {
                    while (cur.moveToNext())
                    {
                        id=cur.getString(0);
                       s2=cur.getString(2);

                        e2.setText(s2);
                        Toast.makeText(getApplicationContext(),s2,Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),id,Toast.LENGTH_LONG).show();
                    }
                }
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newvalue=e2.getText().toString();

                        boolean status=database.updatedata(id,newvalue);
                        if(status==true)
                        {
                            Toast.makeText(getApplicationContext(),"updated successfully",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"error updation",Toast.LENGTH_LONG).show();

                        }
                        Toast.makeText(getApplicationContext(),newvalue,Toast.LENGTH_LONG).show();

                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    AlertDialog alertDialog=builder.create();
                        alertDialog.show();
                    }
                });

            }
        });
    }
}
