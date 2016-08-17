package net.alexandroid.shprefhelper;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.alexandroid.shpref.ShPref;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewsAndListeners();
    }

    private void setViewsAndListeners() {
        mTextView = (TextView)findViewById(R.id.textView);
        findViewById(R.id.btnLoad).setOnClickListener(this);
        findViewById(R.id.btnSave).setOnClickListener(this);
        findViewById(R.id.btnRemove).setOnClickListener(this);
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
        }
    }



    private void onSaveBtnPressed() {
        ShPref.put(R.string.key_key, "Sample text");
        mTextView.setText(R.string.some_text_was_saved);
    }

    @SuppressLint("SetTextI18n") // To make it simple
    private void onLoadBtnPressed() {
        String loadedText = ShPref.getString(R.string.key_key, "Default value");
        mTextView.setText(getString(R.string.loaded_text) + loadedText);
    }

    private void onRemoveBtnPressed() {
        ShPref.remove(R.string.key_key);
        mTextView.setText(R.string.saved_text_removed);
    }

}
