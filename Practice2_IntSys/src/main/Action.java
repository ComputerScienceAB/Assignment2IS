package main;

// in this class we define a basic action for our problem. Going from position p0 to position p1.
public class Action {

    Position m_initPos = null;
    Position m_finalPos = null;

    public Action(Position p0, Position p1) {
        m_initPos = p0;
        m_finalPos = p1;
    }

    public Action() {
        this.m_initPos = new Position(-1,-1);
        this.m_finalPos = new Position(-1,-1);
    }

    /**
     * This method checks that the action chosen by the human player doesn't
     * contain out-of-bounds positions
     *
     * @param boardSize
     * @return
     */
    public boolean isValid(int boardSize) {
        
        boolean ret = false;

        if (this.m_initPos.row >= 0 && this.m_initPos.row < boardSize) {
            if (this.m_initPos.col >= 0 && this.m_initPos.col < boardSize) {
                if (this.m_finalPos.row >= 0 && this.m_finalPos.row < boardSize) {
                    if (this.m_finalPos.col >= 0 && this.m_finalPos.col < boardSize) {
                        ret = true;
                    }
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

