package com.baserecyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.baserecyclerview.baseadapter.BaseAdapter
import com.baserecyclerview.baseadapter.ItemData
import com.baserecyclerview.baseadapter.ViewHolder
import com.baserecyclerview.itemdata.ImageItemData
import com.baserecyclerview.itemdata.ImageTextItemData
import com.baserecyclerview.itemdata.TextItemData
import com.baserecyclerview.listitem.ImageItem
import com.baserecyclerview.listitem.ImageTextItem
import com.baserecyclerview.listitem.TextItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BaseAdapter.OnItemClickListener {
    lateinit var adapter: BaseAdapter<ItemData>

    override fun onItemLongClick(view: View, holder: RecyclerView.ViewHolder, position: Int): Boolean {
        return false
    }

    override fun onItemClick(view: View, holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("MainActivity", "item data: "+ adapter.getItemData(position).toString())
        if(adapter.getItemData(position) is ImageItemData){
            Toast.makeText(this, "Image Item", Toast.LENGTH_SHORT).show()
        }
        if(view.id == R.id.image1){
            Toast.makeText(this, "Image1", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = arrayListOf<ItemData>()
//        data.add(ImageItemData(R.drawable.abc_ic_arrow_drop_right_black_24dp))
        data.add(TextItemData("124"))
        data.add(TextItemData("124"))
//        data.add(ImageTextItemData("title", R.drawable.ic_launcher_background))
        data.add(TextItemData("124"))
        data.add(TextItemData("124"))
//        data.add(ImageItemData(R.drawable.abc_ic_arrow_drop_right_black_24dp))
//        data.add(ImageItemData(R.drawable.ic_launcher_background))
        data.add(TextItemData("124"))
        data.add(TextItemData("124"))
//        data.add(ImageTextItemData("title", R.drawable.abc_ic_clear_material))
//        data.add(ImageTextItemData("title", R.drawable.abc_btn_check_material))
//        data.add(ImageTextItemData("title", R.drawable.ic_launcher_background))
//        data.add(ImageTextItemData("title", R.drawable.abc_btn_check_material))
//        data.add(ImageTextItemData("title", R.drawable.ic_launcher_background))
//        data.add(ImageTextItemData("title", R.drawable.abc_btn_check_material))
//        data.add(ImageTextItemData("title", R.drawable.ic_launcher_background))


        adapter = BaseAdapter(this, data)
        adapter.register(TextItem())
               .register(ImageItem())
               .register(ImageTextItem())

        adapter.setOnItemClickListener(this)

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
    }
}
