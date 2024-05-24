package dailydescretedeck.set.services;

import java.util.ArrayList;
import java.util.List;

public class Feature {
    public static <T> void fill(List<List<T>> list, List<T> temp, List<T> colors, int start) {
        if(!temp.isEmpty()) list.add(new ArrayList<>(temp));
        for (int i = start; i < colors.size(); i++) {
            temp.add(colors.get(i));
            fill(list, temp, colors, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
