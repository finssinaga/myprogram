package myprogram;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import java.awt.Color;

public class list extends JPanel{
	private JScrollPane scrollPane;
	private JTable table;
	private JToolBar toolBar;
	private JButton btnOpen;
	private JButton btnEdit;
	private JButton btnX;
	private Component horizontalGlue;
	
	public list(Main mn) {
		
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.NORTH);
		
		btnOpen = new JButton("open");
		
		toolBar.add(btnOpen);
		
		btnEdit = new JButton("edit");
		toolBar.add(btnEdit);
		
		horizontalGlue = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue);
		
		btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mn.clear();
			}
		});
		btnX.setBackground(Color.RED);
		toolBar.add(btnX);
		
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setcolumn();
				getlistpc();
			}
		});
	}
	private void getlistpc() {
		
		String query = queries.gquery("query_name", "q_select_list");
		String no,ip,mac;
		try {
			DefaultTableModel tb = (DefaultTableModel)table.getModel();
			Class.forName(vars.Driver());
			Connection con = DriverManager.getConnection(vars.url(),vars.userpass(),vars.userpass());
			Statement stat = con.createStatement();
			ResultSet res = stat.executeQuery(query);
			while(res.next()) {
				no=res.getString(1);
				ip=res.getString(2);
				mac=res.getString(3);
				Object[] rdata = {no,ip,mac};
				tb.addRow(rdata);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void setcolumn() {
		DefaultTableModel tb = (DefaultTableModel)table.getModel();
		tb.setColumnIdentifiers(vars.sqlGetColumn("select * from list"));
	}

}
