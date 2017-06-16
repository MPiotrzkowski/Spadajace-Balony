package Spadajace_Balony;

import java.awt.EventQueue;


/**
 * Gra "Spadające Balony", jako symulacja programu na platformę eDmuchawka.
 * @author Mikołaj Piotrzkowski
 */
public class App 
{
    /**
     * Metoda uruchamia grę. Tworzony jest nowy obiekt klasy MenadzerGry.
     * @param args
     */
    public static void main( String[] args ) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenadzerGry();
            }
        });
    }
}//koniec class App()