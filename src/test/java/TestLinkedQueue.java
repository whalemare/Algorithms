import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.whalemare.dev.trpo.lab1.LinkedDeque;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
@RunWith(JUnit4.class)
public class TestLinkedQueue {

    private LinkedDeque<Integer> queue;

    @Before
    public void setup() {
        queue = new LinkedDeque<>();
    }

    @Test
    public void items_should_inserted_to_head() {
        queue.addLast(0); // head
        queue.addLast(1); // middle
        queue.addLast(2); // last
        Assert.assertEquals(2, (int) queue.peekLast());
    }

    @Test
    public void items_should_inserted_to_last() {
        // queue.addLast(-1) should be here
        queue.addLast(0); // head
        queue.addLast(1); // middle
        queue.addLast(2); // last
        queue.addHead(-1);
        Assert.assertEquals(-1, (int) queue.peekHead());
    }

    @Test
    public void items_should_be_deleted_head() {
        queue.addLast(0); // head
        queue.addLast(1); // middle
        queue.addLast(2); // last
        Assert.assertEquals(0, (int) queue.removeHead());
        Assert.assertEquals(1, (int) queue.peekHead());
        Assert.assertEquals(2, (int) queue.peekLast());
    }

    @Test
    public void items_should_be_deleted_last() {
        queue.addLast(0); // head
        queue.addLast(1); // middle
        queue.addLast(2); // last
        Assert.assertEquals(2, (int) queue.removeLast());
        Assert.assertEquals(0, (int) queue.peekHead());
        Assert.assertEquals(1, (int) queue.peekLast());
    }

    @Test
    public void items_should_be_iterated() {
        queue.addLast(0); // head
        queue.addLast(1); // middle
        queue.addLast(2); // last
        for (Integer value : queue) {
            System.out.println("Iterable value: " + value);
        }
    }

    @Test
    public void items_should_be_indexed() {
        queue.addLast(9); // 0
        queue.addLast(0); // 1
        queue.addLast(1); // 2
        queue.addLast(5); // 3
        queue.addLast(6); // 4
        Assert.assertEquals(3, queue.indexOf(5));
    }


}
