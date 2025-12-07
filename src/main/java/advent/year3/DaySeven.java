package advent.year3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DayOne
 */
public class DaySeven {

    private List<List<String>> sub_list = new ArrayList<>();
    
    private List<List<Long>> solved = new ArrayList<>();

    private long split(int x, int y){
        long total = 0; 
        int left = x - 1;
        int right = x + 1;
        
        boolean running = true;
        int temp_y = y;
        do {
            if(sub_list.get(temp_y).get(left).equals("^")){
                running = false;
                sub_list.get(temp_y).set(left, "@");
                long result = split(left, temp_y);
                total += result;
                solved.get(temp_y).set(left, result);
            } else if (sub_list.get(temp_y).get(left).equals("@")){
                running = false;
                total += solved.get(temp_y).get(left);
            }

            temp_y ++;
            if(temp_y >= sub_list.size()){
                running = false;
                total++;
            }
        }while(running);

        running = true;
        temp_y = y;
        do {
            if(sub_list.get(temp_y).get(right).equals("^")){
                running = false;
                sub_list.get(temp_y).set(right, "@");
                long result = split(right, temp_y);
                total += result;
                solved.get(temp_y).set(right, result);
            } else if (sub_list.get(temp_y).get(right).equals("@")){
                running = false;
                total+= solved.get(temp_y).get(right);
            }
            temp_y ++;
            if(temp_y >= sub_list.size()){
                running = false;
                total ++;
            }
        }while(running);

        if(total == 0){
            System.out.println("Total incorrect");
        }

        return total;
    }
    
    private void print(){
        sub_list.forEach(row -> {
            row.forEach(System.out::print);
            System.out.println();
        });
    }

    public DaySeven(List<String> list){
        int start_point = List.of(list.get(0).split("")).indexOf("S");
        list.forEach(str -> {
            solved.add(new ArrayList<>());
            sub_list.add(new ArrayList<>());
            String[] split_r = str.split("");
            sub_list.getLast().addAll(List.of(split_r));
            for (int i = 0; i < split_r.length; i++) {
                solved.getLast().add(0l);
            }
        });

        print();

        boolean running = true;
        int temp_y = 0;
        long total = 0;
        do {
            if(sub_list.get(temp_y).get(start_point).equals("^")){
                running = false;
                sub_list.get(temp_y).set(start_point, "@");
                total += split(start_point, temp_y);
            }
            temp_y ++;
        }while(running);

        print();


        System.out.println("Result: " + total);
    }
}
