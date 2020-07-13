import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.View;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.awt.SystemColor;

public class attendanceGUI extends JFrame {

	private JPanel contentPane;
	private static  Main main = new Main();
	private JTextField textField;
	private static  JTable table = new JTable();
	private static DefaultTableModel tableModel = new DefaultTableModel();
	private static Object[][] obj = new Object[15][4];
	private static String[] columnNames = {"ID","姓名","签到时间","签退时间"};
 
	
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		main.setEmployee();
		main.setMessage();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					attendanceGUI frame = new attendanceGUI();
					frame.setVisible(true);
					frame.fillTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public attendanceGUI() {
		setBackground(SystemColor.activeCaptionBorder);
		setTitle("员工打卡系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 360);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("点击签到");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer inputID = Integer.parseInt(textField.getText(),10);
				String str = main.attendanceCheckIn(inputID);
				JOptionPane.showMessageDialog(null,str);
				fillTable();
			} 
		});
		btnNewButton.setBounds(323, 160, 123, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("点击签退");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer inputID = Integer.parseInt(textField.getText(),10);
				String str = main.attendanceCheckOut(inputID);
				JOptionPane.showMessageDialog(null,str);
	     		fillTable();
			}
		});
		btnNewButton_1.setBounds(466, 160, 123, 40);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("请输入员工ID：");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(300, 5, 165, 67);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(365, 87, 180, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("员工打卡情况");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(90, 210, 181, 63);
		contentPane.add(lblNewLabel_1);
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E");
		String str = sdf.format(date);
		JLabel lblNewLabel_2 = new JLabel(str);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(75, 225, 200, 100);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("将员工打卡记录保存到文件中");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.fileWrite();
				JOptionPane.showMessageDialog(null,"已成功保存至\"e:/AttendanceMessage.txt\"中");
			}
		});
		btnNewButton_2.setBounds(350, 245, 200, 37);
		contentPane.add(btnNewButton_2);
	
	}
		
	
	public void fillTable() {
        int i = 0;
        for(DakaInfo daka : main.list) {
        	obj[i][0] = daka.getID();
        	for(Employee employee : main.company.employees) {
        		if(employee.getID() == daka.getID()) {
        			obj[i][1] = employee.getName();
        		}
        	}
 			if(daka.getSignIn() == null) {
 				obj[i][2] = "未签到";
 			}else {
 				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
 				String strIn = sdf.format(daka.getSignIn());
 				obj[i][2] = strIn;
 			}
	    	if(daka.getSignOut() == null) {
	    		obj[i][3]  = "未签退";
	    	}else {
	    		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
 				String strOut = sdf.format(daka.getSignOut());
	    		obj[i][3] = strOut;
	    	}
	    	i++;
	    }
        tableModel = new DefaultTableModel(obj,columnNames) {
			public boolean isCellEditable(int row, int column) {
			    return false;
			  }
			};
		table = new JTable(tableModel);
		JScrollPane scroll = new JScrollPane(table);  
		scroll.setSize(300, 200);  
		getContentPane().add(scroll,BorderLayout.EAST);  
		setVisible(true);
		}
}
	