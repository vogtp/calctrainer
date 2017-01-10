package ch.almana.calctrainer;

/**
 * Created by vogtp on 09.01.17.
 */
public interface ICalulcationBuilder {
    void setMin(int min);

    void setMax(int max);

    void build();

    int getMin();

    int getMax();

    int getResult();

    int getNumber1();

    int getNumber2();

    CharSequence getOperator();

    boolean checkResult(String result);

}
