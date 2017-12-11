
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
    public float[] s;

    Solucao(Dados d) {

        this.dados = d;
        int m = d.getM();
        int n = d.getN();

        x = new int[n + 1];
        y = new int[n + 1];
        Xp = new int[n + 1];
        Jp = new int[n];
        Ap = new int[n];
        Dp = new int[n];
        Cp = new int[n];
        Djp = new int[n];
        bJp = new boolean[n + 1];
        s = new float[m + 1];

        for (int i = 0; i < n; i++) {

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
        z = 100000000;
    }
    
    public int Xp(int i){
        
        return (y[i] == 1 ? 1 - Xp[i] : Xp[i]);
    }
    
    public void calculaZ(){
        
        z = dados.getC()[dados.getN()+1];
        for(int i = 1;i <= dados.getN(); i++){
            z = z + dados.getC()[i] * Xp(i);
        }
    }
    
    public void calculaS() {

        for (int i = 1; i <= dados.getM(); i++) {
            s[i] = 0;
            for (int j = 1; j <= dados.getN(); j++) {
                s[i] = s[i] + dados.getA()[i][j] * Xp[j];
            }
            s[i] = dados.getB()[i] - s[i];
        }
    }

    public float modulo(float valor) {

        return (valor < 0 ? -1 * valor : valor);
    }

    public float minimo(float valor) {

        return (valor < 0 ? valor : 0);
    }

    public void ajustarDados() {

        dados.getC()[dados.getN() + 1] = 0;

        for (int i = 1; i <= dados.getN(); i++) {
            if (dados.getC()[i] < 0) {
                dados.getC()[dados.getN() + 1] = dados.getC()[i] + dados.getC()[dados.getN() + 1];
                dados.getC()[i] = modulo(dados.getC()[i]);
                y[i] = 1;
            }
        }

        for (int i = 1; i <= dados.getM(); i++) {
            for (int j = 1; j <= dados.getN(); j++) {
                if (y[j] == 1) {
                    dados.getA()[i][j] = -1 * dados.getA()[i][j];
                    dados.getB()[i] = dados.getB()[i] + dados.getA()[i][j];
                }
            }
        }
    }

    public Dados getDados() {
        return dados;
    }

    public void setDados(Dados dados) {
        this.dados = dados;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public int[] getX() {
        return x;
    }

    public void setX(int[] x) {
        this.x = x;
    }

    public int[] getY() {
        return y;
    }

    public void setY(int[] y) {
        this.y = y;
    }

    public int[] getXp() {
        return Xp;
    }

    public void setXp(int[] Xp) {
        this.Xp = Xp;
    }

    public int[] getJp() {
        return Jp;
    }

    public void setJp(int[] Jp) {
        this.Jp = Jp;
    }

    public int[] getAp() {
        return Ap;
    }

    public void setAp(int[] Ap) {
        this.Ap = Ap;
    }

    public int[] getDp() {
        return Dp;
    }

    public void setDp(int[] Dp) {
        this.Dp = Dp;
    }

    public int[] getCp() {
        return Cp;
    }

    public void setCp(int[] Cp) {
        this.Cp = Cp;
    }

    public int[] getDjp() {
        return Djp;
    }

    public void setDjp(int[] Djp) {
        this.Djp = Djp;
    }

    public int getDlp() {
        return Dlp;
    }

    public void setDlp(int Dlp) {
        this.Dlp = Dlp;
    }

    public int getiJp() {
        return iJp;
    }

    public void setiJp(int iJp) {
        this.iJp = iJp;
    }

    public boolean[] getbJp() {
        return bJp;
    }

    public void setbJp(boolean[] bJp) {
        this.bJp = bJp;
    }

    public float[] getS() {
        return s;
    }

    public void setS(float[] s) {
        this.s = s;
    }
    
    

}
