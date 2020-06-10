package disono.com.webmons.material.components.adapater

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import disono.com.webmons.material.components.R

class ExpandableListAdapter(
    private val context: Context,
    private var dataList: MutableMap<ItemParent, MutableList<ItemChild>>
) : BaseExpandableListAdapter() {

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        val titleList = ArrayList(this.dataList.keys)
        return this.dataList[titleList[listPosition]]!![expandedListPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        view: View?,
        parent: ViewGroup
    ): View {
        var convertView = view
        val itemChild = getChild(listPosition, expandedListPosition) as ItemChild

        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.item_child_main_menu, null)
        }

        val expandedListTextView = convertView!!.findViewById<TextView>(R.id.item_sub_title)
        expandedListTextView.text = itemChild.title

        if (itemChild.indicator) {
            convertView!!.findViewById<ImageView>(R.id.item_sub_indicator).visibility = View.VISIBLE
        } else {
            convertView!!.findViewById<ImageView>(R.id.item_sub_indicator).visibility = View.GONE
        }

        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        val titleList = ArrayList(this.dataList.keys)
        return this.dataList[titleList[listPosition]]!!.size
    }

    override fun getGroup(listPosition: Int): Any {
        val titleList = ArrayList(this.dataList.keys)
        return titleList[listPosition]
    }

    override fun getGroupCount(): Int {
        val titleList = ArrayList(this.dataList.keys)
        return titleList.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(listPosition: Int, isExpanded: Boolean, view: View?, parent: ViewGroup): View {
        var convertView = view
        val itemParent = getGroup(listPosition) as ItemParent

        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.item_menu_group, null)
        }

        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.item_group_title)
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = itemParent.title

        if (itemParent.indicator) {
            convertView!!.findViewById<ImageView>(R.id.item_group_indicator).visibility = View.VISIBLE
        } else {
            convertView!!.findViewById<ImageView>(R.id.item_group_indicator).visibility = View.GONE
        }

        val arrow = convertView!!.findViewById<ImageView>(R.id.item_group_arrow)
        if (isExpanded) {
            arrow.animate().setDuration(300).rotation(180f)
        } else {
            arrow.animate().setDuration(300).rotation(0f)
        }

        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }

    fun add() {
        notifyDataSetChanged()
    }

    data class ItemParent(
        val title: String,
        val icon: Int,
        var indicator: Boolean = false,
        var itemType: String = "MENU"
    )

    data class ItemChild(val title: String, var indicator: Boolean = false, var intent: Intent? = null)

}