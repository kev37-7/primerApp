@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// composable principal que representa toda la interfaz de la aplicacion de tareas
@Composable
fun ToDoApp(padding: PaddingValues) {
    // se obtiene el estado de la aplicacion contiene el texto actual la lista de tareas y funciones para modificarlas
    val state = rememberToDoState()

    // componente scaffold que organiza la pantalla en diferentes secciones como la barra superior y el contenido principal
    Scaffold(
        topBar = {
            // barra superior centrada que muestra el titulo de la app
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Gestor de Tareas", // titulo de la app
                        style = MaterialTheme.typography.headlineSmall // estilo de titulo
                    )
                }
            )
        }
    ) { innerPadding ->

        // contenido principal del scaffold organizado en una columna vertical
        Column(
            modifier = Modifier
                .fillMaxSize() // ocupa todo el alto y ancho disponible
                .padding(innerPadding) // padding interno del scaffold
                .padding(padding) // padding externo recibido como parametro
                .padding(16.dp) // padding adicional uniforme
        ) {
            // fila que contiene el campo de texto para ingresar una nueva tarea y el boton para agregarla
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically // centrado verticalmente dentro de la fila
            ) {
                // campo de texto donde el usuario escribe el contenido de una nueva tarea
                TextField(
                    value = state.taskText, // texto actual del campo
                    onValueChange = { state.onTaskTextChange(it) }, // actualiza el estado cuando cambia el texto
                    modifier = Modifier.weight(1f), // ocupa todo el espacio disponible de la fila
                    label = { Text("Nueva tarea") }, // etiqueta del campo de texto
                    singleLine = true // el texto se limita a una sola linea
                )

                // espacio entre el campo de texto y el boton
                Spacer(modifier = Modifier.width(8.dp))

                // boton para agregar una nueva tarea solo se habilita si el texto no esta en blanco
                Button(
                    onClick = { state.addTask() }, // agrega la tarea al hacer clic
                    enabled = state.taskText.isNotBlank() // el boton se desactiva si el texto esta vacio
                ) {
                    Text("Agregar")
                }
            }

            // espacio entre la seccion de entrada y la lista de tareas
            Spacer(modifier = Modifier.height(24.dp))

            // titulo para la seccion de la lista de tareas
            Text(
                text = "Mis tareas",
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp), // estilo personalizado
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // lista vertical perezosa que muestra todas las tareas de forma eficiente
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp), // espaciado entre elementos
                modifier = Modifier.fillMaxSize() // ocupa todo el espacio vertical restante
            ) {
                // se recorre la lista de tareas actual
                items(state.taskList) { task ->
                    // cada tarea se muestra dentro de una tarjeta estilizada
                    Card(
                        modifier = Modifier
                            .fillMaxWidth() // la tarjeta ocupa todo el ancho disponible
                            .padding(horizontal = 4.dp), // padding horizontal leve para separar del borde
                        shape = MaterialTheme.shapes.medium, // forma de la tarjeta con esquinas suavizadas
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // sombra para dar profundidad
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface // color del fondo segun el tema
                        )
                    ) {
                        // componente que representa visualmente una tarea individual
                        ToDoItem(
                            task = task, // se pasa la tarea actual
                            onRemove = { state.removeTask(task) }, // accion para eliminar la tarea
                            onToggleDone = { state.toggleTaskDone(task) }, // accion para marcar como completada o no
                            onEdit = { newText -> state.editTask(task, newText) } // accion para editar el texto
                        )
                    }
                }
            }
        }
    }
}
