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
public class Agent extends Player{

    int remainingMoves;
    boolean dummy;
    String searchStrategy;

    public Agent(String c, int rM, boolean d, String ss) {        
        if (c.equals("white")) {
            this.color = 0;
        } else {
            this.color = 1;
        }
        this.remainingMoves = rM;
        this.dummy = d;
        this.searchStrategy = ss;
    }
    
    @Override
    public State Move(State st){
        return null;
    }
    
    

}
