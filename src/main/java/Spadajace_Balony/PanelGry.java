package Spadajace_Balony;

import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.io.IOException;


/**
 * Główny panel gry.
 * Klasa dziedzicząca po klasie JPanel.
 * @author Mikołaj Piotrzkowski
 */
public class PanelGry extends JPanel implements MouseListener, MouseMotionListener {


    /**
     * Główny konstruktor klasy. Wczytywanie plików
     * graficznych oraz dodanie obsługi zdarzeń.
     */
    public PanelGry(){
        Parametry.wczytajObrazki();     //Wywołaj metodę wczytującą pliki graficzne.
        addMouseListener(this);     //Dodaj nasłuchiwanie przycisków myszy.
        addMouseMotionListener(this);   //Dodaj nasłuchiwanie ruchów myszy.
    }//koniec PanelGry()


    /**
     * Metoda dodająca nowy obiekt klasy Balon do listy.
     * @param balon obiekt klasy Balon
     */
    public static void nowyBalon(Balon balon){
        Parametry.getBalony().add(balon);
    }//koniec nowyBalon()


    /**
     * Nadpisz metodę odpowiedzialną za odrysowanie panelu
     * (własne wypełnienie pola graficznego gry).
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //Narysuj tło.
        g2d.drawImage(Parametry.getTlo(), 0, 0, this);

        //Narysuj każdy obiekt przechowywany w liście obiektów klasy Balon.
        for (Balon balon : Parametry.getBalony()) {
            g2d.drawImage(balon.getObrazek(), balon.getX(), balon.getY(), balon.getSzerokosc(), balon.getWysokosc(), this);
        }//koniec foreach

        //Rysuj różne elementy w zależności od parametru stan gry.
        switch (Parametry.getStanGry()) {
            case 0:     //Gra jest w stanie pauzy
                //Narysuj przycisk nowa gra.
                g2d.drawImage(Parametry.getNowaGra(), 94, 905, this);
                //Narysuj przycisk wróc do gry.
                g2d.drawImage(Parametry.getWrocDoGry(), 365, 905, this);
                //Narysuj przycisk najlepsze wyniki.
                g2d.drawImage(Parametry.getNajlepszeWyniki(), 669, 905, this);
                //Narysuj przycisk zakończ grę.
                g2d.drawImage(Parametry.getZakonczGre(), 1007, 905, this);

                //Czy wyświetlić wyniki?
                if (Parametry.isWyswietlWyniki()){
                    //Ustaw czcionkę.
                    g2d.setFont(Parametry.getNajlepszeWynikiNapis());
                    //Ustaw biały kolor napisu.
                    g2d.setColor(Color.white);
                    g2d.drawString("NAJLEPSZE WYNIKI", 380, 100);
                    //Ustaw czcionkę.
                    g2d.setFont(Parametry.getNajlepszeWynikiCzasDataGracz());
                    g2d.drawString("CZAS", 170, 200);
                    g2d.drawString("DATA", 450, 200);
                    g2d.drawString("GRACZ", 900, 200);

                    //Wypisz dziewięć najlepszych wyników.
                    for(int i=0; i<=8; i++){
                        //Narysuj numer miejsca.
                        g2d.drawString(String.valueOf(i+1), 50, 250+50*i);
                        //Narysuj czas danego wyniku.
                        g2d.drawString(Parametry.wypiszCzas(i), 170, 250+50*i);
                        //Narysuj datę danego wyniku.
                        g2d.drawString(Parametry.wypiszDate(i), 450, 250+50*i);
                        //Narysuj nazwę gracza danego wyniku.
                        g2d.drawString(Parametry.wypiszImie(i), 900, 250+50*i);
                    }//koniec for
                }// koniec if
                break;
            case 1:      //Gra jest w trakcie normalnego przebiegu.
                //Narysuj przycisk menu.
                g2d.drawImage(Parametry.getMenu(), 230, Parametry.getWysokoscOkna() - 128, this);
                //Narysuj pole do wyświetlenia czasu.
                g2d.drawImage(Parametry.getCzas(), 730, Parametry.getWysokoscOkna() - 128, this);
                //Ustaw czcionkę.
                g2d.setFont(Parametry.getCzasCzcionka());
                //Ustaw czarny kolor napisu.
                g2d.setColor(Color.black);
                //Narysuj aktualny czas gry.
                g2d.drawString(Parametry.przetworzCzas(Parametry.getCzasGry()),880,943);
                break;
            case 2:     //Gra się zakończyła.
                //Ustaw czcionkę.
                g2d.setFont(Parametry.getKoniecGryCzcionka());
                //Ustaw czerwony kolor napisu.
                g2d.setColor(Color.red);

                //Czy wyświetlić wyniki?
                if (Parametry.isWyswietlWyniki()){
                    //Ustaw czcionkę.
                    g2d.setFont(Parametry.getNajlepszeWynikiNapis());
                    //Ustaw biały kolor napisu.
                    g2d.setColor(Color.white);
                    g2d.drawString("NAJLEPSZE WYNIKI", 380, 100);
                    //Ustaw czcionkę.
                    g2d.setFont(Parametry.getNajlepszeWynikiCzasDataGracz());
                    g2d.drawString("CZAS", 170, 200);
                    g2d.drawString("DATA", 450, 200);
                    g2d.drawString("GRACZ", 900, 200);

                    //Wypisz dziewięć najlepszych wyników.
                    for(int i=0; i<=8; i++){
                        //Narysuj numer miejsca.
                        g2d.drawString(String.valueOf(i+1), 50, 250+50*i);
                        //Narysuj czas danego wyniku.
                        g2d.drawString(Parametry.wypiszCzas(i), 170, 250+50*i);
                        //Narysuj datę danego wyniku.
                        g2d.drawString(Parametry.wypiszDate(i), 450, 250+50*i);
                        //Narysuj nazwę gracza danego wyniku.
                        g2d.drawString(Parametry.wypiszImie(i), 900, 250+50*i);
                    }//koniec for
                }//koniec if
                else{
                    g2d.drawString("KONIEC GRY", 320, 250);
                    //Ustaw czcionkę.
                    g2d.setFont(Parametry.getCzasKoncowyCzcionka());
                    //Narysuj końcowy czas gry.
                    g2d.drawString("TWÓJ CZAS: " + Parametry.przetworzCzas(Parametry.getCzasCalejGry()), 370, 370);
                }//koniec else

                //Narysuj przycisk nowa gra.
                g2d.drawImage(Parametry.getNowaGra(), 94, 905, this);
                //Narysuj przycisk wróc do gry.
                g2d.drawImage(Parametry.getWrocDoGry(), 365, 905, this);
                //Narysuj przycisk najlepsze wyniki.
                g2d.drawImage(Parametry.getNajlepszeWyniki(), 669, 905, this);
                //Narysuj przycisk zakończ grę.
                g2d.drawImage(Parametry.getZakonczGre(), 1007, 905, this);
                break;

        }//koniec switch

        //Ustaw czcionkę.
        g2d.setFont(Parametry.getCzasCzcionka());
        //Ustaw czerwony kolor napisu.
        g2d.setColor(Color.red);
        //Narysuj pozostałą liczbę żyć w rogu okna.
        g2d.drawString("Liczba żyć: "+String.valueOf(Parametry.getLiczbaZyc()), 1040, 850);
    }//koniec paintComponent()



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Nadpisz metodę odpowiedzialną reakcję
     * programu na przyciśnięcie przycisku myszy.
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {

        //Działaj róznie w zależności od parametru stan gry.
        switch (Parametry.getStanGry()) {
            case 0:     //Gra jest w stanie pauzy
                //Czy przyciśnięto przycisk nowa gra?
                if ((e.getX() > 94 && e.getY() > 905 && e.getX() < 232 && e.getY() < 945)) {
                    MenadzerGry.nowaGra();      //Wywołaj funkcję resetującą parametry gry.
                }//koniec if

                //Czy przyciśnięto przycisk wróc do gry?
                if ((e.getX() > 365 && e.getY() > 905 && e.getX() < 533 && e.getY() < 945)) {
                    Parametry.setStanGry(1);    //Ustaw stan gry na wartość 1.
                    Parametry.setCzasKoncaPauzy(System.currentTimeMillis());       //Ustaw parametr czas końca pauzy na aktualną liczbę milisekund która minęła od 1 Stycznia 1970r.
                    Parametry.setCzasPauzy(Parametry.getCzasPauzy() + Parametry.getCzasKoncaPauzy() - Parametry.getCzasPoczatkuPauzy());    //Oblicz i ustaw parametr czas pauzy.
                    Parametry.setWyswietlWyniki(false);     //Ustaw parametr wyświetl wyniki na wartość false.
                }//koniec if

                //Czy przyciśnięto przycisk najlepsze wyniki?
                if ((e.getX() > 669 && e.getY() > 905 && e.getX() < 879 && e.getY() < 945)) {
                    Parametry.setWyswietlWyniki(true);      //Ustaw parametr wyświetl wyniki na wartość true.
                    try {
                        Parametry.odczytZPliku();   //Wywołaj metodę odczytującą wyniki z pliku tekstowego.
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }//koniec if

                //Czy przyciśnięto przycisk koniec gry?
                if ((e.getX() > 1007 && e.getY() > 905 && e.getX() < 1169 && e.getY() < 945)) {
                    System.exit(0);     //Zakończ aplikację.
                }//koniec if
                break;

            case 1:     //Gra jest w trakcie normalnego przebiegu.
                //Dla każdego obiektu w liście obiektów klasy Balon.
                for (Balon balon : Parametry.getBalony()) {
                    //Czy kursor myszy jest w obrębie balonu?
                    if ((e.getX() > balon.getX() && e.getY() > balon.getY() && e.getX() < balon.getX() + balon.getSzerokosc() && e.getY() < balon.getY() + balon.getWysokosc())) {
                        balon.setCzyTrafiony(true);     //Ustaw parametr balonu czy trafiony na wartość true.
                        break;
                    }//koniec if
                }// koniec foreach
                Parametry.setCzyPrzycisniety(true);     //Ustaw parametr czy przyciśnięty przycisk myszy na wartość true.
                //Czy przyciśnięty był lewy przycisk myszy?
                if (e.getButton() == MouseEvent.BUTTON1){
                    Parametry.setSilaDmuchu(30);       //Ustaw parametr siła dmuchu symulowanej eDmuchawki na wartość 30.
                }//koniec if
                //Czy przyciśnięty był prawy przycisk myszy?
                if (e.getButton() == MouseEvent.BUTTON3){
                    Parametry.setSilaDmuchu(70);       //Ustaw parametr siła dmuchu symulowanej eDmuchawki na wartość 70.
                }//koniec if

                //Czy przyciśnięto przycisk menu?
                if ((e.getX() > 230 && e.getY() > Parametry.getWysokoscOkna() - 128 && e.getX() < 398 && e.getY() < Parametry.getWysokoscOkna() - 62)) {
                    Parametry.setStanGry(0);    //Ustaw parametr stan gry na 0 (stan pauzy).
                    Parametry.setCzasPoczatkuPauzy(System.currentTimeMillis());     //Ustaw parametr czas początku pauzy na aktualną liczbę milisekund która minęła od 1 Stycznia 1970r.
                }//koniec if
                break;

            case 2:     //Gra się zakończyła.
                //Czy przyciśnięto przycisk nowa gra?
                if ((e.getX() > 94 && e.getY() > 905 && e.getX() < 232 && e.getY() < 945)) {
                    MenadzerGry.nowaGra();      //Wywołaj funkcję resetującą parametry gry.
                }//koniec if

                //Czy przyciśnięto przycisk wróc do gry?
                if ((e.getX() > 365 && e.getY() > 905 && e.getX() < 533 && e.getY() < 945)) {
                    MenadzerGry.nowaGra();      //Gra jest zakończona więc wywołaj funkcję resetującą parametry gry.
                }//koniec if

                //Czy przyciśnięto przycisk najlepsze wyniki?
                if ((e.getX() > 669 && e.getY() > 905 && e.getX() < 879 && e.getY() < 945)) {
                    //Czy obecnie wyświetlane są wyniki?
                    if(Parametry.isWyswietlWyniki()){
                        Parametry.setWyswietlWyniki(false);     //Ustaw parametr wyswietl wyniki na wartość false.
                    }//koniec if
                    else{
                        Parametry.setWyswietlWyniki(true);    //Ustaw parametr wyswietl wyniki na wartość true.

                        try {
                            Parametry.odczytZPliku();       //Wywołaj metodę odczytującą wyniki z pliku tekstowego.
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }//koniec else
                }//koniec if

                //Czy wciśnięto przycisk koniec gry?
                if ((e.getX() > 1007 && e.getY() > 905 && e.getX() < 1169 && e.getY() < 945)) {
                    System.exit(0);     //Zakończ aplikację.
                }//koniec if
                break;
        }//koniec switch
    }//koniec mousePressed()

    /**
     * Nadpisz metodę odpowiedzialną reakcję
     * programu na puszczenie przycisku myszy.
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        Parametry.setCzyPrzycisniety(false);      //Ustaw parametr czy przyciśnięty przycisk myszy na wartość false.
        Parametry.setSilaDmuchu(0);     //Ustaw parametr symulacji eDmuchawki siła dmuchu na wartość 0;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Nadpisz metodę odpowiedzialną reakcję
     * programu na przeciągnięcie myszy
     * przy wciśniętym przycisku.
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        //Dla każdego obiektu w liście obiektów klasy Balon.
        for(Balon balon : Parametry.getBalony()){
            //Czy najechano myszą na balon przy trzymanym przycisku myszy?
            if((e.getX()>balon.getX() && e.getY()>balon.getY()&& e.getX()<balon.getX()+balon.getSzerokosc() && e.getY()<balon.getY()+balon.getWysokosc())) {
                balon.setCzyTrafiony(true);     //Ustaw parametr balonu czy trafiony na wartość true.
                break;
            }//koniec if
            else{
                balon.setCzyTrafiony(false);      //Ustaw parametr balonu czy trafiony na wartość false.
            }//koniec else
        }//koniec foreach
    }//koniec mouseDragged()

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}//koniec class PanelGry