package L_MyMud;

public class Decoration {//房间内物品（仅供Look）
    String id;
    String name;
    String description;

    public String look(){
        return (name + "\t" + description);
    }
}
