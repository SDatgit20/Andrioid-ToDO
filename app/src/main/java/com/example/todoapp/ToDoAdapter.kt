package com.example.todoapp

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_create_todo.*
import kotlinx.android.synthetic.main.todo_item.view.*

class ToDoAdapter(val todos: List<String>) : RecyclerView.Adapter<ToDoAdapter.TodoHolder>() {
    class TodoHolder(v: View) : RecyclerView.ViewHolder(v) {
        var view: View = v;
        var title: String = "";

//        init {
//            view.setOnClickListener(this);
//        }
//
//        override fun onClick(p0: View?) {
//            var intent = Intent(view.context, CompleteToDoActivity::class.java);
//            intent.putExtra("title", title);
//            startActivity(view.context, intent, null);
//        }

        fun bindTodo(title: String) {
            this.title = title;
            view.textView.text = title;
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        return TodoHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        );
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        val title = todos[position];
//        holder.view.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
//            println(isChecked);
//            if (isChecked) {
//                holder.view.textView.setPaintFlags(holder.view.textView.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG);
//            }
//            else{
//                holder.view.textView.setPaintFlags( Paint.STRIKE_THRU_TEXT_FLAG.inv());
//            }
//        }
        holder.view.editButton.setOnClickListener {
            var intent = Intent(holder.view.context, CreateTodoActivity::class.java);
            intent.putExtra("title", title);
            intent.putExtra("from","edit");
            intent.putExtra("index",position);
            startActivity(holder.view.context, intent, null);
        }
        holder.view.deleteButton.setOnClickListener{
//            var prefs=PreferenceManager.getDefaultSharedPreferences(holder.view.context);
//            var todoList= prefs.getStringSet("todoString", setOf())?.toMutableSet();
//            todoList?.remove(title);
//            prefs.edit().putStringSet("todoString",todoList).apply();
            var intent = Intent(holder.view.context, CompleteToDoActivity::class.java);
            intent.putExtra("title", title);
            startActivity(holder.view.context, intent, null);
        }
        holder.bindTodo(title);
    }

    override fun getItemCount(): Int {
        return todos.count();
    }
}