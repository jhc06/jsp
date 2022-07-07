package TEMP;

import java.sql.Array;
import java.util.Random;

public class RandomOneSix {
    public static void main(String[] args) {
        //Math 클래스 활용
        //Math.random은 0~0.9999 사이의 double 값을 리턴한다.
        int mathResult = (int)(Math.random()*6)+1;
        System.out.println(mathResult);

        //random 클래스활용
        Random rd = new Random();
        int randomResult = rd.nextInt(6);
        System.out.println(randomResult);

    }
}
