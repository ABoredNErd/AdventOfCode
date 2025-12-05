package advent.year3;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import advent.days.Pair;

/**
 * DayOne
 */
public class DayFive {

    public DayFive(List<String> list){
        System.out.println("<<<<");
        list.forEach(System.out::println);
        System.out.println("<<<<");
        List<Pair<Long, Long>> ranges = new ArrayList<>();

        List<Long> valid = new ArrayList<>();
        boolean first = true;

        for(String row: list){
            if(row.equals("")){
                System.out.println(row);
                first = false;
                continue;
            }
            if(first){
                String[] split = row.split("-");
                Pair<Long, Long> range = new Pair<Long, Long>(Long.valueOf(split[0]), Long.valueOf(split[1]));
                ranges.add(range);
            } else { 
                valid.add(Long.valueOf(row));
            }
        }

        System.out.println(">>>>");
        System.out.println(ranges.size());
        System.out.println(">>>>");

        ranges.sort((f, s) -> {
            Long f_l = f.second - f.first;
            Long s_l = s.second - s.first;
            return s_l.compareTo(f_l);
        });

        List<Pair<Long, Long>> sorted_ranges = new ArrayList<>();

        long total = 0;
        for(Pair<Long, Long> range: ranges){
            Pair<Long, Long> adding_range = new Pair<>(range);
            for(Pair<Long, Long> s_r: sorted_ranges){
                if((range.first <= s_r.second && range.first >= s_r.first) && !(range.second <= s_r.second && range.second >= s_r.first)){
                    adding_range.first = s_r.second + 1;
                }
                if(!(range.first <= s_r.second && range.first >= s_r.first) && (range.second <= s_r.second && range.second >= s_r.first)){
                    adding_range.second = s_r.first - 1;
                }
                if((range.first <= s_r.second && range.first >= s_r.first) && (range.second <= s_r.second && range.second >= s_r.first)){
                    adding_range.second = 0l;
                    adding_range.first = 0l;
                }
            }
            System.out.println();
            System.out.println(adding_range.first + " / " + adding_range.second);
            System.out.println(range.first + " / " + range.second);
            if(adding_range.first != 0 && adding_range.second != 0){
                total += adding_range.second - adding_range.first + 1;
                sorted_ranges.add(adding_range);
            }
        }

        System.out.println(total);
    }
// Answer between 43110500722292 479646489718015
}
