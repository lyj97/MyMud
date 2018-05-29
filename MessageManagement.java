package L_MyMud;
import java.io.*;
import java.util.*;
public class MessageManagement {
	static HashMap<String,PrintWriter> playerChannels = new HashMap<String,PrintWriter>();
	
	public static void showToPlayer(Player player, String message){
		if(playerChannels.containsKey(player.getId())) {
			playerChannels.get(player.getId()).println(message);
			playerChannels.get(player.getId()).flush();
		}
		else {
			System.out.println("���󣺸�����playerChannels�е�player " 
		+ player.getId() + " ��������Ϣ" + message +"��");
		}
	}
	public static void showToPlayer(Player player, String message, HashMap<String, Player> list) {//player����Ϊ�����ߣ�Ҫ�ƶ���player�� list�� Ҫ֪ͨ���û��б�
        //ʵ��list�������ֱ��list�е�player����Ϣ
        for (Map.Entry<String, Player> entry : list.entrySet()) {
            if (playerChannels.containsKey(entry.getKey())) {
                playerChannels.get(entry.getKey()).println(message);
                playerChannels.get(entry.getKey()).flush();
            }
            else {
                System.out.println("���󣺸�����playerChannels�е�player "
                        + entry.getKey() + " ��������Ϣ" + message + "��");
            }
        }
    }
	public static void addPlayerChannels(String playerId,PrintWriter pw){
		if(!playerChannels.containsKey(playerId)) {
			playerChannels.put(playerId, pw);
		}
		else {
			System.out.println("���󣺳�����playerChannels������Ѵ��ڵĵ�player " 
		+ playerId +"��");
		}
	}
	public static void removePlayerChannels(String playerId){
		if(playerChannels.containsKey(playerId)) {
			playerChannels.remove(playerId);
		}
		else {
			System.out.println("���󣺳����Ƴ�����playerChannels�е�player " 
		+ playerId +"��");
		}
	}
}
