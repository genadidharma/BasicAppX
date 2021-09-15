package org.aplas.basicappx;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Distance dist = new Distance();
    private Weight weight = new Weight();
    private Temperature temp = new Temperature();
    private Button convertBtn;
    private EditText inputTxt;
    private EditText outputTxt;
    private Spinner unitOri;
    private Spinner unitConv;
    private RadioGroup unitType;
    private CheckBox roundBox;
    private CheckBox formBox;
    private ImageView imgView;
    private ImageView imgFormula;

    private AlertDialog startDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convertBtn = findViewById(R.id.convertButton);
        inputTxt = findViewById(R.id.inputText);
        outputTxt = findViewById(R.id.outputText);
        unitOri = findViewById(R.id.oriList);
        unitConv = findViewById(R.id.convList);
        unitType = findViewById(R.id.radioGroup);
        roundBox = findViewById(R.id.chkRounded);
        formBox = findViewById(R.id.chkFormula);
        imgView = findViewById(R.id.img);
        imgFormula = findViewById(R.id.imgFormula);

        unitType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                ArrayAdapter<CharSequence> adapter;

                if (radioButton.getId() == R.id.rbTemp) {
                    adapter = ArrayAdapter.createFromResource(unitType.getContext(), R.array.tempList, android.R.layout.simple_spinner_item);
                    imgView.setImageResource(R.drawable.temperature);
                } else if (radioButton.getId() == R.id.rbWeight) {
                    adapter = ArrayAdapter.createFromResource(unitType.getContext(), R.array.weightList, android.R.layout.simple_spinner_item);
                    imgView.setImageResource(R.drawable.weight);
                } else {
                    adapter = ArrayAdapter.createFromResource(unitType.getContext(), R.array.distList, android.R.layout.simple_spinner_item);
                    imgView.setImageResource(R.drawable.distance);
                }

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                unitOri.setAdapter(adapter);
                unitConv.setAdapter(adapter);

            }
        });

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doConvert();
            }
        });

        unitOri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                doConvert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        unitConv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                doConvert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        roundBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                doConvert();
            }
        });

        formBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if ((b)) {
                    imgFormula.setVisibility(View.VISIBLE);
                } else {
                    imgFormula.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        startDialog = new AlertDialog.Builder(MainActivity.this).create();
        startDialog.setTitle("Application started");
        startDialog.setMessage("This app can use to convert any units");
        startDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        startDialog.show();
    }

    protected double convertUnit(String type, String oriUnit, String convUnit, double value) {
        double result;

        switch (type) {
            case "Temperature":
                result = temp.convert(oriUnit, convUnit, value);
                break;
            case "Distance":
                result = dist.convert(oriUnit, convUnit, value);
                break;
            case "Weight":
                result = weight.convert(oriUnit, convUnit, value);
                break;
            default:
                result = 0;
        }

        return result;
    }

    protected String strResult(double val, boolean rounded) {
        if (rounded) {
            return new DecimalFormat("#.##").format(val);
        } else {
            return new DecimalFormat("#.#####").format(val);
        }
    }

    protected void doConvert() {
        RadioButton radio = (RadioButton) findViewById(unitType.getCheckedRadioButtonId());

        double input = Double.parseDouble(inputTxt.getText().toString());
        boolean rounded = roundBox.isChecked();

        String oriUnit = unitOri.getSelectedItem().toString();
        String convUnit = unitConv.getSelectedItem().toString();
        double result;

        if (radio.getId() == R.id.rbTemp) {
            result = temp.convert(oriUnit, convUnit, input);
        } else if (radio.getId() == R.id.rbWeight) {
            result = weight.convert(oriUnit, convUnit, input);
        } else {
            result = dist.convert(oriUnit, convUnit, input);
        }

        outputTxt.setText(strResult(result, rounded));
    }
}