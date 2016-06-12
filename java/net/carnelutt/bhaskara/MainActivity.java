package net.carnelutt.bhaskara;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity
{
    private RelativeLayout lytMain;
    private Button btnCalcular;
    private Button btnLimpar;
    private TextView lblResultado;
    private EditText txtA;
    private EditText txtB;
    private EditText txtC;

    private boolean resolucao = false;

    private final static String TAG = "LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setando as variaveis
        final ButtonHandler hdlButton = new ButtonHandler();
        final LongButtonHandler hdlButtonLong = new LongButtonHandler();
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnLimpar = (Button) findViewById(R.id.btnLimpar);
        txtA = (EditText) findViewById(R.id.txtA);
        txtB = (EditText) findViewById(R.id.txtB);
        txtC = (EditText) findViewById(R.id.txtC);
        lblResultado = (TextView) findViewById(R.id.lblResultado);
        lytMain = (RelativeLayout) findViewById(R.id.actMain);

        // onClick Listeners
        btnCalcular.setOnClickListener(hdlButton);
        btnCalcular.setOnLongClickListener(hdlButtonLong);
        btnLimpar.setOnClickListener(hdlButton);
    }

    private class ButtonHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            // Botao = btnLimpar
            if (v.getId() == R.id.btnLimpar)
            {
                txtA.setText("");
                txtB.setText("");
                txtC.setText("");
                lblResultado.setText("");
            }

            // Botao = btnCalcular
            if (v.getId() == R.id.btnCalcular)
            {
                calcular();
            }
        }
    }

    private class LongButtonHandler implements View.OnLongClickListener
    {

        @Override
        public boolean onLongClick(View v)
        {
            if (v.getId() == R.id.btnCalcular)
            {
                calcular();

                float a = (txtA.getText().toString().matches("")) ? 0 : Float.parseFloat(txtA.getText().toString());
                float b = (txtB.getText().toString().matches("")) ? 0 : Float.parseFloat(txtB.getText().toString());
                float c = (txtC.getText().toString().matches("")) ? 0 : Float.parseFloat(txtC.getText().toString());
                float delta = b * b - 4 * a * c;

                Intent resolucao = new Intent(MainActivity.this, ResolucaoActivity.class);
                resolucao.putExtra("a", a);
                resolucao.putExtra("b", b);
                resolucao.putExtra("c", c);
                resolucao.putExtra("delta", delta);

                MainActivity.this.startActivity(resolucao);
            }
            return false;
        }
    }

    private void calcular()
    {
        float a = (txtA.getText().toString().matches("")) ? 0 : Float.parseFloat(txtA.getText().toString());
        float b = (txtB.getText().toString().matches("")) ? 0 : Float.parseFloat(txtB.getText().toString());
        float c = (txtC.getText().toString().matches("")) ? 0 : Float.parseFloat(txtC.getText().toString());
        float delta = b * b - 4 * a * c;
        String resultado = "";

        if (delta >= 0) // Delta Positivo
        {
            Toast.makeText(MainActivity.this, R.string.deltaPositivo, Toast.LENGTH_SHORT).show();
            float raiz1 = (float) ((-b + Math.sqrt(delta)) / (2 * a));
            float raiz2 = (float) ((-b - Math.sqrt(delta)) / (2 * a));
            resultado = String.format(
                    "Raiz 1 = %.2f\nRaiz 2 = %.2f",
                    raiz1,
                    raiz2
            );
            lblResultado.setText(resultado);
        } else // Delta negativo
        {
            Toast.makeText(MainActivity.this, R.string.deltaNegativo, Toast.LENGTH_SHORT).show();
            String raiz1 = String.format("%.2f + %.2fi", -b / (2 * a), Math.sqrt(-delta) / (2 * a));
            String raiz2 = String.format("%.2f - %.2fi", -b / (2 * a), Math.sqrt(-delta) / (2 * a));
            resultado = String.format(
                    "Raiz 1 = %s\nRaiz 2 = %s",
                    raiz1,
                    raiz2
            );
            lblResultado.setText(resultado);
        }


    }

}
