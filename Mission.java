package L_MyMud;

import java.util.*;
import java.io.*;

public class Mission {//任务
    String id;
    String name;
    String description;
    String purpose;

    Mission next_stage = null;//下一阶段

    static void load_mission(Player player){
        File file = new File("Missions\\" + player.getId() +".txt");
        if(file.exists()){
            try {
                BufferedReader bfreader = new BufferedReader(new FileReader(file));
                String str;
                while((str = bfreader.readLine()) != null){
                    if(str.length() > 0) {
                        if (MissionManagement.MISSION_LIST.containsKey(str)) {
                            player.add_mission(MissionManagement.MISSION_LIST.get(str));
                        } else {
                            System.out.println("错误：不存在的任务：mission:" + str);
                        }
                    }
                }
                player.flush_mission();
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("错误：读取玩家任务信息文件出错！Player：" + player.getfullName());
            }
        }
    }

    static void save_mission(Player player) {
        if (!player.mission_list.isEmpty()) {
            File file = new File("Missions\\" + player.getId() +".txt");
            if(!file.exists()){
                try {
                    file.createNewFile();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("错误：创建玩家任务信息文件出错！Player：" + player.getfullName());
                }
            }
            try {
                PrintWriter pwriter = new PrintWriter(new FileWriter(file));
                for(Map.Entry<String, Mission> entry : player.mission_list.entrySet()){
                    pwriter.println(entry.getKey());
                }
                pwriter.flush();
                if(pwriter != null){
                    pwriter.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("错误：保存玩家任务信息文件出错！Player：" + player.getfullName());
            }

        }
        else{
            File file = new File("Missions\\" + player.getId() +".txt");
            if(file.exists()){
                try {
                    PrintWriter pwriter = new PrintWriter(new FileWriter(file));
                    pwriter.println();
                    pwriter.flush();
                    if(pwriter != null){
                        pwriter.close();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("错误：保存玩家任务信息文件出错！Player：" + player.getfullName());
                }
            }
        }
    }

    static void change_state_by_item(Player player, Decoration decoration) {//对Item的动作导致的任务状态改变
        for(Map.Entry<String, Mission> entry : player.mission_list.entrySet()) {
            if (entry.getValue().purpose.equals(decoration.id)) {
                if (entry.getValue().next_stage == null) {
                    MessageManagement.showToPlayer(player, CommonContent.NOTIFICATION + "\t\b" + "恭喜完成任务[" + entry.getValue().name + "]！");
                    player.achieve_mission(entry.getValue().id);
                }
                else {
                    MessageManagement.showToPlayer(player, CommonContent.NOTIFICATION + "\t\b" + "恭喜达成任务[" + entry.getValue().name + "]的本阶段目标！\t【下一阶段】：\t" + entry.getValue().next_stage.mission_information());
                    player.achieve_mission(entry.getValue().id);
                    player.add_mission(entry.getValue().next_stage);
                }
                String mission_information = "";
                if(!player.mission_list.isEmpty()) {
                    for (Map.Entry<String, Mission> entry1 : player.mission_list.entrySet()) {
                        mission_information += entry1.getValue().mission_information() + "\t";
                    }
                }
                MessageManagement.showToPlayer(player, CommonContent.UPDATE_MISSION_INFORMATION_FLAG + "\t" + mission_information);
            }
        }
    }

    String mission_information(){
        return "【任务[" + name + "]】\t" + description;
    }
}
