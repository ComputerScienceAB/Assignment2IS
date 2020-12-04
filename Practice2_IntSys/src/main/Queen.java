package main;

import main.Action;
import main.Piece;
import main.State;
import main.Utils;

import java.util.ArrayList;

/**
 *
 * @author javis
 */
public class Queen extends Piece {
    
    public Queen(int color){
        this.color = color;
        if(this.color == 0){
            this.type = Utils.wQueen;
        }else this.type = Utils.bQueen;
    }
	

    public ArrayList<Action> getPossibleActions(State st){
        ArrayList<Action> list = null;

        list = this.getDiagonalUpLeftMoves(st,false);
        list.addAll(this.getDiagonalUpRightMoves(st,false));
        list.addAll(this.getDiagonalDownLeftMoves(st,false));
        list.addAll(this.getDiagonalDownRightMoves(st,false));
        list.addAll(this.getHorizontalLeftMoves(st,false));
        list.addAll(this.getHorizontalRightMoves(st,false));
        list.addAll(this.getVerticalDownMoves(st,false));
        list.addAll(this.getVerticalUpMoves(st,false));

        return list;
    }
}