package ch.almana.calctrainer;

/**
 * Created by vogtp on 09.01.17.
 */
public interface ICalulcationBuilder {
    void setMin(int min);

    void setMax(int max);

    void build();

    CharSequence getResult();

    CharSequence getNumber1();

    CharSequence getNumber2();

    CharSequence getOperator();

    boolean checkResult(String result);

}
