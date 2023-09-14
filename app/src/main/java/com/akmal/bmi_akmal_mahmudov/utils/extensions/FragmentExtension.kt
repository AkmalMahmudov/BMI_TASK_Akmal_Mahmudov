package com.akmal.bmi_akmal_mahmudov.utils.extensions

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.akmal.bmi_akmal_mahmudov.presentation.MainActivity
import com.permissionx.guolindev.PermissionX
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun Fragment.getMainActivity(run: (MainActivity) -> (Unit)) {
    (activity as? MainActivity).let {
        it?.let { it1 ->
            run(it1)
        }
    }
}

fun <T> Fragment.observe(flow: Flow<T>, action: (T) -> Unit) {
    flow.onEach {
        if (this.view != null && this.isAdded) {
            action(it)
        }
    }.launchIn(this.lifecycleScope)
}

fun Fragment.toast(message: String?) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}


fun Fragment.goWeb(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(intent)
}

fun Fragment.requestPermissionsHelper(
    vararg list: String,
    granted: () -> Unit,
    denied: (List<String>) -> Unit
) {
    activity?.let {
        PermissionX.init(it)
            .permissions(list.asList())
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    granted.invoke()
                } else {
                    denied.invoke(deniedList)
                }
            }
    }
}