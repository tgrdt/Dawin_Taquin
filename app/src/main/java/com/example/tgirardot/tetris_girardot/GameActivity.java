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
    private Bitmap btn_tmp = null;

    private int _width;

    private GridView game_layout;

    private ArrayList<Bitmap> mThumbIds = new ArrayList<>();
    private ArrayList<Bitmap> mThumbOriginal = new ArrayList<>();
    private ArrayList<Bitmap> mThumbPuzzled = new ArrayList<>();


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

                mThumbIds.remove(8);
                Log.d("Test", "Before create");
                btn_tmp = Bitmap.createBitmap(by3,by3, Bitmap.Config.ALPHA_8);
                Log.d("Test", "after create");
                mThumbIds.add(btn_tmp);
                Log.d("Test", "after add");

//                Log.d("Matrix", "MATRIX : " + matrixGrille);

                /*
                mThumbOriginal = mThumbIds;
                mThumbPuzzled.add(mThumbIds.get(3));
                mThumbPuzzled.add(mThumbIds.get(6));
                mThumbPuzzled.add(mThumbIds.get(2));

                mThumbPuzzled.add(mThumbIds.get(7));
                mThumbPuzzled.add(mThumbIds.get(5));
                mThumbPuzzled.add(mThumbIds.get(1));

                mThumbPuzzled.add(mThumbIds.get(0));
                mThumbPuzzled.add(mThumbIds.get(5));
                mThumbPuzzled.add(mThumbIds.get(8));
                */

                break;
            case 4:
                int by4 = _width / 4;

                for(int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        mThumbIds.add(Bitmap.createBitmap(toPuzzle,by4 * j, by4 * i, by4, by4  ));

                    }
                }

                mThumbIds.remove(15);
                btn_tmp = Bitmap.createBitmap(by4,by4, Bitmap.Config.ALPHA_8);
                mThumbIds.add(btn_tmp);
                /*
                mThumbOriginal = mThumbIds;
                mThumbPuzzled.add(mThumbIds.get(5));
                mThumbPuzzled.add(mThumbIds.get(9));
                mThumbPuzzled.add(mThumbIds.get(12));
                mThumbPuzzled.add(mThumbIds.get(0));

                mThumbPuzzled.add(mThumbIds.get(8));
                mThumbPuzzled.add(mThumbIds.get(11));
                mThumbPuzzled.add(mThumbIds.get(1));
                mThumbPuzzled.add(mThumbIds.get(3));

                mThumbPuzzled.add(mThumbIds.get(2));
                mThumbPuzzled.add(mThumbIds.get(13));
                mThumbPuzzled.add(mThumbIds.get(7));
                mThumbPuzzled.add(mThumbIds.get(14));

                mThumbPuzzled.add(mThumbIds.get(10));
                mThumbPuzzled.add(mThumbIds.get(4));
                mThumbPuzzled.add(mThumbIds.get(6));
                mThumbPuzzled.add(mThumbIds.get(15));
                */

                break;
            case 5:
                int by5 = _width / 5;

                for(int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        mThumbIds.add(Bitmap.createBitmap(toPuzzle,by5 * j, by5 * i, by5, by5  ));

                    }
                }
                mThumbIds.remove(24);
                btn_tmp = Bitmap.createBitmap(by5,by5, Bitmap.Config.ALPHA_8);
                mThumbIds.add(btn_tmp);
                /*

                mThumbOriginal = mThumbIds;

                mThumbPuzzled.add(mThumbIds.get(19));
                mThumbPuzzled.add(mThumbIds.get(11));
                mThumbPuzzled.add(mThumbIds.get(9));
                mThumbPuzzled.add(mThumbIds.get(16));
                mThumbPuzzled.add(mThumbIds.get(6));

                mThumbPuzzled.add(mThumbIds.get(10));
                mThumbPuzzled.add(mThumbIds.get(3));
                mThumbPuzzled.add(mThumbIds.get(5));
                mThumbPuzzled.add(mThumbIds.get(21));
                mThumbPuzzled.add(mThumbIds.get(23));

                mThumbPuzzled.add(mThumbIds.get(15));
                mThumbPuzzled.add(mThumbIds.get(12));
                mThumbPuzzled.add(mThumbIds.get(22));
                mThumbPuzzled.add(mThumbIds.get(7));
                mThumbPuzzled.add(mThumbIds.get(14));

                mThumbPuzzled.add(mThumbIds.get(13));
                mThumbPuzzled.add(mThumbIds.get(4));
                mThumbPuzzled.add(mThumbIds.get(1));
                mThumbPuzzled.add(mThumbIds.get(20));
                mThumbPuzzled.add(mThumbIds.get(8));

                mThumbPuzzled.add(mThumbIds.get(0));
                mThumbPuzzled.add(mThumbIds.get(17));
                mThumbPuzzled.add(mThumbIds.get(2));
                mThumbPuzzled.add(mThumbIds.get(18));
                mThumbPuzzled.add(mThumbIds.get(24));
*/

                break;
            default:
                int by6 = _width / 3;

                for(int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        mThumbIds.add(Bitmap.createBitmap(toPuzzle,by6 * j, by6 * i, by6, by6  ));

                    }
                }
                mThumbOriginal = mThumbIds;
                break;
        }

        Bitmap matrixGrilleOriginel[][] = createMatrice(sizeGrille, mThumbIds);

        Bitmap matrixGrillePuzzled[][] = null;




        Button buttonStart = (Button) findViewById(R.id.buttonStart);
        Log.d("Toto", "tot");





    }

    public Bitmap[][] createMatrice(int sizeGrille, ArrayList<Bitmap> bitmaps){

        Log.d("Test", "entree fonction");

        Bitmap matrixGrille[][] = new Bitmap[sizeGrille][sizeGrille];
        int listLenght = bitmaps.size();
        int tmp = 0;
        while(tmp < listLenght) {
            for(int i = 0; i < sizeGrille; i++) {
                Log.d("Test", "entree boucle 1");
                for(int j = 0; j < sizeGrille; j++) {
                    Log.d("Test", "entree boucle 2");
                    matrixGrille[i][j] = bitmaps.get(tmp);
                    tmp = tmp + 1;
                }
            }
        }

        Log.d("Test", "fin while");
        return matrixGrille;
    }


}
