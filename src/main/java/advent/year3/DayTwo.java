package advent.year3;

import java.util.ArrayList;
import java.util.List;
import advent.days.Pair;

/**
 * DayOne
 */
public class DayTwo {

    public Long result(String value){
        int length = value.length();
        String first = value.substring(0, length / 2);
        String second = value.substring(length / 2);
        if(first.equals(second)){
            return Long.valueOf(value);
        }
        return 0l;
    }
    public DayTwo(List<String> list){
        String[] ranges_split = list.get(0).split(",");

        
        List<Pair<Long, Long>> ranges = new ArrayList<>();

        for (String r: ranges_split) {
            String[] split = r.split("-");
            ranges.add(new Pair<Long,Long>(Long.valueOf(split[0]), Long.valueOf(split[1])));
        }

        long total = 0;
        for (Pair<Long,Long> r : ranges) {
            for(long i = r.first; i <= r.second; i++){
                String i_value = String.valueOf(i);
                total += result(i_value);
            }
        }
        System.out.println();
        System.out.println("Result: ");
        System.out.println(total);
    }
}
