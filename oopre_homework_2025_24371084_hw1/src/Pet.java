public class Pet {
    private String name;
    private int hunger;
    private int happiness;
    private int health;
    private int biscuitCount;
    private final int biscuitHungerReduction = 10;
    private final int fruitHungerReduction = 8;
    private final int maxBiscuitCount = 3;

    public Pet(String name, int hunger, int happiness, int health) {
        this.name = name;
        this.hunger = hunger;
        this.happiness = happiness;
        this.health = health;
        this.biscuitCount = 0;
    }

    public void eat(String foodName) {
        if (foodName.equals("biscuit")) {
            biscuitCount++;
            if (biscuitCount > maxBiscuitCount) {
                health -= 5;
            }
            hunger -= biscuitHungerReduction;
        } else {
            hunger -= fruitHungerReduction;
        }
        hunger = Math.max(hunger, 0);
    }

    public void play() {
        happiness += 5;
        if (happiness > 100) {
            happiness = 100;
        }
    }

    public void printStatus() {
        System.out.println(name + ":");
        System.out.println("Hunger: " + hunger);
        System.out.println("Happiness: " + happiness);
        System.out.println("Health: " + health);
    }

    // JUnit测试所需的getter方法
    public String getName() {
        return name;
    }

    public int getHunger() {
        return hunger;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getHealth() {
        return health;
    }

    public int getBiscuitCount() {
        return biscuitCount;
    }
}
