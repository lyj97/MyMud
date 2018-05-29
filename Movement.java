package L_MyMud;

import java.util.HashMap;
import java.util.Map;

public class Movement {

    String id;
    String name;
    String description;
    String reverse_description;
    Affect affect;

    public static HashMap<String, Movement> movement_list = new HashMap<>();

    public String get_movement_list(){
        String list = "Movement:\t";
        //返回动作列表

//        for(Map.Entry<String, Movement> entry : movement_list.entrySet()){
//
//        }

        return list;
    }

    public static void act(Player from, Player to, String name){
        //做动作
        //给双方发消息，返回动作效果。
        Movement mov = movement_list.get(name);
        if(mov != null){
            mov.act(from, to);
        }
        else{
            MessageManagement.showToPlayer(from, CommonContent.INFORMATION + "\t\b" + "也许可以尝试其他的命令？（act [player_id] [movement_id]）");
        }
    }

    public void act(Player from, Player to){
        MessageManagement.showToPlayer(from, CommonContent.INFORMATION + "\t\b" + "您对玩家【" + to.getfullName() +"】" + description);
        MessageManagement.showToPlayer(to, CommonContent.INFORMATION + "\t\b" + "玩家【" + from.getfullName() +"】" + reverse_description);
        if(affect != null) {
            from.be_changed(to, affect);
        }
    }

    private static HashMap<String, Movement> get_possible_movement(String name){
        HashMap<String, Movement> movements = new HashMap<>();
        //根据name获取movement
        return movements;
    }
}

class Affect{
    //效果
    int experience = 0;
    int con = 0;
    int dex = 0;
    int str = 0;
    int wis = 0;
    int hp = 0;
    int nl = 0;
    int jl = 0;
}
