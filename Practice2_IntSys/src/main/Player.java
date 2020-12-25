/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Javier
 */
public class Player {
    
    //0 = White; 1 = Black
    int color;
    int score = 0;
    //Only used by Agents, we declare it here in order to be able to access it from ChessGame
    Algorithm searchEngine;
    
    public State Move(State st){
        return null;  //Never arrive here
    }
}
