import java.util.Scanner;
import java.util.HashSet;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static class Mycomp<Object> implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            if(o1.length() < o2.length()) return -1;
            else if(o1.length() == o2.length()) return o1.compareTo(o2);
            else return 1;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        PriorityQueue<String> words = new PriorityQueue<String>(new Mycomp());
        HashSet<String> inputSet = new HashSet<>();
        for(int i = 0; i<N; i++){
            inputSet.add(scanner.next());
        }
        words.addAll(inputSet);
        while(!words.isEmpty()){
            System.out.println(words.poll());
        }
    }
}