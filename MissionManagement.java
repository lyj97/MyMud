package L_MyMud;

import java.util.HashMap;
import java.util.Map;

public class MissionManagement {
    public static HashMap<String, Mission> MISSION_LIST = new HashMap<>();//�����б���׶�����ֻ��ӵ�һ�׶�

    static String getAllMissions(){
        String str = "�������б�\t";
        for(Map.Entry<String, Mission> entry : MISSION_LIST.entrySet()){
            str += entry.getValue().mission_information() + "\t";
        }
        return str;
    }

    public static void create_missions(){
        //��������1
        Mission mission_test1 = new Mission();
        mission_test1.id = "mission_test1";
        mission_test1.name = "��������1";
        mission_test1.description = "��������1������\t��ȥ��������������������µķ����ء���";
        mission_test1.purpose = "yangzhou_guangchang_tree";
        MISSION_LIST.put(mission_test1.id, mission_test1);
        DecorationMissionRelation.add_relation(mission_test1, DecorationManagement.DECORATION_LIST.get("yangzhou_guangchang_tree"));

        //��������2
        Mission mission_test2_3 = new Mission();
        mission_test2_3.id = "mission_test2_3";
        mission_test2_3.name = "��������2";
        mission_test2_3.description = "��������2 �����׶ε�����\t��ȥ�������ݹ㳡����������Ҷ�Ӱɣ��������µķ����أ�";
        mission_test2_3.purpose = "yangzhou_guangchang_leaf";
        DecorationMissionRelation.add_relation(mission_test2_3, DecorationManagement.DECORATION_LIST.get("yangzhou_guangchang_leaf"));

        Mission mission_test2_2 = new Mission();
        mission_test2_2.id = "mission_test2_2";
        mission_test2_2.name = "��������2";
        mission_test2_2.description = "��������2 �ڶ��׶ε�����\t��ȥ�������ݹ㳡�����Ӱɣ��������µķ����أ�";
        mission_test2_2.purpose = "yangzhou_guangchang_sign";
        mission_test2_2.next_stage = mission_test2_3;
        DecorationMissionRelation.add_relation(mission_test2_2, DecorationManagement.DECORATION_LIST.get("yangzhou_guangchang_sign"));

        Mission mission_test2_1 = new Mission();
        mission_test2_1.id = "mission_test2_1";
        mission_test2_1.name = "��������2";
        mission_test2_1.description = "��������2 ��һ�׶ε�����\t��ȥ�������ŵĸ�ʾ�ɣ��������µķ����أ�";
        mission_test2_1.purpose = "yangzhou_beimen_gaoshi";
        mission_test2_1.next_stage = mission_test2_2;
        MISSION_LIST.put(mission_test2_1.id, mission_test2_1);
        DecorationMissionRelation.add_relation(mission_test2_1, DecorationManagement.DECORATION_LIST.get("yangzhou_beimen_gaoshi"));

    }
}
