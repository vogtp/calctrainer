package ch.almana.calctrainer;

import android.content.SharedPreferences;

/**
 * Created by vogtp on 10.01.17.
 */
public abstract  class BaseCalculationBuilder implements ICalulcationBuilder {

    private static final String PREF_KEY_MIN = "_Min";
    private static final String PREF_KEY_MAX = "_Max";
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
    public abstract CharSequence getResult();

    @Override
    public abstract CharSequence getNumber1();

    @Override
    public abstract CharSequence getNumber2();

    @Override
    public abstract CharSequence getOperator();

    @Override
    public String toString() {
        return getClass().getSimpleName()+": "+getNumber1()+ " "+getOperator() +" "+getNumber2()+" = "+getResult()+ "; Min: "+getMin()+" Max: "+getMax();
    }
}
