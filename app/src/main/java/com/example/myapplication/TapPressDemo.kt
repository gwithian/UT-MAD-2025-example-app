package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun TapPressDemo() {

    var circleSize by remember { mutableStateOf(100.dp) }
    val events = remember { mutableStateListOf("Logging touch events...") }
    val positions = remember { mutableStateListOf(Offset.Zero) }

    val reset = remember {
        {
            circleSize = 100.dp
            // Clear and reset the lists to their initial state to avoid a crash.
            events.clear()
            positions.clear()
            events.add("Logging touch events...")
            positions.add(Offset.Zero)
        }
    }

    Column {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)) {
            itemsIndexed(events) { index, event ->
                Text(
                    text = event,
                    fontWeight = if (index == 0) FontWeight.Bold else FontWeight.Normal,
                    fontSize = if (index == 0) 18.sp else 14.sp
                )
            }
        }
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(300.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(circleSize)
//                    .offset { IntOffset(positions[0].x.roundToInt(), 0) }
//                    .offset(x = -circleSize / 2)
//                    .background(Color.Red, shape = CircleShape)
//                    .clickable {
//                        reset()
//                    }
//            )
//        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.LightGray)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { offset ->
                            events.add(0, "onPress at $offset")
                            tryAwaitRelease()
                            events.add(0, "onRelease at $offset")
                        },
                        onTap = { offset ->
                            events.add(0, "onTap at $offset")
                            positions.add(0, offset)
                        },
                        onLongPress = { offset ->
                            events.add(0, "onLongPress at $offset")
                            positions.add(0, offset)
                            circleSize += 40.dp
                        }
                    )
                }
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Tap, press, or long press here")
        }


    }
}