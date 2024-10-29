package myprogram;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

public class vars {
	public static String[] defaultkey() {
		String[] key = {"url","database","port","userpass"};
		return key;
	}
	public static String[] defaultvalue() {
		String[] value = {"192.168.0.69","pclist","3306","pclist"};
		return value;
	}
	public static String configload(String config) {
		String conf;
		Properties confs = new Properties();
			try (FileInputStream load = new FileInputStream("config/set.conf")){
				confs.load(load);
			} catch (Exception e) {
				e.printStackTrace();
			}
			conf = confs.getProperty(config);
			return conf;
	}
	public static void confsetarray(String[] key, String[] value) throws Exception {
		Properties confs = new Properties();
		for(int i=0; i<key.length;i++) {
			confs.setProperty(key[i], value[i]);
			confs.store(new FileOutputStream("config/set.conf"),null);
		}
			
	}
	public static String url() {
		String url = "jdbc:mysql://"+configload("url")+":"+configload("port")+"/"+configload("database");
		return url;
	}
	public static String userpass() {
		String userpass = configload("userpass");
		return userpass;
	}
	public static String Driver() {
		String driver = "com.mysql.cj.jdbc.Driver";
		return driver;
	}
	public static String[] sqlGetColumn(String query) {
		String[] cn = null;
		int col = 0;
		try {
			Class.forName(Driver());
			Connection con = DriverManager.getConnection(url(),userpass(),userpass());
			Statement stat = con.createStatement();
			ResultSet res = stat.executeQuery(query);
			ResultSetMetaData resmd = res.getMetaData();
			col=resmd.getColumnCount();
			cn=new String[col];
			for (int i=0;i<col;i++) {
			cn[i]=resmd.getColumnName(i+1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cn;
	}
	
	public static int sqlGetRowCount(String query) {
		int roc=0;
		try {
			Class.forName(Driver());
			Connection con = DriverManager.getConnection(url(),userpass(),userpass());
			Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String qu = query;
			ResultSet res = stat.executeQuery(qu);
			res.last();
			roc=res.getRow();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roc;
		
	}
	
}
