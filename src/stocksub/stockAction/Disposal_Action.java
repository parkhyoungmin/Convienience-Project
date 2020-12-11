package stocksub.stockAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import stocksub.StockDao;
import stocksub.stockView.StockInfoView;
import stocksub.stockframe.StockInfoFrame;

public class Disposal_Action implements ActionListener{
	
	StockInfoFrame stockInfoF;
	public Disposal_Action(StockInfoFrame stockInfoF) {
		this.stockInfoF = stockInfoF;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (stockInfoF.stockInfoView.stockTable.getSelectedRow() != -1) {
			
			// 선택한 행
			int row = stockInfoF.stockInfoView.stockTable.getSelectedRow();
			String product_id = ((String) stockInfoF.stockInfoView.tblModel.getValueAt(row, 0));
			String dis_date = ((String) stockInfoF.stockInfoView.tblModel.getValueAt(row, 4));
			System.out.println(product_id);
			System.out.println(dis_date);
			
			new StockDao().Disposal_product(product_id, dis_date);
			
			

		} else {
			JOptionPane.showMessageDialog(null, "[SYSTEM] 폐기하려는 상품을 선택해주세요.", "확인", JOptionPane.CLOSED_OPTION);
		}
		
	}

}
