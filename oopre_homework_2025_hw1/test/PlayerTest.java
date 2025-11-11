import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class PlayerTest {

    @Test
    public void testConstructor() {
        // 测试Player构造函数是否正确初始化
        Pet pet = new Pet("Fluffy", 50, 60, 80);
        Player player = new Player(pet);

        // 验证构造函数是否正确设置了宠物引用和初始经验值
        assertSame(pet, player.getPet());
        assertEquals(0, player.getExp());
    }

    @Test
    public void testFeedPetWithBiscuit() {
        // 测试喂食饼干功能
        Pet pet = new Pet("Buddy", 50, 60, 80);
        Player player = new Player(pet);

        player.feedPet("biscuit");

        assertEquals(40, pet.getHunger()); // 宠物饥饿值应该减少
        assertEquals(5, player.getExp()); // 玩家经验值应该增加5
        assertEquals(1, pet.getBiscuitCount()); // 饼干计数应该增加
    }

    @Test
    public void testFeedPetWithFruit() {
        // 测试喂食水果功能
        Pet pet = new Pet("Buddy", 50, 60, 80);
        Player player = new Player(pet);

        player.feedPet("something");

        assertEquals(42, pet.getHunger()); // 宠物饥饿值应该减少8
        assertEquals(5, player.getExp()); // 玩家经验值应该增加5
        assertEquals(0, pet.getBiscuitCount()); // 饼干计数不应该改变
    }

    @Test
    public void testPlayWithPet() {
        // 测试与宠物玩耍功能
        Pet pet = new Pet("Buddy", 50, 60, 80);
        Player player = new Player(pet);

        player.playWithPet();

        assertEquals(65, pet.getHappiness()); // 宠物快乐值应该增加5
        assertEquals(7, player.getExp()); // 玩家经验值应该增加7
        assertEquals(50, pet.getHunger()); // 宠物饥饿值不应该改变
        assertEquals(80, pet.getHealth()); // 宠物健康值不应该改变
    }

    @Test
    public void testCheckPetStatus() {
        // 测试检查宠物状态不会改变任何状态
        Pet pet = new Pet("Observer", 50, 60, 80);
        Player player = new Player(pet);

        // 记录初始状态
        final int initialHunger = pet.getHunger();
        final int initialHappiness = pet.getHappiness();
        final int initialHealth = pet.getHealth();
        final int initialExp = player.getExp();

        player.checkPetStatus(); // 这个方法只是打印状态

        // 确认checkPetStatus方法是纯查询操作，不改变状态
        assertEquals(initialHunger, pet.getHunger());
        assertEquals(initialHappiness, pet.getHappiness());
        assertEquals(initialHealth, pet.getHealth());
        assertEquals(initialExp, player.getExp());
    }
}
