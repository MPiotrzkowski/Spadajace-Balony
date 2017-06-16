package Spadajace_Balony;

import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Date;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;


/**
 * Kontener parametrów.
 * Klasa przechowuje wszystkie parametry programu oraz
 * pozwala na dostęp do nich i ich modyfikację.
 * Zapisuje, odczytuje i przetwarza wyniki gry z pliku tekstowego.
 * @author Mikołaj Piotrzkowski
 */
public class Parametry {

    /**   Obiekt klasy zawierającej metodę generowania liczb pseudolosowych   */
    private static Random generator = new Random();
    /**   Wysokość okna gry  */
    private static final int wysokoscOkna = 1024;
    /**   Szerokość okna gry   */
    private static final int szerokoscOkna = 1280;
    /**   Współrzędna x okna gry   */
    private static int wspolrzednaXOkna = (Toolkit.getDefaultToolkit().getScreenSize().width-szerokoscOkna)/2;
    /**   Współrzędna y okna gry   */
    private static int wspolrzednaYOkna = (Toolkit.getDefaultToolkit().getScreenSize().height-wysokoscOkna)/2;
    /**   Czas początku gry   */
    private static long czasPoczatkowy;
    /**   Czas końca gry   */
    private static long czasKoncowy;
    /**   Obecny czas gry   */
    private static long czasGry;
    /**   Czas całej gry po zakończeniu   */
    private static long czasCalejGry;
    /**   Liczba żyć   */
    private static int liczbaZyc = 3;
    /**   Podstawowa prędkość spadania balonu   */
    private static int zmianaY = 3;
    /**   Siła dmuchu w symulacji eDmuchawki (od 0 do 100)   */
    private static float silaDmuchu;
    /**   Czy przycisk myszy jest przyciśnięty   */
    private static boolean czyPrzycisniety = false;
    /**   Obecny stan gry (0: pauza, 1: gra, 2: gra skończona)   */
    private static int stanGry;
    /**   Czas całej pauzy po jej zakończeniu   */
    private static long czasPauzy;
    /**   Czas początku pauzy   */
    private static long czasPoczatkuPauzy;
    /**   Czas końca pauzy   */
    private static long czasKoncaPauzy;
    /**   Nazwa gracza pobrana z pola tekstowego   */
    private static String nazwaUzytkownika = "";
    /**   Wynik gry zawierający czas gry, datę oraz nazwę gracza   */
    private static String wynik = "";
    /**   Czy wyświetlić wyniki   */
    private static boolean wyswietlWyniki = false;
    /**   Czcionka do wyświetlania czasu gry   */
    private static Font czasCzcionka = new Font("Dialog",Font.BOLD,36);
    /**   Czcionka do wyświetlenia napisu koniec gry   */
    private static Font koniecGryCzcionka = new Font("Dialog",Font.BOLD,100);
    /**   Czcionka do wyświetlenia czasu końcowego gry po jej zakończeniu.   */
    private static Font czasKoncowyCzcionka = new Font("Dialog",Font.BOLD,60);
    /**   Czcionka do wyświetlenia napisu najlepsze wyniki   */
    private static Font najlepszeWynikiNapis = new Font("Dialog", Font.BOLD, 50);
    /**   Czcionka do wyświetlenia danych najlepszych wyników   */
    private static Font najlepszeWynikiCzasDataGracz = new Font("Dialog", Font.BOLD, 30);

    /**   Lista obiektów klasy Wynik   */
    private static java.util.List<Wynik> wyniki = new ArrayList<Wynik>();
    /**  Lista obiektów klasy Balon   */
    private static List<Balon> balony = new ArrayList<Balon>();;

    /**   Obrazek tła   */
    private static Image tlo;
    /**   Obrazek przycisku menu   */
    private static Image menu;
    /**   Obrazek pola do wyświetlania czasu gry   */
    private static Image czas;
    /**   Obrazek przycisku nowa gra   */
    private static Image nowaGra;
    /**   Obrazek przycisku wróc do gry   */
    private static Image wrocDoGry;
    /**   Obrazek przycisku najlepsze wyniki   */
    private static Image najlepszeWyniki;
    /**   Obrazek przycisku zakończ grę   */
    private static Image zakonczGre;
    /**   Tablica obrazków balonów w różnych kolorach   */
    private static Image[] obrazkiBalonow;


    /**
     * Metoda ładowania obrazków z plików graficznych.
     */
    public static void wczytajObrazki() {

        tlo = loadImage("images/tlo.png");
        menu = loadImage("images/menu.png");
        czas = loadImage("images/czas.png");
        nowaGra = loadImage("images/nowaGra.png");
        wrocDoGry = loadImage("images/wrocDoGry.png");
        najlepszeWyniki = loadImage("images/najlepszeWyniki.png");
        zakonczGre = loadImage("images/zakonczGre.png");


        obrazkiBalonow = new Image[5];
        obrazkiBalonow[0] = loadImage("images/czerwonyBalon.png");
        obrazkiBalonow[1] = loadImage("images/fioletowyBalon.png");
        obrazkiBalonow[2] = loadImage("images/niebieskiBalon.png");
        obrazkiBalonow[3] = loadImage("images/zielonyBalon.png");
        obrazkiBalonow[4] = loadImage("images/zoltyBalon.png");
    }//koniec wczytajObrazki()

    /**
     * Metoda pobierania obiektu klasy Image
     * @param fileName ścieżka dostępu do pliku graficznego
     * @return obiekt klasy Image zawierający obrazek wczytany z pliku graficznego
     */
    public static Image loadImage(String fileName) {
        return new ImageIcon(fileName).getImage();
    }

    /**
     * Metoda zapisująca wynik rozgrywki do pliku tekstowego.
     * @param wynik ciąg znaków zawierający czas gry, datę oraz nazwę gracza
     */
    public static void zapisDoPliku(String wynik) throws IOException{
        FileWriter zapis = new FileWriter("najlepszeWyniki.txt", true);
        BufferedWriter out = new BufferedWriter(zapis);
        out.write(wynik);
        out.newLine();
        out.close();
    }

    /**
     * Metoda odczytująca wyniki rozgrywki z pliku tekstowego
     * i zapisująca do listy wyników a następnie je sortująca
     * od najdłuższego czasu gry do najkrótszego.
     * @throws IOException
     */
    public static void  odczytZPliku() throws IOException{
        File file = new File("najlepszeWyniki.txt");        //Utwórz nowy obiekt klasy File z pliku o podanej ścieżce dostępu
        Scanner in = new Scanner(file);     //Utwórz nowy obiekt klasy Scanner do którego przekaż obiekt klasy File
        wyniki.clear();     //Wyczyść dotychczasową listę wyników przed każdym wczytaniem.

        //Dodaj do listy wyników 9 wyników domyślnym.
        for(int i=0; i<=8; i++){
            wyniki.add(new Wynik(0+":"+0+"*-----------"));
        }//koniec for

        //Twórz i dodawaj do listy, obiekt klasy Wynik, dla każdej kolejnej linii odczytanego pliku tekstowego.
        do{
            String liniaPliku = in.nextLine();
            wyniki.add(new Wynik(liniaPliku));
        } while (in.hasNextLine());


        //Posortuj wczytaną listę wyników względem czasu gry.
        Collections.sort(wyniki, new Comparator<Wynik>()
        {
            @Override
            public int compare(Wynik b1, Wynik b2)
            {
                return Long.valueOf(b2.wczytanyCzas).compareTo(Long.valueOf(b1.wczytanyCzas));
            }
        });
    }//koniec odczytZPliku()

    /**
     * Metoda przetwarza datę zapisaną w milisekundach
     * na format łatwiejszy do odczytania.
     * @param wczytanaData data podana w milisekundach
     * @return data w czytelnym formacie
     */
    public static String przetworzDate(String wczytanaData){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss");    //Utwórz nowy obiekt klasy SimpleDateFormat
        Date resultdate = new Date(Long.valueOf(wczytanaData));     //Utwórz nowy obiekt klasy Date z parametrem wczytanej daty w milisekundach
        return sdf.format(resultdate);
    }//koniec przetworzDate()

    /**
     * Metoda przetwarza czas zapisany w milisekundach
     * na format łatwiejszy do odczytania.
     * @param czas czas podany w milisekundach
     * @return czas w czytelnym formacie
     */
    public static String przetworzCzas(long czas){
        return TimeUnit.MILLISECONDS.toMinutes(czas)+":"+TimeUnit.MILLISECONDS.toSeconds(czas)%60+":"+String.valueOf((czas/10)%100);
    }//koniec przetworzCzas

    /**
     * Metoda zwracająca przetworzony czas danego wyniku gry.
     * @param i indeks danego wyniku z listy obiektów klasy Wynik
     * @return przetworzony czas danego wyniku
     */
    public static String wypiszCzas(int i){
        return przetworzCzas(wyniki.get(i).wczytanyCzas);
    }//koniec wypiszCzas

    /**
     * Metoda zwracająca przetworzoną datę danego wyniku gry.
     * @param i indeks danego wyniku z listy obiektów klasy Wynik
     * @return przetworzoną datę danego wyniku
     */
    public static String wypiszDate(int i){
        return wyniki.get(i).wczytanaData;
    }//koniec wypiszDate

    /**
     * Metoda zwracająca imię gracza danego wyniku gry.
     * @param i indeks danego wyniku z listy obiektów klasy Wynik
     * @return nazwę gracza danego wyniku
     */
    public static String wypiszImie(int i){
        return wyniki.get(i).wczytaneImie;
    }//koniec wypiszImie


    /**
     * Klasa wewnętrzna przechowująca pojedynczy wynik gry
     * odczytany z pliku oraz oddzielająca jego części.
     */
    static class Wynik{
        /**   Wczytany czas gry w danym wyniku   */
        private long wczytanyCzas;
        /**   Wczytana data gry w danym wyniku   */
        private String wczytanaData;
        /**   Wczytana nazwa gracza w danym wyniku   */
        private String wczytaneImie;


        /**
         * Główny konstruktor klasy.
         * Przypisanie do zmiennych odpowiednich części
         * wyniku przekazanego w parametrze.
         * @param liniaPliku linia wczytanego pliku tekstowego
         */
        public Wynik(String liniaPliku){
            wczytanyCzas = Long.valueOf(liniaPliku.substring(0, liniaPliku.indexOf(":")));      //Oddzielenie czasu gry z wyniku
            wczytanaData = przetworzDate(liniaPliku.substring(liniaPliku.indexOf(":") + 1 , liniaPliku.indexOf("*")));      //Oddzielenie daty z wyniku
            wczytaneImie = liniaPliku.substring(liniaPliku.indexOf("*") + 1);       //Oddzielenie nazwy gracza z wyniku
        }//koniec Wynik()
    }//koniec class Wynik




    //gettery


    /**
     * Getter for {@link #generator}
     * @return {@link #generator}
     */
    public static Random getGenerator() {
        return generator;
    }

    /**
     * Getter for {@link #czasKoncowy}
     * @return {@link #czasKoncowy}
     */
    public static long getCzasKoncowy() {
        return czasKoncowy;
    }

    /**
     * Getter for {@link #czasPoczatkowy}
     * @return {@link #czasPoczatkowy}
     */
    public static long getCzasPoczatkowy() {
        return czasPoczatkowy;
    }

    /**
     * Getter for {@link #czasGry}
     * @return {@link #czasGry}
     */
    public static long getCzasGry() {
        return czasGry;
    }

    /**
     * Getter for {@link #tlo}
     * @return {@link #tlo}
     */
    public static Image getTlo() {
        return tlo;
    }

    /**
     * Getter for {@link #menu}
     * @return {@link #menu}
     */
    public static Image getMenu() {
        return menu;
    }

    /**
     * Getter for {@link #obrazkiBalonow}
     * @return {@link #obrazkiBalonow}
     */
    public static Image[] getObrazkiBalonow() {
        return obrazkiBalonow;
    }

    /**
     * Getter for {@link #zakonczGre}
     * @return {@link #zakonczGre}
     */
    public static Image getZakonczGre() {
        return zakonczGre;
    }

    /**
     * Getter for {@link #najlepszeWyniki}
     * @return {@link #najlepszeWyniki}
     */
    public static Image getNajlepszeWyniki() {
        return najlepszeWyniki;
    }

    /**
     * Getter for {@link #wrocDoGry}
     * @return {@link #wrocDoGry}
     */
    public static Image getWrocDoGry() {
        return wrocDoGry;
    }

    /**
     * Getter for {@link #nowaGra}
     * @return {@link #nowaGra}
     */
    public static Image getNowaGra() {
        return nowaGra;
    }

    /**
     * Getter for {@link #czas}
     * @return {@link #czas}
     */
    public static Image getCzas() {
        return czas;
    }

    /**
     * Getter for {@link #zmianaY}
     * @return {@link #zmianaY}
     */
    public static int getZmianaY() {
        return zmianaY;
    }

    /**
     * Getter for {@link #wysokoscOkna}
     * @return {@link #wysokoscOkna}
     */
    public static int getWysokoscOkna(){
        return wysokoscOkna;
    }

    /**
     * Getter for {@link #szerokoscOkna}
     * @return {@link #szerokoscOkna}
     */
    public static int getSzerokoscOkna(){
        return szerokoscOkna;
    }

    /**
     * Getter for {@link #wspolrzednaXOkna}
     * @return {@link #wspolrzednaXOkna}
     */
    public static int getWspolrzednaXOkna() {
        return wspolrzednaXOkna;
    }

    /**
     * Getter for {@link #wspolrzednaYOkna}
     * @return {@link #wspolrzednaYOkna}
     */
    public static int getWspolrzednaYOkna() {
        return wspolrzednaYOkna;
    }

    /**
     * Getter for {@link #liczbaZyc}
     * @return {@link #liczbaZyc}
     */
    public static int getLiczbaZyc() {
        return liczbaZyc;
    }

    /**
     * Getter for {@link #stanGry}
     * @return {@link #stanGry}
     */
    public static int getStanGry() {
        return stanGry;
    }

    /**
     * Getter for {@link #czasPauzy}
     * @return {@link #czasPauzy}
     */
    public static long getCzasPauzy() {
        return czasPauzy;
    }

    /**
     * Getter for {@link #czasPoczatkuPauzy}
     * @return {@link #czasPoczatkuPauzy}
     */
    public static long getCzasPoczatkuPauzy() {
        return czasPoczatkuPauzy;
    }

    /**
     * Getter for {@link #czasKoncaPauzy}
     * @return {@link #czasKoncaPauzy}
     */
    public static long getCzasKoncaPauzy() {
        return czasKoncaPauzy;
    }

    /**
     * Getter for {@link #silaDmuchu}
     * @return {@link #silaDmuchu}
     */
    public static float getSilaDmuchu() {
        return silaDmuchu;
    }

    /**
     * Getter for {@link #wynik}
     * @return {@link #wynik}
     */
    public static String getWynik() {
        return wynik;
    }

    /**
     * Getter for {@link #czasCalejGry}
     * @return {@link #czasCalejGry}
     */
    public static long getCzasCalejGry() {
        return czasCalejGry;
    }

    /**
     * Getter for {@link #czyPrzycisniety}
     * @return {@link #czyPrzycisniety}
     */
    public static boolean isCzyPrzycisniety() {
        return czyPrzycisniety;
    }

    /**
     * Getter for {@link #wyswietlWyniki}
     * @return {@link #wyswietlWyniki}
     */
    public static boolean isWyswietlWyniki() {
        return wyswietlWyniki;
    }

    /**
     * Getter for {@link #czasCzcionka}
     * @return {@link #czasCzcionka}
     */
    public static Font getCzasCzcionka() {
        return czasCzcionka;
    }

    /**
     * Getter for {@link #koniecGryCzcionka}
     * @return {@link #koniecGryCzcionka}
     */
    public static Font getKoniecGryCzcionka() {
        return koniecGryCzcionka;
    }

    /**
     * Getter for {@link #czasKoncowyCzcionka}
     * @return {@link #czasKoncowyCzcionka}
     */
    public static Font getCzasKoncowyCzcionka() {
        return czasKoncowyCzcionka;
    }

    /**
     * Getter for {@link #najlepszeWynikiNapis}
     * @return {@link #najlepszeWynikiNapis}
     */
    public static Font getNajlepszeWynikiNapis() {
        return najlepszeWynikiNapis;
    }

    /**
     * Getter for {@link #najlepszeWynikiCzasDataGracz}
     * @return {@link #najlepszeWynikiCzasDataGracz}
     */
    public static Font getNajlepszeWynikiCzasDataGracz() {
        return najlepszeWynikiCzasDataGracz;
    }

    /**
     * Getter for {@link #balony}
     * @return {@link #balony}
     */
    public static List<Balon> getBalony() {
        return balony;
    }


    //settery


    /**
     * Setter for {@link #czasPoczatkuPauzy}
     * @param czasPoczatkuPauzy the {@link #czasPoczatkuPauzy} to set
     */
    public static void setCzasPoczatkuPauzy(long czasPoczatkuPauzy) {
        Parametry.czasPoczatkuPauzy = czasPoczatkuPauzy;
    }

    /**
     * Setter for {@link #wyswietlWyniki}
     * @param wyswietlWyniki the {@link #wyswietlWyniki} to set
     */
    public static void setWyswietlWyniki(boolean wyswietlWyniki) {
        Parametry.wyswietlWyniki = wyswietlWyniki;
    }

    /**
     * Setter for {@link #czasCalejGry}
     * @param czasCalejGry the {@link #czasCalejGry} to set
     */
    public static void setCzasCalejGry(long czasCalejGry) {
        Parametry.czasCalejGry = czasCalejGry;
    }

    /**
     * Setter for {@link #wynik}
     * @param wynik the {@link #wynik} to set
     */
    public static void setWynik(String wynik) {
        Parametry.wynik = wynik;
    }

    /**
     * Setter for {@link #nazwaUzytkownika}
     * @param nazwaUzytkownika the {@link #nazwaUzytkownika} to set
     */
    public static void setNazwaUzytkownika(String nazwaUzytkownika) {
        Parametry.nazwaUzytkownika = nazwaUzytkownika;
    }

    /**
     * Setter for {@link #silaDmuchu}
     * @param silaDmuchu the {@link #silaDmuchu} to set
     */
    public static void setSilaDmuchu(float silaDmuchu) {
        Parametry.silaDmuchu = silaDmuchu;
    }

    /**
     * Setter for {@link #czasKoncaPauzy}
     * @param czasKoncaPauzy the {@link #czasKoncaPauzy} to set
     */
    public static void setCzasKoncaPauzy(long czasKoncaPauzy) {
        Parametry.czasKoncaPauzy = czasKoncaPauzy;
    }

    /**
     * Setter for {@link #czasPauzy}
     * @param czasPauzy the {@link #czasPauzy} to set
     */
    public static void setCzasPauzy(long czasPauzy) {
        Parametry.czasPauzy = czasPauzy;
    }

    /**
     * Setter for {@link #stanGry}
     * @param stanGry the {@link #stanGry} to set
     */
    public static void setStanGry(int stanGry) {
        Parametry.stanGry = stanGry;
    }

    /**
     * Setter for {@link #czyPrzycisniety}
     * @param czyPrzycisniety the {@link #czyPrzycisniety} to set
     */
    public static void setCzyPrzycisniety(boolean czyPrzycisniety) {
        Parametry.czyPrzycisniety = czyPrzycisniety;
    }

    /**
     * Setter for {@link #czasPoczatkowy}
     * @param czasPoczatkowy the {@link #czasPoczatkowy} to set
     */
    public static void setCzasPoczatkowy(long czasPoczatkowy) {
        Parametry.czasPoczatkowy = czasPoczatkowy;
    }

    /**
     * Setter for {@link #czasKoncowy}
     * @param czasKoncowy the {@link #czasKoncowy} to set
     */
    public static void setCzasKoncowy(long czasKoncowy) {
        Parametry.czasKoncowy = czasKoncowy;
    }

    /**
     * Setter for {@link #liczbaZyc}
     * @param liczbaZyc the {@link #liczbaZyc} to set
     */
    public static void setLiczbaZyc(int liczbaZyc) {
        Parametry.liczbaZyc = liczbaZyc;
    }

    /**
     * Setter for {@link #czasGry}
     * @param czasGry the {@link #czasGry} to set
     */
    public static void setCzasGry(long czasGry) {
        Parametry.czasGry = czasGry;
    }

}//koniec class Parametry