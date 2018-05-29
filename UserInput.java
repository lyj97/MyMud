package L_MyMud;
public class UserInput {
	public static void dealInput(Player player, String inputMessage) {
		/*
		 * ���Դ�������� l,look e,east,w,west,n,north,s,south,
		 */
		if(player == null) {
			System.out.println("���󣺳��Ը������ڵ��û� (player == null) ������Ϣ��");
			return;
		}
		String[] inputs = inputMessage.split(" ");
		if (inputs[0].equals("l") || inputs[0].equals("look")) {
			if (inputs.length == 1) {
			    player.look("");
				return;
			}
			else if(inputs.length == 2){
                player.look(inputs[1]);
            }
            else{
                MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + "������һ�����������look [something_in_the_room]��");
            }
		}
		else if (inputs[0].equals("quit")) {
			
			return;
		}
		else if (inputs[0].equals("e") || inputs[0].equals("east")) {
		    player.move(CommonContent.DIRECTION.EAST);
			return;
		}
		else if (inputs[0].equals("w") || inputs[0].equals("west")) {
            player.move(CommonContent.DIRECTION.WEST);
			return;
		}
		else if (inputs[0].equals("s") || inputs[0].equals("south")) {
            player.move(CommonContent.DIRECTION.SOUTH);
			return;
		}
		else if (inputs[0].equals("n") || inputs[0].equals("north")) {
            player.move(CommonContent.DIRECTION.NORTH);
			return;
		}
		else if (inputs[0].equals("ne") || inputs[0].equals("northeast")) {
            player.move(CommonContent.DIRECTION.NORTHEAST);
		}
		else if (inputs[0].equals("nw") || inputs[0].equals("northwest")) {
            player.move(CommonContent.DIRECTION.NORTHWEST);
			return;
		}
		else if (inputs[0].equals("se") || inputs[0].equals("southeast")) {
            player.move(CommonContent.DIRECTION.SOUTHEAST);
			return;
		}
		else if (inputs[0].equals("sw") || inputs[0].equals("southwest")) {
            player.move(CommonContent.DIRECTION.SOUTHWEST);
		}
		else if (inputs[0].equals("u") || inputs[0].equals("up")) {
            player.move(CommonContent.DIRECTION.UP);
			return;
		}
		else if (inputs[0].equals("d") || inputs[0].equals("down")) {
            player.move(CommonContent.DIRECTION.DOWN);
			return;
		}
        else if (inputs[0].equals("c") || inputs[0].equals("chat")){
            if (inputs.length == 1){
                MessageManagement.showToPlayer(player,CommonContent.INFORMATION + "\t\b" + "��˭���죿��chat [player_ID] [Something you want to say.]����");
            }
            else if (inputs.length == 2){
                MessageManagement.showToPlayer(player,CommonContent.NOTIFICATION + "\t\b" + ">>��˵��" + inputs[1]);
                MessageManagement.showToPlayer(player,CommonContent.MESSAGE + "\t\b" + "��" + player.getfullName() + "��˵��" + inputs[1], RoomManagement.cityMap.get(player.getLocation()).getRoomPlayers(player));
            }
            else if (inputs.length == 3){
                Player player_to_chat = Player.online_player_list.get(inputs[1]);
                if(player_to_chat != null){
                	if(player.getId().equals(player_to_chat.getId())){
                        MessageManagement.showToPlayer(player,CommonContent.INFORMATION + "\t\b" + "��һ�º�����������ɣ�chat [player_ID] [Something you want to say.]����");
                    }
                    else {
                        MessageManagement.showToPlayer(player, CommonContent.NOTIFICATION + "\t\b" + "�����Ķԡ�" + player_to_chat.getfullName() + "��˵��" + inputs[2]);
                        MessageManagement.showToPlayer(player_to_chat, CommonContent.MESSAGE + "\t\b" + "��" + player.getfullName() + "�����Ķ���˵��" + inputs[2]);
                    }
                }
                else{
                    MessageManagement.showToPlayer(player,CommonContent.WARNING + "\t\b" + "��ȡ��ҡ�" + inputs[1] + "����Ϣʧ�ܣ�����player_ID�����Ƿ���ȷ�����ԡ�");
                }
            }
            else{
                MessageManagement.showToPlayer(player,CommonContent.WARNING + "\t\b" + "����һ����Ч��ָ�");
                MessageManagement.showToPlayer(player,CommonContent.INFORMATION + "\t\b" + "��˭���죿��chat [player_ID] [Something you want to say.]����");
            }
        }
        else if (inputs[0].equals("act")){
            if (inputs.length == 1){
                MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + "act to ?(act [player_id] [movement_id])");
            }
            else if(inputs.length == 2){
                MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + "act to ?(act [player_id] [movement_id])");
            }
            else{
                Player player_to_act = (RoomManagement.cityMap.get(player.getLocation()).getPlayerList()).get(inputs[1]);
                if(player_to_act != null){
                    Movement.act(player, player_to_act, inputs[2]);
                }
                else{
                    MessageManagement.showToPlayer(player,CommonContent.INFORMATION + "\t\b" + "��ȡ��ҡ�" + inputs[1] + "����Ϣʧ�ܣ�����player_ID�����Ƿ���ȷ����������Ƿ���ͬһ�����в����ԡ�");
                }
            }
        }
        else if (inputs[0].equals("ask")){
		    if(inputs.length == 3){
		        NonPlayerCharacter npc = RoomManagement.cityMap.get(player.getLocation()).NPCList.get(inputs[1]);
		        if(npc == null){
                    MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + "Ask something ? (ask [NPC_id] [something])");
                }
                else{
		            npc.chat(player, inputs[2]);
                }
            }
            else{
		        MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + "Ask something ? (ask [NPC_id] [something])");
            }
        }
		else if (inputs[0].equals("get")){
		    if(inputs.length == 3){
		        if(inputs[1].equals("item")) {
                    player.get_item(inputs[2]);
                }
                else if(inputs[1].equals("mission")){
		            Mission mission = MissionManagement.MISSION_LIST.get(inputs[2]);
		            if(mission != null) {
                        player.get_mission(mission);
                    }
                    else{
                        MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + "Get mission ?(get mission [mission_id])");
                    }
                }
                else{
                    MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + "Get something ?(get [sign(e.g. item)] [something])");
                }
            }
            else{
                MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + "Get something ?(get [sign(e.g. item)] [something])");
            }
        }
        else if (inputs[0].equals("drop")){
            if(inputs.length == 2){
                player.drop_item(inputs[1]);
            }
            else{
                MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + "Drop something ?(drop [item_id_you_have])");
            }
        }
        else if (inputs[0].equals("set")){
            if(inputs.length == 2){
                player.setName(inputs[1]);
            }
            else{
                MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + "Set UserName ?(set [username])");
            }
        }
        else if (inputs[0].equals("my") || inputs[0].equals("myitem")){
            MessageManagement.showToPlayer(player, CommonContent.NOTIFICATION + "\t\b" + player.get_my_item());
        }
		else if (inputs[0].equals("m") || inputs[0].equals("map")){
            if (inputs.length == 1){
                MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + World_MAP.getmap(player.getLocation()));
            }
            else if(inputs.length == 2){
                if(inputs[1].equals("w") || inputs[1].equals("world")){
                    MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + World_MAP.getmap());
                }
                else{
                    MessageManagement.showToPlayer(player,CommonContent.INFORMATION + "\t\b" + "����һ����Ч��ָ�");
                }
            }
            else{
                MessageManagement.showToPlayer(player,CommonContent.INFORMATION + "\t\b" + "����һ����Ч��ָ�");
            }
		}
		else if(inputs[0].equals("who")){
            MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + Player.get_player_list(player));
        }
        else if(inputs[0].equals("world")){
            MessageManagement.showToPlayer(player,CommonContent.NOTIFICATION + "\t\b" + ">>��˵��" + inputs[1]);
		    MessageManagement.showToPlayer(player,CommonContent.WORLD_MESSAGE + "\t\b" + "��������Ϣ��" + inputs[1], Player.online_player_list);
        }
		else {
			MessageManagement.showToPlayer(player,CommonContent.WARNING + "\t\b" + "����һ����Ч��ָ�");
		}
		//MessageManagement.showToPlayer(player, "ʲô��\n");
	}
}
