/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.graph;

import domain.*;

/**
 *
 * @author Usuario
 */
public class EdgeWeight {

    private Object edge;//Arista
    private Object weight;//Peso
    public EdgeWeight(Object edge, Object weight) {
        
        this.edge = edge;
        this.weight = weight;
        
    }

    public Object getEdge() {
        return edge;
    }

    public void setEdge(Object edge) {
        this.edge = edge;
    }

    public Object getWeight() {
        return weight;
    }

    public void setWeight(Object weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        
        if(weight==null){
            return "Edge= "+edge;
        }else{
            return "Edge= "+edge+", weight= "+weight;
        }
    }
    
}
