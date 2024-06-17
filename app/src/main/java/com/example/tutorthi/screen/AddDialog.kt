package com.example.onthi.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tutorthi.screen.formatCurrency
import com.example.tutorthi.viewModel.XeHoiViewModel


// add san pham
@Composable
fun AddSanPham(
    viewModel: XeHoiViewModel,
    context: Context,
    onConfirm: () -> Unit) {
    var inputName by remember {
        mutableStateOf("")
    }

    var inputPrice by remember {
        mutableStateOf("")
    }

    var inputDescription by remember {
        mutableStateOf("")
    }

    var inputImage by remember {
        mutableStateOf("")
    }


    var inputStatus by remember { mutableStateOf("San pham cu") } // default selection

    val statusOptions = listOf("San pham cu", "San pham moi")

    AlertDialog(
        onDismissRequest = { onConfirm() },
        dismissButton = {
            Button(
                onClick = {
                    onConfirm()
                }
            ) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val msg = viewModel.addXe(
                        name = inputName,
                        price = inputPrice,
                        model = inputDescription,
                        status = inputStatus,
                    )
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    if (msg.equals("Them thanh cong")){
                        onConfirm()
                    }
                }
            ) {
                Text(text = "Save")
            }
        },
        title = {
            Text(
                text = "Add San pham",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.padding(5.dp)
            )
        },
        text = {
            Column {
                OutlinedTextField(
                    value = inputName,
                    onValueChange = {inputName = it},
                    label = {
                        Text(text = "Name")
                    },
                )
                OutlinedTextField(
                    value = inputPrice,
                    onValueChange = {inputPrice = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = {
                        Text(text = "Price")
                    },
                )
                OutlinedTextField(
                    value = inputDescription,
                    onValueChange = {inputDescription = it},
                    label = {
                        Text(text = "Model")
                    },
                )

//                OutlinedTextField(
//                    value = inputImage,
//                    onValueChange = {inputImage = it},
//                    label = {
//                        Text(text = "Link Image")
//                    },
//                )
                // RadioButton Group for Status
                Text(text = "Status", modifier = Modifier.padding(0.dp,10.dp))
                Column {
                    statusOptions.forEach { status ->
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (status == inputStatus),
                                    onClick = { inputStatus = status },
                                    role = Role.RadioButton
                                )
                        ) {
                            RadioButton(
                                selected = (status == inputStatus),
                                onClick = { inputStatus = status }
                            )
                            Text(
                                text = status,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    )
}