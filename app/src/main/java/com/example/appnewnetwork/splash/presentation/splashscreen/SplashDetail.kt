//package com.example.appnewnetwork.splash.presentation.splashscreen
//
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun SplashScreenRoute(
//    imageUrl: String
//) {
//    SplashScreen(imageUrl = imageUrl)
//}
//
//@Composable
//fun SplashScreen(imageUrl: String) {
//    Scaffold {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(it)
//                .padding(16.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = imageUrl
//            )
//
//        }
//    }
//}
//
//@Preview
//@Composable
//private fun SplashScreenPreview() {
//    SplashScreen(
//        imageUrl = "url"
//    )
//}