package ru.vdh.todocompose.todolist.ui.view

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.vdh.todocompose.common.utils.Action
import ru.vdh.todocompose.common.utils.DateUtils
import ru.vdh.todocompose.core.ui.theme.HighPriorityColor
import ru.vdh.todocompose.core.ui.theme.LARGEST_PADDING
import ru.vdh.todocompose.core.ui.theme.LARGE_PADDING
import ru.vdh.todocompose.core.ui.theme.LowPriorityColor
import ru.vdh.todocompose.core.ui.theme.MediumPriorityColor
import ru.vdh.todocompose.core.ui.theme.NonePriorityColor
import ru.vdh.todocompose.core.ui.theme.PRIORITY_INDICATOR_SIZE
import ru.vdh.todocompose.core.ui.theme.TASK_ITEM_ELEVATION
import ru.vdh.todocompose.todolist.presentation.model.RequestState
import ru.vdh.todocompose.todolist.presentation.model.SearchAppBarState
import ru.vdh.todocompose.todolist.presentation.model.ToDoTaskPresentationModel
import ru.vdh.todocompose.todolist.presentation.viewmodel.SharedViewModel

@ExperimentalAnimationApi
@Composable
fun ListContent(
    allTasks: RequestState<List<ToDoTaskPresentationModel?>>,
    searchedTasks: RequestState<List<ToDoTaskPresentationModel?>>,
    lowPriorityTasks: List<ToDoTaskPresentationModel?>,
    highPriorityTasks: List<ToDoTaskPresentationModel?>,
    sortState: RequestState<String>,
    searchAppBarState: SearchAppBarState,
    onSwipeToDelete: (Action, ToDoTaskPresentationModel?) -> Unit,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel,
    paddingValues: PaddingValues
) {
    if (sortState is RequestState.Success) {
        when {
            searchAppBarState == SearchAppBarState.TRIGGERED -> {
                if (searchedTasks is RequestState.Success) {
                    HandleListContent(
                        tasks = searchedTasks.data,
                        onSwipeToDelete = onSwipeToDelete,
                        navigateToTaskScreen = navigateToTaskScreen,
                        sharedViewModel = sharedViewModel,
                        paddingValues = paddingValues
                    )
                }
            }

            sortState.data == "NONE" -> {
                if (allTasks is RequestState.Success) {
                    HandleListContent(
                        tasks = allTasks.data,
                        onSwipeToDelete = onSwipeToDelete,
                        navigateToTaskScreen = navigateToTaskScreen,
                        sharedViewModel = sharedViewModel,
                        paddingValues = paddingValues
                    )
                }
            }

            sortState.data == "LOW" -> {
                HandleListContent(
                    tasks = lowPriorityTasks,
                    onSwipeToDelete = onSwipeToDelete,
                    navigateToTaskScreen = navigateToTaskScreen,
                    sharedViewModel = sharedViewModel,
                    paddingValues = paddingValues
                )
            }

            sortState.data == "HIGH" -> {
                HandleListContent(
                    tasks = highPriorityTasks,
                    onSwipeToDelete = onSwipeToDelete,
                    navigateToTaskScreen = navigateToTaskScreen,
                    sharedViewModel = sharedViewModel,
                    paddingValues = paddingValues
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun HandleListContent(
    tasks: List<ToDoTaskPresentationModel?>,
    onSwipeToDelete: (Action, ToDoTaskPresentationModel?) -> Unit,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel,
    paddingValues: PaddingValues
) {
    if (tasks.isEmpty()) {
        EmptyContent()
    } else {
        DisplayTasks(
            tasks = tasks,
            onSwipeToDelete = onSwipeToDelete,
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel,
            paddingValues = paddingValues
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@Composable
fun DisplayTasks(
    tasks: List<ToDoTaskPresentationModel?>,
    onSwipeToDelete: (Action, ToDoTaskPresentationModel?) -> Unit,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel,
    paddingValues: PaddingValues
) {
    LazyColumn(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
        items(
            items = tasks,
            key = { task ->
                task?.id ?: 0
            }
        ) { task ->
            val dismissState = rememberDismissState()
            val dismissDirection = dismissState.dismissDirection
            val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)
            if (isDismissed && dismissDirection == DismissDirection.EndToStart) {
                val scope = rememberCoroutineScope()
                SideEffect {
                    scope.launch {
                        delay(300)
                        onSwipeToDelete(Action.DELETE, task)
                    }
                }
            }

            val degrees by animateFloatAsState(
                if (dismissState.targetValue == DismissValue.Default)
                    0f
                else
                    -45f,
                label = ""
            )

            var itemAppeared by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = true) {
                itemAppeared = true
            }

            AnimatedVisibility(
                visible = itemAppeared && !isDismissed,
                enter = expandVertically(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                ),
                exit = shrinkVertically(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                )
            ) {
                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
//                    dismissThresholds = { FractionalThreshold(fraction = 0.2f) },
                    background = { RedBackground(degrees = degrees) },
                    dismissContent = {
                        TaskItem(
                            toDoTask = task,
                            navigateToTaskScreen = navigateToTaskScreen
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun RedBackground(degrees: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HighPriorityColor)
            .padding(horizontal = LARGEST_PADDING),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            modifier = Modifier.rotate(degrees = degrees),
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = ru.vdh.cleanarch.core.ui.R.string.delete_icon),
            tint = Color.White
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun TaskItem(
    toDoTask: ToDoTaskPresentationModel?,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shape = RectangleShape,
        tonalElevation = TASK_ITEM_ELEVATION,
        onClick = {
            toDoTask?.let {
                navigateToTaskScreen(it.id)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row {
                toDoTask?.let {
                    Text(
                        modifier = Modifier.weight(8f),
                        text = it.title,
//                        color = MaterialTheme.colorScheme.surfaceVariant,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                    Log.d("TaskItemToDo", "$toDoTask")
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        toDoTask?.let {
                            drawCircle(
                                color = parsePriority(it.priority)
                            )
                            Log.d("Priority", it.priority)
                        }
                    }
                }
            }
            toDoTask?.let {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = it.description,
                    color = MaterialTheme.colorScheme.outline,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            toDoTask?.let {
                DateUtils.getCurrentDate(toDoTask.date ?: System.currentTimeMillis())?.let {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = it,
                        color = MaterialTheme.colorScheme.outline,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
        }
    }
}

private fun parsePriority(priority: String) =
    when (priority) {
        "HIGH" -> {
            HighPriorityColor
        }

        "MEDIUM" -> {
            MediumPriorityColor
        }

        "LOW" -> {
            LowPriorityColor
        }

        else -> NonePriorityColor
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
private fun TaskItemPreview() {
    TaskItem(
        toDoTask = ToDoTaskPresentationModel(
            id = 0,
            title = "Title",
            description = "Some random text",
            priority = "MEDIUM",
            date = 0
        ),
        navigateToTaskScreen = {}
    )
}

@Composable
@Preview
private fun RedBackgroundPreview() {
    Column(modifier = Modifier.height(80.dp)) {
        RedBackground(degrees = 0f)
    }
}








