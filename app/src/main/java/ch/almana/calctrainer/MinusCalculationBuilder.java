package ch.almana.calctrainer;

import android.text.TextUtils;
import android.util.Log;

import java.util.Random;

/**
 * Created by vogtp on 09.01.17.
 */
public class MinusCalculationBuilder implements ICalulcationBuilder {
    private static final String TAG = "CalcTrainer.Minus";
    private Random rand = new Random();

    private int min;
    private int max;
    private int number1;
    private int number2;

    public MinusCalculationBuilder(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void setMin(int i) {
        this.min = i;
    }

    @Override
    public void setMax(int i) {
        this.max = i;
    }

    @Override
    public void build() {
        number1 = rand.nextInt(max-min) + min;
        number1 = number1 == 0 ? 1 : number1;
        Log.i(TAG, "Number1: "+number1);
        number2 = rand.nextInt(min+number1) + min;
        Log.i(TAG, "Number2: "+number2);
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