import java.util.ArrayList;
import java.util.Arrays;

public class Route extends Table{
    public static ArrayList<String> route = new ArrayList<>();

    public static void add(String stop) {
        route.add(stop);
    }
    public static String print() {
        return Arrays.toString(route.toArray());
    }
    public static boolean contains(String stop){
        return route.contains(stop);
    }
}
