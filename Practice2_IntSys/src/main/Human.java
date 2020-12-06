/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Scanner;

/**
 *
 * @author Javier
 */
public class Human extends Player {

    Scanner keyboard = new Scanner(System.in).useDelimiter("[,\\s+]");
    State ret;
    Action action;
    boolean invalidMovement;

    public Human(String c) {
        if (c.equals("white")) {
            this.color = 0;
        } else {
            this.color = 1;
        }
    }

    @Override
    public State Move(State st) {
        String input;
        String[] buffer;
        this.ret = null;
        this.action = new Action();
        this.invalidMovement = true;
        while (this.invalidMovement) {
            System.out.println("Enter the position of the piece you want to move:");
            input = keyboard.nextLine();
            buffer = input.split(",");
            this.action.m_initPos.row = Integer.parseInt(buffer[0]);
            this.action.m_initPos.col = Integer.parseInt(buffer[1]);
            System.out.println("Enter the destination position for the chosen piece:");
            input = keyboard.nextLine();
            buffer = input.split(",");
            this.action.m_finalPos.row = Integer.parseInt(buffer[0]);
            this.action.m_finalPos.col = Integer.parseInt(buffer[1]);
            //Check that the human's move is valid
            //if (this.action.isValid(st.m_boardSize)) {     //Check first that the positions of the actions aren't out-of-bounds
                if (this.color == 1) {
                    //Check that the chosen piece corresponds to the color of the player
                    if ((st.m_board[this.action.m_initPos.row][this.action.m_initPos.col] >= Utils.bPawn) && (st.m_board[this.action.m_initPos.row][this.action.m_initPos.col] <= Utils.bKing)) {
                        //Check that the destination position is empty or occupied by an enemy piece
                        if(this.action.isLegal(st, st.m_board[this.action.m_initPos.row][this.action.m_initPos.col])){
                            this.invalidMovement = false;
                            ret = st.applyAction(this.action);
                        }else{
                            System.out.println("Ilegal action");
                        }
                        /*if (((st.m_board[this.action.m_finalPos.row][this.action.m_finalPos.col] >= Utils.wPawn) && (st.m_board[this.action.m_finalPos.row][this.action.m_finalPos.col] <= Utils.wKing)) || (st.m_board[this.action.m_finalPos.row][this.action.m_finalPos.col] == Utils.empty)) {
                            
                        } else {
                            System.out.println("Invalid action: you can't eat your own pieces");
                        }*/
                    } else {
                        System.out.println("Invalid action: pick a black piece");
                    }
                } else {
                    if ((st.m_board[this.action.m_initPos.row][this.action.m_initPos.col] >= Utils.wPawn) && (st.m_board[this.action.m_initPos.row][this.action.m_initPos.col] <= Utils.wKing)) {
                        if(this.action.isLegal(st, st.m_board[this.action.m_initPos.row][this.action.m_initPos.col])){
                            this.invalidMovement = false;
                            ret = st.applyAction(this.action);
                        }else{
                            System.out.println("Ilegal action");
                        }
                        /*if ((st.m_board[this.action.m_finalPos.row][this.action.m_finalPos.col] >= Utils.bPawn) && (st.m_board[this.action.m_finalPos.row][this.action.m_finalPos.col] <= Utils.empty)) {
                            this.invalidMovement = false;
                            ret = st.applyAction(this.action);
                        } else {
                            System.out.println("Invalid action: you can't eat your own pieces");
                        }*/
                    } else {
                        System.out.println("Invalid action: pick a white piece");
                    }
                }
            /*} else {
                System.out.println("Invalid action: out-of-bounds position/s provided");
            }*/
        }

        return this.ret;

    }
}
