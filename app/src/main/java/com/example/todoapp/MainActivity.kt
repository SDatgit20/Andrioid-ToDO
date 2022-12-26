package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: ToDoAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener { view ->
            val intent=Intent(this,CreateTodoActivity::class.java);
            startActivity(intent);
        }
        updateWelcomeMessage();
    }

    fun updateWelcomeMessage(){
        val c = Calendar.getInstance()
        val timeOfDay = c.get(Calendar.HOUR_OF_DAY)
        val time=Calendar.getInstance().time;
        val dayOfWeek= LocalDate.now().getDayOfWeek().name;
        val date = Calendar.getInstance().time;
        val formatter = SimpleDateFormat.getDateInstance();
        val formatedDate = formatter.format(date);
        val current = LocalDateTime.now()
        textDate.text= formatedDate;
        textDay.text= "Today's "+ dayOfWeek.toLowerCase().capitalize();

         when (timeOfDay) {
            in 0..11 -> welcomeText.text=" Good \n Morning"+"☀️";
            in 12..15 ->  welcomeText.text=" Good \n Afternoon"+"☀️";
            in 16..20 -> welcomeText.text=" Good \n Evening ⭐️ ";
            in 21..23 -> welcomeText.text=" Good \n Evening"+"🌝️";
            else -> welcomeText.text="Hello"+"🌸";
        }
    }
    override fun onResume() {
        super.onResume();
        updateRecycler();
        updateWelcomeMessage();
    }


    fun updateRecycler(){
        var prefs=getSharedPreferences("com.example.todoapp.sharedprefs", MODE_PRIVATE);
        var todoList= prefs.getStringSet("todoString", setOf())?.toMutableSet();
        println(todoList.toString());
        layoutManager= LinearLayoutManager(this);
        binding.recyclerView.layoutManager=layoutManager;
        if (todoList != null) {
            adapter=ToDoAdapter(todoList.toList())
        };
        binding.recyclerView.adapter=adapter;

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(item.itemId==R.id.action_delete_all){
            var prefs=getSharedPreferences("com.example.todoapp.sharedprefs", MODE_PRIVATE);
            prefs.edit().putStringSet("todoString",null).apply();
            updateRecycler();
        }
        return  super.onOptionsItemSelected(item);
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.recyclerView)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}