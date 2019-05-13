package ru.whalemare.dev.trpo.lab4

import ru.whalemare.dev.trpo.lab1.BinaryTree

/**
  * @since 2019
  * @author Anton Vlasov - whalemare
  */
class Controller {

  private val tree = new BinaryTree[Integer]

  def onClickAdd(value: Integer, callback: Callback): Unit = {
    tree.add(value)
    callback.call(tree.toString)
  }

  def onClickRemove(value: Integer, callback: Callback): Unit = {
    tree.remove(value)
    callback.call(tree.toString)
  }

}
