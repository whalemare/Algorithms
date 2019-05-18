package ru.whalemare.dev.trpo.lab4

import ru.whalemare.dev.trpo.lab2.Controller
import ru.whalemare.dev.trpo.lab2.Controller.Renderable
import ru.whalemare.dev.trpo.lab3.LinkedDequeScala


class ScalaController extends Controller {
  private val linkedDeque = new LinkedDequeScala[Integer]
  private var renderable: Renderable = _


  private def render(value: Int): Unit = {
    renderable.render(linkedDeque.toString,
      "Список пуст: " + linkedDeque.isEmpty + "\n"
        + "Начальный элемент: " + safe(() => linkedDeque.peekHead) + "\n"
        + "Конечный элемент: " + safe(() => linkedDeque.peekLast) + "\n"
        + "Индекс элемента [" + value + "]: " + safe(() => linkedDeque.indexOf(value)))
  }

  override def onClickAddHead(value: Int): Unit = {
    linkedDeque.addHead(value)
    render(value)
  }

  override def onClickAddLast(value: Int): Unit = {
    linkedDeque.addLast(value)
    render(value)
  }

  override def onClickRemoveHead(value: Int): Unit = {
    linkedDeque.removeHead
    render(value)
  }

  override def onClickRemoveLast(value: Int): Unit = {
    linkedDeque.removeLast
    render(value)
  }

  override def onRender(renderable: Controller.Renderable): Unit = {
    this.renderable = renderable
  }

  def safe(callable: () => Int): Int = {
    try
      return callable()
    catch {
      case e: Exception =>
        e.printStackTrace()
    }
    return -1
  }
}
