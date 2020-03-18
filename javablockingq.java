import java.util.concurrent.SynchronousQueue;

class BlockingQ<T>{

        Integer qSize;
        T[] array ;
        Object lock = new Object();
        int curr_size = 0;
        int head =0;
        int tail =0;

        public BlockingQ(int qsize){
            this.qSize = qsize;
            this.array = (T[])new Object[qsize];
        }
        public void enque(T item) throws InterruptedException{
            synchronized (lock) {
                while (curr_size == qSize) {
                    lock.wait();
                }


                array[tail] = item;
                tail++;
                curr_size++;
                lock.notifyAll();
            }
        }
        public T dequeue() throws InterruptedException{
            T retVar =(T) new Object();
            synchronized (lock){
                while (curr_size==0)
                {
                    lock.wait();
                }
                System.out.println(array[tail]);
                retVar = array[head];

                head ++;
                array[head]=null;
                curr_size --;
                lock.notifyAll();
            }
            return  retVar;
        }
    }
    public class javablockingq {
    public static void main(String  args[]) throws Exception{
        final BlockingQ<Integer> myQ= new BlockingQ<Integer>(5);
        Thread t1 = new Thread(new Runnable() {
          @Override
          public void run() {
            try{
                myQ.enque(new Integer(4));
            }
            catch (InterruptedException ie){}
          }
      });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Object ob=
                    myQ.dequeue();
                    System.out.println(String.format("dequed %d",ob));
                }
                catch (InterruptedException ie){}
            }
        });
        t1.start();

        Thread.sleep(4000);
        t2.start();
        t1.join();
        t2.join();
    System.out.println("BlockingQ");
    }
}
