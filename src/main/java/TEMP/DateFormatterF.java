package TEMP;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatterF {
    public static void main(String[] args) {
        Date time = new Date();
        time.setTime(1000000);
        System.out.println(time);
        System.out.println(time.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(time));
    }
}
