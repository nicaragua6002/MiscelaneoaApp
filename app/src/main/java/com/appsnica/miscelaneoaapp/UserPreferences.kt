package com.appsnica.miscelaneoaapp
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserPreferences(context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "encrypted_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private val _examplePreference = MutableStateFlow<String?>(null)
    val examplePreference: StateFlow<String?> = _examplePreference.asStateFlow()

    init {
        _examplePreference.value = sharedPreferences.getString(EXAMPLE_KEY, null)
    }

    fun saveExamplePreference(value: String) {
        sharedPreferences.edit().putString(EXAMPLE_KEY, value).apply()
        _examplePreference.value = value
    }

    companion object {
        private const val EXAMPLE_KEY = "example_key"
    }
}