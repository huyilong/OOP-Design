parking lot - multiple levels
level -- multiple rows of spots

motocycle spots / compact spots / large spots

public enum VehicleSize {
	Motocycle,
	Compact,
	Large
}

Private
Like youd think, only the class in which it is declared can see it.

Package Private
Can only be seen and used by the package in which it was declared. This is the default in Java (which some see as a mistake).

Protected
Package Private + can be seen by subclasses or package member.

Public
Everyone can see it.


Yes, an abstract class can have a constructor. Consider this:

abstract class Product { 
    int multiplyBy;
    public Product( int multiplyBy ) {
        this.multiplyBy = multiplyBy;
    }

    public int mutiply(int val) {
       return multiplyBy * val;
    }
}

class TimesTwo extends Product {
    public TimesTwo() {
        super(2);
    }
}

class TimesWhat extends Product {
    public TimesWhat(int what) {
        super(what);
    }
}
The superclass Product is abstract and has a constructor. 
The concrete class TimesTwo has a constructor that just hardcodes the value 2. 
The concrete class TimesWhat has a constructor that allows the caller to specify 
the value.


Abstract constructors will frequently be used to enforce class constraints or 
invariants such as the minimum fields required to setup the class.

NOTE: As there is no default (or no-arg) constructor in the parent abstract class 
the constructor used in subclasses must be specified.


This is a most frequently asked java interview question. 
The answer is No, interface cannot have constructors. 
In this post we will discuss why

Lets come to the point now: All the methods of interface doesn’t 
have body so there is no need to call the methods in the interface itself. 
In order to call any method we need an object since there is no need 
to have object of interface, there is no need of having constructor in 
interface (Constructor is being called during creation of object).

//abstract class here!!
public abstract class Vehicle{
	protected List<Spot> spots = new ArrayList<>();
	protected String car_id; // this could be the license plate of the car

	//the following two actually are similar
	protected int spotsNeed; // every car has this feature
	protected VehicleSize size;//could only be one of the three size


	public int getSoptsNeed(){
		return spotsNeed;
	}
	public VehicleSize getSize(){
		return size;
	}


	//park into the spots 
	//we need to know 
	public void parkInSpot(Spot s){
		//this is very important!!!
		//we need to finish parking and track it within vehicle instead
		//of the parking lot !!! parking lot is only used for 
		//tell you whether you are able to park!!!
		spots.add(s);
	}

	public void clearSpot(){
		//remove car from the spot 
		//this is exit 
		//compared with the enter defined in parkiInSpot
		//

		notify spot that it is gone
	}
}