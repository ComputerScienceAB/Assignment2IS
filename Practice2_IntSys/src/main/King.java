/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class King extends Piece {

    public King(int color, Position pos) {
        this.color = color;
        if (this.color == 0) {
            this.type = Utils.wKing;
        } else {
            this.type = Utils.bKing;
        }
        this.piecePosition = pos;
    }

    public ArrayList<Action> getPossibleActions(State st) {
        ArrayList<Action> list = null;

        list = this.getDiagonalUpLeftMoves(st, true);
        list.addAll(this.getDiagonalUpRightMoves(st, true));
        list.addAll(this.getDiagonalDownLeftMoves(st, true));
        list.addAll(this.getDiagonalDownRightMoves(st, true));
        list.addAll(this.getHorizontalLeftMoves(st, true));
        list.addAll(this.getHorizontalRightMoves(st, true));
        list.addAll(this.getVerticalDownMoves(st, true));
        list.addAll(this.getVerticalUpMoves(st, true));

        return list;
    }

}
