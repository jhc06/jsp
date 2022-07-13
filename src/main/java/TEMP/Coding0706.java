package TEMP;

import java.util.*;

public class Coding0706 {

//ArrayList를 생성하고 런타임시 필요에 의해 객체들을 추가하는 것이 일반적이지만,
// 고정된 객체들로 구성된 List생성할 때도 있다. 이0런 경우에는 Array.asList(T ... a)메소드를 사용하는 것이 간편한다.
    List<Integer> number = Arrays.asList(10, 11, 12, 13, 14, 15);
    List<Integer> list = new ArrayList<>();

    Map<Integer,Integer> map;


    public List<Integer> countNum(){
        for(int value : number){
            int firstRemain = value/1000; // 1000을 나눠서 나온 값
            int secondRemain = value/100; //
            int thirdRemain = value/10; //
            int fourthRemain = value%10; // 10을 나눠서 나온 나머지
            list.add(firstRemain);
            list.add(secondRemain);
        }
        return list;
    }

    public Map<Integer,Integer> result(){
        map = new HashMap<>();
        int countNum = 0; // list값과 비교할 숫자 0부터 9까지 10이 되면 break;
        int count = 0; // 해당되는 숫자가 나온 횟수
        while(true){
            for(int result : list){
                if(result == countNum){
                    count++;
                    map.put(result,count);
                }
            }
            count=0;
            countNum++;
            if(countNum == 10){
                break;
            }
        }
        return map;
    }
    public void print(Map<Integer,Integer> map){
        System.out.println("------------------------------------------");
        for(int i =0; i<10; i++){
            if(map.get(i)==null){
                System.out.println("숫자 "+i+":"+0+"개");
            } else {
                System.out.println("숫자 "+i+":"+map.get(i)+"개");
            }
        }
        System.out.println("------------------------------------------");
        System.out.println("END");
    }

    public void check(){ // Arraylist가 중복된 값을 저장하는지 체크용 메소드
        System.out.println("총 숫자 리스트(12개): ");
        for(int print : list){
            System.out.print(print+"\t");
        }
        System.out.println("");
    }
}
class main{
    public static void main(String[] args) {
////        Coding0706 co = new Coding0706();
//        co.countNum();
//        co.check();
//        co.print(co.result());
    }
}
/*
총 숫자 리스트(12개):
1	0	1	1	1	2	1	3	1	4	1	5
------------------------------------------
숫자 0:1개
숫자 1:7개
숫자 2:1개
숫자 3:1개
숫자 4:1개
숫자 5:1개
숫자 6:0개
숫자 7:0개
숫자 8:0개
숫자 9:0개
------------------------------------------
END
 */