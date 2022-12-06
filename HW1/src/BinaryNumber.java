//this assignment is using big-endian to represent binary number

import java.util.Arrays;

public class BinaryNumber {
    private int binaryNum[];
    private boolean overflow = false;

    public int[] getBinaryNum() {
        return binaryNum;
    }

    public BinaryNumber(int length) {
        binaryNum = new int[length];
        Arrays.stream(binaryNum).forEach(value -> value = 0);
    }

    public BinaryNumber(String str) {
        binaryNum = new int[str.length()];
        for (int i = str.length() - 1; i >= 0; i--) {
            binaryNum[i] = Character.getNumericValue(str.charAt(i));
        }
    }

    public int getLength() {
        return binaryNum.length;
    }

    public int getDigit(int index) {
        if (index > binaryNum.length - 1) {
            throw new IndexOutOfBoundsException("the given index is beyond the length of the binary number");
        }
        return binaryNum[binaryNum.length - index - 1];
    }

    public void shiftR(int amount) {
        //110 ->110 000
        int[] temp = binaryNum;
        binaryNum = new int[temp.length + amount];
        for (int i = 0; i < temp.length; i++) {
            binaryNum[i] = temp[i];
        }
        for (int j = temp.length; j < binaryNum.length; j++) {
            binaryNum[j] = 0;
        }
    }

    public void add(BinaryNumber aBinaryNumber) {
        if (aBinaryNumber.binaryNum.length != this.binaryNum.length) {
            System.out.println("the lengths of the binary numbers do not coincide");
        }
        int carrier = 0;
        String sum = "";
        for (int i = binaryNum.length - 1; i >= 0; i--) {
            if (binaryNum[i] + aBinaryNumber.binaryNum[i] + carrier == 0) {
                carrier = 0;
                sum = "0" + sum;
            } else if (binaryNum[i] + aBinaryNumber.binaryNum[i] + carrier == 1) {
                carrier = 0;
                sum = "1" + sum;
            } else if (binaryNum[i] + aBinaryNumber.binaryNum[i] + carrier == 2) {
                carrier = 1;
                sum = "0" + sum;
            } else {
                carrier = 1;
                sum = "1" + sum;
            }
        }
        if (carrier == 1) {
            sum = "1" + sum;
        }
        if (sum.length() > aBinaryNumber.getLength()) {
            this.overflow = true;
            System.out.println(" memory overflow");
        } else {
            int[] tempArray = new int[sum.length()];
            for (int i = 0; i < sum.length(); i++) {
                tempArray[i] = Character.getNumericValue(sum.charAt(i));
            }
            aBinaryNumber.binaryNum = tempArray;
        }
    }

    @Override
    public String toString() {
        if(overflow){
            return "Overflow";
        }
        String output = "";
        for (int i = 0; i < binaryNum.length; i++) {
            output += binaryNum[i];
        }
        return output;
    }

    public int toDecimal() {
        int total = 0;
        String str = "";
        for (int i = 0; i < this.binaryNum.length; i++) {
            str += binaryNum[i];
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            if (i == str.length() - 1) {
                total += Character.getNumericValue(str.charAt(i));
            } else {
                int multiplier = 1;
                for (int j = 0; j < str.length() - i - 1; j++) {
                    multiplier *= 2;
                }
                total += multiplier * Character.getNumericValue(str.charAt(i));
            }
        }
        return total;
    }

    public void clearOverflow() {
        this.overflow = false;
    }
    
}