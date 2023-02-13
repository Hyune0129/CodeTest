import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.next();
        StringBuilder stringBuilder1, stringBuilder2, stringBuilder3;
        String priorStr = null;
        String temp;
        for (int i = 1; i < word.length() - 1; i++) {
            for (int j = i + 1; j < word.length(); j++) {
                stringBuilder1 = new StringBuilder(word.substring(0, i));
                stringBuilder2 = new StringBuilder(word.substring(i, j));
                stringBuilder3 = new StringBuilder(word.substring(j, word.length()));
                stringBuilder1.reverse();
                stringBuilder2.reverse();
                stringBuilder3.reverse();
                temp = stringBuilder1.toString() + stringBuilder2.toString() + stringBuilder3.toString();
                if (priorStr == null || priorStr.compareTo(temp) > 0) {
                    priorStr = temp;
                }
            }
        }
        System.out.println(priorStr);
    }
}