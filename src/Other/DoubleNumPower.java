package Other;

public class DoubleNumPower {
	
    public double power(double base, int exponent) {
        double temp = 1.0;
        double answer = 0.0;
        if(exponent > 0) {
            answer = numberPower(base, exponent);
        } 
        else if(exponent < 0) {
            exponent = -exponent;
            answer = numberPower(base, exponent);
            answer = 1.0/answer;
        }
        else {
            return 1;
        }
        return answer;
  }
    public double numberPower(double base , int p) {
        double temp = base;
        double result = 1.0;
        while(p != 0) {
            if((p&1) != 0) {
                result = result * temp;
                if(result > 1.7976931348623157E308) {
                    System.out.println("Խ��");
                    return -1;
                }
            }
            temp *= temp;
            p >>= 1;
        }
        return result;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DoubleNumPower power = new DoubleNumPower();
		double result =  power.power(4.5, 2);
		System.out.println(result);
	}

}
