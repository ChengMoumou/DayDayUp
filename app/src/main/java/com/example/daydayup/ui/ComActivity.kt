package com.example.daydayup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import com.example.daydayup.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/*使用jetpack compose*/
class ComActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            preViewChange()
        }
    }
}

data class Message(val author:String, val content:String)
@Composable
fun textChange(text:Message){
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(painter = painterResource(R.mipmap.cat),
            contentDescription = "image content description",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape))
        Spacer(modifier = Modifier.width(8.dp))
        var isExpand by remember{ mutableStateOf(false) }
        val sufaceColor by animateColorAsState(if (isExpand) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,)
        Column (modifier = Modifier.clickable{isExpand = !isExpand}){
            Text(text = text.author, color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.width(8.dp))
            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 6.dp,color = sufaceColor, modifier = Modifier.animateContentSize().padding(1.dp)) {
                Text(
                    text = text.content,modifier = Modifier.padding(all = 4.dp),
                    maxLines = if(isExpand) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>){
    LazyColumn {
        items(messages) {
                message -> textChange(message)
        }
    }
}

@Preview
@Composable
fun preViewChange(){
    Conversation(SampleData.conversationSample)
//        textChange(text = Message("Author","content"))
}
