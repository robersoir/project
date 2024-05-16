package net.ezra.ui

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


val Context.myPreferencesDatastore: DataStore<Preferences> by preferencesDataStore("settings")

@Singleton
class MyPreferencesDataStore @Inject constructor(
    @ApplicationContext context: Context
){
    private val myPreferencesDataStore = context.myPreferencesDatastore

    private object PreferenceKey{
            val APP_ENTRY_KEY = booleanPreferencesKey("app_entry")}
    val readAppEntry = myPreferencesDataStore.data
        .catch{ exception ->
            if ( exception is IOException){
                emit(emptyPreferences())
            } else {
                throw exception
            }

        }.map { preferences->
            preferences[PreferenceKey.APP_ENTRY_KEY] ?: true
        }

    suspend fun saveAppEntry () {
        myPreferencesDataStore.edit { preferences ->
            preferences[PreferenceKey.APP_ENTRY_KEY] = false
        }
    }

}