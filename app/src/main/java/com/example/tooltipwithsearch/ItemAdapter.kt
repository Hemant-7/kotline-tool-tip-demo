import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tooltipwithsearch.R
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation


data class Item(val name: String, val info: String)

class ItemAdapter(private val context: Context, private val items: List<Item>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var tooltip: Balloon? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.itemName)
        val infoButton: ImageButton = itemView.findViewById(R.id.infoButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.itemName.text = item.name

        // Set the click listener for the info button
        holder.infoButton.setOnClickListener {
            val listItems = listOf(
                ListItem("Item 1", false),
                ListItem("Item 2", false),
                ListItem("Item 3", false),
                ListItem("Item 4", false),
                ListItem("Item 5", false),
                ListItem("Item 6", false),
                ListItem("Item 7", false),
                ListItem("Item 8", false),
                ListItem("Item 9", false),
                ListItem("Item 10", false),
                ListItem("Item 11", false),
                ListItem("Item 12", false)
            )

            tooltip = createItemTooltip(context, listItems, {
                // Handle item selection here if needed
            }, {
                tooltip?.dismiss() // Dismiss the tooltip
            })
            tooltip?.showAlignBottom(it) // Displays the tooltip above the button with the arrow at the bottom of the tooltip
        }
    }

    override fun getItemCount(): Int = items.size

    private fun createItemTooltip(
        context: Context,
        items: List<ListItem>,
        onItemSelected: (ListItem) -> Unit,
        dismissTooltip: () -> Unit
    ): Balloon {
        val customView: View = LayoutInflater.from(context).inflate(R.layout.tooltip_list, null)
        val searchEditText: EditText = customView.findViewById(R.id.searchEditText)
        val recyclerView: RecyclerView = customView.findViewById(R.id.itemListView)

        // Set up the RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = TooltipItemAdapter(context, items, items, onItemSelected, dismissTooltip)
        recyclerView.adapter = adapter

        // Add a text watcher to the search EditText to filter the list based on user input
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // No action needed here
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed here
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }
        })

        return Balloon.Builder(context)
            .setLayout(customView)
            .setArrowSize(20)
            .setArrowColorResource(R.color.test)
            .setBackgroundColorResource(R.color.white)
            .setWidthRatio(0.95f)
            .setHeight(600)
            .setCornerRadius(8f)
            .setArrowOrientation(ArrowOrientation.TOP)
            .setArrowPosition(0.92f)
            .setElevation(6)
            .setArrowColorMatchBalloon(true)
            .setDismissWhenClicked(true)
            .setBalloonAnimation(BalloonAnimation.OVERSHOOT)
            .setLifecycleOwner(context as LifecycleOwner)
            .build()
    }
}