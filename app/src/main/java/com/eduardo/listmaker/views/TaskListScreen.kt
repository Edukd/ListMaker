package com.eduardo.listmaker.views

import androidx.compose.foundation.layout.fillMaxSize
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(navigate: (String) -> Unit) {
    val taskListViewModel: ListDataManager = viewModel()
    val viewModelTask = taskListViewModel.ReadList().toList()
    var tasks by remember {
        mutableStateOf(viewModelTask)
    }
    Scaffold(
        topBar = {
            ListMakerTopAppBar(
                title = stringResource(id = R.string.label_list_maker),
                showBackButton = false,
                onBackPressed = {}
            )
        },
        content = {
            TaskListContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            tasks = tasks,
            onClick = { taskName ->
               navigate(taskName)}
            )
        },
        floatingActionButton = {
            ListMakerFloatingActionButton(
                title = stringResource(id = R.string.name_of_list) ,
                inputHint = stringResource(id = R.string.task_hint),
                onFabClick = {
                    tasks = (tasks + TaskList(it))
                    taskListViewModel.SaveList(TaskList(it))
                    navigate(it)

                }
            )
        }
    )
}

@Composable
fun TaskListContent(
    modifier: Modifier,
    tasks: List<TaskList>,
    onClick: (String) -> Unit
) {
    if (tasks.isEmpty()) {
        EmptyView(message = stringResource(id = R.string.text_no_tasks))
    } else {
        LazyColumn(modifier = modifier, content = {
            items(tasks) {
                ListItemView(value = it.name, onClick = onClick)
            }
        })

    }
}