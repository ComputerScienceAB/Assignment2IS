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
 * @author Javier
 */
public class MiniMaxSearch extends Algorithm{

    
    public MiniMaxSearch(int c, int d) {
        super(c,d);
    }

    @Override
    public Action Minimax(State state) {
        int i, rnd, v, buffer = 0;
        ArrayList<Action> piecePossibleActions = new ArrayList<>();
        ArrayList<Action> totalPossibleActions = new ArrayList<>();
        ArrayList<Action> bestPossibleActions = new ArrayList<>();
        v = negInf;
        for (i = 0; i < state.m_boardSize; i++) {
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

        for (i = 0; i < totalPossibleActions.size(); i++) {
            this.generatedStates++;
            totalPossibleActions.get(i).value = MinValue(state.applyAction(totalPossibleActions.get(i)), this.searchDepth - 1);
            
        }

        v = this.negInf;
        for (i = 0; i < totalPossibleActions.size(); i++) {
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

    public int MaxValue(State state, int depthLimit) {
        int best;
        ArrayList<Action> piecePossibleActions = new ArrayList<>();
        ArrayList<Action> totalPossibleActions = new ArrayList<>();
        if (state.isFinal() || depthLimit == 0) {
            return Utils.getUtility(state, this.agentColor);
        }
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
            this.generatedStates++;
            best = Math.max(best, MinValue(state.applyAction(totalPossibleActions.get(i)), (depthLimit - 1)));
        }

        return best;
    }

    public int MinValue(State state, int depthLimit) {
        int best;
        ArrayList<Action> piecePossibleActions = new ArrayList<>();
        ArrayList<Action> totalPossibleActions = new ArrayList<>();
        if (state.isFinal() || depthLimit == 0) {
            return Utils.getUtility(state, this.agentColor);
        }
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
            this.generatedStates++;
            best = Math.min(best, MaxValue(state.applyAction(totalPossibleActions.get(i)), (depthLimit - 1)));
        }

        return best;
    }

}
