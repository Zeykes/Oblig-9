public class SortThread extends Thread {
    private String[] old;
    
    public SortThread(String[] myBunch) {
        old = myBunch;
    }
    public void run() {
        while (!LsTool.isSortert(old)) {
            for (int i = 1; i < old.length; i++) {
                if (!isSorted(old[i - 1], old[i])) {
                    String s1 = old[i - 1];
                    String s2 = old[i];
                    old[i - 1] = String s2;
                    old[i] = String s1;
                }
            }
        }
        String[][] meBabies = Monitor.feedMonitor(old);
        while (meBabies != null) {
            String[] alpha = meBabies[0];
            String[] beta = meBabies[1];
            
            String[] ferdig = new String[alpha.length + beta.length];
            
            
            for (int atA = 0, atB = 0, atF = 0; atF < ferdig.length; atF++) {
                if (atA == alpha.length) {
                    ferdig[atF] = beta[atB];
                    atB++;
                    continue;
                } else if (atB == beta.length) {
                    ferdig[atF] = alpha[atA];
                    atA++;
                    continue;
                }
                
                String stringA = alpha[atA];
                String stringB = beta[atB];
                
                if (LsTool.isSorted(stringA, stringB)) {
                    ferdig[atF] = stringA;
                    atA++;    
                } else {
                    ferdig[atF] = stringB;
                    atB++;
                }
            
            } 
            
            
            meBabies = Monitor.feedMonitor(ferdig);
        }
        Monitor.threadDeathNotification();
        join();
    }
    
}