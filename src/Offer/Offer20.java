package Offer;

public class Offer20 {

    private int index = 0;

    // A[.[B]][e|EC] || .B[e|EC]
    public boolean solve(String number) {
        if (number == null) return false;
        char[] numArr = number.toCharArray();
        boolean result = false;
        // .xxx
        if (numArr[0] == '.') {
//            ++index;
//            if (index == numArr.length || numArr[index] == 'e'
//                    || numArr[index] == 'E') return false; // "." or ".e" or ".E"
//            result = scanB(numArr);
//            if (result && index != numArr.length) { // .xxxE/e
//                if (index == numArr.length-1) return false;
//                ++index;
//                result = scanC(numArr);
//            }
            result = commonCode(numArr);
        }else {
            result = scanA(numArr);
            if (result && index != numArr.length) {
                if ((index == numArr.length-1)) {
//                    if (numArr[index] == '.') return true; // 123. == 123.0
//                    else return false; // 123E / 123e
                    return numArr[index] == '.';
                }
                if (numArr[index] == '.') {
//                    ++index;
//                    if (numArr[index] == 'e' || numArr[index] == 'E') return false; // ".e" or ".E"
//                    result = scanB(numArr);
//                    if (result && index != numArr.length) {
//                        if (index == numArr.length-1) return false; // xxx.xxxE/e
//                        ++index;
//                        result = scanC(numArr);
//                    }
                    result = commonCode(numArr);
                }else {
                    ++index;
                    result = scanC(numArr);
                }
            }
        }
        return result;
    }

    private boolean commonCode(char[] numArr) {
        ++index;
        if (index == numArr.length || numArr[index] == 'e' || numArr[index] == 'E') return false; // ".e" or ".E"
        boolean result = scanB(numArr);
        if (result && index != numArr.length) {
            if (index == numArr.length-1) return false; // xxx.xxxE/e
            ++index;
            result = scanC(numArr);
        }
        return result;
    }

    private boolean scanA(char[] num) {
        if (num[index] == '+' || num[index] == '-') {
            ++index;
            if (index == num.length) return false;
        }
        while(index < num.length && num[index] >= '0' && num[index] <= '9') ++index;
        return index == num.length || num[index] == '.' || num[index] == 'e' || num[index] == 'E';
    }

    private boolean scanB(char[] num) {
        while (index < num.length && num[index] >= '0' && num[index] <= '9') ++index;
        return index == num.length || num[index] == 'e' || num[index] == 'E';
    }

    private boolean scanC(char[] num) {
        if (num[index] == '+' || num[index] == '-') {
            ++index;
            if (index == num.length) return false;
        }
        while(index < num.length && num[index] >= '0' && num[index] <= '9') ++index;
        return index == num.length;
    }

    public static void main(String[] args) {
        Offer20 instance = new Offer20();
        String[] arr = {"0.33e4", ".3456E0", "23524.2456", "8526985", "928.",
                "24536.000", "3453e245", "3456E45"}; //都是合格的
        String[] err = {"0.33e4e", ".345a6E0", "23524.24.56", "85e269E85", ".",
                ".E", "0.235E", ".e324", "E", "2345.e34", "e"}; // 都是不合格的
        for (String s : arr) {
            if (!instance.solve(s)) {
                System.out.println(s + " failure");
                break;
            }
            instance.index = 0;
            System.out.println(s + " success");
        }

        System.out.println("==============================");

        for (String s : err) {
            if (instance.solve(s)) {
                System.out.println(s + " failure");
                break;
            }
            instance.index = 0;
            System.out.println(s + " success");
        }
    }
}
