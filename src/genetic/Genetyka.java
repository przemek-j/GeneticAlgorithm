package genetic;

import java.util.concurrent.ThreadLocalRandom;

public class Genetyka {
    public Chromosom[] krzyzowanie(Chromosom ch[], double pk){
        Chromosom nowyCh[] = new Chromosom[6];
        int nowybin[] = new int[5];
        int nowybin2[] = new int[5];
        for (int i = 0; i < ch.length; i+=2){
            if(pk > ThreadLocalRandom.current().nextDouble(0, 1)){
                int lokus = ThreadLocalRandom.current().nextInt(1,4);
                for(int j = 4; j >= 5-lokus; j--){
                    nowybin[j] = ch[i].getBin()[j];
                    nowybin2[j] = ch[i+1].getBin()[j];
                }
                for(int j = 5-lokus-1; j >=0; j--){
                    nowybin[j] = ch[i+1].getBin()[j];
                    nowybin2[j] = ch[i].getBin()[j];
                }
                nowyCh[i] = new Chromosom(nowybin);
                nowyCh[i+1] = new Chromosom(nowybin2);
            }else {
                nowyCh[i] = ch[i];
                nowyCh[i+1] = ch[i+1];
            }
        }
        return nowyCh;
    }
    public Chromosom[] mutacja(Chromosom ch[], double pm){
        Chromosom nowyCh[] = new Chromosom[6];
        int nowyBin[] = new int[5];
        for(int i = 0; i < ch.length; i++){
            if(pm > ThreadLocalRandom.current().nextDouble(0, 1)){
                int lokus = ThreadLocalRandom.current().nextInt(0,4);
                nowyBin = ch[i].getBin().clone();
                nowyBin[lokus] = nowyBin[lokus]<1 ? 1:0;
                nowyCh[i] = new Chromosom(nowyBin);
            }else {
                nowyCh[i] = ch[i];
            }

        }
        return nowyCh;
    }

}
