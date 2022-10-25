package Spring.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class calculatorTest {
    @Test
    public void testSum(){
        assertEquals(5,calculator.sum(2,3));
    }
}
