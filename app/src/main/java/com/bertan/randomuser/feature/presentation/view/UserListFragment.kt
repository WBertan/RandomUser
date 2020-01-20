package com.bertan.randomuser.feature.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bertan.randomuser.R
import com.bertan.randomuser.feature.presentation.view.adapter.UserAdapter
import com.bertan.randomuser.feature.presentation.view.data.Error
import com.bertan.randomuser.feature.presentation.view.data.UserClickedDialog
import com.bertan.randomuser.feature.presentation.view.data.UserViewData
import com.bertan.randomuser.feature.presentation.view.data.UserViewEvent
import com.bertan.randomuser.feature.presentation.viewmodel.UserListViewModel
import com.bertan.randomuser.feature.presentation.viewmodel.UserListViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_user_list.*
import javax.inject.Inject

class UserListFragment : Fragment() {

    @Inject
    lateinit var factory: UserListViewModelFactory

    private val viewModel: UserListViewModel by viewModels { factory }

    private val adapter: UserAdapter by lazy { UserAdapter(viewModel::onItemClick) }

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

        recyclerView.adapter = adapter
        val divider = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(divider)
    }

    private fun onUserData(viewData: List<UserViewData>?) {
        viewData?.let(adapter::setItems)
    }

    private fun onEvent(event: UserViewEvent) {
        when (event) {
            is Error -> toast(message = event.message)
            is UserClickedDialog -> toast(title = event.title, message = event.message)
        }
    }

    private fun toast(title: String? = null, message: String? = null) =
        AlertDialog.Builder(requireContext())
            .setTitle(title ?: getString(R.string.alert_title))
            .setMessage(message ?: getString(R.string.alert_default_message))
            .setPositiveButton(R.string.ok, null)
            .show()

}