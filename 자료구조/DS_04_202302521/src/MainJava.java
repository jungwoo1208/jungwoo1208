import java.io.*;
import java.util.*;
class node <E>{
    private E data;
    private node<E> next;
    private node<E> back;
    node(node<E> back, E data, node<E> next) {
        this.data = data;
        this.next = next;
        this.back = back;
    }
    node(E data){
        this.data=data;
        this.back=null;
        this.next=null;
    }
    E getData() {
        return this.data;
    }
    void setNext(node<E> next) {
        this.next = next;
    }
    node<E> getNext() {
        return this.next;
    }
    void setBack(node<E> back) {
        this.back = back;
    }
    node<E> getBack() {
        return this.back;
    }
}
class double_linked_list<E>{
    node<E> head;
    int size;
    public double_linked_list() {
        this.head = new node<>(null, null, null);
        head.setNext(head);
        head.setBack(head);
        size=0;
    }
    void insert(node<E>n){
        if(size==0)
            head.setBack(n);
        n.setBack(head);
        n.setNext(head.getNext());
        head.getNext().setBack(n);
        head.setNext(n);
        size++;
    }
    void delete(){
        if(size==0){
            return;
        }
        node <E> last=head.getBack();
        last.getBack().setNext(head);
        head.setBack(last.getBack());
        size--;
    }
    void printAll(){
        node<E> temp=head.getNext();
        while(temp!=head){
            System.out.print(temp.getData()+" ");
            temp=temp.getNext();
        }
        System.out.println();
    }
}
public class MainJava {
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\gjw19\\Downloads\\week04_input.txt"));
        String line = br.readLine();
        while (line != null) {
            StringTokenizer st = new StringTokenizer(line);
            double_linked_list<String> list = new double_linked_list<>();
            int n=st.countTokens();
            boolean flag=false;
            for(int i=0;i<n;i++){
                String s= st.nextToken();
                if(s.equals("i")) {
                    flag = true;
                    continue;
                }
                if(s.equals("d")) {
                    list.delete();
                    flag = false;
                }
                if(flag)
                    list.insert(new node<>(s));
            }
            list.printAll();
            line = br.readLine();
        }
    }
}
