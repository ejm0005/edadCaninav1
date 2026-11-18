package es.davante.edadcanina.modelo;

public class EdadCaninaModelo {
    //Incluimos las constantes de la MainActivity a la clase modelo
    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30;

    //Variables para realizar las validaciones y cálculos
    private String edadTexto = "";
    private int edadHumana = 0;

    //Validacion inicializada con el estado SIN_CALCULAR
    private Validacion validacion = Validacion.SIN_CALCULAR;

    public Validacion calcular() {
        //Comprobar si el campo de texto está vacío
        if (edadTexto == null || edadTexto.trim().isEmpty()) {
            validacion = Validacion.VACIO;
            return validacion;
        }

        //Comprobar si el valor introducido en la aplicación es un valor numérico
        int edad;
        try {
            edad = Integer.parseInt(edadTexto.trim());
        } catch (NumberFormatException e) {
            validacion = Validacion.NO_ES_NUMERO;
            return validacion;
        }

        //Comprobar si el valor introducido está dentro del rango permitido por las constantes definidas arriba
        if (edad < EDAD_MINIMA || edad > EDAD_MAXIMA) {
            validacion = Validacion.FUERA_DE_RANGO;
            return validacion;
        }

        //Después de las validaciones, se procede con el cálculo de la edad canina
        edadHumana = edad * FACTOR_CANINO;
        validacion = Validacion.CORRECTO;
        return validacion;
    }

    // Getters y Setters
    public String getEdadTexto() {
        return edadTexto;
    }

    public void setEdadTexto(String edadTexto) {
        this.edadTexto = edadTexto;
        //Si el texto cambia el resultado anterior deja de valer
        this.validacion = Validacion.SIN_CALCULAR;
    }

    public int getEdadHumana() {
        return edadHumana;
    }

    public Validacion getValidacion() {
        return validacion;
    }
}
