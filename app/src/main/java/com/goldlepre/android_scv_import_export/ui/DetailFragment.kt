package com.goldlepre.android_scv_import_export.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.goldlepre.android_scv_import_export.R

class DetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)


        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DetailFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}