package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class CalculatorGUI extends JFrame implements ActionListener {
	private Toolkit tk;
	private Dimension screenSize;
	private double frameWidth, frameHeight, frameX, frameY;
	private Container pane;
	private JTextField txtScreen;
	private String screenEntries;
	private char[] singleScreenEntries;
	private JPanel mainPanel;
	private JLabel[] memory; 
	private JButton[] funcButtons; 
	private JButton[] opsButtons;
	private String[] opsToolTips;
	private int toolTipCount;
	//private ImagePanel iPanel;
	
	public CalculatorGUI() {
		super("Calculator");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		tk = Toolkit.getDefaultToolkit();
		screenSize = tk.getScreenSize();
		frameWidth = screenSize.getWidth()/2;
		frameHeight = screenSize.getHeight()/2;
		frameX = frameWidth /2;
		frameY = frameHeight /2;
		pane = getContentPane();
		mainPanel = new JPanel();
		GridLayout paneLayout = new GridLayout(8,1);
		mainPanel.setLayout(paneLayout);
		
		screenEntries = "0";
		
		addNumberScreen(mainPanel);
		
		memory = new JLabel[4];
		addMemoryRow(mainPanel);
		
		funcButtons = new JButton[5];
		addFunctionRow(mainPanel);
		
		opsButtons = new JButton[30];
		opsToolTips = new String[4];
		toolTipCount = 0;
		addOperationPanel1(mainPanel);
		addOperationPanel2(mainPanel);
		addOperationPanel3(mainPanel);
		addOperationPanel4(mainPanel);
		addOperationPanel5(mainPanel);
		pane.add(mainPanel);
		
		setSize((int)frameWidth, (int)frameHeight);
		setLocation((int)frameX, (int)frameY);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton){
			JButton source = (JButton)e.getSource();
			String sourceText = source.getText().toString();
			for(int i = 0; i < opsButtons.length; i++)
			{
				if(source == opsButtons[i]) 
				{
					if(isDecimal(sourceText)) {
						if(!decimalInString(screenEntries)) {
							screenEntries += sourceText;
						}
					}
					else if(isOperator(sourceText)) {
						if(isOperator(getLastEntry(screenEntries)) || isFunction(getLastEntry(screenEntries))){
							screenEntries = backSpace(screenEntries);
						}
						screenEntries += sourceText;
					}
					else
					{
						if(!screenEntries.equals("0")) {
							screenEntries += sourceText;
						}else {
							screenEntries = sourceText;
						}
					}
				}
			}
			for(int i = 0; i < funcButtons.length; i++) {
				if(source == funcButtons[i]) {
					if(i == 0) {
						screenEntries = "0";
					}
					if(!screenEntries.equals("0"))
					{
						switch(i) {
							case 1:
							screenEntries += "¬";
							break;
						case 2:
							screenEntries += "^";
							break;
						case 3:
							try {
								double percent = Double.parseDouble(screenEntries);
								percent = percent/100;
								screenEntries = ""+percent;
							}catch(NumberFormatException Err) {
								JOptionPane.showMessageDialog(this, Err, "Error", JOptionPane.ERROR_MESSAGE);;
							}
	
							break;
						case 4:
							screenEntries = backSpace(screenEntries);
							break;
						}
					}
				}
			}
			if(screenEntries.equals("")){
				screenEntries = "0";
			}
			txtScreen.setText(screenEntries);
		}
	}
	public boolean isFunction(String entry) {
		if(entry.equals("¬") || entry.equals("^")) {
			return true;
		}
		return false;
	}
	public boolean isOperator(String entry) {
		if(entry.equals("/") || entry.equals("x") || entry.equals("+") 
				|| entry.equals("-"))
		{
			return true;
		}
		return false;
	}
	public boolean isDecimal(String entry) {
		if(entry.equals(".")){
			return true;
		}
		return false;
	}
	public boolean isLeftBracket(String entry) {
		if(entry.equals("(")){
			return true;
		}
		return false;
	}
	public boolean isRightBracket(String entry) {
		if(entry.equals(")")){
			return true;
		}
		return false;
	}
	public String getLastEntry(String screenEntries) {
		char[] charEntries = screenEntries.toCharArray();
		return new String(""+charEntries[charEntries.length-1]);
	}
	public boolean decimalInString(String screenEntries) {
		String dec = ".";
		char[] charEntries = screenEntries.toCharArray();
		char[] charDec = dec.toCharArray();
		for(int i = 0; i < charEntries.length; i++) {
			if(charEntries[i] == charDec[0]) {
				return true;
			}
		}
		return false;
	}
	public String backSpace(String screenValue) {
		char[] sv = screenValue.toCharArray();
		char[] newSV = new char[sv.length -1];
		for(int i = 0; i < newSV.length; i++) {
			newSV[i] = sv[i]; 
		}
		return new String(newSV);
	}
	public void addOperationPanel1(JPanel jp) {
		JPanel opsPanel1 = new JPanel();
		GridLayout topLayout = new GridLayout(1,6);
		opsPanel1.setLayout(topLayout);
		for(int i = 0; i <= 5; i++) {
			if(i <  2)
			{
				opsButtons[i] = new JButton("");
				opsButtons[i].setEnabled(false);
			}else {
				switch(i) {
					case 2:
						opsButtons[i] = new JButton("7");
						break;
					case 3:
						opsButtons[i] = new JButton("8");
						break;
					case 4:
						opsButtons[i] = new JButton("9");
						break;
					case 5:
						opsButtons[i] = new JButton("/");
						opsToolTips[toolTipCount] = new String("Divide");
						toolTipCount++;
						break;
				}
				opsButtons[i].setFont(new Font("Calibri", Font.BOLD, 40));
				opsButtons[i].addActionListener(this);
			}
			opsPanel1.add(opsButtons[i]);
		}
		jp.add(opsPanel1);
	}
	public void addOperationPanel2(JPanel jp) {
		JPanel opsPanel2 = new JPanel();
		GridLayout opsPanelLayout = new GridLayout(1,6);
		opsPanel2.setLayout(opsPanelLayout);
		for(int i = 6; i <= 11; i++) {
			if(i < 8) {
				opsButtons[i] = new JButton("");
				opsButtons[i].setEnabled(false);
			}else {
				switch(i) {
					case 8:
						opsButtons[i] = new JButton("4");
						break;
					case 9:
						opsButtons[i] = new JButton("5");
						break;
					case 10:
						opsButtons[i] = new JButton("6");
						break;
					case 11:
						opsButtons[i] = new JButton("x");
						opsToolTips[toolTipCount] = new String("Multiply");
						toolTipCount++;
						break;
					}
				opsButtons[i].addActionListener(this);
				opsButtons[i].setFont(new Font("Calibri", Font.BOLD, 40));
			}
			opsPanel2.add(opsButtons[i]);
		}			
		jp.add(opsPanel2);
	}
	public void addOperationPanel3(JPanel jp) {
		JPanel opsPanel3 = new JPanel();
		GridLayout opsPanel3Layout = new GridLayout(1,6);
		opsPanel3.setLayout(opsPanel3Layout);
		for(int i = 12; i <= 17; i++) {
			if(i < 14) {
				opsButtons[i] = new JButton("");
				opsButtons[i].setEnabled(false);
			}else {
				switch(i) {
					case 14:
						opsButtons[i] = new JButton("1");
						break;
					case 15:
						opsButtons[i] = new JButton("2");
						break;
					case 16:
						opsButtons[i] = new JButton("3");
						break;
					case 17:
					opsButtons[i] = new JButton("+");
					opsToolTips[toolTipCount] = new String("Add");
					toolTipCount++;
					break;
				}	
				opsButtons[i].addActionListener(this);
				opsButtons[i].setFont(new Font("Calibri", Font.BOLD, 40));
			}
			opsPanel3.add(opsButtons[i]);
		}
		jp.add(opsPanel3);
	}
	public void addOperationPanel4(JPanel jp) {
		JPanel opsPanel4 = new JPanel();
		GridLayout opsPanel4Layout = new GridLayout(1,6);
		opsPanel4.setLayout(opsPanel4Layout);
		for(int i = 18; i <= 23; i++) {
			if(i < 21) {
				opsButtons[i] = new JButton("");
				opsButtons[i].setEnabled(false);
			}else {
				switch(i) {
					case 21:
						opsButtons[i] = new JButton("0");
						break;
					case 22:
						opsButtons[i] = new JButton("");
						opsButtons[i].setEnabled(false);
						break;
					case 23:
						opsButtons[i] = new JButton("-");
						opsToolTips[toolTipCount] = new String("Minus");
						break;
				}
				if(i != 22) {
					opsButtons[i].addActionListener(this);
					opsButtons[i].setFont(new Font("Calibri", Font.BOLD, 40));
				}
			}
			opsPanel4.add(opsButtons[i]);
		}
		jp.add(opsPanel4);
		
	}
	public void addOperationPanel5(JPanel jp) {
		JPanel opsPanel5 = new JPanel();
		GridLayout opsPanel5Layout = new GridLayout(1,6);
		opsPanel5.setLayout(opsPanel5Layout);
		for(int i = 24; i <= 29; i++)
		{
			if(i < 26) {
				opsButtons[i] = new JButton("");
				opsButtons[i].setEnabled(false);
			}else
			{
				switch(i) {
					case 26:
						opsButtons[i] = new JButton("(");
						break;
					case 27:
						opsButtons[i] = new JButton(".");
						break;
					case 28:
						opsButtons[i] = new JButton(")");
						break;
					case 29:
						opsButtons[i] = new JButton("=");
						break;
				}
				opsButtons[i].addActionListener(this);
				opsButtons[i].setFont(new Font("Calibri", Font.BOLD, 40));
			}
			opsPanel5.add(opsButtons[i]);
		}
		jp.add(opsPanel5);
	}
	public void addFunctionRow(JPanel jp) {
		JPanel functionPanel = new JPanel();
		GridLayout functionLayout = new GridLayout(1,5);
		functionPanel.setLayout(functionLayout);
		
		funcButtons[0] = new JButton("AC");
		funcButtons[0].setFont(new Font("Calibri", Font.BOLD, 40));
		funcButtons[1] = new JButton("Ab/c");
		funcButtons[1].setFont(new Font("Calibri", Font.BOLD, 40));
		funcButtons[2] = new JButton("xY");
		funcButtons[2].setFont(new Font("Calibri", Font.BOLD, 40));
		funcButtons[3] = new JButton("%");
		funcButtons[3].setFont(new Font("Calibri", Font.BOLD, 40));
		funcButtons[4] = new JButton("BACK");
		funcButtons[4].setFont(new Font("Calibri", Font.BOLD, 40));
		for(int i = 0; i < funcButtons.length; i++) {
			funcButtons[i].addActionListener(this);
			functionPanel.add(funcButtons[i]);
		}
		jp.add(functionPanel);
	}
	public void addMemoryRow(JPanel jp) {
		JPanel memoryPanel = new JPanel();
		GridLayout memoryLayout = new GridLayout(1,4);
		memoryPanel.setLayout(memoryLayout);
		
		memory[0] = new JLabel("M+");
		memory[1] = new JLabel("Ms");
		memory[2] = new JLabel("Mr");
		memory[3] = new JLabel("Mc");
		for(int i = 0; i < memory.length; i++) {
			memory[i].setHorizontalAlignment(JLabel.CENTER);
			memory[i].setFont(new Font("Calibri", Font.BOLD, 40));
			memory[i].setBorder(new LineBorder(Color.BLACK, 2));
			memoryPanel.add(memory[i]);
			memory[i].addMouseListener(new MouseAdapter() {
				JLabel source;
				public void mouseEntered(MouseEvent e) {
					source = (JLabel)e.getSource();
					switch(source.getText().toString()) {
						case "M+":
							source.setToolTipText("Memory Plus");
							break;
						case "Ms":
							source.setToolTipText("Memory Store");
							break;
						case "Mr":
							source.setToolTipText("Memory Recall");
							break;
						case "Mc":
							source.setToolTipText("Memory Clear");
							break;
					}
					source.getToolTipText(e);
					mainPanel.repaint();
				}
				public void mouseClicked(MouseEvent e) {
					source = (JLabel)e.getSource();
					for(int i = 0; i < memory.length; i++) {
						if(source == memory[i]) {
							JOptionPane.showMessageDialog(pane, "you clicked: "+
									source.getText().toString(), "Information", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
		}
		jp.add(memoryPanel);
	}
	public void addNumberScreen(JPanel jp) {
		JPanel screenPanel = new JPanel();
		GridLayout screenLayout = new GridLayout(1,1);
		screenPanel.setLayout(screenLayout);
		txtScreen = new JTextField();
		txtScreen.setHorizontalAlignment(JTextField.RIGHT);
		txtScreen.setFont(new Font("Aerial", Font.BOLD, 40));
		txtScreen.setText(screenEntries);
		txtScreen.setFocusable(false);
		txtScreen.setBorder(new LineBorder(Color.BLACK, 3));
		screenPanel.add(txtScreen);
		
		jp.add(screenPanel);
		
	}
}
