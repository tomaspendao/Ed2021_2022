
/**
 * Epoca Normal ED
 * Daniel Pinto 8200412
 * Tomás Pendão 8170308
 */
import API.Menu;
import java.io.File;
import java.io.IOException;

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

        File file = new File("./exportJSON/empresa");//Creating the directory
        file = new File("./exportJSON/vendedor");//Creating the directory
        file = new File("./exportJSON/mercado");//Creating the directory
        file = new File("./exportJSON/armazem");//Creating the directory

        Menu.start();
    }
}
