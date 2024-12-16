package advent.days.year2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import advent.days.Pair;

/**
 * Day15
 */
public class Day16 {

    Scanner debug = new Scanner(System.in);

    public record Location(int x, int y){
        public double distance_from(int x, int y){
            double d_x = Math.pow(Math.abs(x - this.x), 2);
            double d_y = Math.pow(Math.abs(y - this.y), 2);

            return Math.sqrt(d_x + d_y);
        }
    };

    public class Score{
        public int ID;
        private static int total = 0;


        public double f_s;
        public double f_e;

        public int x;
        public int y;

        public double weight = 0;

        public boolean is_wall = false;

        public boolean visited = false;

        public Score(double f_s, double f_e, int x, int y){
            this.f_s = f_s;
            this.f_e = f_e;
            this.ID = get_id();
            this.x = x;
            this.y = y;
        }

        private static int get_id(){
            total += 1;
            return total;
        }

        public void set_wall(){
            this.weight = 1000000;
            this.is_wall = true;
        }

        public double get_score(){
            return this.f_s + this.f_e + this.weight;
        }
    }

    Location start;
    Location end;

    List<String[]> map;
    List<List<Score>> map_scores = new ArrayList<>();

    public Day16(List<String> data){
        map = data.stream().map(elm -> elm.split("")).collect(Collectors.toList());

        for (int i = 0; i < map.size(); i ++) {
            for(int j = 0; j < map.get(i).length; j++){
                if (map.get(i)[j].equals("S")) {
                    start = new Location(j, i);
                } else if (map.get(i)[j].equals("E")) {
                    end = new Location(j, i);
                }
            }
        }

        // calc_score
        for (int i = 0; i < map.size(); i ++) {
            map_scores.add(new ArrayList<>());
            for(int j = 0; j < map.get(i).length; j++){
                if (map.get(i)[j].equals("#")) {
                    Score score = new Score(0, 0, j, i);
                    score.set_wall();
                    map_scores.get(i).add(score);
                } else {
                    map_scores.get(i).add(new Score(start.distance_from(j, i), end.distance_from(j, i), j, i));
                }
            }
        }
        

        int x = start.x;
        int y = start.y;
        System.out.println(x + " | " + y);
        boolean found = false;
        Stack<Score> visited = new Stack<>();
        Stack<Score> bad = new Stack<>();
        while (!found) {
            Score next = new Score(100, 0, -1, -1);
            next.set_wall();
            // find next move
            if (map_scores.get(y + 1).get(x).get_score() < next.get_score() && !visited.contains(map_scores.get(y + 1).get(x)) && !bad.contains(map_scores.get(y + 1).get(x))) {
                System.out.println("HERE 1");
                next = map_scores.get(y + 1).get(x);
            }
            if (map_scores.get(y - 1).get(x).get_score() < next.get_score() && !visited.contains(map_scores.get(y - 1).get(x)) && !bad.contains(map_scores.get(y - 1).get(x))) {
                System.out.println("HERE 2");
                next = map_scores.get(y - 1).get(x);
            }
            if (map_scores.get(y).get(x + 1).get_score() < next.get_score() && !visited.contains(map_scores.get(y).get(x + 1)) && !bad.contains(map_scores.get(y).get(x + 1))) {
                System.out.println("HERE 3");
                next = map_scores.get(y).get(x + 1);
            }
            if (map_scores.get(y).get(x - 1).get_score() < next.get_score() && !visited.contains(map_scores.get(y).get(x - 1)) && !bad.contains(map_scores.get(y).get(x - 1))) {
                System.out.println("HERE 4");
                next = map_scores.get(y).get(x - 1);
            }

            Score visit = map_scores.get(y).get(x); 
            if(next.is_wall){
                bad.add(visit);
                next = visited.pop();
                next.visited = false;
            } else {
                visit.visited = true;
                visited.add(visit);
            }


            x = next.x;
            y = next.y;
            if (next.f_e == 0) {
                System.out.println(end);
                System.out.println(next.f_e);
                found = true;
            }

            // debug
            System.out.println(next.ID);
            System.out.println(next.get_score());
            System.out.println(next.x + " | " + next.y);
            // path find
            for(List<Score> row: map_scores){
                for(Score col: row){
                    if (col.is_wall) {
                        System.out.print("====");
                    } else if (col.visited) {
                        System.out.print("----");
                    } else {
                        System.out.print(Math.floor(col.get_score()));
                        //System.out.print(col.ID);
                    }
                    System.out.print(" | ");
                }
                System.out.println();
            }
            debug.nextLine();
        }

    }
}
