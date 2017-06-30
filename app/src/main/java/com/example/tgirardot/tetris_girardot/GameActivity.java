package com.example.tgirardot.tetris_girardot;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by tgirardot on 26/06/17.
 */
public class GameActivity extends Activity {

    private Bitmap toPuzzle = null;
    private Bitmap btn_tmp = null;
    private Bitmap emptyCase = null;

    private int _width;

    private boolean _mouvement = false;

    private GridView game_layout;

    private Chronometer timer;

    private ArrayList<Bitmap> mThumbIds = new ArrayList<>();
    private ArrayList<Bitmap> mThumbPuzzled = new ArrayList<>();


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_game);

        Intent intent = getIntent();
        final int sizeGrille = intent.getIntExtra("selectedSize", 0);

        int numPic = intent.getIntExtra("selectedImage", 0);

        final GridView gridview = (GridView) findViewById(R.id.gridview_game);
        gridview.setNumColumns(sizeGrille);

        timer = (Chronometer) findViewById(R.id.gameTimer);
        timer.setFormat("Temps" + " - %s");
        timer.start();


        switch (numPic) { // Recupération de l'image suite au changement d'activité
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

        _width = toPuzzle.getWidth();

        Log.d("sizeGrille", "Taille grille " + sizeGrille);
        Log.d("sizeGrille", "image numero  " + numPic);

        // Oui c'est un switch dégeulasse, j'ai pas eu le temps de refactor en une belle fonction
        switch (sizeGrille) { // découpage de l'image en bitmap pour faire les pieces de jeu
            case 3:
                int by3 = _width / 3;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        mThumbIds.add(Bitmap.createBitmap(toPuzzle, (by3 * j), (by3 * i), by3, by3));
                    }
                }
                // Suppression de la dernière case + ajout case blanche
                mThumbIds.remove(8);
                btn_tmp = Bitmap.createBitmap(by3, by3, Bitmap.Config.ALPHA_8);
                mThumbIds.add(btn_tmp);
                emptyCase = btn_tmp;

                break;
            case 4:
                int by4 = _width / 4;

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        mThumbIds.add(Bitmap.createBitmap(toPuzzle, by4 * j, by4 * i, by4, by4));

                    }
                }
                // Suppression de la dernière case + ajout case blanche
                mThumbIds.remove(15);
                btn_tmp = Bitmap.createBitmap(by4, by4, Bitmap.Config.ALPHA_8);
                mThumbIds.add(btn_tmp);
                emptyCase = btn_tmp;

                break;
            case 5:
                int by5 = _width / 5;

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        mThumbIds.add(Bitmap.createBitmap(toPuzzle, by5 * j, by5 * i, by5, by5));

                    }
                }
                // Suppression de la dernière case + ajout case blanche
                mThumbIds.remove(24);
                btn_tmp = Bitmap.createBitmap(by5, by5, Bitmap.Config.ALPHA_8);
                mThumbIds.add(btn_tmp);
                emptyCase = btn_tmp;

                break;
            default:
                int by6 = _width / 3;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        mThumbIds.add(Bitmap.createBitmap(toPuzzle, by6 * j, by6 * i, by6, by6));

                    }
                }
                // Suppression de la dernière case + ajout case blanche
                mThumbIds.remove(8);
                btn_tmp = Bitmap.createBitmap(by6, by6, Bitmap.Config.ALPHA_8);
                mThumbIds.add(btn_tmp);
                emptyCase = btn_tmp;
                break;
        }

        Bitmap matrixGrilleOriginel[][] = createMatrice(sizeGrille, mThumbIds);

        mThumbPuzzled = randomizeGrid(sizeGrille, mThumbIds);
        mThumbPuzzled = randomizeGrid(sizeGrille, mThumbPuzzled);

        Log.d("Test", "mThumbIds = " + mThumbIds);
        Log.d("Test", "mThumbPuz = " + mThumbPuzzled);

        if (mThumbPuzzled.equals(mThumbIds)) { // On s'amuse ?
            Log.d("test", "ELLES SONT IDENTIQUES");
        } else {
            Log.d("test", "ELLES NE SONT PAS IDENTIQUE");
        }

        final ImageAdapterBitmap myadapter = new ImageAdapterBitmap(this, mThumbPuzzled);
        gridview.setAdapter(myadapter);

        // Evènements sur les éléments de la grille
        // Vérifie si le mouvement est possible pour une pièce + déplacement piece + condition victoire
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Log.d("test", "Image = " + mThumbPuzzled.get(position));
                Bitmap tmpMatrix[][] = createMatrice(sizeGrille, mThumbPuzzled);

                _mouvement = isMouvementOk(sizeGrille, emptyCase, position, mThumbPuzzled, tmpMatrix);
                if (_mouvement) {
                    mThumbPuzzled = deplacementPiece(sizeGrille, emptyCase, position, mThumbPuzzled, tmpMatrix);
                    Log.d("Test", "mThumbIds = " + mThumbIds);
                    Log.d("Test", "mThumbPuz = " + mThumbPuzzled);
                    Log.d("test", "Le mouvement est possible");
                    gridview.setAdapter(new ImageAdapterBitmap(GameActivity.this, mThumbPuzzled));
                    // myadapter.notifyDataSetChanged();
                    // gridview.setAdapter(myadapter); // NE MARCHE PAS
                    if (mThumbPuzzled.equals(mThumbIds)) {
                        timer.stop();
                        Long temps = timer.getBase() - SystemClock.elapsedRealtime();
                        Log.d("victory", "Vous avez gagné  en  " + temps);
                        Toast.makeText(GameActivity.this, "VICTOIRE EN " + temps, Toast.LENGTH_LONG);
                    }

                }
            }
        });

        // Vous aviez dis qu'il fallait du code pour faire des commentaires. Vu que je n'ai pas le premier,
        // je me lâche dans les seconds 0/

    }
    // Deplacement des pièces. Le titre est explicite non ?
    public ArrayList<Bitmap> deplacementPiece(int sizeGrille, Bitmap emptyCase, int position, ArrayList<Bitmap> mThumb, Bitmap[][] matrice) {

        ArrayList<Bitmap> newPuzzled = mThumb;

        for (int i = 0; i < sizeGrille; i++) {
            for (int j = 0; j < sizeGrille; j++) {

                if (matrice[i][j] == mThumb.get(position)) {

                    if ((j > 0) && (matrice[i][j - 1] == emptyCase)) { // cas déplacement vers le haut
                        matrice[i][j - 1] = matrice[i][j];
                        matrice[i][j] = emptyCase;
                        Log.d("test", "deplacement vers haut");
                    } else if ((j < (sizeGrille - 1)) && (matrice[i][j + 1] == emptyCase)) { // cas déplacement vers le bas
                        matrice[i][j + 1] = matrice[i][j];
                        matrice[i][j] = emptyCase;
                        Log.d("test", "deplacement vers bas");
                    } else if ((i < (sizeGrille - 1)) && (matrice[i + 1][j] == emptyCase)) { // cas déplacement vers la droite
                        matrice[i + 1][j] = matrice[i][j];
                        matrice[i][j] = emptyCase;
                        Log.d("test", "deplacement vers droite");
                    } else if ((i > 0) && (matrice[i - 1][j] == emptyCase)) { // cas déplacement vers la gauche
                        matrice[i - 1][j] = matrice[i][j];
                        matrice[i][j] = emptyCase;
                        Log.d("test", "deplacement vers gauche");
                    }
                    newPuzzled = matrixToArray(sizeGrille, matrice);
                    return newPuzzled;
                }
            }
        }

        newPuzzled = matrixToArray(sizeGrille, matrice);

        return newPuzzled;
    }

    // Vérification si déplacement possible
    public boolean isMouvementOk(int sizeGrille, Bitmap emptyCase, int position, ArrayList<Bitmap> mThumb, Bitmap[][] matrice) {

        boolean mouvementPossible = false;

        for (int i = 0; i < sizeGrille; i++) {
            for (int j = 0; j < sizeGrille; j++) {

                if (matrice[i][j] == mThumb.get(position)) {

                    if ((j > 0) && (matrice[i][j - 1] == emptyCase)) { // cas déplacement vers le haut
                        mouvementPossible = true;
                    }

                    if ((j < (sizeGrille - 1)) && (matrice[i][j + 1] == emptyCase)) { // cas déplacement vers le bas
                        mouvementPossible = true;
                    }

                    if ((i < (sizeGrille - 1)) && (matrice[i + 1][j] == emptyCase)) { // cas déplacement vers la droite
                        mouvementPossible = true;
                    }

                    if ((i > 0) && (matrice[i - 1][j] == emptyCase)) { // cas déplacement vers la droite
                        mouvementPossible = true;
                    }
                }
            }
        }

        return mouvementPossible;
    }

    // Transforme un ArrayList en matrice
    public Bitmap[][] createMatrice(int sizeGrille, ArrayList<Bitmap> bitmaps) {

        Bitmap matrixGrille[][] = new Bitmap[sizeGrille][sizeGrille];
        int listLenght = bitmaps.size();
        int tmp = 0;
        while (tmp < listLenght) {
            for (int i = 0; i < sizeGrille; i++) {

                for (int j = 0; j < sizeGrille; j++) {
                    matrixGrille[i][j] = bitmaps.get(tmp);
                    tmp = tmp + 1;
                }
            }
        }
        return matrixGrille;
    }

    // Transforme une matrice en ArrayList
    public ArrayList<Bitmap> matrixToArray(int sizeGrille, Bitmap matrice[][]) {

        ArrayList<Bitmap> listFromMatrix = new ArrayList<>();
        for (int i = 0; i < sizeGrille; i++) {
            for (int j = 0; j < sizeGrille; j++) {

                listFromMatrix.add(matrice[i][j]);

            }
        }

        return listFromMatrix;
    }

    // Mélange les pieces en partant de l'image originale.
    public ArrayList<Bitmap> randomizeGrid(int sizeGrille, ArrayList<Bitmap> bitmapsOriginel) {

        Bitmap emptyCase = bitmapsOriginel.get((bitmapsOriginel.size() - 1));
        Bitmap matrixGrille[][] = new Bitmap[sizeGrille][sizeGrille];

        ArrayList<Bitmap> randomGrid = new ArrayList<>();

        matrixGrille = createMatrice(sizeGrille, bitmapsOriginel);

        for (int x = 0; x < 150; x++) { // Nombre de tour de mélange à faire
            for (int i = 0; i < sizeGrille; i++) {
                for (int j = 0; j < sizeGrille; j++) {

                    if (matrixGrille[i][j] == emptyCase) { // La piece blanche est localisée dans la matrice

                        int tmpRand = (int) (Math.random() * 4) + 1;

                        switch (tmpRand) {
                            case 1: // cas haut
                                if (j > 0) {
                                    matrixGrille[i][j] = matrixGrille[i][j - 1];
                                    matrixGrille[i][j - 1] = emptyCase;
                                }
                                break;
                            case 2: // cas droit
                                if (i < (sizeGrille - 1)) {
                                    matrixGrille[i][j] = matrixGrille[i + 1][j];
                                    matrixGrille[i + 1][j] = emptyCase;
                                }
                                break;
                            case 3: // cas bas
                                if (j > (sizeGrille - 1)) {
                                    matrixGrille[i][j] = matrixGrille[i][j + 1];
                                    matrixGrille[i][j + 1] = emptyCase;
                                }
                                break;
                            case 4: // cas gauche
                                if (i > 0) {
                                    matrixGrille[i][j] = matrixGrille[i - 1][j];
                                    matrixGrille[i - 1][j] = emptyCase;
                                }
                                break;
                        }
                        randomGrid = matrixToArray(sizeGrille, matrixGrille);
                        matrixGrille = createMatrice(sizeGrille, randomGrid);

                    }


                }
            }
        }

        randomGrid = matrixToArray(sizeGrille, matrixGrille);

        return randomGrid;
    }

}
// Voilà c'est fini. Le cauchemard est terminé.
