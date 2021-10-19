

class ResizeableArrayStack
{
   private int array[], size, top;
   public ResizeableArrayStack(int size)
   {
      this.array = new int[size];
      this.size = size;
      this.top = -1;
   }
   public void push(int i)  //Method for the push 
   {
      if (top == size - 1) 
      {
         throw new RuntimeException("Stack full");
      }
      array[++top] = i;
   }
   public int pop()  //Method for pop
   {
      if (top == -1) 
      {
         throw new RuntimeException("Stack empty");
      }
      return array[top--];
   }
   public int peek() // Method for peek
   {
      if (top == -1) 
      {
         throw new RuntimeException("Stack empty");
      }
      return array[top];
   }
   public static int evaluatePostfix(String postfix) //Implemented Algorithm 
   {
      char[] ch = postfix.toCharArray();
      ResizeableArrayStack valueStack = new ResizeableArrayStack(ch.length);
      for (char nextCharacter : ch) 
      {
         //System.out.println(Character.toString(ch) + "\n");
        if (Character.isDigit(nextCharacter)) 
        {
            valueStack.push(nextCharacter - '0');
        } 
        else 
        {
            int operandOne = valueStack.pop();
            int operandTwo = valueStack.pop();
            switch (nextCharacter) 
            {
               case '+':
                  valueStack.push(operandOne + operandTwo);
                  break;
               case '*':
                  valueStack.push(operandOne * operandTwo);
                  break;
               case '-':
                  valueStack.push(operandTwo - operandOne);
                  break;
               case '/':
                  valueStack.push(operandTwo / operandOne);
                  break;

            }
        }
      }
    return valueStack.pop();
   }


   public static void main(String[] args)
   {
      String postA = "23*42-/56*+";
      //String postB = "2+3*4−9";
      //String postC = "234−/5*";

      int outputA = evaluatePostfix(postA);
      //int outputB = evaluatePostfix(postB);
      //int outputC = evaluatePostfix(postC);

      System.out.printf("Postfix expression: " + outputA + "\n");
      //System.out.printf("Postfix expression: " + outputB + "\n");
      //System.out.printf("Postfix expression: " + outputC + "\n");
   }
   
}