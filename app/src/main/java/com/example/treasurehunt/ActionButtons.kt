package com.example.treasurehunt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.treasurehunt.ui.theme.TreasureHuntTheme

@Composable
fun ActionButtons(
    onDigClick: () -> Unit,
    onMetalDetectorClick: () -> Unit,
    onDynamiteClick: () -> Unit
) {
    TreasureHuntTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = onDigClick,
                    modifier = Modifier
                        .size(60.dp)
                        .border(
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                            shape = MaterialTheme.shapes.small
                        )
                ) {
                    Image(
                        painter = painterResource(R.drawable.pa),
                        contentDescription = "Pá",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                IconButton(
                    onClick = onMetalDetectorClick,
                    modifier = Modifier
                        .size(60.dp)
                        .border(
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                            shape = MaterialTheme.shapes.small
                        )
                ) {
                    Image(
                        painter = painterResource(R.drawable.metaldetector),
                        contentDescription = "Pá",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                IconButton(
                    onClick = onDynamiteClick,
                    modifier = Modifier
                        .size(60.dp)
                        .border(
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                            shape = MaterialTheme.shapes.small
                        )
                ) {
                    Image(
                        painter = painterResource(R.drawable.dynamite),
                        contentDescription = "Pá",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}
