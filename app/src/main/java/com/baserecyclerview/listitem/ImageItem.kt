package com.baserecyclerview.listitem

import android.widget.ImageView
import com.baserecyclerview.itemdata.ImageItemData
import com.baserecyclerview.R
import com.baserecyclerview.baseadapter.ItemData
import com.baserecyclerview.baseadapter.ItemViewDelegate
import com.baserecyclerview.baseadapter.ViewHolder

class ImageItem : ItemViewDelegate<ItemData> {
    override fun isForViewType(item: ItemData, position: Int): Boolean {
        return item is ImageItemData
    }


    override fun getItemLayoutId(): Int {
        return R.layout.image_item
    }


    override fun bindData(holder: ViewHolder, data: ItemData, position: Int) {
        data as ImageItemData
        holder.getView<ImageView>(R.id.image).setImageResource(data.resId)
    }
}