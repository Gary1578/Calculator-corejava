package numbersystem;

import calculation.Calculate;
import gui.CalculatorDisplay;

public class Octal {
	private CalculatorDisplay cd;
	private Calculate calculate;
	public Octal(Calculate calculate) {
		this.calculate = calculate;
		this.cd = calculate.getCalcDisplay();
	}
	public String convertToDecimal(String screenEntry) {
		double dec = 0.0;
		double octMultiplier = 0.0;
		char[] octNumbers = screenEntry.toCharArray(); 
		int base = 8;
		int exponent = 0;
		for(int i = octNumbers.length-1; i >= 0; i--) {
			if(octNumbers[i] != '0') {
				String octNum = String.valueOf(octNumbers[i]);
				octMultiplier = Double.parseDouble(octNum);
				dec += octMultiplier*calculate.powerOf(base, exponent);
			}
			exponent++;
		}
		int decimal = (int)dec;
		return ""+decimal;
	}
}
