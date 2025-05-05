package com.example.battlecommand

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.battlecommand.R.drawable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val windowInsertController = WindowCompat.getInsetsController(window, window.decorView)
            windowInsertController.apply {
                systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE //To auto-rehide, behaviour is set to this
                hide(WindowInsetsCompat.Type.systemBars()) //used to hide the system bars
            }

            val navController = rememberNavController()
            NavHost(navController,startDestination = "menu") {
                composable("menu") {
                    MainMenu(10,onStartGame = {
                        navController.navigate("game")
                    })
                }
                composable("game") {
                    BattleArmadaGameScreen(10)
                }
            }
        }
    }
}
const val GRID_SIZE = 6

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420",
    name = "Game Menu Preview",

    )
@Composable
private fun PreviewFunction() {
    BattleArmadaGameScreen(5)
}

@Composable
fun MainMenu(paddingValues: Int,onStartGame:() -> Unit) {
    var showHelpDialog by remember { mutableStateOf(false) }
    var onPlayClicked by remember { mutableStateOf(false) }
    val gradient =  Brush.verticalGradient(colors = listOf(Color(0xFF555555), Color(0xFF888888)))
    val (screenWidth, screenHeight) = getScreenSizeDp()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient),
        contentAlignment = Alignment.Center) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .height(26.dp)
            )
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "BATTLESHIPS",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    //Text 1
                )
                Text(
                    text = "ARMADA",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                    //Text 2
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight / 2 + 80).dp), // Adjust height as needed
                contentAlignment = Alignment.Center
            ) {
                // Draw a circle that is larger than the screen
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val radius = 600f // Radius of the circle
                    val centerX = size.width / 2 // Center X position
                    val centerY = size.height / 2 // Center Y position)

                    drawCircle(
                        color = Color.White,
                        radius = radius,
                        center = Offset(centerX, centerY)
                    )
                }
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val radius = 500f // Radius of the circle
                    val centerX = size.width / 2 // Center X position
                    val centerY = size.height / 2 // Center Y position)

                    drawCircle(
                        color = Color(0xFFE0E0E0),
                        radius = radius,
                        center = Offset(centerX, centerY)
                    )
                }
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val radius = 198f // Radius of the circle
                    val centerX = size.width / 2 // Center X position
                    val centerY = size.height / 2 + 6// Center Y position)

                    drawCircle(
                        color = Color(0xFF7E7E7E),
                        radius = radius,
                        center = Offset(centerX, centerY)
                    )
                }
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val radius = 200f // Radius of the circle
                    val centerX = size.width / 2 // Center X position
                    val centerY = size.height / 2 // Center Y position)

                    drawCircle(
                        color = Color.White,
                        radius = radius,
                        center = Offset(centerX, centerY)

                    )
                }

                PlayIcon(onPlayClicked = onStartGame)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        Modifier
                            .offset(x = (-30).dp)
                            .size(90.dp, 100.dp)
                            .background(Color.Black, shape = RoundedCornerShape(10.dp))
                    )
                    Box(
                        Modifier
                            .offset(x = (30).dp)
                            .size(90.dp, 100.dp)
                            .background(Color(0xFF60D2C6), shape = RoundedCornerShape(10.dp))
                    )
                }
            }


            Spacer(modifier = Modifier.weight(12f))

            SettingsAndHelpIcons(onSettingsClicked =  {},
                onHelpClicked = { showHelpDialog = true })

        }
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center) {
            if (showHelpDialog) {
                GameDescriptionDialog(
                    showDialog = true,
                    onDismiss = { showHelpDialog = false }
                )
            }
        }
    }

}

@Composable
fun getScreenSizeDp(): Pair<Int, Int> {
    val config = LocalConfiguration.current
    return Pair(config.screenWidthDp, config.screenHeightDp)
}

@Composable
fun SettingsAndHelpIcons(
    onSettingsClicked: () -> Unit = {},
    onHelpClicked: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        IconButton(onClick = onSettingsClicked) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Settings",
                tint = Color(0xFF444400),
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Yellow, shape = CircleShape)
            )
        }

        IconButton(onClick = onHelpClicked) {
            Icon(
                imageVector = Icons.Filled.Help,
                contentDescription = "Help",
                tint = Color(0xFF444400),
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Yellow, shape = CircleShape)
            )
        }
    }
}

@Composable
fun PlayIcon(onPlayClicked: () -> Unit = {}) {
    IconButton(onClick = onPlayClicked,
        modifier = Modifier.clickable {
            onPlayClicked()
        }) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "Play",
            tint = Color.White,
            modifier = Modifier
                .size(140.dp)
                .background(Color(0xFFFF5722), shape = CircleShape)
                .padding(12.dp)
        )
    }
}


@Composable
fun GameDescriptionDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Battleships Armada",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "Sink enemy ships by guessing their locations. Use strategy and intuition to outwit your opponent!\n",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(Color.DarkGray) ) {
                        Text("Close",
                            color = Color.White)
                    }
                }
            }
        }
    }
}


@Composable
fun BattleArmadaGameScreen(paddingValues: Int) {
    val player1Mode = remember { mutableStateOf("Attack") }
    val player2Mode = remember { mutableStateOf("Attack") }
    val (screenWidth, screenHeight) = getScreenSizeDp()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Transparent)) {

        Column(modifier = Modifier.fillMaxSize()) {
            // Upper half of the game screen
            Box(modifier = Modifier
                .weight(1f)
                .background(color = Color.Cyan)
                .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter) {

                // Action Bar for Player 1
                ActionBarAI(
                    onAttack = {
                        player1Mode.value = "Attack"
                    },
                    onFortify = {
                        player1Mode.value = "Fortify"
                    }
                )

                // Game Grid Board for Player 1
                GameGridBoard()

                // Ship Bar with draggable ships for Player 1
                Box(modifier = Modifier
                    .rotate(180f)
                    .align(Alignment.BottomCenter)) {
                    ShipBar(
                        ships = player1Ships
                    )
                }
            }

            // Lower half of the game screen (similar to the upper half but for Player 2)
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = Color.DarkGray, shape = RectangleShape)
            ) {
                // Ship Bar for Player 2
                ShipBar(
                    ships = player2Ships, // Change this dynamically depending on whose turn it is
                    modifier = Modifier.align(Alignment.TopCenter)
                )

                // Game Grid Board for Player 2
                GameGridBoard()
                // Action Bar for Player 2 (similar to Player 1)
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                    contentAlignment = Alignment.Center) {
                    ActionBarP(
                        onAttack = {
                            player2Mode.value = "Attack"
                        },
                        onFortify = {
                            player2Mode.value = "Fortify"
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun GameGridBoard(
    modifier: Modifier = Modifier,
    player: String = "Player",
) {
    val cellSize = 42.dp
    val spacing = 8.dp

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(spacing)) {
            repeat(GRID_SIZE) { row ->
                Row(horizontalArrangement = Arrangement.spacedBy(spacing)) {
                    repeat(GRID_SIZE) { col ->
                        GridCell(
                            row = row,
                            col = col,
                            onCellClick = {clickededRow, clickedCol ->

                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun GridCell(
    row: Int,
    col: Int,
    modifier: Modifier = Modifier,
    onCellClick:(Int,Int) -> Unit
) {
    var cellColor by remember { mutableStateOf(Color(0xFFF5F5F5)) }
    Box(
        modifier = modifier
            .size(42.dp)
            .border(1.dp, cellColor, shape = RoundedCornerShape(4.dp))
            .clickable(enabled = true, onClick = {
                // Toggle cell color based on its current state
                cellColor = if (cellColor == Color(0xFFF5F5F5)) {
                    Color.Transparent
                } else {
                    Color(0xFFF5F5F5)
                }
                // Call the onCellClick to handle the position and place a ship if necessary
                onCellClick(row, col)


            }
            )
            .background(
                color = cellColor,
                shape = RoundedCornerShape(4.dp)
            )

    )
}



@Composable
fun ActionBarAI(onAttack: (Boolean) -> Unit, onFortify: (Boolean) -> Unit) {
    var isAttackMode by remember { mutableStateOf(true) } // true = Attack, false = Fortify
    val activeCount by remember { mutableIntStateOf(3) } //both will get three shots initially

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(
            color = if (isAttackMode) Color(0xFFEF6F6F) else Color(0xFFEEEEEE),
            shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
    ),
        contentAlignment = Alignment.Center

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {


            Box(modifier = Modifier.fillMaxSize()
                .rotate(180f)) {
                //using a switch to change between attack and defence
                GameModeSwitch(
                    isAttackMode = isAttackMode,
                    isFortifyMode = {
                        isAttackMode = it
                        if (it) {
                            onAttack(true)
                        } else {
                            onFortify(true)
                        }
                    },activeCount = activeCount
                )
            }

        }
    }
}
@Composable
fun ActionBarP(onAttack: (Boolean) -> Unit, onFortify: (Boolean) -> Unit) {
    var isAttackMode by remember { mutableStateOf(true) } // true = Attack, false = Fortify
    val activeCount by remember { mutableIntStateOf(3) } //both will get three shots initially
    //the action bar changes colour according to the mode
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(
            color = if (isAttackMode) Color(0xFFEF6F6F) else Color(0xFFEEEEEE),//colour change logic
            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
        ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(modifier = Modifier.fillMaxSize()
                ) {
                GameModeSwitch(
                    isAttackMode = isAttackMode,
                    isFortifyMode = {
                        isAttackMode = it
                        if (it) {
                            onAttack(true)
                        } else {
                            onFortify(true)
                        }
                    },activeCount = activeCount
                )
            }
        }
    }
}

@Composable
fun GameModeSwitch(isAttackMode: Boolean,
                     isFortifyMode: (Boolean) -> Unit,
                   activeCount: Int) {

    Row (verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()) {
        // the no. of chances for each player
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            repeat(3) { index ->
                Box(
                    modifier = Modifier
                        .size(26.dp)
                        .background(
                            color = if(isAttackMode) (if (index < activeCount) Color.White else Color.Black) else (if (index < activeCount) Color.Black else Color.White),
                            shape = CircleShape
                        )
                        .border(border = BorderStroke(width = 1.dp, color = Color(0xFFEAEAEA)), shape = CircleShape)
                )
            }
        }
        // game mode text according to mode
        Text(
            text = if (isAttackMode) "ATTACK" else "DEFEND",
            color = if (isAttackMode) Color.White else Color(0xFF565656),
            fontSize = 38.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        //Actual Switch for opting between two modes
        Switch(
            checked = isAttackMode,
            onCheckedChange = {
                isFortifyMode(it)
            },thumbContent = {
                if(isAttackMode)
                    Image(painter = painterResource(id = drawable.compass),
                    contentDescription = null,
                     modifier = Modifier.size(26.dp),
                    colorFilter = ColorFilter.tint(Color.White),
                    contentScale = ContentScale.Inside)
                else Image(painter = painterResource(id = drawable.defence),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp),
                    colorFilter = ColorFilter.tint(Color(0xFF565656)),
                    contentScale = ContentScale.Inside)

            },  colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFFB33A3A),
                uncheckedThumbColor = Color.White,
                checkedTrackColor = Color(0xFFB33A3A),     // Darker red shade (like action bar)
                uncheckedTrackColor = Color(0xFF565656),   // Gray or another darker neutral
            )
        )
    }
}

enum class ShipType(val size: Int, val iconRes: Int) {
    SMALL(1, R.drawable.small_ship),
    MEDIUM(2, R.drawable.medium_ship),
    LARGE(3, R.drawable.large_ship)
}

data class Ship(
    val id: Int,                 // Unique ID to differentiate even similar ships
    val type: ShipType,
    val isDamaged: Boolean = false,
    var position: Pair<Int, Int>? = null, // optional: row, col on grid
)

val player2Ships = listOf(
    Ship(id = 1, type = ShipType.SMALL),
    Ship(id = 2, type = ShipType.SMALL),
    Ship(id = 3, type = ShipType.MEDIUM),
    Ship(id = 4, type = ShipType.LARGE)
)

val player1Ships = listOf(
    Ship(id = 1, type = ShipType.SMALL),
    Ship(id = 2, type = ShipType.SMALL),
    Ship(id = 3, type = ShipType.MEDIUM),
    Ship(id = 4, type = ShipType.LARGE)
)


@Composable
fun ShipBar(
    ships: List<Ship>,
    modifier: Modifier = Modifier
) {
    val maxShipHeight = ships.maxOf {
        when (it.type) {
            ShipType.SMALL -> 28.dp
            ShipType.MEDIUM -> 48.dp
            ShipType.LARGE -> 66.dp
        }
    }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center  // Centers everything inside
    ) {
        // Bottom line
        Canvas(
            modifier = Modifier
                .height(4.dp)
                .width(228.dp)
                .align(Alignment.TopCenter)
                .padding(top = maxShipHeight - 6.dp)
        ) {
            drawLine(
                color = Color.White,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = 4f
            )
        }

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(bottom = 4.dp)
                .clickable(enabled = true, onClick = {}),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ships.forEach { ship ->
                val shipSize = when (ship.type) {
                    ShipType.SMALL -> 28.dp
                    ShipType.MEDIUM -> 52.dp
                    ShipType.LARGE -> 66.dp
                }

                Box(
                    modifier = Modifier
                        .size(shipSize)
                        .background(Color.Transparent, shape = RoundedCornerShape(6.dp))
                        .padding(bottom = if(ship.type == ShipType.LARGE) 6.dp else 0.dp) ,
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = ship.type.iconRes),
                        contentDescription = ship.type.name,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

fun PlaceShip(row: Int, col: Int, ship: Ship) {
    
}

