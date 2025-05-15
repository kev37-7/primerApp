package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import com.example.myapplication.ui.ToDoApp
import com.example.myapplication.ui.theme.MyApplicationTheme

// esta clase representa la actividad principal de la aplicacion
// extiende de componentactivity lo que permite usar jetpack compose para definir la interfaz
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setcontent se usa para definir el contenido visual de la app usando composables
        setContent {
            // se aplica el tema definido en el proyecto
            MyApplicationTheme {
                // scaffold proporciona una estructura base para pantallas con padding y otros elementos
                Scaffold { innerPadding ->
                    // se muestra la interfaz principal pasando el padding proporcionado por scaffold
                    ToDoApp(padding = innerPadding)
                }
            }
        }
    }
}
