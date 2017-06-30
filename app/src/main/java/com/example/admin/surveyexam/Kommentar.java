package com.example.admin.surveyexam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStreamWriter;

public class Kommentar extends AppCompatActivity {

    Button submitNow;
    SharedPreferences kommentarPref;
    EditText kommentarEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kommentar);

        kommentarEditor = (EditText) findViewById(R.id.kommentarFelt);

        SharedPreferences harDetPref = getSharedPreferences("harDuDetIdag", 0);
        final String brugerenHarDet = harDetPref.getString("harDuDetIdag", "Brugeren overlevede ikke.");

        SharedPreferences smertePref = getSharedPreferences("smerteNiveauIdag", 0);
        final String brugerensSmerte = smertePref.getString("smerteNiveauIdag", "Muligvis død.");

        SharedPreferences prefs = getSharedPreferences("alder", 0);
        final String brugerensAlder = prefs.getString("alder", "Bruger alder ikke fundet :(");
        SharedPreferences koenPrefs = getSharedPreferences("koen", 0);
        final String brugerensKoen = koenPrefs.getString("koen", "Brugeren er trans.");


        Toast.makeText(getApplicationContext(), brugerenHarDet, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), brugerensSmerte, Toast.LENGTH_LONG).show();

        submitNow = (Button) findViewById(R.id.submitNow);

        kommentarPref = getSharedPreferences("kommentar", 0);


        submitNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kommentarInput = kommentarEditor.getText().toString();

                SharedPreferences.Editor kommentarEditor = kommentarPref.edit();
                kommentarEditor.putString("kommentar", kommentarInput).commit();


                    Log.i("Send email", "");

                    String[] TO = {"heinekristian@gmail.com"};
                    String[] CC = {"mads0014@gmail.com"};
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");


                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_CC, CC);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Blabla");

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                        finish();
                        Log.i("Finished", "");
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(Kommentar.this,
                                "There is no email client installed.", Toast.LENGTH_SHORT).show();

                }

                Toast.makeText(getApplicationContext(), "Tak for din besvarelse!", Toast.LENGTH_LONG).show();

            }
        });

    }
}
