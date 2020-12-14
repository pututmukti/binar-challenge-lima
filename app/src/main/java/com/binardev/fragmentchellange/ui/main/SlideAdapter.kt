package com.binardev.fragmentchellange.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SlideAdapter(Ad: FragmentActivity, listener: (CharSequence) -> Unit) : FragmentStateAdapter(Ad) {
    private val dataFragment = mutableListOf(
        SlideFragment.newInstance("1", listener),
        SlideFragment.newInstance("2",listener),
        SlideFragment.newInstance("3", listener)

    )

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return dataFragment[position]
    }
}