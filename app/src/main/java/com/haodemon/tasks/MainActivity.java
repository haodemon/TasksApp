package com.haodemon.tasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import com.haodemon.tasks.Storage.*;


public class MainActivity extends AppCompatActivity {
    private ArrayList<String> items = new ArrayList<>();
    private ArrayAdapter<String> itemsAdapter;
    private ListView listViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewItems = findViewById(R.id.listView);
        // read caches items from todo.txt
        items = new SimpleFileReader().read(getFilesDir());
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listViewItems.setAdapter(itemsAdapter);

        setupListViewListener();
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
        new SimpleFileWriter().write(getFilesDir(), items);
        inputField.setText("");
    }

    private void setupListViewListener() {
        listViewItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
