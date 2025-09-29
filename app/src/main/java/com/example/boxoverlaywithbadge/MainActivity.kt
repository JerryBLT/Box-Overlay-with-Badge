package com.example.boxoverlaywithbadge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boxoverlaywithbadge.ui.theme.BoxOverlayWithBadgeTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoxOverlayWithBadgeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileBadge(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileBadge( modifier: Modifier = Modifier) {
    //State variable controlling whether the badge is shown or hidden
    var showBadge by remember { mutableStateOf(true) }

    //Column to arrange profile picture + badge at the top, button below
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Container for profile picture and badge
        Box(
            modifier = Modifier.size(120.dp), // Match profile image size
            contentAlignment = Alignment.Center
        ) {
            // Profile picture
            Box(
                modifier = Modifier
                    .size(120.dp) // Profile image size
                    .clip(CircleShape) //Round shape for badge
                    .background(Color.LightGray) // Placeholder color
                    .border(2.dp, Color.White, CircleShape) // White border
            )

            // Notification badge
            if (showBadge) {
                Box(
                    modifier = Modifier
                        .size(28.dp) //Size of the badge
                        .align(Alignment.BottomEnd) // Position badge correctly
                        .clip(CircleShape) //Round shape for badge
                        .background(Color.Blue),
                    contentAlignment = Alignment.Center
                ) {
                    //Text inside badge
                    Text("®", color = Color.White, style = MaterialTheme.typography.labelSmall)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Toggle button
        Button(onClick = { showBadge = !showBadge }) {
            Text(if (showBadge) "Hide Badge" else "Show Badge")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BoxOverlayWithBadgeTheme {
        ProfileBadge()
    }
}