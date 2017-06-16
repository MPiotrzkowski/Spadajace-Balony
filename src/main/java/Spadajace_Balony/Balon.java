package Spadajace_Balony;

import java.awt.Image;

/**
 * Klasa modelująca zachowanie balonu.
 * @author Mikołaj Piotrzkowski
 */
public class Balon {

    /**   Współrzędna x balonu   */
    private int x;
    /**   Współrzędna y balonu   */
    private double y;
    /**   Parametr prędkości spadania balonu   */
    private double parametrZmianyY = 0;
    /**   Czy balon został trafiony   */
    private boolean czyTrafiony;
    /**   Parametr wysokości obrazka balonu   */
    private float wysokosc;
    /**   Parametr szerokości obrazka balonu   */
    private float szerokosc;
    /**   Ikona balonu   */
    private Image obrazek;


    /**
     * Główny konstruktor klasy. Wylosowanie i ustawienie początkowych parametrów.
     * @param obrazkiBalonow tablica obrazków z balonami w różnych kolorach
     */
    public Balon(Image obrazkiBalonow){
        x=20+Parametry.getGenerator().nextInt(1200);    //Wylosuj na jakiej szerokości okna pojawia się nowy balon.
        y=-200;     //Ustaw początkową wartość współrzędnej y na wartość - 200 (nad widocznym oknem gry).
        obrazek = obrazkiBalonow;       //Ustaw ikonę balonu na wylosowany obrazek.
        wysokosc = obrazek.getHeight(null);     //Ustaw parametr wysokości obrazka balonu na wysokość pliku graficznego.
        szerokosc = obrazek.getWidth(null);     //Ustaw parametr szerokości obrazka balonu na wysokość pliku graficznego.
        //Dopóki wylosowany parametr zmiany współrzędnej y jest mniejszy niż 0.4, losuj ponownie.
        while(parametrZmianyY<0.4) {
            parametrZmianyY = Parametry.getGenerator().nextDouble();      //Wylosuj wartość od 0.0 do 1.0
        }//koniec while
    }//koniec Balon()


    /**
     * Oddal balon
     * @param silaDmuchu sila dmuchu w symulacji eDmuchawki
     */
    public void zmniejszBalon(float silaDmuchu){
        wysokosc = wysokosc - 3*(silaDmuchu/100);   //Ustaw nowy parametr wysokości obrazka balonu.
        szerokosc = szerokosc - 2*(silaDmuchu/100);     //Ustaw nowy parametr szerokości obrazka balonu.

        //Czy parametr szerokości obrazka balonu jest podzielny przez 3? Bez przesuwania balonu przy zmniejszaniu, maleje on do lewego górnego rogu obrazka.
        if ((int)szerokosc%3==0) {
            x++;    //Dodaj 1 do współrzędnej x balonu.
            y++;    //Dodaj 1 do współrzędnej y balonu.
        }//koniec if
    }//koniec zmniejszBalon()



    //gettery

    /**
     * Getter for {@link #obrazek}
     * @return {@link #obrazek}
     */
    public Image getObrazek() {
        return obrazek;
    }

    /**
     * Getter for {@link #x}
     * @return {@link #x}
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for {@link #y}
     * @return {@link #y}
     */
    public int getY() {
        return (int) y;
    }

    /**
     * Getter for {@link #wysokosc}
     * @return {@link #wysokosc}
     */
    public int getWysokosc() {
        return (int) wysokosc;
    }

    /**
     * Getter for {@link #szerokosc}
     * @return {@link #szerokosc}
     */
    public int getSzerokosc() {
        return (int) szerokosc;
    }

    /**
     * Getter for {@link #czyTrafiony}
     * @return {@link #czyTrafiony}
     */
    public boolean isCzyTrafiony() {
        return czyTrafiony;
    }


    //settery


    /**
     * Setter for {@link #y}
     * @param y the {@link #y} to set
     */
    public void setY(int y) {
        double noweY = y+Parametry.getZmianaY()*parametrZmianyY;
        this.y = noweY;
    }

    /**
     * Setter for {@link #czyTrafiony}
     * @param czyTrafiony the {@link #czyTrafiony} to set
     */
    public void setCzyTrafiony(boolean czyTrafiony) {
        this.czyTrafiony = czyTrafiony;
    }
}//koniec class Balon