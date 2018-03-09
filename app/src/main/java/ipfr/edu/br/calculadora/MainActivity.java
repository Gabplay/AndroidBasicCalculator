package ipfr.edu.br.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.math.BigDecimal;
import java.lang.Math;

public class MainActivity extends AppCompatActivity {
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0,
            bMais, bMenos, bMulti, bDiv, bIgual, bClear;

    BigDecimal result = new BigDecimal("0"), x = new BigDecimal("0");
    int count_operations = 0;
    String last_operation = "=";
    boolean error = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TrataBotao trataBotao = new TrataBotao();
        b1 = (Button) findViewById(R.id.b1);
        b1.setOnClickListener(trataBotao);
        b2 = (Button) findViewById(R.id.b2);
        b2.setOnClickListener(trataBotao);
        b3 = (Button) findViewById(R.id.b3);
        b3.setOnClickListener(trataBotao);
        b4 = (Button) findViewById(R.id.b4);
        b4.setOnClickListener(trataBotao);
        b5 = (Button) findViewById(R.id.b5);
        b5.setOnClickListener(trataBotao);
        b6 = (Button) findViewById(R.id.b6);
        b6.setOnClickListener(trataBotao);
        b7 = (Button) findViewById(R.id.b7);
        b7.setOnClickListener(trataBotao);
        b8 = (Button) findViewById(R.id.b8);
        b8.setOnClickListener(trataBotao);
        b9 = (Button) findViewById(R.id.b9);
        b9.setOnClickListener(trataBotao);
        b0 = (Button) findViewById(R.id.b0);
        b0.setOnClickListener(trataBotao);
        bMais = (Button) findViewById(R.id.bMais);
        bMais.setOnClickListener(trataBotao);
        bMenos = (Button) findViewById(R.id.bMenos);
        bMenos.setOnClickListener(trataBotao);
        bMulti = (Button) findViewById(R.id.bMulti);
        bMulti.setOnClickListener(trataBotao);
        bDiv = (Button) findViewById(R.id.bDiv);
        bDiv.setOnClickListener(trataBotao);
        bIgual = (Button) findViewById(R.id.bIgual);
        bIgual.setOnClickListener(trataBotao);
        bClear = (Button) findViewById(R.id.bClear);
        bClear.setOnClickListener(trataBotao);
    }

    private class TrataBotao implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            EditText screen = (EditText) findViewById(R.id.screen);
            String screen_text = screen.getText().toString();

            Button btn_clicked = (Button) v;
            String btn_text = btn_clicked.getText().toString();

            switch(btn_text) {
                case "+":
                case "-":
                case "X":
                case "/":
                    last_operation = btn_text;
                    if(!screen_text.equals("")){
                        count_operations++;
                        screen.setText("");

                        if(count_operations <= 1){
                            result = BigDecimal.valueOf(Integer.parseInt(screen_text));
                        } else {
                            result = getResult();
                        }
                    }
                    break;
                case "=":
                    if(!last_operation.equals(btn_text)) {
                        screen.setText(getResult().toString());
                        last_operation = btn_text;
                    }
                    count_operations = 0;
                    break;
                case "C":
                    screen.setText("");
                    result = new BigDecimal("0");
                    last_operation = btn_text;
                    break;
                default:
                    if(screen_text.length() <= 16 && !(screen_text.equals("0") && btn_text.equals("0"))){
                        screen.setText(screen_text + btn_text);
                    }
                    x = BigDecimal.valueOf(Integer.parseInt(screen_text + btn_text));
                    break;
            }
        }

        private BigDecimal getResult(){
            // Main calculations
            switch(last_operation){
                case "+":
                    result = result.add(x);
                    break;
                case "-":
                    result = result.subtract(x);
                    break;
                case "X":
                    result = result.multiply(x);
                    break;
                case "/":
                    // Cannot divide by zero
                    if(x.compareTo(BigDecimal.ZERO) == 0){
                        error = true;
                        result = new BigDecimal("0");
                    } else{
                        result = result.divide(x, 2, BigDecimal.ROUND_HALF_EVEN);
                    }
                    break;
                default:
                    result = new BigDecimal("0");
                    break;
            }
            return result;
        }
    }
}
