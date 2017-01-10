package ch.almana.calctrainer;

/**
 * Created by vogtp on 10.01.17.
 */
public abstract  class BaseCalculationBuilder {

    private int min;
    private int max;

    public BaseCalculationBuilder(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public void setMin(int i) {
        this.min = i;
    }

    public void setMax(int i) {
        this.max = i;
    }

    public abstract CharSequence getResult();

    public abstract CharSequence getNumber1();

    public abstract CharSequence getNumber2();

    public abstract CharSequence getOperator();

    @Override
    public String toString() {
        return getClass().getSimpleName()+": "+getNumber1()+ " "+getOperator() +" "+getNumber2()+" = "+getResult()+ "; Min: "+min+" Max: "+max;
    }
}
