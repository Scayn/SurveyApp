package com.example.admin.surveyexam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class HvordanHarDuDet extends AppCompatActivity {

    Button nextTwo;

    Spinner harDetIdag;
    Spinner painLevel;

    SharedPreferences harDetPref;
    SharedPreferences smertePref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hvordan_har_du_det);

        harDetIdag = (Spinner) findViewById(R.id.hvordanHarDuDet);
        painLevel = (Spinner) findViewById(R.id.smerteniveau_spinner);


        nextTwo = (Button) findViewById(R.id.nextTwo);



        SharedPreferences koenPrefs = getSharedPreferences("koen", 0);
        String brugerensKoen = koenPrefs.getString("koen", "Brugeren er trans.");

        SharedPreferences prefs = getSharedPreferences("alder", 0);
        String brugerensAlder = prefs.getString("alder", "Bruger alder ikke fundet :(");

        Toast.makeText(getApplicationContext(), brugerensKoen, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), brugerensAlder, Toast.LENGTH_LONG).show();

        harDetPref = getSharedPreferences("harDuDetIdag", 0);
        smertePref = getSharedPreferences("smerteNiveauIdag", 0);



        nextTwo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                String harDetInput = harDetIdag.getSelectedItem().toString();

                SharedPreferences.Editor harDetEditor = harDetPref.edit();
                harDetEditor.putString("harDuDetIdag", harDetInput).commit();


                String smerteInput = painLevel.getSelectedItem().toString();

                SharedPreferences.Editor smerteEditor = smertePref.edit();
                smerteEditor.putString("smerteNiveauIdag", smerteInput).commit();



                Intent myIntentTo = new Intent(HvordanHarDuDet.this, Kommentar.class);
                startActivity(myIntentTo);


            }});


    }
}
