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
			System.out.println("错误：给不在playerChannels中的player " 
		+ player.getId() + " 发送了消息" + message +"。");
		}
	}
	public static void showToPlayer(Player player, String message, HashMap<String, Player> list) {//player：行为发生者（要移动的player） list： 要通知的用户列表
        //实现list遍历，分别给list中的player发消息
        for (Map.Entry<String, Player> entry : list.entrySet()) {
            if (playerChannels.containsKey(entry.getKey())) {
                playerChannels.get(entry.getKey()).println(message);
                playerChannels.get(entry.getKey()).flush();
            }
            else {
                System.out.println("错误：给不在playerChannels中的player "
                        + entry.getKey() + " 发送了消息" + message + "。");
            }
        }
    }
	public static void addPlayerChannels(String playerId,PrintWriter pw){
		if(!playerChannels.containsKey(playerId)) {
			playerChannels.put(playerId, pw);
		}
		else {
			System.out.println("错误：尝试在playerChannels中添加已存在的的player " 
		+ playerId +"。");
		}
	}
	public static void removePlayerChannels(String playerId){
		if(playerChannels.containsKey(playerId)) {
			playerChannels.remove(playerId);
		}
		else {
			System.out.println("错误：尝试移除不在playerChannels中的player " 
		+ playerId +"。");
		}
	}
}
