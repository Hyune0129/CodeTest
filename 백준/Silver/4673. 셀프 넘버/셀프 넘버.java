public class Main {

    static int d(int n) {
        int result = n;
        int len = Integer.toString(result).length();
        for(int i = len; i > 0; i--) {
            result += ((n % (int)Math.pow(10, i)) / (int)Math.pow(10, i - 1) ) ;
        }

        return result;
    }
    public static void main(String[] args) {
        Boolean[] isSelfNumbers = new Boolean[10001];
        for(int index = 1; index <= 10000; index++){
            isSelfNumbers[index] = true;
        }
        for(int number = 1; number<= 10000; number++){
            int dn = d(number);
            if(dn <= 10000) {
                isSelfNumbers[dn] = false;
            }
        }
        for(int index = 1; index <= 10000; index++){
            if(isSelfNumbers[index]){
                System.out.println(index);
            }
        }


    }
}