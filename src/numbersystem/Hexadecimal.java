package numbersystem;

import calculation.Calculate;
import gui.CalculatorDisplay;
import main.Calculator;

public class Hexadecimal {
	private CalculatorDisplay cd;
	private Calculate calculate;
	public Hexadecimal(Calculate calculate) {
		this.calculate = calculate;
		this.cd = calculate.getCalcDisplay();
	}
	public String convertToDecimal(String screenEntry, char[] hexChars) {
		int dec = 0;
		int decMultiplier = 0;
		int base = 16;
		int exponent = 0;
		char[] screenEntries = screenEntry.toCharArray();
		for(int i = screenEntries.length-1; i >=0; i--) {
			for(int j = 0; j < hexChars.length; j++) {
				if(screenEntries[i] == hexChars[j]) {
					decMultiplier = j;
					dec += decMultiplier*calculate.powerOf(base, exponent);
				}
			}
			exponent++;
		}
		return ""+dec;
	}
	
}
