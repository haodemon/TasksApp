package com.haodemon.tasks;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.haodemon.tasks.Storage.SimpleStorage;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> items;
    private ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listViewItems = findViewById(R.id.listView);
        items = SimpleStorage.read(getFilesDir());
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listViewItems.setAdapter(itemsAdapter);
        listViewItems.setOnItemLongClickListener((parent, view, pos, id) -> {
            items.remove(pos);
            itemsAdapter.notifyDataSetChanged();
            return true;
        });
    }

    public void addItem(View v) {
        EditText inputField = findViewById(R.id.inputField);
        String itemText = inputField.getText().toString();
        if (itemText.isEmpty()) {
            getWindow().getDecorView().performHapticFeedback(
                    HapticFeedbackConstants.VIRTUAL_KEY,
                    HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
            Snackbar.make(inputField, getString(R.string.snackbar_empty_task), Snackbar.LENGTH_SHORT)
                    .setAction(getString(R.string.snackbar_ok), view -> {})
                    .setActionTextColor(Color.RED)
                    .show();
            return;
        }
        itemsAdapter.add(itemText);
        SimpleStorage.write(getFilesDir(), items);
        inputField.setText("");
    }
}
