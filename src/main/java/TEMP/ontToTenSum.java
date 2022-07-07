package TEMP;

public class ontToTenSum {
    public static void main(String[] args) {
        int sum = 0;
        int count = 0;
// for문 이용
//        for(int i =0; i<10; i++){
//            sum += (i+1);
//        }

// while문 이용
        while(count<=10){
            sum += count;
            count++;
        }

        System.out.println(sum);
        int a = 3;
        double bc = a/3;
        System.out.println(bc);
    }
}
