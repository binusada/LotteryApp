package com.mykodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mykodo.ui.components.DrawDetailsScreen
import com.mykodo.ui.components.DrawListScreen
import com.mykodo.ui.navigation.MainScreen
import com.mykodo.ui.navigation.NavGraph
import com.mykodo.ui.theme.MykodoLotteryAppTheme
import com.mykodo.vm.DrawsUIState
import com.mykodo.vm.DrawsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: DrawsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { NavGraph(viewModel) }
        viewModel.fetchDraws(this)
    }
}
