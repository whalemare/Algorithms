import java.util.Random

import org.junit.{Assert, Before, Test}
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import ru.whalemare.dev.trpo.lab3.BinaryTreeScala

/**
  * @since 2019
  * @author Anton Vlasov - whalemare
  */
@RunWith(classOf[JUnitRunner])
class TestBinaryTreeScala extends FlatSpec {

  var tree: BinaryTreeScala[Integer] = null

  "items" should "be inserted" in {
    tree = new BinaryTreeScala[Integer]
    tree.add(0)
    tree.add(1)
    tree.add(2)
    Assert.assertEquals(3, tree.size)
  }

  "items" should "be deleted" in {
    tree = new BinaryTreeScala[Integer]
    tree.add(0)
    tree.add(1)
    tree.add(2)
    Assert.assertEquals(true, tree.remove(0))
    Assert.assertEquals(true, tree.remove(1))
    Assert.assertEquals(true, tree.remove(2))
  }

  "items" should "contain elements" in {
    tree = new BinaryTreeScala[Integer]
    tree.add(0)
    tree.add(1)
    tree.add(2)
    Assert.assertEquals(true, tree.contains(2))
    Assert.assertEquals(false, tree.contains(777))
  }

  "items" should "be empty " in {
    tree = new BinaryTreeScala[Integer]
    Assert.assertEquals(true, tree.isEmpty)
    tree.add(0)
    Assert.assertEquals(false, tree.isEmpty)
  }

  "items" should "be printable" in {
    tree = new BinaryTreeScala[Integer]
    val random = new Random
    var i = 0
    while ( {
      i < 10
    }) {
      tree.add(random.nextInt(500))
        i += 1
        i - 1
    }
    System.out.println(tree.toString)
  }
}
