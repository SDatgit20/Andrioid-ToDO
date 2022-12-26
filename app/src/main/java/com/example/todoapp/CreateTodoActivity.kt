package com.example.todoapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_complete_to_do.*
import kotlinx.android.synthetic.main.activity_create_todo.*
import java.util.*
import kotlin.collections.HashSet

class CreateTodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide();
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_todo);
        var from= intent.extras?.getString("from");
        var index= intent.extras?.getInt("index");
        var title = "";
        if(from.equals("edit")){
            aetask.text="Edit Task";
        }
        else{
            aetask.text="Add Task";
        }
        backButton.setOnClickListener{
            finish();
        }
        button.setOnClickListener{
            if(from.equals("edit")){
                var prefs = getSharedPreferences("com.example.todoapp.sharedprefs", MODE_PRIVATE);
               var list=prefs.getStringSet("todoString", setOf())?.toTypedArray();
                if (index != null) {
                    if (impCheckBox.isChecked) {
                        title = "❗️" + todoTitle.text.toString();
                    } else
                        title = todoTitle.text.toString();
                    list?.set(index,title);
                };
                val seta = mutableSetOf<String>();
                if (list != null) {
                    for(i in list){
                        seta.add(i);
                    }
                }
                prefs.edit().putStringSet("todoString",seta).apply();
                finish();
            }
            else {
                if (impCheckBox.isChecked) {
                    title = "❗️" + todoTitle.text.toString();
                } else
                    title = todoTitle.text.toString();
                var prefs = getSharedPreferences("com.example.todoapp.sharedprefs", MODE_PRIVATE);
                var todoList = prefs.getStringSet("todoString", setOf())?.toMutableSet();
                todoList?.add(title);

                prefs.edit().putStringSet("todoString", todoList).apply();
                finish();
            }
        }
    }
}