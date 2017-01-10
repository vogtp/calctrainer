package ch.almana.calctrainer;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.Random;

/**
 * Created by vogtp on 10.01.17.
 */
public abstract  class BaseCalculationBuilder implements ICalulcationBuilder {
    private static final String TAG = "CalcTrainer.Builder";

    private static final String PREF_KEY_MIN = "_Min";
    private static final String PREF_KEY_MAX = "_Max";

    protected Random rand = new Random();
    private final SharedPreferences preferences;


    public BaseCalculationBuilder(SharedPreferences preferences) {
        super();
        this.preferences = preferences;
    }

    public abstract String getName();


    @Override
    public int getMin() {
        return preferences.getInt(getName()+PREF_KEY_MIN, 0);
    }

    @Override
    public int getMax() {
        return preferences.getInt(getName()+PREF_KEY_MAX, 30);
    }

    @Override
    public void setMin(int i) {
        preferences.edit().putInt(getName()+PREF_KEY_MIN,i).apply();
    }

    @Override
    public void setMax(int i) {
        preferences.edit().putInt(getName()+PREF_KEY_MAX,i).apply();
    }

    @Override
    public abstract int getResult();

    @Override
    public abstract int getNumber1();

    @Override
    public abstract int getNumber2();

    @Override
    public abstract CharSequence getOperator();

    @Override
    public void build() {
        internal_build();
        while ((getResult() == 0 || getNumber1() == 0 || getNumber2() == 0) && rand.nextInt(10) > 1) {
            Log.v(TAG, "Building new calculation since: "+toString());
            internal_build();
        }
        Log.i(TAG,"Using calculation "+toString());
    }

    protected abstract void internal_build();

    @Override
    public String toString() {
        return getClass().getSimpleName()+": "+getNumber1()+ " "+getOperator() +" "+getNumber2()+" = "+getResult()+ "; Min: "+getMin()+" Max: "+getMax();
    }
}
