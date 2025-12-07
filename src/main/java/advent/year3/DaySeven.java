package advent.year3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DayOne
 */
public class DaySeven {

    private List<List<String>> sub_list = new ArrayList<>();


    private int split(int x, int y){
        int total = 0; 
        int left = x - 1;
        int right = x + 1;
        
        boolean running = true;
        int temp_y = y;
        do {
            if(sub_list.get(temp_y).get(left).equals("^")){
                running = false;
                sub_list.get(temp_y).set(left, ".");
                total += split(left, temp_y);
            } if (sub_list.get(temp_y).get(left).equals("@")){
                running = false;
            }

            temp_y ++;
            if(temp_y >= sub_list.size()){
                running = false;
                return 1;
            }
        }while(running);

        running = true;
        temp_y = y;
        do {
            if(sub_list.get(temp_y).get(right).equals("^")){
                running = false;
                sub_list.get(temp_y).set(right, "@");
                total += split(right, temp_y);
            } if (sub_list.get(temp_y).get(right).equals("@")){
                running = false;
            }
            temp_y ++;
            if(temp_y >= sub_list.size()){
                running = false;
                return 1;
            }
        }while(running);

        return total;
    }

    public DaySeven(List<String> list){
        int start_point = List.of(list.get(0).split("")).indexOf("S");
        list.forEach(str -> {
            sub_list.add(new ArrayList<>());
            String[] split_r = str.split("");
            sub_list.getLast().addAll(List.of(split_r));
        });

        boolean running = true;
        int temp_y = 0;
        int total = 0;
        do {
            if(sub_list.get(temp_y).get(start_point).equals("^")){
                total++;
                running = false;
                sub_list.get(temp_y).set(start_point, ".");
                total += split(start_point, temp_y);
            }
            temp_y ++;
            if(temp_y >= sub_list.size()){
                running = false;
            }
        }while(running);

        sub_list.forEach(row -> {
            row.forEach(System.out::print);
            System.out.println();
        });


        System.out.println("Result: " + total);
    }
}
