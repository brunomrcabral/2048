import java.util.Random;

public class Modelo{
    int [][] tabela = new int[4][4];
    int points = 0 ;
    int w = 0 ;
    int z = 0 ;

    public void inicio() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tabela[i][j] = 0;
            }
        }
    }

    //----------------------------------Jogadas------------------------------------
    public void down() {
        move();
        move();
        for (int j = 3; j >= 0; j--) {
            int soma = 0;
            for (int i = 2; i >= 0; i--) {
                if (tabela[i][j] == tabela[i + 1][j] && tabela[i][j] != 0 && soma <= 1) {
                    soma++;
                    tabela[i + 1][j] = tabela[i][j] * 2;
                    tabela[i][j] = 0;
                    points++;
                    if ( soma >= 1 ) {
                        w++;
                    }
                    if (i <= 2) {
                        i++;
                    }
                }
            }
            move();
        }
        move();
    }

    public void up(){
        inversao();
        down();
        inversao();
    }

    public void left() {
        rotate();
        up();
        rotate();
    }

    public void right() {
        rotate();
        down();
        rotate();
    }

    public void diferença_do_anterior() {
        if (z != 0 || w != 0 ){
            numero_random();
            z = 0 ;
            w = 0;
        }
    }

    //------------------------------Fim de jogo----------------------------------------
    public  boolean fim() {
        int prenchidos = 0 ;
        for ( int i = 0 ; i < 4 ; i++) {
            for ( int j = 0 ; j < 4 ; j++) {
                if ( tabela[i][j] != 0 ) {
                    prenchidos++;
                }
            }
        }
        if ( prenchidos == 16 ) {
            System.out.println("Derrota");
            return true;
        }
        else return false;
    }

    //---------------------------Print---------------------------------------------
    public void print_matriz(){
        for(int i=0 ; i<4 ;i++ ) {
            for (int j = 0; j < 4; j++) {
                System.out.print(tabela[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Pontuacao ----> " + points);
    }

    //--------------------------Geracao de numeros---------------------------------
    public void numero_random(){
        Random rn = new Random();
        int numero = 0 ,posicaox = 0  ,posicaoy = 0 ;
        while ( numero!=2 && numero != 4 ) {
            numero =rn.nextInt(5-2)+2;
            posicaox = rn.nextInt(4);
            posicaoy = rn.nextInt(4);
        }
        //System.out.println("Numero ---> " + numero +" x ---> " + posicaox + " y ----->" + posicaoy);
        verificar(posicaox,posicaoy,numero);
        // System.out.println("---> " +numero + "//// " + posicao);
    }

    private void verificar(int x , int y , int numero ){
        if ( tabela[x][y] != 0 ) {
            numero_random();
        }
        else tabela[x][y] = numero;
    }


    //-----------------------Manipulação da matriz---------------------------------
    private void move() {
        for ( int i = 3 ; i >= 1 ; i--) {
            for ( int j = 3 ; j >= 0 ; j--) {
                if ( tabela[i-1][j] != 0 && tabela[i][j] == 0 ) {
                    tabela[i][j] = tabela[i-1][j];
                    tabela[i-1][j] = 0 ;
                    z++;
                }
            }
        }
    }

    private void rotate() {
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

    private void inversao()  {
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
}
