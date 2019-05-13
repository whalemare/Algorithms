import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.whalemare.dev.trpo.lab1.BinaryTree;

import java.util.Random;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
@RunWith(JUnit4.class)
public class TestBinaryTree {

    BinaryTree<Integer> tree;

    @Before
    public void setup() {
        tree = new BinaryTree<>();
    }

    @Test
    public void items_should_inserted() {
        tree.add(0);
        tree.add(1);
        tree.add(2);
        Assert.assertEquals(3, tree.size());
    }

    @Test
    public void items_should_deleted() {
        tree.add(0);
        tree.add(1);
        tree.add(2);
        Assert.assertEquals(true, tree.remove(0));
        Assert.assertEquals(true, tree.remove(1));
        Assert.assertEquals(true, tree.remove(2));
    }

    @Test
    public void items_should_contains_element() {
        tree.add(0);
        tree.add(1);
        tree.add(2);

        Assert.assertEquals(true, tree.contains(2));
        Assert.assertEquals(false, tree.contains(777));
    }

    @Test
    public void items_should_is_empty() {
        Assert.assertEquals(true, tree.isEmpty());
        tree.add(0);
        Assert.assertEquals(false, tree.isEmpty());
    }

    @Test
    public void items_should_be_printable() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            tree.add(random.nextInt(500));
        }
        System.out.println(tree.toString());
    }
}
