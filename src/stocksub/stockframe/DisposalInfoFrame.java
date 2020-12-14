package stocksub.stockframe;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.toedter.calendar.JDateChooser;

import stocksub.StockDao;
import stocksub.stockView.DisposalInfoView;

public class DisposalInfoFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	Date choose_date;
	public JDateChooser dateChooser = new JDateChooser();
	public JButton go = new JButton("GO");
	
	public DisposalInfoView div = new DisposalInfoView();
	StockDao sd = new StockDao();
	
	
	public DisposalInfoFrame() {
		
		dateChooser.setBounds(500, 10, 100, 20);
		add(dateChooser);
		
		div.setBounds(12, 30, 700, 350);
		add(div);
		
		go.setBounds(600, 10, 60, 20);
		add(go);
		
		go.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				choose_date = new Date();
				choose_date = dateChooser.getDate();
				SimpleDateFormat transFormat = new SimpleDateFormat("YYYY-MM-dd");
				String choose = transFormat.format(choose_date);
				System.out.println(choose);
				
				
				div.tblModel.setNumRows(0);
				div.addDisposalInfoLine(sd.disposals(choose));
				
			}
		});
		
		
		setLayout(null);
		setFont(new Font("맑은 고딕", Font.BOLD, 15));
		setTitle("폐기 정보");		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 100, 700, 400);
		setVisible(true);
		
	}
}
