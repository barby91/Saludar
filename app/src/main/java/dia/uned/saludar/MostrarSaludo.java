package dia.uned.saludar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MostrarSaludo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_saludo);

        Intent intent = getIntent();
        String saludo = intent.getStringExtra(ActividadPrincipal.SALUDO);

        TextView mensajeTexto = new TextView(this);
        mensajeTexto.setTextSize(40);
        mensajeTexto.setText(saludo);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.vista);
        layout.addView(mensajeTexto);
    }
}