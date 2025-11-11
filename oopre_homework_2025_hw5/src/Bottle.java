public class Bottle implements Usable {
    private String id;
    private int effect;
    private boolean isEquipped;

    public Bottle(String id, int effect) {
        this.id = id;
        this.effect = effect;
        this.isEquipped = false;
    }

    public int getEffect() { return this.effect; }

    @Override
    public String getID() {
        return id;
    }

    public void setIsEquipped() {
        isEquipped = true;
    }

    public void setUnequipped() {
        isEquipped = false;
    }

    @Override
    public boolean canUse(Adventurer user) {
        return isEquipped;
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
            String className = this.getClassName();
            switch (className) {
                case "HpBottle": {
                    target.addHitPoint(effect);
                    break;
                }
                case "AtkBottle": {
                    target.addAtk(effect);
                    break;
                }
                case "DefBottle": {
                    target.addDef(effect);
                    break;
                }
                case "ManaBottle": {
                    target.addMana(effect);
                    break;
                }
                default: { ; } //do nothing
            }
        }
        user.removeBottle(this); //用完后删掉
        System.out.printf("%s %d %d %d %d\n",
                target.getID(),
                target.getHitPoint(), target.getAtk(), target.getDef(), target.getMana());
    }
}
