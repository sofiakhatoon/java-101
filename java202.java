import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Semaphore;

class myThread<T extends String> extends Thread {
    T argIn ;
    final Semaphore semaphore= new Semaphore(1);

    public myThread(T item){
        this.argIn = item;
    }
    public void run(){

        String arg0 = argIn;
        try {
            semaphore.acquire();
            System.out.println(String.format("My friend %s", arg0));
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            semaphore.release();
        }

    }
}
class ContactBook{
    Collection<String> contacts = new ArrayList<>();
    synchronized void addContact(String inContact){
        contacts.add(inContact);
    }
}
public class java202 {

    public static void main(String[] args) throws Exception{
        int T=args.length;
        Collection<String> arguments;
        int i=0;

        while(i<T) {
            myThread thr = new myThread(args[i]);
            thr.start();
            thr.join();
            //System.out.println(String.format("My friend %s", args[i]));
            i++;
        }
    }
}
