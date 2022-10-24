
package lfsr_designer;

import javax.swing.*; // Buttons etc.
import java.awt.*; // for Layouts
import java.awt.event.*; // for ActionListener
import javax.swing.JOptionPane; // for ShowMessage
import java.io.*; // for Files
import java.awt.Font;

public class LFSR_Designer {
    	public static void main(String[] args) { 
		LFSRDesignGUI DesignGUI = new LFSRDesignGUI();
		DesignGUI.go();      
	}
}

class LFSRDesignGUI implements ActionListener {
	Frame MSGframe = new JFrame("");
	JFrame frame00;
	
	LFSRDesign Design;

	JPanel panel00;
	JPanel panel01;
	JPanel panel02;

	JTextArea textArea00;
	JScrollPane scroller00;

	JPanel panel20;
	JTextField textField20;
	JLabel label20;

	JPanel panel21;
	JTextField textField21;
	JLabel label21;

	JPanel panel22;
	JTextField textField22;
	JLabel label22;

	JPanel panel23;
	JTextField textField23;
	JLabel label23;

	JPanel panel24;
	JTextField textField24;
	JLabel label24;

        JButton button10;
	JButton button20;
	JButton button21;
	JButton button22;
        
        JCheckBox checkBox10;
        JCheckBox checkBox11;
        JCheckBox checkBox12;
        JCheckBox checkBox13;

        Integer length;
	Integer taps_count;
	Integer taps[];
	Boolean seed[];
	Integer steps;
	Integer output;
        Boolean wrapEnabled;
        Boolean lineNumbersEnabled;
        String mode;
	Integer m;

	void go() {
		frame00 = new JFrame("LFSR Designer");
		frame00.setSize(1000,290);
		frame00.setResizable(false);
		//frame00.setLocationRelativeTo(null);
		frame00.setLocation(500, 100);
		frame00.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel00 = new JPanel();
		frame00.getContentPane().add(BorderLayout.NORTH, panel00);
		panel00.setBackground(Color.GRAY);

		panel01 = new JPanel();
		frame00.getContentPane().add(BorderLayout.CENTER, panel01);
		panel01.setBackground(Color.WHITE);

		panel02 = new JPanel();
		frame00.getContentPane().add(BorderLayout.SOUTH, panel02);
		panel02.setBackground(Color.GREEN);

		textArea00 = new JTextArea(10, 85);
		panel00.add(textArea00);
		textArea00.setBackground(Color.LIGHT_GRAY);
		textArea00.setLineWrap(true);

		scroller00 = new JScrollPane(textArea00);
		scroller00.getVerticalScrollBar().setBackground(Color.LIGHT_GRAY);
		scroller00.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel00.add(scroller00);

		panel02.add(Box.createRigidArea(new Dimension(5,0)));

		panel20 = new JPanel();
		panel02.add(panel20);
		panel20.setBackground(Color.YELLOW);
		
		label20 = new JLabel("Length: ");
		panel20.add(label20);

		textField20 = new JTextField();
		panel20.add(textField20);
		textField20.setColumns(4);
		textField20.setText("8");
		textField20.setBackground(Color.WHITE);

		panel02.add(Box.createRigidArea(new Dimension(5,0)));

		panel21 = new JPanel();
		panel02.add(panel21);
		panel21.setBackground(Color.YELLOW);
		
		label21 = new JLabel("Tap positions: ");
		panel21.add(label21);

		textField21 = new JTextField();
		panel21.add(textField21);
		textField21.setColumns(8);
		textField21.setText("3,4,5");
		textField21.setBackground(Color.WHITE);

		panel02.add(Box.createRigidArea(new Dimension(5,0)));

		panel22 = new JPanel();
		panel02.add(panel22);
		panel22.setBackground(Color.YELLOW);
		
		label22 = new JLabel("Seed: ");
		panel22.add(label22);

		textField22 = new JTextField();
		panel22.add(textField22);
		textField22.setColumns(8);
		textField22.setText("00000001");
		textField22.setBackground(Color.WHITE);

		panel02.add(Box.createRigidArea(new Dimension(5,0)));

		panel23 = new JPanel();
		panel02.add(panel23);
		panel23.setBackground(Color.YELLOW);
		
		label23 = new JLabel("Steps: ");
		panel23.add(label23);

		textField23 = new JTextField();
		panel23.add(textField23);
		textField23.setColumns(4);
		textField23.setText("256");
		textField23.setBackground(Color.WHITE);

		panel02.add(Box.createRigidArea(new Dimension(5,0)));

		panel24 = new JPanel();
		panel02.add(panel24);
		panel24.setBackground(Color.YELLOW);
		
		label24 = new JLabel("Output: ");
		panel24.add(label24);

		textField24 = new JTextField();
		panel24.add(textField24);
		textField24.setColumns(4);
		textField24.setText("7");
		textField24.setBackground(Color.WHITE);
                
                panel01.add(Box.createRigidArea(new Dimension(5,0)));
                
                button10= new JButton("Clear");
		panel01.add(button10);
		button10.addActionListener(this);
                
                checkBox10 = new JCheckBox("WRAP", false);
                panel01.add(checkBox10);
		checkBox10.addActionListener(this);
                        
                checkBox11 = new JCheckBox("LINES", false);
                panel01.add(checkBox11);
		checkBox11.addActionListener(this);
                
               
                checkBox12 = new JCheckBox("BIN mode", true);
                panel01.add(checkBox12);
		//checkBox12.addActionListener(this);
                
                checkBox13 = new JCheckBox("DEC mode", false);
                panel01.add(checkBox13);
		//checkBox13.addActionListener(this);
                
                panel01.add(Box.createRigidArea(new Dimension(550,0)));
                
		panel02.add(Box.createRigidArea(new Dimension(5,0)));

		button20= new JButton("Make");
		panel02.add(button20);
		button20.addActionListener(this);

		button21= new JButton("Open");
		panel02.add(button21);
		button21.addActionListener(this);

		button22= new JButton("Save");
		panel02.add(button22);
		button22.addActionListener(this);
                
                frame00.setVisible(true);

		Design = new LFSRDesign();
	}

	public void actionPerformed(ActionEvent event) {
            
            	if (event.getSource() == button10) {
                    textArea00.setText(null);
                }
                
                if (checkBox10.isSelected()) {
                    wrapEnabled = true;
		} else {
                    wrapEnabled = false;
		}
                
                               
                if ((checkBox10.isSelected())&&(checkBox11.isSelected())) {
                    lineNumbersEnabled = true;
		} else {
                    lineNumbersEnabled = false;
		}
                
		if (event.getSource() == button20) {
			Make();
		}
		if (event.getSource() == button21) {
			// Open file dialog
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Open"); 
                        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                        int result = fileChooser.showOpenDialog(frame00);
                        if (result == JFileChooser.APPROVE_OPTION) {
                                File selectedFile = fileChooser.getSelectedFile();
                                OpenLFSR(selectedFile.getPath());
                        }
		}
		if (event.getSource() == button22) {
			// Save file dialog
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Save");
                        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int userSelection = fileChooser.showSaveDialog(frame00);
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				SaveLFSR(fileToSave.getPath());
			}
		}
	}

	public void Make(){
			length = Integer.valueOf(textField20.getText());
			taps_count = countMatches(textField21.getText());
			taps = new Integer[taps_count];
			m = 0;
			for(String s : textField21.getText().split(",")){
				taps[m] = Integer. parseInt(s);
				m++;
			}
			seed = new Boolean[textField22.getText().length()]; 
			for (int i=0; i<textField22.getText().length(); i++){
				if (textField22.getText().charAt(i)== '0') seed[i] = false;
				if (textField22.getText().charAt(i)== '1') seed[i] = true;
			}
			steps = Integer.valueOf(textField23.getText());;
			output = Integer.valueOf(textField24.getText());
		Design.InitalizeFrame();
		Design.DrawBlock(length, taps, seed); 
                    mode = "DEC";
		textArea00.append(Design.Calcul(length, taps, seed, steps, output, 
                                        wrapEnabled, lineNumbersEnabled, mode)+"\n");
	}

	void SaveLFSR(String fileName) {
                File f = new File(fileName+".lfsr");
                if (f.exists()) { 
                    f.delete(); 
                    JOptionPane.showMessageDialog(MSGframe,"OLD VERSION HAS BEEN DELETED");
                }
		try {
			FileWriter writer = new FileWriter(fileName+".lfsr",true);
			writer.write(textField20.getText()+"\n");
			writer.write(textField21.getText()+"\n");
			writer.write(textField22.getText()+"\n");
			writer.write(textField23.getText()+"\n");
			writer.write(textField24.getText()+"\n");
			writer.close();
			JOptionPane.showMessageDialog(MSGframe,"Saved");
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	void OpenLFSR(String fileName) {
            try {
                //создаем объект FileReader для объекта File
                FileReader reader = new FileReader(fileName);
                //создаем BufferedReader с существующего FileReader для построчного считывания
                BufferedReader bufReader = new BufferedReader(reader);
                // считаем сначала первую строку
                int x = 0;
                String line = bufReader.readLine();
                while (line != null) {
                    switch(x) {
			case 0: 
                            textField20.setText(line);
                            break;
			case 1: 
                            textField21.setText(line);
                            break;
			case 2: 
                            textField22.setText(line);
                            break;                            
			case 3: 
                            textField23.setText(line);
                            break;
			case 4: 
                            textField24.setText(line);
                            break;
                    }
                    // считываем остальные строки в цикле
                    x++;
                    line = bufReader.readLine();
                }
                reader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
	

	int countMatches(String str){
		int count = 1;
		for (char element : str.toCharArray()){
    			if (element == ',') count++;
    		}
	return count;
	}
}

class LFSRDesign {
	JFrame frame01;
	Boolean allowFrameCreation=true;
	Painter p;
	Integer totalBlocks;
	Integer taps[];
	Boolean seed[];

	void InitalizeFrame() {
	if (allowFrameCreation==false) {
		frame01.dispose();
		allowFrameCreation=true;
	}

	if (allowFrameCreation) {
		frame01 = new JFrame("LFSR Design");
		frame01.setSize(1000,200);
		frame01.setResizable(false);
		//frame01.setLocationRelativeTo(null);
		frame01.setLocation(500, 390);
		frame01.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame01.setVisible(true);
		allowFrameCreation=false;
	}
	}

	void DrawBlock(Integer tb, Integer t[], Boolean s[]) {
		totalBlocks = tb;
		taps = new Integer[totalBlocks];
		seed = new Boolean[totalBlocks];
		seed = s;

		for(int i = 0; i < t.length; i++)
			taps[t[i]]=1;
		
		p = new Painter();
		frame01.getContentPane().add(p);
	}

	class Painter extends JPanel {
		// Соединительная точка
		static final Integer ovalDiam = 6;
		// Блок РСЛОС
		static final Integer rect_x = 50;
		static final Integer rect_y = 95;
		static final Integer rect_w = 70;
		static final Integer rect_h = 40;
		static final Integer rect_gap = 30;
		// Вывод номера блока РСЛОС
		static final Integer number_padding_x = 5;
		static final Integer number_padding_y = 15;
		// Вывод значений сида
		static final Integer seed_padding_x = 20;
		static final Integer seed_padding_y = 5;
		// Соединительная линия между блоками РСЛОС
		static final Integer line_x1 = rect_x + rect_w;
		static final Integer line_y1 = rect_y + rect_h/2;
		static final Integer line_x2 = line_x1 + rect_gap;
		static final Integer line_y2 = line_y1;

		public void paintComponent(Graphics g) {
			Font stringFont = new Font( "SansSerif", Font.PLAIN, 14 );
			g.setFont(stringFont);
			g.setColor(Color.red);
			for(Integer i = 0; i < totalBlocks; i++) {
				g.drawRect((rect_w + rect_gap)*i + rect_x,
						rect_y,
						rect_w,
						rect_h);
				g.drawLine((rect_w + rect_gap)*i + line_x1,
						line_y1,
						(rect_w + rect_gap)*i + line_x2,
						line_y2);
				if (taps[i] != null) {
					g.fillOval(line_x1 + rect_gap/2 - ovalDiam/2 + (rect_w + rect_gap)*i,
							line_y1 - ovalDiam/2,
							ovalDiam,
							ovalDiam);
					g.drawLine(line_x1 + rect_gap/2 + (rect_w + rect_gap)*i,
							line_y1 - ovalDiam/2,
							line_x1 + rect_gap/2 + (rect_w + rect_gap)*i,
							line_y2- 2*rect_h+rect_h/2);
				}
				g.drawString(i.toString(), 
							(rect_w + rect_gap)*i + rect_x + number_padding_x, 
							rect_y + number_padding_y);

				if (seed[i]) 
					g.drawString("1", 
						(rect_w + rect_gap)*i + rect_x + rect_w/2 + seed_padding_x, 
						rect_y + rect_h/2 + seed_padding_y);
				else	g.drawString("0", 
						(rect_w + rect_gap)*i + rect_x + rect_w/2 + seed_padding_x, 
						rect_y + rect_h/2 + seed_padding_y);
			}
			g.setColor(Color.green);
			g.drawLine((rect_w + rect_gap)*totalBlocks-rect_w + line_x1,
					line_y1,
					(rect_w + rect_gap)*totalBlocks-rect_w + line_x1,
					line_y2- 2*rect_h);
			g.drawLine((rect_w + rect_gap)*totalBlocks-rect_w + line_x1,
					line_y2- 2*rect_h,
					(rect_w + rect_gap)*totalBlocks-rect_w + line_x1-rect_gap,
					line_y2- 2*rect_h);
			g.drawRect(rect_x,
					rect_y - 2*rect_h,
					(rect_w + rect_gap)*totalBlocks-rect_gap,
					rect_h);
			g.drawLine(rect_x,
					line_y2- 2*rect_h,
					rect_x-rect_gap,
					line_y2- 2*rect_h);
			g.drawLine(rect_x-rect_gap,
					line_y2- 2*rect_h,
					rect_x-rect_gap,
					line_y1);
			g.drawLine(rect_x-rect_gap,
					line_y1,
					rect_x,
					line_y1);
		}
	}

	String Calcul(Integer size, Integer taps[], Boolean position[], Integer steps, Integer output, 
                                                        Boolean wrapEnabled, Boolean lineNumbersEnabled, String mode) {
		Boolean sum;
                String lineNo;
		String result = "";
                
		for (int t = 0; t < steps; t++) {
                    sum = false;
                        
                    // Show result in "output" register
                    if (wrapEnabled) {
                        if (lineNumbersEnabled) lineNo=String.valueOf(t)+": ";
                            else lineNo = "";
			if (position[output]) result=result+lineNo+"1"+"\n";
                            else result=result+lineNo+"0"+"\n";
                    } else {
                        if (position[output]) result=result+"1";
                            else result=result+"0";
			}
                                                    
                    // Calculate XOR
                    for (int i = 0; i < taps.length; i++)
            		sum = (sum ^ position[taps[i]]); 
                    sum = (position[size-1] ^ sum);
                    
                    // Shift one position to the right.
                    for (int i = size-1; i > 0; i--)
                	position[i] = position[i-1];
                    
                    // Put calculated XOR bit to the left end.
                    position[0] = sum;
		}
	return result;  
	}

}




