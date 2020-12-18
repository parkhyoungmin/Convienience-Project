//package commute.MainCommuteFrame;
//
//
//import java.awt.BorderLayout;  
//import java.awt.CardLayout;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//
//import commute.TimeDao;
//import commute.CommutePanel;
//import commute.Action.Add_Commute_Off_Time;
//import commute.Action.Add_Commute_On_Time;
//import commute.Action.Check_on_Time;
//import commute.BtnAction.CommuteBtnAction;
//import commute.List.Commute_ListDao;
////import commute.List.Commute_ListView;
//import commute.List.List_Input;
//import product.ProductDao;
//import product.ProductView;
//
//
//public class CommuteMainFrame extends JFrame{
//
//	
//	private static final long serialVersionUID = 1L;
//
//	TopPanel topPanel = new TopPanel();
//	public CommuteBtnPanel rightBtnPanel = new CommuteBtnPanel();
//
//
//	public CardLayout cardlayout;
//	public JPanel contentPanel;
//
//	public JPanel tView;
//	public JPanel cBtnView;
//	public JPanel topView;
//	public JPanel lView;
//	
//	public CommutePanel tv = null;
//	public TimeDao tdao = new TimeDao();
//
//	
//	
//	
//	
//	public CommuteMainFrame() {
//		
//		tv= new CommutePanel();
//		
//		cardlayout = new CardLayout();
//
//		setFont(new Font("맑은 고딕", Font.BOLD, 20));
//		setTitle("근태프로그램");
//		setResizable(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 1326, 753);
//		setVisible(true);
//
//		contentPanel = new JPanel();
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPanel);
//		contentPanel.setLayout(null);
//
//		
//		topView = new JPanel();
//		topView.add(topPanel, "topPanel");
//		topView.setBounds(0, 0, 1326, 50);
//		topView.setBackground(new Color(0, 255, 204));
//		topView.setLayout(new BorderLayout());
//		contentPanel.add(topView);
//		
//		tView = new JPanel();
//		tView.add(tv , "TimeView");
//		tView.setBackground(Color.WHITE);
//		tView.setBounds(0, 50, 1100, 800);
//		tView.setLayout(cardlayout);
//		contentPanel.add(tView);
//		
////		lView = new JPanel();
////		lView.add(lv , "Commuite_ListView");
////		lView.setBackground(Color.WHITE);
////		lView.setBounds(600, 50, 550, 800);
////		lView.setLayout(cardlayout);
////		contentPanel.add(lView);
//		
//		
//
//	
//		cBtnView = new JPanel();
//		cBtnView.setBackground(Color.WHITE);
//		cBtnView.setBounds(1158, 50, 164, 675);
//		cBtnView.add(rightBtnPanel, "rightBtnPanel");
//		cBtnView.setLayout(cardlayout);
//		contentPanel.add(cBtnView);
//
//	
//		rightBtnPanel.on_timeBtn.addActionListener(new CommuteBtnAction(this)); // 우측패널 출근 버튼
//		rightBtnPanel.off_timeBtn.addActionListener(new CommuteBtnAction(this)); // 우측패널 퇴근 버튼
//		rightBtnPanel.commuteBtn.addActionListener(new CommuteBtnAction(this)); // 우측패널 근태목록 버튼
//	
//	}
//	
//	public static void main(String[] args) {
//		new CommuteMainFrame();
//	}
//
//}