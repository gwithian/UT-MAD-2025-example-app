package com.example.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    isFavourite: Boolean
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.custom_button_container_color),
            contentColor = if (isFavourite) colorResource(id = R.color.custom_button_content_color_favourite) else colorResource(id = R.color.custom_button_content_color)
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.star),
            contentDescription = "Star",
            modifier = Modifier.size(20.dp), // Scale down the star size
        )
        Spacer(modifier = Modifier.width(8.dp)) // Add some space between icon and text
        Text(text)
    }
}

@Preview
@Composable
fun CustomButtonDemo() {

    val isFavourite = remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CustomButton(
            text = if (isFavourite.value) "Favourite" else "Save as Favourite",
            onClick = { isFavourite.value = !isFavourite.value },
            isFavourite = isFavourite.value
        )
    }
}
