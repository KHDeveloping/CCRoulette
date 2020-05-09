package com.example.ccroulette;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private EditText txt1;
    public static ArrayList<String> playerList = new ArrayList<>();
    private TextView mText;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        playerList = new ArrayList<String>();
        lv = (ListView) findViewById(R.id.lv); // Instantiates new list view
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this,
                        android.R.layout.simple_list_item_1,
                        playerList);
        lv.setAdapter(arrayAdapter);//Sets list view to adapt
        txt1 = (EditText) findViewById(R.id.editText1); // Instantiates edittext

        //add more items button
        ImageButton add = findViewById(R.id.button1);
        add.setOnClickListener(new View.OnClickListener() {
                                   public void onClick(View view) {
                                       String aB = txt1.getText().toString().trim();//Gets text from edittext
                                       if (aB.length() != 0) {
                                           playerList.add(aB);
                                           txt1.setText(""); // adds text to arraylist and make edittext blank again
                                           arrayAdapter.notifyDataSetChanged();// Updates array adapter when new player is added
                                       }
                                   }
                               }
        );

        Button done = (Button) findViewById(R.id.done_button);
        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextView mText = findViewById(R.id.myText); //Instantiates new text view
                for (int i = 0; i < playerList.size(); i++) { //Loops through list
                    Collections.shuffle(playerList);//Randomizes the list
                    mText.setText(playerList.get(i));//Gets string from list and displays it in the textview
                    Toast toast = Toast.makeText(MainActivity.this, "Pay up, better luck next time!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 250);
                    toast.show();
                }
            }

        });



        Button reset = (Button) findViewById(R.id.reset_button);
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                playerList.clear(); //Clears the arraylist after button is pressed
                arrayAdapter.notifyDataSetChanged();
            }
        })
        ;
    }
}

