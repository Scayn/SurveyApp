package com.example.admin.surveyexam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import static android.R.id.candidatesArea;
import static android.R.id.list;
import static android.R.id.switch_widget;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerAlder;
    Spinner spinnerKøn;
    Button nextOne;

    SharedPreferences alderPrefs;
    SharedPreferences kønPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerKøn = (Spinner) findViewById(R.id.spinnerKøn);
        spinnerAlder = (Spinner) findViewById(R.id.spinnerAlder);


        nextOne = (Button) findViewById(R.id.nextOne);
        alderPrefs = getSharedPreferences("alder", 0);
        kønPrefs = getSharedPreferences("koen", 0);



        nextOne.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                String alderInput = spinnerAlder.getSelectedItem().toString();


                SharedPreferences.Editor alderEditor = alderPrefs.edit();
                alderEditor.putString("alder", alderInput).commit();

                String kønInput = spinnerKøn.getSelectedItem().toString();

                SharedPreferences.Editor kønEditor = kønPrefs.edit();
                kønEditor.putString("koen", kønInput).commit();

                Intent myIntent = new Intent(MainActivity.this, HvordanHarDuDet.class);
                startActivity(myIntent);


            }});



    }
}
