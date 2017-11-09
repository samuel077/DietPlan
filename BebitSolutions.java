import java.util.HashMap;
import java.util.Map;

public class BebitSolutions {

	public static final int A = 3;
	public static final int B = 2;
	public static final int N = 5;
	public static int finalWeight = 0;
	public static Map<Integer, Integer> map = new HashMap<>();
	public static String pattern = "";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initial();
		calculateWeight(70, 1, 0, "");
		
		for(int i = 0 ; i < pattern.length() ; i++) {
			char chr = pattern.charAt(i);
			if(chr == '0')
				System.out.println("Day " + (i+1) +" not eat");
			else
				System.out.println("Day " + (i+1) + " eat");
		}
		System.out.println("final weight = " + finalWeight);
		
	}

	private static void initial() {
		// TODO Auto-generated method stub
		map.put(new Integer(1), new Integer(4));
		map.put(new Integer(2), new Integer(6));
		map.put(new Integer(3), new Integer(2));
		map.put(new Integer(4), new Integer(5));
		map.put(new Integer(5), new Integer(3));
	}
	
	public static void calculateWeight(int currentWeight, int NthDays, int stressPoint, String str) {
		if(NthDays == N) {
			// last day, compare global variables
			int eatWeight = currentWeight + map.get(NthDays);
			int notEatWeight = currentWeight - A + stressPoint * B;
			
			if(finalWeight == 0) {
				// initialize
				finalWeight = Math.min(eatWeight, notEatWeight);
				if(finalWeight == eatWeight)
					pattern = str + '1'; 
				else if(finalWeight == notEatWeight)
					pattern = str + '0';
			} else {
				// final has something.
				if(eatWeight < notEatWeight && eatWeight < finalWeight) {
					// update final as eatWeight
					finalWeight = eatWeight;
					pattern = str + '1';
				} else if(notEatWeight < eatWeight && notEatWeight < finalWeight) {
					finalWeight = notEatWeight;
					pattern = str + '0';
				}
			}
		} else {
			// not the last day, keep choosing. 
			// make a decision, and calculate 
			int weightEatToday = currentWeight + map.get(NthDays);
			int weightNotEatToday = currentWeight - A;

			calculateWeight(weightEatToday, NthDays + 1, 0, str + '1');
			calculateWeight(weightNotEatToday, NthDays + 1, stressPoint + 1, str + '0');
		}
	}
}
