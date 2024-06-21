import java.io.*;
class Heap<Key extends Comparable<Key>, Value> {
    private Entry<Key, Value>[] a;
    private int n;
    private boolean isMinHeap;
    public Heap(int capacity, boolean isMinHeap) {
        a = new Entry[capacity + 1];
        this.isMinHeap = isMinHeap;
        n = 0;
    }
    private void upheap(int k) {
        if (k/2==0)
            return;
        if (isMinHeap) {
            if (a[k].getKey().compareTo(a[k/2].getKey())<0) {
                swap(k,k/2);
                upheap(k/2);
            }
        } else {
            if (a[k].getKey().compareTo(a[k/2].getKey())>0) {
                swap(k,k/2);
                upheap(k/2);
            }
        }
    }
    private void downheap(int k) {
        int child=2*k;
        if (child>n) return;
        if (isMinHeap) {
            if (child<n&&a[child+1] !=null &&a[child+ 1].getKey().compareTo(a[child].getKey()) < 0) {
                child++;
            }
            if (a[child] != null && a[child].getKey().compareTo(a[k].getKey()) < 0) {
                swap(k, child);
                downheap(child);
            }
        } else {
            if (child < n && a[child + 1] != null && a[child + 1].getKey().compareTo(a[child].getKey()) > 0) {
                child++;
            }
            if (a[child] != null && a[child].getKey().compareTo(a[k].getKey()) > 0) {
                swap(k, child);
                downheap(child);
            }
        }
    }
    public Entry<Key, Value> peekRoot() {
        return a[1];
    }
    private void swap(int i, int j) {
        Entry<Key, Value> temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public void insert(Key newKey, Value newValue) {
        Entry<Key, Value> temp = new Entry<>(newKey, newValue);
        a[++n] = temp;
        upheap(n);
    }
    public int size() {
        return n;
    }
    public Entry<Key, Value> deleteRoot() {
        Entry<Key, Value> root = a[1];
        swap(1, n--);
        a[n + 1] = null;
        downheap(1);
        return root;
    }
}
class Entry<Key extends Comparable<Key>, Value> {
    private Key key;
    private Value value;
    public Entry(Key key, Value value) {
        this.key = key;
        this.value = value;
    }
    public Key getKey() {
        return key;
    }
    public Value getValue() {
        return value;
    }
    public void setKey(Key key) {
        this.key = key;
    }
    public void setValue(Value value) {
        this.value = value;
    }
}
public class MainJava {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\gjw19\\Downloads\\week11_input.txt"));
        String line = br.readLine();
        while (line != null) {
            String[] numbers = line.split(" ");
            Heap<Integer, Integer> maxHeap = new Heap<>(numbers.length, false);
            Heap<Integer, Integer> minHeap = new Heap<>(numbers.length, true);
            for (String num : numbers) {
                int n = Integer.parseInt(num);
                maxHeap.insert(n, 1);
                minHeap.insert(n, 1);
            }
            if(numbers.length%2==0){
                maxHeap.deleteRoot();
            }
            while (true) {

                if (maxHeap.peekRoot().getKey().compareTo(minHeap.peekRoot().getKey()) == 0) {
                    System.out.println(maxHeap.peekRoot().getKey());
                    break;
                }
                maxHeap.deleteRoot();
                minHeap.deleteRoot();
            }
            line = br.readLine();
        }
        br.close();
    }
}
