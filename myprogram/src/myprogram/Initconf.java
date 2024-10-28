package myprogram;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;

public class Initconf extends JPanel{
	private JTable table;
	
	public Initconf(Main mn) {
		
		setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.NORTH);
		
		JButton btnCreateConfig = new JButton("Create Config");
		
		toolBar.add(btnCreateConfig);
		
		JButton btnSaveConfig = new JButton("Save Config");
		
		toolBar.add(btnSaveConfig);
		
		JButton btnOpenConfig = new JButton("Open Config");
		
		toolBar.add(btnOpenConfig);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue);
		
		JButton btnX = new JButton("X");
		btnX.setBackground(Color.RED);
		
		toolBar.add(btnX);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Key", "Values"
			}
		));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		toolBar_1.setOrientation(SwingConstants.VERTICAL);
		scrollPane.setRowHeaderView(toolBar_1);
		
		JButton btnAddLine = new JButton("Add line");
		btnAddLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tb = (DefaultTableModel)table.getModel();
				tb.setRowCount(tb.getRowCount()+1);
			}
		});
		toolBar_1.add(btnAddLine);
		
		JButton btnRemoveLine = new JButton("Remove Line");
		btnRemoveLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tb = (DefaultTableModel)table.getModel();
				tb.removeRow(tb.getRowCount()-1);
			}
		});
		toolBar_1.add(btnRemoveLine);
		
		btnSaveConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tb = (DefaultTableModel)table.getModel();
				String[] key = new String[tb.getRowCount()],value = new String[tb.getRowCount()];
				for(int i=0;i<tb.getRowCount();i++) {
					key[i]=tb.getValueAt(i, 0).toString();
					value[i]=tb.getValueAt(i, 1).toString();
				}
				try {
					vars.confsetarray(key, value);
				} catch (Exception e1) {
					e1.printStackTrace();
				}finally {
					JOptionPane.showMessageDialog(null, "ok");
				}
				tb.setRowCount(0);
			}
		});
		btnCreateConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mn.setdefault();
			}
		});
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mn.clear();
			}
		});
		btnOpenConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tb = (DefaultTableModel)table.getModel();
				tb.setRowCount(0);
				for(String read : readconf()) {
					if(read.contains("#")) {
						continue;
					}else {
					String key = read.substring(0,read.indexOf("="));
					String value = read.substring(read.indexOf("=")+1);
					Object[] d = {key,value};
					tb.addRow(d);
					}
				}
			}
		});
	}
	
	private int readline() {
		int line = 0;
		try {
			BufferedReader b = new BufferedReader(new FileReader("config/set.conf"));
			while(b.readLine()!=null) {
				line++;
			}
			b.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return line;
	}
	private String[] readconf() {
		String[] data=new String[readline()];
		for(int i=0;i<readline();i++) {
			try {
				data[i] = Files.readAllLines(Paths.get("config/set.conf")).get(i);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}

}
