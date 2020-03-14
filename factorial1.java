import javafx.concurrent.Task;

import java.util.concurrent.*;

class LambdaTargetType {

    public void getWorking(String arg0) throws Exception {
        System.out.println(String.format("  class LambdaTarget with arg0 %s",arg0));
        if(arg0.equals(new String("")))
        compute(() -> "done");
        else
            compute(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("running thread");
                }
            }));
    }

    void compute(Runnable r) {
        System.out.println("Runnable invoked");
        r.run();
    }

    <T> T compute(Callable<T> c) throws Exception {
        System.out.println("Callable invoked");

        return c.call();
    }
}
 class LinkedList {

    Node head; // head of list


    static class Node {

        int data;
        Node next;

        // Constructor
        Node(int d) {
            data = d;
            next = null;
        }
    }
     public static int getLength(LinkedList list)
     {
         int lgth=0;
         Node currNode = list.head;



         // Traverse through the LinkedList
         while (currNode != null) {
             // Print the data at current node
             // Go to next node
             lgth++;
             currNode = currNode.next;
         }
         System.out.println("\n");
         return  lgth;
     }
     // **************INSERTION**************

    // Method to insert a new node
    public LinkedList insert(LinkedList list, int data) {
        // Create a new node with given data
        Node new_node = new Node(data);
        new_node.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
            list.head = new_node;
        } else {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }

            // Insert the new_node at last node
            last.next = new_node;
        }

        // Return the list by head
        return list;
    }
}
class testSafety{
        int sumThreadSafe(int... vals){

        int total=0;
        for(int i=0;i<vals.length;i++){
        total+=vals[i];
        }
        return total;
        }
        }
    public class factorial1
{
     int calc(int arg0){
        if(arg0 == 1)

        return arg0;
        else
            return arg0* calc(arg0-1);
    }
     int calc2(int arg0){
        int fac =1;
        for(int i=1; i<=arg0; i++){
            fac=fac *i;
        }
        return  fac;
    }
     int CountDigit(int arg0){
        int numDig=0;
        int num=arg0;
        while (num/10>0) {
            numDig++;
            num = num/10;
        }
        return numDig+1;
    }


public static int pow(int arg0, int idex){

         if(idex ==1 ) return arg0;
         else {
             idex--;
             System.out.println(idex);
             return arg0* pow(arg0, idex);
         }
}
public static  int sumofAll(int arg0, int index){
         if(index == 1) return 1;
         int answer = 0;
    ;index=arg0-1;
         answer = arg0+ sumofAll( arg0-1,index);
         return answer;
    }

    public static void main(String args[]) throws ExecutionException, InterruptedException {

        LinkedList llist = new LinkedList();
        llist.insert(llist,200);
        llist.insert(llist,500);
       System.out.println( String.format(" head is %d",llist.head.data));
       System.out.println(String.format("length %d",llist.getLength(llist)));
       System.out.println(pow(2,5));
       System.out.println(String.format("sum of numbers %d", sumofAll(6,6)));
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        Callable<Void> someTask = new Callable<Void>() {

            public Void call() throws Exception {
                System.out.println("Running");
                Object mySafe=  new testSafety();
                System.out.println(String.format("tested safety %d",((testSafety) mySafe).sumThreadSafe(2,4)));
                return null;
            }
        };

        threadPool.submit(someTask).get();
        threadPool.submit(someTask).get();

        System.out.println( "threadpool Exiting" );
        threadPool.shutdown();
        try {
            if(args.length==0)args=new String[]{"1"};
            (new LambdaTargetType()).getWorking(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Thread t = new Thread(()->{System.out.println("New thread");});
        t.start();
        t.join();


        Callable<Void> tsk = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                System.out.println("Callable interface ivoked by future task that can go into thread contructor");
                return null;
            }
        };

        FutureTask<Void> ft = new FutureTask<>(tsk);
        Thread myThread = new Thread(ft);
        myThread.start();
        myThread.join();

    }
}
