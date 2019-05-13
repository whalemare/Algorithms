package ru.whalemare.dev.trpo.lab3

import java.util

import scala.compat.Platform.ConcurrentModificationException

/**
  * @since 2019
  * @author Anton Vlasov - whalemare
  */
class BinaryTreeScala[T <: Comparable[T]] {
  var size: Int = 0
  private var root: Node = null

  private class Node(var left: Node,
                     var right: Node,
                     var data: T)

  def isEmpty: Boolean = this.size == 0

  /**
    * Добавление элемента в бинарное дерево, при условии что его там еще нет
    *
    * @return true если элемент успешно добавлен
    */
  def add(element: T): Boolean = if (contains(element)) false
  else {
    root = add(root, element)
    size += 1
    true
  }

  private def add(node: Node, elem: T): Node = {
    var temp = node
    if (temp == null) {
      temp = new Node(null, null, elem)
    } else if (elem.compareTo(temp.data) < 0) {
      temp.left = add(temp.left, elem)
    } else {
      temp.right = add(temp.right, elem)
    }
    temp
  }

  /**
    * Удалить значение из дерева если оно там есть
    *
    * @param element
    * @return true если значение удалено
    */
  def remove(element: T): Boolean = {
    if (contains(element)) {
      root = remove(root, element)
      size -= 1
      return true
    }
    false
  }

  private def remove(node: Node, elem: T): Node = {
    var temp = node;
    if (temp == null) return null
    val cmp: Int = elem.compareTo(temp.data)
    if (cmp < 0) temp.left = remove(temp.left, elem)
    else if (cmp > 0) temp.right = remove(temp.right, elem)
    else if (temp.left == null) {
      val rightChild: Node = temp.right
      //      temp.data = null
      temp = null
      return rightChild
    }
    else if (temp.right == null) {
      val leftChild: Node = temp.left
      //      temp.data = null
      temp = null
      return leftChild
    }
    else {
      val tmp: Node = findMin(temp.right)
      temp.data = tmp.data
      temp.right = remove(temp.right, tmp.data)
    }
    temp
  }

  private def findMin(node: Node): Node = {
    var temp = node
    while ( {
      temp.left != null
    }) temp = temp.left
    temp
  }

  private def findMax(node: Node): Node = {
    var temp = node
    while ( {
      temp.right != null
    }) temp = temp.right
    temp
  }

  def contains(elem: T): Boolean = contains(root, elem)

  private def contains(node: Node, elem: T): Boolean = {
    if (node == null) return false
    val cmp: Int = elem.compareTo(node.data)
    if (cmp < 0) contains(node.left, elem)
    else if (cmp > 0) contains(node.right, elem)
    else true
  }

  def height: Int = height(root)

  private def height(node: Node): Int = {
    if (node == null) return 0
    Math.max(height(node.left), height(node.right)) + 1
  }

  private def preOrderTraversal: util.Iterator[T] = {
    val expectedNodeCount: Int = size
    val stack: util.Stack[Node] = new util.Stack[Node]
    stack.push(root)
    new util.Iterator[T]() {
      override def hasNext: Boolean = {
        if (expectedNodeCount != size) throw new ConcurrentModificationException
        root != null && !stack.isEmpty
      }

      override

      def next: T = {
        if (expectedNodeCount != size) throw new ConcurrentModificationException
        val node: Node = stack.pop
        if (node.right != null) stack.push(node.right)
        if (node.left != null) stack.push(node.left)
        node.data
      }

      override

      def remove(): Unit = {
        throw new UnsupportedOperationException
      }
    }
  }

  private def inOrderTraversal: util.Iterator[T] = {
    val expectedNodeCount: Int = size
    val stack: util.Stack[Node] = new util.Stack[Node]
    stack.push(root)
    new util.Iterator[T]() {
      private var trav: Node = root

      def hasNext: Boolean = {
        if (expectedNodeCount != size) throw new ConcurrentModificationException
        root != null && !stack.isEmpty
      }

      def next: T = {
        if (expectedNodeCount != size) throw new ConcurrentModificationException
        while ( {
          trav != null && trav.left != null
        }) {
          stack.push(trav.left)
          trav = trav.left
        }
        val node: Node = stack.pop
        if (node.right != null) {
          stack.push(node.right)
          trav = node.right
        }
        node.data
      }

      override def remove(): Unit = {
        throw new UnsupportedOperationException
      }
    }
  }

  private def postOrderTraversal: util.Iterator[T] = {
    val expectedNodeCount: Int = size
    val stack1: util.Stack[Node] = new util.Stack[Node]
    val stack2: util.Stack[Node] = new util.Stack[Node]
    stack1.push(root)
    while ( {
      !stack1.isEmpty
    }) {
      val node: Node = stack1.pop
      if (node != null) {
        stack2.push(node)
        if (node.left != null) stack1.push(node.left)
        if (node.right != null) stack1.push(node.right)
      }
    }
    new util.Iterator[T]() {
      override def hasNext: Boolean = {
        if (expectedNodeCount != size) throw new ConcurrentModificationException
        root != null && !stack2.isEmpty
      }

      override

      def next: T = {
        if (expectedNodeCount != size) throw new ConcurrentModificationException
        stack2.pop.data
      }

      override

      def remove(): Unit = {
        throw new UnsupportedOperationException
      }
    }
  }

  private def levelOrderTraversal: util.Iterator[T] = {
    val expectedNodeCount: Int = size
    val queue: util.Queue[Node] = new util.LinkedList[Node]
    queue.offer(root)
    new util.Iterator[T]() {
      override def hasNext: Boolean = {
        if (expectedNodeCount != size) throw new ConcurrentModificationException
        root != null && !queue.isEmpty
      }

      override

      def next: T = {
        if (expectedNodeCount != size) throw new ConcurrentModificationException
        val node: Node = queue.poll
        if (node.left != null) queue.offer(node.left)
        if (node.right != null) queue.offer(node.right)
        node.data
      }

      override

      def remove(): Unit = {
        throw new UnsupportedOperationException
      }
    }
  }

  private def toString(node: Node): String = {
    var result: String = ""
    if (node == null) return ""
    result += node.data.toString + "\n"
    result += toString(node.left)
    result += toString(node.right)
    result
  }

  override def toString: String = {
    val ts: TreeString = new TreeString
    ts.solve
  }

  private class TreeString {
    private var treeHeight: Int = 0
    private var size: Int = 0
    private var yHeight: Int = 0
    private var xHeight: Int = 0
    private var maxWordLength: Array[Int] = null
    private var preorderHeights: Array[Array[Int]] = null
    private var chars: Array[Array[Char]] = null

    def solve: String = {
      this.treeHeight = height(root)
      this.size = size(root)
      if (size == 0) return "(empty tree)"
      else if (size == 1) return "[" + String.valueOf(root.data) + "]"
      this.maxWordLength = new Array[Int](treeHeight + 1)
      this.yHeight = computeYLength(root)
      this.xHeight = computeXLength
      this.chars = Array.ofDim[Char](yHeight,xHeight)
      fillCharsWithWhitespace()
      traverseAndWrite()
      charArrayToString
    }

    private def fillCharsWithWhitespace(): Unit = {
      var i: Int = 0
      while ( {
        i < yHeight
      }) {
        var j: Int = 0
        while ( {
          j < xHeight
        }) {
          chars(i)(j) = ' '

          {
            j += 1;
            j - 1
          }
        }

        {
          i += 1;
          i - 1
        }
      }
    }

    private def height(n: Node): Int = {
      if (n == null) return -1
      1 + Math.max(height(n.left), height(n.right))
    }

    private def size(n: Node): Int = {
      if (n == null) return 0
      1 + size(n.left) + size(n.right)
    }

    private def traverseAndWrite(): Unit = {
      this.preorderHeights = Array.ofDim[Int](size, 3)
      findPreorderHeights(root, 0)
      // find starting y-point
      val rootsLeftHeight: Int = preorderHeights(0)(1)
      val rootStartY: Int = if (rootsLeftHeight == 0) 0
      else rootsLeftHeight + 1
      traverseAndWrite(root, 0, rootStartY, 0, Array[Int](0))
    }

    private def traverseAndWrite(n: Node, depth: Int, startY: Int, startX: Int, iterator: Array[Int]): Unit = {
      var tempStartX = startX
      val num: Int = preorderHeights({
        iterator(0) += 1;
        iterator(0) - 1
      })(0)
      val nodeString: String = valueString(n, depth)
      writeToCharArray(nodeString, startY, tempStartX)
      tempStartX += nodeString.length
      if (n.left != null) {
        val leftsRightHeight: Int = preorderHeights(iterator(0))(2)
        val leftsInnerHeight: Int = if (leftsRightHeight == 0) 1
        else leftsRightHeight + 2
        val leftStartY: Int = (startY - 1) - leftsInnerHeight
        writeConnectingLines(startY, leftStartY, tempStartX)
        traverseAndWrite(n.left, depth + 1, leftStartY, tempStartX + 5, iterator)
      }
      if (n.right != null) {
        val rightsLeftHeight: Int = preorderHeights(iterator(0))(1)
        val rightsInnerHeight: Int = if (rightsLeftHeight == 0) 1
        else rightsLeftHeight + 2
        val rightStartY: Int = startY + 1 + rightsInnerHeight
        writeConnectingLines(startY, rightStartY, tempStartX)
        traverseAndWrite(n.right, depth + 1, rightStartY, tempStartX + 5, iterator)
      }
    }

    private def writeConnectingLines(startY: Int, endY: Int, startX: Int): Unit = {
      writeToCharArray("--+", startY, startX)
      val diff: Int = endY - startY
      val increment: Int = if (diff > 0) 1
      else -1
      if (diff > 0) {
        var i: Int = startY + 1
        while ( {
          i < endY
        }) {
          writeToCharArray("|", i, startX + 2)

          {
            i += 1;
            i - 1
          }
        }
      }
      else {
        var i: Int = endY + 1
        while ( {
          i < startY
        }) {
          writeToCharArray("|", i, startX + 2)

          {
            i += 1;
            i - 1
          }
        }
      }
      writeToCharArray("+--", endY, startX + 2)
    }

    private def findPreorderHeights(n: Node, h: Int): Array[Int] = if (n.left == null && n.right == null) {
      preorderHeights(h)(0) = 1
      Array[Int](preorderHeights(h)(0), h)
    }
    else if (n.right == null) {
      val resultLeft: Array[Int] = findPreorderHeights(n.left, h + 1)
      preorderHeights(h)(0) = 2 + resultLeft(0)
      preorderHeights(h)(1) = resultLeft(0)
      Array[Int](preorderHeights(h)(0), resultLeft(1))
    }
    else if (n.left == null) {
      val resultRight: Array[Int] = findPreorderHeights(n.right, h + 1)
      preorderHeights(h)(0) = 2 + resultRight(0)
      preorderHeights(h)(2) = resultRight(0)
      Array[Int](preorderHeights(h)(0), resultRight(1))
    }
    else {
      val resultLeft: Array[Int] = findPreorderHeights(n.left, h + 1)
      val resultRight: Array[Int] = findPreorderHeights(n.right, resultLeft(1) + 1)
      preorderHeights(h)(0) = 3 + resultLeft(0) + resultRight(0)
      preorderHeights(h)(1) = resultLeft(0)
      preorderHeights(h)(2) = resultRight(0)
      Array[Int](preorderHeights(h)(0), resultRight(1))
    }

    private def writeToCharArray(line: String, y: Int, x: Int): Unit = {
      if (line.length + x >= xHeight) new Exception("Line was to long to write")
      var i: Int = 0
      while ( {
        i < line.length
      }) {
        chars(y)(x + i) = line.charAt(i)

        {
          i += 1;
          i - 1
        }
      }
    }

    private def charArrayToString: String = {
      var result: String = ""
      var i: Int = 0
      while ( {
        i < chars.length
      }) {
        var j: Int = 0
        while ( {
          j < chars(0).length
        }) {
          result += String.valueOf(chars(i)(j))

          {
            j += 1;
            j - 1
          }
        }
        result += "\n"

        {
          i += 1;
          i - 1
        }
      }
      result.substring(0, result.length - 1) // remove last newline

    }

    private def computeYLength(n: Node): Int = if (n.left == null && n.right == null) 1
    else if (n.right == null) 2 + computeYLength(n.left)
    else if (n.left == null) 2 + computeYLength(n.right)
    else 3 + computeYLength(n.left) + computeYLength(n.right)

    private def computeXLength: Int = {
      computeMaxWordLength(root, 0)
      var sum: Int = 0
      var i: Int = 0
      while ( {
        i < treeHeight
      }) {
        sum += "[".length + maxWordLength(i) + "]".length + "--+--".length

        {
          i += 1;
          i - 1
        }
      }
      sum += "[".length + maxWordLength(treeHeight) + "]".length
      sum
    }

    private def computeMaxWordLength(n: Node, depth: Int): Unit = {
      if (n == null) return
      val nodeStringLength: Int = n.toString.length
      if (nodeStringLength > maxWordLength(depth)) maxWordLength(depth) = nodeStringLength
      computeMaxWordLength(n.left, depth + 1)
      computeMaxWordLength(n.right, depth + 1)
    }

    private def valueString(n: Node, depth: Int): String = {
      val result: String = ""
      val totalCount: Int = maxWordLength(depth) - n.toString.length
      val leftCount: Int = totalCount / 2
      val rightCount: Int = totalCount - leftCount
      val leftPadding: String = repeat(" ", leftCount)
      val rightPadding: String = repeat(" ", rightCount)
      "[" + leftPadding + String.valueOf(n.data) + rightPadding + "]"
    }

    private def repeat(s: String, count: Int): String = {
      var result: String = ""
      var i: Int = 0
      while ( {
        i < count
      }) {
        result += s

        {
          i += 1;
          i - 1
        }
      }
      result
    }
  }

}
