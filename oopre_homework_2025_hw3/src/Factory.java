import java.util.ArrayList;

public class Factory {
    // 装备
    public static Equipment findEquipment(ArrayList<Equipment> equipments, String equipmentId) {
        for (Equipment equipment : equipments) {
            if (equipmentId.equals(equipment.getID())) {
                return equipment;
            }
        }
        return null;
    }

    // 药水
    public static Bottle createBottle(String id, String type, int effect) {
        switch (type) {
            case "HpBottle": {
                return new HpBottle(id, effect);
            }
            case "AtkBottle": {
                return new AtkBottle(id, effect);
            }
            case "DefBottle": {
                return new DefBottle(id, effect);
            }
            case "ManaBottle": {
                return new ManaBottle(id, effect);
            }
            default: {
                return new Bottle(id, effect);
            }
        }
    }

    public static Bottle findBottle(ArrayList<Bottle> bottles, String bottleId) {
        for (Bottle bottle : bottles) {
            if (bottleId.equals(bottle.getID())) {
                return bottle;
            }
        }
        return null;
    }

    // 法术
    public static Spell createSpell(String id, String type, int manaCost, int power) {
        switch (type) {
            case "HealSpell": {
                return new HealSpell(id, manaCost, power);
            }
            case "AttackSpell": {
                return new AttackSpell(id, manaCost, power);
            }
            default: {
                return new Spell(id, manaCost, power);
            }
        }
    }

    public static Spell findSpell(ArrayList<Spell> spells, String spellId) {
        for (Spell spell : spells) {
            if (spellId.equals(spell.getID())) {
                return spell;
            }
        }
        return null;
    }

    // 命令执行
    public static void execute(ArrayList<ArrayList<String>> inputInfo,
                               ArrayList<Adventurer> adventurers,
                               int n) { //方法太长待改
        for (int i = 0; i < n; i++) {
            String advId = inputInfo.get(i).get(1);
            switch (inputInfo.get(i).get(0)) {
                case "aa" : {
                    adventurers.add(new Adventurer(advId));
                    break;
                }
                case "ab" : {
                    String botId = inputInfo.get(i).get(2); //药水ID
                    String type = inputInfo.get(i).get(3); //药水种类
                    int effect = Integer.parseInt(inputInfo.get(i).get(4)); //效果值
                    Adventurer adventurer = Adventurer.findAdv(adventurers, advId); //找到人
                    Bottle b = Factory.createBottle(botId, type, effect);
                    adventurer.addBottle(b);
                    break;
                }
                case "ae" : {
                    Adventurer adventurer = Adventurer.findAdv(adventurers, advId);
                    Equipment equipment = new Equipment(inputInfo.get(i).get(2));
                    adventurer.addEquipment(equipment);
                    break;
                }
                case "ls" : {
                    String speId = inputInfo.get(i).get(2);
                    String type = inputInfo.get(i).get(3);
                    int manaCost = Integer.parseInt(inputInfo.get(i).get(4));
                    int power = Integer.parseInt(inputInfo.get(i).get(5));
                    Adventurer adventurer = Adventurer.findAdv(adventurers, advId);
                    Spell sp = Factory.createSpell(speId, type, manaCost, power);
                    adventurer.addSpell(sp);
                    break;
                }
                case "ri" : {
                    String itemId = inputInfo.get(i).get(2);
                    Adventurer adventurer = Adventurer.findAdv(adventurers, advId);
                    adventurer.removeItem(itemId);
                    break;
                }
                case "ti" : {
                    String itemId = inputInfo.get(i).get(2);
                    Adventurer adventurer = Adventurer.findAdv(adventurers, advId);
                    adventurer.takeItem(itemId);
                    break;
                }
                case "use" : {
                    String usableId = inputInfo.get(i).get(2);
                    String targetId = inputInfo.get(i).get(3);
                    Adventurer user = Adventurer.findAdv(adventurers, advId);
                    Adventurer target = Adventurer.findAdv(adventurers, targetId);
                    user.use(target, usableId);
                    break;
                }
                default : {
                    break;
                }
            }
        }
    }
}
