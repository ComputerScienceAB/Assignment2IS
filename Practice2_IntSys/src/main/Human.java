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

    //Scanner keyboard = new Scanner(System.in).useDelimiter("[,\\s+]");
    Scanner keyboard = new Scanner(System.in);
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
        //String[] buffer;
        this.ret = null;
        this.action = new Action();
        this.invalidMovement = true;
        
        while (this.invalidMovement) {
            System.out.println("Enter the position of the piece you want to move:");
            input = keyboard.nextLine();
            //buffer = input.split(",");
            this.action.m_initPos.row = Character.getNumericValue(input.charAt(0));
            this.action.m_initPos.col = Character.getNumericValue(input.charAt(2));
            //this.action.m_initPos.row = Integer.parseInt(buffer[0]);
            //this.action.m_initPos.col = Integer.parseInt(buffer[1]);
            System.out.println("Enter the destination position for the chosen piece:");
            input = keyboard.nextLine();
            //buffer = input.split(",");
            this.action.m_finalPos.row = Character.getNumericValue(input.charAt(0));
            this.action.m_finalPos.col = Character.getNumericValue(input.charAt(2));
            //this.action.m_finalPos.row = Integer.parseInt(buffer[0]);
            //this.action.m_finalPos.col = Integer.parseInt(buffer[1]);

            if (this.color == 1) {
                //Check that the chosen piece corresponds to the color of the player
                if ((st.m_board[this.action.m_initPos.row][this.action.m_initPos.col] >= Utils.bPawn) && (st.m_board[this.action.m_initPos.row][this.action.m_initPos.col] <= Utils.bKing)) {
                    //Check that the specified action is legal (not out-of-bounds, and legal movement)
                    if (this.action.isLegal(st, st.m_board[this.action.m_initPos.row][this.action.m_initPos.col])) {
                        this.invalidMovement = false;
                        ret = st.applyAction(this.action);
                        this.score += this.action.value;
                    } else {
                        System.out.println("Ilegal action");
                    }
                } else {
                    System.out.println("Invalid action: pick a black piece");
                }
            } else {
                if ((st.m_board[this.action.m_initPos.row][this.action.m_initPos.col] >= Utils.wPawn) && (st.m_board[this.action.m_initPos.row][this.action.m_initPos.col] <= Utils.wKing)) {
                    if (this.action.isLegal(st, st.m_board[this.action.m_initPos.row][this.action.m_initPos.col])) {
                        this.invalidMovement = false;
                        ret = st.applyAction(this.action);
                    } else {
                        System.out.println("Ilegal action");
                    }
                } else {
                    System.out.println("Invalid action: pick a white piece");
                }
            }

        }

        return this.ret;

    }
}
