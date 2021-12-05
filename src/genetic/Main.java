package genetic;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {

        int iteracje = 0;
        double maksWartość = 0;
        int lMaksWartość = 0;
        double wynik = 0;

        int lBezZmiany = 0;
        double ostWynik = 0;
        Genetyka genetyka = new Genetyka();
        Ruletka ruletka;

        int a,b,c,d;    //ax^3 + bx^2 + cx + d
        int Xepok,NWyst;    //kryteria zatrzymania (ilość iteracji bez zmiany wyniku) lub (ilość wystąpień maks wyniku)
        double pk,pm;   //wspołczynnik krzyżowania i mutacji

            //input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj a ");
        a = scanner.nextInt();
        System.out.print("Podaj b ");
        b = scanner.nextInt();
        System.out.print("Podaj c ");
        c = scanner.nextInt();
        System.out.print("Podaj d ");
        d = scanner.nextInt();
        System.out.print("Podaj wspołczzynik krzyżowania ");
        pk = scanner.nextDouble();
        System.out.print("Podaj wspołczynnik mutacji ");
        pm = scanner.nextDouble();
        System.out.print("Podaj maksymalną liczbę epok bez zmiany wyniku");
        Xepok = scanner.nextInt();
        System.out.print("Podaj maksymalną liczbę wystąpień największej wartości ");
        NWyst = scanner.nextInt();
            //input

        System.out.println("\nPoczątkowa pula chromosomów: ");
        Chromosom chromosomy[] = new Chromosom[6];
        for(int i = 0; i < chromosomy.length; i++){
            chromosomy[i] = new Chromosom(ThreadLocalRandom.current().nextInt(0, 31));
            chromosomy[i].setWartoscFunkcji(a,b,c,d);

            chromosomy[i].soutChromosom();
        }
        ruletka = new Ruletka(chromosomy);

        do {


            chromosomy = ruletka.selekcja().clone();
            chromosomy = genetyka.krzyzowanie(chromosomy, pk).clone();
            chromosomy = genetyka.mutacja(chromosomy, pm).clone();
            double maks = 0;
            for(Chromosom chromosom : chromosomy){
                chromosom.setWartoscFunkcji(a,b,c,d);
                //chromosom.soutChromosom();
                if(chromosom.getFenotyp() > maks)
                    maks = chromosom.getFenotyp();

            }
            ruletka = new Ruletka(chromosomy);
            iteracje++;
            wynik = maks;
            if(wynik == maksWartość) {
                lMaksWartość++;
            }
            else if(wynik > maksWartość) {
                maksWartość = wynik;
                lMaksWartość = 0;
            }
            if(wynik == ostWynik)
                lBezZmiany++;
            else
                lBezZmiany = 0;

            ostWynik = wynik;

            //System.out.print("i[" + iteracje + "]X dla maks wartości: " + wynik + "\n" + "lBezZmiany = " + lBezZmiany + ",  lmaksWartość = " + lMaksWartość + "\n");


        }while ((lMaksWartość < NWyst) && (lBezZmiany < Xepok));

        double y = a*Math.pow(maksWartość,3) + b*Math.pow(maksWartość,2) + c*maksWartość + d;
        System.out.println("\nLiczba iteracji - " + iteracje + "\nMaksymalna wartość funkcji w przedziale - " + y +
                            "\nX dla maksymalnej wartości = " + maksWartość);

    }
}
