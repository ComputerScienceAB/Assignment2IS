/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Javier
 */
public class MiniMaxSearch {
    
    final int posInf = Integer.MAX_VALUE;
    final int negInf = Integer.MIN_VALUE;
    
    int alpha, beta;
    int best;
    Piece mPiece;
    
    ArrayList<Action> actions;
    
    
    
    public MiniMaxSearch(){
        this.alpha = Integer.MIN_VALUE;
        this.beta = Integer.MAX_VALUE;
        
    }
    
    public Action Minimax(State state){
        best = MaxValue(state);
        for (int i = 0; i< actions.size(); i++){
            if (actions.get(i).value == best) return actions.get(i);
        }
        return null;
    }
    
    public int MaxValue(State state){
        actions = mPiece.getPossibleActions(state); 
        if (state.isFinal()) return state.getUtility();
        best = negInf;
        for (int i = 0; i < actions.size(); i++){
            best = Math.max(best, MinValue(state.applyAction(actions.get(i))));
        }
        return best;
    }
    
    public int MinValue(State state){
        actions = mPiece.getPossibleActions(state);
        if (state.isFinal()) return state.getUtility();
        best = posInf;
        for (int i = 0; i < actions.size(); i++){
            best = Math.min(best, MaxValue(state.applyAction(actions.get(i))));
        }
        return best;
    }
    
    
}
