package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.DataSource
import com.example.myapplication.model.Hero
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HeroApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroApp() {
    Scaffold(
        topBar = {
            HeroTopAppBar()
        }
    ) {it ->
        LazyColumn {
            items(DataSource.heroes) {
                HeroItem(
                    hero = it,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroTopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier
                        .size(64.dp)
                        .padding(8.dp),
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun HeroItem(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(hero.name),
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = stringResource(hero.desc),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                HeroButton(
                    expanded = expanded,
                    onClick = {}
                )
                Image(
                    modifier = Modifier
                        .size(72.dp)
                        .padding(8.dp)
                        .clip(MaterialTheme.shapes.small),
                    painter = painterResource(hero.imageResourceId),
                    contentDescription = null

                )
            }
            HeroStats(hero = hero,
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 8.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
            )
        }
    }
}

@Composable
private fun HeroButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun HeroStats(hero: Hero,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier){
        Text(
            text = stringResource(hero.vuln)
        )
        Text(
            text = stringResource(hero.vulndesc)
        )
    }
}

@Preview
@Composable
fun HeroPreview() {
    MyApplicationTheme {
        HeroApp()
    }
}

@Preview
@Composable
fun HeroDarkPreview() {
    MyApplicationTheme(darkTheme = true){
        HeroApp()
    }
}