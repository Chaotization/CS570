public class selectionSort {
    public static void sorted(int[] nums, int numsizes){
        for(int i = 0; i < numsizes - 1; i++){
            int smallestIndex = i;
            for(int j = i + 1; j < numsizes; j++){
                if(nums[j] < nums[smallestIndex]){
                    smallestIndex = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[smallestIndex];
            nums[smallestIndex] = temp;
        }
    }
    public static void main(String[] args){
        int[] nums = { 10, 2, 78, 4, 45, 32, 7, 11 };
        int numsizes = 8;
        selectionSort a = new selectionSort();
        a.sorted(nums,numsizes);
        for(int i = 0; i < numsizes;i++){
            System.out.print(nums[i] +" ");
        }
    }
}
