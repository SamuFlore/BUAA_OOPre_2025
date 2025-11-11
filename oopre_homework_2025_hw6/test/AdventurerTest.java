import org.junit.Test;

import java.util.ArrayList;
import java.util.EnumMap;

import static org.junit.Assert.*;

public class AdventurerTest {

    @Test
    public void getID() {
        Adventurer testAdv = new Adventurer("Alice");
        System.out.println(testAdv.getID());
    }

    @Test
    public void rmItem() {
        Bottle testBottle1 = Factory.crtBottle("Bot1", "HpBottle", 100);
        Bottle testBottle2 = Factory.crtBottle("Bot2", "DefBottle", 100);
        Bottle testBottle3 = Factory.crtBottle("Bot3", "AtkBottle", 100);
        Bottle testBottle4 = Factory.crtBottle("Bot4", "ManaBottle", 100);
        Equipment sword = new Sword("Excalibur", 100);
        Adventurer testAdv = new Adventurer("Alice");
        testAdv.addBt(testBottle1);
        testAdv.addBt(testBottle2);
        testAdv.addBt(testBottle3);
        testAdv.addBt(testBottle4);
        testAdv.addEqpmt(sword);
        testAdv.takeItem("Excalibur");
        testAdv.rmItem("Excalibur");
        assertNull(testAdv.getWeapon());
        testAdv.rmItem("Bot1");
        testAdv.rmItem("Bot2");
        testAdv.rmItem("Bot3");
        testAdv.rmItem("Bot4");
        testAdv.rmItem("Equip1");
        testAdv.rmItem("asdff"); //not exist
    }

    @Test
    public void takeItem() {
        Bottle testBottle1 = Factory.crtBottle("Bot1", "HpBottle", 100);
        Bottle testBottle2 = Factory.crtBottle("Bot2", "DefBottle", 100);
        Bottle testBottle3 = Factory.crtBottle("Bot3", "AtkBottle", 100);
        Bottle testBottle4 = Factory.crtBottle("Bot4", "ManaBottle", 100);
        Equipment testEquip = new Equipment("Equip1", 10);
        Adventurer testAdv = new Adventurer("Alice");
        testAdv.addBt(testBottle1);
        testAdv.addBt(testBottle2);
        testAdv.addBt(testBottle3);
        testAdv.addBt(testBottle4);
        testAdv.addEqpmt(testEquip);
        testAdv.takeItem("Bot1");
        testAdv.takeItem("Bot2");
        testAdv.takeItem("Bot3");
        testAdv.takeItem("Bot4");
        testAdv.takeItem("Equip1");
        testAdv.takeItem("asdff"); //not exist
    }

    @Test
    public void use() {
        Adventurer testUser = new Adventurer("Alice");
        Adventurer testTarget = new Adventurer("Bob");
        Adventurer testUser2 = new Adventurer("Carol");
        Bottle testBottle1 = Factory.crtBottle("Bot1", "HpBottle", 100);
        Bottle testBottle2 = Factory.crtBottle("Bot2", "DefBottle", 100);
        Bottle testBottle3 = Factory.crtBottle("Bot3", "AtkBottle", 100);
        Bottle testBottle4 = Factory.crtBottle("Bot4", "ManaBottle", 100);
        Spell testSpell1 = Factory.crtSpell("Sp1", "HealSpell", 1, 10);
        Spell testSpell2 = Factory.crtSpell("Sp2", "AttackSpell", 1, 10000);
        int effect = testBottle1.getEffect();
        testUser.addBt(testBottle1);
        testUser.addBt(testBottle2);
        testUser.addBt(testBottle3);
        testUser.addBt(testBottle4);
        testUser.takeItem("Bot1");
//        testUser.takeItem("Bot2"); not take
        testUser.takeItem("Bot3");
        testUser.takeItem("Bot3");
        testUser.takeItem("Bot4");
        testUser.takeItem(" wqefvsd"); //not exist
        testUser.addSp(testSpell1);
        testUser.addSp(testSpell2);
        testUser.use(testTarget, "Bot1");
        testUser.use(testTarget, "Bot2");
        testUser.use(testTarget, "Bot3");
        testUser.use(testTarget, "Bot4");
        testUser.use(testTarget, "Sp1");
        testUser.use(testTarget, "Sp2");
        testUser.use(testTarget, "adsasd"); //not exist
        testUser.use(testTarget, "1r2dfas"); //not exist
        testBottle1.setUnequipped();
        testTarget.takeItem("Bot1");
        testTarget.takeItem("Bot1"); //has been taken
        testTarget.takeItem("Bot2");
        testTarget.takeItem("Bot2");
        testTarget.takeItem("Bot3");
        testTarget.takeItem("Bot4");
        testTarget.takeItem("Sp1");
        testTarget.takeItem("Sp2");
        testTarget.takeItem("adsasd"); //not exist
        testUser2.addBt(testBottle1); // not taken
        testUser2.use(testUser, "Bot1");
        testUser2.use(testTarget, "Bot2");
        testUser2.addServant(testUser);
        testUser2.use(testUser ,"Bot1");
        testUser2.takeItem("Bot1");
        testUser2.use(testUser, "Bot1");
    }

    @Test
    public void buy() {
        Adventurer testUser = new Adventurer("Alice");
        testUser.buy("Excalibur", "Sword");
        testUser.addMoney(352);
        testUser.buy("Mathematical Analysis", "Magicbook");
        testUser.buy("abc", "Armour");
        testUser.buy("hpb", "HpBottle");
        testUser.buy("atkb", "AtkBottle");
        testUser.buy("def", "DefBottle");
        testUser.buy("mana", "ManaBottle");
    }

    @Test
    public void fight() {
        Adventurer testUser = new Adventurer("Alice");
        Adventurer testTarget1 = new Adventurer("Bob");
        Adventurer testTarget2 = new Adventurer("Carol");
        testTarget2.addDef(1);
        int totalDef = testTarget2.getTotalDef();
        Spell testSpell = Factory.crtSpell("Sp1", "AttackSpell", 1, 10);
        testUser.addSp(testSpell);
        Equipment testSword = new Sword("Excalibur", 499);
        Equipment testMagicbook = new Magicbook("Mathematical Analysis", 81);
        Equipment testArmour = new Armour("Armour", 499);
        testUser.addEqpmt(testSword);
        testUser.addEqpmt(testMagicbook);
        testTarget1.addEqpmt(testArmour);
        testUser.takeItem("Excalibur");
        testTarget1.takeItem("Armour");
        testUser.takeItem("Mathematical Analysis");
        testUser.fight(testTarget1, 0, 1, 1);
        testUser.takeItem("Excalibur");
        testUser.fight(testTarget1, 999999, 1, 1);
        testUser.fight(testTarget1, 0, 3, 1);
        testUser.fight(testTarget1, 0, 3, 2);
        testUser.fight(testTarget1, 0, 3, 3);
        testUser.use(testTarget2, "Sp1");
    }

    @Test
    public void findAdv() {
        ArrayList<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("Alice"));
        adventurers.add(new Adventurer("Bob"));
        Adventurer testUser1 = Adventurer.findAdv(adventurers, "Alice");
        assertNotNull(testUser1);
        Adventurer testUser2 = Adventurer.findAdv(adventurers, "Bob");
        assertNotNull(testUser2);
        Adventurer testUser3 = Adventurer.findAdv(adventurers, "fhjask"); //not exist
        assertNull(testUser3);
    }

    @Test
    public void Relation() {
        Adventurer testUser0 = new Adventurer("Alice");
        Adventurer testUser1 = new Adventurer("Bob");
        Adventurer testUser2 = new Adventurer("Carol");
        Adventurer testUser3 = new Adventurer("fhjask");
        Sword testS = new Sword("Excalibur", 1000);
        testUser0.addEqpmt(testS);
        testUser0.takeItem("Excalibur");
        Spell testSp = new HealSpell("Sp", 0, 100);
        testUser3.addSp(testSp);
        assert(testUser0.getServants().isEmpty());
        testUser0.addServant(testUser1);
        testUser0.removeServant(testUser1);
        assert(testUser0.getServants().isEmpty());
        testUser0.addServant(testUser2);
        testUser2.addServant(testUser3);
        assert(testUser0.getServants().contains(testUser3));
        testUser0.fight(testUser1, 0, 3, 1);
        testUser0.fight(testUser2, 0, 3, 2);
        testUser0.fight(testUser3, 0, 3, 3);
        testUser3.fight(testUser0, 0, 3, 3);
        assert(testUser2.getHitPoint() == 0);
        testUser2.checkHelp(500);
    }

    @Test
    public void Relation2() {
        Adventurer Alice = new Adventurer("Alice");
        Adventurer Bob = new Adventurer("Bob");
        Adventurer Carol = new Adventurer("Carol");
        Adventurer Fish = new Adventurer("Fish");
        Alice.addServant(Bob);
        Carol.addServant(Fish);
        Bob.addServant(Carol);
        Spell Heal = new HealSpell("Heal", 4, 10);
        Spell Heal2 = new HealSpell("Heal2", 6, 20);
        Spell Heal3 = new HealSpell("Heal3", 2, 10);
        Fish.addSp(Heal);
        Fish.addSp(Heal2);
        Fish.addSp(Heal3);
        Carol.addSp(Heal);
        Carol.addSp(Heal2);
        Bob.addSp(Heal);
        Bob.addSp(Heal2);
        Alice.underAttack(300);
        Alice.checkHelp(500);
        Alice.underAttack(150);
        Alice.checkHelp(260);
        Alice.underAttack(10000);
        assert(Fish.getMana() == 2);
        assert(Bob.getMana() == 0);
        assert(Bob.getMasters().isEmpty());
        Bob.underAttack(300);
        Bob.checkHelp(500);
        assert(Bob.getMana() == 0);
        assert(Carol.getMana() == 0);
        assert(Fish.getMana() == 0);
    }

    @Test
    public void Relation3() {
        Adventurer Alice = new Adventurer("Alice");
        Adventurer Bob = new Adventurer("Bob");
        Adventurer Carol = new Adventurer("Carol");
        Adventurer Fish = new Adventurer("Fish");
        Alice.addServant(Bob);
        Carol.addServant(Fish);
        Bob.addServant(Carol);
        Spell Heal = new HealSpell("Heal", 4, 10);
        Spell Fire = new AttackSpell("Fire", 2, 300);
        Adventurer Enemy = new Adventurer("Enemy");
        Enemy.addSp(Fire);
        Fish.addSp(Heal);
        Enemy.use(Alice, "Fire");
        Enemy.use(Bob, "Fire");
        Enemy.use(Carol, "Fire");
        Enemy.use(Fish, "Fire");
    }

    @Test
    public void Command1() {
        ArrayList<ArrayList<String>> inputInfo = new ArrayList<>(); // 解析后的输入将会存进该容器中, 类似于c语言的二维数组
        int n = 11;
        ArrayList<String> testInput1 = new ArrayList<>();
        testInput1.add("aa");
        testInput1.add("Alice");
        inputInfo.add(testInput1);
        ArrayList<String> testInput2 = new ArrayList<>();
        testInput2.add("aa");
        testInput2.add("Bob");
        inputInfo.add(testInput2);
        ArrayList<String> testInput3 = new ArrayList<>();
        testInput3.add("ab");
        testInput3.add("Alice");
        testInput3.add("Bot1");
        testInput3.add("HpBottle");
        testInput3.add("40");
        inputInfo.add(testInput3);
        ArrayList<String> testInput4 = new ArrayList<>();
        testInput4.add("ti");
        testInput4.add("Alice");
        testInput4.add("Bot1");
        inputInfo.add(testInput4);
        ArrayList<String> testInput5 = new ArrayList<>();
        testInput5.add("use");
        testInput5.add("Alice");
        testInput5.add("Bot1");
        testInput5.add("Bob");
        inputInfo.add(testInput5);
        ArrayList<String> testInput6 = new ArrayList<>();
        testInput6.add("ae");
        testInput6.add("Alice");
        testInput6.add("HugeSword");
        testInput6.add("Sword");
        testInput6.add("10");
        inputInfo.add(testInput6);
        ArrayList<String> testInput7 = new ArrayList<>();
        testInput7.add("ti");
        testInput7.add("Alice");
        testInput7.add("HugeSword");
        inputInfo.add(testInput7);
        ArrayList<String> testInput8 = new ArrayList<>();
        testInput8.add("ri");
        testInput8.add("Alice");
        testInput8.add("HugeSword");
        inputInfo.add(testInput8);
        ArrayList<String> testInput9 = new ArrayList<>();
        testInput9.add("ls");
        testInput9.add("Alice");
        testInput9.add("Fire");
        testInput9.add("AttackSpell");
        testInput9.add("5");
        testInput9.add("1000");
        inputInfo.add(testInput9);
        ArrayList<String> testInput10 = new ArrayList<>();
        testInput10.add("use");
        testInput10.add("Alice");
        testInput10.add("Bot1");
        testInput10.add("Bob");
        inputInfo.add(testInput10);
        ArrayList<String> testInput11 = new ArrayList<>();
        testInput11.add("use");
        testInput11.add("Alice");
        testInput11.add("Fire");
        testInput11.add("Bob");
        inputInfo.add(testInput11);
        ArrayList<Adventurer> adventurers = new ArrayList<>();
        Factory.execute(inputInfo, adventurers, n);
    }

    @Test
    public void Command2() {
        ArrayList<ArrayList<String>> inputInfo = new ArrayList<>(); // 解析后的输入将会存进该容器中, 类似于c语言的二维数组
        int n = 13;
        ArrayList<String> testInput1 = new ArrayList<>();
        testInput1.add("aa");
        testInput1.add("Alice");
        inputInfo.add(testInput1);
        ArrayList<String> testInput2 = new ArrayList<>();
        testInput2.add("aa");
        testInput2.add("Bob");
        inputInfo.add(testInput2);
        ArrayList<String> testInput3 = new ArrayList<>();
        testInput3.add("aa");
        testInput3.add("Carol");
        inputInfo.add(testInput3);
        ArrayList<String> testInput4 = new ArrayList<>();
        testInput4.add("ar");
        testInput4.add("Alice");
        testInput4.add("Carol");
        inputInfo.add(testInput4);
        ArrayList<String> testInput5 = new ArrayList<>();
        testInput5.add("ar");
        testInput5.add("Carol");
        testInput5.add("Bob");
        inputInfo.add(testInput5);
        ArrayList<String> testInput6 = new ArrayList<>();
        testInput6.add("fight");
        testInput6.add("Alice");
        testInput6.add("2");
        testInput6.add("Carol");
        testInput6.add("Bob");
        inputInfo.add(testInput6);
        ArrayList<String> testInput7 = new ArrayList<>();
        testInput7.add("fight");
        testInput7.add("Bob");
        testInput7.add("2");
        testInput7.add("Carol");
        testInput7.add("Alice");
        inputInfo.add(testInput7);
        ArrayList<String> testInput8 = new ArrayList<>();
        testInput8.add("ls");
        testInput8.add("Bob");
        testInput8.add("Heal");
        testInput8.add("HealSpell");
        testInput8.add("5");
        testInput8.add("1000");
        inputInfo.add(testInput8);
        ArrayList<String> testInput9 = new ArrayList<>();
        testInput9.add("ae");
        testInput9.add("Alice");
        testInput9.add("Excalibur");
        testInput9.add("Sword");
        testInput9.add("1000");
        inputInfo.add(testInput9);
        ArrayList<String> testInput10 = new ArrayList<>();
        testInput10.add("ti");
        testInput10.add("Alice");
        testInput10.add("Excalibur");
        inputInfo.add(testInput10);
        ArrayList<String> testInput11 = new ArrayList<>();
        testInput11.add("fight");
        testInput11.add("Alice");
        testInput11.add("1");
        testInput11.add("Carol");
        inputInfo.add(testInput11);
        ArrayList<String> testInput12 = new ArrayList<>();
        testInput12.add("aa");
        testInput12.add("Enemy");
        inputInfo.add(testInput12);
        ArrayList<String> testInput13 = new ArrayList<>();
        testInput13.add("fight");
        testInput13.add("Enemy");
        testInput13.add("2");
        testInput13.add("Alice");
        testInput13.add("Bob");
        inputInfo.add(testInput13);
        ArrayList<Adventurer> adventurers = new ArrayList<>();
        Factory.execute(inputInfo, adventurers, n);
    }

    @Test
    public void Factory() {
        Factory factory = new Factory();
    }
}