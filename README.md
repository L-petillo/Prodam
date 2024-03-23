# Prodam

1R) 2

2R) import java.util.List;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 9, 15, 41, 12, 1, 17, 28);
        
        int maior = list.get(0); 
        
    
        for (int i = 1; i < list.size(); i++) {
            int elemento = list.get(i);
            if (elemento > maior) {
                maior = elemento;
            }
        }
        
        System.out.println(maior);
    }
}
