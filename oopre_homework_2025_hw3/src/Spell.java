public class Spell implements Usable {
    private String id;
    private int manaCost;
    private int power;

    public Spell(String id, int manaCost, int power) {
        this.id = id;
        this.manaCost = manaCost;
        this.power = power;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public boolean canUse(Adventurer user) {
        return (user.getMana() >= manaCost);
    }

    @Override
    public String getClassName() {
        return this.getClass().getName();
    }

    @Override
    public void use(Adventurer user, Adventurer target) {
        if (!canUse(user)) {
            System.out.printf("%s fail to use %s\n", user.getID(), this.id);
            return;
        }
        else {
            user.addMana(-manaCost); //扣蓝
            String className = this.getClassName();
            switch (className) {
                case "AttackSpell": {
                    target.underAttack(power);
                    break;
                }
                case "HealSpell": {
                    target.addHitPoint(power);
                    break;
                }
                default: { ; } //why must default???
            }
        }
        System.out.printf("%s %d %d %d %d\n",
                target.getID(),
                target.getHitPoint(), target.getAtk(), target.getDef(), target.getMana());
    }
}