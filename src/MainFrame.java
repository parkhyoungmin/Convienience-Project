import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;



import product.ProdEditFrame;
import product.ProdRegistFrame;
import product.Product;
import product.ProductDao;
import product.ProductView;
import sale.SaleDao;
import sale.SalePanel;
import stock.Stock;
import stock.StockPanel;

public class MainFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 상단 정보 보여줄 패널
	TopPanel topPanel = new TopPanel();

	// 가운데 상품 보여줄 패널
	ProductView productView = new ProductView();
	
	// 가운데 재고 보여줄 패널
	StockPanel stockPanel = new StockPanel();
	
	// 가운데 판매 보여줄 패널
	SalePanel salePanel = new SalePanel();

	// 오른쪽 버튼들 보여줄 패널
	RightBtnPanel rightBtnPanel = new RightBtnPanel();
	
	// 하단 버튼들 보여줄 패널
	BottomPanel bottomPanel = new BottomPanel();

	// 상품 등록 팝업 프레임
	ProdRegistFrame prodRegistFrame = new ProdRegistFrame();

	// 상품 수정 팝업 프레임
	ProdEditFrame prodEditFrame = new ProdEditFrame();

	// 상품
	Product product;

	public CardLayout cardlayout;
	public CardLayout btnlayout;

	public JPanel contentPanel;

	public JPanel centerView;
	public JPanel pBtnView;
	public JPanel topView;
	public JPanel bottomView;

	ProductDao pdao = new ProductDao();
	SaleDao sdao = new SaleDao();

	public MainFrame() {

		cardlayout = new CardLayout();
		btnlayout = new CardLayout();

		setFont(new Font("맑은 고딕", Font.BOLD, 20));
		setTitle("편의점프로그램");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1326, 753);
		setVisible(true);

		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		// 상단 패널부분
		topView = new JPanel();
		topView.add(topPanel, "topPanel");
		topView.setBounds(0, 0, 1297, 50);
		topView.setBackground(new Color(0, 255, 204));
		topView.setLayout(new BorderLayout());
		contentPanel.add(topView);

		// 가운데 패널부분 - 상품 , 재고
		centerView = new JPanel();
		centerView.setLayout(cardlayout);
		centerView.add(productView, "productView"); // 상품 
		centerView.add(stockPanel, "stockPanel"); 	// 재고
		centerView.add(salePanel, "salePanel");		//판매
		centerView.setBackground(Color.WHITE);
		centerView.setBounds(0, 50, 1157, 552);
		contentPanel.add(centerView);


		// 오른쪽 패널부분
		pBtnView = new JPanel();
		pBtnView.setBackground(Color.WHITE);
		pBtnView.setBounds(1158, 50, 142, 675);
		pBtnView.add(rightBtnPanel, "rightBtnPanel");
		pBtnView.setLayout(btnlayout);
		contentPanel.add(pBtnView);
				
		// 하단 패널부분
		bottomView = new JPanel();
		bottomView.setBackground(Color.WHITE);
		bottomView.setBounds(8, 610, 1145, 100);
		bottomView.add(bottomPanel, "bottomPanel");
		bottomView.setLayout(btnlayout);
		contentPanel.add(bottomView);

		// 버튼들 액션 달기 Start
		rightBtnPanel.registProdBtn.addActionListener(this); // 우측패널 상품등록 버튼
		rightBtnPanel.editProdBtn.addActionListener(this); // 우측패널 상품 수정 버튼
		rightBtnPanel.delProdBtn.addActionListener(this); // 우측패널 상품 삭제 버튼

		prodRegistFrame.regBtn.addActionListener(this); // 팝업 상품등록 프레임 등록 버튼
		prodRegistFrame.cancelBtn.addActionListener(this); // 팝업 상품등록 프레임 취소 버튼

		prodEditFrame.compEditBtn.addActionListener(this);	//팝업 상품 수정 프레임 수정 버튼
		prodEditFrame.cancelEidtBtn.addActionListener(this);// 팝업 상품 수정 프레임 취소 버튼
		
		bottomPanel.productBtn.addActionListener(this);	// 하단패널 상품 버튼
		bottomPanel.saleBtn.addActionListener(this);	// 하단패널 판매 버튼
		bottomPanel.stockBtn.addActionListener(this);	// 하단패널 재고 버튼
		bottomPanel.disBtn.addActionListener(this);		// 하단패널 폐기 버튼
		bottomPanel.accountBtn.addActionListener(this); // 하단패널 유저 버튼
		bottomPanel.commuteBtn.addActionListener(this); // 하단패널 근태 버튼
		bottomPanel.calcBtn.addActionListener(this);  	// 하단패널 정산 버튼
		
		salePanel.addBucketBtn.addActionListener(this);
		salePanel.delBucketBtn.addActionListener(this);
		salePanel.completeBtn.addActionListener(this);
		// 버튼들 액션 달기 End
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		Object obb = e.getActionCommand();

		if (ob == rightBtnPanel.registProdBtn) {
			prodRegistFrame.resetText();
			prodRegistFrame.setVisible(true);
		} else if (ob == prodRegistFrame.regBtn) {

			product = new Product();

			product.setProduct_id(prodRegistFrame.tf1.getText());
			product.setProduct_name(prodRegistFrame.tf2.getText());
			product.setPrice(Integer.parseInt(prodRegistFrame.tf3.getText()));

			pdao.productAdd(product);

			// 상품목록J테이블 초기화 해주기.
			productView.tblModel.setNumRows(0);

			// 상품목록J테이블 새로 채우기
			productView.addProductLine(pdao.productAll());

			// 텍스트 필드에 채워진 값 초기화 해주기.
			prodRegistFrame.resetText();

			// 확인 팝업창
			JOptionPane.showMessageDialog(null, "[SYSTEM] 등록이 완료되었습니다.", "확인", JOptionPane.CLOSED_OPTION);

			// 창 안보이게
			prodRegistFrame.setVisible(false);

		} else if (ob == prodRegistFrame.cancelBtn) {
			prodRegistFrame.resetText();
			prodRegistFrame.setVisible(false);
		} else if (ob == rightBtnPanel.editProdBtn) {
			if (productView.productTable.getSelectedRow() != -1) {
				prodEditFrame.setVisible(true);

				// 선택한 행
				int row = productView.productTable.getSelectedRow();

				// 선택한 행 내용 수정 프레임창에 세팅해주기
				prodEditFrame.tf1.setText((String) productView.tblModel.getValueAt(row, 1));
//				try {
//					Date date1 = new SimpleDateFormat("yyyy-MM-dd")
//							.parse((String) productView.tblModel.getValueAt(row, 2));			
//					Date date2 = new SimpleDateFormat("yyyy-MM-dd")
//							.parse((String) productView.tblModel.getValueAt(row, 3));
//
//					prodEditFrame.dateChooser1.setDate(date1);
//					prodEditFrame.dateChooser2.setDate(date2);
//				} catch (ParseException e1) {
//					System.out.println("Date Parser error!\n");
//					e1.printStackTrace();
//				}

				prodEditFrame.tf2.setText((String) productView.tblModel.getValueAt(row, 2));
				//prodEditFrame.tf3.setText((String) productView.tblModel.getValueAt(row, 5));

			} else {
				JOptionPane.showMessageDialog(null, "[SYSTEM] 수정하시려는 상품을 선택해주세요.", "확인", JOptionPane.CLOSED_OPTION);
			}

		} else if (ob == rightBtnPanel.delProdBtn) {
			if (productView.productTable.getSelectedRow() != -1) {
				int row = productView.productTable.getSelectedRow();
				String product_id = (String) productView.tblModel.getValueAt(row, 0);

				// DB 삭제
				pdao.productDel(product_id);

				// 상품목록 화면테이블 초기화 해주기.
				productView.tblModel.setNumRows(0);

				// 상품목록 화면테이블 새로 채우기
				productView.addProductLine(pdao.productAll());

				JOptionPane.showMessageDialog(null, "\t[SYSTEM] 삭제가 완료되었습니다.", "확인", JOptionPane.CLOSED_OPTION);

			} else {
				JOptionPane.showMessageDialog(null, "\t[SYSTEM] 삭제하시려는 상품을 선택해주세요.", "확인", JOptionPane.CLOSED_OPTION);
			}
		} else if (ob == prodEditFrame.compEditBtn) {

			int row = productView.productTable.getSelectedRow();
			String product_id = (String) productView.tblModel.getValueAt(row, 0);
			product = new Product();
			product.setProduct_id(product_id);
			product.setProduct_name(prodEditFrame.tf1.getText());
			product.setPrice(Integer.parseInt(prodEditFrame.tf2.getText()));

			// DB 수정
			pdao.productEdit(product);

			// 상품목록 화면테이블 초기화 해주기.
			productView.tblModel.setNumRows(0);

			// 상품목록 화면테이블 새로 채우기
			productView.addProductLine(pdao.productAll());

			// 텍스트 필드에 채워진 값 초기화 해주기.
			prodEditFrame.resetText();

			// 확인 팝업창
			JOptionPane.showMessageDialog(null, "\t[SYSTEM] 수정이 완료되었습니다.", "확인", JOptionPane.CLOSED_OPTION);

			// 창 안보이게
			prodEditFrame.setVisible(false);
		} else if (ob == prodEditFrame.cancelEidtBtn) {
			prodEditFrame.resetText();
			prodEditFrame.setVisible(false);
		}else if (ob == bottomPanel.productBtn) {	
			//메인 버튼 클릭시 색 변경해주기
			bottomPanel.selectedBtn(bottomPanel.productBtn);			
			cardlayout.show(centerView, "productView");			
		}else if (ob == bottomPanel.saleBtn) {
			//필드 초기화 먼저.
			salePanel.prodnameTf.setText("");
			salePanel.prodQt.setText("");
			//메인 버튼 클릭시 색 변경해주기
			bottomPanel.selectedBtn(bottomPanel.saleBtn);
			cardlayout.show(centerView, "salePanel");
			
			// 판매 패널의 J테이블에서 로우 선택시 발생하는 이벤트
			salePanel.stockTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JTable jt = (JTable)e.getSource();
					int row = jt.getSelectedRow();
					if(row != -1) {
						String product_name = (String) salePanel.stockTable.getValueAt(row, 1);
						salePanel.prodnameTf.setText(product_name);
						salePanel.prodQt.setText("");
					}
				}
			});

		}else if (ob == salePanel.addBucketBtn) {
			int row  = salePanel.stockTable.getSelectedRow();

			if(salePanel.prodnameTf.getText().equals("")) {
				// 확인 팝업창
				JOptionPane.showMessageDialog(null, "\t[SYSTEM] 추가할 상품의 행을 선택해주세요.", "확인", JOptionPane.CLOSED_OPTION);
			}
			else if(salePanel.prodQt.getText().equals("") || !Pattern.matches("^[1-9]*$", salePanel.prodQt.getText())) {
				// 확인 팝업창
				JOptionPane.showMessageDialog(null, "\t[SYSTEM] 정확한 수량을 입력해주세요", "확인", JOptionPane.CLOSED_OPTION);
			}else if(Integer.parseInt(String.valueOf(salePanel.prodQt.getText())) > Integer.parseInt(String.valueOf(salePanel.stockTblModel.getValueAt(row, 3)))) {
				// 확인 팝업창
				JOptionPane.showMessageDialog(null, "\t[SYSTEM] 현 재고 수량보다 많은 양을 구매하실 수 없습니다.", "확인", JOptionPane.CLOSED_OPTION);
			}else {

				String [] arrData = new String[4];
				arrData[0] = String.valueOf(salePanel.stockTblModel.getValueAt(row, 0));
				arrData[1] = salePanel.prodnameTf.getText();
				arrData[2] = salePanel.prodQt.getText();
				arrData[3] = String.valueOf(salePanel.stockTblModel.getValueAt(row, 2));
				
				
				salePanel.bucketTblModel.addRow(arrData);
				
				//재고테이블에서 선택한 수량만큼 차감하고 장바구니에 넣기.
				int rs = Integer.parseInt(String.valueOf(salePanel.stockTblModel.getValueAt(row, 3)))  - Integer.parseInt(String.valueOf(salePanel.prodQt.getText()));
				salePanel.stockTblModel.setValueAt(rs, row, 3);
				
			}
			
		}else if (ob == salePanel.delBucketBtn) {
			int bucketRow  = salePanel.bucketTable.getSelectedRow();
			
			//행이 선택되었을 때
			if(bucketRow != -1) {
				for(int i =0; i<salePanel.stockTable.getRowCount(); i++) {
					if(String.valueOf(salePanel.stockTable.getValueAt(i, 0)).equals(String.valueOf(salePanel.bucketTable.getValueAt(bucketRow, 0)))){	
						int addQT = Integer.parseInt(String.valueOf(salePanel.stockTable.getValueAt(i, 3))) + Integer.parseInt(String.valueOf(salePanel.bucketTable.getValueAt(bucketRow, 2)));
						salePanel.stockTable.setValueAt(addQT, i, 3);
						break;
					}
				}
				
				salePanel.bucketTblModel.removeRow(bucketRow);
				
				
			}else {
				// 확인 팝업창
				JOptionPane.showMessageDialog(null, "\t[SYSTEM] 삭제할 행을 선택해주세요", "확인", JOptionPane.CLOSED_OPTION);
			}

		}else if (ob == salePanel.completeBtn) {
			int count = salePanel.bucketTblModel.getRowCount();
			ArrayList<Stock> stocks = new ArrayList<Stock>();
			for(int i=0; i<count; i++) {
				Stock stock = new Stock();
				stock.setProduct_id(String.valueOf(salePanel.bucketTable.getValueAt(i, 0)));
				stock.setQuantity(Integer.parseInt(String.valueOf(salePanel.bucketTable.getValueAt(i, 2))));
				stock.setPrice(Integer.parseInt(String.valueOf(salePanel.bucketTable.getValueAt(i, 3))));
				
				stocks.add(stock);
			}
			
			if(sdao.pay(stocks)) {
				// 확인 팝업창
				JOptionPane.showMessageDialog(null, "\t[SYSTEM] 결제가 완료 되었습니다!", "확인", JOptionPane.CLOSED_OPTION);
				salePanel.bucketTblModel.setNumRows(0);
				
			}
			
		}else if (ob == bottomPanel.stockBtn) {
			//메인 버튼 클릭시 색 변경해주기
			bottomPanel.selectedBtn(bottomPanel.stockBtn);
			cardlayout.show(centerView, "stockPanel");
		}else if (ob == bottomPanel.disBtn) {
			//메인 버튼 클릭시 색 변경해주기
			bottomPanel.selectedBtn(bottomPanel.disBtn);
		}else if (ob == bottomPanel.accountBtn) {
			//메인 버튼 클릭시 색 변경해주기
			bottomPanel.selectedBtn(bottomPanel.accountBtn);
		}else if (ob == bottomPanel.commuteBtn) {
			//메인 버튼 클릭시 색 변경해주기
			bottomPanel.selectedBtn(bottomPanel.commuteBtn);
		}else if (ob == bottomPanel.calcBtn) {
			//메인 버튼 클릭시 색 변경해주기
			bottomPanel.selectedBtn(bottomPanel.calcBtn);
		}
	}

	public static void main(String[] args) {
		new MainFrame();
	}
}
