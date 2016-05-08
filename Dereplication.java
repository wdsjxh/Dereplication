package dereplication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Dereplication extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField3;
	private JButton btnNewButton1;
	private JTextArea textArea;
	private String resouce_file;
	private String destination_file;
	private BufferedWriter bb;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dereplication frame = new Dereplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public  void Two_file_to_one_file () throws IOException{
			HashMap<String, String> map =new HashMap<String, String>();
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(resouce_file),"GB2312"));
			String ss = br.readLine();
			while( ss != null){
				map.put(ss,"");
				ss = br.readLine();
			}
			br.close();
			FileWriter fw =new FileWriter(destination_file);
			bb=new BufferedWriter(fw);
//			Iterator<String> its=map.keySet().iterator();
			TreeMap<String, String> treemap =new TreeMap<String, String>();
			treemap.putAll(map);
			Iterator<String> its = treemap.keySet().iterator();
			while(its.hasNext()){
				bb.write(its.next().toString());
				bb.flush();
//				System.out.println(its.next().toString());
				bb.newLine();
			}
			bb.close();
			fw.close();
		}
	
	public Dereplication() {
		setTitle("java去重demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField1 = new JTextField();
		textField1.setBounds(95, 31, 232, 21);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		JLabel lblNewLabel1 = new JLabel("原文件：");
		lblNewLabel1.setBounds(10, 34, 54, 18);
		contentPane.add(lblNewLabel1);
		
		JLabel lblNewLabel2 = new JLabel("中间过程\r\n");
		lblNewLabel2.setBounds(20, 68, 99, 15);
		contentPane.add(lblNewLabel2);
		
		JLabel lblNewLabel3 = new JLabel("新文件\r\n：");
		lblNewLabel3.setBounds(12, 224, 54, 15);
		contentPane.add(lblNewLabel3);
		
		textField3 = new JTextField();
		textField3.setBounds(103, 219, 221, 21);
		contentPane.add(textField3);
		textField3.setColumns(10);
		
		JButton Button = new JButton("执行");
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Two_file_to_one_file();
					textArea.append("开始去重，哈哈");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		Button.setBounds(337, 114, 75, 44);
		contentPane.add(Button);
		
		btnNewButton1 = new JButton("选择");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc=new JFileChooser("C:\\Users\\wdsj\\Desktop\\dereplication");
				int val=fc.showOpenDialog(null);   //showOpenDialog 打开选择框
				
				if(val==JFileChooser.APPROVE_OPTION){
					resouce_file=fc.getSelectedFile().toString();   //保存原文件路径
					textField1.setText(fc.getSelectedFile().toString());
					textArea.setText("已选择原文件：");
					textArea.append(fc.getSelectedFile().toString()+"\n");
				}else{
					textField1.setText("未选择文件");
				}
			}
		});
		btnNewButton1.setBounds(337, 30, 75, 23);
		contentPane.add(btnNewButton1);
		
		textArea = new JTextArea();
		textArea.setBounds(30, 93, 297, 90);
		contentPane.add(textArea);
		
		JButton btnNewButton2 = new JButton("保存");
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc=new JFileChooser("C:\\Users\\wdsj\\Desktop\\dereplication");
				int val=fc.showSaveDialog(null);   //showSaveDialog 保存选择框
				
				if(val==JFileChooser.APPROVE_OPTION){
					destination_file=fc.getSelectedFile().toString();   //保存目的文件路径
					textField3.setText(fc.getSelectedFile().toString());
					textArea.append("去重后文件：");
					textArea.append(fc.getSelectedFile().toString()+"\n");
				}else{
					textField3.setText("未选择文件");
				}
			}
		});
		btnNewButton2.setBounds(347, 220, 65, 23);
		contentPane.add(btnNewButton2);
	}
}
