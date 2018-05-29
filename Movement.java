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
        //���ض����б�

//        for(Map.Entry<String, Movement> entry : movement_list.entrySet()){
//
//        }

        return list;
    }

    public static void act(Player from, Player to, String name){
        //������
        //��˫������Ϣ�����ض���Ч����
        Movement mov = movement_list.get(name);
        if(mov != null){
            mov.act(from, to);
        }
        else{
            MessageManagement.showToPlayer(from, CommonContent.INFORMATION + "\t\b" + "Ҳ����Գ��������������act [player_id] [movement_id]��");
        }
    }

    public void act(Player from, Player to){
        MessageManagement.showToPlayer(from, CommonContent.INFORMATION + "\t\b" + "������ҡ�" + to.getfullName() +"��" + description);
        MessageManagement.showToPlayer(to, CommonContent.INFORMATION + "\t\b" + "��ҡ�" + from.getfullName() +"��" + reverse_description);
        if(affect != null) {
            from.be_changed(to, affect);
        }
    }

    private static HashMap<String, Movement> get_possible_movement(String name){
        HashMap<String, Movement> movements = new HashMap<>();
        //����name��ȡmovement
        return movements;
    }
}

class Affect{
    //Ч��
    int experience = 0;
    int con = 0;
    int dex = 0;
    int str = 0;
    int wis = 0;
    int hp = 0;
    int nl = 0;
    int jl = 0;
}
