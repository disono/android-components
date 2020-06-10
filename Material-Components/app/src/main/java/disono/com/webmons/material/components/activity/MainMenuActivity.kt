package disono.com.webmons.material.components.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import disono.com.webmons.material.components.R
import disono.com.webmons.material.components.adapater.ExpandableListAdapter as CustomExpandableListAdapter

class MainMenuActivity : AppCompatActivity() {

    private var expandableListView: ExpandableListView? = null
    private var adapter: ExpandableListAdapter? = null
    private val data: MutableMap<CustomExpandableListAdapter.ItemParent, MutableList<CustomExpandableListAdapter.ItemChild>> = setData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expandableListView = findViewById(R.id.expandableListMainMenu)
        if (expandableListView != null) {
            adapter = CustomExpandableListAdapter(this, data)

            expandableListView!!.setAdapter(adapter)
            expandableListView!!.setOnGroupExpandListener { }
            expandableListView!!.setOnChildClickListener { _, _, i, il, _ ->

                Log.v(
                    "MainMenuActivity",
                    "Parent: ${ArrayList(data.keys)[i].title} " +
                            "Child: ${data[ArrayList(data.keys)[i]]!![il].title} " +
                            "Size: ${data.size}"
                )

                false

            }
        }
    }

    private fun setData() : MutableMap<CustomExpandableListAdapter.ItemParent, MutableList<CustomExpandableListAdapter.ItemChild>> {
        val listData = HashMap<CustomExpandableListAdapter.ItemParent, MutableList<CustomExpandableListAdapter.ItemChild>>()

        val bottomNavigation = mutableListOf<CustomExpandableListAdapter.ItemChild>()
        bottomNavigation.add(CustomExpandableListAdapter.ItemChild("Basic", false))
        bottomNavigation.add(CustomExpandableListAdapter.ItemChild("Shifting", false))
        bottomNavigation.add(CustomExpandableListAdapter.ItemChild("Light", false))
        bottomNavigation.add(CustomExpandableListAdapter.ItemChild("Dark", false))
        bottomNavigation.add(CustomExpandableListAdapter.ItemChild("Icon", false))
        bottomNavigation.add(CustomExpandableListAdapter.ItemChild("Primary", false))
        bottomNavigation.add(CustomExpandableListAdapter.ItemChild("Map Blue", false))
        bottomNavigation.add(CustomExpandableListAdapter.ItemChild("Light Simple", false))

        val bottomSheet = mutableListOf<CustomExpandableListAdapter.ItemChild>()
        bottomSheet.add(CustomExpandableListAdapter.ItemChild("Basic", false))
        bottomSheet.add(CustomExpandableListAdapter.ItemChild("Shifting", false))
        bottomSheet.add(CustomExpandableListAdapter.ItemChild("Light", false))
        bottomSheet.add(CustomExpandableListAdapter.ItemChild("Dark", false))
        bottomSheet.add(CustomExpandableListAdapter.ItemChild("Icon", false))
        bottomSheet.add(CustomExpandableListAdapter.ItemChild("Primary", false))
        bottomSheet.add(CustomExpandableListAdapter.ItemChild("Map Blue", false))
        bottomSheet.add(CustomExpandableListAdapter.ItemChild("Light Simple", false))

        listData[CustomExpandableListAdapter.ItemParent("Bottom Navigation", R.drawable.ic_arrow_down)] = bottomNavigation
        listData[CustomExpandableListAdapter.ItemParent("Bottom Sheet", R.drawable.ic_arrow_down)] = bottomSheet

        return listData
    }
}