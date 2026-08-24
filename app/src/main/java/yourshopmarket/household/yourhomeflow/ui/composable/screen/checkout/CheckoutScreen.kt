package yourshopmarket.household.yourhomeflow.ui.composable.screen.checkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import yourshopmarket.household.yourhomeflow.data.entity.OrderEntity
import yourshopmarket.household.yourhomeflow.ui.state.DataUiState
import yourshopmarket.household.yourhomeflow.ui.viewmodel.CheckoutViewModel

@Composable
fun CheckoutScreen(modifier: Modifier = Modifier, viewModel: CheckoutViewModel = koinViewModel(), onNavigateToOrdersScreen: () -> Unit) {
    val orderState by viewModel.orderState.collectAsStateWithLifecycle()
    val invalidContact by viewModel.emailInvalidState.collectAsStateWithLifecycle()
    val enabled by remember {
        derivedStateOf {
            viewModel.customerFirstName.isNotBlank() && viewModel.customerLastName.isNotBlank() && viewModel.customerEmail.isNotBlank()
        }
    }

    if (orderState is DataUiState.Populated) {
        val order = (orderState as DataUiState.Populated<OrderEntity>).data
        CheckoutDialog(orderNumber = order.orderNumber, onConfirm = onNavigateToOrdersScreen)
    }

    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text("Reserve your order", style = MaterialTheme.typography.headlineMedium)
        Text("Enter collection details. We will hold your order in store for 24 hours after confirmation.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        OutlinedTextField(
            value = viewModel.customerFirstName,
            onValueChange = viewModel::updateCustomerFirstName,
            label = { Text("Full name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        OutlinedTextField(
            value = viewModel.customerLastName,
            onValueChange = viewModel::updateCustomerLastName,
            label = { Text("Collection address") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        OutlinedTextField(
            value = viewModel.customerEmail,
            onValueChange = viewModel::updateCustomerEmail,
            label = { Text("Email for confirmation") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = invalidContact,
            supportingText = { if (invalidContact) Text("Enter a valid email address") },
            singleLine = true,
        )
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Order summary", style = MaterialTheme.typography.titleMedium)
                Text("Your selected items and final total will be saved to Order History.")
            }
        }
        Button(onClick = viewModel::placeOrder, enabled = enabled, modifier = Modifier.fillMaxWidth()) {
            Text("Place Reservation")
        }
    }
}
