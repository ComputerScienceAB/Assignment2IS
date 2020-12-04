package main;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
    boolean informedSearch;
    
    public NodeComparator(boolean is){
        this.informedSearch=is;
    }
    
    @Override
    public int compare(Node n1, Node n2) {
    // ordering nodes by their heuristic value in ascendant way.
        
        if(informedSearch){
           if (n1.Heuristic > n2.Heuristic){
                return 1;
            }else if (n1.Heuristic < n2.Heuristic){
                return -1;
            } 
        }else{
            if(n1.cost > n2.cost){
                return 1;
            }else if(n1.cost < n2.cost){
                return -1;
            }
        }
        
        return 0;
    }
}
