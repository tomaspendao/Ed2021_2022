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

    public UnorderedListADT<Place> getRota() {
        return rota;
    }

    public void setRota(UnorderedListADT<Place> rota) {
        this.rota = rota;
    }

    public int getAmountOfRefills() {
        return amountOfRefills;
    }

    public void setAmountOfRefills(int amountOfRefills) {
        this.amountOfRefills = amountOfRefills;
    }
    
    public void incrementAmountOfRefills() {
        this.amountOfRefills++;
    }

    public float getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(float totalDistance) {
        this.totalDistance = totalDistance;
    }
    
    public void addTotalDistance(float plusDistance) {
        this.totalDistance = this.totalDistance + plusDistance;
    }

    public Seller getVendedor() {
        return vendedor;
    }

    public void setVendedor(Seller vendedor) {
        this.vendedor = vendedor;
    }

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }
    
    public void addStock(float plusStock) {
        this.stock = this.stock + plusStock;
    }
    
    public void removeStock(float minusStock) {
        this.stock = this.stock - minusStock;
    }

    public Place getStart() {
        return start;
    }

    public void setStart(Place start) {
        this.start = start;
    }

    public Place getTarget() {
        return target;
    }

    public void setTarget(Place target) {
        this.target = target;
    }
}
