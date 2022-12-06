public class mergeSort {
    public static void merge(int[] nums, int leftFirst, int leftLast, int rightLast){
        int mergezsize = rightLast - leftFirst + 1, mergePos = 0, leftPos = leftFirst, rightPos = leftLast + 1;
        int[] mergeNums = new int[mergezsize];
        while(leftPos <= leftLast && rightPos <= rightLast){
            if(nums[leftPos] <= nums[rightPos]){
                mergeNums[mergePos] = nums[leftPos];
                leftPos++;
            }else{
                mergeNums[mergePos] = nums[rightPos];
                rightPos++;
            }
            mergePos++;
        }
        while(leftPos <= leftLast){
            mergeNums[mergePos] = nums[leftPos];
            leftPos++;
            mergePos++;
        }
        while(rightPos <= rightLast){
            mergeNums[mergePos] = nums[rightPos];
            rightPos++;
            mergePos++;
        }
        for(mergePos = 0; mergePos < mergezsize; mergePos++){
            nums[leftFirst + mergePos] = mergeNums[mergePos];
        }
    }
    public static void sorted(int[] nums,int leftFirst, int rightLast){
        if(leftFirst < rightLast){
            int leftLast = (leftFirst + rightLast) / 2;
            sorted(nums,leftFirst,leftLast);
            sorted(nums,leftLast + 1, rightLast);
            merge(nums,leftFirst,leftLast,rightLast);
        }
    }
    public static void main(String[] args){
        int[] nums = {10, 2, 78, 4, 45, 32, 7, 11};
        int numsize = 8;
        sorted(nums, 0,numsize -1);
        for (int i = 0; i < numsize; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
