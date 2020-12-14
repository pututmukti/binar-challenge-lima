package com.binardev.fragmentchellange.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.binardev.fragmentchellange.R
import kotlinx.android.synthetic.main.fragment_activity.*

private const val ARG_PARAM1 = "param1"


class SlideFragment : Fragment() {
    private var param1: String? = null
    private lateinit var listener: (CharSequence) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (param1) {
            "3" -> {
                textFragment.text = resources.getString(R.string.human)
                EtFragment.visibility = View.VISIBLE
                imageFragment.setBackgroundResource(R.drawable.ic_people)
                EtFragment.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                    override fun onTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {
                        listener(s)
                    }

                    override fun afterTextChanged(s: Editable?) {}
                })
            }
            "2" -> {
                textFragment.text = resources.getString(R.string.player_versus_computer)
                EtFragment.visibility = View.GONE
                imageFragment.setBackgroundResource(R.drawable.ic_player_computer)
            }
            else -> {
                textFragment.text = resources.getString(R.string.player_versus_player)
                EtFragment.visibility = View.GONE
                imageFragment.setBackgroundResource(R.drawable.ic_player_player)
            }
        }
        Log.i("SlideFragment", "slide page bergeser")
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, listener: (CharSequence) -> Unit) =
            SlideFragment().apply {
                this.listener = listener
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}