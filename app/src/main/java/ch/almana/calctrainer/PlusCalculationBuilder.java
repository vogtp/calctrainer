package ch.almana.calctrainer;

import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by vogtp on 09.01.17.
 */
public class PlusCalculationBuilder extends BaseCalculationBuilder implements ICalulcationBuilder {
    private static final String TAG = "CalcTrainer.Plus";


    private int number1;
    private int number2;

    public PlusCalculationBuilder(SharedPreferences preferences) {
        super(preferences);
    }

    @Override
    public String getName() {
        return "Plus";
    }

    @Override
    protected int getDetaultMin() {
        return 0;
    }

    @Override
    protected int getDefaultMax() {
        return 30;
    }


    @Override
    public void internal_build() {
        int min = getMin();
        int max = getMax();
//        Log.i(TAG, "Generating plus calculation between " + min + " and " + max);
        number1 = rand.nextInt(max - min) + min;
//        Log.i(TAG, "Number1: " + number1);
        int n = max - number1;
        if (n <= 0) {
            number2 = min;
        } else {
            number2 = rand.nextInt(n)+min;
        }
//        Log.i(TAG, "Number2: " + number2);
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
    public int getResult() {
        return number1 + number2;
    }
    @Override
    public CharSequence getOperator() {
        return "+";
    }

    @Override
    public boolean checkResult(String result) {
        if (TextUtils.isEmpty(result)){
            return false;
        }
        try{
            int r = Integer.parseInt(result);
           return r == number1 + number2;
        }catch (Exception e) {
            return false;
        }
    }
}
