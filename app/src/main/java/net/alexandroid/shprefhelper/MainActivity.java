package net.alexandroid.shprefhelper;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.alexandroid.shpref.MyLog;
import net.alexandroid.shpref.ShPref;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewsAndListeners();
    }

    private void setViewsAndListeners() {
        mTextView = (TextView) findViewById(R.id.textView);
        findViewById(R.id.btnLoad).setOnClickListener(this);
        findViewById(R.id.btnSave).setOnClickListener(this);
        findViewById(R.id.btnRemove).setOnClickListener(this);
        findViewById(R.id.btnContains).setOnClickListener(this);
        findViewById(R.id.btnSaveWithEditor).setOnClickListener(this);
        findViewById(R.id.btnLoadTestEditor).setOnClickListener(this);
        findViewById(R.id.btnTestLogger).setOnClickListener(this);
        findViewById(R.id.btnSaveList).setOnClickListener(this);
        findViewById(R.id.btnLoadList).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                onSaveBtnPressed();
                break;
            case R.id.btnLoad:
                onLoadBtnPressed();
                break;
            case R.id.btnRemove:
                onRemoveBtnPressed();
                break;
            case R.id.btnContains:
                onContainsBtnPressed();
                break;
            case R.id.btnSaveWithEditor:
                onSaveWithEditorBtnPressed();
                break;
            case R.id.btnLoadTestEditor:
                onLoadDataSavedWithEditorBtnPressed();
                break;
            case R.id.btnTestLogger:
                onTestLoggerBtnPressed();
                break;
            case R.id.btnSaveList:
                onSaveListBtnPressed();
                break;
            case R.id.btnLoadList:
                onLoadListBtnPressed();
                break;
        }
    }


    private void onSaveBtnPressed() {
        ShPref.put(R.string.key_key, "Sample text");
        mTextView.setText(R.string.some_text_was_saved);

        // TEST
        ShPref.put("Integer", 123);
        ShPref.put("Boolean", false);
        ShPref.put("Float", 1.23f);
        ShPref.put("Long", 123456789L);
        ShPref.put("Double", 3.45678);

        showToast("Saved");
    }

    @SuppressLint("SetTextI18n") // To make it simple
    private void onLoadBtnPressed() {
        String loadedText = ShPref.getString(R.string.key_key, "Default value");
        String result = getString(R.string.loaded_text) + loadedText;
        mTextView.setText(result);

        MyLog.d("Integer: " + ShPref.getInt("Integer", 0));
        MyLog.d("Boolean: " + ShPref.getBoolean("Boolean", true));
        MyLog.d("Float: " + ShPref.getFloat("Float", 0.0f));
        MyLog.d("Long: " + ShPref.getLong("Long", 0L));
        MyLog.d("Double: " + ShPref.getDouble("Double", 1.0));
        showToast(result);
    }

    private void onRemoveBtnPressed() {
        ShPref.remove(R.string.key_key);
        mTextView.setText(R.string.saved_text_removed);
        showToast(getString(R.string.saved_text_removed));
    }

    private void onContainsBtnPressed() {
        boolean isContains = ShPref.contains(R.string.key_key);
        String result = getString(R.string.contains2) + " " + isContains;
        mTextView.setText(result);
        showToast(result);
    }

    private void onSaveWithEditorBtnPressed() {
        new ShPref.Editor()
                .put("key1", "key1value")
                .put("key2", "key2value")
                .put("key3", "key3value")
                .commit();
        mTextView.setText(R.string.some_text_was_saved);
        showToast("Saved with editor");
    }

    private void onLoadDataSavedWithEditorBtnPressed() {
        String loadedText = ShPref.getString("key1") + ", " + ShPref.getString("key2") + ", " + ShPref.getString("key3");
        String result = getString(R.string.loaded_text) + loadedText;
        mTextView.setText(result);
        showToast(result);
    }

    private void onTestLoggerBtnPressed() {
        MyLog.showLogs(true);
        MyLog.d("Debug test");
        MyLog.e("Error test");
        MyLog.setTag("MyLog-NEW_TAG");
        MyLog.i("Testing setTag method ^)");

        mTextView.setText(R.string.logger_test);
        showToast(getString(R.string.logger_test));
    }


    private void onSaveListBtnPressed() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        ShPref.putList("myListKey", list);

        mTextView.setText(R.string.saved_a_list);
        showToast(getString(R.string.saved_a_list));
    }


    private void onLoadListBtnPressed() {
        List<Integer> list = ShPref.getListOfIntegers("myListKey");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Result: ");
        for (Integer i : list) {
            stringBuilder.append(i).append(", ");
        }
        mTextView.setText(stringBuilder.toString());
        showToast(stringBuilder.toString());
    }


    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
