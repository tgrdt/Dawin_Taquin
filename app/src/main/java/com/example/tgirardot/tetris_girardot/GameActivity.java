package com.example.tgirardot.tetris_girardot;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by tgirardot on 26/06/17.
 */
public class GameActivity extends Activity {

    private Bitmap toPuzzle = null;
    private int _width;
    private GridView game_layout;

    private ArrayList<Bitmap> mThumbIds = new ArrayList<>();


    public void onCreate(Bundle savedInstanceState) {


        Log.d("A", "a");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_game);

        Intent intent = getIntent();
        int sizeGrille = intent.getIntExtra("selectedSize", 0);

        int numPic = intent.getIntExtra("selectedImage", 0);

        Log.d("Toto", "toaaaat");


        GridView gridview = (GridView) findViewById(R.id.gridview_game);
        gridview.setNumColumns(sizeGrille);
        gridview.setAdapter(new ImageAdapterBitmap(this, mThumbIds));

        switch(numPic) {
            case 0:
                 toPuzzle = BitmapFactory.decodeResource(getResources(), R.drawable.sample_2);
                break;
            case 1:
                 toPuzzle = BitmapFactory.decodeResource(getResources(), R.drawable.sample_3);
                break;
            case 2:
                toPuzzle = BitmapFactory.decodeResource(getResources(), R.drawable.sample_4);
                break;
            case 3:
                toPuzzle = BitmapFactory.decodeResource(getResources(), R.drawable.sample_5);
                break;
            case 4:
                toPuzzle = BitmapFactory.decodeResource(getResources(), R.drawable.sample_6);
                break;
            case 5:
                toPuzzle = BitmapFactory.decodeResource(getResources(), R.drawable.sample_7);
                break;
            case 6:
                toPuzzle = BitmapFactory.decodeResource(getResources(), R.drawable.sample_0);
                break;
            case 7:
                toPuzzle = BitmapFactory.decodeResource(getResources(), R.drawable.sample_1);
                break;
            default:
                toPuzzle = BitmapFactory.decodeResource(getResources(), R.drawable.sample_0);
                break;

        }

        Log.d("Toto", "totazerze");

        _width = toPuzzle.getWidth();

        Log.d("Toto", "totdfsdfsdf");
        Log.d("sizeGrille", "Taille grille " + sizeGrille);
        Log.d("sizeGrille", "image numero  " + numPic);

        switch (sizeGrille) {
            case 3:
                int by3 = _width / 3;
                Log.d("sizeGrille", "By3 = " + by3);
                Log.d("sizeGrille", "je vais aller dans la boucle");

                for(int i = 0; i < 3; i++) {
                    Log.d("sizeGrille", "je rentre dans la boucle");

                    for (int j = 0; j < 3; j++) {
                        Log.d("sizeGrille", "je plop la boucle");

                        mThumbIds.add(Bitmap.createBitmap(toPuzzle,(by3 * j), (by3 * i), by3, by3  ));
                        Log.d("sizeGrille", "je suis dans la boucle");
                    }
                }
                Log.d("sizeGrille", "je suis passee dans la boucle");

                break;
            case 4:
                int by4 = _width / 4;

                for(int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        mThumbIds.add(Bitmap.createBitmap(toPuzzle,by4 * j, by4 * i, by4, by4  ));

                    }
                }
                break;
            case 5:
                int by5 = _width / 5;

                for(int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        mThumbIds.add(Bitmap.createBitmap(toPuzzle,by5 * j, by5 * i, by5, by5  ));

                    }
                }

                break;
            default:
                int by6 = _width / 3;

                for(int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        mThumbIds.add(Bitmap.createBitmap(toPuzzle,by6 * j, by6 * i, by6, by6  ));

                    }
                }break;
        }



        Button buttonStart = (Button) findViewById(R.id.buttonStart);
        Log.d("Toto", "tot");





    }


}
