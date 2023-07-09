package `in`.hahow.android_recruit_project.ui.course

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.hahow.android_recruit_project.databinding.FragmentCourseBinding
import hahow.android_recruit_project.data.JsonFileDataLoader
import hahow.android_recruit_project.factory.ViewModelFactory

class CourseFragment : Fragment() {

    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = CourseFragment()
    }

    private lateinit var viewModel: CourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CourseViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataLoader = JsonFileDataLoader(requireContext(), "courses.json")
        viewModel = ViewModelProvider(this, ViewModelFactory(dataLoader)).get(CourseViewModel::class.java)

        viewModel.courses.observe(viewLifecycleOwner) { courses ->

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}