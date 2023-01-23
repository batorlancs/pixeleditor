package sprites.io;

import sprites.io.UI.MainUI;
import sprites.io.UI.mainMenu.MainMenu;

public class App
{
    public static void main( String[] args )
    {

        //MainWindow app = new MainWindow();
        //MainUI app = new MainUI();
        //app.CreateDisplay();

        MainMenu app = new MainMenu();
        app.createDisplay();

    }
}