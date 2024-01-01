package com.example.icomerch.model

import android.content.Context
import androidx.annotation.StringRes
import com.example.icomerch.R

enum class Role(@StringRes val roleNameResId: Int) {
    SUPPLIER(R.string.supplier),
    CLIENT(R.string.client);

    companion object {
        fun fromString(name: String, context: Context): Role? {
            return values().firstOrNull {
                context.getString(it.roleNameResId).equals(name, ignoreCase = true)
            }
        }
    }
}
