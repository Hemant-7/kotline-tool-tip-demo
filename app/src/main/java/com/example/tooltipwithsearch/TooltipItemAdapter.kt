import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tooltipwithsearch.R
import com.skydoves.balloon.Balloon

data class ListItem(val name: String, var isSelected: Boolean)

class TooltipItemAdapter(
    private val context: Context,
    private var items: List<ListItem>,
    private val originalItems: List<ListItem>,
    private val onItemSelected: (ListItem) -> Unit,
    private val dismissTooltip: () -> Unit
) : RecyclerView.Adapter<TooltipItemAdapter.ViewHolder>() {

    fun filter(query: String) {
        items = if (query.isEmpty()) {
            originalItems
        } else {
            originalItems.filter { it.name.contains(query, true) }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tooltip_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.itemName.text = item.name

        holder.container.setBackgroundColor(
            if (item.isSelected) {
                ContextCompat.getColor(context, R.color.gray)
            } else {
                ContextCompat.getColor(context, android.R.color.white)
            }
        )

        holder.container.setOnClickListener {
            item.isSelected = !item.isSelected
            for (i in originalItems.indices) {
                if (i != position) originalItems[i].isSelected = false
            }
            notifyDataSetChanged()
            onItemSelected(item)
            dismissTooltip()
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: LinearLayout = view.findViewById(R.id.container)
        val itemName: TextView = view.findViewById(R.id.itemName)
    }
}