package ch.almana.calctrainer;

import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by vogtp on 11.01.17.
 */

public class DivisionCalculationBuilder extends BaseCalculationBuilder implements ICalulcationBuilder {
    private static final String TAG = "CalcTrainer.Division";


    private int number1;
    private int number2;

    public DivisionCalculationBuilder(SharedPreferences preferences) {
        super(preferences);
    }

    @Override
    public String getName() {
        return "Division";
    }

    @Override
    protected int getDetaultMin() {
        return 1;
    }

    @Override
    protected int getDefaultMax() {
        return 9;
    }

    @Override
    public int getResult() {
        return number1 / number2;
    }

    @Override
    public int getNumber1() {
        return number1;
    }

    @Override
    public int getNumber2() {
        return number2;
    }

    @Override
    public CharSequence getOperator() {
        return "/";
    }

    @Override
    public boolean checkResult(String result) {
        if (TextUtils.isEmpty(result)) {
            return false;
        }
        try {
            int r = Integer.parseInt(result);
            return r == number1 / number2;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void internal_build() {
        int min = getMin();
        int max = getMax();
        number1 = rand.nextInt(max - min) + min;
        number2 = rand.nextInt(max - min) + min;
        number1 = number1 * number2;
    }
}
