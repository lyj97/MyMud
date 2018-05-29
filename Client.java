package L_MyMud;
import java.io.*;
import javax.swing.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Client extends JFrame {
	private JTextArea screen;
	private JTextField input;
	private JTextArea information;
    private JTextArea mission;
	private JButton connection;

	private Socket socket;
	private BufferedReader in;
	//private BufferedWriter out;
	private PrintWriter pwriter;

	private String ipaddress = "127.0.0.1";
	private int port = 9000;
	private boolean connected = false;
	
	private MonitorThread mthread;
//
//	private HashMap<Integer, String> history;
//	private int current_number;
//	private int temp_number;
//
//	private void set_history(int no, String str){
//	    if(!(no > CommonContent.MAX_HISTORY_NUMBER)){
//	        System.out.println("错误：History标记出错！");
//        }
//        else{
//            history.put(no, str);
//        }
//    }
//
//    private String get_history(int no){
//        if(!(no < CommonContent.MAX_HISTORY_NUMBER)){
//            System.out.println("错误：History标记出错！");
//            return null;
//        }
//        else{
//            return history.get(no);
//        }
//    }

	class MonitorThread extends Thread {
		public MonitorThread(BufferedReader br) {
			//添加
			this.br = br;
		}

		BufferedReader br;

		@Override
		public void run() {
			//接收服务器消息的控制在这里添加
			//System.out.println("Run Client");
			String str;
			try {
				while(true) {
					str = br.readLine();
					//System.out.println("客户端："+str);
					if(str != null) {
						System.out.println("客户端：[服务器发来消息] "+str);
						setText(screen,str);
					}
					else {
						setText(screen,"您已经与服务器断开连接！");
						connected = false;
						break;
					}
				}
			}
			catch (Exception e) {
				setText(screen,"您已经与服务器断开连接！");
				connected = false;
				e.printStackTrace();
			}
		}
	}

	public Client() {
		super("MyMud Client");
		//history = new HashMap<>();
		//current_number = 0;
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		container.add(BorderLayout.CENTER, leftPanel);
		container.add(BorderLayout.EAST, rightPanel);
		leftPanel.setLayout(new BorderLayout());
		rightPanel.setLayout(new BorderLayout());
		screen = new JTextArea();
		screen.setEditable(false);
		screen.setAutoscrolls(true);
		screen.setFont(new Font("",Font.BOLD,CommonContent.FOUNT_SIZE));
		screen.setForeground(Color.BLACK);
		JScrollPane jsp = new  JScrollPane(screen);
		input = new JTextField();
		input.setFont(new Font("宋体",Font.BOLD,CommonContent.FOUNT_SIZE));
		connection = new JButton("Conncet");
		connection.setFont(new Font("宋体",Font.BOLD,CommonContent.FOUNT_SIZE));
		leftPanel.add(BorderLayout.CENTER, jsp);
		leftPanel.add(BorderLayout.SOUTH, input);
		information = new JTextArea("未登录。");
		mission = new JTextArea("暂无任务。");
		information.setEditable(false);
		information.setFont(new Font("微软雅黑",Font.BOLD,CommonContent.FOUNT_SIZE));
		mission.setEditable(false);
        mission.setVisible(false);
        mission.setFont(new Font("微软雅黑",Font.BOLD,CommonContent.FOUNT_SIZE));
		rightPanel.add(BorderLayout.NORTH, connection);
        rightPanel.add(BorderLayout.CENTER, information);
        rightPanel.add(BorderLayout.SOUTH, mission);
		this.setSize(500, 1000);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//temp_number = -1;
		input.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent keyevent) {
				// TODO Auto-generated method stub
				//用户键盘输入在这里添加
                if(connected) {
                    pwriter.flush();
                    if (keyevent.getKeyChar() == KeyEvent.VK_ENTER) {
                        String str = input.getText();
//                        if(str.equals(get_history(current_number))){
//
//                        }
//                        else{
//                            set_history(current_number+1, str);
//                            current_number++;
//                            if(current_number >= CommonContent.MAX_HISTORY_NUMBER){
//                                current_number = 0;
//                            }
//                        }
                        setText(screen, "您输入了：" + str);
                        input.selectAll();
                        try {
                            if (str.equals("q") || str.equals("quit")) {
                                pwriter.println("quit");
                                pwriter.flush();
                                information.setText("未登录。");
                                mission.setText("暂无任务。");
                                mission.setVisible(false);
                                mthread.destroy();
                            }
                            pwriter.println(str);
                            pwriter.flush();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
//
//                    else if (keyevent.getKeyChar() == KeyEvent.VK_UP){
//                        if(temp_number == -1){
//                            temp_number = current_number;
//                        }
//                        else{
//                            temp_number--;
//                            if(temp_number < 1){
//                                temp_number = CommonContent.MAX_HISTORY_NUMBER;
//                            }
//                        }
//                        input.setText(get_history(temp_number));
//                        input.selectAll();
//                    }
//                    else if (keyevent.getKeyChar() == KeyEvent.VK_DOWN){
//                        if(temp_number == -1){
//                            temp_number = current_number;
//                        }
//                        else{
//                            temp_number++;
//                            if(temp_number < 1){
//                                temp_number = CommonContent.MAX_HISTORY_NUMBER;
//                            }
//                        }
//                        input.setText(get_history(temp_number));
//                        input.selectAll();
//                    }
//
//                    else{
//                        temp_number = -1;
//                    }
//
                }
                else{
                    setText(screen, "您还未与服务器建立连接，请首先点击右侧\"Connect\"按钮连接服务器！");
                    input.setEditable(false);
                }
			}
		});
		connection.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(connected) {
					try {
						setText(screen,"您已经连接到服务器，不可重复连接！");
                        input.setEditable(true);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				else {
					try {
						//连接服务器在这里添加
						socket = new Socket(ipaddress,port);
						pwriter = new PrintWriter(socket.getOutputStream());
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						mthread = new MonitorThread(in);
						mthread.start();
                        connected = true;
                        input.setEditable(true);
					}
					catch (Exception e) {
						e.printStackTrace();
						screen.setText(screen.getText() + "连接服务器失败！请重试\n");
						//System.exit(1);
					}
				}
			}
		});
	}

	private void set_information(String information){
	    String inf = "【INFORMATION】\n";
	    String items[] = information.split("\t");
	    String temp[];
	    for(int i=1; i<items.length; i++){
	        temp = items[i].split(":");
	        if(temp.length == 2){
	            if(temp[0].equals("ID")){
	                inf += ("【" + temp[0] + "】:" + temp[1] + "\n");
                }
                else if(temp[0].equals("USERNAME")){
                    inf += ("【" + temp[0] + "】:" + temp[1] + "\n");
                }
                else if(temp[0].equals("EXPERIENCE")){
                    inf += ("【" + temp[0] + "】:" + temp[1] + "\n");
                }
                else if(temp[0].equals("CON")){
                    inf += ("【" + temp[0] + "】:" + temp[1] + "\n");
                }
                else if(temp[0].equals("DEX")){
                    inf += ("【" + temp[0] + "】:" + temp[1] + "\n");
                }
                else if(temp[0].equals("STR")){
                    inf += ("【" + temp[0] + "】:" + temp[1] + "\n");
                }
                else if(temp[0].equals("WIS")){
                    inf += ("【" + temp[0] + "】:" + temp[1] + "\n");
                }
                else if(temp[0].equals("HP")){
                    inf += ("【" + temp[0] + "】:" + temp[1] + "\n");
                }
                else if(temp[0].equals("MAX_HP")){
                    inf += ("【" + temp[0] + "】:" + temp[1] + "\n");
                }
                else if(temp[0].equals("NL")){
                    inf += ("【" + temp[0] + "】:" + temp[1] + "\n");
                }
                else if(temp[0].equals("MAX_NL")){
                    inf += ("【" + temp[0] + "】:" + temp[1] + "\n");
                }
                else if(temp[0].equals("JL")){
                    inf += ("【" + temp[0] + "】:" + temp[1] + "\n");
                }
                else if(temp[0].equals("MAX_JL")){
                    inf += ("【" + temp[0] + "】:" + temp[1] + "\n");
                }
                else if(temp[0].equals("PARTY")){
                    inf += ("【" + temp[0] + "】:" + temp[1] + "\n");
                }
                else if(temp[0].equals("LOCATION")){
                    inf += ("【" + temp[0] + "】:" + temp[1] + "\n");
                }
            }
            else{
	            System.out.println("出错：item:" + items[i]);
				if(temp[0].equals("ID")){
					inf += ("【" + temp[0] + "】:" + "\n");
				}
				else if(temp[0].equals("USERNAME")){
                    inf += ("【" + temp[0] + "】:" + "\n");
				}
				else if(temp[0].equals("EXPERIENCE")){
                    inf += ("【" + temp[0] + "】:" + "\n");
				}
				else if(temp[0].equals("CON")){
                    inf += ("【" + temp[0] + "】:" + "\n");
				}
				else if(temp[0].equals("DEX")){
                    inf += ("【" + temp[0] + "】:" + "\n");
				}
				else if(temp[0].equals("STR")){
                    inf += ("【" + temp[0] + "】:" + "\n");
				}
				else if(temp[0].equals("WIS")){
                    inf += ("【" + temp[0] + "】:" + "\n");
				}
				else if(temp[0].equals("HP")){
                    inf += ("【" + temp[0] + "】:" + "\n");
				}
				else if(temp[0].equals("MAX_HP")){
                    inf += ("【" + temp[0] + "】:" + "\n");
				}
				else if(temp[0].equals("NL")){
                    inf += ("【" + temp[0] + "】:" + "\n");
				}
				else if(temp[0].equals("MAX_NL")){
                    inf += ("【" + temp[0] + "】:" + "\n");
				}
				else if(temp[0].equals("JL")){
                    inf += ("【" + temp[0] + "】:" + "\n");
				}
				else if(temp[0].equals("MAX_JL")){
                    inf += ("【" + temp[0] + "】:" + "\n");
				}
				else if(temp[0].equals("PARTY")){
                    inf += ("【" + temp[0] + "】:" + "\n");
				}
				else if(temp[0].equals("LOCATION")){
                    inf += ("【" + temp[0] + "】:" + "\n");
				}
            }
        }
        this.information.setText(inf);
    }

    private void set_mission(String information){
        mission.setVisible(true);
        String inf = "【MISSION】\n";
        String items[] = information.split("\t");
        if(items.length == 1){
            mission.setText("暂无任务。");
            mission.setVisible(false);
            return;
        }
        for(int i=1 ;i<items.length; i++){
            inf += items[i] + "\n";
        }
        this.mission.setText(inf);
    }

	public void setDefaultCloseOperation(int arg0) {
		//super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (connected) {
			try {
				connected = false;
				pwriter.println("quit");
				pwriter.flush();
				socket.close();
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//System.exit(1);
	}

	public void setText(JTextArea screen, String Message) {
		String[] temp = Message.split("\t");
		if(temp[0].equals(CommonContent.UPDATE_USER_INFORMATION_FLAG)){
            set_information(Message);
        }
        else if(temp[0].equals(CommonContent.UPDATE_MISSION_INFORMATION_FLAG)){
            set_mission(Message);
        }
        else {
            for (int i = 0; i < temp.length; i++) {
                screen.setText(screen.getText() + temp[i] + "\n");
                // System.out.print(temp[i]+"\n");
            }
        }
		screen.setCaretPosition(screen.getDocument().getLength());
	}

	public static void main(String[] args) {
		new Client();
	}
}
