package advent.year3;

import java.util.ArrayList;
import java.util.List;



/**
 * DayOne
 */
public class DaySix {

    public DaySix(List<String> list){
        System.out.println(">>>>>>>>>>");
        list.forEach(System.out::println);
        System.out.println(">>>>>>>>>>");
        List<String> math_t = new ArrayList<>();
        String[] math_t_a = list.get(list.size() -1).split("");
        for (String t : math_t_a) {
            if(!t.equals(" ")){
                math_t.add(t);
            }
        }
        
        List<List<Long>> numbers = new ArrayList<>();

        for (int i = 0; i < list.size() - 1; i++) {
            numbers.add(new ArrayList<>());
        }

        
        for(int y = 0; y < list.size() - 1; y++){
            String[] row = list.get(y).split("");
            String next_number = "";
            for (int i = 0; i < row.length; i++) {
                if(!row[i].equals(" ")){
                    next_number += row[i];
                } else if (!next_number.equals("")){
                    numbers.get(y).add(Long.valueOf(next_number));
                    next_number = "";
                }
            }
            if(!next_number.equals("")){
                numbers.get(y).add(Long.valueOf(next_number));
            }
        }

        System.out.println("<<<<<<");
        numbers.get(numbers.size() - 1).forEach(System.out::println);
        System.out.println("<<<<<<");



        Long[] totals = new Long[math_t.size()];
        for (int i = 0; i < totals.length; i++) {
            totals[i] = 0l;
        }
        
        for (List<Long> number : numbers) {
            number.forEach(System.out::print);
            System.out.println();
            for (int i = 0; i < number.size(); i++) {
                System.out.println(number.get(i));
                if(math_t.get(i).equals("*")){
                    if(totals[i] == 0l){
                        totals[i] = 1l;
                    }
                    totals[i] *= number.get(i);
                } else if(math_t.get(i).equals("+")){
                    totals[i] += number.get(i);
                }
            }
        }

        Long result = 0l;
            System.out.println();
        for (Long t: totals) {
            System.out.println(t);
            result += t;
        }
        System.out.println();
        System.out.println(result);
    }
}
