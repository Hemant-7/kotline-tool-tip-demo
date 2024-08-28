import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.tooltipwithsearch.R
import com.skydoves.balloon.Balloon

data class ListItem(val name: String, var isSelected: Boolean)

class TooltipItemAdapter(
    private val context: Context,
    private var items: List<ListItem>,
    private val originalItems: List<ListItem>,
    private val onItemSelected: (ListItem) -> Unit,
    private val dismissTooltip: () -> Unit
) : BaseAdapter() {

    fun filter(query: String) {
        items = if (query.isEmpty()) {
            originalItems
        } else {
            originalItems.filter { it.name.contains(query, true) }
        }
        notifyDataSetChanged()
    }

    override fun getCount(): Int = items.size
    override fun getItem(position: Int): Any = items[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.tooltip_list_item, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

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

        return view
    }

    private class ViewHolder(view: View) {
        val container: LinearLayout = view.findViewById(R.id.container)
        val itemName: TextView = view.findViewById(R.id.itemName)
    }
}
