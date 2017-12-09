
import java.io.IOException;

/**
 *
 * @author Marcos
 */
public class Principal {

    public static void main(String[] args) throws IOException {
        
        Dados d = new Dados();
        d.leitura();
        Solucao s = new Solucao(d);
        
    }
    
}
