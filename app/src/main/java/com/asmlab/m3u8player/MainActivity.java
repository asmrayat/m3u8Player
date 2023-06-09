package com.asmlab.m3u8player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button playBtn;
    EditText input;

    String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = findViewById(R.id.play);
        input = findViewById(R.id.inputText);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link = input.getText().toString().trim();
                if(link.length()>0){
                    player.websiteUri = link;
                    Intent intent = new Intent(MainActivity.this, player.class);
                    startActivity(intent);
                    input.setText("");
                    Toast.makeText(MainActivity.this,
                            "Playing...", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,
                            "Please enter a Link", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
