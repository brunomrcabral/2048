/**
 * Created by bruno on 2/27/16.
 */

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.input.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Jogo_2048
{
    private Terminal term;
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int cursor_x=10, cursor_y=10;

    public Jogo_2048()
    {
        term = TerminalFacade.createTerminal();
        term.enterPrivateMode();

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

            show(dateString, cursor_x, cursor_y);

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

    private void show(String str, int x, int y)
    {
        term.moveCursor(x, y);

        int len = str.length();

        for (int i = 0; i < len; i++)
        {
            term.putCharacter(str.charAt(i));
        }
    }

    public static void main(String[] args)
    {
        new Jogo_2048();
    }
}
