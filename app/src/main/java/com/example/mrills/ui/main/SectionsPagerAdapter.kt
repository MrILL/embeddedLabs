package com.example.mrills.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mrills.FermaFraction
import com.example.mrills.GeneticAlgorithm
import com.example.mrills.Perceptron
import com.example.mrills.R

private val TAB_TITLES = arrayOf(
    "Ferma's Factor",
    "Perceptron",
    "Genetic Algorithm"
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FermaFraction.newInstance()
            1 -> Perceptron.newInstance()
            2 -> GeneticAlgorithm.newInstance()
            else -> PlaceholderFragment.newInstance(position + 1)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        return 3
    }
}