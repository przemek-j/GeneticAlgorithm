package genetic;

import java.util.concurrent.ThreadLocalRandom;

public class Ruletka {
    private Chromosom ch[];
    private double py[];

    public double getSumaFunkcji() {
        return sumaFunkcji;
    }

    private double sumaFunkcji;

    public Ruletka(Chromosom c[]){
        ch = c;
        py = new double[6];
        policzsume();
        policzpy();
    }
    private void policzsume(){
        for(Chromosom chromosom : ch){
            sumaFunkcji+=chromosom.getWartoscFunkcji();
        }
    }
    private void policzpy(){
        double sumapy = 0;
        for (int i = 0; i < ch.length; i++){
            sumapy += (ch[i].getWartoscFunkcji() / sumaFunkcji);
            py[i] = sumapy;
        }
    }

    public Chromosom[] selekcja(){
        double rzut[] = new double[6];
        Chromosom noweCh[] = new Chromosom[6];
        for(int i = 0; i < rzut.length; i++){
            rzut[i] = ThreadLocalRandom.current().nextDouble(0, 1);
            for(int j = 0; j < 6; j++){
                if(rzut[i] <= py[j]) {
                    noweCh[i] = ch[j];
                    break;
                }

            }
        }
        return noweCh;
    }

}
