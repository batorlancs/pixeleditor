package sprites.io;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import sprites.io.UI.MainUI;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void shouldShowMainWindow()
    {
        //MainWindow mainWindow = new MainWindow();
        //assertTrue( mainWindow != null );
        MainUI app = new MainUI();
        assertTrue( app != null );
    }

}
