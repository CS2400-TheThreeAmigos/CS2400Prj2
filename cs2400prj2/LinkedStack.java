import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<Item> implements Iterable<Item> 
{
    private int n;          
    private Node first;    

    private class Node {
        private Item item;
        private Node next;
    }

    public LinkedStack() {
        first = null;
        n = 0;
        assert check();
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
        assert check();
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;        
        first = first.next;           
        n--;
        assert check();
        return item;                  
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }
       
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

    private boolean check() {

        if (n < 0) {
            return false;
        }
        if (n == 0) {
            if (first != null) return false;
        }
        else if (n == 1) {
            if (first == null)      return false;
            if (first.next != null) return false;
        }
        else {
            if (first == null)      return false;
            if (first.next == null) return false;
        }

        int numberOfNodes = 0;
        for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != n) return false;

        return true;
    }

public static String convertToPostfix(String infix)
{
    infix = infix + ")";

    //create an object of LinkedStack class
    LinkedStack<Character> stk = new LinkedStack<Character>();
   
    stk.push('(');
   
    String postfix = "";
   
    //convert from infix to postfix
    for(int i=0; i<infix.length(); i++)
    {
        char ch = infix.charAt(i);
       
        //check for variable
        if(Character.isLetter(ch))
        {
            postfix = postfix + ch + " ";
        }
        //check for left parenthesis
        else if(ch=='(')
        {
            stk.push(ch);
        }
        //check for righr parenthesis
        else if(ch==')')
        {
            while(stk.peek()!='(')
            {
                postfix = postfix + stk.peek() + " ";
                stk.pop();
            }
            stk.pop();
        }
        //operator
        else
        {
            break;
        }
    }
   
    //check for error  
    if(!stk.isEmpty())
        System.out.println(" 0_0 ");

    //return postfix expression
    return postfix;
    }
   public static void main (String[] args) throws Exception
   {
       String infixA = "a+b";
       String infixB = "a+b*c";
       String infixC = "ab/(c-a)+de";

       String postfixA = convertToPostfix(infixA);
       String postfixB = convertToPostfix(infixB);
       String postfixC = convertToPostfix(infixC);
       
       System.out.println ("Postfix expression: " + postfixA + "\n");
       System.out.println ("Postfix expression: " + postfixB + "\n");
       System.out.println ("Postfix expression: " + postfixC + "\n");
   }
}