package ch.almana.calctrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CalcActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        tvZahl1 = (TextView)findViewById(R.id.tvZahl1);
        tvZahl2 = (TextView)findViewById(R.id.tvZahl2);
        tvOperator = (TextView)findViewById(R.id.tvOperator);
        tvResult = (TextView)findViewById(R.id.tvResult);
        etResultat = (EditText)findViewById(R.id.etResultat);
        ivSmilie = (ImageView) findViewById(R.id.ivSmilie);

        plusCalculator = new PlusCalculationBuilder(0,30);
        minusCalculator = new MinusCalculationBuilder(0,30);
        calulcationBuilder = plusCalculator;
        newCalculation();

        ivSmilie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ok){
                    newCalculation();
                }else if (calulcationBuilder.checkResult(etResultat.getText().toString())){
                    tvResult.setText("Richtig!");
//                    tvResult.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_correct,0,0,0);
                    ivSmilie.setImageResource(R.mipmap.icon_correct);
                    ok = true;
                }else{
                    tvResult.setText("Falsch....");
//                    tvResult.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_falsch,0,0,0);
                    ivSmilie.setImageResource(R.mipmap.icon_falsch);
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

    private void newCalculation() {
        ok = false;
        tvResult.setText("");
        etResultat.setText("");
        ivSmilie.setImageResource(R.mipmap.icon_wink);
        calulcationBuilder.build();
        tvZahl1.setText(calulcationBuilder.getNumber1());
        tvZahl2.setText(calulcationBuilder.getNumber2());
        tvOperator.setText(calulcationBuilder.getOperator());
        etResultat.requestFocus();
    }
}
