/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import ADT.UnorderedListADT;
import Collections.DoubleLinkedList.DoubleLinkedUnorderedList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Tomás Pendão
 */
public class Menu {

    public static void start() {
        Menu menu = new Menu();

        menu.menu();
    }

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

    private int mainMenu(Scanner in) {

        System.out.println("1 - Criar uma Empresa\n2 - Importar um JSON"
                + "\n0 - Sair");
        System.out.print("Opção: ");
        int choice = in.nextInt();
        System.out.println();

        //int choice = in.nextInt();
        if (choice >= 0 && choice <= 2) {
            return choice;
        } else {
            System.out.println("Opção errada!");
            return choice;
        }
    }

    private void createCompany(Scanner name) {

        System.out.println("Nome da Empresa:");

        String companyName = name.nextLine();
        System.out.println();

        Company empresa = new Company(companyName);

        System.out.println("Nome da Empresa (\"Super Bock\"): " + empresa.getName());
        System.out.println("Place 1 : " + empresa.getLocais().first().getName());
        System.out.println("Place 1 : " + empresa.getLocais().first().getType());

        Scanner in = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            switch (companyMenu(in)) {
                case 1:
                    System.out.println("createSeller");

                    Scanner seller = new Scanner(System.in);
                    System.out.println("Nome do Vendedor (\"Manuel Rocha\"): ");
                    String nameSeller = seller.nextLine();
                    System.out.println("Capacidade Máxima do Vendedor (\"100\"): ");
                    float capacitySeller = seller.nextFloat();

                    Seller vendedor = new Seller(capacitySeller, nameSeller);
                    empresa.addSeller(vendedor);
                    System.out.println(empresa.printSellers());
                    break;
                case 2:
                    System.out.println("editSeller");

                    this.printIdOfSeller(empresa);
                    
                    Scanner editSeller = new Scanner(System.in);
                    System.out.println("Id do Vendedor: ");
                    int idEditSeller = editSeller.nextInt();
                    System.out.println("Nova Capacidade Máxima do Vendedor (\"100\"): ");
                    float capacityEditSeller = editSeller.nextFloat();

                    empresa.editSeller(idEditSeller, capacityEditSeller);
                    break;
                case 3:
                    System.out.println("createMarket");
                    break;
                case 4:
                    System.out.println("editMarket");
                    break;
                case 5:
                    System.out.println("createWarehouse");
                    break;
                case 6:
                    System.out.println("editWarehouse");
                    break;
                case 7:
                    System.out.println("createPath");
                    break;
                case 8:
                    System.out.println("editPath");
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

    private int companyMenu(Scanner in) {
        System.out.println("1 - Adicionar um Vendedor\n2 - Editar um Vendedor"
                + "\n3 - Adicionar um Mercado\n4 - Editar um Mercado\n5 - "
                + "Adicionar um Armazém\n6 - Editar um Armazém\n7 -"
                + " Adicionar um Caminho\n8 - Edotar um Caminho"
                + "\n0 - Back");
        System.out.print("Opção: ");
        int choice = in.nextInt();
        System.out.println();

        //int choice = in.nextInt();
        if (choice >= 0 && choice <= 8) {
            return choice;
        } else {
            System.out.println("Opção errada!");
            return choice;
        }
    }

    private void printIdOfSeller(Company empresa) {
        UnorderedListADT<Seller> value = empresa.getVendedores();
        Iterator<Seller> iter = value.iterator();
        while (iter.hasNext()) {
            Seller temp = iter.next();
            System.out.println(temp.getId() + " --> " + temp.getNome());
        }
    }
}
