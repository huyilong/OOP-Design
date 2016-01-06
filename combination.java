import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {
    //create a list of word lists to test
    ArrayList<String> words1 = new ArrayList<>();
    words1.add("quick");
    words1.add("lazy");
    ArrayList<String> words2 = new ArrayList<>();
    words2.add("brown");
    words2.add("black");
    words2.add("grey");
    ArrayList<String> words3 = new ArrayList<>();
    words3.add("fox");
    words3.add("dog");
    //add the word lists into the dictionary
    ArrayList<ArrayList<String>> dict = new ArrayList<ArrayList<String>>();
    dict.add(words1);
    dict.add(words2);
    dict.add(words3);
    //call the helper and get the combination result
    ArrayList<ArrayList<String>> res = helper(dict);
    //print out the result
    for(ArrayList<String> sub: res){
      for(String s : sub){
        System.out.print(s+" ");
      }
      System.out.println();
    }
  }
  
  private static ArrayList<ArrayList<String>> helper(ArrayList<ArrayList<String>> dict){ 
    ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
    if(dict == null || dict.size() == 0){
      return res;
    }
    //initialize the result with each word in the first word list of dictionary
    ArrayList<String> temp = dict.get(0);
    for(int m=0; m<temp.size(); m++){
        ArrayList<String> sub = new ArrayList<String>();
        sub.add(temp.get(m));
        res.add(new ArrayList<String>(sub));    
    }
    //iteratively fetch the existing words from previous result
    //and combine them with the new words in the dictionary
    ArrayList<String> sub = new ArrayList<String>();
    ArrayList<ArrayList<String>> preres = new ArrayList<ArrayList<String>>();
    for(int i=1; i<dict.size(); i++){
      temp = dict.get(i);     
      for(int j=0; j<res.size(); j++){
        sub = res.get(j);
        for(int k=0; k<temp.size(); k++){
            sub.add(temp.get(k));
            preres.add(new ArrayList<String>(sub)); 
            sub.remove(sub.size()-1);
        } 
      } 
      //prepare for the next iteration
      res = preres;
      preres = new ArrayList<ArrayList<String>>();
    }
    //return the result
    return res;
  }
}

/*
You're given a vector of vectors of words, e.g.: 
[['quick', 'lazy'], ['brown', 'black', 'grey'], ['fox', 'dog']]. 

Write a generalized function that prints all combinations of one word from the first vector, one word from the second vector, etc. 
The solution may not use recursion. 
NOTE: the number of vectors and number of elements within each vector may vary.

For the input above, it should print (in any order): 
quick brown fox 
quick brown dog 
quick black fox 
quick black dog 
... 
lazy grey dog
*/

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {
    //create a list of word lists to test
    ArrayList<String> words1 = new ArrayList<>();
    words1.add("quick");
    words1.add("lazy");
    ArrayList<String> words2 = new ArrayList<>();
    words2.add("brown");
    words2.add("black");
    words2.add("grey");
    ArrayList<String> words3 = new ArrayList<>();
    words3.add("fox");
    words3.add("dog");
    //add the word lists into the dictionary
    ArrayList<ArrayList<String>> dict = new ArrayList<ArrayList<String>>();
    dict.add(words1);
    dict.add(words2);
    dict.add(words3);
    //call the helper and get the combination result
    ArrayList<ArrayList<String>> res = helper(dict);
    //print out the result
    for(ArrayList<String> sub: res){
      for(String s : sub){
        System.out.print(s+" ");
      }
      System.out.println();
    }
  }
  
  private static ArrayList<ArrayList<String>> helper(ArrayList<ArrayList<String>> dict){ 
    ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
    //check illegal input
    if(dict == null || dict.size() < 2){
      return res;
    }
    //initialize the result with each word in the first word list of dictionary
    ArrayList<String> temp = dict.get(0);
    for(int m=0; m<temp.size(); m++){
        ArrayList<String> sub = new ArrayList<String>();
        sub.add(temp.get(m));
        res.add(new ArrayList<String>(sub));    
    }
    //iteratively fetch the existing words from previous result
    //and combine them with the new words in the dictionary
    ArrayList<String> sub = new ArrayList<String>();
    ArrayList<ArrayList<String>> preres = new ArrayList<ArrayList<String>>();
    for(int i=1; i<dict.size(); i++){
      //get the word list from dictionary
      temp = dict.get(i);     
      for(int j=0; j<res.size(); j++){
        //get the existing combinations from result
        sub = res.get(j);
        for(int k=0; k<temp.size(); k++){
            //add the word into combination list
            sub.add(temp.get(k));
            //add the combination into result
            preres.add(new ArrayList<String>(sub)); 
            //delete the last word for next iteration
            sub.remove(sub.size()-1);
        } 
      } 
      //prepare for the next iteration
      res = preres;
      preres = new ArrayList<ArrayList<String>>();
    }
    //return the result
    return res;
  }
}

/*
You're given a vector of vectors of words, e.g.: 
[['quick', 'lazy'], ['brown', 'black', 'grey'], ['fox', 'dog']]. 

Write a generalized function that prints all combinations of one word from the first vector, one word from the second vector, etc. 
The solution may not use recursion. 
NOTE: the number of vectors and number of elements within each vector may vary.

For the input above, it should print (in any order): 
quick brown fox 
quick brown dog 
quick black fox 
quick black dog 
... 
lazy grey dog
*/

