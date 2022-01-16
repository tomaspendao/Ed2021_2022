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

    /**
     * Capacidade máxima de um armazém
     */
    private float capacidade;
    /**
     * Stock que o armazém tem no momento
     */
    private float stock;

    /**
     * Construtor que intacia armazém
     * 
     * @param maxCapacity Capacidade máxima do armazém
     * @param availableCapacity stock do armazem
     * @param name nome do armazem
     */
    public Warehouse(float maxCapacity, float availableCapacity, String name) {
        super(name, "Armazém");
        this.setCapacity(maxCapacity);
        this.setAvailableCapacity(availableCapacity);
    }

    /**
     * Construtor vazio
     */
    public Warehouse() {
    }
        
    /**
     * Definir uma capacidade máxima de mercadoria que um armazém pode ter.
     *
     * @param maxCapacity capacidade máxima
     */
    @Override
    public final void setCapacity(float maxCapacity) {
        if (maxCapacity >= 0 && maxCapacity >= this.stock) {
            this.capacidade = maxCapacity;
        } else {
            throw new InvalidValueException(Float.toString(maxCapacity));
        }
    }

    /**
     * Definir a quantidade atual de mercadoria disponivel que um armazém tem.
     *
     * @param capacity quantidade atual
     */
    @Override
    public final void setAvailableCapacity(float capacity) {
        if (capacity >= 0 && capacity <= this.capacidade) {
            this.stock = capacity;
        } else {
            throw new InvalidValueException(Float.toString(capacity));
        }
    }

    /**
     * Lista a informação relativa a um armazém.
     *
     * @return Lista um armazém.
     */
    @Override
    public String printWarehouse() {
        String str = "";
        str = "Nome: " + this.getName() + ";Tipo: " + this.getType() + ";Capacidade: " + this.getMaxCapacity() + ";Stock: " + this.stock;
        return str;
    }

    /**
     * Exporta os dados de um armazém para formato JSON.
     *
     * @return true caso seja possível exportar para formato JSON, false caso
     * contrário.
     */
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

    /**
     * Obter o valor da capacidade máxima de um Armazém
     * 
     * @return a capacidade máxima
     */
    public float getMaxCapacity() {
        return capacidade;
    }

    /**
     * Obter o valor do stock num armazém
     * 
     * @return o stock
     */
    public float getAvailableCapacity() {
        return stock;
    }

}
