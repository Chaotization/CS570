public class insertionSort {
    public static void sorted(int[] nums, int numsizes){
        for(int i = 0; i < numsizes; i++){
            int j = i;
            while(j > 0 && nums[j] < nums[j - 1]){
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;
                j--;
            }
        }
    }
    public static void main(String[] args){
        int[] nums = { 10, 2, 78, 4, 45, 32, 7, 11 };
        int numsizes = 8;
        insertionSort a = new insertionSort();
        a.sorted(nums,numsizes);
        for(int i = 0; i < numsizes;i++){
            System.out.print(nums[i] +" ");
        }
    }
}
