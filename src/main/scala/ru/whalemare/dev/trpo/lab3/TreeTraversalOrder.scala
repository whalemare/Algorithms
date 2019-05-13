package ru.whalemare.dev.trpo.lab3

/**
  * @since 2019
  * @author Anton Vlasov - whalemare
  */
object TreeTraversalOrder extends Enumeration {
  type TreeTraversalOrder = Value
  val PRE_ORDER, IN_ORDER, POST_ORDER, LEVEL_ORDER = Value
}
