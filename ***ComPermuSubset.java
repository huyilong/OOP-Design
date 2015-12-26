//subset 1


//subset 2
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>> ();
        if(nums.length == 0 || nums == null){
            return res;
            
        }
        
        boolean[] used = new boolean[nums.length];
        List<Integer> sub = new ArrayList<>();
        Arrays.sort(nums);
        //helper(res,sub, used, nums);
        //we need to pass a position because when we used a memeber we do not want to use again
        helper(res,sub, used, nums, 0);
        return res;
    }
   
    
    private void helper(List<List<Integer>> res, List<Integer> sub, boolean[] used, int[] nums , int pos){
        res.add(new ArrayList<Integer>(sub));
        for(int i=pos; i<nums.length; i++){
            //if(nums[i] == nums[i+1] && used[i] == false){
        	//when there is a dup we need to use this condition to avoid dups
            if(i>0 && nums[i-1] == nums[i] && used[i-1] == false){
                continue;
            }
            
            if(!used[i]){
                used[i] = true;
                sub.add(nums[i]);
                helper(res, sub, used, nums, pos+1);
                sub.remove(sub.size()-1);
                used[i] = false;
            }
        }
    }
}

//permutation 1 -- permutation we do not need pos every time start from 0
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0){
            return res;
        }
        List<Integer> sub = new ArrayList<Integer>();
        helper(res, sub, nums);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> sub, int[] nums){
        if(sub.size() == nums.length){
            res.add(new ArrayList<Integer>(sub));
            return;
        }
        
        for(int i=0; i<nums.length; i++){
            //here we are assuming that test case will not contain any dups
            //because permutations we are not just using the number after the current pos
            //therefore every time we are starting from 0
            //and therefore we need to check whether sub has already contained the current pos
            //we cannot use one number multiple times
            
            
            //or if we need to be more strict -- there are dups in the tuple
            //we need to pass an array of used boolean
            //boolean[] used = new boolean[nums.length] -- default as all false
            
            //and we also need to mard and add -- used[i] = true
            //and then unmark and remove -- used[i] = false
            if(sub.contains(nums[i])){
                continue;
            }
            sub.add(nums[i]);//mark
            helper(res, sub, nums);
            sub.remove(sub.size()-1);
            
        }
    }
        
}




//permutation 2
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums.length == 0 || nums == null){
            return res;
        }
        
        List<Integer> sub = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        //here because there could be dups in the array
        ///////we must also do the sorting similar to the combinations
        //very important
        Arrays.sort(nums);
        
        //helper(res, sub, nums, 0);
        helper(res, sub, nums, used);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> sub, int[] nums, boolean[] used){
        // if(index == nums.length){//this is for combination!!!!
        if(sub.size() == nums.length){
            res.add(new ArrayList<Integer>(sub));
            return;
        }
        // 这道题还要考虑的是visited情况，
        //前一个元素就算跟当前元素相同，如果visited==true也没关系。但是如果前面元素跟当前元素相同还没被visited，那么就要做去重处理了。
        
        //this is permutation!!!  we do not need index!!! 
        //index is for combination!!!!
        for(int i=0; i<nums.length; i++){
            //但是如果前面元素跟当前元素相同还没被visited，那么就要做去重处理了。
            if(i>0 && nums[i-1] == nums[i] && !used[i-1]){
                continue;
            }
            
            
            if(!used[i]){
                used[i] = true;
                sub.add(nums[i]);
                helper(res, sub, nums, used);
                sub.remove(sub.size()-1);
                used[i] = false;
            }
        }
        
        // //combinations we need to start from 0
        // for(int i=0; i<nums.length; i++){
        //     // if(nums[i] == nums[i-1]){
        //     //     continue;
        //     // }//this is for no dups
        //     if(sub.contains(nums[i])){
        //         continue;
        //     }
        //     sub.add(nums[i]);
        //     helper(res, sub, nums, i+1);
        //     sub.remove(sub.size()-1);
        // }
    }
}



//combinations -----  passing i+1 as the new start
public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        //Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
        //starting from 1 instead of an array of candidate we need to sort
      
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> sub = new ArrayList<>();
        
        helper(res, sub, n, k, 1);
        return res;
        
        
        
    }
    
    private void helper(List<List<Integer>> res, List<Integer> sub, int n, int k, int start){
        if(k == 0){
            res.add(new ArrayList<Integer>(sub));
            return;
        }
        
        for(int i=start; i<=n; i++){
            //conbination we need this 
            //for combinations we need to each time increase start for next recursion by passing i+1 to avoid using dups
            //permutation we need start from 0 but also check whether used by boolean[] used or just contains
            sub.add(i);
            helper(res, sub, n, k-1, i+1);
            sub.remove(sub.size()-1);
            
        }
    }

}




//combination sum 1   --- passing i as the new start of the loop
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //NP problem -- the polynomial time
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidates == null || candidates.length==0){
            return res;
        }
        List<Integer> item = new ArrayList<>();
        Arrays.sort(candidates);
        helper(candidates, res, item, 0, target);
        return res;
        
    }
    
    
    private void helper(int[] candidates, List<List<Integer>> res, List<Integer> item, int index, int target){
        if(target < 0){
            return;//means this is done
        }
        if(target == 0){
            //must new arraylist item before adding into the final res list
            res.add(new ArrayList<Integer>(item));
            return;
        }
        
        for(int i=index; i<candidates.length; i++){
            // //if each elements just could used once
            // if(i>0 && candidates[i] == candidates[i-1]){
            //     continue;
            // }
            
            item.add(candidates[i]);
            //sorted and i is passed as the new one
            helper(candidates, res, item, i, target - candidates[i]);
            item.remove(item.size()-1);
        }
        
        
    }
}

/*

will this change reflect in the ArrayList?
Yes, since you added a reference to the object in the list. The reference you added will still point to the same object, (which you modified).



or when I add the object to the ArrayList, Java creates a copy and add it to the ArrayList?
No, it won't copy the object. (It will copy the reference to the object.)



What if I change the reference to this object to null? Does that mean that the object in the ArrayList now null too?
No, since the content of the original reference was copied when added to the list. (Keep in mind that it is the reference that is copied, not the object.)

*/