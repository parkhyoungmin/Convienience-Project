package commute;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

public class Commute_list {

	public Commute_list() {
	
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl("jdbc:oracle:thin:@175.115.175.207:1521/orcl.115.175.144");
		ds.setUsername("puser");
		ds.setPassword("12341234");
		
		String sql = "SELECT * FROM daily_check WHERE dc_date between '?'and'?' and mem_no='?'";
		
		try {
			Connection conn=ds.getConnection();
			PreparedStatement pstmt=
					conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();	
			
			
			
	
			while(rs.next()) {
					System.out.printf("%s\t%s\t%s\t%s\n",
							rs.getString("dc_date"),
							rs.getString("mem_no"),
							rs.getString("on_time"),
							rs.getString("off_time")
					);
			}
			
			pstmt.execute();
			pstmt.close();
			conn.close();
			rs.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}
