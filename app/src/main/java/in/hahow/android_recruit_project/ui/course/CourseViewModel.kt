package `in`.hahow.android_recruit_project.ui.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import `in`.hahow.android_recruit_project.data.DataLoader
import `in`.hahow.android_recruit_project.model.Course
import kotlinx.coroutines.launch

//pass interface instead of entity, then we can create different DataLoader entity to pass in and
// no need to change CourseViewModel code
class CourseViewModel(private val dataLoader: DataLoader) : ViewModel() {

    private var _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>>
        get() = _courses

    init {
        loadCourses()
    }

    //use view modelScope as a coroutine scope, that make this scope bind with viewModel
    // lifecycle, and avoid memory leak
    private fun loadCourses() = viewModelScope.launch {
        val data = dataLoader.loadCourses()

        //pass result to live data
        _courses.value = data
    }

}