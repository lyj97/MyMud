package L_MyMud;

import java.util.Map;

public class Item {//��Ʒ   !!!ע�����Room�е���Ʒ�б�
    String id;
    String name;
    String description;

    Owner owner;//ӵ���ߣ�Player��Room

    public static void check_player_item_list(Player player){
        if(ItemManagement.PLAYER_ITEM_LIST.containsValue(player.getId())){
            for(Map.Entry<String, Item> entry : ItemManagement.ITEM_LIST.entrySet()){
                if(entry.getKey().equals(player.getId())){
                    entry.getValue().owner.player = player;
                }
            }
        }
    }

    public void get(Player player){
        if(owner == null){
            owner = new Owner();
        }
        owner.player = player;
        player.add_item(this);
        ItemManagement.PLAYER_ITEM_LIST.put(id, player.getId());
        RoomManagement.cityMap.get(player.getLocation()).remove_item(this);
        ItemManagement.save_PLAYER_ITEM_LIST();
        MessageManagement.showToPlayer(player, CommonContent.NOTIFICATION + "\t\b" + "��ϲ�������Ʒ��" + name + "(" + id + ")����");
    }

    public void drop(Player player){
        if(owner == null){
            owner = new Owner();
        }
        owner.room = RoomManagement.cityMap.get(player.getLocation());
        player.remove_item(this);
        owner.player = null;
        ItemManagement.PLAYER_ITEM_LIST.remove(id);
        owner.room.add_item(this);
        ItemManagement.save_PLAYER_ITEM_LIST();
        MessageManagement.showToPlayer(player, CommonContent.NOTIFICATION + "\t\b" + "��Ʒ��" + name + "(" + id + ")���Ѿ����������ˡ�");
    }
}

class Owner{
    Room room;
    Player player;
}
