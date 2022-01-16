/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import ADT.WarehouseADT;
import Exceptions.InvalidValueException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Armazém
 *
 * @author Tomás Pendão
 */
public class Warehouse extends Place implements WarehouseADT {

    private float capacidade;
    private float stock;

    public Warehouse(float maxCapacity, float availableCapacity, String name) {
        super(name, "Armazém");
        this.setCapacity(maxCapacity);
        this.setAvailableCapacity(availableCapacity);
    }

    @Override
    public final void setCapacity(float maxCapacity) {
        if (maxCapacity >= 0 && maxCapacity >= this.stock) {
            this.capacidade = maxCapacity;
        } else {
            throw new InvalidValueException(Float.toString(maxCapacity));
        }
    }

    @Override
    public final void setAvailableCapacity(float capacity) {
        if (capacity >= 0 && capacity <= this.capacidade) {
            this.stock = capacity;
        } else {
            throw new InvalidValueException(Float.toString(capacity));
        }
    }

    @Override
    public String printWarehouse() {
        String str = "";
        str = "Nome: " + this.getName() + ";Tipo: " + this.getType() + ";Capacidade: " + this.getMaxCapacity() + ";Stock: " + this.stock;
        return str;
    }

    @Override
    public boolean export() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String json = gson.toJson(this);
        try (FileWriter writer = new FileWriter("Warehouse_"+this.getName()+".json")) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println(json);
        
        return true;
    }

    public float getMaxCapacity() {
        return capacidade;
    }

    public float getAvailableCapacity() {
        return stock;
    }

}
