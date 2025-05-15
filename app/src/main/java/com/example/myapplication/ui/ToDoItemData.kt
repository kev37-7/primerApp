package com.example.myapplication.ui

// esta clase de datos representa una tarea individual en la aplicacion
// contiene el texto de la tarea y un estado booleano que indica si ya fue completada
data class ToDoItemData(
    var text: String, // texto que describe la tarea
    var isDone: Boolean = false // indica si la tarea ha sido completada por defecto esta en falso
)
