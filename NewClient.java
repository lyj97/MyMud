package L_MyMud;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class NewClient extends JFrame {
    private JTextPane screen;//JTestArea
    private JTextField input;
    private JTextArea information;//JTextArea
    private JTextArea mission;//JTextArea
    private JButton connection;
    private Document doc = null;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter pwriter;

    private String ipaddress = "127.0.0.1";
    private int port = 9000;
    private boolean connected = false;

    private MonitorThread mthread;

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
                        System.out.println("客户端：[服务器发来消息] " + str);
                        String temp[] = str.split("\t\b");

                        setText(screen, str);

                    }
                    else {
                        setText(screen,CommonContent.WARNING + "\t\b" +  "您已经与服务器断开连接！");
                        connected = false;
                        break;
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                setText(screen,CommonContent.WARNING + "\t\b" +  "您已经与服务器断开连接！");
                connected = false;
            }
        }
    }

    public Component getComponent(){
        return screen;
    }

    public NewClient() {
        super("MyMud NewClient");
        try { // 使用Windows的界面风格
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        container.add(BorderLayout.CENTER, leftPanel);
        container.add(BorderLayout.EAST, rightPanel);
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout());
        screen = new JTextPane();
        screen.setEditable(false);
        screen.setAutoscrolls(true);
        screen.setBackground(Color.black);
        doc = screen.getStyledDocument();
        JScrollPane jsp = new  JScrollPane(screen);
        input = new JTextField();
        input.setFont(new Font("宋体",Font.BOLD,CommonContent.FOUNT_SIZE));
        connection = new JButton("Conncet");
        connection.setFont(new Font("宋体",Font.BOLD,CommonContent.FOUNT_SIZE));
        connection.setBackground(new Color(87, 206, 255));
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
        this.setSize(1200, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        input.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent keyevent) {
                // TODO Auto-generated method stub
                //用户键盘输入在这里添加
                if(connected) {
                    pwriter.flush();
                    if (keyevent.getKeyChar() == KeyEvent.VK_ENTER) {
                        String str = input.getText();
                        setText(screen, CommonContent.INPUT_INFORMATION + "\t\b" +  ">>您输入了：" + str);
                        input.selectAll();
                        try {
                            if (str.equals("q") || str.equals("quit")) {
                                pwriter.println("quit");
                                pwriter.flush();
                                information.setText("未登录。");
                                mission.setText("暂无任务。");
                                mission.setVisible(false);
                                //mthread.destroy();
                                return;
                            }
                            pwriter.println(str);
                            pwriter.flush();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    setText(screen, CommonContent.WARNING + "\t\b" +  "您还未与服务器建立连接，请首先点击右侧\"Connect\"按钮连接服务器！");
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
                        setText(screen,CommonContent.WARNING + "\t\b" +  "您已经连接到服务器，不可重复连接！");
                        connection.setBackground(new Color(255, 255, 255));
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
                        connection.setBackground(new Color(255, 255, 255));
                        input.setEditable(true);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        setText(screen,CommonContent.WARNING  + "\t\b" +  "连接服务器失败！请重试\n");
                        connection.setBackground(new Color(87, 206, 255));
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

    public void setText(JTextPane screen, String Message) {
        String[] temp1 = Message.split("\t");
        System.out.println(temp1[0]);
        if(temp1[0].equals(CommonContent.UPDATE_USER_INFORMATION_FLAG)){
            set_information(Message);
        }
        else if(temp1[0].equals(CommonContent.UPDATE_MISSION_INFORMATION_FLAG)){
            set_mission(Message);
        }
        else {
            String temp2[] = Message.split("\t\b");
            if(temp2.length == 2) {
                if(temp2[0].equals(CommonContent.INFORMATION)){
                    set_message_information(temp2[1]);
                }
                else if(temp2[0].equals(CommonContent.INPUT_INFORMATION)){
                    set_message_input_information(temp2[1]);
                }
                else if(temp2[0].equals(CommonContent.VALUE_CHANGED)){
                    set_message_value_changed(temp2[1]);
                }
                else if(temp2[0].equals(CommonContent.NOTIFICATION)){
                    set_message_notification(temp2[1]);
                }
                else if(temp2[0].equals(CommonContent.PLAYER_MOVING)){
                    set_message_player_moving(temp2[1]);
                }
                else if(temp2[0].equals(CommonContent.WARNING)){
                    set_message_warning(temp2[1]);
                }
                else if(temp2[0].equals(CommonContent.MESSAGE)){
                    set_message_message(temp2[1]);
                }
                else if(temp2[0].equals(CommonContent.WORLD_MESSAGE)){
                    set_message_world_message(temp2[1]);
                }
                else if(temp2[0].equals(CommonContent.NPC_MESSAGE)){
                    set_message_npc_message(temp2[1]);
                }
            }
            else{
                for (int i = 0; i < temp1.length; i++) {
                    screen.setText(screen.getText() + temp1[i] + "\n");
                    // System.out.print(temp[i]+"\n");
                }
            }
        }
        screen.setCaretPosition(screen.getDocument().getLength());
    }

    public static void main(String[] args) {
        new NewClient();
    }

    public void set_message_input_information(String str){
        SimpleAttributeSet attrset=new SimpleAttributeSet();
        StyleConstants.setForeground(attrset,new Color(101, 184, 133));
        StyleConstants.setFontFamily(attrset, "仿宋");
        insert(str,attrset);
    }

    public void set_message_information(String str){
        SimpleAttributeSet attrset=new SimpleAttributeSet();
        StyleConstants.setForeground(attrset,new Color(2, 201, 50));
        StyleConstants.setFontFamily(attrset, "楷体");
        insert(str,attrset);
    }

    public void set_message_value_changed(String str){
        SimpleAttributeSet attrset=new SimpleAttributeSet();
        StyleConstants.setForeground(attrset,new Color(255, 46, 174));
        StyleConstants.setBold(attrset,true);
        StyleConstants.setFontFamily(attrset, "微软雅黑");
        insert(str,attrset);
    }

    public void set_message_notification(String str){
        SimpleAttributeSet attrset=new SimpleAttributeSet();
        StyleConstants.setForeground(attrset,new Color(167, 76, 255));
        StyleConstants.setBold(attrset,true);
        insert(str,attrset);
    }

    public void set_message_player_moving(String str){
        SimpleAttributeSet attrset=new SimpleAttributeSet();
        StyleConstants.setForeground(attrset,new Color(59, 223, 255));
        insert(str,attrset);
    }

    public void set_message_warning(String str){
        SimpleAttributeSet attrset=new SimpleAttributeSet();
        StyleConstants.setForeground(attrset,new Color(255, 41, 28));
        StyleConstants.setBold(attrset,true);
        insert(str,attrset);
    }

    public void set_message_npc_message(String str){
        SimpleAttributeSet attrset=new SimpleAttributeSet();
        StyleConstants.setForeground(attrset,new Color(214, 163, 71));
        insert(str,attrset);
    }

    public void set_message_message(String str){
        SimpleAttributeSet attrset=new SimpleAttributeSet();
        StyleConstants.setForeground(attrset,new Color(32, 158, 255));
        StyleConstants.setBold(attrset,true);
        insert(str,attrset);
    }

    public void set_message_world_message(String str){
        SimpleAttributeSet attrset=new SimpleAttributeSet();
        StyleConstants.setForeground(attrset,new Color(151, 101, 255));
        StyleConstants.setBold(attrset,true);
        insert(str,attrset);
    }

    public void insert(String str,AttributeSet attrset){
        doc = screen.getDocument();//利用getDocument()方法取得JTextPane的Document instance.0
        str = str + "\n";
        try{
            String[] temp = str.split("\t");
            for (int i = 0; i < temp.length; i++) {
                doc.insertString(doc.getLength(),temp[i] + "\n",attrset);
                // System.out.print(temp[i]+"\n");
            }
            screen.setCaretPosition(screen.getDocument().getLength());
        }
        catch(BadLocationException ble){
            System.out.println("BadLocationException:"+ble);
        }
    }
}
