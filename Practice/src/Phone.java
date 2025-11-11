public class Phone {
    private String brand;
    private String color;

    public void call() {
        System.out.println("Calling...");
    }

    public void playGame() {
        System.out.println("Playing...");
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
