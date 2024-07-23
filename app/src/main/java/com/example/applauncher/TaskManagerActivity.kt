package com.example.applauncher

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class TaskManagerActivity : AppCompatActivity() {

    private lateinit var etTaskName: EditText
    private lateinit var spinnerPriority: Spinner
    private lateinit var btnAddTask: Button
    private lateinit var listViewTasks: ListView
    private lateinit var tasksAdapter: ArrayAdapter<String>
    private val tasksList = ArrayList<String>()

    private lateinit var etRecipeName: EditText
    private lateinit var spinnerDifficulty: Spinner
    private lateinit var btnAddRecipe: Button
    private lateinit var listViewRecipes: ListView
    private lateinit var recipesAdapter: ArrayAdapter<String>
    private val recipesList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_manager)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialize Task Manager UI components
        etTaskName = findViewById(R.id.etTaskName)
        spinnerPriority = findViewById(R.id.spinnerPriority)
        btnAddTask = findViewById(R.id.btnAddTask)
        listViewTasks = findViewById(R.id.listViewTasks)

        // Set up the priority spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.priority_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerPriority.adapter = adapter
        }

        // Set up the task list adapter
        tasksAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tasksList)
        listViewTasks.adapter = tasksAdapter

        // Set up task button listener
        btnAddTask.setOnClickListener {
            val taskName = etTaskName.text.toString()
            val priority = spinnerPriority.selectedItem.toString()
            if (taskName.isNotEmpty()) {
                val task = "$taskName - Priority: $priority"
                tasksList.add(task)
                tasksAdapter.notifyDataSetChanged()
                etTaskName.text.clear()
            } else {
                Toast.makeText(this, "Please enter a task name", Toast.LENGTH_SHORT).show()
            }
        }

        // Initialize Recipe Manager UI components
        etRecipeName = findViewById(R.id.etRecipeName)
        spinnerDifficulty = findViewById(R.id.spinnerDifficulty)
        btnAddRecipe = findViewById(R.id.btnAddRecipe)
        listViewRecipes = findViewById(R.id.listViewRecipes)

        // Set up the difficulty spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.difficulty_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerDifficulty.adapter = adapter
        }

        // Set up the recipe list adapter
        recipesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, recipesList)
        listViewRecipes.adapter = recipesAdapter

        // Set up the recipe spinner listener
        spinnerDifficulty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Handle item selection
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@TaskManagerActivity, "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle case where nothing is selected
            }
        }

        // Set up recipe button listener
        btnAddRecipe.setOnClickListener {
            val recipeName = etRecipeName.text.toString()
            val difficulty = spinnerDifficulty.selectedItem.toString()
            if (recipeName.isNotEmpty()) {
                val recipe = "$recipeName - Difficulty: $difficulty"
                recipesList.add(recipe)
                recipesAdapter.notifyDataSetChanged()
                etRecipeName.text.clear()
            } else {
                Toast.makeText(this, "Please enter a recipe name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
