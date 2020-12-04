/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

public class Node {
    State state;
    Action action;
    Node parent;
    int depth;
    double cost;

    double Heuristic;
    
    public Node(State st){
        this.state=st;


    }
    
}
