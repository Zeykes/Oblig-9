public class LsTool {
    public static boolean isSorted(String a, String b) {
        if (a == null || b == null) {
            return true;
        } else if (a.compareTo(b) < 0) {
            return true;
        }
        return false;   
    }
    public static boolean isSorted(String[] myBunch) {
        for (int i = 0; i < myBunch.length; i++) {
            if (!isSorted(myBunch[i - 1], myBunch[i])) {
                return false;
            }
        }
        return true;
    }
    
    public String[] append(String[] ls1, String[] ls2) throws NullPointerException {
        String[] ls3 = new String[ls1.length + ls2.length];
        for (int i = 0; i < ls1.length; i++) {
            if(ls1[i] == null) {
                throw new NullPointerException();
            }
            ls3[i] = ls1[i];
        }
        for (int i = ls1.length, int j = 0; j < ls2.length; i++, j++) {
            if(ls2[j] == null) {
                throw new NullPointerException();
            }
            ls3[i] = ls2[j];
        }
        return ls3;
    }

}