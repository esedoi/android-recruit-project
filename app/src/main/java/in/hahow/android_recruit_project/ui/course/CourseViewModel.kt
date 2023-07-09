package `in`.hahow.android_recruit_project.ui.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import `in`.hahow.android_recruit_project.data.DataLoader
import `in`.hahow.android_recruit_project.model.Course
import kotlinx.coroutines.launch

class CourseViewModel(private val dataLoader: DataLoader) : ViewModel() {

    private var _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>>
        get() = _courses

    init {
        loadCourses()
    }

    private fun loadCourses() = viewModelScope.launch {
        val data = dataLoader.loadCourses()
        _courses.value = data
    }

}