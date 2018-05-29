package L_MyMud;

public class NonPlayerCharacterManagement {

    static void create_NPC(){
        NonPlayerCharacter mission_npc = new NonPlayerCharacter();
        mission_npc.id = "mission_npc";
        mission_npc.name = "��������";
        mission_npc.location = RoomManagement.cityMap.get("yangzhou_guangchang");
        mission_npc.pet_phrase = "ֻҪ����ѡ�úã�����֮��˭���ţ�";
        mission_npc.keyWord = "mission";
        mission_npc.default_response = "Why not ��ask " + mission_npc.id + " about " + mission_npc.keyWord + " ��?";
        mission_npc.response = MissionManagement.getAllMissions() + "\t��������get mission [mission_id]\t";
        mission_npc.location.NPCList.put(mission_npc.id,mission_npc);


//        NonPlayerCharacter help_npc = new NonPlayerCharacter();
//        help_npc.id = "help_npc";
//        help_npc.name = "��������";
//        help_npc.location = RoomManagement.cityMap.get("yangzhou_guangchang");
//        help_npc.pet_phrase = "��Ҫ�ڽ�������ʧ����...";
//        help_npc.keyWord = "help";
//        help_npc.default_response = "Why not ��ask " + help_npc.id + " about " + help_npc.keyWord + " ��?";
//        help_npc.response = StaticFunctions.help_message();
//        help_npc.location.NPCList.put(help_npc.id,help_npc);

    }
}
