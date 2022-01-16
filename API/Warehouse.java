/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import ADT.WarehouseADT;
import Exceptions.InvalidValueException;

/**
 * Armazém
 * 
 * @author Tomás Pendão
 */
public class Warehouse extends Place implements WarehouseADT{

    private float maxCapacity;
    private float availableCapacity;

    public Warehouse(float maxCapacity, float availableCapacity, String name) {
        super(name, "Armazém");
        if(maxCapacity >= 0 && availableCapacity >= 0){
            this.maxCapacity = maxCapacity;
            if(availableCapacity <= maxCapacity){
                this.availableCapacity = availableCapacity;
            }
        }else{
            this.maxCapacity = this.availableCapacity = 0;
        }
    }
    
    @Override
    public void setCapacity(float maxCapacity) {
        if(maxCapacity >= 0){
            this.maxCapacity = maxCapacity;
        }else{
            throw new InvalidValueException(Float.toString(maxCapacity));
        }
    }

    @Override
    public void setAvailableCapacity(float capacity) {
        if(capacity >= 0 && capacity <= this.maxCapacity){
            this.availableCapacity = capacity;
        }else{
            throw new InvalidValueException(Float.toString(capacity));
        }
    }

    @Override
    public String printWarehouses() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean export() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public float getMaxCapacity() {
        return maxCapacity;
    }

    public float getAvailableCapacity() {
        return availableCapacity;
    }
    
    
}
