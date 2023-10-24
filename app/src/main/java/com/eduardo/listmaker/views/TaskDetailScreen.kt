package com.eduardo.listmaker.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eduardo.listmaker.R
import com.eduardo.listmaker.data.TaskList
import com.eduardo.listmaker.viewmodel.ListDataManager

@Composable
fun TaskDetailsScreenContent(
    modifier: Modifier,
    taskTodos: List<String>
) {
    if (taskTodos.isEmpty()) {
        EmptyView(message = stringResource(id = R.string.text_no_todos))
    } else {
        LazyColumn(
            modifier = modifier,
            content = {
                items(taskTodos) {
                    ListItemView(
                        value = it,
                        onClick = { }
                    )
                }
            }
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    taskName: String?,
    onBackPressed: () -> Unit
) {
    val viewModel: ListDataManager = viewModel()
    var taskTodos by remember {
        mutableStateOf(viewModel.ReadList().firstOrNull { it.name == taskName }?.tasks ?: emptyList())
    }

    Scaffold(
        topBar = {
            ListMakerTopAppBar(
                title = taskName ?: stringResource(id = R.string.label_task_list)
                ,
                showBackButton = true,
                onBackPressed = onBackPressed
            )
        },
        content = {
            TaskDetailsScreenContent(modifier = Modifier.padding(it), taskTodos = taskTodos)
        },
        floatingActionButton = {
            ListMakerFloatingActionButton(
                title = stringResource(id = R.string.task_to_add),
                inputHint = stringResource(id = R.string.task_hint),
                onFabClick = { todoName ->
                    viewModel.SaveList(TaskList(taskName ?: "", taskTodos + listOf(todoName)))
                    taskTodos = viewModel.ReadList().firstOrNull { it.name == taskName }?.tasks
                        ?: emptyList()


                }
            )
        }
    )

}

