package com.example.tooltipwithsearch
import Item
import ItemAdapter
import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = listOf(
            Item("Item 1", "Info about Item 1"),
            Item("Item 2", "Info about Item 2"),
            Item("Item 3", "Info about Item 3"),
            Item("Item 1", "Info about Item 4"),
            Item("Item 1", "Info about Item 1"),
            Item("Item 2", "Info about Item 2"),
            Item("Item 3", "Info about Item 3"),
            Item("Item 1", "Info about Item 4"),
            Item("Item 1", "Info about Item 1"),
            Item("Item 2", "Info about Item 2"),
            Item("Item 3", "Info about Item 3"),
            Item("Item 1", "Info about Item 4"),
            Item("Item 1", "Info about Item 1"),
            Item("Item 2", "Info about Item 2"),
            Item("Item 3", "Info about Item 3"),
            Item("Item 1", "Info about Item 4"),
            Item("Item 1", "Info about Item 1"),
            Item("Item 2", "Info about Item 2"),
            Item("Item 3", "Info about Item 3"),
            Item("Item 1", "Info about Item 4"),
            Item("Item 1", "Info about Item 1"),
            Item("Item 2", "Info about Item 2"),
            Item("Item 3", "Info about Item 3"),
            Item("Item 1", "Info about Item 4"),

        )

        recyclerView.adapter = ItemAdapter(this, items)
    }
}
