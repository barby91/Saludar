package dia.uned.saludar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ActividadPrincipal extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //establecemos que se use la vista adecuada
        setContentView(R.layout.vista_actividad_principal);
    }
}
