package `in`.hahow.android_recruit_project.ui.course

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.hahow.android_recruit_project.databinding.FragmentCourseBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import `in`.hahow.android_recruit_project.data.JsonFileDataLoader
import `in`.hahow.android_recruit_project.factory.ViewModelFactory
import android.annotation.SuppressLint

class CourseFragment : Fragment() {

    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding!!

    private lateinit var courseAdapter: CourseAdapter
    private var layoutManager: RecyclerView.LayoutManager? = null

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

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataLoader = JsonFileDataLoader(requireContext(), "data.json")
        viewModel =
            ViewModelProvider(this, ViewModelFactory(dataLoader)).get(CourseViewModel::class.java)

        setupRecyclerView()

        viewModel.courses.observe(viewLifecycleOwner) { courses ->

            courseAdapter.submitList(courses)
            courseAdapter.notifyDataSetChanged()
        }

    }

    private fun setupRecyclerView() {
        courseAdapter = CourseAdapter()
        layoutManager = LinearLayoutManager(this.context)
        binding.rvCourses.layoutManager = layoutManager
        binding.rvCourses.adapter = courseAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}