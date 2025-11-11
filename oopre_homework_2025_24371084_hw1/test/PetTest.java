import org.junit.Test;
import static org.junit.Assert.*;

public class PetTest {

    @Test
    public void testConstructor() {
        // 看看Pet构造函数是否正确初始化
        Pet pet = new Pet("Fluffy", 50, 60, 80);

        // 验证构造函数是否正确设置了所有初始值，饼干计数应该为0
        assertEquals("Fluffy", pet.getName());
        assertEquals(50, pet.getHunger());
        assertEquals(60, pet.getHappiness());
        assertEquals(80, pet.getHealth());
        assertEquals(0, pet.getBiscuitCount());

    }

    @Test
    public void testEatBiscuit() {
        // 测试宠物吃饼干的功能
        Pet pet = new Pet("Buddy", 50, 60, 80);

        pet.eat("biscuit");

        assertEquals(40, pet.getHunger()); // 50 - 10 = 40
        assertEquals(1, pet.getBiscuitCount()); // 吃了一个饼干，0->1
        assertEquals(80, pet.getHealth()); // 健康值不应该改变
        // 第一次吃饼干应该减少10点饥饿值，增加饼干计数，健康值不变
    }

    @Test
    public void testEatFruit() {
        // 为覆盖else分支，我们还需要测试宠物吃水果的功能
        Pet pet = new Pet("Buddy", 50, 60, 80);

        pet.eat("something"); // 任何非"biscuit"的食物都被视为水果

        assertEquals(42, pet.getHunger()); // 50 - 8 = 42
        assertEquals(0, pet.getBiscuitCount()); // 饼干计数不应该改变
        assertEquals(80, pet.getHealth()); // 健康值不应该改变
        // 吃水果应该减少8点饥饿值，不影响饼干计数和健康值
    }

    @Test
    public void testEatTooManyBiscuits() {
        // 测试吃太多饼干时，饥饿值不小于0且健康值减少
        Pet pet = new Pet("Buddy", 50, 60, 80);

        // 连续吃6个饼干
        pet.eat("biscuit");
        pet.eat("biscuit");
        pet.eat("biscuit");
        pet.eat("biscuit");
        pet.eat("biscuit");
        pet.eat("biscuit");

        assertEquals(0, pet.getHunger()); // 50 - 10*6 = -10, max(0, -10) = 0
        assertEquals(6, pet.getBiscuitCount()); // 吃了6个饼干
        assertEquals(65, pet.getHealth()); // 健康值应该减少15点，80 - 5*3 = 65
    }

    @Test
    public void testPlay() {
        // 测试宠物玩耍功能
        Pet pet = new Pet("Buddy", 50, 60, 80);

        pet.play();

        assertEquals(65, pet.getHappiness()); // 60 + 5 = 65
        assertEquals(50, pet.getHunger()); // 饥饿值不应该改变
        assertEquals(80, pet.getHealth()); // 健康值不应该改变
        // 玩耍应该只影响快乐值，增加5点
    }

    @Test
    public void testPlayHappinessCapAt100() {
        // 测试快乐值上限为100的边界条件
        Pet pet = new Pet("Buddy", 50, 98, 80);

        pet.play(); // 98 + 5 = 103，但快乐值上限为100

        assertEquals(100, pet.getHappiness());
        // 验证快乐值的上界保护，确保不会超过100
    }
}
