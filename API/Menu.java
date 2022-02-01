/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
package API;

import ADT.UnorderedListADT;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Classe para armazenar comportamentos de um menu.
 *
 * @author Tomás Pendão
 */
public class Menu {

    /**
     * Ponto de começo.
     */
    public static void start() {
        Menu menu = new Menu();

        menu.menu();
    }

    /**
     * Menu inicial.
     */
    private void menu() {
        Scanner in = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            switch (mainMenu(in)) {
                case 1:
                    System.out.println("Criar uma empresa");

                    Scanner nameEmpresa = new Scanner(System.in);
                    createCompany(nameEmpresa);
                    break;
                case 2:
                    System.out.println("Importar JSON");

                    Scanner fileName = new Scanner(System.in);
                    importJSON(fileName);
                    break;
                case 0:
                    System.out.println("Exiting");

                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Menu principal.
     *
     * @param in
     * @return Retorna a opção escolhida.
     */
    private int mainMenu(Scanner in) {
        System.out.println("1 - Criar uma Empresa\n2 - Importar um JSON" + "\n0 - Sair");
        System.out.print("Opção: ");

        int choice = in.nextInt();
        System.out.println();

        if (choice >= 0 && choice <= 2) {
            return choice;
        } else {
            System.out.println("Opção inválida!");

            return choice;
        }
    }

    /**
     * Sub menu para criar uma empresa.
     *
     * @param name Nome da empresa a criar.
     */
    private void createCompany(Scanner name) {
        System.out.println("Nome da Empresa:");

        String companyName = name.nextLine();
        System.out.println();

        Company empresa = new Company(companyName);

        System.out.println("Nome da Empresa: " + empresa.getName());
        System.out.println("Place 1 : " + empresa.getLocais().first().getName());
        System.out.println("Place 1 : " + empresa.getLocais().first().getType());

        Scanner in = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            switch (companyMenu(in)) {
                case 1:
                    System.out.println("Adicionar Informação");

                    Scanner add = new Scanner(System.in);
                    this.addInfo(add, empresa);
                    break;
                case 2:
                    System.out.println("Editar Informação");

                    Scanner edit = new Scanner(System.in);
                    this.editInfo(edit, empresa);
                    break;
                case 3:
                    System.out.println("Imprimir Informação");

                    Scanner printo = new Scanner(System.in);
                    this.printInfo(printo, empresa);
                    break;
                case 4:
                    System.out.println("Exportar Informação");

                    Scanner exporto = new Scanner(System.in);
                    this.exportInfo(exporto, empresa);
                    break;
                case 5:
                    System.out.println("Start");

                    Scanner teste = new Scanner(System.in);
                    this.startTeste(empresa, teste);
                    break;
                case 0:
                    System.out.println("Exiting");

                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Deseja guardar a empresa?\n\t1 - SIM\n\t2 - NÃO");
                    int firstAnswer = scanner.nextInt();

                    if (firstAnswer == 1) {
                        empresa.export();
                    }

                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Sub menu com opções de uma empresa.
     *
     * @param in
     * @return
     */
    private int companyMenu(Scanner in) {
        System.out.println("1 - Adicionar Informação\n2 - Editar Informação" + "\n3 - Imprimir Informação\n"
                + "4 - Exportar Informação\n5 - Start\n0 - Sair");
        System.out.print("Opção: ");

        int choice = in.nextInt();
        System.out.println();

        if (choice >= 0 && choice <= 5) {
            return choice;
        } else {
            System.out.println("Opção inválida!");

            return choice;
        }
    }

    /**
     * Executa opções como adicionar um vendedor, mercado, armazém ou caminho.
     *
     * @param in
     * @param empresa
     */
    private void addInfo(Scanner in, Company empresa) {
        boolean exit = false;

        while (!exit) {
            switch (this.addInfoMenu(in)) {
                case 1:
                    System.out.println("Adicionar Vendedor");
                    Scanner seller = new Scanner(System.in);

                    System.out.println("Nome do Vendedor: ");
                    String nameSeller = seller.nextLine();

                    System.out.println("Capacidade Máxima do Vendedor (kg): ");
                    float capacitySeller = seller.nextFloat();
                    Seller vendedor = new Seller(capacitySeller, nameSeller);

                    this.addMarkets(vendedor, empresa);
                    empresa.addSeller(vendedor);

                    System.out.println(empresa.printSellers());
                    break;
                case 2:
                    System.out.println("Adicionar Mercado");
                    Scanner market = new Scanner(System.in);

                    System.out.println("Nome do Mercado: ");
                    String nameMarket = market.nextLine();
                    Market mercado = new Market(nameMarket);

                    //perguntar se quer adicionar clientes automaticamnte ou manualmente
                    this.addClients(mercado);

                    //System.out.println(mercado.getClients().toString());
                    empresa.addMarket(mercado);

                    System.out.println(empresa.printMarket());
                    break;
                case 3:
                    System.out.println("Adicionar Armazém");
                    Scanner warehouse = new Scanner(System.in);

                    System.out.println("Nome do Armazém: ");
                    String nameWarehouse = warehouse.nextLine();

                    System.out.println("Capacidade máxima do Armazém (kg): ");
                    float maxCapWarehouse = warehouse.nextFloat();
                    Scanner stockWarehouse = new Scanner(System.in);

                    float currentCapWarehouse;

                    switch (this.choiceRandomOrManualMenu(stockWarehouse)) {
                        case 1:
                            System.out.println("Capacidade atual do Armazém (kg): ");
                            currentCapWarehouse = warehouse.nextFloat();
                            break;
                        default:
                            //random
                            currentCapWarehouse = (float) (Math.random() * (maxCapWarehouse - 0)) + 0;
                            System.out.println("Capacidade atual do Armazém: " + currentCapWarehouse);
                    }

                    Warehouse armazem = new Warehouse(maxCapWarehouse, currentCapWarehouse, nameWarehouse);

                    empresa.addWarehouse(armazem);
                    break;
                case 4:
                    System.out.println("Adicionar Caminho");
                    Scanner edge = new Scanner(System.in);

                    System.out.println("Selecionar o ponto de partida");
                    String startName = this.printExistingPlaces(empresa);

                    System.out.println("Selecionar o ponto de chegada");
                    String targetName = this.printExistingPlaces(empresa);

                    System.out.println("Distancia (ex:\"30\"): ");
                    float weightPath = edge.nextFloat();

                    empresa.addRoute(startName, targetName, weightPath);
                    break;
                case 0:
                    System.out.println("Backing");

                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Sub menu que apresenta as opções de adicionar um vendedor, mercado,
     * armazém ou caminho.
     *
     * @param in
     * @return
     */
    private int addInfoMenu(Scanner in) {
        System.out.println("1 - Adicionar Vendedor\n2 - Adicionar Mercado" + "\n3 - Adicionar Armazém\n"
                + "4 - Adicionar Caminho\n0 - Back");
        System.out.print("Opção: ");

        int choice = in.nextInt();
        System.out.println();

        if (choice >= 0 && choice <= 4) {
            return choice;
        } else {
            System.out.println("Opção inválida!");
            return choice;
        }
    }

    /**
     * Executa opções como editar um vendedor, mercado, armazém ou caminho.
     *
     * @param in
     * @param empresa
     */
    private void editInfo(Scanner in, Company empresa) {
        boolean exit = false;

        while (!exit) {
            switch (this.editInfoMenu(in)) {
                case 1:
                    System.out.println("Editar Vendedor");

                    this.printIdOfSeller(empresa);

                    Scanner editSeller = new Scanner(System.in);

                    System.out.println("Id do Vendedor: ");
                    int idEditSeller = editSeller.nextInt();

                    System.out.println("Nova Capacidade Máxima do Vendedor (kg): ");
                    float capacityEditSeller = editSeller.nextFloat();

                    empresa.editSeller(idEditSeller, capacityEditSeller);
                    break;
                case 2:
                    System.out.println("Editar Mercado");

                    Scanner marketEdit = new Scanner(System.in);

                    String oldName = printExistingMarkets(empresa);

                    System.out.println("Novo Nome para o Mercado: ");
                    String newName = marketEdit.nextLine();

                    empresa.editMarket(oldName, newName);
                    break;
                case 3:
                    System.out.println("Editar Armazém");

                    Scanner warehouse = new Scanner(System.in);

                    String nameWarehouse = this.printExistingWarehouses(empresa);

                    System.out.println("Nova Capacidade máxima do Armazém (kg): ");
                    float maxCapWarehouse = warehouse.nextFloat();

                    Scanner stockWarehouse = new Scanner(System.in);
                    float currentCapWarehouse;

                    System.out.println("Adicionar o Stock");

                    switch (this.choiceRandomOrManualMenu(stockWarehouse)) {
                        case 1:
                            System.out.println("Nova Capacidade atual do Armazém (kg): ");
                            currentCapWarehouse = warehouse.nextFloat();
                            break;
                        default:
                            //random
                            currentCapWarehouse = (float) (Math.random() * (maxCapWarehouse - 0)) + 0;
                            System.out.println("Capacidade atual do Armazém: " + currentCapWarehouse);
                            break;
                    }

                    empresa.editWarehouse(nameWarehouse, maxCapWarehouse, currentCapWarehouse);
                    break;
                case 4:
                    System.out.println("Editar Caminho");

                    Scanner places = new Scanner(System.in);

                    System.out.println("Selecionar o ponto de partida");
                    String startName = this.printExistingPlaces(empresa);

                    System.out.println("Selecionar o ponto de chegada");
                    String targetName = this.printExistingPlaces(empresa);

                    System.out.println("Nova Distancia (ex:\"1000\"): ");
                    float newWeight = places.nextFloat();

                    empresa.editRoute(startName, targetName, newWeight);
                    break;
                case 0:
                    System.out.println("Backing");

                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Sub menu que apresenta as opções de editar um vendedor, mercado, armazém
     * ou caminho.
     *
     * @param in
     * @return
     */
    private int editInfoMenu(Scanner in) {
        System.out.println("1 - Editar Vendedor\n2 - Editar Mercado" + "\n3 - Editar Armazém\n4 - Editar Caminho\n"
                + "0 - Back");
        System.out.print("Opção: ");

        int choice = in.nextInt();
        System.out.println();

        if (choice >= 0 && choice <= 4) {
            return choice;
        } else {
            System.out.println("Opção inválida!");

            return choice;
        }
    }

    /**
     * Executa opções como apresentar informação de um vendedor, mercado,
     * armazém ou caminho.
     *
     * @param in
     * @param empresa
     */
    private void printInfo(Scanner printo, Company empresa) {
        boolean exit = false;

        while (!exit) {
            switch (this.printInfoMenu(printo)) {
                case 1:
                    System.out.println("Imprimir Vendedor");
                    System.out.println(empresa.printSellers());
                    break;
                case 2:
                    System.out.println("Imprimir Mercado");
                    System.out.println(empresa.printMarket());
                    break;
                case 3:
                    System.out.println("Imprimir Armazéns");
                    System.out.println(empresa.printWarehouses());
                    break;
                case 4:
                    System.out.println("Imprimir Caminhos");
                    System.out.println(empresa.getCaminhos().getAdjList().toString());//preciso fazer uma cena para listar caminhos
                    break;
                case 0:
                    System.out.println("Backing");

                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Sub menu que apresenta as opções de apresentar informação de um vendedor,
     * mercado, armazém ou caminho.
     *
     * @param in
     * @return
     */
    private int printInfoMenu(Scanner in) {
        System.out.println("1 - Imprimir Vendedores\n2 - Imprimir Mercados" + "\n3 - Imprimir Armazéns\n"
                + "4 - Imprimir Caminhos\n0 - Back");
        System.out.print("Opção: ");

        int choice = in.nextInt();
        System.out.println();

        if (choice >= 0 && choice <= 4) {
            return choice;
        } else {
            System.out.println("Opção inválida!");

            return choice;
        }
    }

    /**
     * Executa opções como exportar informação de um vendedor, mercado, armazém
     * ou caminho.
     *
     * @param in
     * @param empresa
     */
    private void exportInfo(Scanner exporto, Company empresa) {
        boolean exit = false;

        while (!exit) {
            switch (this.exportInfoMenu(exporto)) {
                case 1:
                    System.out.println("Exportar Vendedor");
                    Iterator<Seller> iterSeller = empresa.getVendedores().iterator();

                    while (iterSeller.hasNext()) {
                        Seller next = iterSeller.next();
                        next.exportJSON();
                    }
                    break;
                case 2:
                    System.out.println("Exportar Mercado");
                    Iterator<Place> iterMarket = empresa.getLocais().iterator();

                    while (iterMarket.hasNext()) {
                        Place next = iterMarket.next();

                        if (next.getType().equals("Mercado")) {
                            next.exportJSON();
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exportar Armazéns");
                    Iterator<Warehouse> iterWarehouse = empresa.getWarehouses().iterator();

                    while (iterWarehouse.hasNext()) {
                        Place next = iterWarehouse.next();
                        next.exportJSON();
                    }
                    break;
                case 4:
                    System.out.println("Exportar Empresa");
                    empresa.export();
                    break;
                case 0:
                    System.out.println("Backing");

                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Sub menu que apresenta as opções de exportar informação de um vendedor,
     * mercado, armazém ou caminho.
     *
     * @param in
     * @return
     */
    private int exportInfoMenu(Scanner in) {
        System.out.println("1 - Exportar Vendedores\n2 - Exportar Mercados" + "\n3 - Exportar Armazéns\n"
                + "4 - Exportar Empresa\n0 - Back");
        System.out.print("Opção: ");
        int choice = in.nextInt();
        System.out.println();

        if (choice >= 0 && choice <= 4) {
            return choice;
        } else {
            System.out.println("Opção inválida!");

            return choice;
        }
    }

    /**
     * Método utilizado para apresentar o ID de um vendedor.
     *
     * @param empresa Empresa onde o vendedor trabalha.
     */
    private void printIdOfSeller(Company empresa) {
        UnorderedListADT<Seller> value = empresa.getVendedores();
        Iterator<Seller> iter = value.iterator();

        while (iter.hasNext()) {
            Seller temp = iter.next();
            System.out.println(temp.getId() + " --> " + temp.getNome());
        }
    }

    /**
     * Método utilizado para apresentar os mercados existentes de uma
     * determinada empresa.
     *
     * @param empresa Empresa que detém os mercados.
     * @return String com os mercados existentes.
     */
    private String printExistingMarkets(Company empresa) {
        UnorderedListADT<Place> value = empresa.getLocais();
        Iterator<Place> iter = value.iterator();
        int i = 0;

        while (iter.hasNext()) {
            Place temp = iter.next();

            if (temp.getType().equals("Mercado")) {
                System.out.println(i + " --> " + temp.getName());
            }

            i++;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Número do mercado (ex:\"2\"): ");
        int newI = scanner.nextInt();
        Iterator<Place> iter2 = value.iterator();
        int j = 0;

        while (iter2.hasNext()) {
            Place temp = iter2.next();
            //System.out.println(i + " --> " + temp.getName());

            if (j == newI) {
                return temp.getName();
            }

            j++;
        }

        return null;
    }

    /**
     * Método utilizado para apresentar os armazéns existentes de uma empresa.
     *
     * @param empresa Empresa que detém os armazéns.
     * @return String com os armazéns existentes.
     */
    private String printExistingWarehouses(Company empresa) {
        UnorderedListADT<Place> value = empresa.getLocais();
        Iterator<Place> iter = value.iterator();
        int i = 0;

        while (iter.hasNext()) {
            Place temp = iter.next();

            if (temp.getType().equals("Armazém")) {
                System.out.println(i + " --> " + temp.getName());
            }

            i++;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Numero do Armazém (ex:\"2\"): ");
        int newI = scanner.nextInt();

        Iterator<Place> iter2 = value.iterator();
        int j = 0;

        while (iter2.hasNext()) {
            Place temp = iter2.next();
            //System.out.println(i + " --> " + temp.getName());

            if (j == newI) {
                return temp.getName();
            }

            j++;
        }

        return null;
    }

    /**
     * Método utilizado para apresentar os locais relacionados com uma dada
     * empresa.
     *
     * @param empresa Empresa que detém os diferentes locais.
     * @return String com os locais que a empresa detém.
     */
    private String printExistingPlaces(Company empresa) {
        UnorderedListADT<Place> value = empresa.getLocais();
        Iterator<Place> iter = value.iterator();
        int i = 0;

        while (iter.hasNext()) {
            Place temp = iter.next();
            System.out.println(i + " --> " + temp.getName());
            i++;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Número do Local (ex:\"2\"): ");
        int newI = scanner.nextInt();

        Iterator<Place> iter2 = value.iterator();
        int j = 0;
        while (iter2.hasNext()) {
            Place temp = iter2.next();
            //System.out.println(i + " --> " + temp.getName());

            if (j == newI) {
                return temp.getName();
            }

            j++;
        }

        return null;
    }

    /**
     * Sub menu para apresentar se o utilizador quer introduzir informação
     * manualmente ou aleatoriamente.
     *
     * @param in
     * @return Opção escolhida.
     */
    private int choiceRandomOrManualMenu(Scanner in) {
        System.out.println("1 - Manualmente\n2 - Aleatoriamente" + "\n0 - Back");
        System.out.print("Opção: ");
        int choice = in.nextInt();
        System.out.println();

        if (choice >= 0 && choice <= 2) {
            return choice;
        } else {
            System.out.println("Opção inválida!");

            return choice;
        }
    }

    private void addClients(Market mercado) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Adicionar Clientes?\n\t1 - SIM\n\t2 - NÃO");
        int firstAnswer = scanner.nextInt();

        if (firstAnswer == 1) {
            switch (this.choiceRandomOrManualMenu(scanner)) {
                case 1:
                    //manual
                    boolean next = false;

                    while (!next) {
                        System.out.println("Adicionar Cliente");
                        float cliente = scanner.nextFloat();
                        mercado.addClient(cliente);
                        System.out.println("Adicionar Outro Cliente?\n\t1 - SIM\n\t2 - NÃO");
                        int secondAnswer = scanner.nextInt();

                        if (secondAnswer != 1) {
                            next = true;
                        }
                    }
                    break;
                default:
                    //random
                    int numberOfClients = (int) (Math.random() * (100 - 0)) + 0;

                    for (int i = 0; i < numberOfClients; i++) {
                        mercado.addClient((float) (Math.random() * (100 - 0)) + 0);
                    }

                    System.out.println("Clientes adicionados: " + numberOfClients);
                    break;
            }
        }
    }

    private void addMarkets(Seller vendedor, Company empresa) {
        if (empresa.getMarkets().isEmpty()) {
            System.out.println("Não existem mercados");
            //addmarket ??

            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Adicionar Mercados?\n\t1 - SIM\n\t2 - NÃO");
        int firstAnswer = scanner.nextInt();

        if (firstAnswer == 1) {
            //switch (this.choiceRandomOrManualMenu(scanner)) {
            //case 1:
            //manual
            boolean next = false;

            while (!next) {
                System.out.println("Adicionar Mercado a Vendedor");

                vendedor.addMarket(this.printExistingMarkets(empresa));
                System.out.println("Adicionar Outro Mercado?\n\t1 - SIM\n\t2 - NÃO");
                int secondAnswer = scanner.nextInt();

                if (secondAnswer != 1) {
                    next = true;
                }
            }
        }
    }

    /**
     * Método para importar ficheiros do tipo JSON.
     *
     * @param fileName Nome do ficheiro a importar.
     */
    private void importJSON(Scanner fileName) {
        File f = new File("./exportJSON/empresa"); // current directory

        File[] files = f.listFiles();
        int i = 0;

        for (File file : files) {
            if (!file.isDirectory() && file.getName().endsWith(".json")) {
                i++;

                System.out.println(i + "  " + file.getName());
            }
        }

        System.out.println("Intoduza o número do ficheiro a importar: ");

        int filePos = fileName.nextInt();
        String file = null;
        int j = 0;

        for (File file2 : files) {
            if (!file2.isDirectory() && file2.getName().endsWith(".json")) {
                j++;

                if (j == filePos) {
                    file = file2.getName();
                    break;
                }
            }
        }

        if (file == null) {
            System.out.println("Sem ficheiros");
            return;
        }

        System.out.println();

        Company empresa = Company.importCompany(file);
        boolean exit = false;

        while (!exit) {
            switch (companyMenu(fileName)) {
                case 1:
                    System.out.println("Adicionar Informação");
                    Scanner add = new Scanner(System.in);

                    this.addInfo(add, empresa);
                    break;
                case 2:
                    System.out.println("Editar Informação");
                    Scanner edit = new Scanner(System.in);

                    this.editInfo(edit, empresa);
                    break;
                case 3:
                    System.out.println("Imprimir Informação");
                    Scanner printo = new Scanner(System.in);

                    this.printInfo(printo, empresa);
                    break;
                case 4:
                    System.out.println("Exportar Informação");
                    Scanner exporto = new Scanner(System.in);

                    this.exportInfo(exporto, empresa);
                    break;
                case 5:
                    System.out.println("Start");
                    Scanner teste = new Scanner(System.in);
                    this.startTeste(empresa, teste);

                    break;
                case 0:
                    System.out.println("Exiting");
                    Scanner scanner = new Scanner(System.in);

                    System.out.println("Deseja guardar a empresa?\n\t1 - SIM\n\t2 - NÃO");
                    int firstAnswer = scanner.nextInt();

                    if (firstAnswer == 1) {
                        empresa.export();
                    }

                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }

    private void startTeste(Company empresa, Scanner teste) {
        //System.out.println(empresa.getVendedores().size());
        Iterator<Seller> iter = empresa.getVendedores().iterator();

        while (iter.hasNext()) {
            Seller vendedor = iter.next();
            Route rota = RouteUtils.generateRoute(empresa.getCaminhos(), vendedor, empresa.getLocais(), empresa);

            if (rota == null) {
                return;
            }

            System.out.println("Rota para o vendedor " + vendedor.getNome() + ":");
            //System.out.println("\tAmount of refills:" + rota.getAmountOfRefills());
            //System.out.println("\tAmount of clients not served by this seller:" + rota.getFailedClients());
            //System.out.println("\tDistancia total:" + rota.getTotalDistance() + "km");
            //System.out.println(rota.getRota().toString());
            //System.out.println("\t\tteste: " + rota.toString());

            String file = this.exportSellerResults(empresa, vendedor, rota);

            System.out.println("Resultados Guardados no ficheiro " + file);
        }
    }

    private String exportSellerResults(Company empresa, Seller vendedor, Route rota) {
        File file = new File("exportJSON/resultados/Company_" + empresa.getName() + "_" + vendedor.getNome() + ".json");
        file.getParentFile().mkdirs();

        try ( JsonWriter writer = new JsonWriter(new FileWriter(file))) {
            writer.setIndent("  ");
            writer.beginObject();
            writer.name("vendedor").value(vendedor.getNome());
            writer.name("refills").value(rota.getAmountOfRefills());
            writer.name("missed_clients").value(rota.getFailedClients());
            writer.name("total_distance").value(rota.getTotalDistance() + "km");

            writer.name("rota");
            writer.beginArray();
            Iterator<Place> iterator = rota.getRota().iterator();

            while (iterator.hasNext()) {
                Place place = iterator.next();
                writer.value(place.getName());
            }

            writer.endArray();

            writer.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return file.getCanonicalPath();
        } catch (IOException ex) {
            return file.getName();
        }
    }
}
