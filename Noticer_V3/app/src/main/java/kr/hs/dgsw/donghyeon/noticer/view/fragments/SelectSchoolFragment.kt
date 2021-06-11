package kr.hs.dgsw.donghyeon.noticer.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.dgsw.donghyeon.noticer.R
import kr.hs.dgsw.donghyeon.noticer.base.BaseFragment
import kr.hs.dgsw.donghyeon.noticer.databinding.FragmentSelectSchoolBinding
import kr.hs.dgsw.donghyeon.noticer.viewmodel.fragments.SelectSchoolViewModel

class SelectSchoolFragment : BaseFragment<FragmentSelectSchoolBinding, SelectSchoolViewModel>() {
    override val layoutRes: Int
        get() = R.layout.fragment_select_school
    override val viewModel: SelectSchoolViewModel
        get() = ViewModelProvider(requireActivity())[SelectSchoolViewModel::class.java]

    override fun onCreatedView(view: FragmentSelectSchoolBinding) {

        val info : SelectSchoolFragmentArgs by navArgs()

        with(viewModel) {
            view.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    getSchools(query!!)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean = false
            })

            actionToInfoInput.observe(requireActivity(), Observer { isCompleted ->
                if(isCompleted) {
                    AlertDialog.Builder(requireActivity())
                        .setTitle("재차 확인")
                        .setMessage("${selectedData.value!!.SCHUL_NM}가 맞나요?")
                        .setPositiveButton("네") { a, b ->
                            findNavController().navigate(
                                SelectSchoolFragmentDirections.actionToInputInfo(info.email, info.password, selectedData.value!!.SCHUL_NM)
                            )
                        }.setNegativeButton("아니오") { a, b -> }
                        .create()
                        .show()
                }
            })
        }
    }

}

