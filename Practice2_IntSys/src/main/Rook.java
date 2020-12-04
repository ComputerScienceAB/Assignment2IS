package main;

import main.Action;
import main.Piece;
import main.State;
import main.Utils;

import java.util.ArrayList;


public class Rook extends Piece {
    
    public Rook(int color){
        this.color = color;
        if(this.color == 0){
            this.type = Utils.wRook;
        }else this.type = Utils.bRook;
    }
	
    public ArrayList<Action> getPossibleActions(State st){
        ArrayList<Action> list = null;

        list = this.getHorizontalLeftMoves(st,false);
        list.addAll(this.getHorizontalRightMoves(st,false));
        list.addAll(this.getVerticalDownMoves(st,false));
        list.addAll(this.getVerticalUpMoves(st,false));

        return list;
    }
}