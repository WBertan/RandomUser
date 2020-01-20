package com.bertan.randomuser.feature.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bertan.randomuser.R
import com.bertan.randomuser.feature.presentation.view.data.Error
import com.bertan.randomuser.feature.presentation.view.data.UserViewData
import com.bertan.randomuser.feature.presentation.view.data.UserViewEvent
import com.bertan.randomuser.feature.presentation.viewmodel.UserListViewModel
import com.bertan.randomuser.feature.presentation.viewmodel.UserListViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class UserListFragment : Fragment() {

    @Inject
    lateinit var factory: UserListViewModelFactory

    private val viewModel: UserListViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_user_list, container, false)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewData.observe(this, Observer(::onUserData))
        viewModel.events.observe(this, Observer(::onEvent))
        viewModel.start()
    }

    private fun onUserData(viewData: List<UserViewData>?) {
        viewData?.let {
            toast("onUserData: ${it.size}")
        }
    }

    private fun onEvent(event: UserViewEvent) {
        when (event) {
            is Error -> toast("onEvent: ${event.error}")
        }
    }

    private fun toast(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

}