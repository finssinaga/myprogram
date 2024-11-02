package penjualan;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import myprogram.vars;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPJ extends JFrame{
	private JSplitPane splitPane;
	private JTextField log_user;
	private JTextField log_pass;
	private JPanel panel_2;
	private JButton log_login;
	public static void main(String[] args0) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MainPJ frame = new MainPJ();
				frame.setVisible(true);
			}
		});
	}
	
	
	public MainPJ() {
		setTitle("PENJUALAN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 5));
		panel.setBackground(new Color(169, 169, 169));
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCompanyName = new JLabel("Company Name");
		lblCompanyName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(lblCompanyName, BorderLayout.NORTH);
		
		JLabel lblCompanyLogo = new JLabel("Company Logo ");
		lblCompanyLogo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblCompanyLogo, BorderLayout.CENTER);
		
		JLabel lblCompanyName_1 = new JLabel("MOTD");
		lblCompanyName_1.setVerticalAlignment(SwingConstants.TOP);
		lblCompanyName_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblCompanyName_1, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(255, 255, 255), 5));
		panel_1.setBackground(new Color(100, 149, 237));
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblVersionControl = new JLabel("Version Control");
		panel_1.add(lblVersionControl, BorderLayout.SOUTH);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblLogin, BorderLayout.NORTH);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(100, 149, 237));
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new MigLayout("", "[][::122px,grow]", "[50.00][][27.00][][58.00][][][][]"));
		
		JLabel lblNewLabel = new JLabel("User Name");
		panel_2.add(lblNewLabel, "cell 0 1,alignx right,aligny center");
		
		log_user = new JTextField();
		panel_2.add(log_user, "cell 1 1,alignx leading,aligny center");
		log_user.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		panel_2.add(lblNewLabel_1, "cell 0 3,alignx right,aligny center");
		
		log_pass = new JTextField();
		panel_2.add(log_pass, "cell 1 3,alignx leading");
		log_pass.setColumns(10);
		
		log_login = new JButton("Login");
		log_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String users=log_user.getText(),
						paswd=log_pass.getText();
				auth(users, paswd);
				log_user.setText("");
				log_pass.setText("");
			}
		});
		panel_2.add(log_login, "cell 1 6,alignx leading,aligny center");
		setlayout();
		
	}
	private void setlayout() {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				splitPane.setDividerLocation(splitPane.getSize().width/2);
				splitPane.setDividerSize(0);
				setlistener(panel_2);
			}
		});
	}
	private void setlistener(Container c) {
		Component[] pnl = c.getComponents();
		for(Component px : pnl) {
			if(px instanceof JTextField) {
				px.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						int keypres = e.getKeyCode();
						if(keypres == 10) {
							log_login.doClick();
						}
					}
				});
			}
		}
	}
	private void auth(String users, String paswd) {
		String user=users,
				pass= paswd;
		if(user.equals(vars.getsinglerow("select user from user where user='"+user+"'")) ||
				pass.equals(vars.getsinglerow("select pass from user where pass='"+pass+"'"))) {
			JOptionPane.showMessageDialog(null, "ok");
			MenuPJ.main(null);
			MainPJ.this.dispose();
		}else {
			JOptionPane.showMessageDialog(null, "login salah");
		}
	}
}
