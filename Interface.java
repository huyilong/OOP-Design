As stated above they are used for abstraction. 
Since methods in interfaces do not have body, 
they have to be implemented by the class before you can access them. 

The class that implements interface must implement all the methods of that 
interface. Also, java programming language does not support multiple 
inheritance, using interfaces we can achieve this as a class can implement 
more than one interfaces, however it cannot extend more than one classes.

interface MyInterface
{
   /* All the methods are public abstract by default
    * Note down that these methods are not having body
    */
   public void method1();
   public void method2();
}


class XYZ implements MyInterface
{
  public void method1()
  {
      System.out.println("implementation of method1");
  }
  public void method2()
  {
      System.out.println("implementation of method2");
  }
  public static void main(String arg[])
  {
      MyInterface obj = new XYZ();
      obj. method1();
  }
}


an interface can not implement another interface. 
It has to extend the other interface if required. 
See the below example where we have two interfaces Inf1 and Inf2. 
Inf2 extends Inf1 so If class implements the Inf2 it has to provide 
implementation of all the methods of interfaces Inf1 and Inf2.

public interface Inf1{
   public void method1();
}
public interface Inf2 extends Inf1 {
   public void method2();
}
public class Demo implements Inf2{
  public void method1(){
    //Implementation of method1
  }
  public void method2(){
    //Implementation of method2
  }
}

1) We can’t instantiate an interface in java.

2) Interface provides complete abstraction as none of its methods 
can have body. On the other hand, abstract class provides partial 
abstraction as it can have abstract and concrete(methods with body) 
methods both.

3) implements keyword is used by classes to implement an interface.

4) While providing implementation in class of any method of an interface, it needs to be mentioned as public.

5) Class implementing any interface must implement all the methods, otherwise the class should be declared as “abstract”.

6) Interface cannot be declared as private, protected or transient.

7) All the interface methods are by default abstract and public.

8) Variables declared in interface are public, static and final by default.

