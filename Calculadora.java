import java.awt.BorderLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import javax.swing.JOptionPane;

/**
 * Clase Calculadora
 * -------------------
 * Implementa una calculadora básica con interfaz gráfica usando SWT.
 * Permite realizar operaciones aritméticas simples: suma, resta, multiplicación y división.
 *
 * @author David Ramírez
 * @version 1.0
 */
public class Calculadora {

    // Constantes

    // Máximo número de dígitos permitidos en la entrada
    final int MAX_DIGITS = 5;
    // Modo de entrada de datos
    final int MODE_ENTRADA = 0;
    // Modo de visualización de resultados
    final int MODE_RESULTADO = 1;

    //Variables

    // Modo actual (entrada o resultado)
    int modo;
    // Último dígito ingresado (no usado directamente)
    int digito;
    // Primer operando
    int valor1;
    // Segundo operando
    int valor2;
    // Operación a realizar
    String operacion;
    // Flag para limpiar el campo de texto en la siguiente entrada
    boolean inicializa_resultado;

    // Campo de texto donde se muestra el resultado
    private static Text texto_resultado;

    /**
     * Constructor de la Clase Calculadora.
     * @param gui true para mostrar la interfaz gráfica, false para trabajar sin GUI.
     */
    public Calculadora(boolean gui) {

        //Inicialización de las variables.
        inicializa();
        // Si se indica, dibuja la calculadora
        if (gui==true) dibujaCalculadora();

    }

    /**
     * Dibuja la interfaz gráfica de la calculadora usando SWT.
     * Contiene los botones numéricos y de operaciones.
     */
    private void dibujaCalculadora() {

        Display display = Display.getDefault();
        Shell shlCalculadora = new Shell();
        shlCalculadora.setSize(259, 250);
        shlCalculadora.setText("Calculadora");

        //------------------------------------------------ -
        //Números
        //------------------------------------------------ -

        //botón con el número 0
        Button button_0 = new Button(shlCalculadora, SWT.NONE);
        button_0.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                anadeNuevoDigito(0);
            }
        });
        button_0.setText("0");
        button_0.setBounds(23, 163, 40, 33);

        //botón con el número 1
        Button button_1 = new Button(shlCalculadora, SWT.NONE);
        button_1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                anadeNuevoDigito(1);
            }
        });
        button_1.setText("1");
        button_1.setBounds(23, 124, 40, 33);

        //botón con el número 2
        Button button_2 = new Button(shlCalculadora, SWT.NONE);
        button_2.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                anadeNuevoDigito(2);
            }
        });
        button_2.setText("2");
        button_2.setBounds(69, 124, 40, 33);

        //botón con el número 3
        Button button_3 = new Button(shlCalculadora, SWT.NONE);
        button_3.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                anadeNuevoDigito(3);
            }
        });
        button_3.setText("3");
        button_3.setBounds(115, 124, 40, 33);

        //botón con el número 4
        Button button_4 = new Button(shlCalculadora, SWT.NONE);
        button_4.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                anadeNuevoDigito(4);
            }
        });
        button_4.setText("4");
        button_4.setBounds(23, 85, 40, 33);

        //botón con el número 5
        Button button_5 = new Button(shlCalculadora, SWT.NONE);
        button_5.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                anadeNuevoDigito(5);
            }
        });
        button_5.setText("5");
        button_5.setBounds(69, 85, 40, 33);

        //botón con el número 6
        Button button_6 = new Button(shlCalculadora, SWT.NONE);
        button_6.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                anadeNuevoDigito(6);
            }
        });
        button_6.setText("6");
        button_6.setBounds(115, 85, 40, 33);

        //botón con el número 7
        Button button_7 = new Button(shlCalculadora, SWT.NONE);
        button_7.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                anadeNuevoDigito(7);
            }
        });
        button_7.setText("7");
        button_7.setBounds(23, 46, 40, 33);

        //botón con el número 8
        Button button_8 = new Button(shlCalculadora, SWT.NONE);
        button_8.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                anadeNuevoDigito(8);
            }
        });
        button_8.setBounds(69, 46, 40, 33);
        button_8.setText("8");

        //botón con el número 9
        Button button_9 = new Button(shlCalculadora, SWT.NONE);
        button_9.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                anadeNuevoDigito(9);
            }
        });
        button_9.setText("9");
        button_9.setBounds(115, 46, 40, 33);

        //------------------------------------------------ -
        //Operaciones
        //------------------------------------------------ -

        //botón con la operacion de división
        Button button_12 = new Button(shlCalculadora, SWT.NONE);
        button_12.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ejecutarOperador("/");
            }
        });
        button_12.setText("/");
        button_12.setBounds(178, 46, 40, 33);

        //botón con la operacion de multiplicación
        Button button_13 = new Button(shlCalculadora, SWT.NONE);
        button_13.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ejecutarOperador("*");
            }
        });
        button_13.setText("*");
        button_13.setBounds(178, 85, 40, 33);

        //botón con la operacion de suma
        Button button_14 = new Button(shlCalculadora, SWT.NONE);
        button_14.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ejecutarOperador("+");
            }
        });
        button_14.setText("+");
        button_14.setBounds(178, 124, 40, 33);

        //botón con la operacion de resto
        Button button_15 = new Button(shlCalculadora, SWT.NONE);
        button_15.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ejecutarOperador("-");
            }
        });
        button_15.setText("-");
        button_15.setBounds(178, 163, 40, 33);

        //botón con la operacion de igual
        Button button_11 = new Button(shlCalculadora, SWT.NONE);
        button_11.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ejecutarIgual();
            }
        });
        button_11.setText("=");
        button_11.setBounds(69, 163, 86, 33);

        //Texto donde se visualiza el resultado
        texto_resultado = new Text(shlCalculadora, SWT.BORDER);
        texto_resultado.setText("0");
        texto_resultado.setBounds(22, 19, 196, 21);

        // Bucle de eventos de SWT
        shlCalculadora.open();
        shlCalculadora.layout();
        while (!shlCalculadora.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Inicializa los valores y estado de la calculadora.
     */
    public void inicializa() {
        operacion = "null";
        valor1 = 0;
        valor2 = 0;
        modo = MODE_ENTRADA;
        inicializa_resultado = true;
    }

    /**
     * Obtiene el contenido del campo de texto como String.
     *
     * @return Cadena actual mostrada en el campo de resultado.
     */
    public String getResultadoString (){
        return texto_resultado.getText();
    }

    /**
     * Establece el contenido del campo de texto.
     *
     * @param s Nueva cadena a mostrar en el resultado.
     */
    public void setResultadoString(String s){
        texto_resultado.setText(s);
    }

    /**
     * Devuelve el contenido del campo de texto convertido a entero.
     *
     * @return Valor numérico del campo de resultado.
     * @throws NumberFormatException si el texto no es numérico.
     */
    public int getResultadoInt() {
        String resultado = texto_resultado.getText();
        return Integer.parseInt(resultado);
    }

    /**
     * Añade un nuevo dígito al campo de resultado.
     *
     * @param digito Dígito numérico entre 0 y 9.
     */
    public void anadeNuevoDigito(int digito){
        if (inicializa_resultado)
            setResultadoString("");

        String inputString = getResultadoString();

        if (inputString.indexOf("0") == 0){
            inputString = inputString.substring(1);
        }

        if ((!inputString.equals("0") || digito > 0) && inputString.length() < MAX_DIGITS){
            setResultadoString(inputString + digito);
        }

        modo = MODE_ENTRADA;
        inicializa_resultado = false;
    }

    /**
     * Maneja la lógica cuando se presiona un operador (+,-,*,/).
     *
     * @param new_operacion Operador en forma de cadena.
     */
    public void ejecutarOperador(String new_operacion) {

        int resultado;

        if (operacion.equals("null"))
        {
            resultado = getResultadoInt();
            valor1 = resultado;
        }

        else
        {
            valor2 = getResultadoInt();
            resultado = ejecutarOperacion();
            muestraResultado(resultado);
            valor1 = resultado;
        }

        inicializa_resultado = true;
        operacion = new_operacion;
    }

    /**
     * Ejecuta la operación pendiente al presionar "=".
     */
    public void ejecutarIgual(){
        int resultado = 0;

        valor2 = getResultadoInt();
        resultado = ejecutarOperacion();
        muestraResultado(resultado);

        operacion = "null";
    }

    /**
     * Ejecuta la operación actual entre valor1 y valor2.
     *
     * @return Resultado de la operación.
     */
    public int ejecutarOperacion() {
        int resultado = 0;

        if (operacion.equals("/"))
        {

            if (valor2 == 0)
            {
                JOptionPane.showMessageDialog(null, "No se puede dividir por cero", "Error", JOptionPane.ERROR_MESSAGE);
                operacion = "null";
                valor1 = 0;
                modo = MODE_ENTRADA;
                inicializa_resultado = true;
            }
            else
                resultado = valor1 / valor2;
        }

        if (operacion.equals("*"))
            resultado = valor1 * valor2;

        if (operacion.equals("-"))
            resultado = valor1 - valor2;

        if (operacion.equals("+"))
            resultado = valor1 + valor2;

        return resultado;
    }

    /**
     * Muestra el resultado en pantalla y prepara para una nueva operación.
     *
     * @param resultado Resultado a mostrar.
     */
    public void muestraResultado(int resultado){
        setResultadoString(Integer.toString(resultado));
        valor1 = resultado;
        modo = MODE_RESULTADO;
        inicializa_resultado = true;
    }

    /**
     * Metodo principal para ejecutar la aplicación.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String args[]) {
        Calculadora calculadora = new Calculadora(true);
    }

}
