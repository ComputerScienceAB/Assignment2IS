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
    

    int best;
    int agentColor;
    Piece mPiece;
    
    ArrayList<Action> totalPossibleActions = new ArrayList<>();
    
    
    
    public MiniMaxSearch(int c){
        this.agentColor = c;
    }
    
    public Action Minimax(State state){
        best = MaxValue(state);
        for (int i = 0; i< totalPossibleActions.size(); i++){
            if (totalPossibleActions.get(i).value == best) return totalPossibleActions.get(i);
        }
        return null;
    }
    
    public int MaxValue(State state){
        for(int i=0; i<state.m_boardSize;i++){
            for(int j=0;j<state.m_boardSize;j++){
                if(this.agentColor == 0){
                    if((state.m_board[i][j] >= Utils.wPawn) && (state.m_board[i][j] <= Utils.wKing)){
                        this.mPiece = Utils.getPiece(state.m_board[i][j], new Position(i,j));
                        this.totalPossibleActions.addAll(this.mPiece.getPossibleActions(state));
                    }
                }else{
                    if((state.m_board[i][j] >= Utils.bPawn) && (state.m_board[i][j] <= Utils.bKing)){
                        this.mPiece = Utils.getPiece(state.m_board[i][j], new Position(i,j));
                        this.totalPossibleActions.addAll(this.mPiece.getPossibleActions(state));
                    }
                }           
            }
        }
        if (state.isFinal()) return state.getUtility();
        best = negInf;
        for (int i = 0; i < totalPossibleActions.size(); i++){
            best = Math.max(best, MinValue(state.applyAction(totalPossibleActions.get(i))));
        }
        return best;
    }
    
    public int MinValue(State state){
        for(int i=0; i<state.m_boardSize;i++){
            for(int j=0;j<state.m_boardSize;j++){
                if(this.agentColor == 0){
                    if((state.m_board[i][j] >= Utils.bPawn) && (state.m_board[i][j] <= Utils.bKing)){
                        this.mPiece = Utils.getPiece(state.m_board[i][j], new Position(i,j));
                        this.totalPossibleActions.addAll(this.mPiece.getPossibleActions(state));
                    }
                }else{
                    if((state.m_board[i][j] >= Utils.wPawn) && (state.m_board[i][j] <= Utils.wKing)){
                        this.mPiece = Utils.getPiece(state.m_board[i][j], new Position(i,j));
                        this.totalPossibleActions.addAll(this.mPiece.getPossibleActions(state));
                    }
                }           
            }
        }
        if (state.isFinal()) return state.getUtility();
        best = posInf;
        for (int i = 0; i < totalPossibleActions.size(); i++){
            best = Math.min(best, MaxValue(state.applyAction(totalPossibleActions.get(i))));
        }
        return best;
    }
    
    
}
