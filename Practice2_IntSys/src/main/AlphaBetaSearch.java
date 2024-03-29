/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Usuario
 */
public class AlphaBetaSearch extends Algorithm {
    
   
    public AlphaBetaSearch(int c, int d){
        super(c,d);
    }
    
    @Override
    public Action AlphaBeta(State state){
        int i, rnd, v, buffer = 0;
        ArrayList<Action> piecePossibleActions = new ArrayList<Action>();
        ArrayList<Action> totalPossibleActions = new ArrayList<Action>();
        ArrayList<Action> bestPossibleActions = new ArrayList<Action>();        
        int alpha = this.negInf;
        int beta = this.posInf;
        for(i=0; i<state.m_boardSize;i++){
            for(int j=0;j<state.m_boardSize;j++){
                if(this.agentColor == 0){
                    if((state.m_board[i][j] >= Utils.wPawn) && (state.m_board[i][j] <= Utils.wKing)){
                        this.mPiece = Utils.getPiece(state.m_board[i][j], new Position(i,j));
                        piecePossibleActions = this.mPiece.getPossibleActions(state);
                        if(piecePossibleActions != null){
                           totalPossibleActions.addAll(this.mPiece.getPossibleActions(state));
                        }                      
                    }
                }else{
                    if((state.m_board[i][j] >= Utils.bPawn) && (state.m_board[i][j] <= Utils.bKing)){
                        this.mPiece = Utils.getPiece(state.m_board[i][j], new Position(i,j));
                        piecePossibleActions = this.mPiece.getPossibleActions(state);
                        if(piecePossibleActions != null){
                           totalPossibleActions.addAll(this.mPiece.getPossibleActions(state)); 
                        } 
                    }
                }           
            }
        }
      
        for (i = 0; i < totalPossibleActions.size(); i++) {
            this.generatedStates++;
            v = MinValue(state.applyAction(totalPossibleActions.get(i)), alpha, beta, this.searchDepth - 1);
            totalPossibleActions.get(i).value = v;
            alpha = Math.max(alpha, v);
        }
        
        v = this.negInf;
        for(i = 0; i < totalPossibleActions.size(); i++){
            buffer = totalPossibleActions.get(i).value;
            if (buffer >= v) {
                v = buffer;
                bestPossibleActions.add(totalPossibleActions.get(i));
            }
        }

        while (bestPossibleActions.get(0).value < v) {
            bestPossibleActions.remove(0);
        }

        if (!bestPossibleActions.isEmpty()) {
            rnd = new Random().nextInt(bestPossibleActions.size());
            return bestPossibleActions.get(rnd);
        }
        
        return null;       
    } 

    

    public int MaxValue(State state, int A, int B, int depthLimit){
        int best;
        ArrayList<Action> piecePossibleActions = new ArrayList<Action>();
        ArrayList<Action> totalPossibleActions = new ArrayList<Action>();
        int alpha = A;
        int beta = B;
        if (state.isFinal() || depthLimit == 0) return Utils.getUtility(state,this.agentColor);
        for(int i=0; i<state.m_boardSize;i++){
            for(int j=0;j<state.m_boardSize;j++){
                if(this.agentColor == 0){
                    if((state.m_board[i][j] >= Utils.wPawn) && (state.m_board[i][j] <= Utils.wKing)){
                        this.mPiece = Utils.getPiece(state.m_board[i][j], new Position(i,j));
                        piecePossibleActions = this.mPiece.getPossibleActions(state);
                        if(piecePossibleActions != null){
                           totalPossibleActions.addAll(this.mPiece.getPossibleActions(state)); 
                        }                      
                    }
                }else{
                    if((state.m_board[i][j] >= Utils.bPawn) && (state.m_board[i][j] <= Utils.bKing)){
                        this.mPiece = Utils.getPiece(state.m_board[i][j], new Position(i,j));
                        piecePossibleActions = this.mPiece.getPossibleActions(state);
                        if(piecePossibleActions != null){
                           totalPossibleActions.addAll(this.mPiece.getPossibleActions(state)); 
                        }                      
                    }
                }           
            }
        }        
        best = negInf;
        for (int i = 0; i < totalPossibleActions.size(); i++){
            this.generatedStates++;
            best = Math.max(best, MinValue(state.applyAction(totalPossibleActions.get(i)), alpha, beta, (depthLimit -1)));
            if (best >= beta) return best;
            alpha = Math.max(alpha, best);
        }   
        return best;
    }
    
    public int MinValue(State state, int A, int B, int depthLimit){
        int best;
        ArrayList<Action> piecePossibleActions = new ArrayList<Action>();
        ArrayList<Action> totalPossibleActions = new ArrayList<Action>();
        int alpha = A;
        int beta = B;
        if (state.isFinal() || depthLimit == 0) return Utils.getUtility(state,this.agentColor);
        for(int i=0; i<state.m_boardSize;i++){
            for(int j=0;j<state.m_boardSize;j++){
                if(this.agentColor == 0){
                    if((state.m_board[i][j] >= Utils.bPawn) && (state.m_board[i][j] <= Utils.bKing)){
                        this.mPiece = Utils.getPiece(state.m_board[i][j], new Position(i,j));
                        piecePossibleActions = this.mPiece.getPossibleActions(state);
                        if(piecePossibleActions != null){
                           totalPossibleActions.addAll(this.mPiece.getPossibleActions(state)); 
                        } 
                    }
                }else{
                    if((state.m_board[i][j] >= Utils.wPawn) && (state.m_board[i][j] <= Utils.wKing)){
                        this.mPiece = Utils.getPiece(state.m_board[i][j], new Position(i,j));
                        piecePossibleActions = this.mPiece.getPossibleActions(state);
                        if(piecePossibleActions != null){
                           totalPossibleActions.addAll(this.mPiece.getPossibleActions(state)); 
                        } 
                    }
                }           
            }
        }        
        best = posInf;
        for (int i = 0; i < totalPossibleActions.size(); i++){
            this.generatedStates++;
            best = Math.min(best, MaxValue(state.applyAction(totalPossibleActions.get(i)), alpha, beta, (depthLimit -1)));
            if (best <= alpha) return best;
            beta = Math.min(beta, best);
        }
        return best;
    }
     
}
