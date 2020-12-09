package disposal;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import db.DatabaseConnect;
import product.HintTextField;

public class DisposalGui extends JFrame {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	ResultSetMetaData rsmd;
	
	private static final long serialVersionUID = 1L;
	Vector data, title;
	JTable table;
	DefaultTableModel model;
	JButton disposal_btn, cancle_btn; // 폐기등록, 취소 버튼
	JLabel dis_date, product_label; // 일자,물품id 라벨
	JTextField product_text; // 물품id검색 텍스트필드
	JPanel panel, datepanel;
	JSpinner date; // 날짜를 다룰 스피너
	
	public DisposalGui() {
		super("폐기관리");
		dis_date = new JLabel("폐기일자"); // 옆에 날짜 추가 예정
		data = new Vector<>();
		title = new Vector<>();
		title.add("물품ID");
		title.add("물품명");
		title.add("제조일");
		title.add("폐기일");
		title.add("수량");
		
		model = new DefaultTableModel();
		Vector result = selectAll();
		model.setDataVector(result, title);
		table = new JTable(model);
		JScrollPane sp = new JScrollPane(table); 
//		table.setEnabled(false); // 그래프 더블클릭 편집 못하게 설정
		
		panel = new JPanel();
		product_label = new JLabel("물품id");
//		product_text = new HintTextField("id 입력");
		product_text = new JTextField(20);
		disposal_btn = new JButton("폐기등록");
		cancle_btn = new JButton("취소");
		
		disposal_btn.addActionListener(new ActionListener() { // 폐기등록버튼 실행
			@Override
			public void actionPerformed(ActionEvent e) {
				conn = DatabaseConnect.getConnection();
				String product = product_text.getText();
				String sql = 
						"UPDATE product SET save_status = 'N' WHERE product_id = '"+product+"'";
				try {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.executeUpdate();
					model.fireTableDataChanged(); // 테이블 내용 갱신
					model.setNumRows(0); // 테이블 날리고 
					selectAll(); // 새로 받아와서 갱신
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				try {
					DatabaseConnect.dbClose(rs, ps, conn);
				} catch (SQLException e1) {
					System.out.println("[DB] 자원 반납 중 오류 발생\n");
					e1.printStackTrace();
				}
			}
		});
		
		cancle_btn.addActionListener(new ActionListener() { // 취소버튼 실행
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// 폐기일자, 물품id(label, text), (폐기등록,취소)버튼 add
		panel.add(product_label);
		panel.add(product_text);
		panel.add(disposal_btn);
		panel.add(cancle_btn);
		
		Container c = getContentPane();

		c.add(new JLabel("폐기일자", JLabel.LEFT),"North");
		c.add(sp, BorderLayout.CENTER);
		c.add(panel, BorderLayout.SOUTH);
	}
	
	
	public Vector selectAll() {
		conn = DatabaseConnect.getConnection();
		
		String sql = "select * from product";
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			
			while(rs.next()){ // 그래프에서 값 가져와 정렬
				Vector in = new Vector<String>();
				
				String id = rs.getString(1); // product_id
				String name = rs.getString(2); // product_name
				String manu_date = rs.getString(3); // manu_date
				String dis_date = rs.getString(4); // dis_date
				int quantity = rs.getInt(5); // quantity
				
				in.add(id);
				in.add(name);
				in.add(manu_date);
				in.add(dis_date);
				in.add(quantity);
				// 맨끝에 폐기한 날짜가 들어가도록 할 예정
				
				data.add(in);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			DatabaseConnect.dbClose(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data; 
	}
	
	
}