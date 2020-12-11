package commute;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.zaxxer.hikari.HikariDataSource;

import db.DatabaseConnect;
import product.Product;

public class Commute_Dao {

	static String start_date;
	static String end_date;
	static String mem_name;
	
	
	public Commute_Dao(String start_date,String end_date,String mem_name) {
	
		this.start_date=start_date;
		this.end_date=end_date;
		this.mem_name=mem_name;
		
	}
		
		public static ArrayList<Commute> commutelist(){
		
		ArrayList<Commute> commutes = new ArrayList<Commute>();
		
		String sql = "SELECT * FROM daily_check WHERE dc_date between ? and ? and mem_no= ?";
		
		try {
			Connection conn = DatabaseConnect.getConnection();
			PreparedStatement pstmt = 
					conn.prepareStatement(sql);
			
			pstmt.setString(1,start_date);
			pstmt.setString(2,end_date);
			pstmt.setString(3,mem_name);
			
			ResultSet rs = pstmt.executeQuery();	

			//쿼리문에 해당하는 값 ArrayList에 값 저장
			while(rs.next()) {
				Commute commute = new Commute(rs.getString("dc_date"),rs.getString("mem_no"),rs.getString("on_time"),rs.getString("off_time"));	
				commutes.add(commute);	
			}
			
			DatabaseConnect.dbClose(rs, pstmt, conn);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		 }
		return commutes;
	
	   }
	
	
	
}
