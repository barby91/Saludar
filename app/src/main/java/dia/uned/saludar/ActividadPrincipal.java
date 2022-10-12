package dia.uned.saludar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class ActividadPrincipal extends AppCompatActivity {

    public final static String SALUDO = "dia.uned.saludar.SALUDO";
    public final static String NOMBRE_GUARDADO_EN_DISCO = "com.uned.dia.saludar.NOMBRE_GUARDADO_EN_DISCO";
    public final static String SALUDO_GUARDADO = "com.uned.dia.saludar.SALUDO_GUARDADO";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //establecemos que se use la vista adecuada
        setContentView(R.layout.vista_actividad_principal);

        SharedPreferences preferencias = this.getPreferences(Context.MODE_PRIVATE);
        String nombreGuardado = preferencias.getString(NOMBRE_GUARDADO_EN_DISCO, "");

        escribirNombre(nombreGuardado);
    }

    @Override
    public void onSaveInstanceState(Bundle datosGuardados)
    {
        String salida = obtenerSalida();
        datosGuardados.putString(SALUDO_GUARDADO, salida);
        super.onSaveInstanceState(datosGuardados);
    }

    @Override
    public void onRestoreInstanceState(Bundle datosGuardados)
    {
        super.onRestoreInstanceState(datosGuardados);
        String salida = datosGuardados.getString(SALUDO_GUARDADO);
        escribirSalida(salida);
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

    public void GuardarNombre(View view)
    {
        String nombre = obtenerNombre();
        SharedPreferences preferencias = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(NOMBRE_GUARDADO_EN_DISCO, nombre);
        editor.commit();
        escribirSalida(getString(R.string.nombre_guardado_OK));
    }
}
