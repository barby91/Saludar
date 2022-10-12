package dia.uned.saludar;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class ActividadPrincipal extends AppCompatActivity {

    public final static String SALUDO = "dia.uned.saludar.SALUDO";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //establecemos que se use la vista adecuada
        setContentView(R.layout.vista_actividad_principal);
    }

    public void metodoSaludar (View view)
    {
        escribirSalida(crearSaludo(obtenerNombre()));
    }

    public String obtenerNombre()
    {
        EditText editarTexto = (EditText) findViewById(R.id.entradaNombre);
        return editarTexto.getText().toString();
    }

    public void escribirNombre(String nombre)
    {
        EditText editarTexto = (EditText) findViewById(R.id.entradaNombre);
        editarTexto.setText(nombre);
    }

    public String crearSaludo(String nombre)
    {
        String inicioSaludo = getString(R.string.inicio_saludo);
        return inicioSaludo + " " + nombre + "!";
    }

    public String obtenerSalida()
    {
        TextView respuesta = (TextView) findViewById(R.id.textoSalida);
        return respuesta.getText().toString();
    }

    public void escribirSalida(String textoSalida)
    {
        TextView respuesta = (TextView) findViewById(R.id.textoSalida);
        respuesta.setText(textoSalida);
    }

    public void SaludarActivdadNueva(View view)
    {
        String textoSaludo = crearSaludo(obtenerNombre());
        escribirSalida("");

        Intent intent = new Intent(this, MostrarSaludo.class);
        intent.putExtra(SALUDO, textoSaludo);
        startActivity(intent);
    }

    public void SaludarOtraApp(View view)
    {
        String textoSaludo = crearSaludo(obtenerNombre());
        escribirSalida("");

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, textoSaludo);
        intent.setType("text/plain");
        PackageManager packageManager = getPackageManager();
        List activities= packageManager.queryIntentActivities(intent,PackageManager.MATCH_DEFAULT_ONLY);
        if(activities.size() > 0)
        {
            intent.putExtra(Intent.EXTRA_TEXT, textoSaludo);
            startActivity(intent);
            //escribirSalida( activities.get(0).toString());
        }
        else
        {
            escribirSalida( getString(R.string.error_lanzando_app));
        }
    }
}
