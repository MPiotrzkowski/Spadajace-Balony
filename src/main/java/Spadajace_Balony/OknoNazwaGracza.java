package Spadajace_Balony;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * Okno używane do zczytywania nazwy gracza.
 * Klasa dziedzicząca po klasie JFrame.
 * @author Mikołaj Piotrzkowski
 */
public class OknoNazwaGracza extends JFrame {

    /**  Panel   */
    JPanel panel;
    /**  Etykieta z instrukcją dla użytkownika   */
    final JLabel napis = new JLabel("Podaj nazwę gracza i wciśnij enter:");
    /**  Pole tekstowe przeznaczone na nazwę gracza   */
    final JTextField poleTekstowe = new JTextField(30);


    /**
     * Główny konstruktor klasy. Utworzenie i dodanie panelu do okna oraz ustawienie parametrów.
     */
    public OknoNazwaGracza(){
        panel = new JPanel();   //Utwórz nowy obiekt klasy JPanel.
        panel.add(napis);   //Dodaj etykietę do panelu.
        panel.add(poleTekstowe);    //Dodaj pole tekstowe do panelu.
        add(panel);     //Dodaj panel do okna.

        setSize(380, 70);      //Ustaw rozmiar okna.
        setResizable(false);    //Zablokuj możliwość zmiany rozmiaru.
        setUndecorated(true);   //Ukryj ramkę okna i przyciski kontrolne.
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - 380)/2, (Toolkit.getDefaultToolkit().getScreenSize().height - 70)/2);      //Ustaw pozycję okna.
        setVisible(true);   //Ustaw widoczność okna.


        /* Dodaj obsługę zdarzeń - wciśnięcie entera*/
        poleTekstowe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Parametry.setNazwaUzytkownika(poleTekstowe.getText());      //Ustaw parametr nazwa użytkownika na wartość wpisaną w polu tekstowym.
                try {
                    //Ustaw parametr wynik.
                    Parametry.setWynik(Long.toString(Parametry.getCzasCalejGry()) + ":" + Long.toString(Parametry.getCzasKoncowy()) + "*" + poleTekstowe.getText());
                    //Zapisz parametr wynik do pliku.
                    Parametry.zapisDoPliku(Parametry.getWynik());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                setVisible(false);  //Ustaw niewidoczność okna.
            }
        });
    }//koniec OknoNazwaGracza()
}//koniec class OknoNazwaGracza