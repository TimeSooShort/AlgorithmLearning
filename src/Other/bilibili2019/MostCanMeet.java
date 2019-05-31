package Other.bilibili2019;

import java.io.*;
import java.util.*;

public class MostCanMeet {

    private static int count = 0;

    public static int mostMeet(boolean[][] arr, int num, int total, int numDirectMeet) {
        boolean[] alreadyMeet = new boolean[total];
        dfs(arr, alreadyMeet, num);
        return count-numDirectMeet-1;
    }

    public static void dfs(boolean[][] arr, boolean[] alreadyMeet, int i) {
        for (int j = 0; j < arr.length; j++) {
            if (!arr[i][j] || alreadyMeet[j]) continue;
            alreadyMeet[j] = true;
            count++;
            dfs(arr, alreadyMeet, j);
        }
    }

    public static int mostMeet(Iterator[] arr, int num, int alreadyKnow) {
        ArrayList<Integer> alreadyMeet = new ArrayList<>();
        alreadyMeet.add(num);
        dfs(arr, alreadyMeet, num);
        return alreadyMeet.size()-alreadyKnow-1;
    }

    private static void dfs(Iterator[] arr, ArrayList<Integer> alreadyMeet, int i) {
        int nextIndex;
        Iterator current = arr[i];
        if (current == null) return;
        while (current.hasNext()){
            if (alreadyMeet.contains(nextIndex= (int) current.next())) continue;
            alreadyMeet.add(nextIndex);
            dfs(arr, alreadyMeet, nextIndex);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("D:\\Temp\\testData.txt")));
        int total = Integer.valueOf(reader.readLine().trim());
        int num = Integer.valueOf(reader.readLine().trim());
        int coupleNum = Integer.valueOf(reader.readLine().trim());

        if (total < 3 || total > 10000 || num < 0 || num >= total
                || coupleNum < 1 && coupleNum>=(total-1)*total/2) System.out.println(0);

        boolean[][] dp = new boolean[total][total];
        String coupleLine;
        while ((coupleLine=reader.readLine()) != null) {
            String[] couples = coupleLine.trim().split(" ");
            for (String couple : couples) {
                int empty = couple.indexOf(",");
                int left = Integer.valueOf(couple.substring(0,empty));
                int right = Integer.valueOf(couple.substring(empty+1));
                dp[left][right] = true;
                dp[right][left] = true;
            }
        }
        int numDirectMeet = 0;
        for (boolean direct : dp[num]) {
            if (direct) numDirectMeet++;
        }
        if ((total-2)*(total-1)/2 < coupleNum) {
            System.out.println(total-numDirectMeet-1);
            return;
        }
        System.out.println(mostMeet(dp, num, total, numDirectMeet));
    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int total = Integer.valueOf(scanner.nextLine());
//        int num = Integer.valueOf(scanner.nextLine());
//        int coupleNum = Integer.valueOf(scanner.nextLine());
//        boolean[][] dp = new boolean[total][total];
//        String couple;
//        while (scanner.hasNextLine()) {
//            couple = scanner.nextLine();
//            int comma = couple.indexOf(",");
//            int left = Integer.valueOf(couple.substring(0,comma));
//            int right = Integer.valueOf(couple.substring(comma+1));
//            dp[left][right] = true;
//            dp[right][left] = true;
//        }
//        int numDirectMeet = 0;
//        for (boolean direct : dp[num]) {
//            if (direct) numDirectMeet++;
//        }
//        if ((total-2)*(total-1)/2 < coupleNum) {
//            System.out.println(total-numDirectMeet-1);
//            return;
//        }
//        System.out.println(mostMeet(dp, num, total, numDirectMeet));
//    }
}
