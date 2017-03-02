/**
 * @Date 2016年11月16日
 *
 * @author 郭  璞
 *
 */
package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;

import biz.Business;
import entity.Site;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JRadioButton;

/**
 * @author 郭  璞
 *
 */
public class MainPanel extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	public MainPanel() {
		setResizable(false);
		setSize(new Dimension(460, 493));
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainPanel.class.getResource("/ui/icon48.png")));
		setTitle("小秘·密码本");
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(6, 6, 442, 459);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tabbedPane.addTab("全部", new ImageIcon(MainPanel.class.getResource("/javax/swing/plaf/metal/icons/ocean/homeFolder.gif")), panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 6, 389, 447);
		panel.add(scrollPane);
		
		// 为 列表准备数据
//		Site site = Business.getSiteBySitename("慕课");
		DefaultListModel jListModel = new DefaultListModel();
		String[] listModel = {"慕课", "哔哩哔哩", "软院教务"};
		
		// 添加数据
		for(String item : listModel) {
			jListModel.addElement(item);
		}
		
		
		JList list = new JList();
		list.setFont(new Font("SansSerif", Font.PLAIN, 16));
		scrollPane.setViewportView(list);
		
		list.setModel(jListModel);
		
		//  设置双击事件
	    list.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if(e.getClickCount()%2 == 0) {
	    			JList mylist = (JList) e.getSource();
	    			int index = mylist.getSelectedIndex();
	    			
	    			Object obj = mylist.getModel().getElementAt(index);
	    			String sitename = obj.toString();
	    			Site site = Business.getSiteBySitename(sitename);
	    			String message = "\n网站名称：\t" + site.getsite_name()+":\n 用户名：\t" + site.getSite_username()+ "\n密码:\t"+ Business.decode(site.getSite_password());
	    			JOptionPane.showMessageDialog(list, message, site.getsite_name()+"的详细信息", JOptionPane.PLAIN_MESSAGE);
	    		}
	    	}
		});
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("查询", new ImageIcon(MainPanel.class.getResource("/com/sun/javafx/scene/web/skin/DecreaseIndent_16x16_JFX.png")), panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("网站名称：");
		label.setBounds(34, 36, 67, 18);
		panel_1.add(label);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(34, 66, 304, 2);
		panel_1.add(separator);
		
		textField = new JTextField();
		textField.setText("软院教务");
		textField.setBounds(113, 30, 150, 30);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("搜索");
		btnNewButton.setBounds(261, 30, 77, 30);
		panel_1.add(btnNewButton);
		
		JLabel label_1 = new JLabel("网站名称：");
		label_1.setBounds(34, 136, 69, 18);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("用 户 名：");
		label_2.setBounds(34, 190, 69, 18);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("加密密码：");
		label_3.setBounds(34, 244, 69, 18);
		panel_1.add(label_3);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(34, 166, 304, 2);
		panel_1.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(34, 230, 304, 2);
		panel_1.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(34, 293, 304, 2);
		panel_1.add(separator_3);
		
		JLabel label_4 = new JLabel("软院教务");
		label_4.setBounds(113, 136, 225, 18);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("201492115");
		label_5.setBounds(113, 190, 225, 18);
		panel_1.add(label_5);
		
		JLabel lblMjgnte = new JLabel("285514");
		lblMjgnte.setBounds(113, 244, 225, 18);
		panel_1.add(lblMjgnte);
		
		JButton button = new JButton("显示密码");
		button.setBounds(248, 325, 90, 30);
		panel_1.add(button);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("增加", new ImageIcon(MainPanel.class.getResource("/com/sun/javafx/scene/web/skin/FontBackgroundColor_16x16_JFX.png")), panel_2, null);
		panel_2.setLayout(null);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(43, 70, 304, 2);
		panel_2.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(43, 155, 304, 2);
		panel_2.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(43, 242, 304, 2);
		panel_2.add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(43, 334, 304, 2);
		panel_2.add(separator_7);
		
		JButton btnNewButton_1 = new JButton("确认");
		btnNewButton_1.setBounds(231, 411, 90, 30);
		panel_2.add(btnNewButton_1);
		
		JButton button_1 = new JButton("重置");
		button_1.setBounds(67, 411, 90, 30);
		panel_2.add(button_1);
		
		JLabel lblNewLabel = new JLabel("添加条目");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(240, 230, 140));
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		lblNewLabel.setBounds(114, 21, 147, 37);
		panel_2.add(lblNewLabel);
		
		JLabel label_7 = new JLabel("网站名称：");
		label_7.setBounds(43, 117, 65, 18);
		panel_2.add(label_7);
		
		JLabel label_8 = new JLabel("用 户 名：");
		label_8.setBounds(43, 205, 65, 18);
		panel_2.add(label_8);
		
		JLabel label_9 = new JLabel("用户密码：");
		label_9.setBounds(43, 291, 65, 18);
		panel_2.add(label_9);
		
		textField_1 = new JTextField();
		textField_1.setBounds(120, 111, 227, 30);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(120, 200, 227, 30);
		panel_2.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(120, 292, 227, 30);
		panel_2.add(textField_3);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(43, 397, 304, 2);
		panel_2.add(separator_8);
		
		JRadioButton radioButton = new JRadioButton("教育");
		radioButton.setBounds(43, 359, 54, 18);
		panel_2.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("影音");
		radioButton_1.setBounds(100, 359, 54, 18);
		panel_2.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("游戏");
		radioButton_2.setBounds(156, 359, 54, 18);
		panel_2.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("文档");
		radioButton_3.setBounds(215, 359, 54, 18);
		panel_2.add(radioButton_3);
		
		JRadioButton radioButton_4 = new JRadioButton("其他");
		radioButton_4.setBounds(284, 359, 54, 18);
		panel_2.add(radioButton_4);
		
		ButtonGroup btngroup = new ButtonGroup();
		btngroup.add(radioButton);
		btngroup.add(radioButton_1);
		btngroup.add(radioButton_2);
		btngroup.add(radioButton_3);
		btngroup.add(radioButton_4);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("修改", new ImageIcon(MainPanel.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Copy-Black.png")), panel_3, null);
		panel_3.setLayout(null);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setBounds(36, 67, 304, 2);
		panel_3.add(separator_9);
		
		JLabel label_10 = new JLabel("修改条目");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setForeground(new Color(240, 230, 140));
		label_10.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_10.setBounds(101, 20, 147, 37);
		panel_3.add(label_10);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setBounds(36, 135, 304, 2);
		panel_3.add(separator_10);
		
		JSeparator separator_11 = new JSeparator();
		separator_11.setBounds(36, 210, 304, 2);
		panel_3.add(separator_11);
		
		JSeparator separator_12 = new JSeparator();
		separator_12.setBounds(36, 295, 304, 2);
		panel_3.add(separator_12);
		
		JLabel label_11 = new JLabel("网站名称：");
		label_11.setBounds(36, 101, 73, 18);
		panel_3.add(label_11);
		
		textField_4 = new JTextField();
		textField_4.setBounds(121, 95, 219, 30);
		panel_3.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel label_12 = new JLabel("修改内容：");
		label_12.setBounds(36, 180, 73, 18);
		panel_3.add(label_12);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(121, 168, 219, 30);
		panel_3.add(textField_5);
		
		JLabel label_13 = new JLabel("修改类型：");
		label_13.setBounds(36, 252, 73, 18);
		panel_3.add(label_13);
		
		JRadioButton radioButton_5 = new JRadioButton("用户名");
		radioButton_5.setBounds(119, 252, 73, 18);
		panel_3.add(radioButton_5);
		
		JRadioButton radioButton_6 = new JRadioButton("密码");
		radioButton_6.setBounds(194, 252, 73, 18);
		panel_3.add(radioButton_6);
		
		JRadioButton radioButton_7 = new JRadioButton("所属标签");
		radioButton_7.setBounds(267, 252, 83, 18);
		panel_3.add(radioButton_7);
		
		JButton button_2 = new JButton("放弃修改");
		button_2.setBounds(124, 349, 90, 30);
		panel_3.add(button_2);
		
		JButton button_3 = new JButton("确认修改");
		button_3.setBounds(237, 349, 90, 30);
		panel_3.add(button_3);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("删除", new ImageIcon(MainPanel.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Cut-Black.png")), panel_4, null);
		panel_4.setLayout(null);
		
		JLabel label_14 = new JLabel("删除条目");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setForeground(new Color(240, 230, 140));
		label_14.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_14.setBounds(106, 28, 147, 37);
		panel_4.add(label_14);
		
		JSeparator separator_13 = new JSeparator();
		separator_13.setBounds(22, 77, 304, 2);
		panel_4.add(separator_13);
		
		JLabel label_15 = new JLabel("网站名称：");
		label_15.setBounds(53, 157, 82, 18);
		panel_4.add(label_15);
		
		textField_6 = new JTextField();
		textField_6.setBounds(117, 151, 209, 30);
		panel_4.add(textField_6);
		textField_6.setColumns(10);
		
		JSeparator separator_14 = new JSeparator();
		separator_14.setBounds(22, 201, 304, 2);
		panel_4.add(separator_14);
		
		JButton button_4 = new JButton("狠心删除");
		button_4.setBounds(236, 265, 90, 30);
		panel_4.add(button_4);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(MainPanel.class.getResource("/ui/beach.jpg")));
		lblNewLabel_1.setBounds(22, 240, 164, 188);
		panel_4.add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("放弃删除");
		btnNewButton_2.setBounds(236, 334, 90, 30);
		panel_4.add(btnNewButton_2);
	}
	
	public static void main(String[] args) {
		MainPanel panel = new MainPanel();
		panel.setVisible(true);
	}
}
