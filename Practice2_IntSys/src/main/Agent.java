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


    String searchStrategy;    
    int searchDepth;
    
    

    public Agent(String c, String ss, int depth) {        
        if (c.equals("white")) {
            this.color = 0;
        } else {
            this.color = 1;
        }
        this.searchStrategy = ss;
        this.searchDepth = depth;
        if(ss.equals("minimax")){
            this.searchEngine = new MiniMaxSearch(this.color, this.searchDepth);
        }else{
            this.searchEngine = new AlphaBetaSearch(this.color, this.searchDepth);
        }
        
    }
    
    
    @Override
    public State Move(State st){
        State ret;
        Action action;
        if(this.searchStrategy.equals("minimax")){
            action = this.searchEngine.Minimax(st);
            ret = st.applyAction(action);            
        }else{
            action = this.searchEngine.AlphaBeta(st);
            ret = st.applyAction(action);
        }

        return ret;
    }
    
    

}
