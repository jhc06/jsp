package TEMP;

import java.util.ArrayList;
import java.util.List;
// 구구단 출력하기
public class Gugudan{
    List<Integer> resultList;

    public List<Integer> gugudan(int dan){ // 입력된 단수의 결과값을 Arraylist에 넣어 리턴하는 메소드
        resultList = new ArrayList<>();
        int count =0;
        int sum =0;
        while(count<10){
            sum += dan;
            resultList.add(sum);
            count++;
        }
        return resultList;
    }
    public void print(int dan){ // 특정 단만 출력한다.
        System.out.println("구구단 "+dan+"단");
        int count = 1;
        for(int result : gugudan(dan)){
            System.out.println("\t"+ dan+"*"+count+": "+result);
            count++;
        }
    }
    public void printAll(int limit){ // 구구단 전체를 출력한다. limit는 최대 단수;
        int count = 1;
        while(true){
            print(count);
            if(count==limit){
                break;
            }
            count++;
        }
    }
}
class GugudanRun{
    public static void main(String[] args) {
        Gugudan gg = new Gugudan();
        gg.printAll(10); // 구구단 전체를 출력 10*10까지
//        gg.print(7); // 구구단 중 특정 단만 출력
    }
}