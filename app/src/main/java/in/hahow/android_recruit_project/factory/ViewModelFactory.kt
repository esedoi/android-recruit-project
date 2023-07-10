package `in`.hahow.android_recruit_project.factory

import `in`.hahow.android_recruit_project.ui.course.CourseViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import `in`.hahow.android_recruit_project.data.DataLoader

//use Factory to build viewModel , this can let us pass arguments to viewModel
class ViewModelFactory(private val dataLoader: DataLoader) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseViewModel::class.java)) {
            return CourseViewModel(dataLoader) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}