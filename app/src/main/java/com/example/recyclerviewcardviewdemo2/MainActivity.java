package com.example.recyclerviewcardviewdemo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button buttonInsert;
    private Button buttonRemove;
    private EditText editTextInsert;
    private EditText editTextRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleList();
        buildRecyclerView();

        buttonInsert = findViewById(R.id.button_insert);
        buttonRemove = findViewById(R.id.button_remove);
        editTextInsert = findViewById(R.id.edittext_insert);
        editTextRemove = findViewById(R.id.edittext_remove);

        handleButtonInsertClick();
        handleButtonRemoveClick();
    }

    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line 1", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.ic_music, "Line 3", "Line 4"));
        mExampleList.add(new ExampleItem(R.drawable.ic_sun, "Line 5", "Line 6"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line 1", "Line 2"));
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position, "Clicked");
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    private void handleButtonInsertClick() {
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextInsert.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Enter numeric value first", Toast.LENGTH_SHORT).show();
                    return;
                }

                int position = Integer.parseInt(editTextInsert.getText().toString());
                if (position <= mExampleList.size()) {
                    insertItem(position);
                    mAdapter.notifyItemInserted(position);
                } else {
                    Toast.makeText(MainActivity.this, "Enter proper position to insert item", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void handleButtonRemoveClick() {
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextRemove.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Enter numeric value first", Toast.LENGTH_SHORT).show();
                    return;
                }

                // this is my changing
                int position = Integer.parseInt(editTextRemove.getText().toString());
                if (position <= mExampleList.size()) {
                    removeItem(position);
                    mAdapter.notifyItemRemoved(position);
                } else {
                    Toast.makeText(MainActivity.this, "Enter proper position to remove item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertItem(int position) {
        mExampleList.add(position, new ExampleItem(R.drawable.ic_android, "Added item at position " + position, "This is line " + position));
        mAdapter.notifyItemInserted(position);
    }

    private void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void changeItem(int position, String text) {
        mExampleList.get(position).changeText1(text);
        mAdapter.notifyItemChanged(position);
    }
}
