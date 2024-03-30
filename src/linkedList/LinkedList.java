package linkedList;
/**
 * @author Jiwon Han
 * @version 2
 * @implSpec Singly linked list
 * @see "https://visualgo.net/en/list?slide=1-1"
 */
public class CustomLinkedList<E>{

    class Node<E>{
        E data;
        Node<E> next;
        Node(E data){
            this.data = data;
            this.next = null;
        }
    }
    private Node<E> head;
    private int size;

    public CustomLinkedList(){
        head = null;
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int getSize() {
        return this.size;
    }

    public void addFirst(E obj){
        Node<E> node = new Node<>(obj);
        node.next = head;
        head = node;
        size += 1;
    }

    public void addLast(E obj){
        Node<E> node = new Node<>(obj);

        if (isEmpty()){
            head = node;
            size += 1;
            return;
        }

        Node<E> tmp = head;
        while (tmp.next != null){
            tmp = tmp.next;
        }
        tmp.next = node;
        size += 1;
    }

    public E removeFirst(){
        if (isEmpty()) return null;
        E tmp = head.data;
        head = head.next; // is same as head == null;
        size -= 1;
        return tmp;
    }
    public E removeLast(){
        if (isEmpty()) return null;
        if (size == 1){
            removeFirst();
        }
        Node<E> curr = head;
        Node<E> prev = null;
        while ( curr.next != null){
            prev = curr;
            curr = curr.next;
        }
        prev.next = null;
        size--;
        return curr.data;
    }


    public E remove(E obj){
        if (isEmpty()) return null;
        Node<E> curr = head;
        Node<E> prev = null;
        while (curr != null){
            if( ((Comparable<E>)obj).compareTo(curr.data) == 0){ // have to implement Comparable<E> if you don't, an error will occur.
                if (curr == head) return removeFirst();
                prev.next = curr.next;
                size--;
                return curr.data;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }
    public boolean contains(Object o){
        Node<E> curr = head;
        while (curr != null){
            if(curr.data.equals(o)){
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public E peekFirst(){
        if (isEmpty()) return null;
        return head.data;
    }

    public E peekLast(){
        if (isEmpty()) return null;
        Node<E> curr = head;
        while (curr.next != null) curr = curr.next;
        return curr.data;
    }

    public void printAll(){
        Node<E> tmp = head;
        StringBuilder sb = new StringBuilder();
        while (tmp != null){
            sb.append(tmp.data).append("\t");
            tmp = tmp.next;
        }
        System.out.println(sb);
    }
}
