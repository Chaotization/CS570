public class shellSort {
    public static void sorted(int[] nums, int numsize, int startIndex, int gap){
        for(int i = startIndex + gap; i < numsize; i += gap){
            int j = i;
            while(j - gap >= startIndex && nums[j] < nums[j - gap]){
                int temp = nums[j];
                nums[j] = nums[j - gap];
                nums[j - gap] = temp;
                j -= gap;
            }
        }
    }
    public static void main(String[] args){
        int[] nums = { 10, 2, 78, 4, 45, 32, 7, 11 };
        int numsizes = 8, startIndex = 0, gap = 3;
        shellSort a = new shellSort();
        a.sorted(nums,numsizes,startIndex, gap);
        for(int i = 0; i < numsizes;i++){
            System.out.print(nums[i] + " ");
        }
    }
}
