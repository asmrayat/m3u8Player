package com.asmlab.m3u8player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    Button playBtn,clearBtn,pasteBtn;
    EditText input;

    String link;

    AdView mAdView ;

    ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playBtn = findViewById(R.id.play);
        clearBtn = findViewById(R.id.clear);
        pasteBtn = findViewById(R.id.paste);

        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        input = findViewById(R.id.inputText);
        mAdView  = findViewById(R.id.adView);

        //----------------------------------------------------------------------------------
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        //-----------------------------------------------------------------------------------



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
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link = input.getText().toString().trim();
                if(link.length()>0){
                    input.setText("");
                    Toast.makeText(MainActivity.this,
                            "Cleared", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,
                            "Nothing to clear", Toast.LENGTH_LONG).show();
                }

            }
        });
        pasteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData.Item item = clipboardManager.getPrimaryClip().getItemAt(0);
                String data = item.getText().toString().trim();
                if(data.length()>0){
                    input.setText(item.getText().toString());
                }
                else {
                    Toast.makeText(MainActivity.this,
                            "Nothing to paste", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
