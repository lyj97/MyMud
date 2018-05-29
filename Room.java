package L_MyMud;
import java.util.*;

import L_MyMud.CommonContent.DIRECTION;

public class Room {
	//�����Ʒ�б�

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
	
	private HashMap<String, Player> playerList = new HashMap<String, Player>();//�����е�Player
	HashMap<String, NonPlayerCharacter> NPCList = new HashMap<>();//�����е�NPC
	public HashMap<String, Item> itemList = new HashMap<>();//������Ʒ�б�

	public void exit(Player player) {
        MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + "���[" + player.getfullName() + "]�����ˡ�", getRoomPlayers(player));
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
	    //�û�player�Ե�¼��ʽ���뷿��
        //֪ͨ��ǰ�����е�player��������
        enter();
        Player.player_list.put(player.getId(), player);
        Player.online_player_list.put(player.getId(), player);
        addPlayer(player);
        Mission.load_mission(player);
        MessageManagement.showToPlayer(player, CommonContent.PLAYER_MOVING + "\t\b" + "���[" + player.getfullName() + "]�����ˡ�", getRoomPlayers(player));
        MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + RoomManagement.cityMap.get(player.getLocation()).getRoomLooking(player));
		MessageManagement.showToPlayer(player, player.get_information());
    }

	public void enter(Player player, CommonContent.DIRECTION direction) {
	    enter();
		Room destination = this.getRoom(direction);
		if(destination != null) {
			//�뿪����
			this.removePlayer(player);
            destination.addPlayer(player);
            MessageManagement.showToPlayer(player, CommonContent.PLAYER_MOVING + "\t\b" + "���[" + player.getfullName() + "]��" + StaticFunctions.getDirection(direction) + "�������˳�ȥ��", getRoomPlayers(player));
            MessageManagement.showToPlayer(player, CommonContent.PLAYER_MOVING + "\t\b" + "���[" + player.getfullName() + "]��" + StaticFunctions.getReverseDirection(direction) + "�������˽�����", destination.getRoomPlayers(player));
            player.setLocation(destination.roomId);
			MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + RoomManagement.cityMap.get(player.getLocation()).getRoomLooking(player));
            MessageManagement.showToPlayer(player, player.get_information());
		}
		else {
			//û�г���
			MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + CommonContent.CANNOTMOVE);
		}
	}
	public void removePlayer(Player player){
	//�û��˳�������û����б������ݣ�֪ͨ�������������
		if(this.playerList.containsKey(player.getId())) {
			this.playerList.remove(player.getId());
			//֪ͨ��ǰ�����е�player�����뿪
		}
		else {
			System.out.println("���󣺳��Դ�playerList (Room:" + this.roomName +  ") ���Ƴ������ڵĵ�player " 
					+ player.getId() +"��");
		}
	}
	public void addPlayer(Player player){
	//�û����߽��룬�����б�֪ͨ�����������
		if(!this.playerList.containsKey(player.getId())) {
			this.playerList.put(player.getId(),player);
			//֪ͨ��ǰ�����е�player���˽���
		}
		else {
			System.out.println("���󣺳�����playerList������Ѵ��ڵĵ�player " 
					+ player.getId() +"��");
		}
		player.setLocation(this.roomId);
	}

	public void add_item(Item item){
	    if(itemList.containsKey(item.id)){
	        System.out.println("���󣺳����򷿼�" + getRoomId() + "������Ѵ��ڵ���Ʒ " + item.id +"(" + item.name + ")��");
        }
        else{
            itemList.put(item.id, item);
        }
    }

    public void remove_item(Item item){
        if(!itemList.containsKey(item.id)){
            System.out.println("���󣺳��Դӷ���" + getRoomId() + "���Ƴ������ڵ���Ʒ " + item.id +"(" + item.name + ")��");
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
		// ������
		roomLooking = roomName + "\t";
		// ��������
		// Ӧ����Client�����������������ַ����趨���壬ÿ��������
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

		// �������
		roomLooking += getChuKou() + "\t";
		// ����npc
        roomLooking += listRoomNPCs() + "\t";
        // ����obj
        roomLooking += listRoomItems() + "\t";
		// ����player
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
        String temp = "�����ڵ���Ʒ��\t";
        for(Map.Entry<String, Item> entry : itemList.entrySet()){
            temp += (entry.getValue().name + "(" + entry.getValue().id + ")\t");
        }
        return temp;
    }

    private String listRoomNPCs(){
        String temp = "�����ڵ�NPC��\t";
        for(Map.Entry<String, NonPlayerCharacter> entry : NPCList.entrySet()){
            temp += entry.getValue().get_full_name() + "\t";
        }
        return temp;
    }

	private String listRoomPlayers(Player player){
		//�г���������е��������
		String temp = "�����ڵ���ң�\t";
		for(Map.Entry<String, Player> entry : playerList.entrySet()){
		    if(entry.getKey().equals(player.getId())){
		        temp += (entry.getValue().getfullName() + "���ҡ�\t");
            }
            else{
                temp += (entry.getValue().getfullName() + "\t");
            }
        }
		return temp;
	}

	private String getChuKou() {
		/*����ÿ������ĳ���
		 * 
		 * 
		 * */
		String temp = "��������Գ��ڣ�\t";
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
