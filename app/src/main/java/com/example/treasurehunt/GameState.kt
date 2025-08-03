import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.room.util.copy
import com.example.treasurehunt.Cell
import com.example.treasurehunt.CellContent
import com.example.treasurehunt.TreasureHunterViewModel
import com.example.treasurehunt.ui.theme.TreasureHuntTheme

class GameState (viewModel: TreasureHunterViewModel) {
    val gridSize = 8
    var grid: SnapshotStateList<SnapshotStateList<Cell>> = mutableStateListOf()
    var playerPosition by mutableStateOf(Pair(0, 0))

    var viewModel = viewModel

    init {

        for (i in 0 until gridSize) {
            val row = mutableStateListOf<Cell>()
            for (j in 0 until gridSize) {
                row.add(Cell(x = j, y = i, initialContent = CellContent.EMPTY))
            }
            grid.add(row)
        }

        grid[playerPosition.second][playerPosition.first].isPlayer = true


        placeRandomContent(CellContent.TREASURE, 3)
        placeRandomContent(CellContent.OBSTACLE, 5)
    }

    private fun placeRandomContent(content: CellContent, count: Int) {
        var placedCount = 0
        while (placedCount < count) {
            val randomX = (0 until gridSize).random()
            val randomY = (0 until gridSize).random()

            if (grid[randomY][randomX].content == CellContent.EMPTY) {
                grid[randomY][randomX].content = content
                placedCount++
            }
        }
    }

    fun movePlayer(newX: Int, newY: Int) { // Não precisa mais do parâmetro grid nem do retorno
        if (newX < 0 || newX >= gridSize || newY < 0 || newY >= gridSize) {
            Log.i("aaaa","Limite do grid")
            return
        }

        if (grid[newY][newX].isDig) return

        grid[playerPosition.second][playerPosition.first].isPlayer = false
        playerPosition = Pair(newX, newY)
        grid[newY][newX].isPlayer = true

    }

    fun getItem(posX: Int, posY: Int){

        var gridItern = grid[playerPosition.second][playerPosition.first]
        when (gridItern.content)
        {
            CellContent.OBSTACLE -> {
                this.viewModel.addObstacules()
                gridItern.isDig = true
                gridItern.isRevealed = true
            }

            else -> {
                gridItern.isRevealed = true
                gridItern.isDig = true
            }
        }
        grid[playerPosition.second][playerPosition.first] = gridItern

    }


    // fun dig(x: Int, y: Int) { ... }
    // fun useMetalDetector(x: Int, y: Int) { ... }
    // fun useDynamite(x: Int, y: Int) { ... }
}

