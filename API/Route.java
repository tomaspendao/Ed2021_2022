/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import ADT.UnorderedListADT;
import Collections.DoubleLinkedList.DoubleLinkedUnorderedList;
import Collections.LinkedList.GraphWeightList;
import java.util.Iterator;

/**
 *
 * @author Tomás Pendão
 */
public class Route {
    
    private UnorderedListADT<Place> rota; 
    private int amountOfRefills;
    private float totalDistance;
    private Seller vendedor;
    private float stock;
    
    //e capaz de nao ser necessario
    private Place start;
    private Place target;

    public Route(Seller vendedor) {
        this.rota = new DoubleLinkedUnorderedList<>();
        this.amountOfRefills = 0;
        this.totalDistance = 0;
        this.vendedor = vendedor; 
        this.stock = 0;
        //
        this.start = null;//sede
        this.target = null;
    }
    
    public Iterator generateRoute(GraphWeightList<Place> caminhos, Seller vendedor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Iterator refillRoute(GraphWeightList<Place> caminhos, UnorderedListADT<Warehouse> armazens) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
