package Spring.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class calculatorTest {
    @Test
    public void testSum(){
        assertEquals(3,calculator.sum(1,2));
    }
}
