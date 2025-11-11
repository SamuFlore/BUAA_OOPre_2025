import java.util.ArrayDeque;
import java.util.ArrayList;

public class Factory {
    // 装备
    public static Equipment createEquipment(String id, String type, int ce) {
        switch (type) {
            case "Armour": {
                return new Armour(id, ce);
            }
            case "Sword": {
                return new Sword(id, ce);
            }
            case "Magicbook": {
                return new Magicbook(id, ce);
            }
            default: { return null; }
        }
    }

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
                return null;
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

    public static Bottle findEquippedBottle(ArrayDeque<Bottle> bottles, String id) {
        for (Bottle bottle : bottles) {
            if (bottle.getID().equals(id)) {
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
                return null;
            }
        }
    }

    // 通用
    public static void createItem(Adventurer adv, String id, String type) {
        int money = Math.min(adv.getMoney(), 100);
        adv.addMoney(-money);
        Bottle b = createBottle(id, type, money);
        if (b != null) {
            adv.addBottle(b);
        }
        else {
            Equipment e = createEquipment(id, type, money);
            adv.addEquipment(e);
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
    public static void execute(ArrayList<ArrayList<String>> in, ArrayList<Adventurer> advs, int n) {
        for (int i = 0; i < n; i++) {
            Adventurer adventurer = Adventurer.findAdv(advs, in.get(i).get(1));
            String itemId = (in.get(i).size() <= 2) ? null : in.get(i).get(2); //通用id
            String type = (in.get(i).size() <= 3) ? null : in.get(i).get(3); //种类或目标id
            switch (in.get(i).get(0)) {
                case "aa" :
                    advs.add(new Adventurer(in.get(i).get(1)));
                    break;
                case "ab" :
                    int effect = Integer.parseInt(in.get(i).get(4)); //效果值
                    Bottle b = Factory.createBottle(itemId, type, effect);
                    adventurer.addBottle(b);
                    break;
                case "ae" :
                    int ce = Integer.parseInt(in.get(i).get(4)); //装备效果
                    Equipment e = Factory.createEquipment(itemId, type, ce);
                    adventurer.addEquipment(e);
                    break;
                case "ls" :
                    int manaCost = Integer.parseInt(in.get(i).get(4)); //耗蓝
                    int power = Integer.parseInt(in.get(i).get(5)); //法力
                    Spell sp = Factory.createSpell(itemId, type, manaCost, power);
                    adventurer.addSpell(sp);
                    break;
                case "ri" :
                    adventurer.removeItem(itemId);
                    break;
                case "ti" :
                    adventurer.takeItem(itemId);
                    break;
                case "use" :
                    Adventurer itemTarget = Adventurer.findAdv(advs, type); //type=targetId
                    adventurer.use(itemTarget, itemId);
                    break;
                case "bi" :
                    adventurer.buy(itemId, type);
                    break;
                case "fight" :
                    int k = Integer.parseInt(in.get(i).get(2));
                    int totalDef = 0;
                    ArrayList<Adventurer> toFightWith = new ArrayList<>();
                    for (int x = 0; x < k; x++) {
                        Adventurer fightTarget = Adventurer.findAdv(advs, in.get(i).get(3 + x));
                        toFightWith.add(fightTarget);
                        if (fightTarget.getTotalDef() > totalDef) {
                            totalDef = fightTarget.getTotalDef();
                        }
                    }
                    for (int x = 0; x < k; x++) {
                        adventurer.fight(toFightWith.get(x), totalDef, k, x + 1);
                    }
                    break;
                default : { break; }
            }
        }
    }
}
