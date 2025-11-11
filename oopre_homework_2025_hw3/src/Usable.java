public interface Usable {
    void use(Adventurer user, Adventurer target); //使用方法

    String getID();

    boolean canUse(Adventurer user);

    String getClassName();
}
