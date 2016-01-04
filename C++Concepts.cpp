A destructor is the member function of the class which is having the same name as the class name and prefixed with tilde (~) symbol. 
It gets executed automatically w.r.t the object as soon as the object loses its scope. It cannot be overloaded and the only 
form is without the parameters.

A constructor is the member function of the class which is having the same as the class name and gets executed automatically as
 soon as the object for the respective class is created.

A copy constructor is the constructor which take same class object reference as the parameter. It gets automatically invoked as soon as 
the object is initialized with another object of the same class at the time of its creation.

 ‘delete’ operator is used to release the dynamic memory which was created using ‘new’ operator.
 Yes, as C is the subset of C++, we can all the functions of C in C++ too. -- malloc()
 No, we need to use free() of C language for the same. -- for malloc() we cannot use delete() we need to use free()

Container Class: A class containing at least one member variable of another class type in it is called so.
arg[0] -- Program name.

By default every local variable of the function is automatic (auto). In the below function both the variables ‘i’ and ‘j’ are automatic variables.
Specifies that the type of the variable that is being declared will be automatically deduced from its initializer.
auto a = 1 + 2;
void f() {
   int i;
  
   auto int j;
}
NOTE: A global variable can’t be an automatic variable.

 Function overloading
 Defining several functions with the same name with unique list of parameters is called as function overloading.
 Operator overloading
 Defining a new job for the existing operator w.r.t the class objects is called as operator overloading.
 When you call an overloaded function or operator, the **compiler** determines the most appropriate definition to use by comparing the argument types 
 you used to call the function or operator with the parameter types specified in the definitions. The process of selecting the most appropriate 
 overloaded function or operator is called overload resolution.

 Following is the example to show the concept of operator over loading using a member function. Here an object is passed as an argument whose p
 roperties will be accessed using this object, the object which will call this operator can be accessed using **this** operator as explained below:
This, is the pointer variable of the compiler which always holds the current active object’s address.
A namespace is the logical division of the code which can be used to resolve the name conflict of the identifiers by placing them under 
different name space.
class Box{
   public:
      double getVolume(void){
         return length * breadth * height;
      }
      void setLength( double len ){
          length = len;
      }
      void setBreadth( double bre ){
          breadth = bre;
      }
      void setHeight( double hei ){
          height = hei;
      }
      // Overload + operator to add two Box objects.
      Box operator+(const Box& b){
         Box box;
         box.length = this->length + b.length;
         box.breadth = this->breadth + b.breadth;
         box.height = this->height + b.height;
         return box;
      }
   private:
      double length;      // Length of a box
      double breadth;     // Breadth of a box
      double height;      // Height of a box
};
// Main function for the program
int main( ){
   Box Box1;                // Declare Box1 of type Box
   Box Box2;                // Declare Box2 of type Box
   Box Box3;                // Declare Box3 of type Box
   double volume = 0.0;     // Store the volume of a box here
   // box 1 specification
   Box1.setLength(6.0); 
   Box1.setBreadth(7.0); 
   Box1.setHeight(5.0);
   // box 2 specification
   Box2.setLength(12.0); 
   Box2.setBreadth(13.0); 
   Box2.setHeight(10.0);
   // volume of box 1
   volume = Box1.getVolume();
   cout << "Volume of Box1 : " << volume <<endl;
 
   // volume of box 2
   volume = Box2.getVolume();
   cout << "Volume of Box2 : " << volume <<endl;

   // Add two object as follows:
   Box3 = Box1 + Box2;

   // volume of box 3
   volume = Box3.getVolume();
   cout << "Volume of Box3 : " << volume <<endl;
   return 0;
}

A virtual function makes its class a polymorphic base class. Derived classes can override virtual functions. Virtual functions called through 
base class pointers/references will be resolved at run-time. That is, the dynamic type of the object is used instead of its static type:

 Derived d;
 Base& rb = d;
 // if Base::f() is virtual and Derived overrides it, Derived::f() will be called
 rb.f();  
A pure virtual function is a virtual function whose declaration ends in =0:

class Base {
  // ...
  virtual void f() = 0;
  // ...
A pure virtual function implicitly makes the class it is defined for abstract (unlike in Java where you have a keyword to explicitly declare the 
	class abstract). Abstract classes cannot be instantiated. Derived classes need to override/implement all inherited pure virtual functions. 
If they do not, they too will become abstract.

An interesting 'feature' of C++ is that a class can define a pure virtual function that has an implementation. (What thats good for is debatable.)



 Friend functions
 In principle, private and protected members of a class cannot be accessed from outside the same class in which they are declared. 
 However, this rule does not apply to "friends".

Friends are functions or classes declared with the friend keyword.

A non-member function can access the private and protected members of a class if it is declared a friend of that class. 
That is done by including a declaration of this external function within the class, and preceding it with the keyword friend:
// friend functions
#include <iostream>
using namespace std;

class Rectangle {
    int width, height;
  public:
    Rectangle() {}
    Rectangle (int x, int y) : width(x), height(y) {}
    int area() {return width * height;}
    friend Rectangle duplicate (const Rectangle&);
};

Rectangle duplicate (const Rectangle& param)
{
  Rectangle res;
  res.width = param.width*2;
  res.height = param.height*2;
  return res;
}

The duplicate function is a friend of class Rectangle. Therefore, function duplicate is able to access the members width and height 
(which are private) of different objects of type Rectangle. Notice though that neither in the declaration of duplicate nor in its later use in main, 
function duplicate is considered a member of class Rectangle. It isnt! It simply has access to its private and protected members without being a member.

Similar to friend functions, a friend class is a class whose members have access to the private or protected members of another class:
// friend class
#include <iostream>
using namespace std;
class Rectangle {
    int width, height;
  public:
    int area ()
      {return (width * height);}
    void convert (Square a);
};

class Square {
  friend class Rectangle;
  private:
    int side;
  public:
    Square (int a) : side(a) {}
};

void Rectangle::convert (Square a) {
  width = a.side;
  height = a.side;
}
Friendships are never corresponded unless specified: In our example, Rectangle is considered a friend class by Square, but Square is not considered a 
friend by Rectangle. Therefore, the member functions of Rectangle can access the protected and private members of Square but not the other way around.


In principle, a publicly derived class inherits access to every member of a base class **except**:
its constructors and its destructor
its assignment operator members (operator=)
its friends
its private members

When inheritance is private :
i. Private members of base class are not accessible to derived class.
ii. Protected members of base class become private members of derived class.
iii. Public members of base class become private members of derived class.
When inheritance is protected :
i. Private members of base class are not accessible to derived class.
ii. Protected members of base class remain protected in derived class.
iii. Public members of base class become protected in derived class.
If public key word is left, private inheritance takes place by default. 
class base{
   int i, j;
   public:
       void setij(int a, int b){
          i = a;
          j = b;
       }
       void showij() {
          cout <<”\nI:”<<i<<”\n J:”<<j;
       }
};

class derived : private base{
   int k;
   public:
       void setk(){
          //setij(); 
          k = i + j;
       }
       void showall(){
          cout <<”\nK:”<<k<<show();
       }
};

int main(){
   derived ob;
    //ob.setij(); // not allowed. Setij() is private member of derived
   ob.setk(); //ok setk() is public member of derived
   //ob.showij(); // not allowed. Showij() is private member of derived
   ob.showall(); // ok showall() is public member of derived
   return 0;
}

Even though access to the constructors and destructor of the base class is not inherited as such, they are automatically called by the constructors 
and destructor of the derived class.
Unless otherwise specified, the constructors of a derived class calls the default constructor of its base classes (i.e., the constructor taking no arguments).
 Calling a different constructor of a base class is possible, using the same syntax used to initialize member variables in the initialization list:
derived_constructor_name (parameters) : base_constructor_name (parameters) 
class Mother {
  public:
    Mother ()
      { cout << "Mother: no parameters\n"; }
    Mother (int a)
      { cout << "Mother: int parameter\n"; }
};

class Daughter : public Mother {
  public:
    Daughter (int a)
      { cout << "Daughter: int parameter\n\n"; }
};

class Son : public Mother {
  public:
    Son (int a) : Mother (a)
      { cout << "Son: int parameter\n\n"; }
};
Daughter (int a)          // nothing specified: call default constructor
Son (int a) : Mother (a)  // constructor specified: call this specific constructor

-------------------------------------------------------------
Multiple inheritance
A class may inherit from more than one class by simply specifying more base classes, separated by commas, in the list of a classs base classes 
(i.e., after the colon). For example, if the program had a specific class to print on screen called Output, and we wanted our classes Rectangle 
and Triangle to also inherit its members in addition to those of Polygon we could write:

class Rectangle: public Polygon, public Output;
class Triangle: public Polygon, public Output; 
class Polygon {
  protected:
    int width, height;
  public:
    Polygon (int a, int b) : width(a), height(b) {}
};

class Output {
  public:
    static void print (int i);
};

void Output::print (int i) {
  cout << i << '\n';
}

class Rectangle: public Polygon, public Output {
  public:
    Rectangle (int a, int b) : Polygon(a,b) {}
    int area ()
      { return width*height; }
};

class Triangle: public Polygon, public Output {
  public:
    Triangle (int a, int b) : Polygon(a,b) {}
    int area ()
      { return width*height/2; }
};
  
int main () {
  Rectangle rect (4,5);
  Triangle trgl (4,5);
  rect.print (rect.area());
  Triangle::print (trgl.area());
  return 0;
}

***********************************pointer and reference******************************************
A pointer can be re-assigned:
int x = 5;
int y = 6;
int *p;
p =  &x;
p = &y;
*p = 10;
assert(x == 5);
assert(y == 10);
A reference cannot, and must be assigned at initialization:
int x = 5;
int y = 6;
int &r = x;

A pointer has its own memory address and size on the stack (4 bytes on x86), whereas a reference shares the same memory address 
(with the original variable) but also takes up some space on the stack. Since a reference has the same address as the original variable itself, 
it is safe to think of a reference as another name for the same variable. Note: What a pointer points to can be on the stack or heap. 
Ditto a reference. My claim in this statement is not that a pointer must point to the stack. A pointer is just a variable that holds a memory address. 
This variable is on the stack. Since a reference has its own space on the stack, and since the address is the same as the variable it references. 
More on stack vs heap. This implies that there is a real address of a reference that the compiler will not tell you.
int x = 0;
int &r = x;
int *p = &x;
int *p2 = &r;
assert(p == p2);
You can have pointers to pointers to pointers offering extra levels of indirection. Whereas references only offer one level of indirection.
int x = 0;
int y = 0;
int *p = &x;
int *q = &y;
int **pp = &p;
pp = &q;//*pp = q
**pp = 4;
assert(y == 4);
assert(x == 0);

Pointer can be assigned NULL directly, whereas reference cannot. If you try hard enough, and you know how, you can make the address of 
a reference NULL. Likewise, if you try hard enough you can have a reference to a pointer, and then that reference can contain NULL.
int *p = NULL;
int &r = NULL; <--- compiling error

Pointers can iterate over an array, you can use ++ to go to the next item that a pointer is pointing to, and + 4 to go to the 5th element. 
This is no matter what size the object is that the pointer points to.

A pointer needs to be dereferenced with * to access the memory location it points to, whereas a reference can be used directly.
 A pointer to a class/struct uses -> to access its members whereas a reference uses a ..

A pointer is a variable that holds a memory address. Regardless of how a reference is implemented, a reference has the same memory address as the 
item it references.

References cannot be stuffed into an array, whereas pointers can be (Mentioned by user @litb)

Const references can be bound to temporaries. Pointers cannot (not without some indirection):
const int &x = int(12); //legal C++
int *y = &int(12); //illegal to dereference a temporary.
This makes const& safer for use in argument lists and so forth.

-----------------------------------------------Geek for Geek-------------------------------------------
What are the differences between C and C++?
1) C++ is a kind of superset of C, most of C programs except few exceptions (See this and this) work in C++ as well.
2) C is a procedural programming language, but C++ supports both procedural and Object Oriented programming.
3) Since C++ supports object oriented programming, it supports features like function overloading, templates, inheritance, virtual functions, friend functions. These features are absent in C.
4) C++ supports exception handling at language level, in C exception handling is done in traditional if-else style.
5) C++ supports references, C doesn’t.
6) In C, scanf() and printf() are mainly used input/output. C++ mainly uses streams to perform input and output operations. cin is standard input stream and cout is standard output stream.

What are the differences between references and pointers?
Both references and pointers can be used to change local variables of one function inside another function. 
Both of them can also be used to save copying of big objects when passed as arguments to functions or returned from functions, to get efficiency gain.
Despite above similarities, there are following differences between references and pointers.
--------References are less powerful than pointers
1) Once a reference is created, it cannot be later made to reference another object; it cannot be reseated. This is often done with pointers.
2) References cannot be NULL. Pointers are often made NULL to indicate that they are not pointing to any valid thing.
3) A reference must be initialized when declared. There is no such restriction with pointers
Due to the above limitations, references in C++ cannot be used for implementing data structures like Linked List, Tree, etc. 
In Java, references don’t have above restrictions, and can be used to implement all data structures. References being more powerful in Java, is the main reason Java doesn’t need pointers.
----------References are safer and easier to use:
1) Safer: Since references must be initialized, wild references like wild pointers are unlikely to exist. It is still possible to have references that don’t refer to a valid location (See questions 5 and 6 in the below exercise )
2) Easier to use: References don’t need dereferencing operator to access the value. They can be used like normal variables. **‘&’ operator is needed only at the time of declaration.
‘&’ operator is needed only at the time of declaration. Also, members of an object reference can be accessed with dot operator (‘.’), unlike pointers where arrow operator (->) is needed to access members.


What are virtual functions – Write an example?  --- In Java everything is virtual and open to be overriden
Virtual functions are used with inheritance, they are called according to the type of object pointed or referred, not according to the type of pointer 
or reference. In other words, virtual functions are resolved late, at runtime. Virtual keyword is used to make a function virtual.
Following things are necessary to write a C++ program with runtime polymorphism (use of virtual functions) -- runtime polymorphism -- overriding
compile-time polymorphism --- overloading
1) A base class and a derived class.
2) A function with same name in base class and derived class.
********************3) A pointer or reference of base class type pointing or referring to an object of derived class.********************
For example, in the following program bp is a pointer of type Base, but a call to bp->show() calls show() function of Derived class, because bp points to an object of Derived class.
#include<iostream>
using namespace std;
 
class Base {
public:
    virtual void show() { cout<<" In Base \n"; }
};
 
class Derived: public Base {
public:
    void show() { cout<<"In Derived \n"; } 
};
 
int main(void) {   
    Base *bp = new Derived;     
    bp->show();  // RUN-TIME POLYMORPHISM
    return 0;
}

What is this pointer? --- ****this**** does not exist in static functions beacuse they could be used without instantiating!!!!!!
The ‘this’ pointer is passed as a hidden argument to all nonstatic member function calls and is available as a local variable within the 
body of all nonstatic functions. ‘this’ pointer is a constant pointer that holds the memory address of the current object. ‘this’ pointer 
is not available in static member functions as static member functions can be called without any object (with class name).

--------------------------------VTABLE / VPTR---------------------------------------------
What are VTABLE and VPTR?
vtable is a table of function pointers. It is maintained per class.
vptr is a pointer to vtable. It is maintained per object (See this for an example).
Compiler adds additional code at two places to maintain and use vtable and vptr.
1) Code in every constructor. This code sets vptr of the object being created. This code sets vptr to point to vtable of the class.
2) Code with polymorphic function call (e.g. bp->show() in above code). Wherever a polymorphic call is made, compiler inserts code to 
first look for vptr using base class pointer or reference (In the above example, since pointed or referred object is of derived type, 
vptr of derived class is accessed). Once vptr is fetched, vtable of derived class can be accessed. 
Using vtable, address of derived derived class function show() is accessed and called.

Major Differences between JAVA and C++ 

There are lot of differences, some of the major differences are:
Java has automatic garbage collection whereas C++ has destructors , which are automatically invoked when the object is destroyed.
Java does not support pointers, templates, unions, operator overloading, structures etc.
C++ has no in built support for threads,whereas in Java there is a Thread class that you inherit to create a new thread
No goto in JAVA
C++ support multiple inheritance, method overloading and operator overloading but JAVA only has method overloading.
Java is interpreted and hence platform independent whereas C++ isn’t. At compilation time, Java Source code converts into JVM byte code. 
The interpreter execute this bytecode at run time and gives output. C++ run and compile using compiler which converts source code into machine level language.

What are C++ access specifiers ?

Access specifiers are used to define how the members (functions and variables) can be accessed outside the class.
Private: Members declared as private are accessible only within the same class and they cannot be accessed outside the class they are declared. Child classes are also not allowed to access private members of parent.
Public: Members declared as public are accessible from anywhere.
Protected: Only the class and its child classes can access protected members.

Class: Class is a blueprint of data and functions or methods. Class does not take any space.
Object: Objects are basic run-time entities in an object oriented system, objects are instances of a class these are defined user defined data types.
Encapsulation and Data abstraction: Wrapping up(combining) of data and functions into a single unit is known as encapsulation. The data is not accessible to the outside world 
and only those functions which are wrapping in the class can access it. This insulation of the data from direct access by the program is called data hiding or information hiding.
Data abstraction – providing only needed information to the outside world and hiding implementation details. For example, consider a class Complex with public functions as getReal() and getImag().
We may implement the class as an array of size 2 or as two variables. The advantage of abstractions is, we can change implementation at any point, users of Complex class wont’t be affected as our 
method interface remains same. Had our implementation be public, we would not have been able to change it.
Inheritance: Inheritance is the process by which objects of one class acquire the properties of objects of another class. It supports the concept of hierarchical classification. Inheritance provides reusability. This means that we can add additional features to an existing class without modifying it.
Polymorphism: Polymorphism means ability to take more than one form. An operation may exhibit different behaviors in different instances. The behavior depends upon the types of data used in the operation.
Dynamic Binding: In dynamic binding, the code to be executed in response to function call is decided at runtime. C++ has virtual functions to support this.
Message Passing: Objects communicate with one another by sending and receiving information to each other. A message for an object is a request for execution of a procedure and therefore will invoke a function in the receiving object that generates the desired results. Message passing involves specifying the name of the object, the name of the function and the information to be sent.
