package L_MyMud;

import java.util.Map;

public class Item {//物品   !!!注意更新Room中的物品列表
    String id;
    String name;
    String description;

    Owner owner;//拥有者，Player或Room

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
        MessageManagement.showToPlayer(player, CommonContent.NOTIFICATION + "\t\b" + "恭喜您获得物品【" + name + "(" + id + ")】。");
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
        MessageManagement.showToPlayer(player, CommonContent.NOTIFICATION + "\t\b" + "物品【" + name + "(" + id + ")】已经被您丢弃了。");
    }
}

class Owner{
    Room room;
    Player player;
}
