package myprogram;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.Box;
import java.awt.Font;
import java.awt.Color;

public class stok extends JPanel{
	private JTextField in_stok;
	private JTable in_table;
	private JComboBox in_nama;
	private JComboBox in_kategori;
	private JButton in_tambah;
	private JButton in_simpan;
	private JButton op_tambah;
	private JButton op_input;
	private JButton op_simpan;
	private JTable op_table;
	private JButton btnInput;
	private JComboBox op_kategori;
	private JComboBox op_nama;
	private JTextField op_jumlah;
	private JToolBar toolBar_1;
	private JToolBar toolBar_4;
	public stok() {
		setLayout(new BorderLayout(0, 0));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("master stok", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel.add(toolBar, BorderLayout.NORTH);
		
		op_tambah = new JButton("tambah");
		
		toolBar.add(op_tambah);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue_3);
		
		op_input = new JButton("input");
		
		toolBar.add(op_input);
		
		op_simpan = new JButton("simpan");
		
		toolBar.add(op_simpan);
		
		Component horizontalGlue_4 = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue_4);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		op_table = new JTable();
		scrollPane.setViewportView(op_table);
		
		toolBar_4 = new JToolBar();
		toolBar_4.setOrientation(SwingConstants.VERTICAL);
		toolBar_4.setFloatable(false);
		splitPane.setLeftComponent(toolBar_4);
		
		JSplitPane splitPane_5 = new JSplitPane();
		toolBar_4.add(splitPane_5);
		
		JLabel lblId = new JLabel("kategori");
		splitPane_5.setLeftComponent(lblId);
		
		op_kategori = new JComboBox();
		
		splitPane_5.setRightComponent(op_kategori);
		
		JSplitPane splitPane_6 = new JSplitPane();
		toolBar_4.add(splitPane_6);
		
		JLabel lblNamaBarang_1 = new JLabel("nama barang");
		splitPane_6.setLeftComponent(lblNamaBarang_1);
		
		op_nama = new JComboBox();
		splitPane_6.setRightComponent(op_nama);
		
		JSplitPane splitPane_7 = new JSplitPane();
		toolBar_4.add(splitPane_7);
		
		JLabel lblJumlah = new JLabel("jumlah");
		splitPane_7.setLeftComponent(lblJumlah);
		
		op_jumlah = new JTextField();
		splitPane_7.setRightComponent(op_jumlah);
		op_jumlah.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("update stok", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_4 = new JSplitPane();
		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(splitPane_4, BorderLayout.CENTER);
		
		toolBar_1 = new JToolBar();
		splitPane_4.setLeftComponent(toolBar_1);
		toolBar_1.setOrientation(SwingConstants.VERTICAL);
		toolBar_1.setFloatable(false);
		
		JSplitPane splitPane_1 = new JSplitPane();
		toolBar_1.add(splitPane_1);
		
		JLabel lblKategori = new JLabel("kategori");
		splitPane_1.setLeftComponent(lblKategori);
		
		in_kategori = new JComboBox();
		in_kategori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = in_kategori.getSelectedItem().toString();
				in_nama.removeAllItems();
				vars.setcomboboxmodel(in_nama, "select nama from master_barang where kategori='"+id.substring(0,id.indexOf("."))+"'");
			}
		});
		splitPane_1.setRightComponent(in_kategori);
		
		JSplitPane splitPane_2 = new JSplitPane();
		toolBar_1.add(splitPane_2);
		
		JLabel lblNamaBarang = new JLabel("nama barang");
		splitPane_2.setLeftComponent(lblNamaBarang);
		
		in_nama = new JComboBox();
		splitPane_2.setRightComponent(in_nama);
		
		JSplitPane splitPane_3 = new JSplitPane();
		toolBar_1.add(splitPane_3);
		
		JLabel lblSatuan = new JLabel("jumlah stok");
		splitPane_3.setLeftComponent(lblSatuan);
		
		in_stok = new JTextField();
		splitPane_3.setRightComponent(in_stok);
		in_stok.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane_4.setRightComponent(scrollPane_1);
		
		in_table = new JTable();
		scrollPane_1.setViewportView(in_table);
		
		JToolBar toolBar_2 = new JToolBar();
		toolBar_2.setFloatable(false);
		panel_1.add(toolBar_2, BorderLayout.NORTH);
		
		in_tambah = new JButton("tambah");
		
		toolBar_2.add(in_tambah);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		toolBar_2.add(horizontalGlue_1);
		
		btnInput = new JButton("input");
		
		toolBar_2.add(btnInput);
		
		in_simpan = new JButton("simpan");
		
		toolBar_2.add(in_simpan);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		toolBar_2.add(horizontalGlue_2);
		
		JToolBar toolBar_3 = new JToolBar();
		toolBar_3.setFloatable(false);
		add(toolBar_3, BorderLayout.NORTH);
		
		JLabel lblFormStokBarang = new JLabel("Form Stok Barang");
		lblFormStokBarang.setFont(new Font("Tahoma", Font.BOLD, 18));
		toolBar_3.add(lblFormStokBarang);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		toolBar_3.add(horizontalGlue);
		
		JButton btnX = new JButton("   X  ");
		btnX.setBackground(new Color(255, 0, 0));
		toolBar_3.add(btnX);
		
		in_tambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tb = (DefaultTableModel) in_table.getModel();
				String stok = in_stok.getText().toString();
				String kategori = in_kategori.getSelectedItem().toString(),
						nama = in_nama.getSelectedItem().toString(),
						id=vars.getsinglestring("select id from master_barang where nama='"+nama+"'");
				String subkategori = kategori.substring(0,kategori.indexOf("."));
				if (stok.equals("") ||kategori.equals("")||nama.equals("")){
					JOptionPane.showMessageDialog(null, "nilai tidak boleh 0 / ada yang belum diisi");
				}else {
					Object[] rowdata = {id, subkategori, nama, stok };
					tb.addRow(rowdata);
					in_stok.setText("");
				}
			}
		});
		
		in_simpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tb = (DefaultTableModel) in_table.getModel();
				String[] data = new String[tb.getRowCount()];
				for(int i=0;i<tb.getRowCount();i++) {
					data[i]=tb.getValueAt(i, 3).toString();
					vars.insertdata(data, "update stok_barang set jumlah=? where id='"+tb.getValueAt(i, 0)+"'");
				}
				tb.setRowCount(0);
			}
		});
		op_tambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		op_kategori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = op_kategori.getSelectedItem().toString();
				op_nama.removeAllItems();
				vars.setcomboboxmodel(op_nama, "select nama from master_barang where kategori='"+id.substring(0,id.indexOf("."))+"'");
			}
		});
		op_input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tb = (DefaultTableModel) op_table.getModel();
				String stok = op_jumlah.getText().toString();
				String kategori = op_kategori.getSelectedItem().toString(),
						nama = op_nama.getSelectedItem().toString(),
						id=vars.getsinglestring("select id from master_barang where nama='"+nama+"'");
				String subkategori = kategori.substring(0,kategori.indexOf("."));
				if (stok.equals("") ||kategori.equals("")||nama.equals("")){
					JOptionPane.showMessageDialog(null, "nilai tidak boleh 0 / ada yang belum diisi");
				}else {
					Object[] rowdata = {id, subkategori, nama, stok };
					tb.addRow(rowdata);
					op_jumlah.setText("");
				}
			}
		});
		op_simpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tb = (DefaultTableModel) op_table.getModel();
				String[] data = new String[tb.getColumnCount()];
				for(int r=0;r<tb.getRowCount();r++) {
					for(int i=0;i<tb.getColumnCount();i++) {
						data[i]=tb.getValueAt(r, i).toString();
					}
					vars.insertdata(data, queries.gquery("query_name", "q_insert_master_stok")); 
				}
				tb.setRowCount(0);
				
			}
		});
		setlayout();
	}
	private void setlayout() {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				setdivider(toolBar_1);
				setdivider(toolBar_4);
				
				DefaultTableModel tb = (DefaultTableModel) op_table.getModel();
				vars.setcomboboxmodel(op_kategori, queries.gquery("query_name", "c_kategori_model"));
				tb.setColumnIdentifiers(vars.sqlGetColumn(queries.gquery("query_name", "q_stok_barang")));
				
				DefaultTableModel tx = (DefaultTableModel) in_table.getModel();
				vars.setcomboboxmodel(in_kategori, queries.gquery("query_name", "c_kategori_model"));
				tx.setColumnIdentifiers(vars.sqlGetColumn(queries.gquery("query_name", "q_stok_barang")));
			}
		});
	}
	private void setdivider(Container container) {
		Component[] co = container.getComponents();
		for(Component c : co) {
			if(c instanceof JSplitPane) {
				((JSplitPane) c).setDividerLocation(c.getSize().width/12);
				((JSplitPane) c).setDividerSize(1);
			}
		}
	}

}
