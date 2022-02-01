/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package API;

import ADT.WarehouseADT;
import Exceptions.InvalidValueException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Warehouse representa uma implementação de WarehouseADT.
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
        str = "Nome: " + this.getName() + "\nCapacidade: " + this.getMaxCapacity() + "\nStock: " + this.stock;
        
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

        File file = new File("exportJSON/armazem/Warehouse_" + this.getName() + ".json");
        file.getParentFile().mkdirs();

        try ( FileWriter writer = new FileWriter(file)) {
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

    /**
     * Exporta os dados de um armazem para formato JSON.
     *
     * @return true caso seja possível exportar para formato JSON, false caso
     * contrário.
     */
    @Override
    public boolean exportJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);
        File file = new File("exportJSON/armazem/Warehouse_" + this.getName() + ".json");;

        file.getParentFile().mkdirs();

        try ( FileWriter writer = new FileWriter(file)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(json);
        return true;
    }

    /**
     * Importar um JSON para criar um armazém
     *
     * @return armazém importado com JSON
     */
    public static Warehouse importJSON(String filename) {
        Gson gson = new Gson();

        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(filename));
        } catch (IOException ex) {
            Logger.getLogger(Warehouse.class.getName()).log(Level.SEVERE, null, ex);
        }

        Warehouse ware = gson.fromJson(reader, Warehouse.class);

        //System.out.println(ware.capacidade);
        //System.out.println(ware.stock);
        //System.out.println(ware.getName());
        //System.out.println(ware.getType());
        try {
            reader.close();
            // 2. JSON string to Java object
        } catch (IOException ex) {
            Logger.getLogger(Warehouse.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ware;
    }
}
