package `in`.hahow.android_recruit_project.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import `in`.hahow.android_recruit_project.model.Course
import `in`.hahow.android_recruit_project.model.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JsonFileDataLoader(private val context: Context, private val fileName: String) : DataLoader {

    private val gson = Gson()

    override suspend fun loadCourses(): List<Course> = withContext(Dispatchers.IO) {
        try {
            // load data from json file
            val json = loadJsonFromFile()
            val courseType = object : TypeToken<DataResult>() {}.type
            gson.fromJson<DataResult>(json, courseType).data
        } catch (e: Exception) {
            // handle or log the error
            emptyList()
        }
    }

    private fun loadJsonFromFile(): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}