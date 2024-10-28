package myprogram;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;


public class cmd extends JPanel{

	public cmd(Main mn) {
		setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.NORTH);
		
		JButton btnClear = new JButton("Clear");
		
		toolBar.add(btnClear);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue);
		
		JButton btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mn.clear();
			}
		});
		toolBar.add(btnX);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		JTextArea textArea = new JTextArea();
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if(k.getKeyCode()==10) {
				String tex = textArea.getText();
				String[] line = tex.split("\n");
				String lastline = line[line.length-1];
				
					try {
						Process pc = Runtime.getRuntime().exec(lastline);
						BufferedReader read = new BufferedReader(new InputStreamReader(pc.getInputStream()));
						String outl;
						while((outl=read.readLine())!=null) {
							textArea.setText(textArea.getText()+outl+"\n");
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		scrollPane.setViewportView(textArea);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
	}
	

}
