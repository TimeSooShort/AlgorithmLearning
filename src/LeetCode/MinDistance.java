package LeetCode;

public class MinDistance {

	public MinDistance() {
		// TODO Auto-generated constructor stub
	}
	
    public int minDistance(String word1, String word2) {
        StringBuilder b1 = new StringBuilder(word1);
        StringBuilder b2 = new StringBuilder(word2);
        int change = 0;
        int index = 0;
        while(!b1.toString().equals(b2.toString())) {
            if (index >= b1.length() || index >= b2.length()) {
                int remaining = index == b1.length() ? b2.length()-index : b1.length()-index;
                change = change + remaining;
                break;
            }
            String b1String = b1.charAt(index) + "";
            String b2String = b2.charAt(index) + "";
            int firstB1InB2 = b2.indexOf(b1String);
            //System.out.println("firstB1InB2: " + b1.charAt(index) + ": " + firstB1InB2);
            int firstB2InB1 = b1.indexOf(b2String);
            //System.out.println("firstB2InB1: " + b2.charAt(index) + ": " + firstB2InB1);
            if((firstB1InB2 < firstB2InB1 || firstB2InB1 == -1) && firstB1InB2 != -1) {
                b2 = b2.deleteCharAt(index);
                change++;
            } else if((firstB1InB2 > firstB2InB1 || firstB1InB2 == -1) && firstB2InB1 != -1 ){
                b1 = b1.deleteCharAt(index);
                change++;
            } else if(firstB1InB2 == -1 && firstB2InB1 == -1){
                b2 = b2.deleteCharAt(index);
                change++;
            } else {
                index++;
            }
        }
        return change;
    }
    
    public static void main(String[] args) {
//		String a1 = "sea";
//		String a2 = "eat";
    	String b1 = "sumonstgloy";
    	String b2 = "strgmongyu";
		MinDistance distance = new MinDistance();
		int  minLength = distance.minDistance(b1, b2);
		System.out.println(minLength);
	}
}
