public class Complexity {
    public static void main(String[] args){
        method5(256);
    }
    public static void method1(int n){
        int counter = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                counter++;
            }
        }
        System.out.println(counter);
    }

    public static void method2(int n){
        int counter = 0;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }

    public static void method3(int n){
        int counter = 0;
        for(int i = n; i > 1; i/=2){
            counter++;
        }
        System.out.println(counter);
    }

    public static void method4(int n){
        int counter = 0;
        for(int i = 0; i < n; i++){
            for(int j = n; j > 1; j /= 2) {
                ++counter;
            }
        }
        System.out.println(counter);
    }

    public static void method5(int n){
        int counter = 0;
        for(int i = n; i > 1; i/=2) {
            ++counter;
        }
        int temp = 0;
        for(int j = counter; j > 1; j/=2) {
            ++temp;
        }
        System.out.println(temp);
    }

    public static int method6(int n) {
        return method6(n,1);
    }

    public static int method6(int n, int counter){
        if(n <= 0){
            return counter;
        }
        counter *= 2;
        return method6(n-1, counter);
    }
}