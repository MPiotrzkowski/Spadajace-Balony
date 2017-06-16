package Spadajace_Balony;

import javax.swing.JFrame;
import java.util.TimerTask;

/**
 * Klasa tworząca okno gry oraz modelująca działanie programu.
 * @author Mikołaj Piotrzkowski
 */
public class MenadzerGry {

    /**   Okno gry   */
    private JFrame okno;
    /**   Okno do zczytywania nazwy gracza   */
    private JFrame oknoNazwaGracza;


    /**
     * Główny konstruktor klasy - utworzenie nowego obiektu klasy OknoGry, MyTimerTask
     * oraz ustawienie początkowych parametrów.
     */
    public MenadzerGry() {
        okno = new OknoGry();       //Utwórz nowy obiekt klasy OknoGry.
        nowaGra();   //Wywołaj metodę ustawiającą początkowe parametry.
        java.util.Timer timer1 = new java.util.Timer();     //Utwórz nowy obiekt klasy Timer.
        MyTimerTask timer1_task = new MyTimerTask();        //Utwórz nowy obiekt klasy MyTimerTask.
        timer1.schedule(timer1_task, 0, 15);        //Ustaw parametry obiektu klasy Timer.
    }//koniec MenadzerGry()

    /**
     * Klasa wewnętrzna zawierająca pętlę gry oraz modelująca jej przebieg.
     * Klasa dziedzicząca po klasie TimerTask.
     */
    class MyTimerTask extends TimerTask {

            /**
             * Nadpisz metodę run, aby zawierała pętlę gry.
             */
            public void run () {

                //Zmieniaj działanie programu w zależności od wartości parametru stan gry.
                switch (Parametry.getStanGry()) {

                    case 0:     //Gra jest w stanie pauzy
                        okno.repaint();     //Przemaluj obiekt okno klasy OknoGry.
                        break;

                    case 1:     //Gra jest w trakcie normalnego przebiegu.

                        //Oblicz chwilowy czas rozgrywki i ustaw wartość parametru czas gry.
                        Parametry.setCzasGry(System.currentTimeMillis() - Parametry.getCzasPoczatkowy() - Parametry.getCzasPauzy());

                        //"Wylosuj" czy w obecnym wykonywaniu pętli gry ma się pojawić w grze nowy balon.
                        if (System.currentTimeMillis() % 1000 < 15) {
                            //Wywołaj metodę tworzącą nowy balon i wylosuj parametr determinujący kolor balonu.
                            PanelGry.nowyBalon(new Balon(Parametry.getObrazkiBalonow()[Parametry.getGenerator().nextInt(5)]));
                        }

                        //Pętla wykonująca się kolejno dla każdego balonu.
                        for (Balon balon : Parametry.getBalony()) {
                            balon.setY(balon.getY());   //Ustaw nowe położenie balonu.

                            //Czy przycisk myszy jest przyciśnięty i kursor jest w obrębie balonu?
                            if (Parametry.isCzyPrzycisniety() && balon.isCzyTrafiony()) {

                                //Czy szerokość balonu jest mniejsza od 25
                                if (balon.getSzerokosc() <= 25) {
                                    Parametry.getBalony().remove(balon);     //Usuń balon.
                                    break;
                                }
                                //Zmniejsz balon w zależności od parametru siła dmuchu (w symulacji parametr zależny od przyciśniętego przycisku myszy).
                                balon.zmniejszBalon(Parametry.getSilaDmuchu());
                            }

                            //Czy balon spadł na ziemię?
                            if (balon.getY() > 700) {
                                Parametry.getBalony().remove(balon);     //Usuń balon.
                                Parametry.setLiczbaZyc(Parametry.getLiczbaZyc() - 1);       //Zmniejsz parametr liczba żyć o jeden.

                                //Czy liczba żyć jest mniejsza lub równa zero?
                                if (Parametry.getLiczbaZyc() <= 0) {
                                    Parametry.setCzasKoncowy(System.currentTimeMillis());   //Ustaw parametr czas końcowy na aktualną liczbę milisekund która minęła od 1 Stycznia 1970r.
                                    Parametry.setCzasCalejGry(Parametry.getCzasKoncowy() - Parametry.getCzasPoczatkowy() - Parametry.getCzasPauzy());   //Oblicz czas całej gry.
                                    oknoNazwaGracza = new OknoNazwaGracza();    //Utwórz nowy obiekt klasy OknoNazwaGracza.
                                    Parametry.setStanGry(2);        //Ustaw parametr stan gry na 2 (gra się zakończyła).
                                }
                                break;
                            }
                        }//koniec for
                        okno.repaint();     //Przemaluj obiekt okno klasy OknoGry.
                        break;
                    case 2:     //Gra się zakończyła.
                        okno.repaint();     //Przemaluj obiekt okno klasy OknoGry.
                        break;

                }//koniec switch()
            }//koniec run()
    }//koniec class MyTimerTask


    /**
     * Metoda resetująca parametry gry.
     */
    public static void nowaGra(){
        Parametry.setLiczbaZyc(3);      //Ustaw parametr liczba zyć na wartość 3.
        Parametry.getBalony().clear();   //Usuń wszystkie balony.
        Parametry.setCzasPoczatkowy(System.currentTimeMillis());     //Ustaw parametr czas początkowy na aktualną liczbę milisekund która minęła od 1 Stycznia 1970r.
        Parametry.setCzasPauzy(0);  //Ustaw parametr czas pauzy na wartość 0.
        Parametry.setStanGry(1);    //Ustaw parametr stan gry na wartość 1. Ustawiona jest normalna pętla gry.
        Parametry.setWyswietlWyniki(false);     //Ustaw parametr wyświetl wyniki na wartość false.
    }//koniec nowaGra()
}//koniec class MenadzerGry