package advent.year3;

import java.util.ArrayList;
import java.util.List;

/**
 * DayOne
 */
public class DayFour {


    public List<String> padding(int length){
        List<String> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            result.add(".");
            
        }
        return result;
    }

    public boolean check(int x, int y, List<List<String>> map){
        int count = 0;
        for(int col = x - 1; col <= x + 1; col++){
            if(map.get(y - 1).get(col).equals("@") || map.get(y - 1).get(col).equals("x")){
                count ++;
            }
        }
        for(int col = x - 1; col <= x + 1; col++){
            if(map.get(y + 1).get(col).equals("@") || map.get(y + 1).get(col).equals("x")){
                count ++;
            }
        }
        if(map.get(y).get(x - 1).equals("@") || map.get(y).get(x - 1).equals("x")){
            if(x == 8 && y == 1){
                System.out.println("Yes row");
            }
            count ++;
        }
        if(map.get(y).get(x + 1).equals("@") || map.get(y).get(x + 1).equals("x")){
            count ++;
        }
        if(x == 8 && y == 1){
            System.out.println(count);
        }
        return count < 4;
    }

    public DayFour(List<String> list){
        List<List<String>> map = new ArrayList<>();
        map.add(padding(list.get(0).length() + 2));
        for (String l : list) {
            List<String> result = new ArrayList<>();
            result.add(".");
            String[] split = l.split("");
            for(String str: split){
                result.add(str);
            }
            result.add(".");
            map.add(result);
        }
        map.add(padding(list.get(0).length() + 2));

        map.forEach(row ->{
            row.forEach(System.out::print);
            System.out.println();
        } );
        System.out.println();

        long result_count = 0;
        boolean running = true;
        while(running){
            long count = 0;
            for (int i = 0; i < map.size() ;i++) {
                for (int j = 0; j < map.get(i).size() ;j++) {
                    if(!map.get(i).get(j).equals("@")){
                        continue;
                    }
                    if(check(j, i, map)){
                        if(i == 1 && j == 8){
                            System.out.println("valid????");
                        }
                        map.get(i).set(j, ".");
                        count++;
                    }
                }
            }
            running = count != 0;
            result_count += count;
        }

        map.forEach(row ->{
            row.forEach(System.out::print);
            System.out.println();
        } );
        System.out.println("Result: " + String.valueOf(result_count));
    }
}
