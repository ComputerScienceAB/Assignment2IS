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
        String method = null;
        String initialTranslator;
        boolean initial = true;
        int depth;
        String color = null;
        int remainingMoves = 0;
        double probability = 0.0;
        int seed = -1;

        Player player1 = null;
        Player player2 = null;

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

        switch (color) {
            case "white":
                player1 = new Agent(color, remainingMoves, false, method);
                player2 = new Human("black");
                break;
            case "black":
                player1 = new Human("white");
                player2 = new Agent(color, remainingMoves, false, method);
                break;
            case "both":
                player1 = new Agent("white", remainingMoves, false, method);
                player2 = new Agent("black", remainingMoves, false, method);
                break;
            default:
                player1 = new Agent("white", remainingMoves, false, method);
                player2 = new Agent("black", remainingMoves, true, method);
                break;
        }

        System.out.println("White ones move first");
        while (true) {
            Utils.printBoard(state);
            switch (color) {
                case "white":
                    System.out.println("Player 1 (AI) moves");
                    //state = player1.Move(state);
                    //state.isFinal();
                    System.out.println("Player 2 (human), it's your turn");
                    state = player2.Move(state);
                    //state.isFinal();
                    break;
                case "black":
                    System.out.println("Player 1 (human), it's your turn");
                    state = player1.Move(state);
                    //state.isFinal();
                    System.out.println("Player 2 (AI) moves");
                    //state = player2.Move(state);
                    //state.isFinal();
                    break;
                case "both":
                    System.out.println("Player 1 (AI) moves");
                    //state = player1.Move(state);
                    //state.isFinal();
                    System.out.println("Player 2 (AI) moves");
                    //state = player2.Move(state);
                    //state.isFinal();
                    break;
                case "dummy":
                    System.out.println("Player 1 (AI) moves");
                    //state = player1.Move(state);
                    //state.isFinal();
                    System.out.println("Player 2 (dummy) does nothing");
                    break;
            }

        }

    }

}
