package numbersystem;

import calculation.Calculate;

public class Binary {
	private Calculate calculate;
	public Binary(Calculate calculate) {
		this.calculate = calculate;
	}
	public String convertToDecimal(String screenEntry) {
		double dec = 0.0;
		char[] binNumbers = screenEntry.toCharArray();
		int base = 2;
		int exponent = 0;
		for(int i = binNumbers.length-1; i >= 0; i--) {
			if(binNumbers[i] == '1') {
				dec += calculate.powerOf(base, exponent);
			}
			exponent++;
		}
		int decimal = (int)dec;
		return ""+decimal;
	}
	public String addtion(String screenEntry) {
		return "";
	}
	public String subtraction(String screenEntry) {
		return "";
	}
	public String multiplication(String screenEntry) {
		return "";
	}
	public String division(String screenEntry) {
		return "";
	}
}
