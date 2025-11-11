import java.util.ArrayList;

public class Adventurer {
    private String id;
    private int bottleNum;
    private int equipmentNum;
    private int spellNum;
    private ArrayList<Bottle> bottles; //item
    private ArrayList<Equipment> equipments; //item
    private ArrayList<Spell> spells;
    private int hitPoint; //HP
    private int atk;
    private int def;
    private int mana; //MP

    public Adventurer(String id) {
        this.id = id;
        this.bottleNum = 0;
        this.equipmentNum = 0;
        this.spellNum = 0;
        this.bottles = new ArrayList<>();
        this.equipments = new ArrayList<>();
        this.spells = new ArrayList<>();
        this.hitPoint = 500;
        this.atk = 1;
        this.def = 0;
        this.mana = 10;
    }

    public String getID() { return id; }

    public int getHitPoint() { return hitPoint; }

    public int getAtk() { return atk; }

    public int getDef() { return def; }

    public int getMana() { return mana; }

    public void addHitPoint(int value) { this.hitPoint += value; }

    public void addAtk(int value) { this.atk += value; }

    public void addDef(int value) { this.def += value; }

    public void addMana(int value) { this.mana += value; }

    public void removeBottle(Bottle bottle) { //使用了bottle
        if (this.bottles.remove(bottle)) {
            this.bottleNum--;
        }
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
            this.bottleNum++;
        }
    }

    public void addEquipment(Equipment equipment) {
        if (this.hitPoint == 0) {
            System.out.printf("%s is dead!\n", this.id);
        }
        else {
            this.equipments.add(equipment);
            this.equipmentNum++;
        }
    }

    public void addSpell(Spell spell) {
        if (this.hitPoint == 0) {
            System.out.printf("%s is dead!\n", this.id);
        }
        else {
            this.spells.add(spell);
            this.spellNum++;
        }
    }

    public void removeItem(String itemID) {
        if (this.hitPoint == 0) {
            System.out.printf("%s is dead!\n", this.id);
        }
        else {
            Bottle foundBottle = Factory.findBottle(this.bottles, itemID);
            if (foundBottle != null) {
                String className = foundBottle.getClassName();
                System.out.printf("%s\n", className);
                this.bottles.remove(foundBottle);
                this.bottleNum--;
                return;
            }
            Equipment foundEquipment = Factory.findEquipment(this.equipments, itemID);
            if (foundEquipment != null) {
                String className = foundEquipment.getClassName();
                System.out.printf("%s\n", className);
                this.equipments.remove(foundEquipment);
                this.equipmentNum--;
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
                foundBottle.setIsEquipped();
                return;
            }
            Equipment foundEquipment = Factory.findEquipment(this.equipments, itemID);
            if (foundEquipment != null) {
                String className = foundEquipment.getClassName();
                System.out.printf("%s\n", className);
                foundEquipment.setIsEquipped();
                return;
            }
        }
    }

    public Usable findUsable(String usableId) { //找寻可用物品
        Bottle foundBottle = Factory.findBottle(this.bottles, usableId);
        if (foundBottle != null) { return foundBottle; }

        Spell foundSpell = Factory.findSpell(this.spells, usableId);
        if (foundSpell != null) { return foundSpell; }

        return null;
    }

    public void use(Adventurer target, String itemID) {
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
