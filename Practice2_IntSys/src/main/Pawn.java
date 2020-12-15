/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;

/**
 *
 * @author javis
 */
public class Pawn extends Piece {

    public Pawn(int color, Position pos) {
        this.color = color;
        if (this.color == 0) {
            this.type = Utils.wPawn;
        } else {
            this.type = Utils.bPawn;
        }
        this.piecePosition = pos;
    }

    public ArrayList<Action> getPossibleActions(State st) {

        int r_agent = this.piecePosition.row;
        int c_agent = this.piecePosition.col;

        ArrayList<Action> list = new ArrayList<Action>();
        Action action = null;
        Position origin = this.piecePosition;

        if (this.color == 0) {
            if (r_agent == 1) {
                if (st.m_board[r_agent + 2][c_agent] == Utils.empty && st.m_board[r_agent + 1][c_agent] == Utils.empty) {
                    action = new Action(origin, new Position(r_agent + 2, c_agent));
                    list.add(action);
                }
            }

            if (((r_agent + 1) < st.m_boardSize) && (st.m_board[r_agent + 1][c_agent] == Utils.empty)) {
                action = new Action(origin, new Position(r_agent + 1, c_agent));
                list.add(action);
            }
            if (((r_agent + 1) < st.m_boardSize) && ((c_agent - 1) >= 0) && (st.m_board[r_agent + 1][c_agent - 1] != Utils.empty) && (Utils.getColorPiece(st.m_board[r_agent + 1][c_agent - 1]) != this.color)) {
                action = new Action(origin, new Position(r_agent + 1, c_agent - 1));
                list.add(action);
            }
            if (((r_agent + 1) < st.m_boardSize) && (c_agent + 1 < st.m_boardSize) && (c_agent + 1 < st.m_boardSize) && (st.m_board[r_agent + 1][c_agent + 1] != Utils.empty) && (Utils.getColorPiece(st.m_board[r_agent + 1][c_agent + 1]) != this.color)) {
                action = new Action(origin, new Position(r_agent + 1, c_agent + 1));
                list.add(action);
            }
        } else {
            if (r_agent == 6) {
                if (st.m_board[r_agent - 2][c_agent] == Utils.empty && st.m_board[r_agent - 1][c_agent] == Utils.empty) {
                    action = new Action(origin, new Position(r_agent - 2, c_agent));
                    list.add(action);
                }
            }

            if (((r_agent - 1) < st.m_boardSize) && (st.m_board[r_agent - 1][c_agent] == Utils.empty)) {
                action = new Action(origin, new Position(r_agent - 1, c_agent));
                list.add(action);
            }
            if ((c_agent - 1 >= 0) && (r_agent-1 >= 0) && (st.m_board[r_agent - 1][c_agent - 1] != Utils.empty) && (Utils.getColorPiece(st.m_board[r_agent - 1][c_agent - 1]) != this.color)) {
                action = new Action(origin, new Position(r_agent - 1, c_agent - 1));
                list.add(action);
            }
            if ((c_agent + 1 < st.m_boardSize) && (r_agent-1 >= 0) && (st.m_board[r_agent - 1][c_agent + 1] != Utils.empty) && (Utils.getColorPiece(st.m_board[r_agent - 1][c_agent + 1]) != this.color)) {
                action = new Action(origin, new Position(r_agent - 1, c_agent + 1));
                list.add(action);
            }
        }

        //System.out.println(list.size());
        return list;

    }
}
