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
	private JSeparator separator;
	private JButton btnInput;
	private JButton btnHapus;
	private JButton btnCancel;
	private JSeparator separator_1;
	private JButton btnX;
	
	public MasterBarang(Main mn) {
		setLayout(new BorderLayout(0, 0));
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.NORTH);
		
		JButton btnTambah = new JButton("Tambah");
		
		toolBar.add(btnTambah);
		
		JButton btnSimpan = new JButton("Simpan");
		
		toolBar.add(btnSimpan);
		
		separator = new JSeparator();
		toolBar.add(separator);
		
		btnInput = new JButton("Input");
		
		toolBar.add(btnInput);
		
		btnHapus = new JButton("Hapus");
		
		toolBar.add(btnHapus);
		
		btnCancel = new JButton("Cancel");
		
		toolBar.add(btnCancel);
		
		separator_1 = new JSeparator();
		toolBar.add(separator_1);
		
		btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mn.clear();
			}
		});
		btnX.setBackground(Color.RED);
		toolBar.add(btnX);
		
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
		
		
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setcombmodel(kategori, queries.gquery("query_name", "c_kategori_model"));
				setcombmodel(csatuan, queries.gquery("query_name", "c_satuan_model"));
				DefaultTableModel tb = (DefaultTableModel) table.getModel();
				if(tb.getColumnCount()==0) {
					splitpane();
					for(String p : vars.sqlGetColumn(queries.gquery("query_name","q_select_master_barang"))) {
						tb.addColumn(p);
					}
				};
			}
		});
		
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tb = (DefaultTableModel) table.getModel();
				String id = null,kategori = null,nama = null,harga = null,satuan = null;
				id = MasterBarang.this.id.getText();
				kategori = MasterBarang.this.kategori.getSelectedItem().toString().substring(0,1);
				nama = MasterBarang.this.nama.getText();
				harga = MasterBarang.this.harga.getText();
				satuan = MasterBarang.this.csatuan.getSelectedItem().toString().substring(0,1);
				
				if (id.equals("")||nama.equals("")||harga.equals("")) {
					JOptionPane.showMessageDialog(null, "ada yang belum diisi");
				}else if(id.trim().isEmpty()||nama.trim().isEmpty()||harga.trim().isEmpty()){
					JOptionPane.showMessageDialog(null, "ada yang belum diisi");
				}else {
					String[] rowdata = { id, kategori, nama, harga, satuan };
					tb.addRow(rowdata);
					settext();
				}
			}
		});
		
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
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tb = (DefaultTableModel) table.getModel();
				tb.setRowCount(0);
			}
		});
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tb = (DefaultTableModel) table.getModel();
				
			}
		});
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
	private void setcombmodel(JComboBox<String> c, String query) {

		try {
			Class.forName(vars.Driver());
			Connection con = DriverManager.getConnection(vars.url(), vars.userpass(), vars.userpass());
			Statement stat = con.createStatement();
			ResultSet res = stat.executeQuery(query);
			while (res.next()) {
				c.addItem(res.getString(1));
			} 
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}

}
