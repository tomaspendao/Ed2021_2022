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
public class Warehouse extends Place implements WarehouseADT {

    private float maxCapacity;
    private float availableCapacity;

    public Warehouse(float maxCapacity, float availableCapacity, String name) {
        super(name, "Armazém");
        this.setCapacity(maxCapacity);
        this.setAvailableCapacity(availableCapacity);
    }

    @Override
    public final void setCapacity(float maxCapacity) {
        if (maxCapacity >= 0 && maxCapacity >= this.availableCapacity) {
            this.maxCapacity = maxCapacity;
        } else {
            throw new InvalidValueException(Float.toString(maxCapacity));
        }
    }

    @Override
    public final void setAvailableCapacity(float capacity) {
        if (capacity >= 0 && capacity <= this.maxCapacity) {
            this.availableCapacity = capacity;
        } else {
            throw new InvalidValueException(Float.toString(capacity));
        }
    }

    @Override
    public String printWarehouse() {
        String str = "";
        str = "Nome: " + this.getName() + ";Tipo: " + this.getType() + ";Capacidade: " + this.getMaxCapacity() + ";Stock: " + this.availableCapacity;
        return str;
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
