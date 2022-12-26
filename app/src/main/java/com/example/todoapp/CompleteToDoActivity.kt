package com.example.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Paint
import kotlinx.android.synthetic.main.activity_complete_to_do.*

class CompleteToDoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide();
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_to_do);

        var todo= intent.extras?.getString("title");
        todoText.text="Are you sure want to delete "+todo+" ?";

        doneButton.setOnClickListener{
            var prefs=getSharedPreferences("com.example.todoapp.sharedprefs", MODE_PRIVATE);
            var todoList= prefs.getStringSet("todoString", setOf())?.toMutableSet();
            todoList?.remove(todo);
            prefs.edit().putStringSet("todoString",todoList).apply();
            finish();
        }

        button2.setOnClickListener{
            finish();
        }

    }
}