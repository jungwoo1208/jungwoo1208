import java.io.BufferedReader;
import java.io.FileReader;

class Node<Key extends Comparable<Key>,Value>{
    private Key id;
    private Value name;
    private Node left,right;

    public Node(Key newId, Value newName){
        id =newId;
        name = newName;
        left =right=null;
    }
    public Key getKey(){
        return id;
    }
    public Value getValue(){
        return name;
    }
    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
    public void setKey(Key newId){
        id=newId;
    }
    public void setValue(Value newName){
        name=newName;
    }
    public void setLeft(Node newLeft){
        left=newLeft;
    }
    public void setRight(Node newRight){
        right=newRight;
    }
}
class BST<Key extends Comparable<Key>,Value>{
    private Node root;
    public BST(){
        this.root=null;
    }
    public Key max(){
        if(root==null) {
            System.out.println("empty 트리");
            return null;
        }
        Node m=max(root);
        return (Key) m.getKey();
    }
    public Node max(Node n){
        if(n.getRight()!=null){
            return max(n.getRight());
        }else if(n.getLeft()!=null){
            return max(n.getLeft());
        }else {
            return n;
        }
    }
    public void deleteMax() {
        if (root == null) {
            System.out.println("empty 트리");
        } else {
            root = deleteMax(root);
        }
    }
    public Node deleteMax(Node n) {
        if (n.getRight() != null) {
            n.setRight(deleteMax(n.getRight()));
            return n;
        } else {
            return n.getLeft();
        }
    }

    public void delete_mod(Key k) {
        root = delete_mod(root, k);
    }

    public Node delete_mod(Node n, Key k) {
        if (n == null) {
            return null;
        }
        int cmp = k.compareTo((Key) n.getKey());
        if (cmp < 0) {
            n.setLeft(delete_mod(n.getLeft(), k));
        } else if (cmp > 0) {
            n.setRight(delete_mod(n.getRight(), k));
        } else {
            if (n.getLeft() == null)
                return n.getRight();
            if (n.getRight() == null)
                return n.getLeft();
            Node t = n;
            n = max(t.getLeft());
            n.setLeft(deleteMax(t.getLeft()));
            n.setRight(t.getRight());
        }
        return n;
    }
    public void put_mod(Key k,Value v){
        if(root==null){
            root=new Node(k,v);
        }else {
            Node next=root;
            while(true){
                if(k.compareTo((Key) next.getKey())<0){
                    if(next.getLeft()==null){
                        next.setLeft(new Node(k,v));
                        break;
                    }else {
                        next=next.getLeft();
                    }
                }else {
                    if(next.getRight()==null){
                        next.setRight(new Node(k,v));
                        break;
                    }else {
                        next=next.getRight();
                    }
                }
            }
        }
    }
    public void inorder(Node n){
        if(n!=null){
            inorder(n.getLeft());
            System.out.print(n.getKey()+" ");
            inorder(n.getRight());
        }
    }

    public Node getRoot() {
        return root;
    }
}
public class MainJava {
    public static void main(String[]args)throws Exception{
        BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\gjw19\\Downloads\\week09_input.txt"));
        String line= br.readLine();
        while(line !=null){
            String[]keys =line.split(" ");
            BST<Integer,Integer> bst=new BST<>();
            for(String key: keys){
                bst.put_mod(Integer.parseInt(key),1);
            }
            bst.inorder(bst.getRoot());
            System.out.println();
            bst.deleteMax();
            bst.inorder(bst.getRoot());
            System.out.println();

            line= br.readLine();
            String[]deleteKeys = line.split(" ");
            for(String key:deleteKeys){
                bst.delete_mod(Integer.parseInt(key));
            }
            bst.inorder(bst.getRoot());
            System.out.println();
            line= br.readLine();
        }
    }
}
