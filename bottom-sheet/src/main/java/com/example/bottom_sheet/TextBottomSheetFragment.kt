package com.example.bottom_sheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bottom_sheet.databinding.FragmentTextBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TextBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTextBottomSheetBinding.inflate(inflater, container, false)

        binding.btnSubmit.setOnClickListener {
            val name = binding.etName.text.toString()
            Toast.makeText(mainActivity, "My name is ${name}.", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        return binding.root
    }

    companion object {
        const val TAG = "TextBottomSheetFragment"
        fun newInstance(): TextBottomSheetFragment{
            return TextBottomSheetFragment()
        }
    }
}