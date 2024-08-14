package com.example.myapplication.viewmodels

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.example.myapplication.R
import com.example.myapplication.framework.database.entity.ActivityLevelsEnitity
import com.example.myapplication.framework.database.entity.asActivityLevelsEnitity
import com.example.myapplication.framework.database.room.AppDatabase
import com.example.myapplication.viewmodels.response.ActivityLevels
import com.example.myapplication.viewmodels.state.ActivitiesState
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class ActivitiesViewModel : ViewModel() {

    lateinit var db: AppDatabase

    private val _activityLevelsState: MutableStateFlow<ActivitiesState> =
        MutableStateFlow(ActivitiesState.Default)
    val activityLevelsState: StateFlow<ActivitiesState> get() = _activityLevelsState

    private val _activityLevelsLocal: MutableStateFlow<ActivitiesState> =
        MutableStateFlow(ActivitiesState.Default)
    val activityLevelsLocal: StateFlow<ActivitiesState> get() = _activityLevelsLocal

    private val _openedDocument: MutableStateFlow<List<String>> =
        MutableStateFlow(emptyList())
    val openedDocument: StateFlow<List<String>> get() = _openedDocument

    private val _bitmap: MutableStateFlow<HashMap<String, Bitmap>> =
        MutableStateFlow(HashMap())
    val bitmap: StateFlow<HashMap<String, Bitmap>> get() = _bitmap

    fun getActivityLevels(context: Context)  {
        _activityLevelsState.value = ActivitiesState.Loading
        viewModelScope.launch {
            val json = loadJSONFromAsset(context)
            val response = Gson().fromJson(json, ActivityLevels::class.java)
            insertActivityLevels(response)
            _activityLevelsState.value = ActivitiesState.Success(data = response)
        }
    }

    fun getActivityLevelsLocal()  {
        _activityLevelsLocal.value = ActivitiesState.Loading
        viewModelScope.launch {
            val data = getActivityLevels()?.asActivityLevelsEnitity()
            data?.let {
                _activityLevelsLocal.value = ActivitiesState.Success(data = data)
            }
        }
    }

    private suspend fun getActivityLevels() : ActivityLevelsEnitity? {
        var test: ActivityLevelsEnitity? = null
        db.withTransaction {
             test = db.activityLevelsDao.getLocalActivityLevelsEnitity(1)
        }
        return test
    }

    private suspend fun insertActivityLevels(response: ActivityLevels) {
        db.withTransaction {
            db.activityLevelsDao.insert(response.asActivityLevelsEnitity(1))
        }
    }

    fun setOpenedDocument(string: String, bitmap: Bitmap) {
        if (!_bitmap.value.containsKey(string)) {
            _bitmap.value[string] = bitmap
        }
    }

    fun setOpenedDocument(document: String) {
        if (!_openedDocument.value.contains(document))
            _openedDocument.value = _openedDocument.value.plus(document)
    }

    private fun loadJSONFromAsset(context: Context): String? {
        val json: String? = try {
            val activityLevels = context.resources.openRawResource(R.raw.activitylevels)

            val size = activityLevels.available()

            val buffer = ByteArray(size)

            activityLevels.read(buffer)

            activityLevels.close()

            String(buffer, charset("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
        return json
    }
}