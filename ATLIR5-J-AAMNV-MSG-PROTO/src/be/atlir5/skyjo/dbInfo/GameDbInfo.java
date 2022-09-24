/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.atlir5.skyjo.dbInfo;

import java.io.Serializable;

/**
 *
 * @author justu
 */
public class GameDbInfo implements Serializable{
     private int idGame;
    private int idUser;
    private int score;

    public GameDbInfo(int idGame, int idUser, int score) {
        this.idGame = idGame;
        this.idUser = idUser;
        this.score = score;
    }

    public int getIdGame() {
        return idGame;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getScore() {
        return score;
    }
    
    
}
