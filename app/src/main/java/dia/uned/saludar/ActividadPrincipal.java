package dia.uned.saludar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ActividadPrincipal extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //establecemos que se use la vista adecuada
        setContentView(R.layout.vista_actividad_principal);
    }

    public void metodfoSaludar (View view)
    {
        EditText entrada = (EditText) findViewById(R.id.entradaNombre);
        TextView salida = (TextView) findViewById(R.id.textoSalida);
        String nombre = entrada.getText().toString();
        String inicioSaludo = getString(R.string.inicio_saludo);
        salida.setText(inicioSaludo + " " + nombre + "!");
    }
}
