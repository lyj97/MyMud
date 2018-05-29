package L_MyMud;

import java.util.HashMap;

public class DecorationMissionRelation {

    static HashMap<Mission, String> DECORATION_MISSION_RELATION_LIST = new HashMap<>();

    static void add_relation(Mission mission, Decoration decoration){

        DECORATION_MISSION_RELATION_LIST.put(mission, decoration.id);

    }
}
