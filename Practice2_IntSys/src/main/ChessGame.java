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
        int depth = 0;
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
        
        state.remainingMoves = remainingMoves;

        switch (color) {
            case "white":
                player1 = new Agent(color, false, method, depth);
                player2 = new Human("black");
                break;
            case "black":
                player1 = new Human("white");
                player2 = new Agent(color, false, method, depth);
                break;
            case "both":
                player1 = new Agent("white", false, method, depth);
                player2 = new Agent("black", false, method, depth);
                break;
            default:
                player1 = new Agent("white", false, method, depth);
                player2 = new Agent("black", true, method, depth);
                break;
        }

        System.out.println("White ones move first");
        while (true) {
            Utils.printBoard(state);
            switch (color) {
                case "white":
                    System.out.println("Player 1 (AI) moves");
                    state = player1.Move(state);
                    Utils.printBoard(state);
                    if(state.isFinal()){
                        if(state.remainingMoves == 0){
                            if(player1.score > player2.score){
                                System.out.println("***End of the game, the winner is: Player 1 (White AI)***");
                            }else if(player1.score < player2.score){
                                System.out.println("***End of the game, the winner is: Player 2 (human)***");
                            }else{
                                System.out.println("***Draw, no winner***");
                            }
                        }else{
                            System.out.println("***End of the game, the winner is: Player 1 (White AI)***");
                        }
                        Utils.printBoard(state);
                        System.exit(0);
                    }                   
                    System.out.println("Player 2 (human), it's your turn");
                    state = player2.Move(state);
                    if(state.isFinal()){
                        if(state.remainingMoves == 0){
                            if(player1.score > player2.score){
                                System.out.println("***End of the game, the winner is: Player 1 (White AI)***");
                            }else if(player1.score < player2.score){
                                System.out.println("***End of the game, the winner is: Player 2 (human)***");
                            }else{
                                System.out.println("***Draw, no winner***");
                            }
                        }else{
                            System.out.println("***End of the game, the winner is: Player 2 (human)***");
                        }      
                        Utils.printBoard(state);
                        System.exit(0);
                    }
                    break;
                case "black":
                    System.out.println("Player 1 (human), it's your turn");
                    state = player1.Move(state);
                    Utils.printBoard(state);
                    if(state.isFinal()){
                        if(state.remainingMoves == 0){
                            if(player1.score > player2.score){
                                System.out.println("***End of the game, the winner is: Player 1 (human)***");                                
                            }else if(player1.score < player2.score){
                                System.out.println("***End of the game, the winner is: Player 2 (Black AI)***");
                            }else{
                                System.out.println("***Draw, no winner***");
                            }
                        }else{
                            System.out.println("***End of the game, the winner is: Player 1 (human)***");
                        }     
                        Utils.printBoard(state);
                        System.exit(0);
                    }
                    System.out.println("Player 2 (AI) moves");
                    state = player2.Move(state);
                    if(state.isFinal()){
                        if(state.remainingMoves == 0){
                            if(player1.score > player2.score){
                                System.out.println("***End of the game, the winner is: Player 1 (human)***");                                
                            }else if(player1.score < player2.score){
                                System.out.println("***End of the game, the winner is: Player 2 (Black AI)***");
                            }else{
                                System.out.println("***Draw, no winner***");
                            }
                        }else{
                            System.out.println("***End of the game, the winner is: Player 2 (Black AI)***");
                        }     
                        Utils.printBoard(state);
                        System.exit(0);
                    }
                    break;
                case "both":
                    System.out.println("Player 1 (AI) moves");
                    state = player1.Move(state);
                    Utils.printBoard(state);
                    if(state.isFinal()){
                        if(state.remainingMoves == 0){
                            if(player1.score > player2.score){
                                System.out.println("***End of the game, the winner is: Player 1 (White AI)***");                                
                            }else if(player1.score < player2.score){
                                System.out.println("***End of the game, the winner is: Player 2 (Black AI)***");
                            }else{
                                System.out.println("***Draw, no winner***");
                            }
                        }else{
                            System.out.println("***End of the game, the winner is: Player 1 (White AI)***"); 
                        }    
                        Utils.printBoard(state);
                        System.exit(0);
                    }
                    System.out.println("Player 2 (AI) moves");
                    state = player2.Move(state);
                    if(state.isFinal()){
                        if(state.remainingMoves == 0){
                            if(player1.score > player2.score){
                                System.out.println("***End of the game, the winner is: Player 1 (White AI)***");                                
                            }else if(player1.score < player2.score){
                                System.out.println("***End of the game, the winner is: Player 2 (Black AI)***");
                            }else{
                                System.out.println("***Draw, no winner***");
                            }
                        }else{
                            System.out.println("***End of the game, the winner is: Player 2 (Black AI)***"); 
                        }   
                        Utils.printBoard(state);
                        System.exit(0);
                    }
                    break;
                case "dummy":
                    System.out.println("Player 1 (AI) moves");
                    state = player1.Move(state);
                    Utils.printBoard(state);
                    if(state.isFinal()){
                        System.out.println("***End of the game, the winner is: Player 1 (White AI)***");
                        Utils.printBoard(state);
                        System.exit(0);
                    }
                    System.out.println("Player 2 (dummy) does nothing");
                    break;
            }

        }

    }

}
