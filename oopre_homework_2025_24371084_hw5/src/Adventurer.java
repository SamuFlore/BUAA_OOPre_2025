import java.util.ArrayDeque;
import java.util.ArrayList;

public class Adventurer {
    private String id;
    private int equippedBottlesNum;
    private ArrayList<Bottle> bottles; //item
    private ArrayDeque<Bottle> equippedBottles;
    private ArrayList<Equipment> equipments; //item
    private ArrayList<Spell> spells;
    private int hitPoint; //HP
    private int atk;
    private int def;
    private int mana; //MP
    private int money;
    private Equipment myWeapon;
    private Equipment myArmour;

    public Adventurer(String id) {
        this.id = id;
        this.equippedBottlesNum = 0;
        this.bottles = new ArrayList<>();
        this.equippedBottles = new ArrayDeque<>();
        this.equipments = new ArrayList<>();
        this.spells = new ArrayList<>();
        this.hitPoint = 500;
        this.atk = 1;
        this.def = 0;
        this.mana = 10;
        this.money = 50;
        this.myArmour = null;
        this.myWeapon = null;
    }

    public Equipment getWeapon() { return this.myWeapon; }

    public String getID() { return id; }

    public int getHitPoint() { return hitPoint; }

    public int getAtk() { return atk; }

    public int getDef() { return def; }

    public int getTotalDef() {
        if (this.myArmour == null) { return getDef(); }
        else { return getDef() + this.myArmour.getCe(); }
    }

    public int getMana() { return mana; }

    public int getMoney() { return money; }

    public void addHitPoint(int value) { this.hitPoint += value; }

    public void addAtk(int value) { this.atk += value; }

    public void addDef(int value) { this.def += value; }

    public void addMana(int value) { this.mana += value; }

    public void addMoney(int value) { this.money += value; }

    public void removeBottle(Bottle bottle) { //使用了bottle
        this.bottles.remove(bottle);
        this.equippedBottles.remove(bottle);
        this.equippedBottlesNum--;
    }

    public void underAttack(int value) {
        this.hitPoint -= value;
        if (this.hitPoint <= 0) {
            this.hitPoint = 0;
        }
    }

    public void addBottle(Bottle bottle) {
        if (this.hitPoint == 0) {
            System.out.printf("%s is dead!\n", this.id);
        }
        else {
            this.bottles.add(bottle);
        }
    }

    public void addEquipment(Equipment equipment) {
        if (this.hitPoint == 0) {
            System.out.printf("%s is dead!\n", this.id);
        }
        else {
            this.equipments.add(equipment);
        }
    }

    public void addSpell(Spell spell) {
        if (this.hitPoint == 0) {
            System.out.printf("%s is dead!\n", this.id);
        }
        else {
            this.spells.add(spell);
        }
    }

    public void removeItem(String itemID) {
        if (this.hitPoint == 0) {
            System.out.printf("%s is dead!\n", this.id);
        }
        else {
            Bottle foundBottle = Factory.findBottle(this.bottles, itemID);
            if (foundBottle != null) {
                if (this.equippedBottles.remove(foundBottle)) {
                    this.equippedBottlesNum--;
                }
                String className = foundBottle.getClassName();
                System.out.printf("%s\n", className);
                this.bottles.remove(foundBottle);
                return;
            }
            Equipment foundEquipment = Factory.findEquipment(this.equipments, itemID);
            if (foundEquipment != null) {
                if (this.myArmour != null && this.myArmour.equals(foundEquipment)) {
                    this.myArmour = null;
                }
                else if (this.myWeapon != null && this.myWeapon.equals(foundEquipment)) {
                    this.myWeapon = null;
                }
                String className = foundEquipment.getClassName();
                System.out.printf("%s\n", className);
                this.equipments.remove(foundEquipment);
                return;
            }
        }
    }

    public void takeItem(String itemID) {
        if (this.hitPoint == 0) {
            System.out.printf("%s is dead!\n", this.id);
        }
        else {
            Bottle foundBottle = Factory.findBottle(this.bottles, itemID);
            if (foundBottle != null) {
                String className = foundBottle.getClassName();
                System.out.printf("%s\n", className);
                if (this.equippedBottlesNum == 10) {
                    this.equippedBottles.removeFirst();
                    this.equippedBottles.addLast(foundBottle);
                }
                else {
                    this.equippedBottles.addLast(foundBottle);
                    this.equippedBottlesNum++;
                }
                foundBottle.setIsEquipped();
                return;
            }
            Equipment foundEquipment = Factory.findEquipment(this.equipments, itemID);
            if (foundEquipment != null) {
                String className = foundEquipment.getClassName();
                System.out.printf("%s\n", className);
                if (className.equals("Armour")) { this.myArmour = foundEquipment; }
                else if (className.equals("Sword") || className.equals("Magicbook")) {
                    this.myWeapon =  foundEquipment;
                }
                return;
            }
        }
    }

    public Usable findUsable(String usableId) { //找寻可用物品
        Bottle foundBottle = Factory.findEquippedBottle(this.equippedBottles, usableId);
        if (foundBottle != null) { return foundBottle; }

        Spell foundSpell = Factory.findSpell(this.spells, usableId);
        if (foundSpell != null) { return foundSpell; }

        return null;
    }

    public void use(Adventurer target, String itemID) { //bottle or spell
        if (this.hitPoint == 0) { //user is dead
            System.out.printf("%s is dead!\n", this.id);
        }
        else if (target.hitPoint == 0) { //target is dead
            System.out.printf("%s is dead!\n", target.id);
        }
        else {
            Usable foundUsable = findUsable(itemID);
            if (foundUsable != null) {
                foundUsable.use(this, target);
            }
            else {
                System.out.printf("%s fail to use %s\n", this.getID(), itemID);
            }
        }
    }

    public void fight(Adventurer target, int totalDef, int totalNum, int index) {
        if (this.hitPoint == 0) {
            System.out.printf("%s is dead!\n", this.id);
        }
        else if (target.hitPoint == 0) {
            System.out.printf("%s is dead!\n", target.id);
        }
        else {
            if (this.myWeapon == null || this.myWeapon.getClassName().equals("Sword")) {
                int tmpAtk = 0;
                if (this.myWeapon != null) { tmpAtk = this.myWeapon.getCe(); }
                if (this.atk + tmpAtk <= totalDef) {
                    if (index == totalNum) {
                        System.out.printf("Adventurer %s defeated\n", this.id);
                    }
                }
                else {
                    target.underAttack(this.atk + tmpAtk -  totalDef);
                    if (target.getHitPoint() == 0) { this.addMoney(earnMoney(target)); } //得到所有钱
                    if (totalNum == index) { System.out.printf("%d\n", target.getHitPoint()); }
                    else { System.out.printf("%d ", target.getHitPoint()); }
                }
            }
            else if (this.myWeapon.getClassName().equals("Magicbook")) {
                if (this.getMana() >= (int)Math.ceil(Math.sqrt(this.myWeapon.getCe()))) {
                    target.underAttack(this.atk + this.myWeapon.getCe());
                    if (target.getHitPoint() == 0) { this.addMoney(earnMoney(target)); } //得到所有钱
                    if (index == totalNum) { //只要扣一次蓝
                        this.addMana(-(int)Math.ceil(Math.sqrt(this.myWeapon.getCe())));
                    }
                    if (totalNum == index) { System.out.printf("%d\n", target.getHitPoint()); }
                    else { System.out.printf("%d ", target.getHitPoint()); }
                }
                else {
                    if (index == totalNum) {
                        System.out.printf("Adventurer %s defeated\n", this.id);
                    }
                }
            }
        }
    }

    public int earnMoney(Adventurer target) {
        int rewards = 0;
        int bottleEf = 0;
        for (Bottle bottle : target.bottles) {
            bottleEf += bottle.getEffect();
        }
        int equipmentEf = 0;
        for (Equipment equipment : target.equipments) {
            equipmentEf += equipment.getCe();
        }
        rewards = target.getMoney() + bottleEf + equipmentEf;
        return  rewards;
    }

    public void buy(String id, String type) {
        if (this.hitPoint == 0) {
            System.out.printf("%s is dead!\n", this.id);
        }
        else {
            Factory.createItem(this, id, type);
            System.out.printf("%d\n", this.getMoney());
        }
    }

    public static Adventurer findAdv(ArrayList<Adventurer> adventurers, String advId) { //输入一个ID，返回之
        for (Adventurer adv : adventurers) {
            if (advId.equals(adv.getID())) {
                return adv;
            }
        }
        return null;
    }
}
