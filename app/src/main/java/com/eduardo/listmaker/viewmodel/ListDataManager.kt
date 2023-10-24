package com.eduardo.listmaker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.preference.PreferenceManager
import com.eduardo.listmaker.data.TaskList

class ListDataManager(private val application: Application) : AndroidViewModel(application) {

    fun SaveList(list: TaskList) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(application).edit()
        sharedPrefs.putStringSet(list.name, list.tasks.toHashSet())
        sharedPrefs.apply()
    }

    fun ReadList(): ArrayList<TaskList> {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(application)
        val contents = sharedPrefs.all
        val taskLists = ArrayList<TaskList>()
        for (taskList in contents) {
            val taskItems = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, taskItems)
            taskLists.add(list)
        }
        return taskLists
    }
}





