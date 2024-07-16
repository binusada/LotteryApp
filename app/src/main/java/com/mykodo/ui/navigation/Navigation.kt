package com.mykodo.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mykodo.ui.components.DrawDetailsScreen
import com.mykodo.ui.components.DrawListScreen
import com.mykodo.ui.navigation.Screen.DetailsScreen
import com.mykodo.ui.navigation.Screen.MainScreen
import com.mykodo.ui.theme.MykodoLotteryAppTheme
import com.mykodo.vm.DrawsViewModel

@Composable
fun NavGraph(viewModel: DrawsViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreen.route
    ) {
        composable(
            route = MainScreen.route
        ) {
            MainScreen(viewModel) { id ->
                navController.navigate(route = DetailsScreen.route + "/${id}")
            }
        }
        composable(
            route = DetailsScreen.route + "/{${DetailsScreen.argumentId}}",
            arguments = listOf(navArgument(name = DetailsScreen.argumentId) {
                type = NavType.StringType
            }
            )) {
            it.arguments?.getString(DetailsScreen.argumentId)?.let { id ->
                DetailsScreen(viewModel, id) {
                    navController.popBackStack()
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    viewModel: DrawsViewModel,
    onDrawClicked: (id: String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MykodoLotteryAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBar(
                    title = "My Lotto",
                    isBackEnabled = false
                )
            }
        ) { innerPadding ->
            DrawListScreen(
                modifier = Modifier.padding(innerPadding),
                drawList = uiState.draws,
                onDrawClicked = onDrawClicked,
                currencyFormatter = viewModel.currencyFormatter,
                dateFormatHelper = viewModel.dateFormatHelper,
                numberFormatter = viewModel.numberFormatter
            )
        }
    }
}

@Composable
fun DetailsScreen(
    viewModel: DrawsViewModel,
    id: String,
    onBackClicked: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MykodoLotteryAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBar(
                    title = "Ticket Details",
                    isBackEnabled = true,
                    onBackClick = { onBackClicked() },
                    )
            }
        ) { innerPadding ->
            uiState.draws.find { it.id == id }?.let { draw ->
                DrawDetailsScreen(
                    modifier = Modifier.padding(innerPadding),
                    draw = draw,
                    currencyFormatter = viewModel.currencyFormatter,
                    dateFormatHelper = viewModel.dateFormatHelper,
                    numberFormatter = viewModel.numberFormatter
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    isBackEnabled: Boolean,
    onBackClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        },
        navigationIcon =
        if (isBackEnabled) {
            {
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            {}
        }
    )
}






