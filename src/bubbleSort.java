public class bubbleSort {
    public static void sorted(int[] nums, int numsize){
        for(int i = 0; i < numsize - 1; i++){
            for(int j = 0; j < numsize - i - 1; j++){
                if(nums[j] > nums[j + 1]){
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }
    public static void main(String[] args){
        int[] nums = {10, 2, 78, 4, 45, 32, 7, 11};
        int numsize = 8;
        sorted(nums, numsize);
        for (int i = 0; i < numsize; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
