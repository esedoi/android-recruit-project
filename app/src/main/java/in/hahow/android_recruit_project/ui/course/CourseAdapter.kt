package `in`.hahow.android_recruit_project.ui.course

import `in`.hahow.android_recruit_project.R
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

            setTitle(item)

            val isSoldOut = item.numSoldTickets > item.successCriteria.numSoldTickets

            setNumSoldTicketText(item, isSoldOut)

            setProgressBar(item, isSoldOut)

            setStatusText(item)

            setCoverImg(item)

        }

        private fun setNumSoldTicketText(item: Course, isSoldOut: Boolean) {
            val courseTicketsText = if (isSoldOut) "100%" else "${item.numSoldTickets}/${
                item.successCriteria
                    .numSoldTickets
            } "

            binding.tvCourseTicketNum.text = courseTicketsText
        }

        private fun setProgressBar(item: Course, isSoldOut: Boolean) {
            val soldOutPercent: Float =
                if (isSoldOut) 100F else (item.numSoldTickets.toFloat() / item
                    .successCriteria.numSoldTickets.toFloat()) * 100
            binding.pbCourse.progress = soldOutPercent.toInt()
        }

        private fun setTitle(item: Course) {
            binding.tvCourseTitle.text = item.title
        }

        private fun setCoverImg(item: Course) {
            Glide.with(itemView)
                .load(item.coverImageUrl)
                .into(binding.ivCourse)

        }

        private fun setStatusText(item: Course) {

            val statusType: StatusType = when (item.status) {
                StatusType.SUCCESS.value -> {
                    StatusType.SUCCESS
                }
                StatusType.PUBLISHED.name -> {
                    StatusType.PUBLISHED
                }
                StatusType.INCUBATING.name -> {
                    StatusType.INCUBATING
                }
                else -> {
                    StatusType.INCUBATING
                }
            }

            binding.tvCourseStatus.text = statusType.text

            binding.tvCourseStatus.setBackgroundResource(statusType.backGround)
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

enum class StatusType(val value: String, val text: String, val backGround: Int) {
    SUCCESS("SUCCESS", "已達標", R.drawable.bg_status_success),
    PUBLISHED("PUBLISHED", "已開課", R.drawable.bg_status_published),
    INCUBATING("INCUBATING", "募資中", R.drawable.bg_status_incubating)
}