package L_MyMud;

import java.util.HashMap;

public class DecorationManagement {

    public static HashMap<String, Decoration> DECORATION_LIST = new HashMap<>();

    public static void set_decoration() {

        //���ݹ㳡
        Decoration decoration_yangzhoou_guangchang_tree = new Decoration();
        decoration_yangzhoou_guangchang_tree.id = "yangzhou_guangchang_tree";
        decoration_yangzhoou_guangchang_tree.name = "������";
        decoration_yangzhoou_guangchang_tree.description = "���ݹ㳡��������";
        DECORATION_LIST.put(decoration_yangzhoou_guangchang_tree.id, decoration_yangzhoou_guangchang_tree);

        Decoration decoration_yangzhoou_guangchang_leaf = new Decoration();
        decoration_yangzhoou_guangchang_leaf.id = "yangzhou_guangchang_leaf";
        decoration_yangzhoou_guangchang_leaf.name = "��Ҷ";
        decoration_yangzhoou_guangchang_leaf.description = "���ݹ㳡������������Ҷ";
        DECORATION_LIST.put(decoration_yangzhoou_guangchang_leaf.id, decoration_yangzhoou_guangchang_leaf);

        Decoration decoration_yangzhoou_guangchang_sign = new Decoration();
        decoration_yangzhoou_guangchang_sign.id = "yangzhou_guangchang_sign";
        decoration_yangzhoou_guangchang_sign.name = "����";
        decoration_yangzhoou_guangchang_sign.description = "���ݹ㳡������";
        DECORATION_LIST.put(decoration_yangzhoou_guangchang_sign.id, decoration_yangzhoou_guangchang_sign);

        //����
        Decoration decoration_yangzhoou_beimen_gaoshi = new Decoration();
        decoration_yangzhoou_beimen_gaoshi.id = "yangzhou_beimen_gaoshi";
        decoration_yangzhoou_beimen_gaoshi.name = "��ʾ";
        decoration_yangzhoou_beimen_gaoshi.description = "���ŵĸ�ʾ";
        DECORATION_LIST.put(decoration_yangzhoou_beimen_gaoshi.id, decoration_yangzhoou_beimen_gaoshi);

        //������ջ
        Decoration decoration_yangzhou_kezhan_zhaopai = new Decoration();
        decoration_yangzhou_kezhan_zhaopai.id = "yangzhou_kezhan_zhaopai";
        decoration_yangzhou_kezhan_zhaopai.name = "����";
        decoration_yangzhou_kezhan_zhaopai.description = "������ջ������";
        DECORATION_LIST.put(decoration_yangzhou_kezhan_zhaopai.id, decoration_yangzhou_kezhan_zhaopai);

        Decoration decoration_yangzhou_kezhan_gaoshi = new Decoration();
        decoration_yangzhou_kezhan_gaoshi.id = "yangzhou_kezhan_gaoshi";
        decoration_yangzhou_kezhan_gaoshi.name = "��ʾ";
        decoration_yangzhou_kezhan_gaoshi.description = "������ջ�ĸ�ʾ";
        DECORATION_LIST.put(decoration_yangzhou_kezhan_gaoshi.id, decoration_yangzhou_kezhan_gaoshi);

        //����
        Decoration decoration_yangzhou_dangpu_paizi = new Decoration();
        decoration_yangzhou_dangpu_paizi.id = "yangzhou_dangpu_paizi";
        decoration_yangzhou_dangpu_paizi.name = "����";
        decoration_yangzhou_dangpu_paizi.description = "���̵�����";
        DECORATION_LIST.put(decoration_yangzhou_dangpu_paizi.id, decoration_yangzhou_dangpu_paizi);

        //����
        Decoration decoration_yangzhou_nanmen_gaoshi = new Decoration();
        decoration_yangzhou_nanmen_gaoshi.id = "yangzhou_nanmen_gaoshi";
        decoration_yangzhou_nanmen_gaoshi.name = "��ʾ";
        decoration_yangzhou_nanmen_gaoshi.description = "���ŵĸ�ʾ";
        DECORATION_LIST.put(decoration_yangzhou_nanmen_gaoshi.id, decoration_yangzhou_nanmen_gaoshi);

        //��Ӫ
        Decoration decoration_yangzhou_bingying_window = new Decoration();
        decoration_yangzhou_bingying_window.id = "yangzhou_bingying_window";
        decoration_yangzhou_bingying_window.name = "С����";
        decoration_yangzhou_bingying_window.description = "��Ӫ��С����";//����Щ�ԵĶ���������
        DECORATION_LIST.put(decoration_yangzhou_bingying_window.id, decoration_yangzhou_bingying_window);

        //���
        Decoration decoration_yangzhou_chaguan_zitie = new Decoration();
        decoration_yangzhou_chaguan_zitie.id = "yangzhou_chaguan_zitie";
        decoration_yangzhou_chaguan_zitie.name = "����";
        decoration_yangzhou_chaguan_zitie.description = "��ݵ�����";
        DECORATION_LIST.put(decoration_yangzhou_chaguan_zitie.id, decoration_yangzhou_chaguan_zitie);

        //����
        Decoration decoration_yangzhou_ximen_gaoshi = new Decoration();
        decoration_yangzhou_ximen_gaoshi.id = "yangzhou_ximen_gaoshi";
        decoration_yangzhou_ximen_gaoshi.name = "��ʾ";
        decoration_yangzhou_ximen_gaoshi.description = "���ŵĸ�ʾ";
        DECORATION_LIST.put(decoration_yangzhou_ximen_gaoshi.id, decoration_yangzhou_ximen_gaoshi);

        //����
        Decoration decoration_yangzhou_dongmen_gaoshi = new Decoration();
        decoration_yangzhou_dongmen_gaoshi.id = "yangzhou_dongmen_gaoshi";
        decoration_yangzhou_dongmen_gaoshi.name = "��ʾ";
        decoration_yangzhou_dongmen_gaoshi.description = "���ŵĸ�ʾ";
        DECORATION_LIST.put(decoration_yangzhou_dongmen_gaoshi.id, decoration_yangzhou_dongmen_gaoshi);
    }
}
