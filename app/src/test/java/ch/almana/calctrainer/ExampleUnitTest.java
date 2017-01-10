package ch.almana.calctrainer;

import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        int min = 3;
        int max = 50;
        ICalulcationBuilder calculationBuilder = new PlusCalculationBuilder();
        for (int i = 0; i < max; i++){
            checkBounds("plus ",calculationBuilder,min,max);
        }
    }

    @Test
    public void subtraction_isCorrect() throws Exception {
        int min = 3;
        int max = 50;
        ICalulcationBuilder calculationBuilder = new MinusCalculationBuilder();
        for (int i = 0; i < max; i++){
            checkBounds("minus ",calculationBuilder,min,max);
        }
    }

    private void checkBounds(String msg, ICalulcationBuilder calculationBuilder, int min, int max) {
        calculationBuilder.build();
        assertTrue(calculationBuilder.toString(),calculationBuilder.getNumber1() >= min);
        assertTrue(calculationBuilder.toString(),calculationBuilder.getNumber1() <= max);
        assertTrue(calculationBuilder.toString(),calculationBuilder.getNumber2() >= min);
        assertTrue(calculationBuilder.toString(),calculationBuilder.getNumber2() <= max);
        assertTrue(calculationBuilder.toString(),calculationBuilder.getResult() >= min);
        assertTrue(calculationBuilder.toString(),calculationBuilder.getResult() <= max);
    }
}