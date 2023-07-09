package `in`.hahow.android_recruit_project.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import `in`.hahow.android_recruit_project.model.Course
import `in`.hahow.android_recruit_project.model.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JsonFileDataLoader(private val context: Context, private val fileName: String): DataLoader {
    override suspend fun loadCourses(): List<Course> = withContext(Dispatchers.IO) {
        // load data from json file
        val json = context.assets.open(fileName).bufferedReader().use { it.readText() }
        val gson = Gson()
        val courseType = object : TypeToken<DataResult>() {}.type
        gson.fromJson<DataResult>(json, courseType).data
    }
}