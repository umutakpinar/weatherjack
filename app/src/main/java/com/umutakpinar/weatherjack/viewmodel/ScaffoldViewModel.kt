package com.umutakpinar.weatherjack.viewmodel

import android.graphics.drawable.shapes.Shape
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScaffoldViewModel @Inject constructor() : ViewModel() {
    suspend fun toggleDrawer(scaffoldState: ScaffoldState){
        if(scaffoldState.drawerState.isClosed){
            scaffoldState.drawerState.open()
        }else{
            scaffoldState.drawerState.close()
        }
    }

}