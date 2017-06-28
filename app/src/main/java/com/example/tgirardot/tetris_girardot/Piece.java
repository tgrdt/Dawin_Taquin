package com.example.tgirardot.tetris_girardot;

/**
 * Created by tgirardot on 09/06/17.
 */

public class Piece {

    protected int hauteur;
    protected int largeur;
    protected int[][] matrice_piece;
    protected int pos_i;
    protected int pos_j;
    protected int color;

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int[][] getMatrice_piece() {
        return matrice_piece;
    }

    public void setMatrice_piece(int[][] matrice_piece) {
        this.matrice_piece = matrice_piece;
    }

    public int getPos_i() {
        return pos_i;
    }

    public void setPos_i(int pos_i) {
        this.pos_i = pos_i;
    }

    public int getPos_j() {
        return pos_j;
    }

    public void setPos_j(int pos_j) {
        this.pos_j = pos_j;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}

