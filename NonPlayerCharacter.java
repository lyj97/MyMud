package L_MyMud;

import java.util.HashMap;

public class NonPlayerCharacter {

    String id;
    String name;
    String keyWord;
    String response;
    String default_response;
    String pet_phrase;
    Room location;
    Thread thread = new SaySomething();

    class SaySomething extends Thread{
        @Override
        public void run(){
            HashMap list;
            while (true) {
                list = location.getPlayerList();
                if (!list.isEmpty()) {
                    MessageManagement.showToPlayer(null, CommonContent.NPC_MESSAGE + "\t\b" + "(NPC)[" + get_full_name() + "]：" + pet_phrase, location.getPlayerList());
                    try {
                        this.sleep(CommonContent.NPC_STATEMENT_INTERVAL_TIME);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("NPC【" + get_full_name() + "】消息线程退出！");
                        break;
                    }
                }
                else{
                    try {
                        this.sleep(2 * CommonContent.NPC_STATEMENT_INTERVAL_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("NPC【" + get_full_name() + "】消息线程退出！");
                        break;
                    }
                }
            }
        }
    }

    void chat(Player player, String message){
        if(message.equals(keyWord)){
            MessageManagement.showToPlayer(player, CommonContent.MESSAGE + "\t\b" + response);
        }
        else{
            MessageManagement.showToPlayer(player, CommonContent.MESSAGE + "\t\b" + default_response);
        }
    }

    public String get_full_name(){
        return name + "(" + id + ")";
    }
}
