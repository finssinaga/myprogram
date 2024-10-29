package myprogram;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class Scan extends JPanel{
	private JTable table;
	private JToolBar toolBar;
	private JButton btnNewScan;
	private JButton btnSaveScan;
	private JScrollPane scrollPane;
	private JComboBox txtIpaddr;
	private JButton btnX;
	private Main mn;
	
	public Scan(Main mn) {
		this.mn=mn;
		setLayout(new BorderLayout(0, 0));
		
		toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		txtIpaddr = new JComboBox();
		txtIpaddr.setModel(new DefaultComboBoxModel(new String[] {"192.168.0.", "192.168.1.", "192.168.100."}));
		toolBar.add(txtIpaddr);
		
		btnNewScan = new JButton("New Scan");
		
		toolBar.add(btnNewScan);
		
		btnSaveScan = new JButton("Save Scan");
		
		toolBar.add(btnSaveScan);
		
		btnX = new JButton("X");
		btnX.setBackground(Color.RED);
		toolBar.add(btnX);
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setAutoscrolls(true);
		table.setAutoCreateRowSorter(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"no", "Hostname", "IP Address"
			}
		));
		scrollPane.setViewportView(table);
		
		
		btnNewScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tb = (DefaultTableModel)table.getModel();
				tb.setRowCount(0);
				scan(txtIpaddr.getSelectedItem().toString());
			}
		});
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mn.clear();
			}
		});
		btnSaveScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
	}
	private void scan(String range) {
		int i = 1;
		String subnet = range;
		for(i=1;i<=254;i++) {
			String ip = subnet+i;
			final int j = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						
						DefaultTableModel tb = (DefaultTableModel)table.getModel();
						InetAddress address = InetAddress.getByName(ip);
						String host = address.getCanonicalHostName();
						if(address.isReachable(3000)) {
							Object[] rowdata = {j,host,ip};
							tb.addRow(rowdata);
						}else {
							System.out.println(ip+" is unreachable");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	private void save() {
		DefaultTableModel tb = (DefaultTableModel)table.getModel();
		String no,hostname,ip;
		String query = queries.gquery("query_name", "q_insert_scan");
		try {
			Class.forName(vars.Driver());
			Connection con = DriverManager.getConnection(vars.url(),vars.userpass(),vars.userpass());
			for (int i = 1; i < tb.getRowCount(); i++) {
				no = tb.getValueAt(i, 0).toString();
				hostname = tb.getValueAt(i, 1).toString();
				ip = tb.getValueAt(i, 2).toString();
				PreparedStatement prep = con.prepareStatement(query);
				prep.setString(1, no);
				prep.setString(2, hostname);
				prep.setString(3, ip);
				prep.execute();
			} 
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		} finally {
			JOptionPane.showMessageDialog(null, "ok");
		}
	}
	
}
