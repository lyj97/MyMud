package L_MyMud;
import java.util.*;

import L_MyMud.CommonContent.DIRECTION;

public class Room {
	//添加物品列表？

	private HashMap<CommonContent.DIRECTION, Room> neighbor = new HashMap<CommonContent.DIRECTION, Room>();

	void setRoom(CommonContent.DIRECTION d, Room r) {
		neighbor.put(d, r);
		// assert r.getRoom(d) == this;
	}

	public Room getRoom(CommonContent.DIRECTION d) {
		if(this.neighbor.containsKey(d)) {
			return neighbor.get(d);
		}
		else {
			return null;
		}
	}

	private String roomDescription;
	private String roomLooking;
	private String roomId;
	private String roomName;
	
	private HashMap<String, Player> playerList = new HashMap<String, Player>();//房间中的Player
	HashMap<String, NonPlayerCharacter> NPCList = new HashMap<>();//房间中的NPC
	public HashMap<String, Item> itemList = new HashMap<>();//房间物品列表

	public void exit(Player player) {
        MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + "玩家[" + player.getfullName() + "]下线了。", getRoomPlayers(player));
        removePlayer(player);
	}

	void enter(){
	    for(Map.Entry<String, NonPlayerCharacter> entry : NPCList.entrySet()){
	        if(!entry.getValue().thread.isAlive()){
                entry.getValue().thread.start();
            }
        }
    }

    public void enter(Player player){
	    //用户player以登录形式加入房间
        //通知当前房间中的player有人上线
        enter();
        Player.player_list.put(player.getId(), player);
        Player.online_player_list.put(player.getId(), player);
        addPlayer(player);
        Mission.load_mission(player);
        MessageManagement.showToPlayer(player, CommonContent.PLAYER_MOVING + "\t\b" + "玩家[" + player.getfullName() + "]上线了。", getRoomPlayers(player));
        MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + RoomManagement.cityMap.get(player.getLocation()).getRoomLooking(player));
		MessageManagement.showToPlayer(player, player.get_information());
    }

	public void enter(Player player, CommonContent.DIRECTION direction) {
	    enter();
		Room destination = this.getRoom(direction);
		if(destination != null) {
			//离开房间
			this.removePlayer(player);
            destination.addPlayer(player);
            MessageManagement.showToPlayer(player, CommonContent.PLAYER_MOVING + "\t\b" + "玩家[" + player.getfullName() + "]从" + StaticFunctions.getDirection(direction) + "方向走了出去。", getRoomPlayers(player));
            MessageManagement.showToPlayer(player, CommonContent.PLAYER_MOVING + "\t\b" + "玩家[" + player.getfullName() + "]从" + StaticFunctions.getReverseDirection(direction) + "方向走了进来。", destination.getRoomPlayers(player));
            player.setLocation(destination.roomId);
			MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + RoomManagement.cityMap.get(player.getLocation()).getRoomLooking(player));
            MessageManagement.showToPlayer(player, player.get_information());
		}
		else {
			//没有出口
			MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + CommonContent.CANNOTMOVE);
		}
	}
	public void removePlayer(Player player){
	//用户退出后，清除用户在列表中内容，通知房间内其他玩家
		if(this.playerList.containsKey(player.getId())) {
			this.playerList.remove(player.getId());
			//通知当前房间中的player有人离开
		}
		else {
			System.out.println("错误：尝试从playerList (Room:" + this.roomName +  ") 中移除不存在的的player " 
					+ player.getId() +"。");
		}
	}
	public void addPlayer(Player player){
	//用户连线进入，加入列表，通知房间其他玩家
		if(!this.playerList.containsKey(player.getId())) {
			this.playerList.put(player.getId(),player);
			//通知当前房间中的player有人进入
		}
		else {
			System.out.println("错误：尝试在playerList中添加已存在的的player " 
					+ player.getId() +"。");
		}
		player.setLocation(this.roomId);
	}

	public void add_item(Item item){
	    if(itemList.containsKey(item.id)){
	        System.out.println("错误：尝试向房间" + getRoomId() + "中添加已存在的物品 " + item.id +"(" + item.name + ")。");
        }
        else{
            itemList.put(item.id, item);
        }
    }

    public void remove_item(Item item){
        if(!itemList.containsKey(item.id)){
            System.out.println("错误：尝试从房间" + getRoomId() + "中移除不存在的物品 " + item.id +"(" + item.name + ")。");
        }
        else{
            itemList.remove(item.id);
        }
    }

	public void setDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public String getDescription() {
		return roomDescription;
	}

	public HashMap<String, Player> getPlayerList(){
	    return playerList;
    }

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void SetRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
	}

	public String getRoomLooking(Player player) {
		// 房间名
		roomLooking = roomName + "\t";
		// 房间描述
		// 应该由Client负责解析传输过来的字符（设定字体，每行字数）
		int roomDescriptionLength = roomDescription.length();
		if (roomDescriptionLength <= CommonContent.CHARS_PER_LINE)
			roomLooking += roomDescription + "\t";
		else {
			int i;
			for (i = 0; i <= roomDescriptionLength
					- CommonContent.CHARS_PER_LINE; i = i
					+ CommonContent.CHARS_PER_LINE) {
				roomLooking += roomDescription.substring(i, i
						+ CommonContent.CHARS_PER_LINE)
						+ "\t";
			}
			roomLooking += roomDescription.substring(i, roomDescriptionLength)
					+ "\t";
		}

		// 房间出口
		roomLooking += getChuKou() + "\t";
		// 房间npc
        roomLooking += listRoomNPCs() + "\t";
        // 房间obj
        roomLooking += listRoomItems() + "\t";
		// 房间player
		roomLooking += listRoomPlayers(player);
		return roomLooking;
	}

	public HashMap<String, Player> getRoomPlayers(Player player){
        HashMap<String, Player> player_list = new HashMap<>();
        for(Map.Entry<String, Player> entry : playerList.entrySet()){
        	if(!entry.getKey().equals(player.getId())){
                player_list.put(entry.getKey(), entry.getValue());
            }
		}
        return player_list;
    }

    private String listRoomItems(){
        String temp = "房间内的物品：\t";
        for(Map.Entry<String, Item> entry : itemList.entrySet()){
            temp += (entry.getValue().name + "(" + entry.getValue().id + ")\t");
        }
        return temp;
    }

    private String listRoomNPCs(){
        String temp = "房间内的NPC：\t";
        for(Map.Entry<String, NonPlayerCharacter> entry : NPCList.entrySet()){
            temp += entry.getValue().get_full_name() + "\t";
        }
        return temp;
    }

	private String listRoomPlayers(Player player){
		//列出这个房间中的所有玩家
		String temp = "房间内的玩家：\t";
		for(Map.Entry<String, Player> entry : playerList.entrySet()){
		    if(entry.getKey().equals(player.getId())){
		        temp += (entry.getValue().getfullName() + "【我】\t");
            }
            else{
                temp += (entry.getValue().getfullName() + "\t");
            }
        }
		return temp;
	}

	private String getChuKou() {
		/*描述每个房间的出口
		 * 
		 * 
		 * */
		String temp = "这里的明显出口：\t";
		if(this.neighbor.containsKey(CommonContent.DIRECTION.EAST)) {
			temp += StaticFunctions.getDirection(CommonContent.DIRECTION.EAST);
			temp += ":";
			temp += this.neighbor.get(CommonContent.DIRECTION.EAST).roomName;
			temp += "\t";
		}
		if(this.neighbor.containsKey(CommonContent.DIRECTION.WEST)) {
			temp += StaticFunctions.getDirection(CommonContent.DIRECTION.WEST);
			temp += ":";
			temp += this.neighbor.get(CommonContent.DIRECTION.WEST).roomName;
			temp += "\t";
		}
		if(this.neighbor.containsKey(CommonContent.DIRECTION.SOUTH)) {
			temp += StaticFunctions.getDirection(CommonContent.DIRECTION.SOUTH);
			temp += ":";
			temp += this.neighbor.get(CommonContent.DIRECTION.SOUTH).roomName;
			temp += "\t";
		}
		if(this.neighbor.containsKey(CommonContent.DIRECTION.NORTH)) {
			temp += StaticFunctions.getDirection(CommonContent.DIRECTION.NORTH);
			temp += ":";
			temp += this.neighbor.get(CommonContent.DIRECTION.NORTH).roomName;
			temp += "\t";
		}
		if(this.neighbor.containsKey(CommonContent.DIRECTION.NORTHEAST)) {
			temp += StaticFunctions.getDirection(CommonContent.DIRECTION.NORTHEAST);
			temp += ":";
			temp += this.neighbor.get(CommonContent.DIRECTION.NORTHEAST).roomName;
			temp += "\t";
		}
		if(this.neighbor.containsKey(CommonContent.DIRECTION.NORTHWEST)) {
			temp += StaticFunctions.getDirection(CommonContent.DIRECTION.NORTHWEST);
			temp += ":";
			temp += this.neighbor.get(CommonContent.DIRECTION.NORTHWEST).roomName;
			temp += "\t";
		}
		if(this.neighbor.containsKey(CommonContent.DIRECTION.SOUTHEAST)) {
			temp += StaticFunctions.getDirection(CommonContent.DIRECTION.SOUTHEAST);
			temp += ":";
			temp += this.neighbor.get(CommonContent.DIRECTION.SOUTHEAST).roomName;
			temp += "\t";
		}
		if(this.neighbor.containsKey(CommonContent.DIRECTION.SOUTHWEST)) {
			temp += StaticFunctions.getDirection(CommonContent.DIRECTION.SOUTHWEST);
			temp += ":";
			temp += this.neighbor.get(CommonContent.DIRECTION.SOUTHWEST).roomName;
			temp += "\t";
		}
		if(this.neighbor.containsKey(CommonContent.DIRECTION.UP)) {
			temp += StaticFunctions.getDirection(CommonContent.DIRECTION.UP);
			temp += ":";
			temp += this.neighbor.get(CommonContent.DIRECTION.UP).roomName;
			temp += "\t";
		}
		if(this.neighbor.containsKey(CommonContent.DIRECTION.DOWN)) {
			temp += StaticFunctions.getDirection(CommonContent.DIRECTION.DOWN);
			temp += ":";
			temp += this.neighbor.get(CommonContent.DIRECTION.DOWN).roomName;
			temp += "\t";
		}
		return temp;
	}
}
