import java.util.Scanner;

public class Puppy
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        int x = sc.nextInt();
        String str = null;
        str = String.format("Ans = %d", i + x);
        System.out.println(str);
        Phone apple = new Phone();
        apple.playGame();
        apple.call();
        apple.setBrand("Apple");
        apple.setColor("Red");
        System.out.println(apple.getColor());
        System.out.println(apple.getBrand());
    }
}