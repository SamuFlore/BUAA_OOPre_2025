import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> inputInfo = new ArrayList<>(); // 解析后的输入将会存进该容器中, 类似于c语言的二维数组
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim()); // 读取行数
        for (int i = 0; i < n; i++) {
            String nextLine = scanner.nextLine(); // 读取本行指令
            String[] strings = nextLine.trim().split(" +"); // 按空格对行进行分割
            inputInfo.add(new ArrayList<>(Arrays.asList(strings))); // 将指令分割后的各个部分存进容器中
        }


        ArrayList<Adventurer> adventurers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (inputInfo.get(i).get(0).equals("aa")) {
                adventurers.add(new Adventurer(inputInfo.get(i).get(1)));
            }
            else if (inputInfo.get(i).get(0).equals("ab")) {
                for (int j = 0; j < adventurers.size(); j++) {
                    if (inputInfo.get(i).get(1).equals(adventurers.get(j).getID())) {
                        Bottle bttle = new Bottle(inputInfo.get(i).get(2), inputInfo.get(i).get(3));
                        adventurers.get(j).addBottle(bttle);
                        break;
                    }
                }
            }
            else if (inputInfo.get(i).get(0).equals("ae")) {
                for (int j = 0; j < adventurers.size(); j++) {
                    if (inputInfo.get(i).get(1).equals(adventurers.get(j).getID())) {
                        Equipment equipment = new Equipment(inputInfo.get(i).get(2));
                        adventurers.get(j).addEquipment(equipment);
                        break;
                    }
                }
            }
            else if (inputInfo.get(i).get(0).equals("rb")) {
                for (int j = 0; j < adventurers.size(); j++) {
                    if (inputInfo.get(i).get(1).equals(adventurers.get(j).getID())) {
                        adventurers.get(j).removeBottle(inputInfo.get(i).get(2));
                        break;
                    }
                }
            }
            else if (inputInfo.get(i).get(0).equals("re")) {
                for (int j = 0; j < adventurers.size(); j++) {
                    if (inputInfo.get(i).get(1).equals(adventurers.get(j).getID())) {
                        adventurers.get(j).removeEquipment(inputInfo.get(i).get(2));
                        break;
                    }
                }
            }
        }
    }
}
