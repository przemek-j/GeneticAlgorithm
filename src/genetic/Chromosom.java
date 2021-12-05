package genetic;

public class Chromosom {
    private int bin[];
    private int fenotyp;
    private double wartoscFunkcji;
    Chromosom ( int t[]){
        fenotyp = 0;
        bin = t.clone();
        for(int i = 0; i < t.length; i++){
            fenotyp += t[i] * Math.pow(2,i);
        }
    }
    Chromosom(int fenotyp){
        this.fenotyp = fenotyp;
        bin = new int[5];
        for(int i = bin.length -1 ; i >= 0; i--){
            if(Math.pow(2,i) <=fenotyp){
                bin[i] = 1;
                fenotyp -= Math.pow(2,i);
            }else
                bin[i] = 0;
        }
    }

    public int[] getBin() {
        return bin;
    }
    public int getFenotyp() {
        return fenotyp;
    }
    public static void testFtoBin(int f){
        Chromosom c1 = new Chromosom(f);
        int t[] = c1.getBin();
        for(int i = 0; i < t.length; i++){
            System.out.println(t[i]);
        }
    }
    public static void testBinToF(int t[]){
        Chromosom c = new Chromosom(t);
        System.out.println(c.fenotyp);
    }

    public double getWartoscFunkcji() {
        return wartoscFunkcji;
    }

    public void setWartoscFunkcji(int a, int b, int c, int d) {
        wartoscFunkcji = a*Math.pow(fenotyp,3) + b*Math.pow(fenotyp,2) + c*fenotyp + d;
    }

    public void soutChromosom(){
        for(int i = bin.length - 1; i >=0; i--){
            System.out.print(bin[i]);
        }
        System.out.print(" = " + fenotyp + ", f(ch) = " + wartoscFunkcji + "\n");
    }
}
