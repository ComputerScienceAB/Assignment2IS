package main;

// This class contains the information needed to represent a state 
public class State {

    int[][] m_board = null;
    int m_boardSize = 8;
    int remainingMoves;
    //This variable will be checked to determine if the game is finished
    int nKings = 2;

    // constructor
    public State(int[][] board) {
        this.m_board = board;
    }
    
    public State(int[][] board, int rm) {
        this.m_board = board;
        this.remainingMoves = rm;
    }

    /**
     * The final state will be reached when there are less than 2 kings or the
     * maximum number of moves has been reached
     *
     * @return
     */
    public boolean isFinal() {
        if (this.nKings < 2) {
            return true;
        }
        return this.remainingMoves == 0;
    }

    // hard copy of an State
    public State copy() {
        int[][] cBoard = new int[this.m_boardSize][this.m_boardSize];

        for (int r = 0; r < this.m_boardSize; r++) {
            for (int c = 0; c < this.m_boardSize; c++) {
                cBoard[r][c] = this.m_board[r][c];
            }
        }

        return new State(cBoard,this.remainingMoves);
    }

    // apply a given action over the current state -which remains unmodified. Return a new state
    public State applyAction(Action action) {
        int aux;
        State newState = this.copy();
        aux = newState.m_board[action.m_initPos.row][action.m_initPos.col];
        newState.m_board[action.m_initPos.row][action.m_initPos.col] = Utils.empty;
        //If the piece at the final position is a king, the king's counter is decreased in order to trigger the return true at isFinal
        if ((newState.m_board[action.m_finalPos.row][action.m_finalPos.col] == Utils.bKing) || (newState.m_board[action.m_finalPos.row][action.m_finalPos.col] == Utils.wKing)) {
            newState.nKings--;
        }
        newState.m_board[action.m_finalPos.row][action.m_finalPos.col] = aux;
        newState.remainingMoves--;
        //The last rows of the board will be checked to see if a pawn has arrived there, in order to turn it into a queen
        for (int i = 0; i < this.m_boardSize; i = i + this.m_boardSize - 1) {
            for (int j = 0; j < this.m_boardSize; j++) {
                if(newState.m_board[i][j] == Utils.bPawn){
                    newState.m_board[i][j] = Utils.bQueen;
                    //System.out.println("Black Pawn in position "+i+","+j+" turned into a Black Queen!!");
                }
                if(newState.m_board[i][j] == Utils.wPawn){
                    newState.m_board[i][j] = Utils.wQueen;
                    //System.out.println("White Pawn in position "+newState.m_board[i][j]+" turned into a White Queen!!");
                }
            }
        }

        return newState;
    }

}
