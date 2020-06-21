package net.alexandroid.shprefhelper

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_kotlin.*
import net.alexandroid.shprefkt.ShPrefKt

class KotlinActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var shPref: ShPrefKt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        shPref = ShPrefKt(this)
        setViewsAndListeners()
    }

    private fun setViewsAndListeners() {
        btnLoad.setOnClickListener(this)
        btnSave.setOnClickListener(this)
        btnRemove.setOnClickListener(this)
        btnContains.setOnClickListener(this)
        btnSaveWithEditor.setOnClickListener(this)
        btnLoadTestEditor.setOnClickListener(this)
        btnSaveList.setOnClickListener(this)
        btnLoadList.setOnClickListener(this)
    }

    // View.OnClickListener
    override fun onClick(v: View?) {
        when (v) {
            btnLoad -> onLoadBtnPressed()
            btnSave -> onSaveBtnPressed()
            btnRemove -> onRemoveBtnPressed()
            btnContains -> onContainsBtnPressed()
            btnSaveWithEditor -> onSaveWithEditorBtnPressed()
            btnLoadTestEditor -> onLoadDataSavedWithEditorBtnPressed()
            btnSaveList -> onSaveListBtnPressed()
            btnLoadList -> onLoadListBtnPressed()
        }
    }

    private fun onSaveBtnPressed() {
        shPref.put(R.string.key_key, "Sample text")
        textView.setText(R.string.some_text_was_saved)

        // TEST
        shPref.put("Integer", 123)
        shPref.put("Boolean", false)
        shPref.put("Float", 1.23f)
        shPref.put("Long", 123456789L)
        shPref.put("Double", 3.45678)
        showToast("Saved")
    }

    @SuppressLint("SetTextI18n") // To make it simple
    private fun onLoadBtnPressed() {
        val loadedText = shPref.getString(R.string.key_key, "Default value")
        val result = getString(R.string.loaded_text) + loadedText
        textView.text = result
        showToast(result)
    }

    private fun onRemoveBtnPressed() {
        shPref.remove(R.string.key_key)
        textView.setText(R.string.saved_text_removed)
        showToast(getString(R.string.saved_text_removed))
    }

    private fun onContainsBtnPressed() {
        val isContains = shPref.contains(R.string.key_key)
        val result = getString(R.string.contains2) + " " + isContains
        textView.text = result
        showToast(result)
    }

    private fun onSaveWithEditorBtnPressed() {
        shPref.Editor()
                .put("key1", "key1value")
                .put("key2", "key2value")
                .put("key3", "key3value")
                .commit()
        textView.setText(R.string.some_text_was_saved)
        showToast("Saved with editor")
    }

    private fun onLoadDataSavedWithEditorBtnPressed() {
        val loadedText = shPref.getString("key1") + ", " + shPref.getString("key2") + ", " + shPref.getString("key3")
        val result = getString(R.string.loaded_text) + loadedText
        textView.text = result
        showToast(result)
    }


    private fun onSaveListBtnPressed() {
        val list = ArrayList<Int?>()
        for (i in 0..9) {
            list.add(i)
        }
        shPref.putList("myListKey", list)
        textView.setText(R.string.saved_a_list)
        showToast(getString(R.string.saved_a_list))
    }


    private fun onLoadListBtnPressed() {
        val list: List<Int> = shPref.getListOfIntegers("myListKey")
        val stringBuilder = StringBuilder()
        stringBuilder.append("Result: ")
        for (i in list) {
            stringBuilder.append(i).append(", ")
        }
        textView.text = stringBuilder.toString()
        showToast(stringBuilder.toString())
    }


    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}