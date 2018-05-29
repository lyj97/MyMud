package L_MyMud;

import java.util.HashMap;

public class DecorationManagement {

    public static HashMap<String, Decoration> DECORATION_LIST = new HashMap<>();

    public static void set_decoration() {

        //扬州广场
        Decoration decoration_yangzhoou_guangchang_tree = new Decoration();
        decoration_yangzhoou_guangchang_tree.id = "yangzhou_guangchang_tree";
        decoration_yangzhoou_guangchang_tree.name = "老桃树";
        decoration_yangzhoou_guangchang_tree.description = "扬州广场的老桃树";
        DECORATION_LIST.put(decoration_yangzhoou_guangchang_tree.id, decoration_yangzhoou_guangchang_tree);

        Decoration decoration_yangzhoou_guangchang_leaf = new Decoration();
        decoration_yangzhoou_guangchang_leaf.id = "yangzhou_guangchang_leaf";
        decoration_yangzhoou_guangchang_leaf.name = "树叶";
        decoration_yangzhoou_guangchang_leaf.description = "扬州广场的老桃树的树叶";
        DECORATION_LIST.put(decoration_yangzhoou_guangchang_leaf.id, decoration_yangzhoou_guangchang_leaf);

        Decoration decoration_yangzhoou_guangchang_sign = new Decoration();
        decoration_yangzhoou_guangchang_sign.id = "yangzhou_guangchang_sign";
        decoration_yangzhoou_guangchang_sign.name = "牌子";
        decoration_yangzhoou_guangchang_sign.description = "扬州广场的牌子";
        DECORATION_LIST.put(decoration_yangzhoou_guangchang_sign.id, decoration_yangzhoou_guangchang_sign);

        //北门
        Decoration decoration_yangzhoou_beimen_gaoshi = new Decoration();
        decoration_yangzhoou_beimen_gaoshi.id = "yangzhou_beimen_gaoshi";
        decoration_yangzhoou_beimen_gaoshi.name = "告示";
        decoration_yangzhoou_beimen_gaoshi.description = "北门的告示";
        DECORATION_LIST.put(decoration_yangzhoou_beimen_gaoshi.id, decoration_yangzhoou_beimen_gaoshi);

        //宝昌客栈
        Decoration decoration_yangzhou_kezhan_zhaopai = new Decoration();
        decoration_yangzhou_kezhan_zhaopai.id = "yangzhou_kezhan_zhaopai";
        decoration_yangzhou_kezhan_zhaopai.name = "招牌";
        decoration_yangzhou_kezhan_zhaopai.description = "宝昌客栈的招牌";
        DECORATION_LIST.put(decoration_yangzhou_kezhan_zhaopai.id, decoration_yangzhou_kezhan_zhaopai);

        Decoration decoration_yangzhou_kezhan_gaoshi = new Decoration();
        decoration_yangzhou_kezhan_gaoshi.id = "yangzhou_kezhan_gaoshi";
        decoration_yangzhou_kezhan_gaoshi.name = "告示";
        decoration_yangzhou_kezhan_gaoshi.description = "宝昌客栈的告示";
        DECORATION_LIST.put(decoration_yangzhou_kezhan_gaoshi.id, decoration_yangzhou_kezhan_gaoshi);

        //当铺
        Decoration decoration_yangzhou_dangpu_paizi = new Decoration();
        decoration_yangzhou_dangpu_paizi.id = "yangzhou_dangpu_paizi";
        decoration_yangzhou_dangpu_paizi.name = "牌子";
        decoration_yangzhou_dangpu_paizi.description = "当铺的牌子";
        DECORATION_LIST.put(decoration_yangzhou_dangpu_paizi.id, decoration_yangzhou_dangpu_paizi);

        //南门
        Decoration decoration_yangzhou_nanmen_gaoshi = new Decoration();
        decoration_yangzhou_nanmen_gaoshi.id = "yangzhou_nanmen_gaoshi";
        decoration_yangzhou_nanmen_gaoshi.name = "告示";
        decoration_yangzhou_nanmen_gaoshi.description = "南门的告示";
        DECORATION_LIST.put(decoration_yangzhou_nanmen_gaoshi.id, decoration_yangzhou_nanmen_gaoshi);

        //兵营
        Decoration decoration_yangzhou_bingying_window = new Decoration();
        decoration_yangzhou_bingying_window.id = "yangzhou_bingying_window";
        decoration_yangzhou_bingying_window.name = "小窗口";
        decoration_yangzhou_bingying_window.description = "兵营的小窗口";//传递些吃的东西？？？
        DECORATION_LIST.put(decoration_yangzhou_bingying_window.id, decoration_yangzhou_bingying_window);

        //茶馆
        Decoration decoration_yangzhou_chaguan_zitie = new Decoration();
        decoration_yangzhou_chaguan_zitie.id = "yangzhou_chaguan_zitie";
        decoration_yangzhou_chaguan_zitie.name = "字帖";
        decoration_yangzhou_chaguan_zitie.description = "茶馆的字帖";
        DECORATION_LIST.put(decoration_yangzhou_chaguan_zitie.id, decoration_yangzhou_chaguan_zitie);

        //西门
        Decoration decoration_yangzhou_ximen_gaoshi = new Decoration();
        decoration_yangzhou_ximen_gaoshi.id = "yangzhou_ximen_gaoshi";
        decoration_yangzhou_ximen_gaoshi.name = "告示";
        decoration_yangzhou_ximen_gaoshi.description = "西门的告示";
        DECORATION_LIST.put(decoration_yangzhou_ximen_gaoshi.id, decoration_yangzhou_ximen_gaoshi);

        //东门
        Decoration decoration_yangzhou_dongmen_gaoshi = new Decoration();
        decoration_yangzhou_dongmen_gaoshi.id = "yangzhou_dongmen_gaoshi";
        decoration_yangzhou_dongmen_gaoshi.name = "告示";
        decoration_yangzhou_dongmen_gaoshi.description = "东门的告示";
        DECORATION_LIST.put(decoration_yangzhou_dongmen_gaoshi.id, decoration_yangzhou_dongmen_gaoshi);
    }
}
