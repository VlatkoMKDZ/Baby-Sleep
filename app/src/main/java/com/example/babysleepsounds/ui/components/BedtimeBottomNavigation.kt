package com.example.babysleepsounds.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.GraphicEq
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material.icons.rounded.Nightlight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.babysleepsounds.ui.theme.*

data class BedtimeNavItem(val label: String, val icon: ImageVector)

@Composable
fun BedtimeBottomNavigation(selectedIndex: Int, onSelected: (Int) -> Unit, modifier: Modifier = Modifier) {
    val items = listOf(BedtimeNavItem("Mixer", Icons.Rounded.GraphicEq), BedtimeNavItem("Favorites", Icons.Rounded.Favorite), BedtimeNavItem("Timer", Icons.Rounded.Nightlight), BedtimeNavItem("More", Icons.Rounded.MoreHoriz))
    NavigationBar(
        modifier = modifier.clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)).background(Brush.horizontalGradient(listOf(CardMidnight, MidnightBlue))).navigationBarsPadding().height(78.dp),
        containerColor = CardMidnight.copy(alpha = .94f),
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onSelected(index) },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                colors = NavigationBarItemDefaults.colors(selectedIconColor = TextWhite, selectedTextColor = TextWhite, indicatorColor = SoftLavender.copy(.36f), unselectedIconColor = TextLightGray, unselectedTextColor = TextLightGray)
            )
        }
    }
}
