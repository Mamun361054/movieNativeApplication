package com.example.myapplication.core.platform

import android.os.Bundle
import android.view.View
import com.example.myapplication.R.color
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.core.extension.appContext
import com.example.myapplication.core.extension.viewContainer
import com.google.android.material.snackbar.Snackbar

open class BaseFragment : Fragment() {

    open fun onBackPressed(){}

    internal fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

    internal  fun showProgress() = progressStatus(View.VISIBLE)

    internal fun hideProgress() = progressStatus(View.GONE)

    private  fun progressStatus(viewStatus: Int) = with(activity){ if(this is BaseActivity) this.progressBar().visibility = viewStatus}

    internal fun notify(@StringRes message: Int) = Snackbar.make(viewContainer,message,Snackbar.LENGTH_SHORT).show()

    internal fun notifyWithAction(
        @StringRes message: Int,
        @StringRes actionText: Int,
        action: () -> Any
    ){
        val snackBar = Snackbar.make(viewContainer, message, Snackbar.LENGTH_INDEFINITE);
        snackBar.setAction(actionText){ _ -> action.invoke()}
        snackBar.setActionTextColor(ContextCompat.getColor(appContext, color.colorPrimary))
        snackBar.show()
    }
}