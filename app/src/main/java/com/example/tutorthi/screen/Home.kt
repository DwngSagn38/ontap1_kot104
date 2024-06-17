package com.example.tutorthi.screen

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onthi.screen.AddSanPham
import com.example.onthi.screen.DeleteDialog
import com.example.onthi.screen.DetailSanPham
import com.example.onthi.screen.UpdateSanPham
import com.example.tutorthi.room.XeHoiModel
import com.example.tutorthi.viewModel.XeHoiViewModel
import com.ibm.icu.text.Transliterator
import java.text.NumberFormat
import java.util.Locale

@Composable
fun Home(navController: NavController, viewModel: XeHoiViewModel){
    val context = LocalContext.current
    val listSP by viewModel.xeHois.collectAsState(initial = emptyList())

    var isShowDialogAdd by remember {
        mutableStateOf(false)
    }
    Scaffold (
        floatingActionButton ={
            FloatingActionButton(
                onClick = {
                    isShowDialogAdd = true
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background,
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    ){
        GetList(listSP,viewModel,context, it )
        if (isShowDialogAdd){
            AddSanPham( viewModel = viewModel, context = context ) {
                isShowDialogAdd = false
            }
        }
    }
}

fun getList(): List<XeHoiModel>{
    var list = mutableListOf<XeHoiModel>()
    list.add(XeHoiModel(0,"Xe 1",3249234f,"Honda",true))
    list.add(XeHoiModel(1,"Xe 2",53454656f,"Honda",false))
    list.add(XeHoiModel(2,"Xe 3",35435543f,"Yamaha",true))
    list.add(XeHoiModel(3,"Xe 4",5345234f,"Toyota",false))
    list.add(XeHoiModel(4,"Xe 5",7654656f,"Honda",true))
    return list.toMutableList()
}

// hien thi danh sach
@Composable
fun GetList(
    list: List<XeHoiModel>,
    viewModel: XeHoiViewModel,
    context: Context,
    paddingValues: PaddingValues
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Danh sach san pham", fontSize = 30.sp, color = Color.Green)

        if(list.size == 0){
            Text(text = "No data", fontSize = 20.sp, fontStyle = FontStyle.Italic)
        }
        else LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(list){sp ->
                ItemSanPham(sp = sp, viewModel,context)
            }
        }
    }
}

// Item san pham
@Composable
fun ItemSanPham(sp: XeHoiModel,viewModel: XeHoiViewModel, context: Context){

    var isShowDilogDelete by remember {
        mutableStateOf(false)
    }

    var isShowDilogDetail by remember {
        mutableStateOf(false)
    }

    var isShowDilogUpdate by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                isShowDilogDetail = true
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
//
//            AsyncImage(
//                model = sp.image,
//                contentDescription = "",
//                contentScale = ContentScale.FillWidth,
//                modifier = Modifier
//                    .size(70.dp)
//                    .clip(RoundedCornerShape(10.dp)))

            Column(
                modifier = Modifier.padding(14.dp)
            ) {

                Text(
                    text = "Name: " + sp.ph42693_name,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(0.dp,5.dp),

                    )
                Text(
                    text = "Price: " + formatCurrency(sp.ph42693_price.toString()),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(0.dp,5.dp),

                    )
                Text(
                    text = "Model: " + sp.model_ph42693,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(0.dp,5.dp),
                )
                Text(
                    text = "Status: " + if (sp.status_ph42693!!) "San pham moi" else "San pham cu",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(0.dp,5.dp),
                )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.width(33.dp)
            ){
                Icon(imageVector = Icons.Default.Edit, contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            isShowDilogUpdate = true
                        }
                )
                Spacer(modifier = Modifier.height(20.dp))
                Icon(imageVector = Icons.Default.Delete, contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            isShowDilogDelete = true
                        }
                )
            }

        }
    }

    if (isShowDilogDelete){
        DeleteDialog(viewModel, sp , context ) {
            isShowDilogDelete = false
        }
    }

    if (isShowDilogDetail){
        DetailSanPham(sp = sp) {
            isShowDilogDetail = false
        }
    }

    if (isShowDilogUpdate){
        UpdateSanPham(viewModel, context, sp) {
            isShowDilogUpdate = false
        }
    }
}

// ham xoa dau
fun String.removeDiacritics(): String {
    val transliterator = Transliterator.getInstance("NFD; [:Nonspacing Mark:] Remove; NFC")
    return transliterator.transliterate(this)
}

// ham format price
fun formatCurrency(value: String): String {
    return if (value.isNotEmpty()) {
        try {
            val number = value.toDouble()
            val format = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
            format.format(number)
        } catch (e: NumberFormatException) {
            ""
        }
    } else {
        ""
    }
}
