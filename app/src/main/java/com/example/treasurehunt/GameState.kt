import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.room.util.copy
import com.example.treasurehunt.Cell
import com.example.treasurehunt.CellContent

class GameState {
    val gridSize = 8
    var grid: SnapshotStateList<SnapshotStateList<Cell>> = mutableStateListOf()
    var playerPosition by mutableStateOf(Pair(0, 0))

    init {

        for (i in 0 until gridSize) {
            val row = mutableStateListOf<Cell>()
            for (j in 0 until gridSize) {
                row.add(Cell(x = j, y = i, content = CellContent.EMPTY))
            }
            grid.add(row)
        }

        grid[playerPosition.second][playerPosition.first].content = CellContent.PLAYER


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

    fun movePlayer(newX: Int, newY: Int, grid: SnapshotStateList<SnapshotStateList<Cell>> = this.grid): SnapshotStateList<SnapshotStateList<Cell>> {

        if (newX < 0 || newX >= gridSize || newY < 0 || newY >= gridSize) {
            Log.i("aaaa","Limite do grid")
            return grid
        }
//        Log.i("", "entrou no MovePlayer")

        grid[playerPosition.second][playerPosition.first].content = CellContent.EMPTY


        playerPosition = Pair(newX, newY)
        Log.i("aaaa","movendo para ($newX, $newY)")


        grid[newY][newX].content = CellContent.PLAYER
        Log.i("aaaa","${grid[newY][newX].content}")
        return grid
    }

    // fun dig(x: Int, y: Int) { ... }
    // fun useMetalDetector(x: Int, y: Int) { ... }
    // fun useDynamite(x: Int, y: Int) { ... }
}

