package ru.whalemare.dev.trpo.lab4

import java.util

import ru.whalemare.dev.trpo.lab2.Controller
import ru.whalemare.dev.trpo.lab2.Controller.Renderable
import ru.whalemare.dev.trpo.lab3.HeaderLinkedListScala


class ScalaController extends Controller[util.List[String]] {
  private val linkedDeque = new HeaderLinkedListScala[String]
  private var renderable: Renderable = _


  private def render(value: util.List[String]): Unit = {
    renderable.render(linkedDeque.toString,
      "Пустой список: " + linkedDeque.isEmpty + "\n"
        + "Первый список: " + safe(() => linkedDeque.peekHead) + "\n"
        + "Последний список: " + safe(() => linkedDeque.peekLast) + "\n"
        + "Индекс списка [" + value + "]: " + safe(() => linkedDeque.indexOf(value)))
  }

  override def onClickAddHead(value: util.List[String]): Unit = {
    linkedDeque.addHead(value)
    render(value)
  }

  override def onClickAddLast(value: util.List[String]): Unit = {
    linkedDeque.addLast(value)
    render(value)
  }

  override def onClickRemoveHead(value: util.List[String]): Unit = {
    linkedDeque.removeHead
    render(value)
  }

  override def onClickRemoveLast(value: util.List[String]): Unit = {
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

  def safe(callable: () => util.List[String]): util.List[String] = {
    try
      return callable()
    catch {
      case e: Exception =>
        e.printStackTrace()
    }
    return java.util.Arrays.asList()
  }
}
