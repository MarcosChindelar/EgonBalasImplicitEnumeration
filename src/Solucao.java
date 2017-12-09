/**
 *
 * @author Marcos
 */
public class Solucao {
    
    Dados dados;
    public float z;
    public int[] x;
    public int[] y;
    public int[] Xp;
    public int[] Jp;
    public int[] Ap;
    public int[] Dp;
    public int[] Cp;
    public int[] Djp;
    public int Dlp;
    public int iJp;
    public boolean[] bJp;
    
    Solucao(Dados d){
        
        this.dados = d;
        int m = d.getM();
        int n = d.getN();
        
        x = new int[n+1];
        y = new int[n+1];
        Xp = new int[n+1];
        Jp = new int[n];
        Ap = new int[n];
        Dp = new int[n];
        Cp = new int[n];
        Djp = new int[n];
        bJp = new boolean[n+1];
        
        for(int i = 0; i < n; i++){
            
            x[i] = 0;
            y[i] = 0;
            Xp[i] = 0;
            Jp[i] = 0;
            Ap[i] = 0;
            Dp[i] = 0;
            Cp[i] = 0;
            Djp[i] = 0;
            bJp[i] = true;       
        }
        
        x[n] = 0;
        y[n] = 0;
        Xp[n] = 0;
        bJp[n] = true;
        iJp = 0;
        z = Float.MAX_VALUE;
    }
    
    
    public float modulo(float valor){
    
        return (valor < 0 ? -1*valor : valor);
    }
    
    public float minimo(float valor){
    
        return (valor < 0 ? valor : 0);
    }
    
    public void ajustarDados(){
        
        dados.getC()[dados.getN()+1] = 0;
        
        for(int i = 1;i <= dados.getN(); i++){
            if(dados.getC()[i] < 0){
                dados.getC()[dados.getN()+1] =  dados.getC()[i] + dados.getC()[dados.getN()+1];
                dados.getC()[i] = modulo(dados.getC()[i]);
                y[i] = 1;
            }
        }
        
        for(int i = 1;i <= dados.getM(); i++){
            for(int j = 1;i <= dados.getN(); j++){
                if(y[j] == 1){
                    dados.getA()[i][j] = -1*dados.getA()[i][j];
                    dados.getB()[i] = dados.getB()[i] + dados.getA()[i][j]; 
                }             
            }
        }
    }
    
    
}
