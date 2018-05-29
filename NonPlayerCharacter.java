package L_MyMud;

import java.util.HashMap;

public class NonPlayerCharacter {

    String id;
    String name;
    HashMap<String, String> keyWord;
    String response;
    String default_response;
    String pet_phrase;
    Room location;
    Thread thread = new SaySomething();

    public NonPlayerCharacter(){
        keyWord = new HashMap<>();
    }

    class SaySomething extends Thread {
        @Override
        public void run() {
            HashMap list;
            if(CommonContent.NPC_AUTOMATICALLY_STATEMENT) {
                while (true) {
                    list = location.getPlayerList();
                    if (!list.isEmpty()) {
                        try {
                            this.sleep(CommonContent.NPC_STATEMENT_INTERVAL_TIME);
                            MessageManagement.showToPlayer(null, CommonContent.NPC_MESSAGE + "\t\b" + "(NPC)[" + get_full_name() + "]：" + pet_phrase, list);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("NPC【" + get_full_name() + "】消息线程异常退出！");
                            break;
                        }
                    } else {
                        try {
                            this.sleep(2 * CommonContent.NPC_STATEMENT_INTERVAL_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.out.println("NPC【" + get_full_name() + "】消息线程异常退出！");
                            break;
                        }
                    }
                }
            }
            else{
                list = location.getPlayerList();
                if (!list.isEmpty()) {
                    try {
                        this.sleep(CommonContent.NPC_STATEMENT_INTERVAL_TIME);
                        MessageManagement.showToPlayer(null, CommonContent.NPC_MESSAGE + "\t\b" + "(NPC)[" + get_full_name() + "]：" + pet_phrase, list);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("NPC【" + get_full_name() + "】消息线程异常退出！");
                    }
                }
            }
        }
    }

    void chat(Player player, String message){
        if(keyWord.containsKey(message)){
            MessageManagement.showToPlayer(player, CommonContent.MESSAGE + "\t\b" + keyWord.get(message));
        }
        else{
            MessageManagement.showToPlayer(player, CommonContent.MESSAGE + "\t\b" + default_response);
        }
    }

    public String get_full_name(){
        return name + "(" + id + ")";
    }
}
