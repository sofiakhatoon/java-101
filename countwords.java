import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



class myCounter{ int myCount( String toCount){

    int count=0;
    String prev_word="";
    List<String> llist = new ArrayList<String >(Arrays.asList(toCount.split("\\W")));
    for(int i=0;i<llist.size();i++)
    {
        prev_word=new String(llist.get(i));
        System.out.println();
        if(llist.subList(0,i).contains(prev_word)){
            System.out.println("dup found "+llist.get(i));
        }
        else
        {
            count++;

            System.out.println(prev_word);
        }
    }
    return count;
}}
public class countwords {

    public static void main(String args[])throws Exception{
        int count=0;
        String toCount = "red ball blue ball red";
        myCounter tmp= new myCounter();
        System.out.println(String.format("total words %d",tmp.myCount(toCount)));
    }
}
