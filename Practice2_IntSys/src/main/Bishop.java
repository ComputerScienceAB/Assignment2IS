package main;

import main.Action;
import main.Piece;
import main.State;
import main.Utils;

import java.util.ArrayList;


public class Bishop extends Piece {
    
    public Bishop(int color){
        this.color = color;
        if(this.color == 0){
            this.type = Utils.wBishop;
        }else this.type = Utils.bBishop;
    }
	

    public ArrayList<Action> getPossibleActions(State st){
        ArrayList<Action> list = null;

        list = this.getDiagonalUpLeftMoves(st,false);
        list.addAll(this.getDiagonalUpRightMoves(st,false));
        list.addAll(this.getDiagonalDownLeftMoves(st,false));
        list.addAll(this.getDiagonalDownRightMoves(st,false));

        return list;
    }
}