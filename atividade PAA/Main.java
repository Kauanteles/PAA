import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Principal {
  public static void main(String[] args) {
    
    int[][] dimensoes = lerDimensoesDoArquivo("matrizes.txt");

    int n = dimensoes.length; 

    int[][] m = new int[n][n]; 

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        m[i][j] = Integer.MAX_VALUE;
      }
    }

    int minimoMultiplicacoes = numMultMin(dimensoes, 0, n - 1, m);
    System.out.println("Número mínimo de multiplicações: " + minimoMultiplicacoes);
  }

  public static int[][] lerDimensoesDoArquivo(String nomeArquivo) {
    int[][] dimensoes = null;

    try {
      File arquivo = new File(nomeArquivo);
      Scanner scanner = new Scanner(arquivo);

      int n = scanner.nextInt(); 
      dimensoes = new int[n][2]; 

      for (int i = 0; i < n; i++) {
        int linhas = scanner.nextInt();
        int colunas = scanner.nextInt(); 
        dimensoes[i][0] = linhas;
        dimensoes[i][1] = colunas;
      }

      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return dimensoes;
  }

  public static int numMultMin(int[][] d, int i, int j, int[][] m) {
    if (m[i][j] < Integer.MAX_VALUE) {
      return m[i][j];
    }

    if (i == j) {
      m[i][j] = 0;
    } else {
      for (int k = i; k < j; k++) {
        int q = numMultMin(d, i, k, m) + numMultMin(d, k + 1, j, m)
            + d[i][0] * d[k + 1][0] * d[j][1];
        if (q < m[i][j]) {
          m[i][j] = q;
        }
      }
    }

    return m[i][j];
  }
}
