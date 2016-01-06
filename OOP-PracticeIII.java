有一题是这样

CCCCCCCCCCENER -- 多结合自己的情况去说  多结合自己做过的项目 多想一些情况  多说一些内容  一定要不停地问问问  一定要不停地说说说
MVC is a software architecture - the structure of the system - that separates domain/application/business 
(whatever you prefer) logic from the rest of the user interface. It does this by separating the application 
into three parts: the model, the view, and the controller.

The model manages fundamental behaviors and data of the application. It can respond to requests for information, 
respond to instructions to change the state of its information, and even to notify observers in event-driven 
systems when information changes. This could be a database, or any number of data structures or storage systems.
 In short, it is the data and data-management of the application.

The view effectively provides the user interface element of the application. It'll render data from the model 
into a form that is suitable for the user interface.

The controller receives user input and makes calls to model objects and the view to perform appropriate actions.

All in all, these three components work together to create the three basic components of MVC.
Design a class which stores demographic information of a person like name, address, phone number, male/female. 
What if the person has any prefix to the name, multiple phone numbers/addresses, 
how will you handle dob in case of multiple locations
(If a person is both at 12 PM in USA on a date, then that date is different from date in India), 
how will you handle the validations of these fields when the user enters the values


目前的想法比较单纯 Code 大概是这样

class Patient {
        String name;
        String address;
        String phoneNumber;
        boolean gender;
        String dob; 
}

但是要加 prefix 或 multiple phonenumbers/addresses，就要去改变的类里面的变量。
应该能有更好的方法吧 ..

求指教

public interface Factory{
	public Patient getPatient();
}
public interface Patient{
	public String getName();
}

public class PatientBean implements Patient{
	String name;
	@Override
	public String getName(){
		return this.name;
	}
}

public class BeanFactory implements Factory{
	@Override
	public Patient getPatient(){
		return new PatientBean();
	}
}
public class Test{

public static void main(String[] args){
Factory f = new BeanFactory();
Patient p = f.getPatient();
System.out.println(p.getName());
}
/*After couple of month, add a prefix before name*/
public class PatientBeanV2 extends PatientBean{
	String prefix;
	@Override
	public String getName(){	
	return prefix+" " + name;
} 
}
//modify class BeanFactory, getPatient() method, return new PatientBeanV2();
但是要加 prefix 或 multiple phonenumbers/addresses，就要去改变的类里面的变量。
受教了 谢谢你的回答
想请问这样子如果没有prefix 但有两只phoneNumber 就需要再新创一个类(PatientBeanV3) 吗？



这篇拖了一会，不好意思让有在等的同学等了一下。 >< .


有gym，有三个campus，cube 都挺大的。
关于他家福利，最让员工开心的似乎就是有on campus clinic，看病做检查都免费。当然一方面也是为了测试自家产品。. 
关于housing，据午餐时间陪聊的工程师说，Downtown 的rental building，一个1bed 大概是$750 - $1100 (全包)。
刚进公司时住在 downtown 觉得好玩。工作两年后在郊区买了房，每天开车上班，不太堵车，好不愉快... 现在想要努力升Software Architect Manager... 

由于先前已经做过一次HireVue Interview，所以Onsite 只有两轮。 (结束后被带去吃饭的时候还一阵错愕..) 
基本流程是这样 
1. 问问履历 
2. 各种 Behavior 
3. Technical Interview (都会给一份problem statement，上面写了要做的题目) 
4. Ask questions

Part I - HireVue

履历 & Behavior
1. Introduce yourself
2. What will you do when a client report there is a error?
3. Talk about your project at intern
4. How did you get along with you manager? (顺便聊聊实习时的主管推荐了什么书)
5. Do you prefer front-end or back-end?.


Technical Part. Waral 
Allergy
build a system for entering and displaying the allergies that patients may have. The allergy will have its own set of symptoms or reactions. 

Person Demographics. 
Design a Person model and a person's demographics are intrinsic to a 'Person' model.



Part II - Onsite

履历 & Behavior 
1. Introduce yourself
2. Talk about a project that you have completed with a group (面试官似乎满有兴趣就聊了很多)
2-1. How did you pick your part to complete the project?
2-2. What did other teammate do?
2-3. How did your team seperate the works?
3. Talk about your internship (之后就聊了实习时做的 project)
3-1. What did you do when you are doing internship?
3-2. How did you face problems when doing your project?
4. What are the difficulties when you learn to code?
5. How do you debug your code? Talk about a situatoin.. 


Technical Part
1. Design a class that can track a patient's drug frequency. 
吃药的frequency 可以是一天两次或三次或四次，也可以是PRN (Take when needed)。 
会规定一个time period，所以要记录start date 和end date。


public void remind(int frequency, boolean isPRN){
	int hours = 24/frequency;
	long millsecond = hours * 3600 * 1000;
	if(!isPRN){
		while(frequency > 0){
			Thread.sleep(millsecond);
			System.out.println("take medicine!");
			frequency -- ;
		}
	}else{
		System.out.println("take medicine!");
	}
}

public class SleepMessages {
    public static void main(String args[])
        throws InterruptedException {
        String importantInfo[] = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
        };

        for (int i = 0;
             i < importantInfo.length;
             i++) {
            //Pause for 4 seconds
            Thread.sleep(4000);
            //Print a message
            System.out.println(importantInfo[i]);
        }
    }
}



2. Log file and concurrency
会给一张纸，上面是关于不同thread access 同一个数据库Log。 问根据这个Log，会发现什么奇怪的地方?

Part III - Onsite 
履历 & Behavior 
1. Talk about a project you have done outside of school
2. What did you do during internship?
3. Talk about a time that you managed to solve a problem that somebody else said something else different. (有的人说这样，有的说那样，你怎么做正确的判断)
4. Talk about an experience that you went to a face to face meeting and need to provide feedback to a sensitive person

Technical Part 
1. 因为有很长的statement 所以我还是用中文大概简化一番。 简单说，就是写程序尤其像数据库会有debug。
但有时候这些bug 可能需要手动除错，一旦手动就是会很麻烦很讨厌。既然这样很烦，那你之后怎么把它做成 automate 的方式？ 
如果你没有这种情况，那想像一下你碰到这样的情况会怎么做


2. Design a class that hold temperature from patient.
这题没什么特别的，就是注意要有单位，温度，这体温是从哪里来的(嘴巴？还是耳朵？). 
之後再 implement 一個 hasFever method。

private int threshold = 37;
boolean hasFever(int temperature){
	return temperature > threshold ? true :false;
}

Enums are lists of constants. When you need a predefined list of values which do not represent some kind of numeric or textual data, 
you should use an enum. For instance, in a chess game you could represent the different types of pieces as an enum:

public enum Pos {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY 
}
public class CrunchifyEnumExample {
 
	public enum Company {
		EBAY(30), PAYPAL(10), GOOGLE(15), YAHOO(20), ATT(25);
		private int value;
 
		private Company(int value) {
			this.value = value;
		}
	}
 
	public static void main(String[] args) {
		for (Company cName : Company.values()) {
			System.out.println("Company Value: " + cName.value + " - Comapny Name: " + cName);
		}
	}
}
enum ChessPiece {
PAWN,
ROOK,
KNIGHT,
BISHOP,
QUEEN,
KING;
}
public class CrunchifyEnumExample {
 
	public enum Company {
		EBAY, PAYPAL, GOOGLE, YAHOO, ATT
	}
 
	Company cName;
 
	public CrunchifyEnumExample(Company cName) {
		this.cName = cName;
	}
 
	public void companyDetails() {
		switch (cName) {
		case EBAY:
			System.out.println("Biggest Market Place in the World.");
			break;
 
		case PAYPAL:
			System.out.println("Simplest way to manage Money.");
			break;
 
		case GOOGLE:
		case YAHOO:
			System.out.println("1st Web 2.0 Company.");
			break;
 
		default:
			System.out.println("Google - biggest search giant.. ATT - my carrier provider..");
			break;
		}
	}
 
	public static void main(String[] args) {
		CrunchifyEnumExample ebay = new CrunchifyEnumExample(Company.EBAY);
		ebay.companyDetails();
		CrunchifyEnumExample paypal = new CrunchifyEnumExample(Company.PAYPAL);
		paypal.companyDetails();
		CrunchifyEnumExample google = new CrunchifyEnumExample(Company.GOOGLE);
		google.companyDetails();
		CrunchifyEnumExample yahoo = new CrunchifyEnumExample(Company.YAHOO);
		yahoo.companyDetails();
		CrunchifyEnumExample att = new CrunchifyEnumExample(Company.ATT);
		att.companyDetails();
	}
}
public boolean hasFever(int temp, String pos){
	Swtch(pos){

	}
}

public class Temperaure{
	private enum Area {
		MOUTH, HEAD, HAND, ARM, LEG
	}
	private int temp;
	private Area pos;
	private boolean fever;

	public boolean hasFever(int temp, Area pos){
		boolean fever = false;
		Swtch(pos){
			case MOUTH:
				fever = temp > 45 ? true : false;
				break;
			case HEAD:
				fever = temp > 40 ? true : false;
				break;
			default:
             throw new IllegalArgumentException("Invalid day of the week: " + dayOfWeekArg);
		}
		return fever;
	}

	//using string would be more convenient here...
}

public String getTypeOfDayWithSwitchStatement(String dayOfWeekArg) {
     String typeOfDay;
     switch (dayOfWeekArg) {
         case "Monday":
             typeOfDay = "Start of work week";
             break;
         case "Tuesday":
         case "Wednesday":
         case "Thursday":
             typeOfDay = "Midweek";
             break;
         case "Friday":
             typeOfDay = "End of work week";
             break;
         case "Saturday":
         case "Sunday":
             typeOfDay = "Weekend";
             break;
         default:
             throw new IllegalArgumentException("Invalid day of the week: " + dayOfWeekArg);
     }
     return typeOfDay;
}
Update
If you dont like the above, you can make the task measure its own execution time, as below:

int totalTime = 50000; // in nanoseconds
long startTime = System.getNanoTime();
boolean toFinish = false;

while (!toFinish) 
{
    System.out.println("Task!");
    ...
    toFinish = (System.getNanoTime() - startTime >= totalTime);
}

后话 
Problem statement 有点长，前天搭飞机很折腾，早上还晕乎乎没睡饱，看得很想睡觉。 
面这间就是speaking 和reading 不要太差吧，聊天聊得欢快重要。 
做class design 的时候就是说自己的想法，因为面试官还会提出不同的看法或是一些他觉得需要的东西，可能要根据他说的去做一些修改。 
最后让问问题时，要一直问一直问，所以多准备一些问题。当场想也挺好想的，就从他回答的一些答案挑问题就好。
记得再从他的回答里，跟自己的project 做点结合讲给他们听，好像面试官满开心。

听说薪水不怎么样，面得开心就好。
餐厅的印度菜很好吃。 旁边还有卖生鲜蔬菜水果，挺不错的。 
关于HireVue 的部分，我有存完整的problem statement。不知道能不能这样公开放上来，有需要的同学可留email，我再寄出..



请问楼主drug frequency那道题可以描述一下吗？多谢！
那时面试官要我想什么答什么我就写constructor 里面放startdate, enddate, 一个boolean value 看这个药是不是take when needed，
和一个list 装吃药时间eg 如果一天吃两次我就随便设了24 /2 = 12, 那就是每天凌晨12点跟中午12点要吃药.. 这样子...



分享一下昨天的onsite。之前面过一轮video，所以只有两轮1 to 1。题目地里都有了，Behavior加ood。
第一轮，白人小哥。
1. 他先介绍自己，然后我自我介绍。
2. 讲一个project out of school.
3. why software engineer.
4. ood: Medicine schedule problem with different frequencies. 
How to decide the data structure.

第二轮，白人大叔。. 
1. talk about your intern.
2. The most interesting/hardest issue you worked on.. 
3. How do you give a feedback to sb
4. ood: temperaure design and write an algorithm for deciding fever.

Cerner 有4个campus 。我们在inovation campus面，这里90%以上都是engieer。
他们好像在India有个很大的branch，不知道是不是support department。downtown有明显的美国中西部风情。比较安静。



补充内容 (2015-9-4 08:13):
video: 
1. Design an allergy class. 
2. Design a Person demographics class
3. Behaviors, behaviors, behaviors

ood: temperaure design and write an algorithm for deciding fever.
这题的具体要求是怎样呢？我看了一下其他的帖子，说记录测温的身体部位和温度单位，这是用string来记录就可以了的意思吗？谢谢lz

之前没店面过，所以onsite有三轮。都是版上的经典题目。其中有一个数据库的多线程访问，说有两个user在访问数据库，其中一个user用了两个process。
好像是因为那一个用户的多process （而非两个用户）才导致了concurrent problem。


楼主可以详细讲一下Allergy那题吗?
怎么search到Allergy
用reaction和Allergy的map?

public class PatientHealthInfo {
public static void main(String[] args) {
Patient pt = new Patient();
pt.setName("jack");
Allergy al = new Allergy();
al.setAllergyName("rashes");
al.setSeverity("medium");
Allergy al1 = new Allergy();
al1.setAllergyName("sweling");
al1.setSeverity("medium");
List<Allergy> allergyList = new ArrayList<Allergy>();
allergyList.add(al);
allergyList.add(al1);
pt.setAllergyList(allergyList);
String str = patient(pt);

System.out.println(" Result is :: " + str);
}

public static String patient(Patient pt){
if(pt.getAllergyList().size() > 0){
return "allergy";
}
else if (pt.getDiseaseList().size() > 0){
return "diease";
}
return null;
}

}
====================

public class Patient {

private String name;

private int age;

private List<Allergy> allergyList; 

private List<Disease> diseaseList;


public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public int getAge() {
return age;
}

public void setAge(int age) {
this.age = age;
}

public List<Allergy> getAllergyList() {
return allergyList;
}

public void setAllergyList(List<Allergy> allergyList) {
this.allergyList = allergyList;
}

public List<Disease> getDiseaseList() {
return diseaseList;
}

public void setDiseaseList(List<Disease> diseaseList) {
this.diseaseList = diseaseList;
}
}
=================================================
public class Allergy {

private String allergyName;

private String severity;

public String getAllergyName() {
return allergyName;
}

public void setAllergyName(String allergyName) {
this.allergyName = allergyName;
}

public String getSeverity() {
return severity;
}

public void setSeverity(String severity) {
this.severity = severity;
}
}
====================================
public class Disease {

private String diseaseName;

}


Package com.niyamit.services;
 
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
 
/**
 * Entity that represents a person
 * @author  
 */
@Entity
public class Person implements Serializable {
    // address associated with the person
    Address address = new Address();
     
     
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "person_seq_gen")
    @SequenceGenerator(name="person_seq_gen", sequenceName="PERSON_SEQ")
    private Long id;
     
    @Column(name = "first_name", nullable = false)
    private String firstName;
     
    @Column(name = "last_name", nullable = false)
    private String lastName;
     
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public Address getAddress() {
        return address;
    }
 
    public void setAddress(Address address) {
        this.address = address;
    }
     
    public String getPersonAddress() {
        String actualAddress= address.getHouseNumber() + address.getStreetAddress() + ", " + address.getCity() + ", " + address.getState() + "  " + address.getZipCode();
        return actualAddress;
    }
     
}
 
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
 
 
/**
 *
 * @author  
 */
@Entity
public class Address implements Serializable {
    // person who is associated with the address
     
    Person person;
     
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "address_seq_gen")
    @SequenceGenerator(name="address_seq_gen", sequenceName="ADDRESS_SEQ")
    private Long id; 
     
    @Column(name = "houseNumber", nullable = false)
    private int houseNumber;
     
    @Column(name = "streetAddress", nullable = false)
    private String streetAddress;
     
    @Column(name = "city", nullable = false)
    private String city;
     
    @Column(name = "state", nullable = false)
    private String state;
     
    @Column(name = "zipCode", nullable = false)
    private String zipCode;
     
    public Person getPerson() {
        return person;
    }
 
    public void setPerson(Person person) {
        this.person = person;
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
 
    public String getState() {
        return state;
    }
 
    public void setState(String state) {
        this.state = state;
    }
 
    public String getZipCode() {
        return zipCode;
    }
 
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
 
    public int getHouseNumber() {
        return houseNumber;
    }
         
    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }
 
    public String getStreetAddress() {
        return streetAddress;
    }
 
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
         
}

来个Cerner面经吧，虽然去面的人不多... 
本人8/13 onsite， 两个背靠背的面试，各一小时左右 
1. 第一面：问了些 behavior 问题，但是面我的香港大哥好像不不在意这些，然后草草的跳到了OOD环节，要求设计一个class来存储药剂方，
会提供一个关于calendar的api，然后要写一个function是提供一个输入（用药频率，药剂方object），
输出一个所有的含有用药时间的calendar 。
然后这题结束后有一个服务器存储日志的案例，实际上是race condition，让你说一下具体怎么发生的，然后问怎么解决。 


2. 第二面：大量behavior问题，例如做过什么项目，有没有利用什么方法把繁琐的事情自动化的经历。本人的一个项目恰好是实验室数据自动收集软件，所以说了以后面试官说这个问题已经被你回答了。最后behavior结束以后，来了个设计体温的class问题，要求能记录测量的位置，测量时候所用的单位，写一个方法如何判断是否发烧。发烧的条件大概是在同一个位置，同一个单位测量的条件下，体温差大于某个数。 

面试结束后，就和员工吃饭，然后项目负责人大概讲解一下新人的培训之类的东东。 

8/21给发了offer，67k+3k relocate。大概就这样了。 
Design an allergy class. The allergy will have its own set of symptoms reactions. The allergy will also have a spectrum of severity which the clinician should be aware of, and allergies can be reported by the patient or by next of kin..
Give rationales of your choice of data type. What if we need an onset date of the allergy or report date of the allergy? What if we need to pull something information out of the allergy such as a treatment method of this allergy?
就是allergy和person demographic设计两个class



public void remind(int timer, int dose){
	Thread.sleep(timer);
	Syetem.out.println("Time to take medicine")
}


int totalTime = 50000; // in nanoseconds
long startTime = System.getNanoTime();
boolean toFinish = false;

while (!toFinish) 
{
    System.out.println("Task!");
    ...
    toFinish = (System.getNanoTime() - startTime >= totalTime);
}


HR效率很差，视频面试预约了3周才约上，今天早上进行了一个小时的面试。面试官是个老美，在公司工作了14年的Architect。

问了很多behavioral question， 我被问到的：
You have 5 things to be done. Which one do you do first?
When did you take feedback from others? What were the results?
Explain any project you had worked on?
How do you approach if a client encountered an error in the application? How do you approach if a client encountered multiple errors in the application?

What is your favorite data structure, in what situation have you had to use it?

What are your favorite programming languages?

Tell me your experience in learning new technologies and the difficulties you faced during that.

How do you keep up with the rapid evolving software technologies?
.
Techinical Question:. 
Design an allergy class. The allergy will have its own set of symptoms reactions. The allergy will also have a spectrum of 
severity which the clinician should be aware of, and allergies can be reported by the patient or by next of kin.. 
Give rationales of your choice of data type. What if we need an onset date of the allergy or report date of the allergy? 
What if we need to pull something information out of the allergy such as a treatment method of this allergy?.

感觉Cerner技术什么的确实不难，比较看重social skill， 技术类的问题多是OO design。 可能developer需要经常和客户打交道，沟通解决客户问题之类的，对表达能力比较看重。.

求通过，求Onsite。


补充内容 (2015-8-15 03:36):
补充onsite面经： 今天早上去了Cerner Innovation Campus， 面了两轮，和之前视频这一轮基本一样，一半bahavioral一半Design。 Design也是Glassdoor上面已经有的Medication Schedule Class和Temperature Class。


