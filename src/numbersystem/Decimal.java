package numbersystem;

import javax.swing.JOptionPane;

import calculation.Calculate;
import gui.CalculatorDisplay;

public class Decimal {
	private CalculatorDisplay cd;
	private Calculate calculate;
	public Decimal(Calculate calculate) {
		this.calculate = calculate;
		this.cd = calculate.getCalcDisplay();
	}
	public String addition(String screenEntry) {
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
	public String convertToBinary(String screenEntry) {
		String bin = "";
		int dec = 0;
		try {
			dec = Integer.parseInt(screenEntry);
			while(dec != 0) {
				int rem = dec % 2;
				dec /= 2;
				bin = rem + bin;
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(cd, e, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return bin;
	}
	public String convertToOct(String screenEntry) {
		String oct = "";
		int dec = 0;
		try {
			dec = Integer.parseInt(screenEntry);
			while(dec != 0) {
				int rem = dec % 8;
				dec = dec / 8;
				oct = rem + oct;
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(cd, e, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return oct;
	}
	public String convertToHex(String screenEntry, char[] hexChars) {
		String hex = "";
		try {
			int dec = Integer.parseInt(screenEntry);
			while(dec != 0) {
				int rem = dec % 16;
				hex = hexChars[rem]+hex;
				dec /= 16;
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(cd, e, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return hex;
	}
}
