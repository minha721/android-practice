package com.example.bottom_sheet

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bottom_sheet.databinding.FragmentListBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.Exception

class ListBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var mainActivity: MainActivity
    lateinit var listBottomSheetClickListener: ListBottomSheetClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
        try {
            listBottomSheetClickListener = context as ListBottomSheetClickListener
        } catch (e: Exception) {
            Log.e("ModalListBottomSheetDialog", "onAttach Error")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentListBottomSheetBinding.inflate(inflater, container, false)

        binding.layoutChange.setOnClickListener {
            listBottomSheetClickListener.onButtonClicked("change")
            dismiss()
        }

        binding.layoutDelete.setOnClickListener {
            listBottomSheetClickListener.onButtonClicked("delete")
            dismiss()
        }

        return binding.root
    }

    companion object {
        const val TAG = "ListBottomSheetFragment"
        fun newInstance(): ListBottomSheetFragment{
            return ListBottomSheetFragment()
        }
    }
}