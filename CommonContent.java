package L_MyMud;

public interface CommonContent {
	String CANNOTMOVE = "这个方向没有出路。";
	enum DIRECTION {
		EAST, WEST, SOUTH, NORTH, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST,UP, DOWN 
	};
	int CHARS_PER_LINE = 40;
	int FOUNT_SIZE = 25;
	int FOR_PLAYER_SAFE = 20;
	int NPC_STATEMENT_INTERVAL_TIME = 15500;//NPC小声说话间隔时间
	//int MAX_HISTORY_NUMBER = 20;
	//消息类型
    String UPDATE_USER_INFORMATION_FLAG = "[update_user_information]";
	String UPDATE_MISSION_INFORMATION_FLAG = "[update_mission_information]";
	//信息，提示，消息，NPC消息，错误信息，属性变化消息
    //登录信息，下线信息，动作消息
    String INPUT_INFORMATION = "[input_information]";
	String INFORMATION = "[information]";
	String NOTIFICATION = "[notification]";
	String MESSAGE = "[message]";
	String WORLD_MESSAGE = "[world_message]";
	String NPC_MESSAGE = "[npc_message]";
	String WARNING = "[wrong_information]";
	String VALUE_CHANGED = "[value_changed]";
	String PLAYER_MOVING = "[player_moving]";
}
