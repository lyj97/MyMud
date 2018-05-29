package L_MyMud;
import java.io.*;
import java.net.*;

public class Server {

	static class ServerThread extends Thread {

		public ServerThread(Socket socket) {
			//添加
			this.socket = socket;
		}

		Socket socket;
		ServerSocket server;
		BufferedReader reader;
		//BufferedWriter writer;
		PrintWriter pwriter;
		
		Player player;

		@Override
		public void run() {
			System.out.println("服务器：线程开始。");
			try {
				//服务器响应代码在这里添加
				//writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pwriter = new PrintWriter(socket.getOutputStream());
				pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "连接服务器成功！\t欢迎！\t请登录或注册您的账号以开始游玩。\t");
				pwriter.flush();
				pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "请输入用户ID：");
				pwriter.flush();
				//writer.write("连接服务器成功！");
				//writer.flush();
				String str;
				str = reader.readLine();
				while(str == null||str.length() == 0) {
					pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "ID不能为空！");
					pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "请输入用户ID：");
					pwriter.flush();
					str = reader.readLine();
				}

				if(Player.player_list.containsKey(str)) {
					Player player = Player.player_list.get(str);
					//登录流程
					String password = player.getpassword();
					pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "请输入密码：");
					pwriter.flush();
					str = reader.readLine();
					int wrong_input = 0;
					while(true) {
						while(str == null||str.length() == 0) {
							pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "密码不能为空！");
							pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "请输入密码：");
							pwriter.flush();
							str = reader.readLine();
						}
						if(password.equals(str)) {
							this.player = player;
							break;
						}
						else {
							wrong_input++;
							if(wrong_input == 5) {
								try {
									if(socket != null) {
										socket.close();
									}
								}
								catch (Exception e) {
									e.printStackTrace();
								}
							}
							pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "密码输入错误！您还可以尝试 " + (5-wrong_input) + " 次。");
							pwriter.flush();
							str = reader.readLine();
						}
					}
				}
				else {
					//注册流程
					pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "用户ID：\"" + str + "\"不存在！将创建新用户！");
					pwriter.flush();
					String user_id = new String(str);
					String user_name;
					String user_password;
					pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "请输入昵称：");
					pwriter.flush();
					str = reader.readLine();
					while(str == null||str.length() == 0) {
						pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "昵称不能为空！");
						pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "请输入昵称：");
						pwriter.flush();
						str = reader.readLine();
					}
					user_name = new String(str);
					pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "请输入密码：");
					pwriter.flush();
					str = reader.readLine();
					while(str == null||str.length() == 0) {
						pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "密码不能为空！");
						pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "请输入密码：");
						pwriter.flush();
						str = reader.readLine();
					}
					user_password = new String(str);
					this.player = new Player(user_id,user_name,user_password);
				}

                pwriter.println(CommonContent.INFORMATION + "\t\b" + "登录成功！欢迎来到MyMud!");
                pwriter.flush();
                //System.out.println("服务器：" + str);
                MessageManagement.addPlayerChannels(player.getId(), pwriter);
				RoomManagement.cityMap.get(player.getLocation()).enter(player);
				
				str = reader.readLine();
				while(true) {
					System.out.println("服务器：[客户端发来消息]"+str);
					if(str != null) {
						if(str.equals("quit")||str.equals("exit")) {
							break;
						}
						UserInput.dealInput(player, str);
						str = reader.readLine();
					}
					else{
					    break;
                    }
				}
				System.out.println("客户端退出。");
				//pwriter.println("您已退出。");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					player.save();
					pwriter.println("您已退出MyMud，期待您再来！");
					player.quit();
					if(socket != null) {
						socket.close();
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static int PORT_NUM = 9000;
	public static RoomManagement rooms;//房间

	static public void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(PORT_NUM);

		StaticFunctions.load_file();
		
		for (;;) {
			Socket socket = serverSocket.accept();
			System.out.println("服务器：收到连接请求。");
			new ServerThread(socket).start();
			
		}
		
	}//end main

}//end Server class
