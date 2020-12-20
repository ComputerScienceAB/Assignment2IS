package main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Utils {

    // all the pieces
    static final int wPawn = 0;
    static final int wRook = 1;
    static final int wBishop = 2;
    static final int wKnight = 3;
    static final int wQueen = 4;
    static final int wKing = 5;
    static final int bPawn = 6;
    static final int bRook = 7;
    static final int bBishop = 8;
    static final int bKnight = 9;
    static final int bQueen = 10;
    static final int bKing = 11;
    static final int empty = 12;

    // number of pieces
    static final int diffPieces = 12;

    // name (and letter) of each piece
    static final String[] names = {"wPawn", "wRook", "wBishop", "wKnight", "wQueen", "wKing",
        "bPawn", "bRook", "bBishop", "bKnight", "bQueen", "bKing"};
    static final String[] letters = {"P", "R", "B", "N", "Q", "K", "p", "r", "b", "n", "q", "k", " "};
    // Note we use N for Knight instead of K (to avoid confussion with King)
    // Note we add " " for empty cell

    /**
     * Get color piece
     */
    public static int getColorPiece(int piece) {
        if ((piece >= 0) && (piece <= 5)) {
            return 0; //white
        } else if ((piece > 5) && (piece <= 11)) {
            return 1; //black
        } else {
            System.out.println("\n** Error, wrong piece code\n");
            System.exit(0);
        }

        return -1; //never arrives here, just to avoid compilation error
    }
    
    /**
     * This method returns an object of a subclass of Piece from the integer id of a piece
     * @param piece
     * @param pos
     * @return 
     */
    public static Piece getPiece(int piece, Position pos){
        Piece p = null;
        switch (piece) {
            case Utils.wPawn:
                p = new Pawn(0, pos);
                break;
            case Utils.wRook:
                p = new Rook(0, pos);
                break;
            case Utils.wBishop:
                p = new Bishop(0, pos);
                break;
            case Utils.wKnight:
                p = new Knight(0, pos);
                break;
            case Utils.wQueen:
                p = new Queen(0, pos);
                break;
            case Utils.wKing:
                p = new King(0, pos);
                break;
            case Utils.bPawn:
                p = new Pawn(1, pos);
                break;
            case Utils.bRook:
                p = new Rook(1, pos);
                break;
            case Utils.bBishop:
                p = new Bishop(1, pos);
                break;
            case Utils.bKnight:
                p = new Knight(1, pos);
                break;
            case Utils.bQueen:
                p = new Queen(1, pos);
                break;
            case Utils.bKing:
                p = new King(1, pos);
                break;
        }
        return p;
    }


    /**
     * fill (by rows) an ArrayList with all the possible coordinates
     *
     * @param n size of the board
     */
    public static ArrayList<Position> getAllBoardPositions(int n) {
        ArrayList<Position> pos = new ArrayList<Position>(n * n);

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                pos.add(new Position(r, c));
            }
        }

        return pos;
    }

    /**
     * Print a state (board + agent)
     */
    public static void printBoard(State state) {
        DecimalFormat df = new DecimalFormat("00");

        int size = state.m_boardSize;
        /*if (size>50){
			System.out.println("**Error, board too large to be text-printed ...\n");
			System.exit(0);
		}*/

        // upper row
        System.out.print("   ");
        for (int c = 0; c < size; c++) {
            System.out.print(df.format(c) + " ");
        }
        System.out.println();

        System.out.print("  ");
        for (int i = 0; i < size; i++) {
            System.out.print("---");
        }
        System.out.println("--");

        // board
        for (int r = 0; r < size; r++) {
            System.out.print(df.format(r) + "|");
            for (int c = 0; c < size; c++) {
                System.out.print(" " + letters[state.m_board[r][c]] + "|");
            }
            //botton row
            System.out.println("  ");
            for (int i = 0; i < size; i++) {
                System.out.print("---");
            }
            System.out.println("--");
        }

    }


    /**
     * This method generates a random board problem to play chess.
     *
     * @param p probability for each piece to be included
     * @param seed to initiate the random generator (for reproducibility)
     * @return the initial state (board)
     */
    public static State getChessInstancePosition(double p, int seed) {

        int[] numPieces = {8, 2, 2, 2, 1, 1, 8, 2, 2, 2, 1, 1};

        int n = 8;
        int[][] board = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                board[r][c] = Utils.empty;
            }
        }
        Random gen = new Random(seed);
        ArrayList<Position> allPositions = getAllBoardPositions(n);
        // placing the two kings in random position
        int r = gen.nextInt(n * n);
        Position wkingPos = allPositions.remove(r);
        board[wkingPos.row][wkingPos.col] = Utils.wKing;
        numPieces[Utils.wKing] -= 1;
        r = gen.nextInt((n * n) - 1);
        Position bkingPos = allPositions.remove(r);
        board[bkingPos.row][bkingPos.col] = Utils.bKing;
        numPieces[Utils.bKing] -= 1;
        // placing the rest of chess.pieces
        Position pos = null;
        for (int piece = 0; piece < diffPieces; piece++) {
            for (int j = 0; j < numPieces[piece]; j++) {
                if (gen.nextDouble() <= p) {
                    r = gen.nextInt(allPositions.size());
                    pos = allPositions.remove(r);
                    board[pos.row][pos.col] = piece;
                }
            }
        }
        // Creating the instance, i.e., the state
        State state = new State(board);
        return state;
    }
    

    /**
     * This method generates the initial state of a chess game
     * @return 
     */
    public static State getChessInstance() {
        int n = 8;
        int[][] board = new int[n][n];
        // Pawn
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                board[r][c] = Utils.empty;
            }
        }
        for (int c = 0; c < n; c++) {
            board[1][c] = Utils.wPawn;
            board[n - 2][c] = Utils.bPawn;
        }
        //white pieces
        board[0][0] = Utils.wRook;
        board[0][1] = Utils.wKnight;
        board[0][2] = Utils.wBishop;
        board[0][n - 1] = Utils.wRook;
        board[0][n - 2] = Utils.wKnight;
        board[0][n - 3] = Utils.wBishop;
        board[0][3] = Utils.wKing;
        board[0][4] = Utils.wQueen;
        //black pieces
        board[n - 1][0] = Utils.bRook;
        board[n - 1][1] = Utils.bKnight;
        board[n - 1][2] = Utils.bBishop;
        board[n - 1][n - 1] = Utils.bRook;
        board[n - 1][n - 2] = Utils.bKnight;
        board[n - 1][n - 3] = Utils.bBishop;
        board[n - 1][3] = Utils.bKing;
        board[n - 1][4] = Utils.bQueen;
        // Creating the instance, i.e., the state
        State state = new State(board);
        return state;
    }
    
    /**
     * This is the method used to get the utility of an state
     * @param state
     * @param agentColor
     * @return 
     */
    public static int getUtility(State state, int agentColor) {
        int whitescore = 0;
        int blackscore = 0;
        for (int i = 0; i < state.m_boardSize; i++) {
            for (int j = 0; j < state.m_boardSize; j++) {
                if (state.m_board[i][j] != Utils.empty) {
                    if (state.m_board[i][j] >= Utils.wPawn && state.m_board[i][j] <= Utils.wKing) { //if the piece in that position is white
                        if (state.m_board[i][j] == Utils.wPawn) {
                            whitescore += 100;
                        }
                        if (state.m_board[i][j] == Utils.wRook) {
                            whitescore += 500;
                        }
                        if (state.m_board[i][j] == Utils.wBishop) {
                            whitescore += 330;
                        }
                        if (state.m_board[i][j] == Utils.wKnight) {
                            whitescore += 320;
                        }
                        if (state.m_board[i][j] == Utils.wQueen) {
                            whitescore += 900;
                        }
                        if (state.m_board[i][j] == Utils.wKing) {
                            whitescore += 20000;
                        }
                    } else { //if piece is black
                        if (state.m_board[i][j] == Utils.bPawn) {
                            blackscore += 100;
                        }
                        if (state.m_board[i][j] == Utils.bRook) {
                            blackscore += 500;
                        }
                        if (state.m_board[i][j] == Utils.bBishop) {
                            blackscore += 330;
                        }
                        if (state.m_board[i][j] == Utils.bKnight) {
                            blackscore += 320;
                        }
                        if (state.m_board[i][j] == Utils.bQueen) {
                            blackscore += 900;
                        }
                        if (state.m_board[i][j] == Utils.bKing) {
                            blackscore += 20000;
                        }
                    }
                }
            }
        }
        if(agentColor == 0){
            return whitescore-blackscore;
        }else{
            return blackscore-whitescore;
        }
        
    }

} // end of class

