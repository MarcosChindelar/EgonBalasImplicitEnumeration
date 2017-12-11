
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
        Solucao s1 = new Solucao(d);
        s.ajustarDados();
        s1.ajustarDados();
        EgonBalas eg = new EgonBalas(s,s1);
        eg.Balas();
        eg.atualizar();
        eg.imprime();
        
        
    }
    
}
