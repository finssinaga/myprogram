package penjualan;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JEditorPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import myprogram.vars;

import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.DropMode;
import javax.swing.ListSelectionModel;

public class MenuPJ extends JFrame {
	private JTable table;
	private JButton btnBaru;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField j_id;
	public static void main(String[] args0) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MenuPJ frame = new MenuPJ();
				frame.setVisible(true);
			}
		});
	}
	public MenuPJ() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setFloatable(false);
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JSplitPane splitPane = new JSplitPane();
		toolBar.add(splitPane);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane.setLeftComponent(lblTotal);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblJumlah.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane.setRightComponent(lblJumlah);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		panel.add(toolBar_1, BorderLayout.NORTH);
		
		btnBaru = new JButton("Baru");
		
		toolBar_1.add(btnBaru);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		toolBar_1.add(horizontalGlue);
		
		JButton btnCetakFaktur = new JButton("Cetak faktur");
		toolBar_1.add(btnCetakFaktur);
		
		JButton btnSimpan = new JButton("Simpan");
		toolBar_1.add(btnSimpan);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		toolBar_1.add(horizontalGlue_2);
		
		JSplitPane splitPane_4 = new JSplitPane();
		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane_4, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane_4.setLeftComponent(scrollPane);
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setShowHorizontalLines(false);
		scrollPane.setViewportView(table);
		
		JPanel panel_3 = new JPanel();
		splitPane_4.setRightComponent(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar_5 = new JToolBar();
		toolBar_5.setFloatable(false);
		panel_3.add(toolBar_5, BorderLayout.SOUTH);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		toolBar_5.add(horizontalGlue_1);
		
		JToolBar toolBar_6 = new JToolBar();
		toolBar_6.setFloatable(false);
		panel_3.add(toolBar_6, BorderLayout.NORTH);
		
		JLabel lblId = new JLabel("ID");
		toolBar_6.add(lblId);
		
		j_id = new JTextField();
		toolBar_6.add(j_id);
		j_id.setColumns(1);
		
		JButton btnOk = new JButton("ok");
		
		toolBar_6.add(btnOk);
		
		JButton btnHapus = new JButton("Hapus");
		toolBar_6.add(btnHapus);
		
		Component horizontalStrut = Box.createHorizontalStrut(700);
		toolBar_6.add(horizontalStrut);
		
		JButton btnBayar = new JButton("Bayar");
		toolBar_6.add(btnBayar);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		toolBar_6.add(horizontalStrut_1);
		
		JButton btnBatal_1 = new JButton("Batal");
		toolBar_6.add(btnBatal_1);
		
		JToolBar toolBar_2 = new JToolBar();
		toolBar_2.setOrientation(SwingConstants.VERTICAL);
		toolBar_2.setFloatable(false);
		panel.add(toolBar_2, BorderLayout.EAST);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		toolBar_2.add(splitPane_1);
		
		JPanel panel_1 = new JPanel();
		splitPane_1.setLeftComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JEditorPane editorPane = new JEditorPane();
		panel_1.add(editorPane);
		
		JPanel panel_2 = new JPanel();
		splitPane_1.setRightComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar_3 = new JToolBar();
		toolBar_3.setOrientation(SwingConstants.VERTICAL);
		toolBar_3.setFloatable(false);
		panel_2.add(toolBar_3, BorderLayout.SOUTH);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		toolBar_3.add(splitPane_2);
		
		JLabel lblBayar = new JLabel("Bayar : ");
		splitPane_2.setLeftComponent(lblBayar);
		
		textField = new JTextField();
		splitPane_2.setRightComponent(textField);
		textField.setColumns(10);
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		toolBar_3.add(splitPane_3);
		
		JLabel lblKembali = new JLabel(" Kembali : ");
		splitPane_3.setLeftComponent(lblKembali);
		
		textField_1 = new JTextField();
		splitPane_3.setRightComponent(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.WEST);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar_4 = new JToolBar();
		toolBar_4.setFloatable(false);
		panel.add(toolBar_4, BorderLayout.SOUTH);
		
		JLabel lblUser = new JLabel("User : ");
		toolBar_4.add(lblUser);
		
		JLabel lblUser_1 = new JLabel("user");
		toolBar_4.add(lblUser_1);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		toolBar_4.add(horizontalGlue_3);
		
		JButton btnLogout = new JButton("LogOut");
		toolBar_4.add(btnLogout);
		
		btnBaru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tb = (DefaultTableModel) table.getModel();
				tb.setColumnIdentifiers(vars.sqlGetColumn("select * from tmp_jual"));
			}
		});
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tb = (DefaultTableModel) table.getModel();
				String defjml = "1";
				String id = j_id.getText();
				ArrayList<String> o = getbasedid(id, "master_barang");
				o.add(5,defjml);
				Object[] row = o.toArray();
				tb.addRow(row);
				j_id.setText("");
			}
		});
	}
	private ArrayList<String> getbasedid(String id, String tblname) {
		ArrayList<String> data = new ArrayList<>();
		String[] dat = vars.getsinglerow("select * from "+tblname+" where id='"+id+"'");
		for(String d : dat) {
			data.add(d);
		}
		return data;
	}

}
