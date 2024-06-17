package com.example.tutorthi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tutorthi.repository.XeHoiRepository
import com.example.tutorthi.room.XeHoiModel
import kotlinx.coroutines.launch

class XeHoiViewModel(val repository: XeHoiRepository) : ViewModel() {

    fun addXe( name : String, price : String, model : String, status : String) : String {
        if (name.isEmpty() || price.isEmpty() || model.isEmpty() || status.isEmpty()){
            return "Khong duoc de trong du lieu"
        }
        if (!isFloat(price)){
            return "Gia san pham chua dung"
        }
        val statusBoolean = if(status.equals("San pham moi")) true else false
        val xeNew = XeHoiModel(0,name,price.toFloat(),model,statusBoolean)
        viewModelScope.launch {
            repository.AddXeToRoom(xeNew)
        }
        return "Them thanh cong"
    }


    val xeHois = repository.getAll()


    fun deleteXe(xe: XeHoiModel) : String {
        viewModelScope.launch {
            repository.DeleteXeFromRoom(xe)
        }
        return "Xoa thanh cong"
    }


    fun updateXe(uid : Int, name : String, price : String, model : String, status : String) : String {
        if (name.isEmpty() || price.isEmpty() || model.isEmpty() || status.isEmpty()){
            return "Khong duoc de trong du lieu"
        }
        if (!isFloat(price)){
            return "Gia san pham chua dung"
        }
        val statusBoolean = if(status.equals("San pham moi")) true else false
        val xeNew = XeHoiModel(uid,name,price.toFloat(),model,statusBoolean)
        viewModelScope.launch {
            repository.UpdateXeFromRoom(xeNew)
        }
        return "Update thanh cong"
    }

}

fun isFloat(value: String): Boolean {
    return try {
        value.toFloat()
        true
    } catch (e: NumberFormatException) {
        false
    }
}