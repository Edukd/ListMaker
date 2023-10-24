package com.eduardo.listmaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.eduardo.listmaker.navigation.AppNavHost
import com.eduardo.listmaker.ui.theme.AppTheme
import com.eduardo.listmaker.views.TaskListScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
              AppNavHost()
                }
            }
        }
    }

