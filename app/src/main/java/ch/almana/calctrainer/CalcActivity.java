package ch.almana.calctrainer;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    private ICalulcationBuilder multiplyCalculationBuilder;
    private ICalulcationBuilder divisionCalculationBuilder;
    private ImageView ivSmilie;
    private boolean ok = false;
    private EditText etMax;
    private EditText etMin;
    private SharedPreferences preferences;
    private int right = 0;
    private int wrong = 0;
    private int total = 0;
    private TextView tvRight;
    private TextView tvWrong;
    private TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        tvZahl1 = (TextView)findViewById(R.id.tvZahl1);
        tvZahl2 = (TextView)findViewById(R.id.tvZahl2);
        tvOperator = (TextView)findViewById(R.id.tvOperator);
        tvResult = (TextView)findViewById(R.id.tvResult);
        tvRight = (TextView)findViewById(R.id.tvRight);
        tvWrong = (TextView)findViewById(R.id.tvWrong);
        tvTotal = (TextView)findViewById(R.id.tvTotal);
        ivSmilie = (ImageView) findViewById(R.id.ivSmilie);
        etResultat = (EditText)findViewById(R.id.etResultat);
        etMax = (EditText)findViewById(R.id.etMax);
        etMin = (EditText)findViewById(R.id.etMin);

        preferences = getSharedPreferences("calctrainer", MODE_PRIVATE);

        plusCalculator = new PlusCalculationBuilder(preferences);
        minusCalculator = new MinusCalculationBuilder(preferences);
        multiplyCalculationBuilder = new MultiplyCalculationBuilder(preferences);
        divisionCalculationBuilder = new DivisionCalculationBuilder(preferences);
        calulcationBuilder = plusCalculator;
        newCalculation();

        ivSmilie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ok){
                    newCalculation();
                }else if (!TextUtils.isEmpty(etResultat.getText().toString())){
                    if (calulcationBuilder.checkResult(etResultat.getText().toString())){
                        tvResult.setText(R.string.right_answer);
                        tvResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                        ivSmilie.setImageResource(R.mipmap.icon_correct);
                        ok = true;
                        right++;
                    }else{
                        tvResult.setText(getString(R.string.wrong_answer, etResultat.getText().toString()));
                        tvResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                        ivSmilie.setImageResource(R.mipmap.icon_falsch);
                        etResultat.setText("");
                        wrong++;
                    }
                }
                updateStats();
            }
        });

        etMax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (calulcationBuilder == null) {
                    return;
                }
                int max = calulcationBuilder.getMax();
                try{
                    max = Integer.parseInt(etMax.getText().toString());
                    if (max > 0 ){
                        calulcationBuilder.setMax(max);
                    }else{
                        etMax.setText(""+ calulcationBuilder.getMax());
                    }
                }catch (Exception e){
                }
            }
        });

        etMin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (calulcationBuilder == null) {
                    return;
                }
                try{
                    int i = Integer.parseInt(etMin.getText().toString());
                    calulcationBuilder.setMin(i);
                }catch (Exception e){
                }
            }
        });


        findViewById(R.id.buPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calulcationBuilder = null;
                etMax.setText("" + plusCalculator.getMax());
                etMin.setText("" + plusCalculator.getMin());
                calulcationBuilder = plusCalculator;
                if (TextUtils.isEmpty(etResultat.getText().toString())){
                    total--;
                }
                newCalculation();
            }
        });
        findViewById(R.id.buMinux).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calulcationBuilder = null;
                etMax.setText("" + minusCalculator.getMax());
                etMin.setText("" + minusCalculator.getMin());
                calulcationBuilder = minusCalculator;
                if (TextUtils.isEmpty(etResultat.getText().toString())){
                    total--;
                }
                newCalculation();
            }
        });
        findViewById(R.id.buMultiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calulcationBuilder = null;
                etMax.setText("" + multiplyCalculationBuilder.getMax());
                etMin.setText("" + multiplyCalculationBuilder.getMin());
                calulcationBuilder = multiplyCalculationBuilder;
                if (TextUtils.isEmpty(etResultat.getText().toString())) {
                    total--;
                }
                newCalculation();
            }
        });
        findViewById(R.id.buDivison).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calulcationBuilder = null;
                etMax.setText("" + divisionCalculationBuilder.getMax());
                etMin.setText("" + divisionCalculationBuilder.getMin());
                calulcationBuilder = divisionCalculationBuilder;
                if (TextUtils.isEmpty(etResultat.getText().toString())) {
                    total--;
                }
                newCalculation();
            }
        });
        findViewById(R.id.buClearStats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CalcActivity.this);
                builder.setTitle(getString(R.string.title_del_counter));
                builder.setMessage(getString(R.string.msg_del_counter));
                builder.setNegativeButton(R.string.no, null);
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        right=0;
                        wrong=0;
                        total=0;
                        updateStats();
                        newCalculation();
                    }
                });
                builder.show();
            }
        });
    }

    private void updateStats() {
        tvRight.setText(Integer.toString(right));
        tvWrong.setText(Integer.toString(wrong));
        tvTotal.setText(Integer.toString(total));
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    private void newCalculation() {
        ok = false;
        tvResult.setText("");
        etResultat.setText("");
        ivSmilie.setImageResource(R.mipmap.icon_wink);

        calulcationBuilder.build();

        tvZahl1.setText(""+calulcationBuilder.getNumber1());
        tvZahl2.setText(""+calulcationBuilder.getNumber2());
        tvOperator.setText(""+calulcationBuilder.getOperator());
        etResultat.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(etResultat, InputMethodManager.SHOW_IMPLICIT);
        total++;
        updateStats();
    }


}
