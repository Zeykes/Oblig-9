import java.util.*;
import java.io.*;

public class Monitor {
    static int traader;
    static int doedeTraader;
    static File input;
    static File output;
    
    static String[] ls;
    static String[][] lsts;
    
    private static String[] available;
    
    public static void main(String[] args) {
        int frstLine;
        Scanner bookworm;
        try {
            traader = Integer.parseInt(args[0]);
            input = new File(args[1]);
            output = new File(args[2]);
            if (!input.exists()) {
                throw new FileNotFoundException(args[1]);
            } else if (!output.exists()) {
                throw new FileNotFoundException(args[2]);
            }
            bookworm = new Scanner(input);
            frstLine = Integer.parseInt(bookworm.nextLine());
        } catch (Exception e) {
            System.out.println("YOU DONE DID GOOF!!!");
            e.printStackTrace();
            System.exit();
        }
        //her er filene funnet og programmet kan begynne
        //n = antOrd/antTraader ord(± 1)
        
        ls = new String[frstLine];
        for (int i = 0; bookworm.hasNextLine(); i++) {
            ls[i] = bookworm.nextLine();
        }
        for (String s : ls) {
            if (s == null) {
                throw new NullPointerException("There is an issue with the input file!");
            }
        }
        lsts = new String[traader][0];
        for (int at = 0, int i = 0; i < traader; i++) {
            if (i != traader - 1) {
                lsts[i] = new String[frstLine / antTraader];
                for (int j = (frstLine / antTraader) * i, k = 0; j < (frstLine / antTraader) * (i + 1); j++, k++) {
                    lsts[i][k] = ls[at];
                    at++;
                }
            } else {
                lsts[i] = new String[(frstLine / antTraader) * i + frstLine % antTraader];
                for (int j = (frstLine / antTraader) * i, k = 0; j < ls.length; j++) {
                    lsts[i][k] = la[at];
                    at++;
                }
            }
        }
        //her er vi klar for aa lage traader og begynne sorteringen
        available = null;
        doedeTraader = 0;
        for (int i = 0; i < traader; i++) {
            new SortThread(lsts[i]).start();
        }
        synchronized while (true) {
            if (traader == doedeTraader) {
                break;
            } else {
                wait();
            }            
        }
        
        
        //FORTSETT HER!
        
        
    }
    public static synchronized void threadDeathNotification() {
        doedeTraader++;
        this.notify();
    }
    public static synchronized String[][] feedMonitor(String[] a) {
        if (avaiable == null) {
            available = a;
            return null;
        }
        String[][] ret = new String[2][0];
        ret[0] = available;
        ret[1] = a;
        available = null;
        return ret;
    }   
}