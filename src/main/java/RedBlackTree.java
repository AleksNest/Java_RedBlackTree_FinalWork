import java.awt.*;

public class RedBlackTree {
    private Node root;                                                  // нода корень дерева


    private class Node {
        private int value;
        private Color color;
        private Node leftChild;
        private Node rightChild;


        @Override
        public String toString() {
            return  "Node{" + "Value=" + value + ", color=" + color + '}';
        }
    }
    private enum Color {
        RED,
        BLACK
    }

    public boolean add (int value) {        // метод добавления root  Node
        if (root != null){
            boolean result = addNode (root, value);
            root = rebalance(root);
            root.color = Color.BLACK;
            return result;
        } else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }


    private boolean addNode(Node node, int value) {                      // метод добавления новых нод (узлов), boolean = false - если не удалось создать новую ноду
        if (node.value == value) {
            return false;
        } else {
            if (node.value > value) {                                   // условие для левого ребенка
                if (node.leftChild != null) {                           // левый ребенок существует
                    boolean result = addNode(node.leftChild, value);    // поиск места для добавления в глубь дерева левого ребенка
                    node.leftChild = rebalance(node.leftChild);        // поиск места для добавления в глубь дерева левого ребенка
                    return result;
                } else {
                    node.leftChild = new Node();                        // создание новой ноды с красным цветом
                    node.leftChild.color = Color.RED;
                    node.leftChild.value = value;
                    return true;
                }
            } else {                                                     // аналогично условия  для правого ребенка
                if (node.rightChild != null) {                           // правый ребенок существует
                    boolean result = addNode(node.rightChild, value);    // поиск места для добавления в глубь дерева правого ребенка
                    node.rightChild = rebalance(node.rightChild);       // поиск места для добавления в глубь дерева левого ребенка
                    return result;
                } else {
                    node.rightChild = new Node();
                    node.rightChild.color = Color.RED;
                    node.rightChild.value = value;
                    return true;
                }
            }
        }
    }

    private Node rebalance(Node node) {                             // метод ребалансировки дерева
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.rightChild != null && result.rightChild.color == Color.RED && (result.leftChild == null || result.leftChild.color == Color.BLACK)) { // есть правый красный ребенок и черный левый ребенок  и
                needRebalance = true;
                result = rightSwap(result);             // производится правый поворот и ребалансировка должна быть выполнена еще раз
            }
            if (result.leftChild != null && result.leftChild.color == Color.RED && result.leftChild.leftChild != null && result.leftChild.leftChild.color == Color.RED) {    // есть левый красный ребенок и у левого рпебенка есть красный левый ребенок (две урасные ноды подряд)
                needRebalance = true;
                result = leftSwap(result);              // производится правый поворот, при котором разделяем левого ребенка от его левог родителя
            }
            if (result.leftChild != null && result.leftChild.color == Color.RED && result.rightChild != null && result.rightChild.color == Color.RED) {  // левый и правый ребенок красные
                needRebalance = true;
                colorSwap(result);
            }
        }
        while (needRebalance);
        return  result;
    }




    private Node leftSwap (Node node) {                             // метод левостороннего перевода
        Node leftChild = node.leftChild;
        Node betweenChild = leftChild.rightChild;
        leftChild.rightChild = node;                                // левосторонний ребенок становится родителем
        node.leftChild = betweenChild;                              // левосторонний ребенок = значению между красной нодой и родительской нодой
        leftChild.color = node.color;                               // леворсторонний ребенок получает цвет родителя
        node.color = Color.RED;                                     // родитель становится красным
        return leftChild;
    }

    private Node rightSwap (Node node) {                             // метод правостороннего перевода
        Node rightChild = node.rightChild;
        Node betweenChild = rightChild.leftChild;
        rightChild.leftChild = node;
        node.rightChild = betweenChild;
        rightChild.color = node.color;
        node.color = Color.RED;
        return rightChild;
    }

    private void colorSwap (Node node) {                            // метод смены цвета для ноды
        node.rightChild.color = Color.BLACK;
        node.leftChild.color = Color.BLACK;
        node.color = Color.RED;
    }


}
