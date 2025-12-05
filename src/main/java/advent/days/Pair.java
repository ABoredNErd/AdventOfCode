package advent.days;

public class Pair<T1, T2> {
    public T1 first;
    public T2 second;

    public Pair(T1 v1, T2 v2){
        first = v1;
        second = v2;
    }

    public Pair(Pair<T1, T2> right){
        this.first = right.first;
        this.second = right.second;
    }
}
