package ch.almana.calctrainer;

import android.text.TextUtils;
import android.util.Log;

import java.util.Random;

/**
 * Created by vogtp on 09.01.17.
 */
public class MinusCalculationBuilder extends BaseCalculationBuilder implements ICalulcationBuilder {
    private static final String TAG = "CalcTrainer.Minus";
    private Random rand = new Random();

    private int number1;
    private int number2;

    public MinusCalculationBuilder(int min, int max) {
        super(min,max);
    }

    @Override
    public void build() {
        int min = getMin();
        int max = getMax();
//        Log.i(TAG, "Generating minus calculation between "+min+" and "+max);
        number1 = rand.nextInt(max-min) + min;
//        Log.i(TAG, "Number1: "+number1);
        int n = number1 - min;
        if (n <= 0){
            number2 = min;
        }else {
            number2 = rand.nextInt(n)+min;
        }
//        Log.i(TAG, "Number2: "+number2);
    }

    @Override
    public CharSequence getNumber1() {
        return Integer.toString(number1);
    }

    @Override
    public CharSequence getNumber2() {
        return Integer.toString(number2);
    }

    @Override
    public CharSequence getResult() {
        return Integer.toString(number1 - number2);
    }
    @Override
    public CharSequence getOperator() {
        return "-";
    }

    @Override
    public boolean checkResult(String result) {
        if (TextUtils.isEmpty(result)){
            return false;
        }
        try{
            int r = Integer.parseInt(result);
            return r == number1 - number2;
        }catch (Exception e) {
            return false;
        }
    }

}
