package com.example.myapplication.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

// esta funcion representa un solo item de tarea en la lista
@Composable
fun ToDoItem(
    task: ToDoItemData, // datos de la tarea actual
    onRemove: () -> Unit, // funcion que se ejecuta al eliminar
    onToggleDone: () -> Unit, // funcion que se ejecuta al marcar como completada o no
    onEdit: (String) -> Unit // funcion que se ejecuta al guardar una edicion
) {
    // determina si el item esta en modo de edicion
    var isEditing by remember { mutableStateOf(false) }

    // texto editable para la tarea cuando esta en modo de edicion
    var editText by remember { mutableStateOf(task.text) }

    // fila que organiza los elementos horizontalmente
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (isEditing) {
            // si esta editando muestra un campo de texto y un boton para guardar
            TextField(
                value = editText,
                onValueChange = { editText = it },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                onEdit(editText)
                isEditing = false
            }) {
                Text("Guardar")
            }
        } else {
            // si no esta editando muestra el texto de la tarea con opcion de marcar como completada
            Text(
                text = task.text,
                style = MaterialTheme.typography.bodyLarge.copy(
                    textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None
                ),
                modifier = Modifier
                    .weight(1f)
                    .clickable { onToggleDone() } // al hacer click se alterna el estado de completado
            )
            // boton para editar la tarea
            IconButton(onClick = { isEditing = true }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
            }
            // boton para eliminar la tarea
            IconButton(onClick = { onRemove() }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")
            }
            // boton para marcar la tarea como completada o no
            IconButton(onClick = { onToggleDone() }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Completar")
            }
        }
    }
}
