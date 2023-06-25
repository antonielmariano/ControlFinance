package com.example.controlfinance

import android.icu.text.NumberFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.controlfinance.ui.theme.ControlFinanceTheme
import java.util.Date
import java.util.Locale
import java.util.Objects

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ControlFinanceTheme {
                Column() {
                    Welcome("Antoniel Mariano")
                    ListTranstactions()
                }
            }
        }
    }
}
@Composable
fun Welcome(userName: String){
    val userName = rememberSaveable{ mutableStateOf(userName) }
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Bem vindo de volta, \n${userName.value}",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Clear Transactions",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

fun TextStyle(value: String, color: Color.Companion): Color {
    if(value.contains("-")){
        return color.Red
    }
    return return color.Green
}

@Composable
fun ListTranstactions(){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        items(transactions.size) { index ->
            Transaction(transactions[index])

        }
    }
}

@Composable
fun Transaction (transaction: Transaction){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {

            Icon(
                imageVector = transaction.icon,
                contentDescription = "Type Transaction"
            )

            Text(
                text = transaction.description,
                modifier = Modifier.weight(2f).padding(horizontal = 12.dp),
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = NumberFormat.getInstance(Locale.GERMAN).format(transaction.value).toString(),
                style = TextStyle(
                    color = TextStyle(transaction.value.toString(), Color)
                ))
        }
    }
}

data class Transaction(val description: String, val value:Double, val icon:ImageVector)
private val transactions = listOf(
    Transaction(description = "Mercado", value = -1000.0, icon = Icons.Filled.ShoppingCart),
    Transaction(description = "Aluguel", value = -1800.0, icon = Icons.Filled.Home),
    Transaction(description = "Celular", value = -90.0, icon = Icons.Filled.Phone),
    Transaction(description = "Moto", value = -570.0, icon = Icons.Filled.Place),
    Transaction(description = "Others", value = -1050.0, icon = Icons.Filled.Info),
    Transaction(description = "Salário", value = 4095.0, icon = Icons.Filled.Star)
)


