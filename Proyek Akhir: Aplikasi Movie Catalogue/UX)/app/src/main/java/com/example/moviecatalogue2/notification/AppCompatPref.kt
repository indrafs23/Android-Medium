package com.example.moviecatalogue2.notification

import android.os.Bundle
import android.preference.PreferenceActivity
import android.view.MenuInflater
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatDelegate


open class AppCompatPref : PreferenceActivity() {

    private var mAppCompatDelegate: AppCompatDelegate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        getDelegate().installViewFactory()
        getDelegate().onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    override fun invalidateOptionsMenu() {
        getDelegate().invalidateOptionsMenu()
    }

    fun getSupportActionBar(): ActionBar? {
        return getDelegate().supportActionBar
    }

    private fun getDelegate(): AppCompatDelegate {
        if (mAppCompatDelegate == null) {
            mAppCompatDelegate = AppCompatDelegate.create(this, null)
        }
        return mAppCompatDelegate as AppCompatDelegate
    }

    override fun getMenuInflater(): MenuInflater {
        return getDelegate().menuInflater
    }

    override fun onTitleChanged(title: CharSequence, color: Int) {
        super.onTitleChanged(title, color)
        getDelegate().setTitle(title)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        getDelegate().onPostCreate(savedInstanceState)
    }

    override fun onPostResume() {
        super.onPostResume()
        getDelegate().onPostResume()
    }

    override fun onStop() {
        super.onStop()
        getDelegate().onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        getDelegate().onDestroy()
    }
}