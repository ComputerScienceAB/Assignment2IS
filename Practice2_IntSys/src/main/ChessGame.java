/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Javier
 */
public class ChessGame {

    public static void main(String args[]) {
        String method;
        String initialTranslator;
        boolean initial = true;
        int depth;
        String color = null;
        int remainingMoves = 0;
        double probability = 0.0;
        int seed = -1;

        Agent player1 = null;
        Agent player2 = null;

        State state;

        if ((args.length == 5) || (args.length == 7)) {
            method = args[0];
            initialTranslator = args[1];
            depth = Integer.parseInt(args[2]);
            color = args[3];
            remainingMoves = Integer.parseInt(args[4]);
            if (initialTranslator.equals("false")) {
                if (args.length != 7) {
                    System.out.println("ERROR: you should provide seed and probability");
                    System.exit(0);
                } else {
                    initial = false;
                    probability = Double.parseDouble(args[5]);
                    seed = Integer.parseInt(args[6]);
                }
            }
        } else {
            System.out.println("ERROR: incorrect number of parameters");
            System.exit(0);
        }

        if (initial) {
            state = Utils.getChessInstance();
        } else {
            state = Utils.getChessInstancePosition(probability, seed);
        }

        
      
        Utils.printBoard(state);

        if (color.equals("white") || color.equals("black")) {
            player1 = new Agent(color, remainingMoves, false);
        } else if (color.equals("both")) {
            player1 = new Agent("white", remainingMoves, false);
            player2 = new Agent("black", remainingMoves, false);
        }else{
            player1 = new Agent("white", remainingMoves, true);
            player2 = new Agent("black", remainingMoves, true);
        }

    }

}
