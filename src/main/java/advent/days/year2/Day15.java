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


    public boolean check_box(int x, int y, int y_d){
        boolean result = true;
        int x_d = 0;
        if (map.get(y)[x].equals("[")){
            // left
            x_d = 1;
        }
        else if (map.get(y)[x].equals("]")){
            // right
            x_d = -1;
        }

        if(map.get(y + y_d)[x].equals("[") || map.get(y + y_d)[x].equals("]")){
            result = check_box(x, y + y_d, y_d);
        } else if (map.get(y + y_d)[x].equals("#")){
            return false;
        } 

        if(map.get(y + y_d)[x + x_d].equals("[") || map.get(y + y_d)[x + x_d].equals("]")){
            result = result && check_box(x + x_d, y + y_d, y_d);
        } else if (map.get(y + y_d)[x + x_d].equals("#")){
            return false;
        }
        return result;
    }

    public boolean move_box(int x, int y, int y_d){
        boolean result = true;
        int x_d = 0;
        if (map.get(y)[x].equals("[")){
            // left
            x_d = 1;
        }
        else if (map.get(y)[x].equals("]")){
            // right
            x_d = -1;
        }

        if(map.get(y + y_d)[x].equals("[") || map.get(y + y_d)[x].equals("]")){
            result = move_box(x, y + y_d, y_d);
        } 

        if(map.get(y + y_d)[x + x_d].equals("[") || map.get(y + y_d)[x + x_d].equals("]")){
            result = move_box(x + x_d, y + y_d, y_d);
        }
        
        // move up 
        map.get(y + y_d)[x] = map.get(y)[x];
        map.get(y + y_d)[x + x_d] = map.get(y)[x + x_d];

        // replace blank
        map.get(y)[x] = ".";
        map.get(y)[x + x_d] = ".";
        return result;
    }

    public void move(int x_d, int y_d){
        int y = robot_y + y_d;
        int x = robot_x + x_d;
        // make move
        if (!map.get(y)[x].equals("#") && !map.get(y)[x].equals("[") && !map.get(y)[x].equals("]")) {
            map.get(y)[x] = map.get(robot_y)[robot_x];
            map.get(robot_y)[robot_x] = ".";
            robot_x = x;
            robot_y = y;
        } else if (map.get(y)[x].equals("[") || map.get(y)[x].equals("]")){
            if(y_d != 0){
                boolean can_move = check_box(x, y, y_d);
                if (can_move) {
                    move_box(x, y, y_d);

                    map.get(y)[x] = map.get(robot_y)[robot_x];
                    map.get(robot_y)[robot_x] = ".";
                    robot_x = x;
                    robot_y = y;
                }
                System.out.println(can_move);
            } else {
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
        } 
        map.forEach(elm -> System.out.println(String.join("", elm)));
    }

    public Day15(List<String> data){
        int location = data.indexOf("");
        map = data.subList(0, location).stream().map(elm -> {
            elm = elm.replaceAll("\\#", "\\#\\#");
            elm = elm.replaceAll("O", "\\[\\]");
            elm = elm.replaceAll("\\.", "\\.\\.");
            elm = elm.replaceAll("\\@", "\\@.");
            return elm.split("");
        }).collect(Collectors.toList());
        String movements = String.join("", data.subList(location + 1, data.size()));
        map.forEach(elm -> System.out.println(String.join("", elm)));
        System.out.println("Start");
        
        for(int i = 0; i < map.size(); i ++){

            for (int j = 0; j < map.get(i).length; j++) {
                if (map.get(i)[j].equals("@")) {
                    robot_x = j;
                    robot_y = i;
                }
            }
        }
        
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
                if(map.get(y)[x].equals("[")){
                    score += y * 100;
                    score += x;
                }
            }
        }
        System.out.println(score);
        //end
    }
}
