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
public class Algorithm {
    final int posInf = Integer.MAX_VALUE;
    final int negInf = Integer.MIN_VALUE;

    int best = -44;
    int agentColor;
    Piece mPiece;
    int searchDepth;
    
    public Algorithm(int c, int d) {
        this.agentColor = c;
        this.searchDepth = d;
    }
    
}