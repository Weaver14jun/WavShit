import java.io.*;
import java.lang.Math;
import java.util.ArrayList;

import static java.lang.Math.log;

public class WavClass {
    public static double lg(double x) {
        return Math.log(x)/Math.log(2.0);
    }
    public static void main(String []args){
        int N=0;
        FFT fft;
        double[] window;
        double[] re;
        double[] im;
        try {
            File f = new File("/home/gerty/Downloads/01_The_Prodigy_-_Invaders_Must_Die.wav");
            WavFile wf = WavFile.openWavFile(f);
            int numChannels = wf.getNumChannels();
            N = 1024*numChannels;
            fft = new FFT(N);
            int counter = 0;
            window = fft.getWindow();
            int[] buffer = new int[N];
            ArrayList<Double> a = new ArrayList<>();
            int framesRead;
            do{
                framesRead = wf.readFrames(buffer, 1024);
                for(int j=0;j<N;j++)
                    a.add(Math.abs((double) buffer[j]));
            }
            while (framesRead != 0);
            System.out.print("aSize= "+a.size());
            /*for(int i=0; i<N; i++) {
                System.out.println(a);
                re[i]=buffer[i];
                im[i]=0;
            }*/
            re = new double[a.size()];
            im = new double[1];
            System.out.print("Comleted");
            System.exit(0);
            fft.fft(re,im);
            System.out.println("After FFT: ");
            for(int i=0; i<a.size(); i++) {
                System.out.println("real: "+re[i]);
            }
            // parsing to notes
            /*System.out.println("*****************Making notes************************");
            for(int i=0; i<N; i++) {
                re[i]=12*(int)lg(re[i]/440)+49;
                System.out.println(re[i]);
            }*/
        }
        catch (Exception e){
            System.out.println("Some shit happened..." + e);
        }
    }
}
