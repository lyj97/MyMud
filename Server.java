package L_MyMud;
import java.io.*;
import java.net.*;

public class Server {

	static class ServerThread extends Thread {

		public ServerThread(Socket socket) {
			//���
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
			System.out.println("���������߳̿�ʼ��");
			try {
				//��������Ӧ�������������
				//writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pwriter = new PrintWriter(socket.getOutputStream());
				pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "���ӷ������ɹ���\t��ӭ��\t���¼��ע�������˺��Կ�ʼ���档\t");
				pwriter.flush();
				pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "�������û�ID��");
				pwriter.flush();
				//writer.write("���ӷ������ɹ���");
				//writer.flush();
				String str;
				str = reader.readLine();
				while(str == null||str.length() == 0) {
					pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "ID����Ϊ�գ�");
					pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "�������û�ID��");
					pwriter.flush();
					str = reader.readLine();
				}

				if(Player.player_list.containsKey(str)) {
					Player player = Player.player_list.get(str);
					//��¼����
					String password = player.getpassword();
					pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "���������룺");
					pwriter.flush();
					str = reader.readLine();
					int wrong_input = 0;
					while(true) {
						while(str == null||str.length() == 0) {
							pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "���벻��Ϊ�գ�");
							pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "���������룺");
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
							pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "������������������Գ��� " + (5-wrong_input) + " �Ρ�");
							pwriter.flush();
							str = reader.readLine();
						}
					}
				}
				else {
					//ע������
					pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "�û�ID��\"" + str + "\"�����ڣ����������û���");
					pwriter.flush();
					String user_id = new String(str);
					String user_name;
					String user_password;
					pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "�������ǳƣ�");
					pwriter.flush();
					str = reader.readLine();
					while(str == null||str.length() == 0) {
						pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "�ǳƲ���Ϊ�գ�");
						pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "�������ǳƣ�");
						pwriter.flush();
						str = reader.readLine();
					}
					user_name = new String(str);
					pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "���������룺");
					pwriter.flush();
					str = reader.readLine();
					while(str == null||str.length() == 0) {
						pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "���벻��Ϊ�գ�");
						pwriter.println(CommonContent.NOTIFICATION + "\t\b" + "���������룺");
						pwriter.flush();
						str = reader.readLine();
					}
					user_password = new String(str);
					this.player = new Player(user_id,user_name,user_password);
				}

                pwriter.println(CommonContent.INFORMATION + "\t\b" + "��¼�ɹ�����ӭ����MyMud!");
                pwriter.flush();
                //System.out.println("��������" + str);
                MessageManagement.addPlayerChannels(player.getId(), pwriter);
				RoomManagement.cityMap.get(player.getLocation()).enter(player);
				
				str = reader.readLine();
				while(true) {
					System.out.println("��������[�ͻ��˷�����Ϣ]"+str);
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
				System.out.println("�ͻ����˳���");
				//pwriter.println("�����˳���");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					player.save();
					pwriter.println("�����˳�MyMud���ڴ���������");
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
	public static RoomManagement rooms;//����

	static public void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(PORT_NUM);

		StaticFunctions.load_file();
		
		for (;;) {
			Socket socket = serverSocket.accept();
			System.out.println("���������յ���������");
			new ServerThread(socket).start();
			
		}
		
	}//end main

}//end Server class
