/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;

/**
 *
 * @author Javier
 */
public class Agent extends Player{

    boolean dummy;
    String searchStrategy;
    MiniMaxSearch mmSearchEngine;
    AlphaBetaSearch abSearchEngine;
    int searchDepth;
    
    

    public Agent(String c, boolean d, String ss, int depth) {        
        if (c.equals("white")) {
            this.color = 0;
        } else {
            this.color = 1;
        }
        this.dummy = d;
        this.searchStrategy = ss;
        this.searchDepth = depth;
        if(ss.equals("minimax")){
            this.mmSearchEngine = new MiniMaxSearch(this.color, this.searchDepth);
        }else{
            this.abSearchEngine = new AlphaBetaSearch(this.color, this.searchDepth);
        }
        
    }
    
    
    @Override
    public State Move(State st){
        State ret;
        if(this.searchStrategy.equals("minimax")){
            ret = st.applyAction(this.mmSearchEngine.Minimax(st));
            this.mmSearchEngine.searchDepth = this.searchDepth;
        }else{
            ret = st.applyAction(this.abSearchEngine.AlphaBeta(st));
            this.abSearchEngine.searchDepth = this.searchDepth;
        }
        
        return ret;
    }
    
    

}
