import com.googlecode.lanterna.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.input.*;

public class Jogo_2048 {
    public Terminal term;
    Modelo modelo_jogo = new Modelo();
    public int[][] tabela = modelo_jogo.tabela;

    public Jogo_2048() {
        term = TerminalFacade.createTerminal();
        term.enterPrivateMode();
        term.getTerminalSize();

        modelo_jogo.inicio();
        modelo_jogo.numero_random();
        modelo_jogo.numero_random();
        System.out.println("Estado Inicial");
        modelo_jogo.print_matriz();
        while (true) {
            Key k = term.readInput();

            if (k != null) {
                switch (k.getKind()) {
                    case Escape:
                        term.exitPrivateMode();
                        return;
                    case ArrowLeft:
                        modelo_jogo.left();
                        break;
                    case ArrowRight:
                        modelo_jogo.right();
                        break;
                    case ArrowDown:
                        modelo_jogo.down();
                        break;
                    case ArrowUp:
                        modelo_jogo.up();
                        break;
                }
                if ( modelo_jogo.fim()) {
                    defeat();
                }
                modelo_jogo.diferença_do_anterior();
                modelo_jogo.print_matriz();
            }
            terminal_display();
            term.flush();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    //-----------------Terminal-lanterna-------------------------------------------
    private void terminal_display() {
        int x = 30;
        int y = 5;
        int n = 0;
        String s = "";
        for ( int i = 0 ; i < 4 ; i++) {
            show_x_table(x-2,y);
            show_y_table(x,y-2);
            x = x +12 ;
        }
        x = 30 ;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                color_table( i, j , x+3,y);
                term.moveCursor(x+3, y);
                s = s + tabela[i][j];
                term.putCharacter(s.charAt(n));
                if (tabela[i][j] == 16 || tabela[i][j] == 32 || tabela[i][j] == 64) {
                    term.putCharacter(s.charAt(n + 1));
                    n++;
                } else if (tabela[i][j] == 128 || tabela[i][j] == 256 || tabela[i][j] == 512) {
                    term.putCharacter(s.charAt(n + 1));
                    n++;
                    term.putCharacter(s.charAt(n + 1));
                    n++;

                } else if (tabela[i][j] == 1024 || tabela[i][j] == 2048) {
                    term.putCharacter(s.charAt(n + 1));
                    n++;
                    term.putCharacter(s.charAt(n + 1));
                    n++;
                    term.putCharacter(s.charAt(n + 1));
                    n++;

                }
                if (tabela[i][j] == 2048) {
                    victory();
                }
                x = x + 12;
                n++;
            }
            x = 30;
            y = y + 5;
            n = 0;
            s = "";
        }
        points();
        term.moveCursor(0,0);
    }

    private void show_x_table(int x1, int y1) {
        int z = x1 ;
        for ( int j = 0; j < 5; j++ ) {
            x1 = z ;
            for (int i = 0; i < 12; i++) {
                term.moveCursor(x1,y1-2);
                term.putCharacter('/');
                x1++;
            }
            y1= y1 + 5 ;
        }
    }

    private void show_y_table(int x1, int y1) {
        int z = y1;
        for (int j = 0; j < 2; j++) {
            y1 = z;
            for (int i = 0; i < 21; i++) {
                term.moveCursor(x1-2, y1);
                term.putCharacter('|');
                y1++;
            }
            x1 = x1 + 12;
        }
    }
    //-------------------------Cores dos numeros e da tabela --------------------------
    private void color( int i, int j ) {
        if (tabela[i][j] == 0) {
            term.applyBackgroundColor(Terminal.Color.WHITE);
            term.applyForegroundColor(Terminal.Color.WHITE);
        } else if (tabela[i][j] == 2) {
            term.applyBackgroundColor(Terminal.Color.BLUE);
            term.applyForegroundColor(Terminal.Color.BLUE);
        } else if (tabela[i][j] == 4) {
            term.applyBackgroundColor(Terminal.Color.RED);
            term.applyForegroundColor(Terminal.Color.RED);
        } else if (tabela[i][j] == 8) {
            term.applyBackgroundColor(Terminal.Color.GREEN);
            term.applyForegroundColor(Terminal.Color.GREEN);

        } else if (tabela[i][j] == 16) {
            term.applyBackgroundColor(Terminal.Color.YELLOW);
            term.applyForegroundColor(Terminal.Color.YELLOW);

        } else if (tabela[i][j] == 32) {
            term.applyBackgroundColor(Terminal.Color.CYAN);
            term.applyForegroundColor(Terminal.Color.CYAN);

        } else if (tabela[i][j] == 64) {
            term.applyBackgroundColor(Terminal.Color.MAGENTA);
            term.applyForegroundColor(Terminal.Color.MAGENTA);

        } else if (tabela[i][j] == 128) {
            term.applyBackgroundColor(255,128,0);
            term.applyForegroundColor(255,128,0);

        } else if (tabela[i][j] == 256) {
            term.applyBackgroundColor(255,51,123);
            term.applyForegroundColor(255,51,123);
        } else if (tabela[i][j] == 512) {
            term.applyBackgroundColor(102,102,0);
            term.applyForegroundColor(102,102,0);
        } else if (tabela[i][j] == 1024) {
            term.applyBackgroundColor(102,0,51);
            term.applyForegroundColor(102,0,51);
        } else if (tabela[i][j] == 2048) {
            term.applyBackgroundColor(0,51,0);
            term.applyForegroundColor(0,51,0);
        }
    }

    private void color_table(int i1 , int j1 , int x2 ,int y2) {
        for (int i = x2-4; i < x2+7 ; i++) {
            for (int j = y2 - 1; j < y2 + 3; j++) {
                term.moveCursor(i, j);
                term.putCharacter('.');
                color(i1,j1);

            }
        }
        term.moveCursor(x2-4,y2-1); // Prenche o 1º espaço , caso contrario aparece um ponto nesta posiçao
        term.putCharacter('.');
        term.applyForegroundColor(Terminal.Color.WHITE);
    }
    //---------------------------Pontuaçao , Derrota e Vitroia---------------------
    private void points(){
        term.applyBackgroundColor(Terminal.Color.BLACK);
        term.moveCursor(1, 9);
        String t = "Pontuacao -> ";
        for (int l = 0; l < t.length(); l++) {
            term.putCharacter(t.charAt(l));
        }
        term.moveCursor(14, 9);
        String k = "";
        k += modelo_jogo.points;
        term.putCharacter(k.charAt(0));
        if ( modelo_jogo.points <= 99  &&modelo_jogo.points >= 10) {
            term.putCharacter(k.charAt(1));
        }
        else if ( modelo_jogo.points > 99  && modelo_jogo.points <= 999) {
            term.putCharacter(k.charAt(1));
            term.putCharacter(k.charAt(2));
        }
    }

    private void defeat(){
        String p ;
        p = "Derrota :(";
        term.moveCursor(3, 10);
        for (int c = 0; c < p.length(); c++) {
            term.putCharacter(p.charAt(c));
        }
    }

    private void victory(){
        String p ;
        p = "Vitoria :D";
        term.moveCursor(3, 10);
        for (int c = 0; c < p.length(); c++) {
            term.putCharacter(p.charAt(c));
        }
    }

    //----------------------------------------Main---------------------------------
    public static void main(String[] args) {
        new Jogo_2048();
    }
}



