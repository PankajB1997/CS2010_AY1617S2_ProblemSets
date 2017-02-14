/**
 *
 * author : [Steffi Wong Wai Kay]
 * matric no: [A0141727H]
 * 
 */

import java.util.*;

public class HelloWorld {
 
 public static void main(String[] args) {

        int result = 0;
        int method;
        int bit1, bit2;
        String query;

        Scanner reader = new Scanner(System.in);
        method = reader.nextInt();

        if (method == 1) {
            int N = reader.nextInt();
            for (int i = 0; i < N; i++) {
                query = reader.next();
                bit1 = reader.nextInt();
                bit2 = reader.nextInt();

                if (query.charAt(0) == 'A') {
                    result = bit1 & bit2;
                    System.out.print(result + "\n");
                }

                else {
                    result = bit1 | bit2;
                    System.out.print(result + "\n");
                }
            }
        }

        if (method == 2) {
            query = reader.next();
            while (!query.equals("0")) {
                bit1 = reader.nextInt();
                bit2 = reader.nextInt();

                if (query.charAt(0) == 'A') {
                    result = bit1 & bit2;
                    System.out.print(result + "\n");
                }
                
                else {
                    result = bit1 | bit2;
                    System.out.print(result + "\n");
                }

                query = reader.next();
            }
        }

        if (method == 3) {
            query = reader.next();
            while (!query.equals(null)) {
                bit1 = reader.nextInt();
                bit2 = reader.nextInt();
                
                if (query.charAt(0) == 'A') {
                    result = bit1 & bit2;
                    System.out.print(result + "\n");
                }

                else {
                    result = bit1 | bit2;
                    System.out.print(result + "\n");
                }

                query = reader.next();
            }
        }
        System.out.println();
    }
}
