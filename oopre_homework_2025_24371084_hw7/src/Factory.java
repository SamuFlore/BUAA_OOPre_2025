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
    public static Bottle crtBottle(String id, String type, int effect) {
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

    public static Bottle fdBottle(ArrayList<Bottle> bottles, String bottleId) {
        for (Bottle bottle : bottles) {
            if (bottleId.equals(bottle.getID())) {
                return bottle;
            }
        }
        return null;
    }

    public static Bottle fdEquippedBottle(ArrayDeque<Bottle> bottles, String id) {
        for (Bottle bottle : bottles) {
            if (bottle.getID().equals(id)) {
                return bottle;
            }
        }
        return null;
    }

    // 法术
    public static Spell crtSpell(String id, String type, int manaCost, int power) {
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
    public static void crtItem(Adventurer adv, String id, String type) {
        int money = Math.min(adv.getMoney(), 100);
        adv.addMoney(-money);
        Bottle b = crtBottle(id, type, money);
        if (b != null) {
            adv.addBt(b);
        }
        else {
            Equipment e = createEquipment(id, type, money);
            adv.addEqpmt(e);
        }
    }

    public static Spell fdSpell(ArrayList<Spell> spells, String spellId) {
        for (Spell spell : spells) {
            if (spellId.equals(spell.getID())) {
                return spell;
            }
        }
        return null;
    }

    public static boolean checkMaster(Adventurer adv, ArrayList<Adventurer> toFight) {
        for (Adventurer people : toFight) {
            if (adv.getMasters().contains(people)) { return true; }
        }
        return false;
    }

    public static String lrOrder(ArrayList<String> cmd) {
        int index = 1;
        StringBuilder sb = new StringBuilder();
        while (index < cmd.size()) {
            sb.append(cmd.get(index));
            index++;
        }
        return sb.toString();
    }

    public static void loadRelation(ArrayList<Adventurer> advs, ArrayList<String> cmd) {
        Lexer lexer = new Lexer(lrOrder(cmd));
        Parser parser = new Parser(lexer);
        parser.parse(advs);
    }

    public static void parseFight(ArrayList<String> cmd, ArrayList<Adventurer> advs,
                                  Adventurer adventurer) {
        int k = Integer.parseInt(cmd.get(2));
        int totalDef = 0;
        ArrayList<Adventurer> fighters = new ArrayList<>();
        ArrayList<Integer> prevHp = new ArrayList<>();
        for (int x = 0; x < k; x++) {
            Adventurer ft = Adventurer.findAdv(advs, cmd.get(3 + x));
            fighters.add(ft);
            prevHp.add(ft.getHitPoint());
            totalDef = Math.max(totalDef, ft.getTotalDef());
        }
        if (checkMaster(adventurer, fighters)) {
            System.out.print("That's my boss!\n");
        } else {
            for (int x = 0; x < k; x++) {
                adventurer.fight(fighters.get(x), totalDef, k, x + 1);
            }
            for (int x = 0; x < k; x++) {
                fighters.get(x).checkHelp(prevHp.get(x));
            }
        }
    }

    // 命令执行
    public static void execute(ArrayList<ArrayList<String>> in, ArrayList<Adventurer> advs, int n) {
        for (int i = 0; i < n; i++) {
            ArrayList<String> cmd = in.get(i);
            Adventurer adventurer = Adventurer.findAdv(advs, cmd.get(1)); // adventurer relations
            String itemId = cmd.size() <= 2 ? null : cmd.get(2);
            String type = cmd.size() <= 3 ? null : cmd.get(3);
            System.out.println(cmd);
            switch (cmd.get(0)) {
                case "aa": advs.add(new Adventurer(cmd.get(1)));
                    break;
                case "ab":
                    adventurer.addBt(Factory.crtBottle(itemId, type, Integer.parseInt(cmd.get(4))));
                    break;
                case "ae":
                    adventurer.addEqpmt(Factory.createEquipment(itemId, type,
                            Integer.parseInt(cmd.get(4))));
                    break;
                case "ls":
                    adventurer.addSp(Factory.crtSpell(itemId, type,
                            Integer.parseInt(cmd.get(4)), Integer.parseInt(cmd.get(5))));
                    break;
                case "ri": adventurer.rmItem(itemId);
                    break;
                case "ti": adventurer.takeItem(itemId);
                    break;
                case "use":
                    adventurer.use(Adventurer.findAdv(advs, type), itemId);
                    break;
                case "bi": adventurer.buy(itemId, type);
                    break;
                case "fight": parseFight(cmd, advs, adventurer);
                    break;
                case "ar": adventurer.addServant(Adventurer.findAdv(advs, itemId));
                    break;
                case "rr": adventurer.removeServant(Adventurer.findAdv(advs, itemId));
                    break;
                case "lr": loadRelation(advs, cmd);
                    break;
                default: break;
            }
        }
    }
}
