import java.util.*;
import java.io.*;
import java.util.Random.*;

public class Jogo_2048 {
    private  int pontuacao = 0;
    public  Jogo_2048(){
        Scanner ler = new Scanner(System.in);
        int[][] tabela = new int [4][4];
        int prenchidos = 0;
        int xi = 0;
        for(int i=0 ; i<4 ;i++ ){
            for(int j=0 ; j<4 ; j++){
                tabela[i][j] = 0;
            }
        }
        tabela[3][0] = 2 ;
        tabela[3][1] = 2 ;
        tabela[3][2] = 2 ;
        tabela[3][3] = 2 ;
        numero_random(tabela);
        numero_random(tabela);
        System.out.println("Estado Inicial");
        print_matriz(tabela);

        while ( xi != 1 ) {
            System.out.println("1 ---- Cima");
            System.out.println("2 ---- Baixo");
            System.out.println("3 ---- Esquerda");
            System.out.println("4 ---- Direita");
            int mov = ler.nextInt();
            switch(mov) {
                case 1:
                    cima(tabela);
                    break;
                case 2:
                    baixo(tabela);
                    break;
                case 3:
                    esquerda(tabela);
                    break;
                case 4:
                    direita(tabela);
                    break;

            }
            numero_random(tabela);
            prenchidos = 0 ;
            System.out.println("Jogada -------------");
            print_matriz(tabela);
            for(int i=0 ; i<4 ;i++ ){
                for(int j=0 ; j<4 ; j++) {
                    if (tabela[i][j] == 2048) {
                        xi = 1;
                        System.out.println("Vitoria");
                    } else {
                        if (tabela[i][j] != 0) {
                            prenchidos++;
                        }
                    }
                }
            }
            if ( prenchidos == 16 ) {
                System.out.println("You Lose");
                xi = 1 ;
            }
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    private void print_matriz(int [][]tabela){
            for(int i=0 ; i<4 ;i++ ) {
                for (int j = 0; j < 4; j++) {
                    System.out.print(tabela[i][j] + " ");
                }
                System.out.println();
            }
           System.out.println("Pontuacao ----> " + pontuacao);
    }
//---------------------------------------------------------------------------------------------------------------------------
    private void numero_random(int [][] tabela){
        Random rn = new Random();
        int numero = 0 ,posicaox = 0  ,posicaoy = 0 ;
        while ( numero!=2 && numero != 4 ) {
            numero =rn.nextInt(5-2)+2;
            posicaox = rn.nextInt(4);
            posicaoy = rn.nextInt(4);
        }
        //System.out.println("Numero ---> " + numero +" x ---> " + posicaox + " y ----->" + posicaoy);
        verificar(posicaox,posicaoy,tabela,numero);
        // System.out.println("---> " +numero + "//// " + posicao);

    }

    private void verificar(int x , int y , int[][] tabela , int numero ){
        if ( tabela[x][y] != 0 ) {
            numero_random(tabela);
        }
        else tabela[x][y] = numero;
    }

//----------------------------------------------------------------------------------------------------------------------
    private void baixo(int [][] tabela){
        move(tabela);
        move(tabela);
        for ( int j = 0 ; j <= 3 ; j++) {
            int soma = 0;
            for ( int i= 0 ; i <= 2 ; i++) {
                if ( tabela[i][j] == tabela[i+1][j] && tabela[i][j] != 0 && soma != 2  ){
                    soma++;
                    tabela[i+1][j] = tabela[i][j]*2;
                    tabela[i][j] = 0  ;
                    pontuacao++;

                }

            }
            move(tabela);
        }
        move(tabela);
    }

    private void cima( int [][] tabela){
        inversao(tabela);
        baixo(tabela);
        inversao(tabela);
    }


    private void esquerda(int[][] tabela ) {
        rotate(tabela);
        cima(tabela);
        rotate(tabela);
    }

    private void direita(int[][] tabela) {
        rotate(tabela);
        baixo(tabela);
        rotate(tabela);
    }

    private void rotate(int [][]tabela) {
        int tmp[][] = new int [4][4];
        for ( int i = 0; i < 4 ; i++) {
            for( int j = 0; j < 4 ; j++) {
                tmp[i][j] = tabela[i][j];
            }
        }
        for ( int i = 3 ; i >=0 ; i--) {
            for ( int j = 0 ; j < 4  ; j++) {
                tabela[i][j] = tmp[j][i];
            }
        }
    }

    private void inversao( int [][] tabela )  {
        int tmp[][] = new int [4][4];
        for ( int i = 0; i < 4 ; i++) {
            for( int j = 0; j < 4 ; j++) {
                tmp[i][j] = tabela[i][j];
            }
        }
        for ( int i = 0 ; i < 4 ; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 0) {
                    tabela[3][j] = tmp[0][j];
                }
                if (i == 3) {
                    tabela[0][j] = tmp[3][j];
                }
                if (i == 1) {
                    tabela[2][j] = tmp[1][j];
                }
                if (i == 2) {
                    tabela[1][j] = tmp[2][j];
                }
            }
        }
    }

    private void move( int [][] tabela ) {
        for ( int i = 3 ; i >= 1 ; i--) {
            for ( int j = 3 ; j >= 0 ; j--) {
                if ( tabela[i-1][j] != 0 && tabela[i][j] == 0 ) {
                    tabela[i][j] = tabela[i-1][j];
                    tabela[i-1][j] = 0 ;
                }
            }
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        new Jogo_2048();
    }
}
