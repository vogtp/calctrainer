package ch.almana.calctrainer;

import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CalcActivity extends AppCompatActivity {

    private static final String PREF_KEY_MIN = "min";
    private static final String PREF_KEY_MAX = "max";
    private TextView tvZahl1;
    private TextView tvZahl2;
    private TextView tvOperator;
    private EditText etResultat;
    private ICalulcationBuilder calulcationBuilder;
    private TextView tvResult;
    private ICalulcationBuilder plusCalculator;
    private ICalulcationBuilder minusCalculator;
    private ImageView ivSmilie;
    private boolean ok = false;
    private EditText etMax;
    private EditText etMin;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        tvZahl1 = (TextView)findViewById(R.id.tvZahl1);
        tvZahl2 = (TextView)findViewById(R.id.tvZahl2);
        tvOperator = (TextView)findViewById(R.id.tvOperator);
        tvResult = (TextView)findViewById(R.id.tvResult);
        ivSmilie = (ImageView) findViewById(R.id.ivSmilie);
        etResultat = (EditText)findViewById(R.id.etResultat);
        etMax = (EditText)findViewById(R.id.etMax);
        etMin = (EditText)findViewById(R.id.etMin);

        preferences = getSharedPreferences("calctrainer", MODE_PRIVATE);

        etMax.setText(""+getMax());
        etMin.setText(""+getMin());

        plusCalculator = new PlusCalculationBuilder(getMin(),getMax());
        minusCalculator = new MinusCalculationBuilder(getMin(),getMax());
        calulcationBuilder = plusCalculator;
        newCalculation();

        ivSmilie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ok){
                    newCalculation();
                }else if (!TextUtils.isEmpty(etResultat.getText().toString())){
                    if (calulcationBuilder.checkResult(etResultat.getText().toString())){
                        tvResult.setText("Richtig!");
//                    tvResult.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_correct,0,0,0);
                        ivSmilie.setImageResource(R.mipmap.icon_correct);
                        ok = true;
                    }else{
                        tvResult.setText(etResultat.getText().toString()+"ist falsch....");
//                    tvResult.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_falsch,0,0,0);
                        ivSmilie.setImageResource(R.mipmap.icon_falsch);
                    }
                }
            }
        });


        findViewById(R.id.buPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calulcationBuilder = plusCalculator;
                newCalculation();
            }
        });
        findViewById(R.id.buMinux).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calulcationBuilder = minusCalculator;
                newCalculation();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        updateMinMax();
    }

    private void newCalculation() {
        updateMinMax();
        ok = false;
        tvResult.setText("");
        etResultat.setText("");
        ivSmilie.setImageResource(R.mipmap.icon_wink);

        calulcationBuilder.setMin(getMin());
        calulcationBuilder.setMax(getMax());
        calulcationBuilder.build();

        tvZahl1.setText(calulcationBuilder.getNumber1());
        tvZahl2.setText(calulcationBuilder.getNumber2());
        tvOperator.setText(calulcationBuilder.getOperator());
        etResultat.requestFocus();
    }

    private void updateMinMax() {
        int max = getMax();
        try{
            max = Integer.parseInt(etMax.getText().toString());
            if (max > 0 ){
                preferences.edit().putInt(PREF_KEY_MAX,max).apply();
            }
        }catch (Exception e){
        }
        try{
            int i = Integer.parseInt(etMin.getText().toString());
            preferences.edit().putInt(PREF_KEY_MIN,i).apply();
        }catch (Exception e){
        }
    }

    public int getMin() {
        return preferences.getInt(PREF_KEY_MIN, 0);
    }
    public int getMax() {
        return preferences.getInt(PREF_KEY_MAX, 30);
    }
}
