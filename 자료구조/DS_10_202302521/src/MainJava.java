import java.io.*;
class Node<Key extends Comparable<Key>, Value> {
    private Key id;
    private Value name;
    private Node<Key, Value> left, right;
    private int height;
    public Node(Key id, Value name,int height) {
        this.id = id;
        this.name = name;
        this.height = height;
        left = right = null;
    }
    public Key getKey() {
        return id;
    }
    public Value getValue() {
        return name;
    }
    public Node<Key, Value> getLeft() {
        return left;
    }
    public Node<Key, Value> getRight() {
        return right;
    }
    public void setKey(Key id) {
        this.id=id;
    }
    public void setValue(Value name) {
        this.name=name;
    }
    public void setLeft(Node<Key, Value> left) {
        this.left=left;
    }
    public void setRight(Node<Key, Value> right) {
        this.right=right;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
}
class AVL<Key extends Comparable<Key>, Value> {
    private Node<Key, Value> root;
    public AVL() {
        this.root = null;
    }
    private int height(Node<Key, Value> n) {
        if (n == null) {
            return 0;
        } else {
            return n.getHeight();
        }
    }
    private int bf(Node<Key, Value> n) {
        if (n == null) {
            return 0;
        } else {
            return height(n.getLeft()) - height(n.getRight());
        }
    }
    public Node<Key, Value> max(Node<Key, Value> n) {
        while (n.getRight() != null) {
            n = n.getRight();
        }
        return n;
    }
    public Node<Key, Value> deleteMax(Node<Key, Value> n) {
        if (n.getRight() == null) {
            return n.getLeft();
        }
        n.setRight(deleteMax(n.getRight()));
        return balance(n);
    }
    public void delete(Key k) {
        root = delete(root, k);
    }
    private Node<Key, Value> balance(Node<Key, Value> n) {
        if (n == null) return null;
        int balanceFactor = bf(n);
        if (balanceFactor > 1) {
            if (bf(n.getLeft()) >= 0) {
                n = rotateRight(n);  // LL
            } else {
                n.setLeft(rotateLeft(n.getLeft()));  // LR
                n = rotateRight(n);
            }
        } else if (balanceFactor < -1) {
            if (bf(n.getRight()) <= 0) {
                n = rotateLeft(n);  // RR
            } else {
                n.setRight(rotateRight(n.getRight()));  // RL
                n = rotateLeft(n);
            }
        }
        n.setHeight(Math.max(height(n.getLeft()), height(n.getRight())) + 1);
        return n;
    }
    private Node<Key, Value> rotateRight(Node<Key, Value> n) {
        Node<Key, Value> x = n.getLeft();
        Node<Key, Value> r = x.getRight();
        x.setRight(n);
        n.setLeft(r);
        n.setHeight(Math.max(height(n.getLeft()), height(n.getRight())) + 1);
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
        return x;
    }
    private Node<Key, Value> rotateLeft(Node<Key, Value> n) {
        Node<Key, Value> x = n.getRight();
        Node<Key, Value> l = x.getLeft();
        x.setLeft(n);
        n.setRight(l);
        n.setHeight(Math.max(height(n.getLeft()), height(n.getRight())) + 1);
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
        return x;
    }
    public Node<Key, Value> delete(Node<Key, Value> n, Key k) {
        if (n == null) {
            return null;
        }
        int cmp = k.compareTo(n.getKey());
        if (cmp < 0) {
            n.setLeft(delete(n.getLeft(), k));
        } else if (cmp > 0) {
            n.setRight(delete(n.getRight(), k));
        } else {
            if (n.getLeft() == null)
                return n.getRight();
            if (n.getRight() == null)
                return n.getLeft();
            Node<Key, Value> t = n;
            n = max(t.getLeft());
            n.setLeft(deleteMax(t.getLeft()));
            n.setRight(t.getRight());
        }
        return balance(n);
    }
    public void put(Key k, Value v) {
        root = put(root, k, v);
    }
    private Node<Key, Value> put(Node<Key, Value> n, Key k, Value v) {
        if (n == null) {
            return new Node<>(k, v,1);
        }
        int cmp = k.compareTo(n.getKey());
        if (cmp < 0) {
            n.setLeft(put(n.getLeft(), k, v));
        } else if (cmp > 0) {
            n.setRight(put(n.getRight(), k, v));
        } else {
            n.setValue(v);
        }
        return balance(n);
    }
    public void inorderMod(Node<Key, Value> n) {
        if (n != null) {
            inorderMod(n.getLeft());
            System.out.print("(" + n.getKey() + ", " + bf(n) + ") ");
            inorderMod(n.getRight());
        }
    }
    public Node<Key, Value> getRoot() {
        return root;
    }
}
public class MainJava {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\gjw19\\Downloads\\week10_input.txt"));
        String line = br.readLine();
        while (line != null) {
            AVL<Integer, Integer> tree = new AVL<>();
            String[] putKeys = line.split(" ");
            line = br.readLine();
            String[] delKeys = line.split(" ");
            for (String putKey : putKeys) {
                int key = Integer.parseInt(putKey);
                tree.put(key, 0);
            }
            tree.inorderMod(tree.getRoot());
            System.out.println();
            for (String delKey : delKeys) {
                int key = Integer.parseInt(delKey);
                tree.delete(key);
            }
            tree.inorderMod(tree.getRoot());
            line = br.readLine();
            System.out.println();
        }
        br.close();
    }
}