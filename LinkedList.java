import java.util.*;

public class LinkedList {
    //Create a LinkedList.      
    public static class Node { 
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    //Methods 

    public static Node head;
    public static Node tail;
    public static int size;


    //Add first in linked list
    public void addFirst(int data) {
        //step 1 --> create new node
        Node newNode = new Node(data);
        size++;

        //Base case 
        if(head == null) {
            head = tail = newNode;
            return;
        }
        //step 2 --> newNode next == head
        newNode.next = head; //link step 

        //step 3 --> head = newNode
        head = newNode;
    }

    public void addLast(int data) {
        //Step 1 --> create a new Node
        Node newNode = new Node(data);
        size++;
        //Base Case 
        if(head == null) {
            head = tail = newNode;
            return;
        }
        //Step 2 --> link last next with newNode
        tail.next = newNode;
        //Step 3 --> tail = newNode
        tail = newNode;
    }

    public void print() {
        if(head == null) {
            System.out.println("LL is Empty");
            return;  
        }
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void addMiddle(int data , int idx) {
        if(idx == 0) {
            addFirst(data);
            return;
        }
        //Create a new Node.
        Node newNode = new Node(data);
        size++;

        //Make a Temp Node.
        Node temp = head;  //Temp point head
        int i = 0; //Index is Zero 

        while(i < idx-1 && temp != null) { //previous
            temp = temp.next;
            i++;
        }

        if(temp == null) {
            System.out.println("Index out of bound");
            return;
        }

        //i = idx-1; temp --> prev
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public int removeFirst() {
        if(size == 0) {
            System.out.println("LL is Empty");
            return Integer.MIN_VALUE;
        }
        else if(size == 1) {
            int value = head.data;
            head = tail = null;
            size = 0;
            return value;
        }
        int value = head.data; //delete 
        head = head.next;
        size--; 
        return value;
    }

    public int removeLast() {
        if(size == 0) {
            System.out.println("LL is Empty");
            return Integer.MIN_VALUE;
        }
        else if(size == 1) {
            int value = head.data;
            head = tail = null;
            size = 0;
            return value;
        }

        //previous : i = size - 2
        Node prev = head;
        for(int i=0; i<size-2; i++) {
            prev = prev.next;  
        }

        int value = prev.next.data; //tail data
        prev.next = null;
        size--;
        return value;  
    }

    //Search Iterative 
    public int itrSearch(int key) {
        Node temp = head;
        int i = 0;

        while(temp != null) {
            if(temp.data == key){  //key found 
                return i;
            }
            temp = temp.next;
            i++;
        }
        // key not found
        return -1;
    }

    public int helper(Node head, int key) {
        if(head == null) {
            return -1;
        }

        if(head.data == key) {
            return 0;
        }

        int idx = helper(head.next, key);
        if(idx == -1) {
            return -1;
        }

        return idx+1;
    }
    public int recSearch(int key) {
        return helper(head , key);
    }


    //Reverse the linkedlist.
    public void reverse() {
        Node prev = null;
        Node curr = tail = head;
        Node Next;

        while(curr != null) {
            Next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = Next;
        }
        head = prev;  //curr = null
    }

    // Find and remove Nth Node From End
    public void deleteNthfromEnd(int n) {
        //Calculate Size
        int sz = 0;
        Node temp = head;

        while(temp != null) {
            temp = temp.next;
            sz++;
        }

        if(n == sz) {  //Remove first (head)
            head = head.next;
            return;
        }

        // sz-n
        int i = 1;
        int iToFind = sz-n;
        Node prev = head;
        while(i < iToFind) {
            prev = prev.next;
            i++;
        }
        prev.next = prev.next.next;
        return;
    }

    // public Node findMid(Node head) {
    //     Node slow = head;
    //     Node fast = head; 
 
    //     while(fast != null && fast.next != null) {
    //         slow = slow.next; //+1
    //         fast = fast.next.next; //+2
    //     }
    //     return slow; //Slow is my middle
    // }

    public Node findMid(Node head) {
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next; //+1
            fast = fast.next.next; //+2
        }
        return slow; //Slow is my MidNode
    }

    public boolean checkPalindrome() {
        if(head == null || head.next == null) {
            return true;
        }
        //Step 1 --> find mid
        Node midNode = findMid(head);

        //Step 2 --> reverse 2nd Half
        Node prev = null;
        Node curr = midNode;
        Node next;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        Node right = prev;  //Right half head
        Node left = head; //left half head

        //Step 3 --> check left & right half
        while(right != null) {
            if(left.data != right.data) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    //Detected a loop / cycle in a LinkedList
    public static boolean isCycle() {
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                return true; //cycle exist karti hai
            }
        }
        return false; // cycle does not exist
    } 

    // public static void removeCycle() {
    //     //detected cycle
    //     Node slow = head;
    //     Node fast = head;
    //     boolean cycle = false;
    //     while(fast != null && fast.next != null) {
    //         slow = slow.next;
    //         fast = fast.next.next;
    //         if(fast == slow) {
    //             cycle = true;
    //             break;
    //         }
    //     }
    //     if(cycle == false) {
    //         return;
    //     }

    //     //Find meeting point 
    //     slow = head;
    //     Node prev = null;
    //     while(slow != fast) {
    //         prev = fast;
    //         slow = slow.next;
    //         fast = fast.next;
    //     }

    //     //Remove Cycle -> last.next = null
    //     prev.next = null;
    // }

    public static void removeCycle() {
        //detected cycle
        Node slow = head;
        Node fast = head;
        boolean cycle = false;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                cycle = true;
                break;
            }
        }
        if(cycle == false) {
            return;
        }
        //find meeting point 
        slow = head;
        Node prev = null;
        while(slow != fast) {
            slow = slow.next;
            prev = fast;
            fast = fast.next;
        }
        //remove cycle 

        prev.next = null;
    }
 
    

    public static void main(String args[]) {
        // LinkedList ll = new LinkedList();
        // ll.addFirst(2);
        // ll.addFirst(1);
        // ll.addLast(3);
        // ll.addLast(4);

        // ll.addMiddle(9 , 2);
        // ll.print();
        // // System.out.println(ll.size);

        // ll.removeFirst();
        // ll.print();

        // ll.removeLast();
        // ll.print();
        // System.out.println(ll.size);

        // System.out.println(ll.recSearch(3));
        // System.out.println(ll.recSearch(10));

        // System.out.println("---------------");
        // ll.reverse();
        // ll.print();
        // System.out.println("--------------");
        // ll.deleteNthfromEnd(3);
        // ll.print();

        // System.out.println(ll.checkPalindrome());
        head = new Node(1);
        Node temp = new Node(2);
        head.next = temp;
        head.next.next = new Node(3);
        head.next.next.next = temp;

        
        System.out.println(isCycle());
        removeCycle();
        System.out.println(isCycle());


    }
} 