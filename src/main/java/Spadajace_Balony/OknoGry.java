package Spadajace_Balony;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;


/**
 * Okno główne gry.
 * Klasa dziedzicząca po klasie JFrame.
 * @author Mikołaj Piotrzkowski
 */
public class OknoGry extends JFrame  {

    /**  Panel gry   */
    private static JPanel panel;

    /**
     * Główny konstruktor klasy. Utworzenie i dodanie panelu gry do okna oraz ustawienie parametrów.
     */
    public OknoGry() {
        super("Spadające Balony");      //Wywołaj konstruktor klasy nadrzędnej z parametrem determinującym nazwę okna.

        panel = new PanelGry();     //Utwórz nowy panel gry.
        add(panel);     //Dodaj utworzony panel do okna.
        setBackground(Color.black);     //Ustaw kolor tła na czarny.
        setIconImage(Parametry.getTlo());       //Ustaw ikonę okna na obrazek używany jako tło gry.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //Zakończ aplikację w przypadku zamknięcia okna.
        setSize(Parametry.getSzerokoscOkna(), Parametry.getWysokoscOkna());     //Ustaw wymiary okna.
        setLocation(Parametry.getWspolrzednaXOkna(), Parametry.getWspolrzednaYOkna());      //Ustaw pozycję okna.
        setResizable(false);    //Zablokuj możliwość zmiany rozmiarów okna.
        setVisible(true);   //Ustaw widoczność okna.
    }//koniec OknoGry()
}//koniec class OknoGry