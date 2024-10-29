package myprogram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class queries {
	public static String gquery(String where, String data) {
		String ids = null;
		String query = "select sql_query from queries where "+where+"='"+data+"'";
		try {
			Class.forName(vars.Driver());
			Connection con = DriverManager.getConnection(vars.url(),vars.userpass(),vars.userpass());
			Statement stat = con.createStatement();
			ResultSet res = stat.executeQuery(query);
			while(res.next()) {
			ids = res.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

}
