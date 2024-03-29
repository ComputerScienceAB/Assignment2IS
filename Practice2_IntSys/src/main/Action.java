package main;

// in this class we define a basic action for our problem. Going from position p0 to position p1.
import java.util.ArrayList;

public class Action {

    Position m_initPos = null;
    Position m_finalPos = null;
    int value;

    public Action(Position p0, Position p1) {
        m_initPos = p0;
        m_finalPos = p1;
    }

    public Action() {
        this.m_initPos = new Position(-1, -1);
        this.m_finalPos = new Position(-1, -1);
    }



    /**
     * This method compares the action specified by the human player with the possible actions of the chosen piece, and returns true if it is legal
     * @param st
     * @param piece
     * @return 
     */
    public boolean isLegal(State st, int piece) {
        boolean ret = false;
        ArrayList<Action> possibleActions;
        Piece p = Utils.getPiece(piece, this.m_initPos);
        possibleActions = p.getPossibleActions(st);
        for(int i=0;i<possibleActions.size();i++){
            if(this.m_initPos.equals(possibleActions.get(i).m_initPos)){
                if(this.m_finalPos.equals(possibleActions.get(i).m_finalPos)){
                    ret = true;
                    break;
                }
            }
        }    
        return ret;
    }

    // the cost of a given action is: 1 + maximum of the horizontal/vertical traveled distance
    public double getCost() {
        return 1 + Math.max(Math.abs(m_initPos.row - m_finalPos.row), Math.abs(m_initPos.col - m_finalPos.col));
    }

    // to String method, just for printing the solution
    public String toString() {
        String s = "";

        s += "[ (" + m_initPos.row + "," + m_initPos.col + ") -> ("
                + +m_finalPos.row + "," + m_finalPos.col + ") ]";

        return s;
    }

} // end of class

