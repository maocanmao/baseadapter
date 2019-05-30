package com.baserecyclerview.listitem

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.baserecyclerview.itemdata.ImageTextItemData
import com.baserecyclerview.R
import com.baserecyclerview.baseadapter.ItemData
import com.baserecyclerview.baseadapter.ItemViewDelegate
import com.baserecyclerview.baseadapter.ViewHolder

class ImageTextItem:ItemViewDelegate<ItemData> {
    override fun getItemLayoutId(): Int {
       return R.layout.image_text_item
    }

    override fun isForViewType(item: ItemData, position: Int): Boolean {
        return item is ImageTextItemData
    }

    override fun bindData(holder: ViewHolder, data: ItemData, position: Int) {
        data as ImageTextItemData
        holder.getView<ImageView>(R.id.image1).setImageResource(data.resId)
        holder.getView<TextView>(R.id.text1).text = data.title
    }
}