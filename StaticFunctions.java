package L_MyMud;

public class StaticFunctions {
	public static String getDirection(CommonContent.DIRECTION direction){
		String chinese = "";
		switch(direction){
		case EAST:
			chinese = "����E��";break;
		case WEST:
			chinese = "����W��";break;
		case SOUTH:
			chinese = "�ϣ�S��";break;
		case NORTH:
			chinese = "����N��";break;
		case NORTHEAST:
			chinese = "������NE��";break;
		case NORTHWEST:
			chinese = "������NW��";break;
		case SOUTHEAST:
			chinese = "���ϣ�SE��";break;
		case SOUTHWEST:
			chinese = "���ϣ�SW��";break;
		case UP:
			chinese = "�ϣ�U��";break;
		case DOWN:
			chinese = "�£�D��";break;
		}
		return chinese;
	}
	public static String getReverseDirection(CommonContent.DIRECTION direction){
		String chinese = "";
		switch(direction){
		case EAST:
			chinese = "��";break;
		case WEST:
			chinese = "��";break;
		case SOUTH:
			chinese = "��";break;
		case NORTH:
			chinese ="��";break;
		case NORTHEAST:
			chinese = "����";break;
		case NORTHWEST:
			chinese = "����";break;
		case SOUTHEAST:
			chinese = "����";break;
		case SOUTHWEST:
			chinese = "����";break;
		case UP:
			chinese = "��";break;
		case DOWN:
			chinese = "��";break;
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
	    String help = "------��HELP��------\t";
        help += "���ƶ���--n,s,w,e,ne,nw,se,sw\t";
        help += "���鿴��--l/look (something)\t";
        help += "�����졿--c (something)\t";
        help += "�����ܡ�--act (something)\t";
        help += "���鿴��ͼ��--m/map\t";
	    return help;
    }
}
