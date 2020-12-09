/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;


public class Piece {
    
    public int color;
    int type;
    Position piecePosition;
    

    
    public ArrayList<Action> getPossibleActions(State state){
			
        return null; //never arrive here
    }
    
    public ArrayList<Action> getVerticalUpMoves(State state, boolean king){
        ArrayList<Action> list;
        int c0,r0;
        c0=this.piecePosition.col;
        r0=this.piecePosition.row;
        Action action = null;
        Boolean busyCell = false;
        
        
        if(king){
            list = new ArrayList<Action>(1);
            if(r0-1>=0){
                if(state.m_board[r0-1][c0] == Utils.empty){
                    action = new Action(this.piecePosition,new Position(r0-1,c0));
                    list.add(action);
                }else{
                    if(this.color != Utils.getColorPiece(state.m_board[r0-1][c0])){
                    action = new Action(this.piecePosition,new Position(r0-1,c0));
                    list.add(action);
                    }
                } 
            }           
        }else{
            list = new ArrayList<Action>(10);
            for(int r=r0-1; (r>=0) && !busyCell; r--){
                if(state.m_board[r][c0] == Utils.empty){
                    action = new Action(this.piecePosition,new Position(r,c0));
                    list.add(action);
                }else{
                    busyCell = true;
                    if(this.color != Utils.getColorPiece(state.m_board[r][c0])){
                        action = new Action(this.piecePosition,new Position(r,c0));
                        list.add(action);
                    }
                }            
            }
        }
  
        return list;
    }
    
    public ArrayList<Action> getVerticalDownMoves(State state, boolean king){
        ArrayList<Action> list;
        int c0,r0;
        c0=this.piecePosition.col;
        r0=this.piecePosition.row;
        Action action = null;
        Boolean busyCell = false;
        
        if(king){
            list = new ArrayList<Action>(1);
            if(r0+1<state.m_boardSize){
                if(state.m_board[r0+1][c0] == Utils.empty){
                    action = new Action(this.piecePosition,new Position(r0+1,c0));
                    list.add(action);
                }else{
                    if(this.color != Utils.getColorPiece(state.m_board[r0+1][c0])){
                        action = new Action(this.piecePosition,new Position(r0+1,c0));
                        list.add(action);
                    }
                }
            }      
        }else{
            list = new ArrayList<Action>(10);
            for(int r=r0+1; (r<state.m_boardSize) && !busyCell; r++){
                if(state.m_board[r][c0] == Utils.empty){
                    action = new Action(this.piecePosition,new Position(r,c0));
                    list.add(action);
                }else{
                    busyCell = true;
                    if(this.color != Utils.getColorPiece(state.m_board[r][c0])){
                        action = new Action(this.piecePosition,new Position(r,c0));
                        list.add(action);
                    }
                }            
            } 
        }

        return list;
    }
    
    public ArrayList<Action> getHorizontalLeftMoves(State state, boolean king){
        ArrayList<Action> list;
        int c0,r0;
        c0=this.piecePosition.col;
        r0=this.piecePosition.row;
        Action action = null;
        Boolean busyCell = false;
        
        if(king){
            list = new ArrayList<Action>();
            if(c0-1>=0){
               if(state.m_board[r0][c0-1] == Utils.empty){
                    action = new Action(this.piecePosition,new Position(r0,c0-1));
                    list.add(action);
                }else{
                    if(this.color != Utils.getColorPiece(state.m_board[r0][c0-1])){
                        action = new Action(this.piecePosition,new Position(r0,c0-1));
                        list.add(action);
                    }
                }  
            }            
        }else{
            list = new ArrayList<Action>();
            for(int c=c0-1; (c>=0) && !busyCell; c--){
                if(state.m_board[r0][c] == Utils.empty){
                    action = new Action(this.piecePosition,new Position(r0,c));
                    list.add(action);
                }else{
                    busyCell = true;
                    if(this.color != Utils.getColorPiece(state.m_board[r0][c])){
                        action = new Action(this.piecePosition,new Position(r0,c));
                        list.add(action);
                    }
                }            
            }
        }
        
        
        return list;
    }
    
    public ArrayList<Action> getHorizontalRightMoves(State state, boolean king){
        ArrayList<Action> list;
        int c0,r0;
        c0=this.piecePosition.col;
        r0=this.piecePosition.row;
        Action action = null;
        Boolean busyCell = false;
        
        if(king){
            list = new ArrayList<Action>();
            if(c0+1<state.m_boardSize){
                if(state.m_board[r0][c0+1] == Utils.empty){
                    action = new Action(this.piecePosition,new Position(r0,c0+1));
                    list.add(action);
                }else{
                    if(this.color != Utils.getColorPiece(state.m_board[r0][c0+1])){
                        action = new Action(this.piecePosition,new Position(r0,c0+1));
                        list.add(action);
                    }
                } 
            }            
        }else{
           list = new ArrayList<Action>();
            for(int c=c0+1; (c<state.m_boardSize) && !busyCell; c++){
                if(state.m_board[r0][c] == Utils.empty){
                    action = new Action(this.piecePosition,new Position(r0,c));
                    list.add(action);
                }else{
                    busyCell = true;
                    if(this.color != Utils.getColorPiece(state.m_board[r0][c])){
                        action = new Action(this.piecePosition,new Position(r0,c));
                        list.add(action);
                    }
                }            
            } 
        }
        
        
        return list;
    }
    
    public ArrayList<Action> getDiagonalUpLeftMoves(State state, boolean king){
        ArrayList<Action> list;
        int c0,r0;
        c0=this.piecePosition.col;
        r0=this.piecePosition.row;
        Action action = null;
        Boolean busyCell = false;
        
        if(king){
            list = new ArrayList<Action>();
            if((r0-1>=0) && (c0-1>=0)){
               if(state.m_board[r0-1][c0-1] == Utils.empty){
                    action = new Action(this.piecePosition,new Position(r0-1,c0-1));
                    list.add(action);
                }else{
                    if(this.color != Utils.getColorPiece(state.m_board[r0-1][c0-1])){
                        action = new Action(this.piecePosition,new Position(r0-1,c0-1));
                        list.add(action);
                    }
                } 
            }            
        }else{
            list = new ArrayList<Action>();
            for(int c=c0-1, r=r0-1; (c>=0) && (r>=0) && !busyCell; c--, r--){
                if(state.m_board[r][c] == Utils.empty){
                    action = new Action(this.piecePosition,new Position(r,c));
                    list.add(action);
                }else{
                    busyCell = true;
                    if(this.color != Utils.getColorPiece(state.m_board[r][c])){
                        action = new Action(this.piecePosition,new Position(r,c));
                        list.add(action);
                    }
                }            
            }
        }
        
        
        return list;
    }
    
    public ArrayList<Action> getDiagonalDownLeftMoves(State state, boolean king){
        ArrayList<Action> list;
        int c0,r0;
        c0=this.piecePosition.col;
        r0=this.piecePosition.row;
        Action action = null;
        Boolean busyCell = false;
        
        if(king){
            list = new ArrayList<Action>();
            if((r0+1<state.m_boardSize) && (c0-1>=0)){
                if(state.m_board[r0+1][c0-1] == Utils.empty){
                    action = new Action(this.piecePosition,new Position(r0+1,c0-1));
                    list.add(action);
                }else{
                    if(this.color != Utils.getColorPiece(state.m_board[r0+1][c0-1])){
                        action = new Action(this.piecePosition,new Position(r0+1,c0-1));
                        list.add(action);
                    }
                } 
            }            
        }else{
           list = new ArrayList<Action>();
            for(int c=c0-1, r=r0+1; (c>=0) && (r<state.m_boardSize) && !busyCell; c--, r++){
                if(state.m_board[r][c] == Utils.empty){
                    action = new Action(this.piecePosition,new Position(r,c));
                    list.add(action);
                }else{
                    busyCell = true;
                    if(this.color != Utils.getColorPiece(state.m_board[r][c])){
                        action = new Action(this.piecePosition,new Position(r,c));
                        list.add(action);
                    }
                }            
            } 
        }
        
        
        return list;
    }
    
    public ArrayList<Action> getDiagonalUpRightMoves(State state, boolean king){
        ArrayList<Action> list;
        int c0,r0;
        c0=this.piecePosition.col;
        r0=this.piecePosition.row;
        Action action = null;
        Boolean busyCell = false;
        
        if(king){
            list = new ArrayList<Action>();
            if((r0-1>=0) && (c0+1<state.m_boardSize)){
                if(state.m_board[r0-1][c0+1] == Utils.empty){
                    action = new Action(this.piecePosition,new Position(r0-1,c0+1));
                    list.add(action);
                }else{
                    if(this.color != Utils.getColorPiece(state.m_board[r0-1][c0+1])){
                        action = new Action(this.piecePosition,new Position(r0-1,c0+1));
                        list.add(action);
                    }
                } 
            }            
        }else{
           list = new ArrayList<Action>();
            for(int c=c0+1, r=r0-1; (c<state.m_boardSize) && (r>=0) && !busyCell; c++, r--){
                if(state.m_board[r][c] == Utils.empty){
                    action = new Action(this.piecePosition,new Position(r,c));
                    list.add(action);
                }else{
                    busyCell = true;
                    if(this.color != Utils.getColorPiece(state.m_board[r][c])){
                        action = new Action(this.piecePosition,new Position(r,c));
                        list.add(action);
                    }
                }            
            } 
        }
        
        
        return list;
    }
    
    public ArrayList<Action> getDiagonalDownRightMoves(State state, boolean king){
        ArrayList<Action> list;
        int c0,r0;
        c0=this.piecePosition.col;
        r0=this.piecePosition.row;
        Action action = null;
        Boolean busyCell = false;
        
        if(king){
            list = new ArrayList<Action>();
            if((r0+1<state.m_boardSize) && (c0+1<state.m_boardSize)){
                if(state.m_board[r0+1][c0+1] == Utils.empty){
                    action = new Action(this.piecePosition,new Position(r0+1,c0+1));
                    list.add(action);
                }else{
                    if(this.color != Utils.getColorPiece(state.m_board[r0+1][c0+1])){
                        action = new Action(this.piecePosition,new Position(r0+1,c0+1));
                        list.add(action);
                    }
                } 
            }           
        }else{
           list = new ArrayList<Action>();
            for(int c=c0+1, r=r0+1; (c<state.m_boardSize) && (r<state.m_boardSize) && !busyCell; c++, r++){
                if(state.m_board[r][c] == Utils.empty){
                    action = new Action(this.piecePosition,new Position(r,c));
                    list.add(action);
                }else{
                    busyCell = true;
                    if(this.color != Utils.getColorPiece(state.m_board[r][c])){
                        action = new Action(this.piecePosition,new Position(r,c));
                        list.add(action);
                    }
                }            
            } 
        }
        
        
        return list;
    }
    
    
}
