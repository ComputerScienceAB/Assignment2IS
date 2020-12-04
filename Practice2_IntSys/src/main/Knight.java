
package main;

import main.*;

import java.util.ArrayList;

public class Knight extends Piece {
    
    public Knight(int color){
        this.color = color;
        if(this.color == 0){
            this.type = Utils.wKnight;
        }else this.type = Utils.bKnight;
    }
    
    @Override
    public ArrayList<Action> getPossibleActions(State st){
        
        int r0 = this.piecePosition.row;
        int c0 = this.piecePosition.col;

        ArrayList<Action> list = new ArrayList<Action>(8);
        Action action = null;
        Position origin = this.piecePosition;
        
        if((r0-2>=0) && (c0-1>=0)){
            if(st.m_board[r0-2][c0-1] == Utils.empty){
                action = new Action(origin, new Position(r0-2,c0-1));
                list.add(action);
            }else if(Utils.getColorPiece(st.m_board[r0-2][c0-1]) != this.color){
                action = new Action(origin, new Position(r0-2,c0-1));
                list.add(action);
            }
        }
        
        if((r0-1>=0) && (c0-2>=0)){
            if(st.m_board[r0-1][c0-2] == Utils.empty){
                action = new Action(origin, new Position(r0-1,c0-2));
                list.add(action);
            }else if(Utils.getColorPiece(st.m_board[r0-1][c0-2]) != this.color){
                action = new Action(origin, new Position(r0-1,c0-2));
                list.add(action);
            }
        }
        
        if((r0+1<st.m_boardSize) && (c0-2>=0)){
            if(st.m_board[r0+1][c0-2] == Utils.empty){
                action = new Action(origin, new Position(r0+1,c0-2));
                list.add(action);
            }else if(Utils.getColorPiece(st.m_board[r0+1][c0-2]) != this.color){
                action = new Action(origin, new Position(r0+1,c0-2));
                list.add(action);
            }
        }
        
        if((r0+2<st.m_boardSize) && (c0-1>=0)){
            if(st.m_board[r0+2][c0-1] == Utils.empty){
                action = new Action(origin, new Position(r0+2,c0-1));
                list.add(action);
            }else if(Utils.getColorPiece(st.m_board[r0+2][c0-1]) != this.color){
                action = new Action(origin, new Position(r0+2,c0-1));
                list.add(action);
            }
        }
        
        if((r0+2<st.m_boardSize) && (c0+1<st.m_boardSize)){
            if(st.m_board[r0+2][c0+1] == Utils.empty){
                action = new Action(origin, new Position(r0+2,c0+1));
                list.add(action);
            }else if(Utils.getColorPiece(st.m_board[r0+2][c0+1]) != this.color){
                action = new Action(origin, new Position(r0+2,c0+1));
                list.add(action);
            }
        }
        
        if((r0+1<st.m_boardSize) && (c0+2<st.m_boardSize)){
            if(st.m_board[r0+1][c0+2] == Utils.empty){
                action = new Action(origin, new Position(r0+1,c0+2));
                list.add(action);
            }else if(Utils.getColorPiece(st.m_board[r0+1][c0+2]) != this.color){
                action = new Action(origin, new Position(r0+1,c0+2));
                list.add(action);
            }
        }
        
        if((r0-1>=0) && (c0+2<st.m_boardSize)){
            if(st.m_board[r0-1][c0+2] == Utils.empty){
                action = new Action(origin, new Position(r0-1,c0+2));
                list.add(action);
            }else if(Utils.getColorPiece(st.m_board[r0-1][c0+2]) != this.color){
                action = new Action(origin, new Position(r0-1,c0+2));
                list.add(action);
            }
        }
        
        if((r0-2>=0) && (c0+1<st.m_boardSize)){
            if(st.m_board[r0-2][c0+1] == Utils.empty){
                action = new Action(origin, new Position(r0-2,c0+1));
                list.add(action);
            }else if(Utils.getColorPiece(st.m_board[r0-2][c0+1]) != this.color){
                action = new Action(origin, new Position(r0-2,c0+1));
                list.add(action);
            }
        }
        
        return list;
                
    }
    
}
