package calculation;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import gui.CalculatorDisplay;
import numbersystem.Binary;
import numbersystem.Decimal;
import numbersystem.Hexadecimal;
import numbersystem.Octal;

public class Calculate {
	private CalculatorDisplay cd;
	private String[] BIDMAS;
	private ArrayList<String> entries;
	private char[] hexChars;
	private Decimal decimal;
	private Binary binary;
	private Octal octal;
	private Hexadecimal hex;
	
	public Calculate(CalculatorDisplay cd) {
		this.cd = cd;
		BIDMAS = new String[]{"()", "^", "/", "*", "+", "-" };
		entries = new ArrayList<String>();
		hexChars = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		decimal = new Decimal(this);
		binary = new Binary(this);
		octal = new Octal(this);
		hex = new Hexadecimal(this);
	}
	public double makeCalculation(char[] screenEntries, String numSystem) {
		double answer = 0.0;
		return answer;
	}
	public char[] analyze(char[] screenEntries) {
		return screenEntries;
	}
	public double powerOf(double base, double exponent) {
		if((base > 0 && exponent > -1) || (base != 0 && exponent != 0)) {
			return Math.pow(base, exponent);
		}else {
			JOptionPane.showMessageDialog(cd, "Base cannot be zero whilst exponent is zero or negative",
													"Error", JOptionPane.ERROR_MESSAGE);
			return 0.0;
		}
		
	}
	public int[] fraction(String[] fractionArray) {
		 //int answer;ryan did this
		int[] fractionAnswers = new int[10];
		return fractionAnswers;
		//5/10+3/10=answer;
	}
	
	public double percentage(double number) {
		try {
			return number = number/100;			
		}catch(NumberFormatException Err) {
			JOptionPane.showMessageDialog(cd, Err, "Error", JOptionPane.ERROR_MESSAGE);
			return 0.0;
		}
	}
	
	public String decimalToBinary(String screenEntry) {
		return decimal.convertToBinary(screenEntry);
	}
	public String decimalToOct(String screenEntry) {
		return decimal.convertToOct(screenEntry);
	}
	public String decimalToHex(String screenEntry) {
		return decimal.convertToHex(screenEntry, hexChars);
	}
	public String binaryToDecimal(String screenEntry) {
		return binary.convertToDecimal(screenEntry);
	}
	public String binaryToHex(String sreenEntry) {
		String binInDecimal = binary.convertToDecimal(sreenEntry);
		return decimal.convertToHex(binInDecimal, hexChars);
	}
	public String binaryToOctal(String screenEntry) {
		String binInDecimal = binary.convertToDecimal(screenEntry);
		return decimal.convertToOct(binInDecimal);
	}
	
	public String octToDecimal(String screenEntry) {
		return octal.convertToDecimal(screenEntry);
	}
	public String octToBinary(String screenEntry) {
		String octInDec = octal.convertToDecimal(screenEntry);
		return decimal.convertToBinary(octInDec);
	}
	public String octToHex(String screenEntry) {
		String octInDec = octal.convertToDecimal(screenEntry);
		return decimal.convertToHex(octInDec, hexChars);
	}
	public String hexToDecimal(String screenEntry) {
		return hex.convertToDecimal(screenEntry, hexChars);
	}
	public String hexToBinary(String screenEntry) {
		String hexInDecimal = hex.convertToDecimal(screenEntry, hexChars);
		return decimal.convertToBinary(hexInDecimal);
	}
	public String hexToOctal(String screenEntry) {
		String hexInDecimal = hex.convertToDecimal(screenEntry, hexChars);
		return decimal.convertToOct(hexInDecimal);
	}
	
	
	public void cleanUp(char[] screenEntries) {
		
	}
	public CalculatorDisplay getCalcDisplay() {
		return cd;
	}
}
