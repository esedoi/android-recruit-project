package `in`.hahow.android_recruit_project.ui.course

import `in`.hahow.android_recruit_project.databinding.ItemCourseBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import `in`.hahow.android_recruit_project.model.Course


class CourseAdapter :
    ListAdapter<Course, RecyclerView.ViewHolder>(
        CourseCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CourseHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        if (holder is CourseHolder) {
            holder.bind(item)
        }
    }

    class CourseHolder(private var binding: ItemCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Course) {


            binding.courseTitleTextView.text = item.title


            val isSoldOut = item.numSoldTickets > item.successCriteria.numSoldTickets

            val courseTicketsText = if (isSoldOut) "100%" else "${item.numSoldTickets}/${
                item.successCriteria
                    .numSoldTickets
            } "
            val soldOutPercent: Float =
                if (isSoldOut) 100F else (item.numSoldTickets.toFloat() / item
                    .successCriteria.numSoldTickets.toFloat()) * 100


            binding.courseProgressBar.progress = soldOutPercent.toInt()


            binding.courseTicketsTextView.text = courseTicketsText

            binding.courseStatusTextView.text = item.status


            Glide.with(itemView)
                .load(item.coverImageUrl)
                .into(binding.courseImageView)

        }

        companion object {
            fun from(parent: ViewGroup): CourseHolder {
                val order =
                    ItemCourseBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return CourseHolder(order)
            }
        }
    }
}

class CourseCallback : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem == newItem
    }
}