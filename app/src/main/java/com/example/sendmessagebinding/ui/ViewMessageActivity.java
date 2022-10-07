package com.example.sendmessagebinding.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sendmessagebinding.R;
import com.example.sendmessagebinding.data.Message;
import com.example.sendmessagebinding.databinding.ActivityViewMessageBinding;

/**
 * Clase que muestraa el mensaje de un usuario. Recoge el mensaje que implementa la interfaz Parcelable
 * @author Alumno Damian Avila
 * @version 1.0
 */
public class ViewMessageActivity extends AppCompatActivity {


    private static final String TAG = "ViewMessageActivity";
    private ActivityViewMessageBinding binding;

    /**
     * Método que se ejecuta cuando se crea la Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d(TAG, "ViewMessageActivity -> onCreate()");

        //1. Asignar a mis actividades de esta clase las características de las de la View

        //2. Recoger el Bundle que me han pasado en el Intent que ha iniciado esta actividad
        Bundle bundle = getIntent().getExtras();
        Message message = bundle.getParcelable("message");
        binding.setMessage(message);

        //3. Mostrar los datos de estos
//        binding.tvUser2.setText(message.getUser().getName());
//        binding.tvMessage2.setText(message.getContent());
    }

    //endregion

    /**
     * Este método se ejecuta cuando se inicia una Actívity
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "ViewMessageActivity -> onStart()");
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
        Log.d(TAG, "ViewMessageActivity -> onStop()");
    }

    /**
     * Este método se ejecuta cuando se destruye la Activity
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding=null;
        Log.d(TAG, "ViewMessageActivity -> onDestroy()");
    }
}