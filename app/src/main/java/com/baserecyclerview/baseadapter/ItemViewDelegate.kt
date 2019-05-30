package com.baserecyclerview.baseadapter

interface ItemViewDelegate<in T> {
    fun getItemLayoutId(): Int

    fun isForViewType(item: T, position: Int): Boolean

    fun bindData(holder: ViewHolder, data: T, position: Int)

}