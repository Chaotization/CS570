import java.util.ArrayList;
import java.util.NoSuchElementException;

public class IDLList<E> {
    private  class Node<E>{
        private E data;
        private Node<E> next;
        private Node<E> prev;

        private Node(E element){
            this.data = element;
            next = null;
            prev = null;
        }
        private Node(E element, Node<E> prev, Node<E> next){
            this.data = element;
            this.prev = prev;
            this.next = next;
        }
    }
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;
    public IDLList() {
        head  = null;
        tail = null;
        size = 0;
        indices = new ArrayList<Node<E>>();
    }
    public boolean add(int index, E elem){
        if (index < 0 || index > size) {
                return false;
        }
        if (index == 0) {
            this.add(elem);
        }
        else{
            Node<E> current = indices.get(index);
            Node<E> flag = new Node<E>(elem, current.prev, current);
            current.prev.next = flag;
            current.prev = flag;
            size++;
            indices.add(index, flag);
        }
        return true;
    }
    public boolean add(E elem) {
        if (head == null) {
            head = new Node<E>(elem, null, null);
            tail = head;
        }
        else if (head == tail) {
            head = new Node<E>(elem, null, tail);
            tail.prev = head;
        }
        else {
            Node<E> newhead = new Node<E>(elem, null, head);
            head.prev = newhead;
        }
        indices.add(0, head);
        size++;
        return true;
    }

    public boolean append(E elem){
        if (head == null) {
            head = new Node<E>(elem, null, null);
            tail = head;
            size++;
            return indices.add(head);
        }
        else if (tail == head) {
            tail = new Node<E>(elem, head, null);
            head.next = tail;
            size++;
            return indices.add(tail);
        }
        Node<E> newtail = new Node<E>(elem, tail, null);
        tail.next =  newtail;
        tail = newtail;
        size++;
        return indices.add(newtail);
        }

    public E get(int index){
        if(indices.get(index).data != null){
            return indices.get(index).data;
        }
        return null;
    }

    public E getHead(){
        if(head == null){
            throw new IllegalStateException();
        }
        return indices.get(0).data;
    }

    public E getLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        return tail.data;
    }

    public int size(){
        return indices.size();
    }

    public E remove(){
        if(head == null){
            throw new NullPointerException();
        }
        if(head == tail){
            Node<E> temp = head;
            head = null;
            tail = null;
            size--;
            indices.clear();
            return temp.data;
        }
        Node<E> temp = head;
        head = head.next;
        indices.remove(0);
        size--;
        return temp.data;
    }

    public E removeLast(){
        if(head == null){
            throw new IllegalStateException();
        }
        if(head == tail){
            Node<E> temp = tail;
            head = null;
            tail = null;
            size--;
            indices.clear();
            return temp.data;
        }
        Node<E> temp = tail;
        tail = tail.prev;
        tail.next = null;
        indices.remove(size - 1);
        size--;
        return temp.data;
    }

    public E removeAt(int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0){
            return remove();
        }
        if(index == size - 1){
            return removeLast();
        }
        Node<E> temp = indices.remove(index);
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        size--;
        return temp.data;
    }

    public boolean remove(E elem){
        if(elem ==null){
            throw new IllegalArgumentException("Cannot add null elements to the list!");
        }
        if(elem.equals(head.data)){
            remove();
            return true;
        }
        if(elem.equals(tail.data)){
            removeLast();
            return true;
        }
        Node<E> temp = head;
        int index = 0;
        while(temp != null){
            if(temp.data.equals(elem)){
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                indices.remove(index);
                size--;
                return true;
            }
            temp = temp.next;
            index++;
        }
        return false;
    }
    @Override
    public String toString(){
        Node<E> temp = head;
        StringBuilder s = new StringBuilder();
        s.append("[");
        while(temp != null){
            if(temp.next==null){
                s.append(temp.data.toString());
                temp = temp.next;
            }
            else {
                s.append(temp.data.toString() + ", " );
                temp = temp.next;
            }

        }
        s.append("]");
        return s.toString();
    }

}
