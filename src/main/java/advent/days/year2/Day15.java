package advent.days.year2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import advent.days.Pair;

/**
 * Day15
 */
public class Day15 {

    List<String[]> map = new ArrayList<>();

    int robot_x = -1;
    int robot_y = -1;


    public void move(int x_d, int y_d){
        int y = robot_y + y_d;
        int x = robot_x + x_d;
        // make move
        if (!map.get(y)[x].equals("#") && !map.get(y)[x].equals("O")) {
            map.get(y)[x] = map.get(robot_y)[robot_x];
            map.get(robot_y)[robot_x] = ".";
            robot_x = x;
            robot_y = y;
        } else if (map.get(y)[x].equals("O")){
            boolean end = false;
            int temp_y = y;
            int temp_x = x;
            do{
                System.out.println("Here1");
                if(map.get(temp_y)[temp_x].equals(".")){
                    while(temp_y != robot_y - y_d || temp_x != robot_x - x_d){
                        System.out.println("Here");
                        map.get(temp_y)[temp_x] = map.get(temp_y - y_d)[temp_x - x_d];
                        temp_y -= y_d;
                        temp_x -= x_d;
                    }
                    System.out.println("Here23");
                    map.get(robot_y)[robot_x] = ".";
                    robot_x = x;
                    robot_y = y;
                    end = true;
                } else if (map.get(temp_y)[temp_x].equals("#")){
                    end = true;
                }
                temp_y += y_d;
                temp_x += x_d;
            } while(!end);
        } 
        map.forEach(elm -> System.out.println(String.join("", elm)));
    }

    public Day15(List<String> data){
        int location = data.indexOf("");
        map = data.subList(0, location).stream().map(elm -> {
            return elm.split("");
        }).collect(Collectors.toList());
        String movements = String.join("", data.subList(location + 1, data.size()));
        
        for(int i = 0; i < map.size(); i ++){

            for (int j = 0; j < map.get(i).length; j++) {
                if (map.get(i)[j].equals("@")) {
                    robot_x = j;
                    robot_y = i;
                }
            }
        }
        System.out.println(robot_x);
        System.out.println(robot_y);
        
        for (String move : movements.split("")) {
            System.out.println(move);
            if (move.equals(">")) {
                this.move(1, 0);
            } 
            else if (move.equals("<")) {
                this.move(-1, 0);
            } 
            else if (move.equals("^")) {
                this.move(0, -1);
            } 
            else if (move.equals("v")) {
                this.move(0, 1);
            } 
        }

        int score = 0;
        for(int y = 0; y < map.size(); y ++){
            for(int x = 0; x < map.get(y).length; x ++){
                if(map.get(y)[x].equals("O")){
                    score += y * 100;
                    score += x;
                }
            }
        }
        System.out.println(score);
        //end
    }
}
