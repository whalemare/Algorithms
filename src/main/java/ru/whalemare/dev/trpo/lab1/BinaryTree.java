package ru.whalemare.dev.trpo.lab1;

public class BinaryTree<T extends Comparable<T>> {

    private int size = 0;

    private Node root = null;

    private class Node {
        T data;
        Node left, right;

        public Node(Node left, Node right, T data) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    /**
     * Добавление элемента в бинарное дерево, при условии что его там еще нет
     *
     * @return true если элемент успешно добавлен
     */
    public boolean add(T element) {
        if (contains(element)) {
            return false;
        } else {
            root = add(root, element);
            size++;
            return true;
        }
    }

    private Node add(Node node, T elem) {
        if (node == null) {
            node = new Node(null, null, elem);
        } else {
            if (elem.compareTo(node.data) < 0) {
                node.left = add(node.left, elem);
            } else {
                node.right = add(node.right, elem);
            }
        }
        return node;
    }

    /**
     * Удалить значение из дерева если оно там есть
     *
     * @param element
     * @return true если значение удалено
     */
    public boolean remove(T element) {
        if (contains(element)) {
            root = remove(root, element);
            size--;
            return true;
        }
        return false;
    }

    private Node remove(Node node, T elem) {
        if (node == null) return null;

        int cmp = elem.compareTo(node.data);
        if (cmp < 0) {
            node.left = remove(node.left, elem);
        } else if (cmp > 0) {
            node.right = remove(node.right, elem);
        } else {
            if (node.left == null) {

                Node rightChild = node.right;

                node.data = null;
                node = null;

                return rightChild;
            } else if (node.right == null) {

                Node leftChild = node.left;

                node.data = null;
                node = null;

                return leftChild;
            } else {
                Node tmp = findMin(node.right);
                node.data = tmp.data;
                node.right = remove(node.right, tmp.data);
            }
        }

        return node;

    }

    private Node findMin(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    private Node findMax(Node node) {
        while (node.right != null)
            node = node.right;
        return node;
    }

    public boolean contains(T elem) {
        return contains(root, elem);
    }

    private boolean contains(Node node, T elem) {
        if (node == null) return false;

        int cmp = elem.compareTo(node.data);
        if (cmp < 0) return contains(node.left, elem);
        else if (cmp > 0) return contains(node.right, elem);
        else return true;
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public java.util.Iterator<T> traverse(TreeTraversalOrder order) {
        switch (order) {
            case PRE_ORDER:
                return preOrderTraversal();
            case IN_ORDER:
                return inOrderTraversal();
            case POST_ORDER:
                return postOrderTraversal();
            case LEVEL_ORDER:
                return levelOrderTraversal();
            default:
                return null;
        }
    }

    private java.util.Iterator<T> preOrderTraversal() {

        final int expectedNodeCount = size;
        final java.util.Stack<Node> stack = new java.util.Stack<>();
        stack.push(root);

        return new java.util.Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != size) throw new java.util.ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != size) throw new java.util.ConcurrentModificationException();
                Node node = stack.pop();
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private java.util.Iterator<T> inOrderTraversal() {

        final int expectedNodeCount = size;
        final java.util.Stack<Node> stack = new java.util.Stack<>();
        stack.push(root);

        return new java.util.Iterator<T>() {
            Node trav = root;

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != size) throw new java.util.ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != size) throw new java.util.ConcurrentModificationException();
                while (trav != null && trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }

                Node node = stack.pop();
                if (node.right != null) {
                    stack.push(node.right);
                    trav = node.right;
                }

                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private java.util.Iterator<T> postOrderTraversal() {
        final int expectedNodeCount = size;
        final java.util.Stack<Node> stack1 = new java.util.Stack<>();
        final java.util.Stack<Node> stack2 = new java.util.Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            if (node != null) {
                stack2.push(node);
                if (node.left != null) stack1.push(node.left);
                if (node.right != null) stack1.push(node.right);
            }
        }
        return new java.util.Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != size) throw new java.util.ConcurrentModificationException();
                return root != null && !stack2.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != size) throw new java.util.ConcurrentModificationException();
                return stack2.pop().data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private java.util.Iterator<T> levelOrderTraversal() {

        final int expectedNodeCount = size;
        final java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.offer(root);

        return new java.util.Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != size) throw new java.util.ConcurrentModificationException();
                return root != null && !queue.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != size) throw new java.util.ConcurrentModificationException();
                Node node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private String toString(Node node) {
        String result = "";
        if (node == null) return "";
        result += node.data.toString() + "\n";
        result += toString(node.left);
        result += toString(node.right);
        return result;
    }

    @Override
    public String toString() {
        TreeString ts = new TreeString();
        return ts.solve();
    }

    private class TreeString {
        private int treeHeight;
        private int size;
        private int yHeight;
        private int xHeight;
        private int[] maxWordLength;
        private int[][] preorderHeights;
        private char[][] chars;

        private String solve() {
            this.treeHeight = height(root);
            this.size = size(root);
            if (size == 0) {
                return "(empty tree)";
            } else if (size == 1) {
                return "[" + String.valueOf(root.data) + "]";
            }

            this.maxWordLength = new int[treeHeight + 1];
            this.yHeight = computeYLength(root);
            this.xHeight = computeXLength();
            this.chars = new char[yHeight][xHeight];
            fillCharsWithWhitespace();
            traverseAndWrite();
            return charArrayToString();
        }

        private void fillCharsWithWhitespace() {
            for (int i = 0; i < yHeight; i++) {
                for (int j = 0; j < xHeight; j++) {
                    chars[i][j] = ' ';
                }
            }
        }

        private int height(Node n) {
            if (n == null) {
                return -1;
            }
            return 1 + Math.max(height(n.left), height(n.right));
        }

        private int size(Node n) {
            if (n == null) return 0;
            return 1 + size(n.left) + size(n.right);
        }

        private void traverseAndWrite() {
            this.preorderHeights = new int[size][3];
            findPreorderHeights(root, 0);

            // find starting y-point
            int rootsLeftHeight = preorderHeights[0][1];
            int rootStartY = rootsLeftHeight == 0 ? 0 : rootsLeftHeight + 1;

            traverseAndWrite(root, 0, rootStartY, 0, new int[]{0});
        }

        private void traverseAndWrite(Node n, int depth, int startY, int startX, int[] iterator) {
            int num = preorderHeights[iterator[0]++][0];
            String nodeString = valueString(n, depth);
            writeToCharArray(nodeString, startY, startX);
            startX += nodeString.length();
            if (n.left != null) {
                int leftsRightHeight = preorderHeights[iterator[0]][2];
                int leftsInnerHeight = leftsRightHeight == 0 ? 1 : leftsRightHeight + 2;
                int leftStartY = (startY - 1) - leftsInnerHeight;
                writeConnectingLines(startY, leftStartY, startX);
                traverseAndWrite(n.left, depth + 1, leftStartY, startX + 5, iterator);
            }

            if (n.right != null) {
                int rightsLeftHeight = preorderHeights[iterator[0]][1];
                int rightsInnerHeight = rightsLeftHeight == 0 ? 1 : rightsLeftHeight + 2;
                int rightStartY = startY + 1 + rightsInnerHeight;
                writeConnectingLines(startY, rightStartY, startX);
                traverseAndWrite(n.right, depth + 1, rightStartY, startX + 5, iterator);
            }
        }

        private void writeConnectingLines(int startY, int endY, int startX) {
            writeToCharArray("--+", startY, startX);
            int diff = endY - startY;
            int increment = diff > 0 ? 1 : -1;
            if (diff > 0) {
                for (int i = startY + 1; i < endY; i++) {
                    writeToCharArray("|", i, startX + 2);
                }
            } else {
                for (int i = endY + 1; i < startY; i++) {
                    writeToCharArray("|", i, startX + 2);
                }
            }
            writeToCharArray("+--", endY, startX + 2);

        }

        private int[] findPreorderHeights(Node n, int h) {
            if (n.left == null && n.right == null) {
                preorderHeights[h][0] = 1;
                return new int[]{preorderHeights[h][0], h};
            } else if (n.right == null) {
                int[] resultLeft = findPreorderHeights(n.left, h + 1);
                preorderHeights[h][0] = 2 + resultLeft[0];
                preorderHeights[h][1] = resultLeft[0];
                return new int[]{preorderHeights[h][0], resultLeft[1]};
            } else if (n.left == null) {
                int[] resultRight = findPreorderHeights(n.right, h + 1);
                preorderHeights[h][0] = 2 + resultRight[0];
                preorderHeights[h][2] = resultRight[0];
                return new int[]{preorderHeights[h][0], resultRight[1]};
            } else {
                int[] resultLeft = findPreorderHeights(n.left, h + 1);
                int[] resultRight = findPreorderHeights(n.right, resultLeft[1] + 1);
                preorderHeights[h][0] = 3 + resultLeft[0] + resultRight[0];
                preorderHeights[h][1] = resultLeft[0];
                preorderHeights[h][2] = resultRight[0];
                return new int[]{preorderHeights[h][0], resultRight[1]};
            }
        }

        private void writeToCharArray(String line, int y, int x) {
            if (line.length() + x >= xHeight) {
                new Exception("Line was to long to write");
            }

            for (int i = 0; i < line.length(); i++) {
                chars[y][x + i] = line.charAt(i);
            }
        }

        private String charArrayToString() {
            String result = "";
            for (int i = 0; i < chars.length; i++) {
                for (int j = 0; j < chars[0].length; j++) {
                    result += String.valueOf(chars[i][j]);
                }
                result += "\n";
            }
            return result.substring(0, result.length() - 1); // remove last newline
        }

        private int computeYLength(Node n) {
            if (n.left == null && n.right == null) {
                return 1;
            } else if (n.right == null) {
                return 2 + computeYLength(n.left);
            } else if (n.left == null) {
                return 2 + computeYLength(n.right);
            } else {
                return 3 + computeYLength(n.left) + computeYLength(n.right);
            }
        }

        private int computeXLength() {
            computeMaxWordLength(root, 0);
            int sum = 0;
            for (int i = 0; i < treeHeight; i++) {
                sum += "[".length()
                        + maxWordLength[i]
                        + "]".length()
                        + "--+--".length();
            }
            sum +=  "[".length() + maxWordLength[treeHeight] + "]".length();
            return sum;
        }

        private void computeMaxWordLength(Node n, int depth) {
            if (n == null) return;
            int nodeStringLength = n.toString().length();
            if (nodeStringLength > maxWordLength[depth]) {
                maxWordLength[depth] = nodeStringLength;
            }
            computeMaxWordLength(n.left, depth + 1);
            computeMaxWordLength(n.right, depth + 1);
        }

        private String valueString(Node n, int depth) {
            String result = "";
            int totalCount = maxWordLength[depth] - n.toString().length();
            int leftCount = totalCount / 2;
            int rightCount = totalCount - leftCount;
            String leftPadding = repeat(" ", leftCount);
            String rightPadding = repeat(" ", rightCount);

            return "[" + leftPadding + String.valueOf(n.data) + rightPadding + "]";
        }

        private String repeat(String s, int count) {
            String result = "";
            for (int i = 0; i < count; i++) {
                result += s;
            }
            return result;
        }
    }

}









