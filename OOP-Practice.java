The basic unit of OOP is a class, which encapsulates both the static properties and dynamic operations within a "box", 
and specifies the public interface for using these boxes. Since classes are well-encapsulated, 
it is easier to reuse these classes. In other words, OOP combines the data structures and algorithms of a software entity inside the same box.
OOP languages permit higher level of abstraction for solving real-life problems. 
The traditional procedural language (such as C and Pascal) forces you to think in terms of the structure of the computer 
(e.g. memory bits and bytes, array, decision, loop) rather than thinking in terms of the problem you are trying to solve. 
The OOP languages (such as Java, C++ and C#) let you think in the problem space, and use software objects to represent and
 abstract entities of the problem space to solve the problem.

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 *
 * tanay.shah@citadel.com
 */

// n, [a,b, c, d ... n]
// 3, [1, 2, 5] --> 10 [5,5], 5 [5], 3 [1,2]
// 5, [1,5 10, 20, 100] --> 75 [20, 20, 20, 10, 5]
// 5, [1,2,3,5,6]

// number of coins in the system -> n 
// distinct values of each coin -> [1, b, c, d .. n]
// number x that has to be converted to coins

// 6 [1,3,4]
//   4,1,1
//   3,3
        
// min_num
// target-coin[i]

class Solution {
  public static void main(String[] args) {
  }
  
  public int[] leastCoin(int[] coins, int len, int target){
    if(coins == null || len < 1){
      return null;
    }
    ArrayList<Integer> res = new ArrayList<>();
    helper(coins, len, target, res);
    
  }
  
  private ArrayList<Integer> helper(int[] coins, int len, int target, ArrayList<Integer> res){
    //base case
    if(target == 0){
      return res;
    }
    
    int min_number = Integer.MAX_VALUE;
    for(int i=0; i<len; i++){
      if(coins[i] <= target){
         res.add(coins[i]);
         ArrayList<Integer> temp =  helper(coins, len, target-coins[i], res);
        
        if(temp.length +1 < min_number ){
          continue;
          //min_number = temp.length+1;
        }
         res.remove(res.size()-1);
      }
    }
    
    
    
  }
  
  
  
}


public class Bank
{
     private String bankName;
     private int bankID;
     private Address bankAddress;

     public Bank()
     {
         bankName = "?";
         bankID = 0;
         bankAddress = new Address();
     }   

     public String getBankName()
     {
         return bankName;
     }

     public int getBankID()
     {
         return bankID;
     }

     public Address getBankAddress()
     {
         return bankAddress;
     }


tanay.shah@citadel.com



     public void setBankName(String aBankName)
     {
         bankName = aBankName;
     }

     public void setBankID(int aBankID)
     {
         bankID = aBankID;
     }

     public void setBankAddress(String aCity, String aState)
     {
        bankAddress.setCity(aCity);
        bankAddress.setState(aState);
     }

     public String toString()
     {
         return "\nBank name:\t\t" + bankName + "\nBank ID:\t\t" + bankID + "\nBank    Address:\t\t" + bankAddress + "\n\n";
      }

}

public class Address
{
    private String city;
    private String state;

    public Address()
    {
    city = "?";
    state = "?";
    }

    public String getCity()
    {
    return city;
    }

    public String getState()
    {
    return state;
    }

    public void setCity(String aCity)
    {
    city = aCity;
    }

    public void setState(String aState)
    {
    state = aState;
    }

    public String toString()
    {
    return city + "," + state;
    }

}