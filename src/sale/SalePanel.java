package sale;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


import stock.Stock;
import stock.StockDao;

public class SalePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	StockDao stockdao = new StockDao();
	public JScrollPane stockScrollPane = new JScrollPane(); 	// 재고 보여줄 스크롤패널
	public JScrollPane bucketScrollPane = new JScrollPane();	// 장바구니 보여줄 스크롤패널
	
	public Vector<String> stockcolNames = getColum("stock");
	public Vector<String> bucketcolNames = getColum("bucket");
	
	public DefaultTableModel stockTblModel = new DefaultTableModel(stockcolNames, 0);
	public DefaultTableModel bucketTblModel = new DefaultTableModel(bucketcolNames, 0);
	
	public JTable stockTable = new JTable(stockTblModel);
	public JTable bucketTable = new JTable(stockTblModel);
	
	public JTextField prodIdTf; 	//상품코드 텍스트필드
	public JTextField prodQt;		//상품수량 텍스트필드
	public JButton addBucketBtn;	//장바구니 추가 버튼
	public JButton delBucketBtn;	//장바구니 삭제 버튼
	
	// 행 정보들 담을 벡터
	public Vector<String> rows;

	public SalePanel() {
		setLayout(null);
		
		prodIdTf = new JTextField();
		prodQt = new JTextField();
		addBucketBtn =  new JButton("추가");
		delBucketBtn = new JButton("삭제");
		
		prodIdTf.setBounds(480, 510, 70, 30);
		prodQt.setBounds(570, 510, 70, 30);
		addBucketBtn.setBounds(660, 510, 70, 30);
		delBucketBtn.setBounds(735, 510, 70, 30);

		add(prodIdTf);
		add(prodQt);
		add(addBucketBtn);
		add(delBucketBtn);
		
		stockScrollPane.setBounds(12, 10, 600, 450);
		bucketScrollPane.setBounds(680, 10, 450, 450);
		add(stockScrollPane);
		add(bucketScrollPane);

		stockTable.setRowMargin(10);
		stockTable.setRowHeight(30);
		stockTable.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		
		bucketTable.setRowMargin(10);
		bucketTable.setRowHeight(30);
		bucketTable.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		
		// 테이블 로우 중 한 줄만 선택 가능.
		stockTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bucketTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		addStockLine(stockdao.stockAll());

		stockScrollPane.setViewportView(stockTable);
		bucketScrollPane.setViewportView(bucketTable);
	}

	// Jtable에 로우 하나씩 추가하기.
	public void addStockLine(ArrayList<Stock> stocks) {
		int size = stocks.size();

		for (int i = 0; i < size; i++) {
			rows = new Vector<String>();
			rows.addElement(Integer.toString(stocks.get(i).getStock_no()));
			rows.addElement(stocks.get(i).getProduct_id());
			rows.addElement(stocks.get(i).getProduct_name());
			rows.addElement(Integer.toString(stocks.get(i).getPrice()));
			rows.addElement(Integer.toString(stocks.get(i).getQuantity()));

			// 로우마다 테이블에 뿌려주기.
			stockTblModel.addRow(rows);
		}

		stockScrollPane.setViewportView(stockTable);
	}
	
	public void addBucketLine(Stock stockOne) {

			String[] stockInfo = new String[] {};
			stockInfo[0] = stockOne.getProduct_name();
			stockInfo[1] = Integer.toString(stockOne.getPrice());
			stockInfo[2] = Integer.toString(stockOne.getQuantity());

			// 로우마다 테이블에 뿌려주기.
			bucketTblModel.addRow(stockInfo);

			bucketScrollPane.setViewportView(bucketTable);
	}

	private Vector<String> getColum(String check) {
		Vector<String> colNames = new Vector<String>();
		if(check.equals("stock")) {
			stockcolNames = new Vector<String>();
			stockcolNames.add("재고번호");
			stockcolNames.add("상품코드");
			stockcolNames.add("상품명");
			stockcolNames.add("가격");
			stockcolNames.add("수량");
			
			colNames =  stockcolNames;
		}else if(check.equals("bucket")){
			bucketcolNames = new Vector<String>();
			bucketcolNames.add("상품명");
			bucketcolNames.add("가격");
			bucketcolNames.add("수량");
			
			colNames =  bucketcolNames;
		}
		
		return colNames;

	}
}