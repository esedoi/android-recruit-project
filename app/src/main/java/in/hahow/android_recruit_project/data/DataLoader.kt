package in.hahow.android_recruit_project.data

import hahow.android_recruit_project.model.Course

interface DataLoader {
    suspend fun loadCourses(): List<Course>
}
