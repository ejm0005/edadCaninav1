package es.davante.edadcanina;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

// Importar la clase binding que se ha generado
import es.davante.edadcanina.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30;

    //Declarar el objeto binding
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //Inicializar el binding sustituyendo al setContentView
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Binding directamente con el ID sustituyendo a findViewById
        binding.btnCalc.setOnClickListener(view -> calcularEdad());
    }

    private void calcularEdad() {
        String ageString = binding.ageInput.getText().toString().trim();
        if (ageString.isEmpty()) {
            mostrarError(getString(R.string.error_vacio));
            return;
        }
        int age;

        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException e) {
            mostrarError(getString(R.string.error_no_numero));
            return;
        }

        if (age < EDAD_MINIMA || age > EDAD_MAXIMA) {
            String mensajeRango = getString(R.string.error_rango, EDAD_MINIMA, EDAD_MAXIMA);
            mostrarError(mensajeRango);
            return;
        }

        binding.ageInput.setError(null);
        int dogAge = age * FACTOR_CANINO;
        binding.resultText.setText(getString(R.string.tu_edad_format, dogAge));
    }

    private void mostrarError(String mensaje) {
        binding.ageInput.setError(mensaje);
        binding.ageInput.requestFocus();
        binding.resultText.setText("");
    }
}