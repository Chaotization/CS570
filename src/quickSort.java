public class quickSort {
    public static int Partition(int[] nums, int lowIndex, int highIndex){
        int mid = lowIndex + (highIndex - lowIndex) / 2, pivot = nums[mid];
        boolean result = false;
        while(!result){
            while(nums[lowIndex] < pivot){
                lowIndex++;
            }
            while(pivot < nums[highIndex]){
                highIndex--;
            }
            if(lowIndex >= highIndex){
                result = true;
            }else{
                int temp = nums[lowIndex];
                nums[lowIndex] = nums[highIndex];
                nums[highIndex] = temp;
                lowIndex++;
                highIndex--;
            }
        }
        return highIndex;
    }
    public static void sorted(int[] nums, int lowIndex, int highIndex){
        if(lowIndex >= highIndex) return;
        int lowEndIndex = Partition(nums, lowIndex, highIndex);
        sorted(nums, lowIndex,lowEndIndex);
        sorted(nums, lowEndIndex + 1,highIndex);
    }
    public static void main(String[] args) {
        int[] nums = {10, 2, 78, 4, 45, 32, 7, 11};
        int low = 0, high = 8;
        quickSort a = new quickSort();
        a.sorted(nums, low, high -1);
        for (int i = 0; i < high; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
