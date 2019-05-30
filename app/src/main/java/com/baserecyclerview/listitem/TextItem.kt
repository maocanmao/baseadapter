package com.baserecyclerview.listitem

import android.widget.TextView
import com.baserecyclerview.R
import com.baserecyclerview.itemdata.TextItemData
import com.baserecyclerview.baseadapter.ItemData
import com.baserecyclerview.baseadapter.ItemViewDelegate
import com.baserecyclerview.baseadapter.ViewHolder

class TextItem : ItemViewDelegate<ItemData> {
    override fun isForViewType(item: ItemData, position: Int): Boolean {
        return item is TextItemData
    }

    override fun getItemLayoutId(): Int {
        return R.layout.text_item
    }



    override fun bindData(holder: ViewHolder, t: ItemData, position: Int) {
        t as TextItemData
        holder.getView<TextView>(R.id.text).text = t.text
    }
}