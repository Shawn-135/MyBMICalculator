package sg.edu.rp.c346.s19040010.mybmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etHeight;
    Button btnCalculate, btnReset;
    TextView tvMessage, tvLastBMI, tvLastCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalculate = findViewById(R.id.buttonCalculate);
        btnReset = findViewById(R.id.buttonReset);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etHeight.setText("");
                etWeight.setText("");
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float weight = Float.parseFloat(etWeight.getText().toString());
                float height = Float.parseFloat(etHeight.getText().toString());
                Float BMI = weight/(height*height);
                Calendar now = Calendar.getInstance();
                String dateTime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                                  now.get(Calendar.MONTH+1) + "/" +
                                  now.get(Calendar.YEAR) +
                                  now.get(Calendar.HOUR_OF_DAY) + ":" +
                                  now.get(Calendar.MINUTE);

                if(BMI < 18.5) {
                    tvMessage.setText("You are underweight");
                }
                else if(BMI >= 18.5 && BMI <= 24.9) {
                    tvMessage.setText("Your BMI is normal weight");
                }
                else if(BMI >= 25 && BMI <= 30) {
                    tvMessage.setText("You are overweight");
                }
                else {
                    tvMessage.setText("You are obese");
                }
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("Last Calculate Date :", dateTime);
                prefEdit.putFloat("Last Calculate BMI :", BMI);
                prefEdit.commit();
                tvLastBMI.setText(BMI|"");
                tvLastCal.setText(dateTime);

            }
        });
    }

}
