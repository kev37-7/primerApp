package com.example.myapplication.ui

import androidx.compose.runtime.*

// esta clase representa el estado de la aplicacion de tareas
class ToDoState {

    // almacena el texto que el usuario esta escribiendo en el campo de entrada
    var taskText by mutableStateOf("")

    // lista de tareas que puede ser observada y modificada de forma reactiva
    var taskList = mutableStateListOf<ToDoItemData>()

    // actualiza el texto de la tarea cuando el usuario escribe
    fun onTaskTextChange(newText: String) {
        taskText = newText
    }

    // agrega una nueva tarea a la lista si el texto no esta vacio
    fun addTask() {
        if (taskText.isNotBlank()) {
            taskList.add(ToDoItemData(taskText))
            taskText = ""
        }
    }

    // elimina una tarea especifica de la lista
    fun removeTask(task: ToDoItemData) {
        taskList.remove(task)
    }

    // cambia el estado de completado de una tarea al valor opuesto
    fun toggleTaskDone(task: ToDoItemData) {
        task.isDone = !task.isDone
    }

    // edita el texto de una tarea existente
    fun editTask(task: ToDoItemData, newText: String) {
        task.text = newText
    }
}

// esta funcion composable recuerda y devuelve una instancia del estado de tareas
@Composable
fun rememberToDoState(): ToDoState = remember { ToDoState() }
