package L_MyMud;

import java.util.HashMap;
import java.util.Map;

public class MissionManagement {
    public static HashMap<String, Mission> MISSION_LIST = new HashMap<>();//任务列表，多阶段任务只添加第一阶段

    static String getAllMissions(){
        String str = "【任务列表】\t";
        for(Map.Entry<String, Mission> entry : MISSION_LIST.entrySet()){
            str += entry.getValue().mission_information() + "\t";
        }
        return str;
    }

    public static void create_missions(){
        //测试任务1
        Mission mission_test1 = new Mission();
        mission_test1.id = "mission_test1";
        mission_test1.name = "测试任务1";
        mission_test1.description = "测试任务1的描述\t（去看看老桃树，或许会有新的发现呢。）";
        mission_test1.purpose = "yangzhou_guangchang_tree";
        MISSION_LIST.put(mission_test1.id, mission_test1);
        DecorationMissionRelation.add_relation(mission_test1, DecorationManagement.DECORATION_LIST.get("yangzhou_guangchang_tree"));

        //测试任务2
        Mission mission_test2_3 = new Mission();
        mission_test2_3.id = "mission_test2_3";
        mission_test2_3.name = "测试任务2";
        mission_test2_3.description = "测试任务2 第三阶段的描述\t（去看看扬州广场的老桃树的叶子吧，或许有新的发现呢）";
        mission_test2_3.purpose = "yangzhou_guangchang_leaf";
        DecorationMissionRelation.add_relation(mission_test2_3, DecorationManagement.DECORATION_LIST.get("yangzhou_guangchang_leaf"));

        Mission mission_test2_2 = new Mission();
        mission_test2_2.id = "mission_test2_2";
        mission_test2_2.name = "测试任务2";
        mission_test2_2.description = "测试任务2 第二阶段的描述\t（去看看扬州广场的牌子吧，或许有新的发现呢）";
        mission_test2_2.purpose = "yangzhou_guangchang_sign";
        mission_test2_2.next_stage = mission_test2_3;
        DecorationMissionRelation.add_relation(mission_test2_2, DecorationManagement.DECORATION_LIST.get("yangzhou_guangchang_sign"));

        Mission mission_test2_1 = new Mission();
        mission_test2_1.id = "mission_test2_1";
        mission_test2_1.name = "测试任务2";
        mission_test2_1.description = "测试任务2 第一阶段的描述\t（去看看北门的告示吧，或许有新的发现呢）";
        mission_test2_1.purpose = "yangzhou_beimen_gaoshi";
        mission_test2_1.next_stage = mission_test2_2;
        MISSION_LIST.put(mission_test2_1.id, mission_test2_1);
        DecorationMissionRelation.add_relation(mission_test2_1, DecorationManagement.DECORATION_LIST.get("yangzhou_beimen_gaoshi"));

    }
}
