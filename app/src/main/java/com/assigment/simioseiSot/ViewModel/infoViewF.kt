package com.assigment.simioseiSot.ViewModel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.assigment.simioseiSot.R
import com.assigment.simioseiSot.databinding.ActivityInfoViewFBinding

class infoViewF : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityInfoViewFBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoViewFBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonFragment1x.setOnClickListener {

            goToFragment(Fragment1x())


        }
        binding.buttonFragment2x.setOnClickListener {

            goToFragment(Fragment2x())



        }


    }
    private fun goToFragment(fragment: Fragment){
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()


    }


}

