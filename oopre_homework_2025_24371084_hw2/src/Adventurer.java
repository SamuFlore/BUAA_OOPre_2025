import java.util.ArrayList;

public class Adventurer {
    private String id;
    private int bottleNum;
    private int equipmentNum;
    private ArrayList<Bottle> bottles;
    private ArrayList<Equipment> equipments;

    public Adventurer(String id) {
        this.id = id;
        this.bottleNum = 0;
        this.equipmentNum = 0;
        this.bottles = new ArrayList<>();
        this.equipments = new ArrayList<>();
    }

    public String getID() {
        return id;
    }

    public void addBottle(Bottle bottle) {
        this.bottles.add(bottle);
        this.bottleNum++;
    }

    public void addEquipment(Equipment equipment) {
        this.equipments.add(equipment);
        this.equipmentNum++;
    }

    public void removeBottle(String bottleID) {
        String effect = null;
        for (int i = 0; i < bottles.size(); i++) {
            if (bottles.get(i).getID().equals(bottleID)) {
                effect = bottles.get(i).getEffect();
                this.bottleNum--;
                break;
            }
        }
        System.out.printf("%d %s\n", this.bottleNum, effect);
    }

    public void removeEquipment(String equipmentID) {
        this.equipmentNum--;
        System.out.printf("%d\n", this.equipmentNum);
    }

    public void getBottles() {
        for (Bottle bottle : this.bottles) {
            System.out.println(bottle.getID() + " " + bottle.getEffect());
        }
    }

    public void getEquipment() {
        for (Equipment equipment : this.equipments) {
            System.out.println(equipment.getID());
        }
    }
}
