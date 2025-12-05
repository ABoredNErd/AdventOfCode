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
        System.out.println(valid.size());
        System.out.println(">>>>");


        List<Pair<Long, Long>> sorted_ranges = new ArrayList<>();

        for(Pair<Long, Long> first_range: ranges){
            List<Pair<Long, Long>> results = new ArrayList<>();
            Pair<Long, Long> f_range = new Pair<Long,Long>(first_range);
            results.add(f_range);
            for(Pair<Long, Long> s_range: sorted_ranges){
                
                for(Pair<Long, Long> range: results){
                    System.out.println("Here");
                    if((s_range.first <= range.second && s_range.first >= range.first) && (s_range.second <= range.second && s_range.second >= range.first)){
                        Pair<Long, Long> result_left = new Pair<Long,Long>(range.first, s_range.first - 1);
                        Pair<Long, Long> result_right = new Pair<Long,Long>(s_range.second + 1, range.second);
                        results.add(result_left);
                        results.add(result_right);
                    }

                    if((s_range.first <= range.second && s_range.first >= range.first) && !(s_range.second <= range.second && s_range.second >= range.first)){
                        Pair<Long, Long> result = new Pair<Long,Long>(range.first, s_range.first - 1);
                        results.add(result);
                    }
                    if(!(s_range.first <= range.second && s_range.first >= range.first) && (s_range.second <= range.second && s_range.second >= range.first)){
                        Pair<Long, Long> result = new Pair<Long,Long>(s_range.second + 1, range.second);
                        results.add(result);
                    }
                }
            }
            if(results.size() > 1){
                results.remove(f_range);
            }
            sorted_ranges.addAll(results);
        }


        long total = 0;
        for (Pair<Long,Long> s_range : sorted_ranges) {
            total += s_range.second - s_range.first;
        }

        System.out.println(total);
    }
// Answer between 43110500722292 479646489718015
}
