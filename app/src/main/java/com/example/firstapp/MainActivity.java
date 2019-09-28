package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // creating variables :-
    TextView tvtaskbar;
    EditText et1,et2;
    Button btnadd , btntaskbar , btncall;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getting access to objects of xml file :-
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        //tv1 = findViewById(R.id.tv1);
        btnadd = findViewById(R.id.btnadd);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // importing data from edit text boxes and converting them into integers :-
                int var1 = Integer.valueOf(et1.getText().toString());
                int var2 = Integer.valueOf(et2.getText().toString());

                // adding and storing the value of the two entered numbers :-
                int var3 = var1 + var2;

                //setting value of text box to the result of the input nubers :-
                //tv1.setText(String.valueOf(var3));

                // travelling from one page/activity to another :-
                //                          name of source page.this, name of destination page.class
                Intent i = new Intent( MainActivity.this , second.class ) ;
                i.putExtra("result",var3);// this creates a table with key value ""result'" and its value as var3
                startActivity(i) ;

            }
        });

        // getting permisions to access phone state and other things :-
        tvtaskbar = findViewById(R.id.tvtaskbar);
        btntaskbar = findViewById(R.id.btntaskbar);

        btntaskbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo netinfo = cm.getActiveNetworkInfo();
                boolean isconn = netinfo != null && netinfo.isConnected();

                tvtaskbar.setText(isconn ? "Connected" : "Not connected");

            }
        });

        //getting dangerous permissions(permissions which need to be allowed by the user)
        btncall = findViewById(R.id.btncall);
        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chekcing if the permission has already been granted :
                int perm = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE); // returns 0 if yes and -1 if no

                if (perm == PackageManager.PERMISSION_GRANTED){
                    call_phone();
                }
                else{
                    //                                      name of activity   ,   name of permission           ,   request code
                    ActivityCompat.requestPermissions(MainActivity.this , Manifest.permission.CALL_PHONE , 121 );
                }
            }
        });

        void call_phone(){
            String num = et1.getText().toString();
            Uri uri = Uri.parse("tel:" + num);
            Intent i = new Intent(Intent.ACTION_CALL , uri);
            startActivity(i);
        }


    }


}
