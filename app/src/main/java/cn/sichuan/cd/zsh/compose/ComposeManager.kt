package cn.sichuan.cd.zsh.compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.core.app.ComponentActivity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.activity.compose.setContent
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

/**
 * https://www.bilibili.com/video/BV1ob4y1a7ad/?spm_id_from=333.337.search-card.all.click&vd_source=83fdbd83d34aa2af54f70627e76de772
 */


@SuppressLint("RestrictedApi")
class ComposeManager :ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            MyApplicationTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }

//        setContent {
//            Messaage("my name:")
//        }

    }
}
@Composable
fun Messaage(name:String){
    Text(text="hello "+name)
}
@Preview
@Composable
fun PreviewMessage(){
    Messaage(name = "android")
}


@Composable
fun Greeting(name: String) {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Hello $name!", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Handle the click event */ }) {
            Text("Click Me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}


