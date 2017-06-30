package com.example.tgirardot.tetris_girardot;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.test.suitebuilder.TestMethod;
import android.util.Log;
import android.view.Menu;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private GridView layout;
    private int sizeGrid = 3;
    private boolean sizeSelected = false;
    private boolean picSelected = false;
    private int numPic;
    private long idPic;

    private   Integer[] mThumbIds = { // Les images du jeu
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, mThumbIds));

        // Les boutons de tailles de grille
        Button button3 = (Button) findViewById(R.id.button1); // 3x3
        Button button4 = (Button) findViewById(R.id.button2); // 4x4
        Button button5 = (Button) findViewById(R.id.button3); // 5x5
        final Button buttonGo = (Button) findViewById(R.id.button4);

        final TextView sizeText = (TextView) findViewById(R.id.text_view_id1);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Log.d("Item_Gridview", "Je clique sur l'image " + position);
                picSelected = true;
                numPic = position;
                idPic = id;
                gamePosible(buttonGo, sizeSelected, picSelected);
            }
        });


        findViewById(R.id.button1).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inform the user the button has been clicked
                Log.d("Btn3*3", "Click Button 3*3");
                sizeGrid = 3;
                sizeSelected = true;
                sizeText.setText(R.string.text_view_id1_3);
                gamePosible(buttonGo, sizeSelected, picSelected);
            }
        });

        findViewById(R.id.button2).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inform the user the button has been clicked
                Log.d("Btn4*4", "Click Button 4*4");
                sizeGrid = 4;
                sizeSelected = true;
                sizeText.setText(R.string.text_view_id1_4);
                gamePosible(buttonGo, sizeSelected, picSelected);
            }
        });

        findViewById(R.id.button3).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inform the user the button has been clicked
                Log.d("Btn5*5", "Click Button 5*5");
                sizeGrid = 5;
                sizeSelected = true;
                sizeText.setText(R.string.text_view_id1_5);
                gamePosible(buttonGo, sizeSelected, picSelected);
            }
        });

        findViewById(R.id.button4).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inform the user the button has been clicked
                Log.d("New_Activity", "Lancer une nouvelle activité");
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("selectedSize", sizeGrid);
                intent.putExtra("selectedImage", numPic);
                startActivity(intent);
            }
        });

    }

    public void gamePosible(Button buttonGo, boolean sizeSelected, boolean picSelected) {
        if(sizeSelected == true && picSelected == true) {
            buttonGo.setVisibility(View.VISIBLE);
        }
    }

}
