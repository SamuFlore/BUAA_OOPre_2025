class Player {
    private Pet pet;
    private int exp;
    private final int feedExp = 5;
    private final int playExp = 7;

    public Player(Pet pet) {
        this.pet = pet;
        exp = 0;
    }

    public void feedPet(String foodName) {
        pet.eat(foodName);
        exp += feedExp;
    }

    public void playWithPet() {
        pet.play();
        exp += playExp;
    }

    public void checkPetStatus() {
        pet.printStatus();
    }

    public void printStatus() {
        System.out.println("Final Player Exp: " + exp);
    }

    // Junit测试所需的getter方法
    public Pet getPet() {
        return pet;
    }

    public int getExp() {
        return exp;
    }
}