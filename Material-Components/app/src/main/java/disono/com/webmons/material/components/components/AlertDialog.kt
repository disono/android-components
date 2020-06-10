package disono.com.webmons.material.components.components

import android.app.Activity
import android.content.DialogInterface
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AlertDialog(self: Activity) {
    private var self: Activity? = self

    var title: String? = null
    var message: String? = null
    var btn: String = "Ok"

    fun show(
        positiveListener: (dialog: DialogInterface, which: Int) -> Unit = { _: DialogInterface, _: Int -> },
        negativeListener: (dialog: DialogInterface, which: Int) -> Unit = { _: DialogInterface, _: Int -> }
    ) {
        MaterialAlertDialogBuilder(this.self)
            .setTitle(title ?: "Alert")
            .setMessage(message)
            .setPositiveButton(this.btn, DialogInterface.OnClickListener(function = positiveListener))
            .setNegativeButton("What The Fuck", DialogInterface.OnClickListener(function = negativeListener))
            .show()
    }

}