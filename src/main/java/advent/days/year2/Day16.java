package advent.days.year2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public class Raindeer{

        public int current_rotation = 90;

        public int score = 0;

        public Raindeer(){
        }

        public Raindeer(Raindeer r){
            this.score = r.score;
            this.current_rotation = r.current_rotation;
        }

        public void rotate(int rotation_goal){
            if (current_rotation == 0 && rotation_goal == 270) {
                this.score += 1000;
            } else if (current_rotation == 270 && rotation_goal == 0) {
                this.score += 1000;
            } else {
                this.score += 1000 * (Math.abs(current_rotation - rotation_goal) / 90);
            }
            current_rotation = rotation_goal;
        }
        
        public void forward(){
            this.score ++;
        }

    }

    public class Score{
        public int ID;
        private static int total = 0;

        public Raindeer raindeer = new Raindeer();

        public double f_s;
        public double f_e;

        public int x;
        public int y;

        public int move_count = 0;

        public double weight = 0;

        public boolean is_wall = false;

        public boolean visited = false;

        public boolean seen = false;

        public boolean route = false;

        public Score previous = null;

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
            return this.f_s + this.f_e + this.weight + this.move_count + this.raindeer.score;
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
        

        map_scores.get(start.y).get(start.x).visited = true;
        map_scores.get(start.y).get(start.x).move_count = 1;
        map_scores.get(start.y).get(start.x).previous = map_scores.get(start.y).get(start.x);
        Score next = map_scores.get(start.y).get(start.x);

        boolean found = false;
        while (!found) {
            int y = next.y;
            int x = next.x;
            // Create score for surroudning tiles
            map_scores.get(y + 1).get(x).seen = true;
            map_scores.get(y + 1).get(x).move_count = next.move_count + 1;
            if(map_scores.get(y + 1).get(x).previous == null){
                map_scores.get(y + 1).get(x).previous = next;
                map_scores.get(y + 1).get(x).raindeer = new Raindeer(next.raindeer);
                map_scores.get(y + 1).get(x).raindeer.rotate(180);
                map_scores.get(y + 1).get(x).raindeer.forward();
            }

            map_scores.get(y - 1).get(x).seen = true;
            map_scores.get(y - 1).get(x).move_count = next.move_count + 1;
            if(map_scores.get(y - 1).get(x).previous == null){
                map_scores.get(y - 1).get(x).previous = next;
                map_scores.get(y - 1).get(x).raindeer = new Raindeer(next.raindeer);
                map_scores.get(y - 1).get(x).raindeer.rotate(0);
                map_scores.get(y - 1).get(x).raindeer.forward();
            }

            map_scores.get(y).get(x + 1).seen = true;
            map_scores.get(y).get(x + 1).move_count = next.move_count + 1;
            if(map_scores.get(y).get(x + 1).previous == null){
                map_scores.get(y).get(x + 1).previous = next;
                map_scores.get(y).get(x + 1).raindeer = new Raindeer(next.raindeer);
                map_scores.get(y).get(x + 1).raindeer.rotate(90);
                map_scores.get(y).get(x + 1).raindeer.forward();
            }

            map_scores.get(y).get(x - 1).seen = true;
            map_scores.get(y).get(x - 1).move_count = next.move_count + 1;
            if(map_scores.get(y).get(x - 1).previous == null){
                map_scores.get(y).get(x - 1).previous = next;
                map_scores.get(y).get(x - 1).raindeer = new Raindeer(next.raindeer);
                map_scores.get(y).get(x - 1).raindeer.rotate(270);
                map_scores.get(y).get(x - 1).raindeer.forward();
            }

            Score best = map_scores.get(0).get(0);
            for (List<Score> row: map_scores) {
                Optional<Score> result = row.stream().filter(e1 -> e1.seen).filter(e1 -> !e1.visited).sorted((e1, e2) -> (int)Math.floor(e1.get_score() - e2.get_score())).findFirst();
                if (result.isPresent()) {
                    if (result.get().get_score() < best.get_score()) {
                        best = result.get();
                    }
                }
            }
            next = best;
            next.visited = true;

            if(next.f_e == 0){
                found = true;
            }

        }

        System.out.println(next.raindeer.score);
        next.route = true;
        found = false;
        while(!found){
            next.previous.route = true;
            next = next.previous;
            if(next.f_s == 0){
                found = true;
            }
        }
        System.out.println();


        // debug
        // Display map
        for(List<Score> row: map_scores){
            for(Score col: row){
                if (col.is_wall) {
                    System.out.print("====");
                } else if (col.route) {
                    System.out.print("----");
                } else {
                    System.out.print("xxxx");
                    //System.out.print(col.ID);
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
}
