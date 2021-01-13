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
        float counterMoveTime = 0;
        float startMoveTime;
        float endMoveTime;

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
                player1 = new Agent(color, method, depth);
                player2 = new Human("black");
                break;
            case "black":
                player1 = new Human("white");
                player2 = new Agent(color, method,depth);
                break;
            case "both":
                player1 = new Agent("white", method, depth);
                player2 = new Agent("black", method, depth);
                break;
            default:
                player1 = new Agent("white", method, depth);
                break;
        }

        System.out.println("White ones move first");
        long startTime = System.nanoTime();
        while (true) {
            Utils.printBoard(state);
            switch (color) {
                case "white":
                    System.out.println("Player 1 (AI) moves");
                    startMoveTime = System.nanoTime();
                    state = player1.Move(state);
                    endMoveTime = System.nanoTime();
                    counterMoveTime += (endMoveTime - startMoveTime);
                    Utils.printBoard(state);
                    if(state.isFinal()){
                        if(state.remainingMoves == 0){
                            player1.score = Utils.getUtility(state, player1.color);
                            player2.score = Utils.getUtility(state, player2.color);
                            if(player1.score > player2.score){
                                System.out.println("***End of the game, the winner is: Player 1 (White AI) with "+player1.score+" points***");
                            }else if(player1.score < player2.score){
                                System.out.println("***End of the game, the winner is: Player 2 (human) with "+player2.score+" points***");
                            }else{
                                System.out.println("***Draw, no winner***");
                            }
                        }else{
                            System.out.println("***End of the game, the winner is: Player 1 (White AI)***");                            
                        }
                        System.out.println("Generated states by Player 1: "+player1.searchEngine.generatedStates);
                        Utils.printBoard(state);
                        long stopTime = System.nanoTime(); //STOP
                        System.out.println("Execution time: "+((stopTime - startTime)/1000000)+ " miliseconds");
                        System.out.println("Average move time: "+(counterMoveTime/(remainingMoves-state.remainingMoves)/1000000)+ " miliseconds");
                        System.exit(0);
                    }                   
                    System.out.println("Player 2 (human), it's your turn");
                    startMoveTime = System.nanoTime();
                    state = player2.Move(state);
                    endMoveTime = System.nanoTime();
                    counterMoveTime += (endMoveTime - startMoveTime);
                    if(state.isFinal()){
                        if(state.remainingMoves == 0){
                            player1.score = Utils.getUtility(state, player1.color);
                            player2.score = Utils.getUtility(state, player2.color);
                            if(player1.score > player2.score){
                                System.out.println("***End of the game, the winner is: Player 1 (White AI) with "+player1.score+" points***");
                            }else if(player1.score < player2.score){
                                System.out.println("***End of the game, the winner is: Player 2 (human) with "+player2.score+" points***");
                            }else{
                                System.out.println("***Draw, no winner***");
                            }
                        }else{
                            System.out.println("***End of the game, the winner is: Player 2 (human)***");
                        }      
                        System.out.println("Generated states by Player 1: "+player1.searchEngine.generatedStates);
                        Utils.printBoard(state);
                        long stopTime = System.nanoTime(); //STOP
                        System.out.println("Execution time: "+((stopTime - startTime)/1000000)+ " miliseconds");
                        System.out.println("Average move time: "+(counterMoveTime/(remainingMoves-state.remainingMoves)/1000000)+ " miliseconds");
                        System.exit(0);
                    }
                    break;
                case "black":
                    System.out.println("Player 1 (human), it's your turn");
                    startMoveTime = System.nanoTime();
                    state = player1.Move(state);
                    endMoveTime = System.nanoTime();
                    counterMoveTime += (endMoveTime - startMoveTime);
                    Utils.printBoard(state);
                    if(state.isFinal()){
                        if(state.remainingMoves == 0){
                            player1.score = Utils.getUtility(state, player1.color);
                            player2.score = Utils.getUtility(state, player2.color);
                            if(player1.score > player2.score){
                                System.out.println("***End of the game, the winner is: Player 1 (human) with "+player1.score+" points***");                                
                            }else if(player1.score < player2.score){
                                System.out.println("***End of the game, the winner is: Player 2 (Black AI) with "+player2.score+" points***");
                            }else{
                                System.out.println("***Draw, no winner***");
                            }
                        }else{
                            System.out.println("***End of the game, the winner is: Player 1 (human)***");
                        }     
                        System.out.println("Generated states by Player 2: "+player2.searchEngine.generatedStates);
                        Utils.printBoard(state);
                        long stopTime = System.nanoTime(); //STOP
                        System.out.println("Execution time: "+((stopTime - startTime)/1000000)+ " miliseconds");
                        System.out.println("Average move time: "+(counterMoveTime/(remainingMoves-state.remainingMoves)/1000000)+ " miliseconds");
                        System.exit(0);
                    }
                    System.out.println("Player 2 (AI) moves");
                    startMoveTime = System.nanoTime();
                    state = player2.Move(state);
                    endMoveTime = System.nanoTime();
                    counterMoveTime += (endMoveTime - startMoveTime);
                    if(state.isFinal()){
                        if(state.remainingMoves == 0){
                            player1.score = Utils.getUtility(state, player1.color);
                            player2.score = Utils.getUtility(state, player2.color);
                            if(player1.score > player2.score){
                                System.out.println("***End of the game, the winner is: Player 1 (human) with "+player1.score+" points***");                                
                            }else if(player1.score < player2.score){
                                System.out.println("***End of the game, the winner is: Player 2 (Black AI) with "+player2.score+" points***");
                            }else{
                                System.out.println("***Draw, no winner***");
                            }
                        }else{
                            System.out.println("***End of the game, the winner is: Player 2 (Black AI)***");
                        }     
                        System.out.println("Generated states by Player 2: "+player2.searchEngine.generatedStates);
                        Utils.printBoard(state);
                        long stopTime = System.nanoTime(); //STOP
                        System.out.println("Execution time: "+((stopTime - startTime)/1000000)+ " miliseconds");
                        System.out.println("Average move time: "+(counterMoveTime/(remainingMoves-state.remainingMoves)/1000000)+ " miliseconds");
                        System.exit(0);
                    }
                    break;
                case "both":
                    System.out.println("Player 1 (AI) moves");
                    startMoveTime = System.nanoTime();
                    state = player1.Move(state);
                    endMoveTime = System.nanoTime();
                    counterMoveTime += (endMoveTime - startMoveTime);
                    Utils.printBoard(state);
                    if(state.isFinal()){
                        if(state.remainingMoves == 0){
                            player1.score = Utils.getUtility(state, player1.color);
                            player2.score = Utils.getUtility(state, player2.color);
                            if(player1.score > player2.score){
                                System.out.println("***End of the game, the winner is: Player 1 (White AI) with "+player1.score+" points***");                                
                            }else if(player1.score < player2.score){
                                System.out.println("***End of the game, the winner is: Player 2 (Black AI) with "+player2.score+" points***");
                            }else{
                                System.out.println("***Draw, no winner***");
                            }
                        }else{
                            System.out.println("***End of the game, the winner is: Player 1 (White AI)***"); 
                        }    
                        System.out.println("Generated states by Player 1: "+player1.searchEngine.generatedStates);
                        System.out.println("Generated states by Player 2: "+player2.searchEngine.generatedStates);
                        System.out.println("Total generated states: "+(player1.searchEngine.generatedStates + player2.searchEngine.generatedStates));
                        Utils.printBoard(state);
                        long stopTime = System.nanoTime(); //STOP
                        System.out.println("Execution time: "+((stopTime - startTime)/1000000)+ " miliseconds");
                        System.out.println("Average move time: "+(counterMoveTime/(remainingMoves-state.remainingMoves)/1000000)+ " miliseconds");
                        System.exit(0);
                    }
                    System.out.println("Player 2 (AI) moves");
                    startMoveTime = System.nanoTime();
                    state = player2.Move(state);
                    endMoveTime = System.nanoTime();
                    counterMoveTime += (endMoveTime - startMoveTime);
                    if(state.isFinal()){
                        if(state.remainingMoves == 0){
                            player1.score = Utils.getUtility(state, player1.color);
                            player2.score = Utils.getUtility(state, player2.color);
                            if(player1.score > player2.score){
                                System.out.println("***End of the game, the winner is: Player 1 (White AI) with "+player1.score+" points***");                                
                            }else if(player1.score < player2.score){
                                System.out.println("***End of the game, the winner is: Player 2 (Black AI) with "+player2.score+" points***");
                            }else{
                                System.out.println("***Draw, no winner***");
                            }
                        }else{
                            System.out.println("***End of the game, the winner is: Player 2 (Black AI)***"); 
                        }   
                        System.out.println("Generated states by Player 1: "+player1.searchEngine.generatedStates);
                        System.out.println("Generated states by Player 2: "+player2.searchEngine.generatedStates);
                        System.out.println("Total generated states: "+(player1.searchEngine.generatedStates + player2.searchEngine.generatedStates));
                        Utils.printBoard(state);
                        long stopTime = System.nanoTime(); //STOP
                        System.out.println("Execution time: "+((stopTime - startTime)/1000000)+ " miliseconds");
                        System.out.println("Average move time: "+(counterMoveTime/(remainingMoves-state.remainingMoves)/1000000)+ " miliseconds");
                        System.exit(0);
                    }
                    break;
                case "dummy":
                    System.out.println("Player 1 (AI) moves");
                    startMoveTime = System.nanoTime();
                    state = player1.Move(state);
                    endMoveTime = System.nanoTime();
                    counterMoveTime += (endMoveTime - startMoveTime);
                    Utils.printBoard(state);
                    if(state.isFinal()){
                        System.out.println("***End of the game, the winner is: Player 1 (White AI)***");
                        System.out.println("Generated states by Player 1: "+player1.searchEngine.generatedStates);
                        Utils.printBoard(state);
                        long stopTime = System.nanoTime(); //STOP
                        System.out.println("Execution time: "+((stopTime - startTime)/1000000)+ " miliseconds");
                        System.out.println("Average move time: "+(counterMoveTime/(remainingMoves-state.remainingMoves)/1000000)+ " miliseconds");
                        System.exit(0);
                    }
                    System.out.println("Player 2 (dummy) does nothing");
                    state.remainingMoves--;
                    if(state.isFinal()){
                        System.out.println("***End of the game, the winner is: Player 1 (White AI)***");
                        System.out.println("Generated states by Player 1: "+player1.searchEngine.generatedStates);
                        Utils.printBoard(state);
                        long stopTime = System.nanoTime(); //STOP
                        System.out.println("Execution time: "+((stopTime - startTime)/1000000)+ " miliseconds");
                        System.out.println("Average move time: "+(counterMoveTime/(remainingMoves-state.remainingMoves)/1000000)+ " miliseconds");
                        System.exit(0);
                    }
                    break;
            }

        }

    }

}
