package com.raz.weatherapp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(getContent(), container, false)
        initViews(view)
        initListeners()
        initTexts()
        return view
    }

    abstract fun initViews(view: View)

    abstract fun initListeners()

    abstract fun initTexts()

    abstract fun getContent(): Int
}