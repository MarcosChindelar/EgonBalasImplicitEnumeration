
/**
 *
 * @author Marcos
 */
public class EgonBalas {

    Solucao atual;
    Solucao melhor;

    EgonBalas(Solucao s, Solucao s1) {

        atual = s;
        melhor = s1;

    }

    public void atualizaMelhor() {

        int n = atual.getDados().getN();
        for (int i = 1; i <= n; i++) {
            melhor.getXp()[i] = atual.getXp()[i];
        }
        melhor.setZ(atual.getZ());
    }

    public boolean criteriosDeParada() {

        int m = atual.getDados().getM();
        int n = atual.getDados().getN();
        boolean satisfeito = false;

        for (int i = 1; i <= m; i++) {
            if (atual.getS()[i] >= 0) {
                satisfeito = true;
            } else {
                satisfeito = false;
                break;
            }
        }
        if (satisfeito) {
            atual.calculaZ();
        } else {
            atual.setZ(Float.MAX_VALUE);
        }
        if (satisfeito) {
            if (atual.getZ() < melhor.getZ()) {
                atualizaMelhor();
            }
            return true;
        }

        for (int i = 1; i <= n; i++) {
            if ((atual.getbJp()[i]) && (atual.getXp()[i] == 0) && (atual.getZ() + atual.getDados().getC()[i] < melhor.getZ())) {
                satisfeito = false;
                break;
            } else {
                satisfeito = true;
            }
        }
        if (satisfeito) {
            return true;
        }

        float somatorio;
        for (int i = 1; i <= m; i++) {
            somatorio = 0;
            for (int j = 1; j <= n; j++) {
                somatorio += ((atual.getbJp()[j]) && (atual.getXp()[j] == 0) ? (atual.getDados().getA()[i][j] < 0 ? atual.getDados().getA()[i][j] : 0) : 0);
            }
            if (atual.getS()[i] - somatorio < 0) {
                return true;
            }

        }
        atual.setZ(Float.MAX_VALUE);
        return false;
    }

    public void enumerar() {

        int m = atual.getDados().getM();
        int n = atual.getDados().getN();

        for (int i = 0; i < n; i++) {
            atual.getCp()[i] = 0;
            atual.getAp()[i] = 0;
            atual.getDp()[i] = 0;
        }

        boolean inserir = false;
        int auxDp = 0;

        for (int k = 1; k <= n; k++) {
            if (atual.getXp()[k] == 0) {
                for (int i = 1; i <= m; i++) {
                    if (atual.getS()[i] < 0) {
                        if (atual.getDados().getA()[i][k] >= 0) {
                            inserir = true;
                        } else {
                            inserir = false;
                            break;
                        }
                    }
                }
                if (inserir) {
                    atual.getDp()[auxDp] = k;
                    auxDp++;
                }
            }
        }

        int iAp = 0;
        int iDp = 0;
        int iCp = 0;

        for (int i = 1; i <= n; i++) {
            if ((atual.getbJp()[i]) && (atual.getXp()[i] == 0)) {
                if (atual.getAp()[iAp] != i && atual.getDp()[iDp] != i) {
                    atual.getCp()[iCp] = i;
                    iCp = iCp + 1;
                }
                if (atual.getAp()[iAp] == i) {
                    iAp = iAp + 1;
                }
                if (atual.getDp()[iDp] == i) {
                    iDp = iDp + 1;
                }
            }
        }
    }

    public void escolheCandidato() {

        int m = atual.getDados().getM();
        int n = atual.getDados().getN();
        for (int i = 0; i < n; i++) {
            atual.getDjp()[i] = 0;
        }
        for (int j = 0; j < n; j++) {
            if (atual.getCp()[j] == 0) {
                break;
            }
            for (int i = 1; i <= m; i++) {
                atual.getDjp()[(atual.getCp()[j] - 1)] = (int) (atual.getDjp()[(atual.getCp()[j] - 1)] + minimo(atual.getS()[i] - atual.getDados().getA()[i][atual.getCp()[j]]));
            }
        }
        int auxDlp = 0;
        atual.setDlp((int) (-1 * Float.MAX_VALUE));
        for (int i = 0; i < n; i++) {
            if (atual.getCp()[i] == 0) {
                break;
            }
            if (atual.getDjp()[atual.getCp()[i - 1]] > atual.getDlp()) {
                auxDlp = atual.getCp()[i];
                atual.setDlp(atual.getDjp()[atual.getCp()[i] - 1]);
            }
        }
        atual.setDlp(auxDlp);
    }

    public float minimo(float valor) {

        return (valor < 0 ? valor : 0);
    }

    public float modulo(float valor) {

        return (valor < 0 ? -1 * valor : valor);
    }

    public void volta() {

        for (int i = atual.getiJp() - 1; i >= 0; i++) {
            if (atual.getJp()[i] < 0) {
                atual.getbJp()[(int) (modulo(atual.getJp()[i]))] = true;
                atual.getJp()[i] = 0;
                atual.setiJp(atual.getiJp() - 1);
            } else {
                atual.getXp()[atual.getJp()[i]] = 0;
                atual.getbJp()[atual.getJp()[i]] = false;
                atual.getJp()[i] = -1 * atual.getJp()[i];
                break;
            }
        }
    }
    
    public void Balas(){
        
        boolean voltar = false;
      
        atual.calculaS();
        
        if(criteriosDeParada()){
            return;
        }else{
            enumerar();
            escolheCandidato();
        }
        do{
            if(!voltar){
                atual.getXp()[atual.getDlp()] = 1;
                atual.getJp()[atual.getiJp()] = atual.getDlp();
                atual.setiJp(atual.getiJp()+1);
            }
            voltar = false;
            atual.calculaS();
            if(criteriosDeParada()){
                volta();
                voltar = true;
            }else{
                enumerar();
                escolheCandidato();
                if(atual.getCp()[0] == 0){
                    volta();
                    voltar = true;
                }
            }
            
        }while(atual.getJp()[0] != 0);
    }
}
