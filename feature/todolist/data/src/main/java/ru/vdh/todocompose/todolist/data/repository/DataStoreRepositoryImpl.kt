package ru.vdh.todocompose.todolist.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import ru.vdh.todocompose.common.utils.Constants.PREFERENCE_KEY
import ru.vdh.todocompose.common.utils.Constants.PREFERENCE_NAME
import ru.vdh.todocompose.todolist.domain.repository.DataStoreRepository
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

class DataStoreRepositoryImpl(
    private val context: Context
): DataStoreRepository {

    private object PreferenceKeys {
        val sortKey = stringPreferencesKey(name = PREFERENCE_KEY)
    }

    private val dataStore = context.dataStore

    override suspend fun persistSortState(priority: String) {
        dataStore.edit { preference ->
            preference[PreferenceKeys.sortKey] = priority
        }
    }

    override fun readSortState() = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val sortState = preferences[PreferenceKeys.sortKey] ?: "NONE"
            sortState
        }

}












