Types of Exception

There are mainly two types of exceptions: 
checked and unchecked where error is considered as unchecked exception. 
The sun microsystem says there are three types of exceptions:

Checked Exception
Unchecked Exception
Error


There are many differences between throw and throws keywords. A list of differences between throw and throws are given below:

No.	throw	throws
1)	Java throw keyword is used to explicitly throw an exception.	Java throws keyword is used to declare an exception.
2)	Checked exception cannot be propagated using throw only.	Checked exception can be propagated with throws.
3)	Throw is followed by an instance.	Throws is followed by class.
4)	Throw is used within the method.	Throws is used with the method signature.
5)	You cannot throw multiple exceptions.	You can declare multiple exceptions e.g.
public void method()throws IOException,SQLException.


1) Checked Exception

The classes that extend Throwable class except RuntimeException and Error are known as checked exceptions e.g.IOException, SQLException etc. Checked exceptions are checked at compile-time.

2) Unchecked Exception

The classes that extend RuntimeException are known as unchecked exceptions e.g. ArithmeticException, NullPointerException, ArrayIndexOutOfBoundsException etc. Unchecked exceptions are not checked at compile-time rather they are checked at runtime.

3) Error

Error is irrecoverable e.g. OutOfMemoryError, VirtualMachineError, AssertionError etc.




There are given some scenarios where unchecked exceptions can occur. They are as follows:

1) Scenario where ArithmeticException occurs

If we divide any number by zero, there occurs an ArithmeticException.

int a=50/0;//ArithmeticException  
2) Scenario where NullPointerException occurs

If we have null value in any variable, performing any operation by the variable occurs an NullPointerException.

String s=null;  
System.out.println(s.length());//NullPointerException  


3) Scenario where NumberFormatException occurs

The wrong formatting of any value, may occur NumberFormatException. Suppose I have a string variable that have characters, converting this variable into digit will occur NumberFormatException.

String s="abc";  
int i=Integer.parseInt(s);//NumberFormatException  
4) Scenario where ArrayIndexOutOfBoundsException occurs

If you are inserting any value in the wrong index, it would result ArrayIndexOutOfBoundsException as shown below:

int a[]=new int[5];  
a[10]=50; //ArrayIndexOutOfBoundsException  



class TestMultipleCatchBlock1{  
  public static void main(String args[]){  
   try{  
    int a[]=new int[5];  
    a[5]=30/0;  
   }  
   catch(Exception e){System.out.println("common task completed");}  
   catch(ArithmeticException e){System.out.println("task1 is completed");}  
   catch(ArrayIndexOutOfBoundsException e){System.out.println("task 2 completed");}  
   System.out.println("rest of the code...");  
 }  
}  


Why use java finally

Finally block in java can be used to put "cleanup" code such as closing a file, closing connection etc.


Case 1

Let's see the java finally example where exception doesn't occur.

class TestFinallyBlock{  
  public static void main(String args[]){  
  try{  
   int data=25/5;  
   System.out.println(data);  
  }  
  catch(NullPointerException e){System.out.println(e);}  
  finally{System.out.println("finally block is always executed");}  
  System.out.println("rest of the code...");  
  }  
}  


Lets see the java finally example where exception occurs and not handled.

class TestFinallyBlock1{  
  public static void main(String args[]){  
  try{  
   int data=25/0;  
   System.out.println(data);  
  }  
  catch(NullPointerException e){System.out.println(e);}  
  finally{System.out.println("finally block is always executed");}  
  System.out.println("rest of the code...");  
  }  
}  


Case 3

Lets see the java finally example where exception occurs and handled.

public class TestFinallyBlock2{  
  public static void main(String args[]){  
  try{  
   int data=25/0;  
   System.out.println(data);  
  }  
  catch(ArithmeticException e){System.out.println(e);}  
  finally{System.out.println("finally block is always executed");}  
  System.out.println("rest of the code...");  
  }  
}  


ava throw keyword example

In this example, we have created the validate method that takes integer value as a parameter. If the age is less than 18, we are throwing the ArithmeticException otherwise print a message welcome to vote.

public class TestThrow1{  
   static void validate(int age){  
     if(age<18)  
      throw new ArithmeticException("not valid");  
     else  
      System.out.println("welcome to vote");  
   }  
   public static void main(String args[]){  
      validate(13);  
      System.out.println("rest of the code...");  
  }  
}  


Java throws keyword

The Java throws keyword is used to declare an exception. 
It gives an information to the programmer that there may occur 
an exception so it is better for the programmer to provide the 
exception handling code so that normal flow can be maintained.

Exception Handling is mainly used to handle the checked exceptions. 
If there occurs any unchecked exception such as NullPointerException, 
it is programmers fault that he is not performing check up before the 
code being used.


Advantage of Java throws keyword

Now Checked Exception can be propagated (forwarded in call stack).

It provides information to the caller of the method about the exception.


import java.io.IOException;  
class Testthrows1{  
  void m()throws IOException{  
    throw new IOException("device error");//checked exception  
  }  
  void n()throws IOException{  
    m();  
  }  
  void p(){  
   try{  
    n();  
   }catch(Exception e){System.out.println("exception handled");}  
  }  
  public static void main(String args[]){  
   Testthrows1 obj=new Testthrows1();  
   obj.p();  
   System.out.println("normal flow...");  
  }  
}  


Rule: If you are calling a method that declares an exception, you must either caught or declare the exception.
///!!! you could either propagate it to another 
//or you can catch it by catching
There are two cases:
Case1:You caught the exception i.e. handle the exception using try/catch.
Case2:You declare the exception i.e. specifying throws with the method.


mport java.io.*;  
class M{  
 void method()throws IOException{  
  throw new IOException("device error");  
 }  
}  
public class Testthrows2{  
   public static void main(String args[]){  
    try{  
     M m=new M();  
     m.method();  
    }catch(Exception e){System.out.println("exception handled");}     
  
    System.out.println("normal flow...");  
  }  
}  


Case2: You declare the exception

A)In case you declare the exception, if exception does not occur, the code will be executed fine.
B)In case you declare the exception if exception occures, an exception will be thrown at runtime because throws does not handle the exception.
A)Program if exception does not occur
import java.io.*;  
class M{  
 void method()throws IOException{  
  System.out.println("device operation performed");  
 }  
}  
class Testthrows3{  
   public static void main(String args[])throws IOException{//declare exception  
     M m=new M();  
     m.method();  
  
    System.out.println("normal flow...");  
  }  
}  
