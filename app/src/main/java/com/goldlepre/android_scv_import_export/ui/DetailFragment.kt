package com.goldlepre.android_scv_import_export.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.goldlepre.android_scv_import_export.R
import com.goldlepre.android_scv_import_export.adapters.DetailListAdapter
import com.goldlepre.android_scv_import_export.adapters.MainListAdapter
import com.goldlepre.android_scv_import_export.databinding.FragmentDetailBinding
import com.goldlepre.android_scv_import_export.models.excel.ExcelData
import com.goldlepre.android_scv_import_export.models.excel.ExcelRepository
import java.lang.IllegalArgumentException

class DetailFragment : Fragment() {

    private lateinit var rep: ExcelRepository
    private lateinit var data: ExcelData
    private var _binder: FragmentDetailBinding? = null
    private val binder
        get() = _binder!!
    private  val position: Int
        get() = requireArguments().getInt(ARG_DATA)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binder = FragmentDetailBinding.inflate(inflater, container, false)

        rep = activity?.let { ExcelRepository(it.application) }!!
        val adapter = DetailListAdapter()
        rep.getAllExcelData().observe(viewLifecycleOwner) {
            data = it[position]
            adapter.updateList(data.userList)

            binder.classTitleText.text = data.title
            binder.teacherTitle.text = data.owner
            Toast.makeText(context,"Here ${data.title}",Toast.LENGTH_LONG).show()
        }

        binder.apply {
            listRecyclerview.layoutManager = LinearLayoutManager(requireActivity().application)
            listRecyclerview.setHasFixedSize(true)
            listRecyclerview.adapter = adapter


        }

        return binder.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binder = null
    }

    companion object {
        private  const val ARG_DATA = "ARG"
        @JvmStatic
        fun newInstance(position: Int) =
            DetailFragment().apply {
                arguments = bundleOf(
                    ARG_DATA to position
                )
            }
    }
}