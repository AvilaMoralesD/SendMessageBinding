package com.example.sendmessagebinding.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sendmessagebinding.R;
import com.example.sendmessagebinding.SendMessageApplication;
import com.example.sendmessagebinding.data.Message;
import com.example.sendmessagebinding.data.User;
import com.example.sendmessagebinding.databinding.ActivitySendMessageBinding;

/**
 * <h1>Proyecto Send Message</h1>
 * En este proyecto aprenderemos a realizar las siguientes operaciones
 * <ol>
 *     <li>Crear un evento en un componente  Button en código XML</li>
 *     <li>Crear un escuchador/listener del evento <code>OnClick()</code></li>
 *     <li>Crear un Intent y un elemento Bundle para pasar información entre Activity</li>
 *     <li>El ciclo de vida de una Activity</li>
 *     <li>Manejar la pila de Activity</li>
 * </ol>
 *
 * @author Alumno Damian Avila
 * @version 1.0
 * @see android.widget.Button
 * @see android.app.Activity
 * @see android.content.Intent
 * @see android.os.Bundle
 */
public class SendMessageActivity extends AppCompatActivity {

    private static final String TAG = "SendMessageActivity";
    private SendMesageOnClickListener delegado;
    private ActivitySendMessageBinding binding;
    //region Ciclo de vida de la Activity

    /**
     * Este método callback crea el menú de opciones en la vista. Se devuelve true
     * para indicar al SO que se ha realizado la opcion de modificar el menú
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Este método callback se llama cuando se pulsa sobre una de las opciones del menú
     *  de las aplicaciones
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId()) {
           case R.id.action_aboutus:
               Toast.makeText(this,"Se ha pulsado sobre AboutUs", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(this, AboutUsActivity.class);
               startActivity(intent);
               break;
       }
        return true;
    }

    /**
     * Método que se ejecuta cuando se crea la Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySendMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*Metodo con delegados //Se inicializa el delegado
        delegado = new SendMesageOnClickListener();
        //Se establece el vínculo entre el botón y el delegado
        btSend.setOnClickListener(delegado);*/

        /* Método con clase anónima//Establecer el Listener OnClickListener
         al botón que implementa la interfaz View.OnClickListener
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SendMessageActivity.this,
                        "Esto es a través de una clase anónima", Toast.LENGTH_SHORT).show();
            }
        });*/

        //La forma más actual, hacerlo con expresiones lambda
        //binding.setMessage(new Message(new User("Damian","damian@gmail")));
        //Con la nueva clase que hereda de aplicación que es SendMessageApplication hacemos un casting
        binding.setMessage(new Message(((SendMessageApplication)getApplication()).getUser()));
        binding.btSend.setOnClickListener(view -> sendMessage());
        //view -> Toast.makeText(SendMessageActivity.this, "Esto es a través de una clase anónima", Toast.LENGTH_SHORT).show());

        Log.d(TAG, "SendMessageActivity -> onCreate()");
    }
    //endregion

    /**
     * Este método se ejecuta cuando se inicia una Actívity
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "SendMessageActivity -> onStart()");
    }

    /**
     * Este método se ejecuta cuando continua una Actívity
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "SendMessageActivity -> onResume()");
    }

    /**
     * Este método se ejecuta cuando se pausa una Actívity
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "SendMessageActivity -> onPause()");
    }

    /**
     * Este método se ejecuta cuando se para una Actívity
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "SendMessageActivity -> onStop()");
    }

    /**
     * Este método se ejecuta cuando se destruye la Activity
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding=null;
        Log.d(TAG, "SendMessageActivity -> onDestroy()");
    }

    /**
     * Este método es el que se llama cuando se pulsa sobre el botón btSend definido en el XML     *
     * <p><code>OnClick()</code> = "sendMessage"</p>
     *
     * @param view - The view
     */
    public void sendMessage(View view) {
        sendMessage();
    }

    public void sendMessage() {
        //  Toast.makeText(this,"Hemos pulsado el botón",Toast.LENGTH_SHORT).show(); Esto era para mostrar mensaje

        //1.1 Crear el contenedor para añadir los datos
        Bundle bundle = new Bundle();
    //1.2 pasar dato a dato
    bundle.putString("user", binding.getMessage().getUser().getName());
    bundle.putString("message", binding.getMessage().getContent());
/*        //1 Crear objeto Message y Bundle parcelable
        Message message = new Message(binding.etUser.getText().toString(), binding.etMessage.getText().toString());
        bundle.putParcelable("message", message);*/
        //1.2 Crear un objeto Message
        bundle.putParcelable("message", binding.getMessage());

        //2. Vamos a crear el objeto Intent (un sobre) explícito porque se conoce la Actividad destino
        Intent intent = new Intent(this, ViewMessageActivity.class);
        intent.putExtras(bundle);

        //3. Se inicia la transición entre una vista y otra mediante SendActivity
        startActivity(intent);
    }

    //Esta clase ya no es anónima e implementa el listener que responde siempre al evento OnClick
    class SendMesageOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Toast.makeText(SendMessageActivity.this, "Esto es a través de una clase normal", Toast.LENGTH_SHORT).show();
        }
    }
}