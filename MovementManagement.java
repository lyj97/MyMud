package L_MyMud;

public class MovementManagement {
    public static void create_movements(){

        Movement movement_test1 = new Movement();
        movement_test1.id = "test_1";
        movement_test1.name = "测试技能";
        movement_test1.description = "使用了测试技能！！！";
        movement_test1.reverse_description = "对您使用了测试技能！！！";
        movement_test1.affect = new Affect();
        movement_test1.affect.hp = -5;
        movement_test1.affect.nl = -5;
        movement_test1.affect.jl = -5;
        Movement.movement_list.put(movement_test1.id, movement_test1);

        Movement movement_test2 = new Movement();
        movement_test2.id = "test_2";
        movement_test2.name = "测试技能";
        movement_test2.description = "使用了测试技能！！！";
        movement_test2.reverse_description = "对您使用了测试技能！！！";
        movement_test2.affect = new Affect();
        movement_test2.affect.hp = 5;
        movement_test2.affect.nl = 5;
        movement_test2.affect.jl = 5;
        Movement.movement_list.put(movement_test2.id, movement_test2);

        Movement movement_joke1 = new Movement();
        movement_joke1.id = "joke_1";
        movement_joke1.name = "扮鬼脸";
        movement_joke1.description = "做了一个鬼脸o(?Д?)っ！";
        movement_joke1.reverse_description = "对您做了一个鬼脸o(?Д?)っ！";
        Movement.movement_list.put(movement_joke1.id, movement_joke1);

        Movement movement_joke2 = new Movement();
        movement_joke2.id = "joke_2";
        movement_joke2.name = "扮鬼脸";
        movement_joke2.description = "做了一个鬼脸o(?Д?)っ！！！吓了TA一大跳！！！";
        movement_joke2.reverse_description = "对您做了一个鬼脸o(?Д?)っ！！！您被吓了一大跳！！！";
        movement_joke2.affect = new Affect();
        movement_joke2.affect.hp = -1;
        Movement.movement_list.put(movement_joke2.id, movement_joke2);

        Movement movement_joke3 = new Movement();
        movement_joke3.id = "joke_3";
        movement_joke3.name = "扮鬼脸";
        movement_joke3.description = "做了一个鬼脸o(?Д?)っ！？？对方心情大为愉悦！！！";
        movement_joke3.reverse_description = "对您做了一个鬼脸o(?Д?)っ！？？您因为很久未曾与人交流，心情大为愉悦！！！";
        movement_joke3.affect = new Affect();
        movement_joke3.affect.hp = 1;
        Movement.movement_list.put(movement_joke3.id, movement_joke3);

        Movement movement_hunian1 = new Movement();
        movement_hunian1.id = "hunian_1";
        movement_hunian1.name = "狐念之术";
        movement_hunian1.description = "使用了狐念之术！！！";
        movement_hunian1.reverse_description = "对您使用了狐念之术！！！您将眼前的三尺壮汉错看成窈窕淑女，被迷得神魂颠倒！！！";
        movement_hunian1.affect = new Affect();
        movement_hunian1.affect.nl = -5;
        movement_hunian1.affect.jl = -5;
        Movement.movement_list.put(movement_hunian1.id, movement_hunian1);

        Movement movement_hunian2 = new Movement();
        movement_hunian2.id = "hunian_2";
        movement_hunian2.name = "狐念之术";
        movement_hunian2.description = "使用了狐念之术！！！";
        movement_hunian2.reverse_description = "对您使用了狐念之术！！！您将眼前的三尺壮汉错看成国民老婆，被迷得神魂颠倒！！！";
        movement_hunian2.affect = new Affect();
        movement_hunian2.affect.nl = -10;
        movement_hunian2.affect.jl = -10;
        Movement.movement_list.put(movement_hunian2.id, movement_hunian2);
    }
}
