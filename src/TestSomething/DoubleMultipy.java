package TestSomething;

import java.math.BigDecimal;

public class DoubleMultipy {
    public static Double multiply(Double a, Double b) {
        BigDecimal a1 = new BigDecimal(Double.toString(a));
        BigDecimal b1 = new BigDecimal(Double.toString(b));
        return a1.multiply(b1).doubleValue();
    }

    public static int solution(int number) {
        int i3 = (number-1)/3;
        int i5 = (number-1)/5;
        int i15 = (number-1)/15;
        int sum = (1+i3)*i3/2*3 + (1+i5)*i5/2*5;
        sum -= (1+i15)*i15/2*15;
        return sum;
    }

    public static void main(String[] args) {
        //System.out.println(solution(200));
        String s = "I should have known that you would have a perfect answer for me!!!";
        System.out.println(s.length());
        System.out.println('\uD800');
        System.out.println('\uDBFF'+1);
    }
}
