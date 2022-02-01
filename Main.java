
import API.Menu;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Epoca Normal ED Daniel Pinto 8200412 Tomás Pendão 8170308
 */
/**
 * Classe que irá invocar um menu. É o ponto de partida do programa.
 *
 * @author Tomás Pendão
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /**
         * To-Do Acabar os tests //done meter o.jar a dar?
         *
         * Melhorar os imports e os prints fazer os metodos das routes e
         * adicionar essas opções ao menu
         *
         */
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        //mostra os nomes dos ficheiros .json de export da empresa

        File file = new File("./exportJSON/empresa");//Creating the directory
        File file2 = new File("./exportJSON/vendedor");//Creating the directory
        File file3 = new File("./exportJSON/mercado");//Creating the directory
        File file4 = new File("./exportJSON/armazem");//Creating the directory

        Menu.start();

    }
}
