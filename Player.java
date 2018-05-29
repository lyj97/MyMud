package L_MyMud;

import java.io.*;
import java.util.*;

public class Player {

	private int experience;//经验
	private int con;//体力
	private int dex;//灵巧（增加命中率，微量减少收到的物理损伤）
	private int str;//力量
	private int wis;//智慧？
	private int hp, max_hp;//血
	private int nl, max_nl;//耐力？
	private int jl, max_jl;//精力？（蓝？）
	private String id;
	private String username;
	private String password;//密码
	private String party;//帮派
	private String location;//房间ID
	
	private Boolean normal_state;//正常？

	public HashMap<String, Mission> mission_list = new HashMap<>();//玩家任务列表

    public void flush_mission(){
        String mission_information = "";
        if(!mission_list.isEmpty()) {
            for (Map.Entry<String, Mission> entry : mission_list.entrySet()) {
                mission_information += entry.getValue().mission_information() + "\t";
            }
        }
        MessageManagement.showToPlayer(this, CommonContent.UPDATE_MISSION_INFORMATION_FLAG + "\t" + mission_information);
    }

    public void get_mission(Mission mission){
        Mission test = mission_list.get(id);
        if(test == null) {
            add_mission(mission);
            MessageManagement.showToPlayer(this, CommonContent.NOTIFICATION + "\t\b" + "接受任务[" + mission.name + "]！");
            flush_mission();
        }
        else{
            MessageManagement.showToPlayer(this, CommonContent.NOTIFICATION + "\t\b" + "您似乎应该看一下您的任务列表了。");
        }
    }

    public void add_mission(Mission mission){
        mission_list.put(mission.id, mission);
    }

    public void achieve_mission(String mission_id){
        mission_list.remove(mission_id);
    }

	private HashMap<String, Item> item_list = new HashMap<>();//玩家物品列表

    public String get_my_item(){
        String temp = "您已获得的物品:\t";
        for(Map.Entry<String, Item> entry : item_list.entrySet()){
            temp += entry.getValue().name + "(" + entry.getKey() + ")\t";
        }
        return temp;
    }

    public void get_item(String item_id){
        Item item = RoomManagement.cityMap.get(getLocation()).itemList.get(item_id);
        if(item == null){
            MessageManagement.showToPlayer(this, CommonContent.INFORMATION + "\t\b" + "不如试一下其他命令？（get item [item_id_in_the_room]）");
        }
        else{
            item.get(this);
        }
    }

    public void drop_item(String item_id){
        Item item = item_list.get(item_id);
        if(item == null){
            MessageManagement.showToPlayer(this, CommonContent.INFORMATION + "\t\b" + "不如试一下其他命令？（drop item [item_id_you_have]）");
        }
        else{
            item.drop(this);
        }
    }

    public void add_item(Item item){
        if(item_list.containsKey(item.id)){
            System.out.println("错误：尝试为玩家" + getfullName() + "添加已存在的物品 " + item.id +"(" + item.name + ")。");
        }
        else{
            item_list.put(item.id, item);
        }
    }

    public void remove_item(Item item){
        if(!item_list.containsKey(item.id)){
            System.out.println("错误：尝试移除玩家" + getfullName() + "未获得的的物品 " + item.id +"(" + item.name + ")。");
        }
        else{
            item_list.remove(item.id);
        }
    }

	public static HashMap<String, Player> player_list = new HashMap<>();
	public static HashMap<String, Player> online_player_list = new HashMap<>();//在线玩家列表

	public static String get_player_list(Player player){
	    String list = "当前世界在线玩家：\t";
	    for(Map.Entry<String, Player> entry : online_player_list.entrySet()){
	        if(!(entry.getKey().equals(player.id))){
	            list += (entry.getValue().getfullName() + "\t");
            }
            else{
	            list += (entry.getValue().getfullName() + "【我】\t");
            }
        }
        return list;
    }

    public void be_changed(Player player, Affect affect){

	    boolean dangerous = false;

        String affect_information_for_suffer ="";
        player.experience += affect.experience;
        if(affect.experience > 0){
            affect_information_for_suffer += ("experience +" + affect.experience + "\t");
        }
        else if(affect.experience < 0){
            affect_information_for_suffer += ("experience " + affect.experience + "\t");
        }
        player.con += affect.con;
        if(affect.con > 0){
            affect_information_for_suffer += ("con +" + affect.con + "\t");
        }
        else if(affect.con < 0){
            affect_information_for_suffer += ("con " + affect.con + "\t");
        }
        player.dex += affect.dex;
        if(affect.dex > 0){
            affect_information_for_suffer += ("dex +" + affect.dex + "\t");
        }
        else if(affect.dex < 0){
            affect_information_for_suffer += ("dex " + affect.dex + "\t");
        }
        player.str += affect.str;
        if(affect.str > 0){
            affect_information_for_suffer += ("str +" + affect.str + "\t");
        }
        else if(affect.str < 0){
            affect_information_for_suffer += ("str " + affect.str + "\t");
        }
        player.wis += affect.wis;
        if(affect.wis > 0){
            affect_information_for_suffer += ("wis +" + affect.wis + "\t");
        }
        else if(affect.wis < 0){
            affect_information_for_suffer += ("wis " + affect.wis + "\t");
        }
        if(affect.hp > 0){
            if(player.hp < player.max_hp) {
                player.hp += affect.hp;
                affect_information_for_suffer += ("hp +" + affect.hp + "\t");
                if(player.hp > player.max_hp){
                    player.hp = player.max_hp;
                }
            }
            else{
                MessageManagement.showToPlayer(player, CommonContent.INFORMATION + "\t\b" + "为何不考虑一下去献血？\t");
            }
        }
        else if(affect.hp < 0){
            player.hp += affect.hp;
            affect_information_for_suffer += ("hp " + affect.hp + "\t");
            if(player.hp < CommonContent.FOR_PLAYER_SAFE){
                dangerous = true;
            }
        }
        if(affect.nl > 0){
            if(player.nl < player.max_nl) {
                player.nl += affect.nl;
                affect_information_for_suffer += ("nl +" + affect.nl + "\t");
                if(player.nl > player.max_nl){
                    player.nl = player.max_nl;
                }
            }
            else{
                //MessageManagement.showToPlayer(player, "为何不考虑一下去献nl？\t");
            }
        }
        else if(affect.nl < 0){
            player.nl += affect.nl;
            affect_information_for_suffer += ("nl " + affect.nl + "\t");
            if(player.nl < CommonContent.FOR_PLAYER_SAFE){
                dangerous = true;
            }
        }
        if(affect.jl > 0){
            if(player.jl < player.max_jl) {
                player.jl += affect.jl;
                affect_information_for_suffer += ("jl +" + affect.jl + "\t");
                if(player.jl > player.max_jl){
                    player.jl = player.max_jl;
                }
            }
            else{
                //MessageManagement.showToPlayer(player, "为何不考虑一下去献jl？\t");
            }
        }
        else if(affect.jl < 0){
            player.jl += affect.jl;
            affect_information_for_suffer += ("jl " + affect.jl + "\t");
            if(player.jl < CommonContent.FOR_PLAYER_SAFE){
                dangerous = true;
            }
        }
        MessageManagement.showToPlayer(player, player.get_information());
        if(this != player) {
            MessageManagement.showToPlayer(this, CommonContent.INFORMATION + "\t\b" + "对方似乎发生了一些变化：\t" + affect_information_for_suffer);
        }
        MessageManagement.showToPlayer(player, CommonContent.VALUE_CHANGED + "\t\b" + "您似乎发生了如下变化：\t" + affect_information_for_suffer);
        if(dangerous){
            player.god_help();
        }
	}

	void god_help(){
        MessageManagement.showToPlayer(this, CommonContent.VALUE_CHANGED + "\t\b" + "您似乎感觉身边一阵寒风“呼~”地吹过！！！\t您似乎得到了神秘高手的帮助：\t");
	    Affect affect = new Affect();
	    if(hp < CommonContent.FOR_PLAYER_SAFE){
	        affect.hp = (max_hp - CommonContent.FOR_PLAYER_SAFE) / 4;
        }
        if(nl < CommonContent.FOR_PLAYER_SAFE){
            affect.nl = (max_nl - CommonContent.FOR_PLAYER_SAFE) / 4;
        }
        if(jl < CommonContent.FOR_PLAYER_SAFE){
            affect.jl = (max_jl - CommonContent.FOR_PLAYER_SAFE) / 4;
        }
        be_changed(this, affect);
    }

	static void load_player() {
		String filename = "Player.txt";
		File file = new File(filename);
		if(file.exists()) {
			BufferedInputStream bis;
			try {
				bis = new BufferedInputStream(new FileInputStream(file));
				InputStreamReader fr = new InputStreamReader(bis);
				Scanner sc = new Scanner(fr);
				String temp;
				if(sc.hasNextLine()) {
					temp = sc.nextLine();
					while(temp.length() != 0) {
						load_player(temp);
						if(sc.hasNextLine()) {
							temp = sc.nextLine();
						}
						else {
							break;
						}
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("创建玩家ID文件失败！");
				e.printStackTrace();
			}
		}
	}
	
	static void load_player(String player_id) {
		String filename = "Player\\"+player_id+".txt";
		File file = new File(filename);
		if(file.exists()) {
			Player player = new Player(player_id,"");
			if(player.normal_state) {
				player_list.put(player.id, player);
			}
			else {
				System.out.println("Load player problem: player_id: " + player_id +" File NOT exist.");
			}
		}
		else {
			System.out.println("Load player problem: player_id: " + player_id +" File NOT exist.");
		}
	}
	
	static void save_player() {
		String filename = "Player.txt";
		File file = new File(filename);
		if(file.exists()) {
			try {
                System.out.println("保存Player信息总表.");
				PrintWriter pwriter = new PrintWriter(file);
				Iterator<String> iterator = player_list.keySet().iterator();
				while(iterator.hasNext()) {
					pwriter.println(iterator.next().toString());
					//System.out.println("保存Player信息总表： 当前iterator：" + iterator.toString());
					pwriter.flush();
				}
				if(pwriter != null) {
					pwriter.close();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Player信息保存异常！");
			}
			
		}
		else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("创建玩家ID文件失败！");
			}
		}
	}
	
	public Player() {
		// create player default value
	}
	
	public Player(String player_id, String username) {
		String filename = "Player\\"+player_id+".txt";
		File file = new File(filename);
		if(file.exists()) {
			try {
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				InputStreamReader fr = new InputStreamReader(bis);
				Scanner sc = new Scanner(fr);
				experience = sc.nextInt();
				con = sc.nextInt();
				dex = sc.nextInt();
				str = sc.nextInt();
				wis = sc.nextInt();
				hp = sc.nextInt();
				max_hp = sc.nextInt();
				nl = sc.nextInt();
				max_nl = sc.nextInt();
				jl = sc.nextInt();
				max_jl = sc.nextInt();
				while((id = sc.nextLine()).length() == 0);
				if(!(player_id.equals(id))) {
					System.out.println("用户ID初始化出错！");
					normal_state = false;
					sc.close();
					return;
				}
				this.username = sc.nextLine();
				password = sc.nextLine();
				party = sc.nextLine();
				location = sc.nextLine();
				sc.close();
				normal_state = true;
				player_list.put(this.id,this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("用户初始化异常！");
				normal_state = false;
			}
		}
		else {
			try {
				this.setDefaultValue();
				this.id = player_id;
				this.username = username;
				file.createNewFile();
                player_list.put(this.id,this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("用户文件创建失败！");
				this.normal_state = false;
			}
		}
	}
	
	public void set_player_by_file(String player_id, String username) {
		String filename = "Player\\"+player_id+".txt";
		File file = new File(filename);
		if(file.exists()) {
			try {
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				InputStreamReader fr = new InputStreamReader(bis);
				Scanner sc = new Scanner(fr);
				experience = sc.nextInt();
				con = sc.nextInt();
				dex = sc.nextInt();
				str = sc.nextInt();
				wis = sc.nextInt();
				hp = sc.nextInt();
				max_hp = sc.nextInt();
				nl = sc.nextInt();
				max_nl = sc.nextInt();
				jl = sc.nextInt();
				max_jl = sc.nextInt();
				while((id = sc.nextLine()).length() == 0);
				if(!(player_id.equals(id))) {
					System.out.println("用户ID初始化出错！");
					normal_state = false;
					sc.close();
					return;
				}
				username = sc.nextLine();
				password = sc.nextLine();
				party = sc.nextLine();
				location = sc.nextLine();
				sc.close();
				normal_state = true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("用户初始化异常！");
				normal_state = false;
			}
		}
		else {
			try {
				this.setDefaultValue();
				this.id = player_id;
				this.username = username;
				file.createNewFile();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("用户文件创建失败！");
				this.normal_state = false;
			}
		}
	}
	
	public Player(String player_id, String username, String password) {//新player
		String filename = "Player\\"+player_id+".txt";
		File file = new File(filename);
		if(file.exists()) {
			System.out.println("错误路径：在判定用户不存在的情况下，用户信息文件存在！");
			this.set_player_by_file(player_id, username);
            player_list.put(this.id,this);
		}
		else {
			try {
				this.setDefaultValue();
				this.id = player_id;
				this.username = username;
				this.password = password;
				file.createNewFile();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("用户文件创建失败！");
				this.normal_state = false;
			}
		}
	}
	
	public Player(int experience,int con,int dex,int str,int wis,int hp,int max_hp,int nl,int max_nl,int jl,int max_jl,String id,String username,String party,String location){
		this.experience = experience;
		this.con = con;
		this.dex = dex;
		this.str = str;
		this.wis = wis;
		this.hp = hp;
		this.max_hp = max_hp;
		this.nl = nl;
		this.max_nl = max_nl;
		this.jl = jl;
		this.max_jl = jl;
		this.id = id;
		this.username = username;
		this.party = party;
		this.location = location;
	}
	
	public void setDefaultValue() {
		this.experience = 0;
		this.con = 50;
		this.dex = 50;
		this.str = 50;
		this.wis = 50;
		this.hp = 100;
		this.max_hp = 100;
		this.nl = 100;
		this.max_nl = 100;
		this.jl = 100;
		this.max_jl = 100;
		this.id = "";
		this.username = "";
		this.password = "";
		this.party = "";
		this.location = "yangzhou_guangchang";
	}
	
	public void save() {
		Mission.save_mission(this);
		String filename = "Player\\"+this.id+".txt";
		File file = new File(filename);
		if(file.exists()) {
			try {
				PrintWriter pwriter = new PrintWriter(file);
				pwriter.println(this.experience);
				pwriter.println(this.con);
				pwriter.println(this.dex);
				pwriter.println(this.str);
				pwriter.println(this.wis);
				pwriter.println(this.hp);
				pwriter.println(this.max_hp);
				pwriter.println(this.nl);
				pwriter.println(this.max_nl);
				pwriter.println(this.jl);
				pwriter.println(this.max_jl);
				pwriter.println(this.id);
				pwriter.println(this.username);
				pwriter.println(this.password);
				pwriter.println(this.party);
				pwriter.println(this.location);
				if(pwriter != null) {
					pwriter.close();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("用户信息保存失败！（文件存在）");
			}
		}
		else {
			try {
				file.createNewFile();
				PrintWriter pwriter = new PrintWriter(file);
				pwriter.println(this.experience);
				pwriter.println(this.con);
				pwriter.println(this.dex);
				pwriter.println(this.str);
				pwriter.println(this.wis);
				pwriter.println(this.hp);
				pwriter.println(this.max_hp);
				pwriter.println(this.nl);
				pwriter.println(this.max_nl);
				pwriter.println(this.jl);
				pwriter.println(this.max_jl);
				pwriter.println(this.id);
				pwriter.println(this.username);
				pwriter.println(this.password);
				pwriter.println(this.party);
				pwriter.println(this.location);
				if(pwriter != null) {
					pwriter.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("用户信息保存失败！（文件不存在）");
			}
		}
		save_player();
	}
	
	public void move(CommonContent.DIRECTION direction) {
		(RoomManagement.cityMap.get(this.location)).enter(this, direction);
	}
	
	public void look(String something){
		if(something.equals("")) {
            //查看当前房间;
            Room room = RoomManagement.cityMap.get(this.getLocation());
            if (room != null) {
                MessageManagement.showToPlayer(this, CommonContent.INFORMATION + "\t\b" + room.getRoomLooking(this));
            }
            else {
                System.out.println("错误：player.getLocation()未返回结果！（player = " + this.getId() + "location = " + this.getLocation() + " ）");
                MessageManagement.showToPlayer(this, CommonContent.INFORMATION + "\t\b" + "获取信息失败，请重试！");
            }
        }
		else {
		    //查看其它物品
            Decoration thing_to_look = DecorationManagement.DECORATION_LIST.get(RoomManagement.cityMap.get(this.getLocation()).getRoomId() + "_" + something);
            if(thing_to_look == null){
                MessageManagement.showToPlayer(this, CommonContent.INFORMATION + "\t\b" + "不如试一下其他命令？（look [something_in_the_room]）");
            }
            else{
                MessageManagement.showToPlayer(this, CommonContent.INFORMATION + "\t\b" + thing_to_look.look());
                if(DecorationMissionRelation.DECORATION_MISSION_RELATION_LIST.containsValue(thing_to_look.id)){
                    Mission.change_state_by_item(this, thing_to_look);
                }
            }
		}
	}
	
	public void quit(){
		//告诉房间退出了，释放资源
		RoomManagement.cityMap.get(this.location).exit(this);
		MessageManagement.removePlayerChannels(this.getId());
        online_player_list.remove(id);
		//save添加在这里
	}
	
	public void setLocation(String location){
		this.location = location;
	}
	
	public String getLocation(){
		return this.location;
	}
	
	public String getId(){
		return id;
	}
	
	public String getpassword() {
		return this.password;
	}
	
	public void setId(String id){
		
	}
	
	public String getName(){
		return this.username;
	}
	
	public String getfullName(){
		return (this.username + "(" + this.id + ")");
	}
	public void setName(String username){
		this.username = username;
	}

	public String get_information(){
	    String information = new String(CommonContent.UPDATE_USER_INFORMATION_FLAG + "\t");
	    information += ("ID:" + id + "\t");
	    information += ("USERNAME:" + username + "\t");
	    information += ("EXPERIENCE:" + (new Integer(experience)).toString() + "\t");
        information += ("CON:" + (new Integer(con)).toString() + "\t");
        information += ("DEX:" + (new Integer(dex)).toString() + "\t");
        information += ("STR:" + (new Integer(str)).toString() + "\t");
        information += ("WIS:" + (new Integer(wis)).toString() + "\t");
        information += ("HP:" + (new Integer(hp)).toString() + "\t");
        information += ("MAX_HP:" + (new Integer(max_hp)).toString() + "\t");
        information += ("NL:" + (new Integer(nl)).toString() + "\t");
        information += ("MAX_NL:" + (new Integer(max_nl)).toString() + "\t");
        information += ("JL:" + (new Integer(jl)).toString() + "\t");
        information += ("MAX_JL:" + (new Integer(max_jl)).toString() + "\t");
        information += ("PARTY:" + party + "\t");
        information += ("LOCATION:" + RoomManagement.cityMap.get(location).getRoomName() + "\t");
        return information;
	}
}
