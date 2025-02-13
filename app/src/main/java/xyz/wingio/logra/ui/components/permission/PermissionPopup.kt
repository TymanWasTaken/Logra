package xyz.wingio.logra.ui.components.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun PermissionPopup() {
    var state by remember { mutableStateOf(PopupState.REQUEST) }

    when (state) {
        PopupState.REQUEST -> RequestPopup { state = it }
        PopupState.SHIZUKU -> GrantAndClosePopup { state = it }
        PopupState.NONE -> Unit
    }
}

enum class PopupState {
    REQUEST,
    SHIZUKU,
    NONE
}