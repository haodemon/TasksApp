package com.haodemon.tasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.haodemon.tasks.Storage.SimpleStorage;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewItems = findViewById(R.id.listView);
        // read caches items from todo.txt
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
        // todo create a notification that the input field should not be empty
        if (itemText.isEmpty()) {
            inputField.setText("");
            return;
        }
        itemsAdapter.add(itemText);
        SimpleStorage.write(getFilesDir(), items);
        inputField.setText("");
    }
}
