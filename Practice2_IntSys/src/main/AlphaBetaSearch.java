/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class AlphaBetaSearch extends MiniMaxSearch {
    
 
    
    public AlphaBetaSearch(){
        super();
    }
    
    public Action AlphaBeta(State state){
        best = MaxValue(state, posInf, negInf);
        for (int i = 0; i< actions.size(); i++){
            if (actions.get(i).value == best) return actions.get(i);
        }
        return null; 
    } 
    
    public int MaxValue(State state, int alpha, int beta){
        this.alpha = alpha;
        this.beta = beta;
        actions = mPiece.getPossibleActions(state);
        if (state.isFinal()) return state.getUtility();
        best = negInf;
        for (int i = 0; i < actions.size(); i++){
            best = Math.max(Math.max(best, MinValue(state.applyAction(actions.get(i)))), Math.max(alpha, beta));
            if (best >= beta) return best;
            alpha = Math.max(alpha, best);
        }   
        return best;
    }
    
    public int MinValue(State state, int alpha, int beta){
        this.alpha = alpha;
        this.beta = beta;
        actions = mPiece.getPossibleActions(state);
        if (state.isFinal()) return state.getUtility();
        best = posInf;
        for (int i = 0; i < actions.size(); i++){
            best = Math.min(Math.min(best, MinValue(state.applyAction(actions.get(i)))), Math.min(alpha, beta));
            if (best <= beta) return best;
            alpha = Math.min(alpha, best);
        }   
        return best;
    }
    
    
    
    
}
