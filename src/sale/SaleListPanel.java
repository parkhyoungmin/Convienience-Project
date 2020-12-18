package sale;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import event.Event;
import event.EventDao;

public class SaleListPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SaleDao saledao = new SaleDao();
	public JScrollPane saleListScrollPane = new JScrollPane();	
	public Vector<String> colNames = getColum();
	public DefaultTableModel tblModel = new DefaultTableModel(colNames, 0);
	public JTable saleListTable = new JTable(tblModel) {
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	//행 정보들 담을 벡터
	public Vector<String> rows;
	
	public JDateChooser dateChooser1;
	public JDateChooser dateChooser2;
	public JButton searchBtn;
	
	public SaleListPanel(){
		setLayout(null);
		dateChooser1 = new JDateChooser();
		dateChooser2 = new JDateChooser();
		dateChooser1.setBounds(390, 10, 110, 27);
		dateChooser2.setBounds(510, 10, 110, 27);
		add(dateChooser1);
		add(dateChooser2);
		
		searchBtn = new JButton("검색");
		searchBtn.setBounds(630, 10, 70, 27);
		add(searchBtn);
		
		saleListScrollPane.setBounds(12, 40, 1133, 500);
		add(saleListScrollPane);
		
		saleListTable.setRowMargin(10);
		saleListTable.setRowHeight(30);		
		saleListTable.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		
		//테이블 로우 중 한 줄만 선택 가능.
		saleListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		saleListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					int row = saleListTable.getSelectedRow();
					String sales_no =  (String) saleListTable.getValueAt(row, 0);
					
					SaleDetailListFrame saleDetailListFrame = new SaleDetailListFrame(sales_no);
					saleDetailListFrame.setVisible(true);
				}
			}
		});

		addSaleLine(saledao.salesList(null, null)); 
		
		saleListScrollPane.setViewportView(saleListTable);
		
	}

	//Jtable에 로우 하나씩 추가하기.
	public void addSaleLine(ArrayList<Sale> sales) {
		int size = sales.size();

		for (int i = 0; i < size; i++) {
			rows = new Vector<String>();

			rows.addElement(Integer.toString(sales.get(i).getSales_no())); 	// 이벤트코드
			rows.addElement(sales.get(i).getSales_date()); // 이벤트명
			rows.addElement(sales.get(i).getSave_time());	// 상품코드
			rows.addElement(sales.get(i).getWorker_no()); // 상품명

			//로우마다 테이블에 뿌려주기.
			tblModel.addRow(rows);
		}
		
		saleListScrollPane.setViewportView(saleListTable);
	}
	
	private Vector<String> getColum() {
		colNames = new Vector<String>();
		colNames.add("판매번호");
		colNames.add("판매일");
		colNames.add("판매시간");

		return colNames;
	}
}