import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.treasurehunt.Cell
import com.example.treasurehunt.CellContent
import com.example.treasurehunt.R
import com.example.treasurehunt.ui.theme.TreasureHuntTheme // Seu tema

//@SuppressLint("UnrememberedMutableState")
@Composable
fun GameGrid(gameState: GameState, grid: List<List<Cell>>, modifier: Modifier) {

//    var grid by remember { mutableStateOf(gameState.grid) }

    Column(
        modifier = Modifier
            .aspectRatio(1f)
            .border(2.dp, Color.Black)
            .background(Color(0xFF8B4513))
    ) {
        grid.forEachIndexed { y, row ->
            Row(
                modifier = Modifier
                    .weight(1f)

            ) {
                row.forEachIndexed { x, cell ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .border(0.5.dp, Color.DarkGray)
                            .background(
                                Color(0xFFA0522D)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.areia1),
                            contentDescription = "areia",
                            modifier = Modifier.fillMaxSize(1f)
                        )
                        if (cell.isDig){
                            Image(
                                painter = painterResource(R.drawable.areiacavada),
                                contentDescription = "areia",
                                modifier = Modifier.fillMaxSize(1f)
                            )
                        }
                        when (cell.content) {
                            CellContent.TREASURE -> {
                                if (cell.isRevealed) {
                                    Image(
                                        painter = painterResource(R.drawable.metaldetector),
//                                        painter = painterResource(R.drawable.treasure_icon),
                                        contentDescription = "Tesouro",
                                        modifier = Modifier.fillMaxSize(0.7f)
                                    )
                                } else {
                                }
                            }
                            CellContent.OBSTACLE -> {
                                if (cell.isRevealed) {
                                    Image(
                                        painter = painterResource(R.drawable.dynamite),
//                                    painter = painterResource(R.drawable.obstacle_icon),
                                        contentDescription = "Obstáculo",
                                        modifier = Modifier.fillMaxSize(0.7f)
                                    )
                                }
                            }

                            else -> {}
                        }

                        if (cell.isPlayer){
                            Image(
                                painter = painterResource(R.drawable.pa),
                                contentDescription = "Player",
                                modifier = Modifier.fillMaxSize(0.8f)
                            )
                        }
                    }
                }
            }
        }
    }
}