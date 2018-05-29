package L_MyMud;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ItemManagement {

    public static HashMap<String, Item> ITEM_LIST = new HashMap();
    public static HashMap<String, String> PLAYER_ITEM_LIST = new HashMap<>();//�����Ʒ�б��Ѿ������get����Ʒ�б���item_id, Player_id��

    public static void save_PLAYER_ITEM_LIST(){
        File player_item_list = new File("PLAYER_ITEM_LIST.txt");
        if(!player_item_list.exists()){
            try {
                player_item_list.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("����δ�ܴ��������Ʒ�б��ļ���");
            }
        }
        try {
            PrintWriter pwriter = new PrintWriter(new FileWriter(player_item_list));
            for(Map.Entry<String, String> entry : PLAYER_ITEM_LIST.entrySet()){
                pwriter.println(entry.getKey() + " " + entry.getValue());
            }
            pwriter.flush();
            if(pwriter != null){
                pwriter.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("���󣺱��������Ʒ�б��ļ�����");
        }
    }

    public static void load_PLAYER_ITEM_LIST(){
        File player_item_list = new File("PLAYER_ITEM_LIST.txt");
        if(player_item_list.exists()){
            try {
                BufferedReader breader = new BufferedReader(new FileReader(player_item_list));
                String str;
                String items[];
                while((str = breader.readLine()) != null){
                    items = str.split(" ");
                    if(items.length == 2){
                        PLAYER_ITEM_LIST.put(items[0], items[1]);
                        Item temp = ITEM_LIST.get(items[0]);
                        if(temp != null){
                            Player.player_list.get(items[1]).add_item(temp);
                        }
                        else{
                            System.out.println("���󣺲����ڵ���Ʒ��item��" + items[0]);
                        }
                    }
                    else{
                        System.out.println("����items���Ȳ�Ϊ2�� items:" + items.toString());
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                player_item_list.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("����δ�ܴ��������Ʒ�б��ļ���");
            }
        }
        for(Map.Entry<String, Item> entry : ITEM_LIST.entrySet()){
            if(!PLAYER_ITEM_LIST.containsKey(entry.getKey())){
                entry.getValue().owner.room.add_item(entry.getValue());
            }
        }
    }

    public static void create_items(){

        Item test_item1 = new Item();
        test_item1.id = "test_item1";
        test_item1.name = "������Ʒ";
        test_item1.description = "������Ʒ������";
        test_item1.owner = new Owner();
        test_item1.owner.room = RoomManagement.cityMap.get("yangzhou_guangchang");
        ITEM_LIST.put(test_item1.id, test_item1);

        Item test_item2 = new Item();
        test_item2.id = "test_item2";
        test_item2.name = "������Ʒ";
        test_item2.description = "������Ʒ������";
        test_item2.owner = new Owner();
        test_item2.owner.room = RoomManagement.cityMap.get("yangzhou_guangchang");
        ITEM_LIST.put(test_item2.id, test_item2);

        Item test_item3 = new Item();
        test_item3.id = "test_item3";
        test_item3.name = "������Ʒ";
        test_item3.description = "������Ʒ������";
        test_item3.owner = new Owner();
        test_item3.owner.room = RoomManagement.cityMap.get("yangzhou_guangchang");
        ITEM_LIST.put(test_item3.id, test_item3);

    }
}
