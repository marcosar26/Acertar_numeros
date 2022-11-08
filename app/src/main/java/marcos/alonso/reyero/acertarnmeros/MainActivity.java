package marcos.alonso.reyero.acertarnmeros;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {
    private final int FALLOS_MAXIMOS = 5;
    private int numero, numeroUsuario, fallos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = (EditText) findViewById(R.id.editTextNumber);
        Button generar = (Button) findViewById(R.id.button4);
        Button comprobar = (Button) findViewById(R.id.button2);
        Button salir = (Button) findViewById(R.id.button3);

        generar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fallos = 0;
                numero = ThreadLocalRandom.current().nextInt(1, 1001);
                Toast.makeText(MainActivity.this, String.valueOf(numero), Toast.LENGTH_SHORT).show();
            }
        });

        comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numero == 0) {
                    Toast.makeText(MainActivity.this, "Genera un número primero.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    String str = editText.getText().toString();
                    numeroUsuario = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Error al procesar el número introducido", Toast.LENGTH_SHORT).show();
                }

                if (numeroUsuario == numero) {
                    Toast.makeText(MainActivity.this, "¡Muy bien! Es el número mostrado", Toast.LENGTH_SHORT).show();
                    numero = 0;
                } else {
                    fallos++;
                    Toast.makeText(MainActivity.this, "Lo siento, no es el número mostrado. Fallos: " + fallos + "/" + FALLOS_MAXIMOS, Toast.LENGTH_SHORT).show();

                    if (fallos == FALLOS_MAXIMOS) {
                        Toast.makeText(MainActivity.this, "Has superado el número de fallos. El número era el " + numero, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}