import java.io.*;
import java.util.*;

class Node <E>{
    private E item;
    private Node<E> next;
    public Node(E item, Node<E> next){
        this.item = item;
        this.next = next;
    }
    E getItem(){
        return item;
    }
    Node<E> getNext(){
        return next;
    }
    void setItem(E newItem){
        item = newItem;
    }
    void setNext(Node<E> newNext){
        next = newNext;
    }
}
class SinglyLinkedList<E> {
    private Node<E> head;


    public SinglyLinkedList() {
        head = null;
    }
    public void insertFirst(E item) {
        head = new Node<E>(item, head);
    }

    public void insertLast(E item) {
        Node<E> p = head;
        while (p.getNext() != null) {
            p = p.getNext();
        }
        p.setNext(new Node<E>(item, null));
    }

    public void printAll() {
        Node<E> p = head;
        while (p != null) {
            System.out.print(p.getItem() + " ");
            p = p.getNext();
        }
    }

    public void reverse() {
        Node<E> p = head;
        Node<E> q = null;
        Node<E> r = null;
        while (p != null) {
            r = q;
            q = p;
            p = p.getNext();
            q.setNext(r);
        }
        head = q;
    }
}
public class MainJava {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\gjw19\\IdeaProjects\\java\\Test\\src\\text.txt\\")));
        String line = br.readLine();
        while (line != null) {
            StringTokenizer st = new StringTokenizer(line);
            SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();
            int len=st.countTokens();
            linkedList.insertFirst(Integer.parseInt(st.nextToken()));
            for(int i=1; i<len; i++){
                linkedList.insertLast(Integer.parseInt(st.nextToken()));
            }
            linkedList.printAll();
            System.out.println();
            linkedList.reverse();
            linkedList.printAll();
            System.out.println();
            line = br.readLine();
        }
    }
}
