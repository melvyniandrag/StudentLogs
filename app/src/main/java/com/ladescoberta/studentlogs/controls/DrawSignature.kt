package com.ladescoberta.studentlogs.controls

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.joelkanyi.sain.Sain
import io.github.joelkanyi.sain.SignatureState

@Composable
fun DrawSignature() {
    var imageBitmap: ImageBitmap? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            color = Color(0xFFF1F0EF),
            shape = RoundedCornerShape(4.dp)
        ) {
            Column {
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp, top = 8.dp),
                        text = "Signature",
                        fontSize = 12.sp,
                        color = Color(0xFF777777)
                    )

                    val state = remember {
                        SignatureState()
                    }

                    Sain(
                        state = state,
                        onComplete = {},
                        modifier=Modifier.fillMaxSize(),
                        ) {


                        Spacer(modifier = Modifier.height(4.dp))

                        ButtonClearSignature(
                            modifier = Modifier
                                .padding(end = 8.dp, bottom = 8.dp)
                                .align(Alignment.End),
                            onClear = {
                                imageBitmap = null
                            }
                        )
                    }
            }
        }



    }
}

@Composable
fun ButtonClearSignature(modifier: Modifier, onClear: () -> Unit) {

}
