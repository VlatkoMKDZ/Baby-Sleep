package com.example.babysleepsounds.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import com.example.babysleepsounds.domain.model.SleepSound
import com.example.babysleepsounds.ui.theme.*

@Composable
fun BedtimeBackground(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "stars")
    val glow by transition.animateFloat(0.35f, 1f, infiniteRepeatable(tween(2600), RepeatMode.Reverse), label = "glow")
    Canvas(modifier) {
        drawRect(Brush.verticalGradient(listOf(DeepMidnightBlue, MidnightBlue, Color(0xFF2A2456))))
        listOf(Offset(size.width*.12f,size.height*.09f), Offset(size.width*.78f,size.height*.14f), Offset(size.width*.9f,size.height*.36f), Offset(size.width*.18f,size.height*.48f), Offset(size.width*.56f,size.height*.68f)).forEachIndexed { i, p ->
            drawCircle(SoftYellowMoon.copy(alpha = if (i % 2 == 0) glow else .45f), 2.2f + i % 2, p)
        }
        repeat(34) { i ->
            val x = ((i * 73) % 100) / 100f * size.width
            val y = ((i * 41) % 78) / 100f * size.height
            drawCircle(Color.White.copy(alpha = .10f + (i % 4) * .035f * glow), 1.4f + (i % 3), Offset(x, y))
        }
        fun cloud(cx: Float, cy: Float, scale: Float) {
            val c = CloudWhite.copy(alpha = .08f)
            drawOval(c, Offset(cx - 54*scale, cy), Size(108*scale, 34*scale))
            drawCircle(c, 26*scale, Offset(cx - 22*scale, cy))
            drawCircle(c, 34*scale, Offset(cx + 12*scale, cy - 8*scale))
        }
        cloud(size.width*.18f, size.height*.21f, 1.1f)
        cloud(size.width*.83f, size.height*.57f, .9f)
    }
}

@Composable
fun HeroBabyMoon(modifier: Modifier = Modifier) = Canvas(modifier) {
    drawCircle(SoftYellowMoon.copy(alpha = .20f), size.minDimension * .38f, center)
    drawArc(SoftYellowMoon, 90f, 260f, false, topLeft = Offset(size.width*.10f, size.height*.08f), size = Size(size.width*.70f, size.height*.72f), style = Stroke(size.width*.15f, cap = StrokeCap.Round))
    drawCircle(Color(0xFFFFC6A9), size.width*.10f, Offset(size.width*.55f, size.height*.50f))
    drawArc(Color(0xFF7E5F4D), 20f, 140f, false, topLeft = Offset(size.width*.49f, size.height*.40f), size = Size(size.width*.14f, size.height*.10f), style = Stroke(3.dp.toPx(), cap = StrokeCap.Round))
    drawOval(LightPurple, Offset(size.width*.41f, size.height*.55f), Size(size.width*.28f, size.height*.17f))
    drawCircle(Color.White.copy(alpha=.75f), 2.5.dp.toPx(), Offset(size.width*.78f, size.height*.25f))
    drawCircle(SoftYellowMoon, 3.dp.toPx(), Offset(size.width*.25f, size.height*.22f))
}

@Composable
fun SoundIllustration(sound: SleepSound, modifier: Modifier = Modifier.size(62.dp)) = Canvas(modifier) {
    when (sound) {
        SleepSound.Rain -> { drawCircle(RainBlue.copy(.25f), size.width*.45f, center); drawOval(CloudWhite, Offset(size.width*.12f,size.height*.20f), Size(size.width*.72f,size.height*.36f)); repeat(3){ drawLine(RainBlue, Offset(size.width*(.28f+it*.18f),size.height*.66f), Offset(size.width*(.24f+it*.18f),size.height*.84f), 4.dp.toPx(), StrokeCap.Round) } }
        SleepSound.Ocean -> repeat(3){ drawArc(OceanBlue.copy(alpha=.95f-it*.2f), 185f, 150f, false, Offset(size.width*(.05f+it*.05f), size.height*(.35f+it*.14f)), Size(size.width*.75f,size.height*.36f), Stroke(6.dp.toPx(), cap=StrokeCap.Round)) }
        SleepSound.Fan -> { drawCircle(FanBlue.copy(.28f), size.width*.45f, center); repeat(3){ rotate(120f*it, center){ drawOval(FanBlue, Offset(size.width*.44f,size.height*.11f), Size(size.width*.18f,size.height*.40f)) } }; drawCircle(CloudWhite, size.width*.11f, center) }
        SleepSound.WhiteNoise -> repeat(5){ drawRoundRect(LightPurple.copy(.9f), Offset(size.width*(.14f+it*.15f), size.height*(.68f-(it%3)*.16f)), Size(size.width*.08f, size.height*(.20f+(it%3)*.16f)), cornerRadius = androidx.compose.ui.geometry.CornerRadius(12f,12f)) }
        SleepSound.BrownNoise -> { drawOval(BrownNoise.copy(.85f), Offset(size.width*.10f,size.height*.30f), Size(size.width*.78f,size.height*.38f)); drawCircle(BrownNoise.copy(.95f), size.width*.21f, Offset(size.width*.36f,size.height*.32f)); drawCircle(BrownNoise.copy(.75f), size.width*.18f, Offset(size.width*.62f,size.height*.35f)) }
        SleepSound.Heartbeat -> { drawCircle(PinkHeart.copy(.25f), size.width*.44f, center); val p=Path().apply{ moveTo(size.width*.5f,size.height*.78f); cubicTo(size.width*.05f,size.height*.48f,size.width*.23f,size.height*.12f,size.width*.5f,size.height*.31f); cubicTo(size.width*.77f,size.height*.12f,size.width*.95f,size.height*.48f,size.width*.5f,size.height*.78f)}; drawPath(p, PinkHeart); drawLine(Color.White, Offset(size.width*.26f,size.height*.52f), Offset(size.width*.40f,size.height*.52f), 3.dp.toPx(), StrokeCap.Round); drawLine(Color.White, Offset(size.width*.40f,size.height*.52f), Offset(size.width*.46f,size.height*.43f), 3.dp.toPx(), StrokeCap.Round); drawLine(Color.White, Offset(size.width*.46f,size.height*.43f), Offset(size.width*.55f,size.height*.58f), 3.dp.toPx(), StrokeCap.Round); drawLine(Color.White, Offset(size.width*.55f,size.height*.58f), Offset(size.width*.72f,size.height*.58f), 3.dp.toPx(), StrokeCap.Round) }
    }
}

@Composable
fun TimerIllustration(modifier: Modifier = Modifier.size(86.dp)) = Canvas(modifier) {
    drawOval(CloudWhite.copy(.22f), Offset(size.width*.05f,size.height*.62f), Size(size.width*.90f,size.height*.25f))
    drawCircle(SoftYellowMoon, size.width*.26f, Offset(size.width*.50f,size.height*.42f))
    drawCircle(CardMidnight, size.width*.19f, Offset(size.width*.50f,size.height*.42f))
    drawLine(SoftYellowMoon, Offset(size.width*.50f,size.height*.42f), Offset(size.width*.50f,size.height*.30f), 3.dp.toPx(), StrokeCap.Round)
    drawLine(SoftYellowMoon, Offset(size.width*.50f,size.height*.42f), Offset(size.width*.61f,size.height*.47f), 3.dp.toPx(), StrokeCap.Round)
}
