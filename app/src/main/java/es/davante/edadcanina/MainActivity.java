package es.davante.edadcanina;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

// Importar la clase binding que se ha generado
import es.davante.edadcanina.databinding.ActivityMainBinding;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;

public class MainActivity extends AppCompatActivity {
    //Declarar el objeto binding
    private ActivityMainBinding binding;
    //Declarar el objeto modelo
    private EdadCaninaModelo modelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //Inicializar el binding sustituyendo al setContentView
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //Inicializar el modelo
        modelo = new EdadCaninaModelo();

        //Binding sustituyendo a findViewById
        binding.btnCalc.setOnClickListener(view -> calcularEdad());
    }

    private void calcularEdad() {
        String ageString = binding.ageInput.getText().toString().trim();
        modelo.setEdadTexto(ageString);

        //Validar y calcular según lo introducido en la aplicación
        Validacion resultado = modelo.calcular();

        //Mostrar según el resultado devuelto por el modelo
        switch (resultado) {
            case VACIO:
                mostrarError(getString(R.string.error_vacio));
                break;
            case NO_ES_NUMERO:
                mostrarError(getString(R.string.error_no_numero));
                break;
            case FUERA_DE_RANGO:
                String mensajeRango = getString(R.string.error_rango, 1, 30);
                mostrarError(mensajeRango);
                break;
            case CORRECTO:
                binding.ageInput.setError(null);
                binding.resultText.setText(getString(R.string.tu_edad_format, modelo.getEdadHumana()));
                break;
            case SIN_CALCULAR:
                break;
        }
    }

    private void mostrarError(String mensaje) {
        binding.ageInput.setError(mensaje);
        binding.ageInput.requestFocus();
        binding.resultText.setText("");
    }
}