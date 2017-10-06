/*
Class: CS262
Team:Nathanael Dick, Jesse Kuntz
Lab 3: downloads a file and outputs it into a multiline text view.
References: see below to see where used
ref1:http://android-er.blogspot.com/2011/04/read-text-file-from-internet-using-java.html
ref2:http://www.java-samples.com/showtutorial.php?tutorialid=1521
 */
package com.example.jrk54.blockingapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private String[] objectSpinner;
    private String[] colorSpinner;
    private String[] sceneSpinner;
    private Button downloadButton;
    private TextView sceneTextView;
    private String sceneText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.objectSpinner = new String[] {
                "Couch", "Table", "Chair", "Person", "Lamp"
        };
        Spinner object = (Spinner) findViewById(R.id.objectMenu);
        ArrayAdapter<String> objectAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, objectSpinner);
        object.setAdapter(objectAdapter);

        this.colorSpinner = new String[] {
                "Blue", "Red", "Yellow", "Green", "Pink"
        };
        Spinner color = (Spinner) findViewById(R.id.colorMenu);
        ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, colorSpinner);
        color.setAdapter(colorAdapter);

        this.sceneSpinner = new String[] {
                "1", "2", "3", "4", "5"
        };
        Spinner scene = (Spinner) findViewById(R.id.sceneMenu);
        ArrayAdapter<String> sceneAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sceneSpinner);
        scene.setAdapter(sceneAdapter);

        this.sceneTextView = (TextView) findViewById(R.id.readLines);

        this.downloadButton = (Button) findViewById(R.id.downloadButton);
        downloadButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                Download d = new Download();
                d.execute();
                sceneTextView.setText(sceneText);
            }
        });
    }


/*class header and method references:
*ref1:http://android-er.blogspot.com/2011/04/read-text-file-from-internet-using-java.html
*ref2:http://www.java-samples.com/showtutorial.php?tutorialid=1521
*/
    public class Download extends AsyncTask <Void, Void, String> {


        @Override
        protected String doInBackground(Void... params) {
            try {
                //defines the url
                URL url = new URL("http://www.calvin.edu/~pmb4/newscript.txt");
                //reference:ref1 lines ecspecially lines 85-96 used code from reference above
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                String StringBuffer;
                String stringText ="";
                while((StringBuffer = br.readLine()) != null) {
                    stringText += StringBuffer + "\n";
                }
                System.out.println(stringText);
                sceneText = stringText;
                br.close();

                return stringText;
            }
            catch (IOException e) {
                Log.e("Blocking App", e.toString());
            }
            return null;
        }
    }

}
