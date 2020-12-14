import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BottomPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JButton productBtn;
	public JButton saleBtn;
	public JButton stockBtn;
	public JButton disBtn;
	public JButton accountBtn;
	public JButton commuteBtn;
	public JButton calcBtn;
	
	public BottomPanel() {
			setBackground(Color.WHITE);
			setLayout(null);
			productBtn = new JButton("상품");
			saleBtn = new JButton("판매");
			stockBtn = new JButton("재고");
			disBtn = new JButton("폐기");
			accountBtn = new JButton("유저");
			commuteBtn = new JButton("근태");
			calcBtn = new JButton("정산");
			
			productBtn.setForeground(Color.WHITE);
			productBtn.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			productBtn.setBackground(new Color(204, 206, 206));
			productBtn.setBounds(8, 0, 140, 98);

			saleBtn.setForeground(Color.WHITE);
			saleBtn.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			saleBtn.setBackground(new Color(204, 206, 206));
			saleBtn.setBounds(168, 0, 140, 98);
			
			stockBtn.setForeground(Color.WHITE);
			stockBtn.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			stockBtn.setBackground(new Color(204, 206, 206));
			stockBtn.setBounds(328, 0, 140, 98);
			
			disBtn.setForeground(Color.WHITE);
			disBtn.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			disBtn.setBackground(new Color(204, 206, 206));
			disBtn.setBounds(488, 0, 140, 98);
			
			accountBtn.setForeground(Color.WHITE);
			accountBtn.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			accountBtn.setBackground(new Color(204, 206, 206));
			accountBtn.setBounds(648, 0, 140, 98);
			
			commuteBtn.setForeground(Color.WHITE);
			commuteBtn.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			commuteBtn.setBackground(new Color(204, 206, 206));
			commuteBtn.setBounds(808, 0, 140, 98);
			
			calcBtn.setForeground(Color.WHITE);
			calcBtn.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			calcBtn.setBackground(new Color(204, 206, 206));
			calcBtn.setBounds(968, 0, 140, 98);

			add(productBtn);
			add(saleBtn);
			add(stockBtn);
			add(disBtn);
			add(accountBtn);
			add(commuteBtn);
			add(calcBtn);
	}
	
	public void selectedBtn(JButton selectedBtn) {
		for (int i = 0; i < getComponentCount(); i++) {
			getComponent(i).setBackground(new Color(204, 206, 206));
		}
		selectedBtn.setBackground(new Color(0,10,10));
	}
}