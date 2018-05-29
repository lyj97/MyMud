package L_MyMud;

public class StaticFunctions {
	public static String getDirection(CommonContent.DIRECTION direction){
		String chinese = "";
		switch(direction){
		case EAST:
			chinese = "东（E）";break;
		case WEST:
			chinese = "西（W）";break;
		case SOUTH:
			chinese = "南（S）";break;
		case NORTH:
			chinese = "北（N）";break;
		case NORTHEAST:
			chinese = "东北（NE）";break;
		case NORTHWEST:
			chinese = "西北（NW）";break;
		case SOUTHEAST:
			chinese = "东南（SE）";break;
		case SOUTHWEST:
			chinese = "西南（SW）";break;
		case UP:
			chinese = "上（U）";break;
		case DOWN:
			chinese = "下（D）";break;
		}
		return chinese;
	}
	public static String getReverseDirection(CommonContent.DIRECTION direction){
		String chinese = "";
		switch(direction){
		case EAST:
			chinese = "西";break;
		case WEST:
			chinese = "东";break;
		case SOUTH:
			chinese = "北";break;
		case NORTH:
			chinese ="南";break;
		case NORTHEAST:
			chinese = "西南";break;
		case NORTHWEST:
			chinese = "东南";break;
		case SOUTHEAST:
			chinese = "西北";break;
		case SOUTHWEST:
			chinese = "东北";break;
		case UP:
			chinese = "下";break;
		case DOWN:
			chinese = "上";break;
		}
		return chinese;
	}
	public static CommonContent.DIRECTION GetReverseDirection(CommonContent.DIRECTION direction){
		CommonContent.DIRECTION rdirection;
		switch(direction){
			case EAST:
				rdirection = CommonContent.DIRECTION.WEST;break;
			case WEST:
				rdirection = CommonContent.DIRECTION.EAST;break;
			case SOUTH:
				rdirection = CommonContent.DIRECTION.NORTH;break;
			case NORTH:
				rdirection = CommonContent.DIRECTION.SOUTH;break;
			case NORTHEAST:
				rdirection = CommonContent.DIRECTION.SOUTHWEST;break;
			case NORTHWEST:
				rdirection = CommonContent.DIRECTION.SOUTHEAST;break;
			case SOUTHEAST:
				rdirection = CommonContent.DIRECTION.NORTHWEST;break;
			case SOUTHWEST:
				rdirection = CommonContent.DIRECTION.NORTHEAST;break;
			case UP:
				rdirection = CommonContent.DIRECTION.DOWN;break;
			case DOWN:
				rdirection = CommonContent.DIRECTION.UP;break;
			default: rdirection = null; break;
		}
		return rdirection;
	}
	public static void load_file(){
        RoomManagement.creatRooms();
        Player.load_player();
        ItemManagement.create_items();
        ItemManagement.load_PLAYER_ITEM_LIST();
        DecorationManagement.set_decoration();
        MovementManagement.create_movements();
        MissionManagement.create_missions();
        NonPlayerCharacterManagement.create_NPC();
    }

    public static String help_message(){
	    String help = "------【HELP】------\t";
        help += "【移动】--n,s,w,e,ne,nw,se,sw\t";
        help += "【查看】--l/look (something)\t";
        help += "【聊天】--c (something)\t";
        help += "【技能】--act (something)\t";
        help += "【查看地图】--m/map\t";
	    return help;
    }
}
