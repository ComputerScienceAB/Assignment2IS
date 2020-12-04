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
public class Agent {

    //0 = White; 1 = Black
    int color;
    int remainingMoves;
    boolean dummy;

    public Agent(String c, int rM, boolean d) {        
        if (c.equals("white")) {
            this.color = 0;
        } else {
            this.color = 1;
        }
        this.remainingMoves = rM;
        this.dummy = d;
        
    }
    
    

}
