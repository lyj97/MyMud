package L_MyMud;

public class MovementManagement {
    public static void create_movements(){

        Movement movement_test1 = new Movement();
        movement_test1.id = "test_1";
        movement_test1.name = "���Լ���";
        movement_test1.description = "ʹ���˲��Լ��ܣ�����";
        movement_test1.reverse_description = "����ʹ���˲��Լ��ܣ�����";
        movement_test1.affect = new Affect();
        movement_test1.affect.hp = -5;
        movement_test1.affect.nl = -5;
        movement_test1.affect.jl = -5;
        Movement.movement_list.put(movement_test1.id, movement_test1);

        Movement movement_test2 = new Movement();
        movement_test2.id = "test_2";
        movement_test2.name = "���Լ���";
        movement_test2.description = "ʹ���˲��Լ��ܣ�����";
        movement_test2.reverse_description = "����ʹ���˲��Լ��ܣ�����";
        movement_test2.affect = new Affect();
        movement_test2.affect.hp = 5;
        movement_test2.affect.nl = 5;
        movement_test2.affect.jl = 5;
        Movement.movement_list.put(movement_test2.id, movement_test2);

        Movement movement_joke1 = new Movement();
        movement_joke1.id = "joke_1";
        movement_joke1.name = "�����";
        movement_joke1.description = "����һ������o(?��?)�ã�";
        movement_joke1.reverse_description = "��������һ������o(?��?)�ã�";
        Movement.movement_list.put(movement_joke1.id, movement_joke1);

        Movement movement_joke2 = new Movement();
        movement_joke2.id = "joke_2";
        movement_joke2.name = "�����";
        movement_joke2.description = "����һ������o(?��?)�ã���������TAһ����������";
        movement_joke2.reverse_description = "��������һ������o(?��?)�ã�������������һ����������";
        movement_joke2.affect = new Affect();
        movement_joke2.affect.hp = -1;
        Movement.movement_list.put(movement_joke2.id, movement_joke2);

        Movement movement_joke3 = new Movement();
        movement_joke3.id = "joke_3";
        movement_joke3.name = "�����";
        movement_joke3.description = "����һ������o(?��?)�ã������Է������Ϊ���ã�����";
        movement_joke3.reverse_description = "��������һ������o(?��?)�ã���������Ϊ�ܾ�δ�����˽����������Ϊ���ã�����";
        movement_joke3.affect = new Affect();
        movement_joke3.affect.hp = 1;
        Movement.movement_list.put(movement_joke3.id, movement_joke3);

        Movement movement_hunian1 = new Movement();
        movement_hunian1.id = "hunian_1";
        movement_hunian1.name = "����֮��";
        movement_hunian1.description = "ʹ���˺���֮��������";
        movement_hunian1.reverse_description = "����ʹ���˺���֮��������������ǰ������׳����������Ů�����Ե����ߵ�������";
        movement_hunian1.affect = new Affect();
        movement_hunian1.affect.nl = -5;
        movement_hunian1.affect.jl = -5;
        Movement.movement_list.put(movement_hunian1.id, movement_hunian1);

        Movement movement_hunian2 = new Movement();
        movement_hunian2.id = "hunian_2";
        movement_hunian2.name = "����֮��";
        movement_hunian2.description = "ʹ���˺���֮��������";
        movement_hunian2.reverse_description = "����ʹ���˺���֮��������������ǰ������׳�����ɹ������ţ����Ե����ߵ�������";
        movement_hunian2.affect = new Affect();
        movement_hunian2.affect.nl = -10;
        movement_hunian2.affect.jl = -10;
        Movement.movement_list.put(movement_hunian2.id, movement_hunian2);
    }
}
