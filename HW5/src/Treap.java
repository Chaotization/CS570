import java.util.Random;
import java.util.Stack;
public class Treap <E extends Comparable<E>> {
    private static class Node<E> {
        public E data;
        public int priority;
        public Node<E> left;
        public Node<E> right;

        public Node(E data, int priority) {
            if (data == null) throw new NullPointerException("Not given data");
            this.data = data;
            this.priority = priority;
            this.left = null;
            this.right = null;
        }

        Node<E> rotateRight() {
            Node<E> temp = new Node<>(data, priority);
            if (this.left != null) {
                temp.right = this.right;
                temp.left = this.left.right;
                this.data = this.left.data;
                this.priority = this.left.priority;
                this.left = this.left.left;
                this.right = temp;
            }
            return temp;
        }

        Node<E> rotateLeft() {
            Node<E> temp = new Node<>(data, priority);
            if (this.right != null) {
                temp.left = this.left;
                temp.right = this.right.left;
                this.data = this.right.data;
                this.priority = this.right.priority;
                this.right = this.right.right;
                this.left = temp;
            }
            return this;
        }
    }

    private Random priorityGenerator;
    private Node<E> root;

    public Treap() {
        this.priorityGenerator = new Random();
        root = null;
    }

    public Treap(long seed) {
        this.priorityGenerator = new Random(seed);
        root = null;
    }

    boolean add(E key) {
        return add(key, priorityGenerator.nextInt());
    }

    boolean add(E key, int priority) {
        Node<E> newNode = new Node<>(key, priority);
        Node<E> temproot = root;
        Stack<Node> stack = new Stack<>();
        if (root == null) {
            root = new Node<>(key, priority);
            return true;
        }
        if (find(key)) return false;
        while (temproot != null) {
            stack.push(temproot);
            if (key.compareTo(temproot.data) < 0) {
                temproot = temproot.left;
            } else {
                temproot = temproot.right;
            }
        }
        if (key.compareTo((E) stack.peek().data) < 0) {
            stack.peek().left = newNode;
        } else {
            stack.peek().right = newNode;
        }
        reheap(stack, newNode);
        return true;
    }

    private void reheap(Stack<Node> stack, Node<E> child) {
        Node<E> parent = stack.pop();
        while (parent != null && parent.priority < child.priority) {
            if (child.data.compareTo(parent.data) < 0) {
                parent.rotateRight();
            } else {
                parent.rotateLeft();
            }
            if (!stack.isEmpty()) {
                parent = stack.pop();
            } else {
                parent = null;
            }
        }
    }

    boolean delete(E key) {
        if (!find(key) || this.root == null) {
            return false;
        }
        else{
            root = delete(this.root, key);
            return true;
        }
    }

        private Node<E> delete(Node<E> root, E key){
        if(root == null) return root;
        else if(key.compareTo(root.data) < 0){
                root.right = delete(root.left, key);
        }else if(key.compareTo(root.data) > 0){
            root.right = delete(root.right,key);
        }else if(root.left == null){
            root = root.left;
        }else if(root.right == null){
            root = root.right;
        } else if(root.right.priority < root.left.priority){
            root = root.rotateRight();
            root.right = delete(root.right,key);
        }else{
                root = root.rotateLeft();
                root.left = delete(root.left,key);
        }
        return root;
    }
    private boolean find(Node<E> root, E key) {
        if (root == null) return false;
        else if (key.compareTo(root.data) < 0) return find(root.left, key);
        else if (key.compareTo(root.data) > 0) return find(root.right, key);
        else return true;
    }

    public boolean find(E key) {
        if (key == null) {
            throw new NullPointerException("Key is null");
        }
        return find(root, key);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        return preOderTravesae(root, 1, str);
    }

    public String preOderTravesae(Node<E> node, int depth, StringBuilder str) {
        for (int i = 1; i < depth; i++) {
            str.append(" ");
        }
        if (node == null) str.append("Null \n");
        else {
            str.append("(key = " + node.data + " priority = " + node.priority + ")\n");
            preOderTravesae(node.left, depth + 1, str);
            preOderTravesae(node.right, depth + 1, str);
        }
        return str.toString();
    }
    public static void main(String[] args){
        Treap<Integer> testTree = new Treap<Integer>();
        testTree.add(4,19);
        testTree.add(2,31);
        testTree.add(6,70);
        testTree.add(1,84);
        testTree.add(3,12);
        testTree.add(5,83);
        testTree.add(7,26);
        System.out.println(testTree.toString());
        System.out.println("Find node with key '5'? "+ testTree.find(5));
        System.out.println("Find node with key '15'? "+ testTree.find(15));
        System.out.println("Delete when key '5' exists, so boolean result is: "+ testTree.delete(5));
        System.out.println("Delete when key '15' exists, so boolean result is: "+ testTree.delete(15));
        System.out.println("Find node with key '5' after deleting? "+ testTree.find(5));
//        Treap<Character> testTree2 = new Treap<Character>();
        //Test Case already given
//        testTree2.add('p',99);
//        testTree2.add('g',80);
//        testTree2.add('u',75);
//        testTree2.add('a',60);
//        testTree2.add('j',65);
//        testTree2.add('r',40);
//        testTree2.add('z',47);
//        testTree2.add('w',32);
//        testTree2.add('v',21);
//        testTree2.add('x',25);
//        System.out.println(testTree2.toString());
//        testTree2.add('i',93);
//        System.out.println("Delete when key with 'z' exists and so the boolean result is: "+ testTree2.delete('z') +"\n");
//        System.out.println(testTree2.toString());
    }
}