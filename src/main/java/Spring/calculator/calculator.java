package Spring.calculator;

import org.springframework.stereotype.Service;

@Service
public class calculator {
    public static int sum(int a, int b){
        return a+b;
    }
}
