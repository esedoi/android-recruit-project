package `in`.hahow.android_recruit_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import `in`.hahow.android_recruit_project.ui.course.CourseFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CourseFragment.newInstance())
                .commitNow()
        }
    }
}