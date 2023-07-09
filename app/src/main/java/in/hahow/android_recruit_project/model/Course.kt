package hahow.android_recruit_project.model

data class Course(
    val successCriteria: SuccessCriteria,
    val numSoldTickets: Int,
    val status: String,
    val proposalDueTime: String,
    val coverImageUrl: String,
    val title: String
)

data class SuccessCriteria(
    val numSoldTickets: Int
)

interface CourseLoader {
    suspend fun loadCourses(): List<Course>
}
