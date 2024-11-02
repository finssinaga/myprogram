package myprogram;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.Box;
import java.awt.Font;

public class MasterBarang extends JPanel{
	private JToolBar toolBar;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JToolBar toolBar_1;
	private JSplitPane splitPane_1;
	private JLabel lblId;
	private JTextField id;
	private JSplitPane splitPane_2;
	private JLabel lblKategori;
	private JComboBox kategori;
	private JSplitPane splitPane_3;
	private JLabel lblNamaBarang;
	private JTextField nama;
	private JSplitPane splitPane_4;
	private JLabel lblHarga;
	private JTextField harga;
	private JSplitPane splitPane_5;
	private JLabel lblSatuan;
	private JComboBox csatuan;
	private JButton btnInput;
	private JButton btnHapus;
	private JButton btnCancel;
	private JButton btnX;
	private JSplitPane splitPane_6;
	private JToolBar toolBar_2;
	private Component horizontalGlue;
	private Component horizontalGlue_1;
	private Component horizontalGlue_2;
	private JLabel lblFormMasterBarang;
	
	public MasterBarang(Main mn) {
		setLayout(new BorderLayout(0, 0));
		
		splitPane_6 = new JSplitPane();
		splitPane_6.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_6.setDividerSize(0);
		add(splitPane_6, BorderLayout.NORTH);
		toolBar = new JToolBar();
		splitPane_6.setRightComponent(toolBar);
		toolBar.setFloatable(false);
		
		btnInput = new JButton("Input");
		
		toolBar.add(btnInput);
		
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tb = (DefaultTableModel) table.getModel();
				String id = null,kategori = null,nama = null,harga = null,satuan = null;
				String kategorisub,satuansub;
				
				id = MasterBarang.this.id.getText();
				kategori = MasterBarang.this.kategori.getSelectedItem().toString();
				nama = MasterBarang.this.nama.getText();
				harga = MasterBarang.this.harga.getText();
				satuan = MasterBarang.this.csatuan.getSelectedItem().toString();
				
				kategorisub = kategori.substring(0,kategori.indexOf("."));
				satuansub = satuan.substring(0,satuan.indexOf("."));
				
				if (id.equals("")||nama.equals("")||harga.equals("")) {
					JOptionPane.showMessageDialog(null, "ada yang belum diisi");
				}else if(id.trim().isEmpty()||nama.trim().isEmpty()||harga.trim().isEmpty()){
					JOptionPane.showMessageDialog(null, "ada yang belum diisi");
				}else {
					String[] rowdata = { id, kategorisub, nama, harga, satuansub };
					tb.addRow(rowdata);
					settext();
				}
			}
		});
		
		btnHapus = new JButton("Hapus");
		
		toolBar.add(btnHapus);
		
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DefaultTableModel tb = (DefaultTableModel) table.getModel();
					tb.removeRow(table.getSelectedRow());
				} catch (Exception hps) {
					JOptionPane.showMessageDialog(null, "tidak ada baris yang dipilih");
				}
			}
		});
		
		horizontalGlue = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue);
		
		JButton btnSimpan = new JButton("Simpan");
		
		toolBar.add(btnSimpan);
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tb = (DefaultTableModel) table.getModel();
				String query = queries.gquery("query_name", "q_insert_master_barang");
				try {
					Class.forName(vars.Driver());
					Connection con = DriverManager.getConnection(vars.url(), vars.userpass(), vars.userpass());
					PreparedStatement prep = con.prepareStatement(query);
					for (int i = 0; i < tb.getRowCount(); i++) {
						prep.setInt(1, Integer.parseInt((String) tb.getValueAt(i, 0)));
						prep.setInt(2, Integer.parseInt((String) tb.getValueAt(i, 1)));
						prep.setString(3, (String) tb.getValueAt(i, 2));
						prep.setInt(4, Integer.parseInt((String) tb.getValueAt(i, 3)));
						prep.setInt(5, Integer.parseInt((String) tb.getValueAt(i, 4)));
						prep.addBatch();
					}
					prep.executeBatch();
					JOptionPane.showMessageDialog(null, "ok");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
				tb.setRowCount(0);
			}
		});
		
		horizontalGlue_1 = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue_1);
		
		btnCancel = new JButton("Cancel");
		
		toolBar.add(btnCancel);
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tb = (DefaultTableModel) table.getModel();
				tb.setRowCount(0);
			}
		});
		
		toolBar_2 = new JToolBar();
		toolBar_2.setFloatable(false);
		splitPane_6.setLeftComponent(toolBar_2);
		
		lblFormMasterBarang = new JLabel("Form Master Barang");
		lblFormMasterBarang.setFont(new Font("Tahoma", Font.BOLD, 18));
		toolBar_2.add(lblFormMasterBarang);
		
		horizontalGlue_2 = Box.createHorizontalGlue();
		toolBar_2.add(horizontalGlue_2);
		
		btnX = new JButton("   X   ");
		toolBar_2.add(btnX);
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mn.clear();
			}
		});
		btnX.setBackground(Color.RED);
		
		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerSize(20);
		add(splitPane, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		toolBar_1.setOrientation(SwingConstants.VERTICAL);
		splitPane.setLeftComponent(toolBar_1);
		
		splitPane_1 = new JSplitPane();
		toolBar_1.add(splitPane_1);
		
		lblId = new JLabel("ID");
		splitPane_1.setLeftComponent(lblId);
		
		id = new JTextField();
		splitPane_1.setRightComponent(id);
		id.setColumns(10);
		
		splitPane_2 = new JSplitPane();
		toolBar_1.add(splitPane_2);
		
		lblKategori = new JLabel("Kategori");
		splitPane_2.setLeftComponent(lblKategori);
		
		kategori = new JComboBox();
		splitPane_2.setRightComponent(kategori);
		
		splitPane_3 = new JSplitPane();
		toolBar_1.add(splitPane_3);
		
		lblNamaBarang = new JLabel("Nama Barang");
		splitPane_3.setLeftComponent(lblNamaBarang);
		
		nama = new JTextField();
		splitPane_3.setRightComponent(nama);
		nama.setColumns(10);
		
		splitPane_4 = new JSplitPane();
		toolBar_1.add(splitPane_4);
		
		lblHarga = new JLabel("Harga");
		splitPane_4.setLeftComponent(lblHarga);
		
		harga = new JTextField();
		splitPane_4.setRightComponent(harga);
		harga.setColumns(10);
		
		splitPane_5 = new JSplitPane();
		toolBar_1.add(splitPane_5);
		
		lblSatuan = new JLabel("Satuan");
		splitPane_5.setLeftComponent(lblSatuan);
		
		csatuan = new JComboBox();
		splitPane_5.setRightComponent(csatuan);
		setlayout();
	}
	private void splitpane() {
		Component[] com = toolBar_1.getComponents();
		ArrayList<JSplitPane> jsplit = new ArrayList<>();
		for(Component c : com) {
			if (c instanceof JSplitPane) {
				jsplit.add((JSplitPane) c);
			}
		}
		for(JSplitPane pane : jsplit) {
			pane.setDividerLocation(pane.getWidth()/12);
			pane.setDividerSize(0);
		}
		
	}
	private ArrayList<Component> getcomponent(JComponent comp) {
		Component[] split = comp.getComponents();
		ArrayList<Component> spl = new ArrayList<>();
		for(Component tb : split) {
			if(tb instanceof JSplitPane) {
			spl.add((JSplitPane) tb);
			}
		}
		return spl;
	}
	private void settext() {
		ArrayList<Component> splitpane = getcomponent(toolBar_1);
		ArrayList<JComponent> splits = new ArrayList<>();
		
		for(Component c : splitpane) {
			if(c instanceof JSplitPane) {
				Component[] text = ((JSplitPane) c).getComponents();
				for(Component d : text) {
					if(d instanceof JTextField) {
						((JTextField) d).setText("");
					}
				}
				
			}
		}
	}
	private void setlayout() {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				vars.setcomboboxmodel(kategori, queries.gquery("query_name", "c_kategori_model"));
				vars.setcomboboxmodel(csatuan, queries.gquery("query_name", "c_satuan_model"));
				DefaultTableModel tb = (DefaultTableModel) table.getModel();
				if(tb.getColumnCount()==0) {
					splitpane();
					for(String p : vars.sqlGetColumn(queries.gquery("query_name","q_select_master_barang"))) {
						tb.addColumn(p);
					}
				};
			}
		});
		
	}

}
