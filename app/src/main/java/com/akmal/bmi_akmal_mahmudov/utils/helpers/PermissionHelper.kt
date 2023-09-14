package com.akmal.bmi_akmal_mahmudov.utils.helpers

import androidx.fragment.app.Fragment
import com.permissionx.guolindev.PermissionX

/**
 * Example to use in fragment
 *
 *  requestPermissions(
 *       Manifest.permission.CAMERA,
 *       Manifest.permission.READ_EXTERNAL_STORAGE,
 *       granted = {
 *           Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show()
 *       },
 *       denied = {
 *           Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
 *      })
 */

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
