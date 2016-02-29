/**
 * Created by bruno on 2/27/16.
 */

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.input.*;


public class Jogo_2048{
    private Terminal term;
    public Jogo_2048(){
		
        term = TerminalFacade.createTerminal();
        term.enterPrivateMode();
		TerminalSize terminalSize = terminal.getTerminalSize();
		System.out.println(terminalSize);
		int random [] = new int [2];
		
        while (true)
        {
            Key k = term.readInput();
            if (k != null) {
                switch (k.getKind()) {
                    case Escape:
                        term.exitPrivateMode();
                        return;
                    case ArrowLeft:
                        cursor_x -= 1;
                        break;
                    case ArrowRight:
                        cursor_x += 1;
                        break;
                    case ArrowDown:
                        cursor_y += 1;
                        break;
                    case ArrowUp:
                        cursor_y -= 1;
                        break;
                }
            }

            term.clearScreen();

            String dateString = df.format(new Date());
            term.applySGR(Terminal.SGR.ENTER_BOLD);
            term.applyForegroundColor(Terminal.Color.GREEN);
			if ( terminalSize > cursor x && terminalSize > cursor y ) {
				show( cursor_x, cursor_y);
			}
            term.flush();

            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }
        }
    }

    private void show( int x, int y)
    {
        term.moveCursor(x, y);

        int len = str.length();

    }

    public static void main(String[] args)
    {
        new Jogo_2048();
    }
}
