package myprogram;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.JobAttributes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.scene.layout.Border;
import javafx.stage.WindowEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class Main extends JFrame {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);
		firstrun();
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JMenuItem mntmInitConfiguration = new JMenuItem("Init Configuration");
		
		mnSettings.add(mntmInitConfiguration);
		
		JMenu mnListPc = new JMenu("List PC");
		menuBar.add(mnListPc);
		
		JMenuItem mntmListPc = new JMenuItem("List PC");
		
		mnListPc.add(mntmListPc);
		
		JMenuItem mntmScanIp = new JMenuItem("Scan IP");
		
		mnListPc.add(mntmScanIp);
		
		JMenu mnTransaksi = new JMenu("Transaksi");
		menuBar.add(mnTransaksi);
		
		JMenuItem mntmMasterBarang = new JMenuItem("Master barang");
		mntmMasterBarang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel master = new MasterBarang(Main.this);
				panel(master);
			}
		});
		mnTransaksi.add(mntmMasterBarang);
		
		JMenuItem mntmPenjualan = new JMenuItem("Penjualan");
		mnTransaksi.add(mntmPenjualan);
		
		JMenuItem mntmStok = new JMenuItem("Stok");
		mntmStok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel p = new stok();
				panel(p);
			}
		});
		mnTransaksi.add(mntmStok);
		
		JMenu mnCmd = new JMenu("CMD");
		menuBar.add(mnCmd);
		
		JMenuItem mntmCommandPrompt = new JMenuItem("Command Prompt");
		mntmCommandPrompt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel cmd = new cmd(Main.this);
				panel(cmd);
			}
		});
		mnCmd.add(mntmCommandPrompt);
		
		mntmListPc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel lis = new list(Main.this);
				panel(lis);
			}
		});
		mntmScanIp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel scan = new Scan(Main.this);
				panel(scan);
			}
		});
		mntmInitConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel init = new Initconf(Main.this);
				panel(init);
			}
		});
		
	}
	public void panel(JPanel panel) {
		clear();
		getContentPane().setLayout(new BorderLayout(0,0));
		getContentPane().add(panel,BorderLayout.CENTER);
		getContentPane().validate();
		getContentPane().repaint();
	}
	public void getip() {
		try {
			byte add = Byte.parseByte((InetAddress.getLocalHost().getHostAddress()));
			System.out.println(add);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void clear() {
		getContentPane().removeAll();
		getContentPane().repaint();
	}
	public void setdefault() {
		File c = new File("config/set.conf");
		if(c.exists()) {
			c.delete();
		}
		try {
			c.createNewFile();
			vars.confsetarray(vars.defaultkey(), vars.defaultvalue());
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			JOptionPane.showMessageDialog(null, "applied default setting");
		
	}
	public void firstrun() {
		
		File f = new File("config/fst.txt");
		if(f.exists()) {
			
		}else {
			setdefault();
			f.getParentFile().mkdirs();
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

