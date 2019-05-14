package Offer;

import java.util.Arrays;

public class Offer61 {

    public boolean isContinues(int[] numbers) {
        if (numbers == null || numbers.length == 0) return false;
        Arrays.sort(numbers);
        int count = 0;
        while (numbers[count] == 0) {
            count++;
        }
        for (int i = count; i < numbers.length-1; i++) {
            if (numbers[i] == numbers[i+1]) return false;
            else if (numbers[i+1]-numbers[i] > 1) {
                int needZero = numbers[i+1]-numbers[i]-1;
                if (count < needZero) return false;
                count -= needZero;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(-11%5);
    }
}
