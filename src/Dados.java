
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Marcos
 */
public class Dados {

    public int n;// Numero de Variaveis    
    public int m;// Numero de Restrições
    public float[][] a;//Matriz a[m][n] de coeficientes das variaveis
    public float[] b;//Matriz b[m] de coeficientes do lado direito das restrições
    public float[] c;//Matriz c[n] de coeficientes das variaveis da função objetivo

    public void leitura() throws FileNotFoundException, IOException {

        File arquivo = new File("entrada.txt");
        FileReader arq = new FileReader(arquivo);
        BufferedReader leitor = new BufferedReader(arq);
        String linha;
        String aux[];

        linha = leitor.readLine();
        aux = linha.split(" ");
        m = Integer.parseInt(aux[0]) - 1; // Descontar a linha da função objetivo.
        n = Integer.parseInt(aux[1]);

        a = new float[m + 1][n + 1];
        b = new float[m + 1];
        c = new float[n + 2];

        c[0] = -1;//Minimização

        a[0][0] = 0;
        b[0] = 0;

        linha = leitor.readLine();
        aux = linha.split("\t");
        for (int i = 1, j = 0; i <= n; i++, j++) {
            c[i] = Float.parseFloat(aux[j]);
        }

        for (int i = 1, k = 0; i <= m; i++, k++) {
            a[i][0] = 0;
            linha = leitor.readLine();
            aux = linha.split("\t");
            for (int j = 1, l = 0; j <= n; j++, l++) {
                a[i][j] = Float.parseFloat(aux[l]);
            }
            b[i] = Float.parseFloat(aux[n]);
        }

        c[n + 1] = 0;

        imprimeA();
        imprimeB();
        imprimeC();
    }

    public void imprimeA() {

        System.out.printf("Matriz a[%d][%d] \n", m, n);
        System.out.print(" ");
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(a[i][j] + "   ");
            }
            System.out.print("\n ");
        }
        System.out.print("\n");
    }

    public void imprimeB() {

        System.out.printf("Matriz b[%d]\n", m);
        for (int i = 1; i < b.length; i++) {
            System.out.print(b[i] + "   ");
        }
        System.out.print("\n");
    }

    public void imprimeC() {

        System.out.print("\n");
        System.out.printf("Matriz c[%d]\n", n);
        for (int i = 1; i < c.length - 1; i++) {
            System.out.print(c[i] + "   ");
        }
        System.out.print("\n");
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public float[][] getA() {
        return a;
    }

    public void setA(float[][] a) {
        this.a = a;
    }

    public float[] getB() {
        return b;
    }

    public void setB(float[] b) {
        this.b = b;
    }

    public float[] getC() {
        return c;
    }

    public void setC(float[] c) {
        this.c = c;
    }

}
