import org.junit.Test;

import static org.junit.Assert.*;

public class ChildTest {

    @Test
    public void subMoney() {
        Child child = new Child(20);
        child.subMoney(5);
        assertEquals(15, child.getMoney());
        // 看看是不是确实的减去了那么多钱？，比如敲错了写成=而不是-=了
    }

    @Test
    public void addOneFruit() {
        Child child = new Child(20);
        child.addOneFruit("apple");
        assertEquals(1, child.getAppleCount());
        //是否加上了？
        child.addOneFruit("banana");
        assertEquals(1, child.getBananaCount());
        //是否加上了？
    }

    @Test
    public void eat() {
        Child child = new Child(20);
        child.eat("banana");
        assertEquals(0, child.getBananaCount());
        //是否没有判断就直接减了？
        child.addOneFruit("banana");
        //为了保证覆盖性，我们可能要测试一下这个意外条件
        child.eat("something");
        //这里的add方法在上面需要已经测试过正确性
        child.eat("banana");
        child.addOneFruit("apple");
        assertEquals(0, child.getBananaCount());
        child.eat("apple");
        assertEquals(0, child.getAppleCount());
    }

    @Test
    public void buyFromStore() {
        Store store = new Store(5, 5);
        Child child1 = new Child(2);
        child1.buyFromStore("banana", store);
        assertEquals(1, child1.getBananaCount());
        assertEquals(0, child1.getMoney());
        Child child2 = new Child(3);
        child2.buyFromStore("apple", store);
        assertEquals(1, child2.getAppleCount());
        assertEquals(0, child2.getMoney());
        Child child3 = new Child(1);
        child3.buyFromStore("apple", store);
        child3.buyFromStore("banana", store);
        assertEquals(1, child3.getMoney());
        assertEquals(0, child3.getAppleCount());
        assertEquals(0, child3.getBananaCount());
    }
}
