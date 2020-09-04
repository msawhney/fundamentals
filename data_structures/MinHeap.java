package data_structures;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MinHeap<T extends Comparable<T>> {
    private List<T> items;

    public MinHeap() {
        items = new ArrayList<>();
    }

    //Public Methods
    public void insert(final T item) {
        // A new element is added to the end of the list
        items.add(item);
        siftUp();
    }

    public T remove() throws NoSuchElementException {
        //Deletion removes the root node
        if (items.size() == 0)
            throw new NoSuchElementException();
        if (items.size() == 1)
            return items.remove(0);
        T hold = items.get(0);
        items.set(0, items.remove(items.size() - 1)); //Make the end element as root and siftDown
        siftDown();
        return hold;
    }

    public T getMin() throws NoSuchElementException {
        if (items.size() == 0)
            throw new NoSuchElementException();
        return items.get(0);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    //Private Methods
    private void siftUp() {
        int k = items.size() - 1;
        //Get the parent
        while (k > 0) {
            int p = (k-1)/2; //parent
            T item = items.get(k);
            T parent = items.get(p);
            if (parent.compareTo(item) > 0) { // Min heap
                //Swap    
                items.set(k, parent);
                items.set(p, item);
                //Move index k to parent
                k = p;
            } else {
                break;
            }
        }
    }

    private void siftDown() {
        //left = 2k+1 and right = 2k+2
        int k = 0;
        int l = 2*k + 1;
        while (l < items.size()) {
            int r = l + 1;
            int min = l;
            if (r < items.size() && items.get(r).compareTo(items.get(l)) < 0) {
                min = r;
            }
            if (items.get(min).compareTo(items.get(k)) < 0) {
                //Swap
                T item = items.get(k);
                items.set(k, items.get(min));
                items.set(min, item);
                k = min;
                l = 2*k + 1;
            } else {
                break;
            }
        }
    }

    public String toString() {
        return items.toString();
    }

    public static void main(String[] args) {
        MinHeap<Integer> maxHeap = new MinHeap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter next int or done to stop");
        String line = sc.next();
        while (!line.equals("done")) {
            maxHeap.insert(Integer.parseInt(line));
            System.out.println(maxHeap);;
            System.out.println("Enter next int or done to stop");
            line = sc.next();
        }

        while(!maxHeap.isEmpty()) {
            int item = maxHeap.remove();
            System.out.println(item + " " + maxHeap);
        }
        sc.close();
    }    
}
