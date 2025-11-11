import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AdventurerTest {

    @Test
    public void getID() {
        Adventurer testAdv = new Adventurer("Alice");
        System.out.println(testAdv.getID());
    }

    @Test
    public void removeItem() {
        Bottle testBottle1 = Factory.createBottle("Bot1", "HpBottle", 100);
        Bottle testBottle2 = Factory.createBottle("Bot2", "DefBottle", 100);
        Bottle testBottle3 = Factory.createBottle("Bot3", "AtkBottle", 100);
        Bottle testBottle4 = Factory.createBottle("Bot4", "ManaBottle", 100);
        Equipment testEquip = new Equipment("Equip1");
        Adventurer testAdv = new Adventurer("Alice");
        testAdv.addBottle(testBottle1);
        testAdv.addBottle(testBottle2);
        testAdv.addBottle(testBottle3);
        testAdv.addBottle(testBottle4);
        testAdv.addEquipment(testEquip);
        testAdv.removeItem("Bot1");
        testAdv.removeItem("Bot2");
        testAdv.removeItem("Bot3");
        testAdv.removeItem("Bot4");
        testAdv.removeItem("Equip1");
        testAdv.removeItem("asdff"); //not exist
    }

    @Test
    public void takeItem() {
        Bottle testBottle1 = Factory.createBottle("Bot1", "HpBottle", 100);
        Bottle testBottle2 = Factory.createBottle("Bot2", "DefBottle", 100);
        Bottle testBottle3 = Factory.createBottle("Bot3", "AtkBottle", 100);
        Bottle testBottle4 = Factory.createBottle("Bot4", "ManaBottle", 100);
        Equipment testEquip = new Equipment("Equip1");
        Adventurer testAdv = new Adventurer("Alice");
        testAdv.addBottle(testBottle1);
        testAdv.addBottle(testBottle2);
        testAdv.addBottle(testBottle3);
        testAdv.addBottle(testBottle4);
        testAdv.addEquipment(testEquip);
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
        Bottle testBottle1 = Factory.createBottle("Bot1", "HpBottle", 100);
        Bottle testBottle2 = Factory.createBottle("Bot2", "DefBottle", 100);
        Bottle testBottle3 = Factory.createBottle("Bot3", "AtkBottle", 100);
        Bottle testBottle4 = Factory.createBottle("Bot4", "ManaBottle", 100);
        Spell testSpell1 = Factory.createSpell("Sp1", "HealSpell", 1, 10);
        Spell testSpell2 = Factory.createSpell("Sp2", "AttackSpell", 1, 10000);
        testUser.addBottle(testBottle1);
        testUser.addBottle(testBottle2);
        testUser.addBottle(testBottle3);
        testUser.addBottle(testBottle4);
        testUser.takeItem("Bot1");
//        testUser.takeItem("Bot2"); not take
        testUser.takeItem("Bot3");
        testUser.takeItem("Bot4");
        testUser.takeItem(" wqefvsd"); //not exist
        testUser.addSpell(testSpell1);
        testUser.addSpell(testSpell2);
        testUser.use(testTarget, "Bot1");
        testUser.use(testTarget, "Bot2");
        testUser.use(testTarget, "Bot3");
        testUser.use(testTarget, "Bot4");
        testUser.use(testTarget, "Sp1");
        testUser.use(testTarget, "Sp2");
        testUser.use(testTarget, "adsasd"); //not exist
        testUser.use(testTarget, "1r2dfas"); //not exist
        testTarget.takeItem("Bot1");
        testTarget.takeItem("Bot2");
        testTarget.takeItem("Bot3");
        testTarget.takeItem("Bot4");
        testTarget.takeItem("Sp1");
        testTarget.takeItem("Sp2");
        testTarget.takeItem("adsasd"); //not exist
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
    public void Command() {
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
        testInput6.add("Sword");
        inputInfo.add(testInput6);
        ArrayList<String> testInput7 = new ArrayList<>();
        testInput7.add("ti");
        testInput7.add("Alice");
        testInput7.add("Sword");
        inputInfo.add(testInput7);
        ArrayList<String> testInput8 = new ArrayList<>();
        testInput8.add("ri");
        testInput8.add("Alice");
        testInput8.add("Sword");
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
    public void Factory() {
        Factory factory = new Factory();
    }
}