package main;

// This class contains the information needed to represent a state 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

// and some useful methods
public class State {

    int[][] m_board = null;
    //Position[] whitePositions = null;
    //Position[] blackPositions = null;
    int m_boardSize = 8;

    // constructor
    public State(int[][] board) {
        this.m_board = board;
    }

    // compares if the current state is final, i.e. the agent is in the last row
    /*public boolean isFinal(){
            if (this.m_agentPos.row == this.m_boardSize-1) return true;
            else return false;
    }*/
    
    // hard copy of an State
    public State copy() {
        int[][] cBoard = new int[this.m_boardSize][this.m_boardSize];

        for (int r = 0; r < this.m_boardSize; r++) {
            for (int c = 0; c < this.m_boardSize; c++) {
                cBoard[r][c] = this.m_board[r][c];
            }
        }

        return new State(cBoard);
    }

    // apply a given action over the current state -which remains unmodified. Return a new state
    public State applyAction(Action action) {
        int aux;
        State newState = this.copy();
        aux = newState.m_board[action.m_initPos.row][action.m_initPos.col];
        newState.m_board[action.m_initPos.row][action.m_initPos.col] = Utils.empty;
        newState.m_board[action.m_finalPos.row][action.m_finalPos.col] = aux;

        return newState;
    }
    
    public boolean isFinal() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    

    /*@Override
    public boolean equals(Object obj){
        State st = (State) obj;
        boolean eq=false;

        if(st.m_agentPos.col == this.m_agentPos.col){
            if(st.m_agentPos.row == this.m_agentPos.row){
                eq=true;
            }
        }

        return eq;
    }

    @Override
    public int hashCode() {
        return this.m_agentPos.col+this.m_agentPos.row;
    }*/

    
}
