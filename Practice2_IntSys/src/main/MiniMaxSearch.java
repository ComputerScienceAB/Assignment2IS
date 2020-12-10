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
public class MiniMaxSearch {

    final int posInf = Integer.MAX_VALUE;
    final int negInf = Integer.MIN_VALUE;

    int best;
    int agentColor;
    Piece mPiece;
    int searchDepth;

    public MiniMaxSearch(int c, int d) {
        this.agentColor = c;
        this.searchDepth = d;
    }

    public Action Minimax(State state) {
        ArrayList<Action> piecePossibleActions = new ArrayList<>();
        ArrayList<Action> totalPossibleActions = new ArrayList<>();
        best = MaxValue(state);
        for (int i = 0; i < state.m_boardSize; i++) {
            for (int j = 0; j < state.m_boardSize; j++) {
                if (this.agentColor == 0) {
                    if ((state.m_board[i][j] >= Utils.wPawn) && (state.m_board[i][j] <= Utils.wKing)) {
                        this.mPiece = Utils.getPiece(state.m_board[i][j], new Position(i, j));
                        piecePossibleActions = this.mPiece.getPossibleActions(state);
                        if (piecePossibleActions != null) {
                            totalPossibleActions.addAll(this.mPiece.getPossibleActions(state));
                        }
                    }
                } else {
                    if ((state.m_board[i][j] >= Utils.bPawn) && (state.m_board[i][j] <= Utils.bKing)) {
                        this.mPiece = Utils.getPiece(state.m_board[i][j], new Position(i, j));
                        piecePossibleActions = this.mPiece.getPossibleActions(state);
                        if (piecePossibleActions != null) {
                            totalPossibleActions.addAll(this.mPiece.getPossibleActions(state));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < totalPossibleActions.size(); i++) {

            if (totalPossibleActions.get(i).value == best) {
                return totalPossibleActions.get(i);
            }
        }
        return null;
    }

    public int MaxValue(State state) {
        ArrayList<Action> piecePossibleActions = new ArrayList<>();
        ArrayList<Action> totalPossibleActions = new ArrayList<>();       
        if (state.isFinal() || this.searchDepth == 0) {
            return getUtility(state);
        }
        this.searchDepth--;
        for (int i = 0; i < state.m_boardSize; i++) {
            for (int j = 0; j < state.m_boardSize; j++) {
                if (this.agentColor == 0) {
                    if ((state.m_board[i][j] >= Utils.wPawn) && (state.m_board[i][j] <= Utils.wKing)) {
                        this.mPiece = Utils.getPiece(state.m_board[i][j], new Position(i, j));
                        piecePossibleActions = this.mPiece.getPossibleActions(state);
                        if (piecePossibleActions != null) {
                            totalPossibleActions.addAll(this.mPiece.getPossibleActions(state));
                        }
                    }
                } else {
                    if ((state.m_board[i][j] >= Utils.bPawn) && (state.m_board[i][j] <= Utils.bKing)) {
                        this.mPiece = Utils.getPiece(state.m_board[i][j], new Position(i, j));
                        piecePossibleActions = this.mPiece.getPossibleActions(state);
                        if (piecePossibleActions != null) {
                            totalPossibleActions.addAll(this.mPiece.getPossibleActions(state));
                        }
                    }
                }
            }
        }

        best = negInf;
        for (int i = 0; i < totalPossibleActions.size(); i++) {
            best = Math.max(best, MinValue(state.applyAction(totalPossibleActions.get(i))));
        }
        
        return best;
    }

    public int MinValue(State state) {
        ArrayList<Action> piecePossibleActions = new ArrayList<>();
        ArrayList<Action> totalPossibleActions = new ArrayList<>();        
        if (state.isFinal() || this.searchDepth == 0) {
            return getUtility(state);
        }
        this.searchDepth--;
        for (int i = 0; i < state.m_boardSize; i++) {
            for (int j = 0; j < state.m_boardSize; j++) {
                if (this.agentColor == 0) {
                    if ((state.m_board[i][j] >= Utils.bPawn) && (state.m_board[i][j] <= Utils.bKing)) {
                        this.mPiece = Utils.getPiece(state.m_board[i][j], new Position(i, j));
                        piecePossibleActions = this.mPiece.getPossibleActions(state);
                        if (piecePossibleActions != null) {
                            totalPossibleActions.addAll(this.mPiece.getPossibleActions(state));
                        }
                    }
                } else {
                    if ((state.m_board[i][j] >= Utils.wPawn) && (state.m_board[i][j] <= Utils.wKing)) {
                        this.mPiece = Utils.getPiece(state.m_board[i][j], new Position(i, j));
                        piecePossibleActions = this.mPiece.getPossibleActions(state);
                        if (piecePossibleActions != null) {
                            totalPossibleActions.addAll(this.mPiece.getPossibleActions(state));
                        }
                    }
                }
            }
        }

        best = posInf;
        for (int i = 0; i < totalPossibleActions.size(); i++) {
            best = Math.min(best, MaxValue(state.applyAction(totalPossibleActions.get(i))));
        }
        
        return best;
    }


    public int getUtility(State state) {
        int whitescore = 0;
        int blackscore = 0;
        for (int i = 0; i < state.m_boardSize; i++) {
            for (int j = 0; j < state.m_boardSize; j++) {
                if (state.m_board[i][j] != Utils.empty) {
                    if (state.m_board[i][j] >= Utils.wPawn && state.m_board[i][j] <= Utils.wKing) { //if the piece in that position is white
                        if (state.m_board[i][j] == Utils.wPawn) {
                            whitescore += 100;
                        }
                        if (state.m_board[i][j] == Utils.wRook) {
                            whitescore += 500;
                        }
                        if (state.m_board[i][j] == Utils.wBishop) {
                            whitescore += 330;
                        }
                        if (state.m_board[i][j] == Utils.wKnight) {
                            whitescore += 320;
                        }
                        if (state.m_board[i][j] == Utils.wQueen) {
                            whitescore += 900;
                        }
                        if (state.m_board[i][j] == Utils.wKing) {
                            whitescore += 20000;
                        }
                    } else { //if piece is black
                        if (state.m_board[i][j] == Utils.bPawn) {
                            blackscore += 100;
                        }
                        if (state.m_board[i][j] == Utils.bRook) {
                            blackscore += 500;
                        }
                        if (state.m_board[i][j] == Utils.bBishop) {
                            blackscore += 330;
                        }
                        if (state.m_board[i][j] == Utils.bKnight) {
                            blackscore += 320;
                        }
                        if (state.m_board[i][j] == Utils.bQueen) {
                            blackscore += 900;
                        }
                        if (state.m_board[i][j] == Utils.bKing) {
                            blackscore += 20000;
                        }
                    }
                }
            }
        }
        return blackscore - whitescore;
    }

}
