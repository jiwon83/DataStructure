package handcoding;

import java.util.Arrays;

/**
 * 최소힙
 * @param <E>
 */
public class MinHeap<E> {
    int lastPosition;
    E [] arr;

    public MinHeap(int size){
        arr = (E[]) new Object[size];
        lastPosition = -1;
    }

    /**
     * @param data
     * @ImplNote
     * 1. 마지막 위치에 데이터를 추가한다.
     * 2. 추가된 데이터를 trickleUp 한다.
     */
    public void add(E data){
        arr[++lastPosition] = data;
        trickleUp(lastPosition);
    }

    /**
     * @return E
     * @ImplNote
     * 1. 루트 노드를 반환
     * 2. 마지막 노드를 루트로 옮긴다.
     * 3. trickleDown 한다.
     */
    public E poll(){
        if (lastPosition == -1) return null;
        E root = arr[0];
        arr[0] = arr[lastPosition];
        arr[lastPosition--] = null;
        trickleDown(0);
        return root;
    }

    private void trickleUp(int child){
        if(child == 0) return;
        int parent = (child - 1) / 2;
        if(((Comparable<E>)arr[child]).compareTo(arr[parent]) < 0){
            swap(child, parent);
            trickleUp(parent);
        }
    }
    private void trickleDown(int parent){

        int childL = parent * 2 + 1;
        int childR = parent * 2 + 2;
        if( childL > lastPosition || childR > lastPosition) return;
        if(childL == lastPosition && ((Comparable<E>)arr[parent]).compareTo(arr[childL]) > 0){
            swap(parent, childL);
            return;
        }
        if(childR == lastPosition && ((Comparable<E>)arr[parent]).compareTo(arr[childR]) > 0){
            swap(parent, childR);
            return;
        }
        if(((Comparable<E>)arr[childL]).compareTo(arr[childR]) < 0 && ((Comparable<E>)arr[parent]).compareTo(arr[childL]) > 0){
            swap(parent, childL);
            trickleDown(childL);
        }
        else if(((Comparable<E>)arr[childL]).compareTo(arr[childR]) > 0 && ((Comparable<E>)arr[parent]).compareTo(arr[childR]) > 0) {
            swap(parent, childR);
            trickleDown(childR);
        }

    }
    private void swap(int from, int to){
        E tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }

    public void print(){

        System.out.println(Arrays.toString(arr));

    }
}
