package com.example.composeprac

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeprac.ui.theme.ComposepracTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposepracTheme {
                val clickedCount = remember { mutableStateOf(0)}
                val messageList = remember { mutableStateListOf<Message>()}




                    Column {

                        Greeting(
                            name = "Android"

                        )


                        MessageList(
                            messageList,
                            onDelete = {
                            Log.d("TAG", "onCreate:${it.id} ")
                                messageList.remove(it)
                        })

                    }

            }
        }
    }
}

@Composable
fun MessageList(messages: List<Message>, onDelete: (Message) -> Unit) {
    LazyColumn {
        items(messages) { message ->
            MessageRow(message, onDelete)
        }
    }
}

@Composable
fun MessageRow(msg: Message, onDelete: (Message) -> Unit ,modifier: Modifier = Modifier ) {
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color = Color.Red),
        shadowElevation = 10.dp,

    ) {
        Column( modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()) {
            Text(
                text = "id ${msg.id} , / msg: ${msg.msg}",
                modifier = modifier
            )
            Button({ onDelete(msg) }) {
                Text("삭제버튼")
            }
        }
    }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String) {
    var presses by remember { mutableIntStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Topappbar")
                },
                colors = topAppBarColors(
                    containerColor = Color(0xffF0A8D0),
                    titleContentColor = Color.Blue,
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xffF0A8D0),
                contentColor = Color.Blue,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app Bar"
                )

            }
        },


        floatingActionButton = {
            FloatingActionButton(onClick = {presses++}) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "gadfaeadsasdfdfqtewtasdtassdaasdtasdtsadtd"

            )

        }
        Text(text = "asdfsdaasdfhi $name")
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposepracTheme {
        Greeting("Android")
    }
}