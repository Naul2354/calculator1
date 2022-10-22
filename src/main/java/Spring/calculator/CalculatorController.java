package Spring.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
 class CalculatorController {
    @Autowired
    private calculator Calculator;
    @RequestMapping("/sum")
    String sum(@RequestParam("a") Integer a,
                @RequestParam("b") Integer b){
        return String.valueOf(Calculator.sum(a,b));
    }
}
