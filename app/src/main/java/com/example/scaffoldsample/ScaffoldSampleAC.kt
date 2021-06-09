package com.example.scaffoldsample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ScaffoldSample(){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState= scaffoldState,
        topBar = { TopBar(scaffoldState, scope) },
        drawerContent = { DrawerContent() },
        bottomBar = { SimpleBottomBar() }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "This is static content",
            )
        }

    }

}

@Composable
fun SimpleBottomBar() {

    val items = listOf(
        BottomBarItem(title = "Home", icon = R.drawable.ic_home),
        BottomBarItem(title = "Search", icon = R.drawable.ic_search),
        BottomBarItem(title = "Favourite", icon = R.drawable.ic_favourites),
        BottomBarItem(title = "Profile", icon = R.drawable.ic_profile),
    )

    BottomNavigation(
        elevation = 5.dp
    ){

        items.map {
            BottomNavigationItem(
                icon= {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.title
                    )
                },
                label= {
                    Text(
                        text = it.title
                    )
                },
                selected = false,
                selectedContentColor= Color.White,
                unselectedContentColor= Color.White.copy(alpha = 0.4f),
                onClick = {

                }
            )
        }

    }
}

@Composable
fun DrawerContent() {
    for ( i in 0 until 5 ){
        Text(text = "Item $i")
    }
}

@Composable
fun TopBar(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    TopAppBar(
        title = {
            Text(
                text = "ScaffoldSample",
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "menu"
                )
            }
        },
        elevation = 8.dp
    )
}