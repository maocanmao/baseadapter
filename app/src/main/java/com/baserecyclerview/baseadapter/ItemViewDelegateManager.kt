package com.baserecyclerview.baseadapter

import android.support.v4.util.SparseArrayCompat


class ItemViewDelegateManager<T : ItemData> {
    private var mDelegateList: SparseArrayCompat<ItemViewDelegate<T>> = SparseArrayCompat()

    fun getItemViewDelegateCount(): Int {
        return mDelegateList.size()
    }

    fun addDelegate(delegate: ItemViewDelegate<T>?): ItemViewDelegateManager<T> {
        val viewType = mDelegateList.size()
        delegate?.let {
            mDelegateList.put(viewType, delegate)
        }

        return this
    }

    fun addDelegate(viewType: Int, delegate: ItemViewDelegate<T>): ItemViewDelegateManager<T> {
        if (mDelegateList.get(viewType) != null) {
            throw IllegalArgumentException(
                "An ItemViewDelegate is already registered for the viewType = "
                        + viewType
                        + ". Already registered ItemViewDelegate is "
                        + mDelegateList.get(viewType)
            )
        }
        mDelegateList.put(viewType, delegate)
        return this
    }

    fun removeDelegate(delegate: ItemViewDelegate<T>?): ItemViewDelegateManager<T> {
        if (delegate == null) {
            throw NullPointerException("ItemViewDelegate is null")
        }
        val indexToRemove = mDelegateList.indexOfValue(delegate)

        if (indexToRemove >= 0) {
            mDelegateList.removeAt(indexToRemove)
        }
        return this
    }

    fun removeDelegate(itemType: Int): ItemViewDelegateManager<T> {
        val indexToRemove = mDelegateList.indexOfKey(itemType)

        if (indexToRemove >= 0) {
            mDelegateList.removeAt(indexToRemove)
        }
        return this
    }

    fun getItemViewType(item: T, position: Int): Int {

        val mDelegateListCount = mDelegateList.size()
        for (i in mDelegateListCount - 1 downTo 0) {
            val delegate = mDelegateList.valueAt(i)
            if (delegate.isForViewType(item, position)) {
                return mDelegateList.keyAt(i)
            }
        }
        throw IllegalArgumentException(
            "No ItemViewDelegate added that matches position=$position in data source"
        )
    }

    fun convert(holder: ViewHolder, item: T, position: Int) {
        val mDelegateListCount = mDelegateList.size()
        for (i in 0 until mDelegateListCount) {
            val delegate = mDelegateList.valueAt(i)

            if (delegate.isForViewType(item, position)) {
                delegate.bindData(holder, item, position)
                return
            }
        }
        throw IllegalArgumentException(
            "No ItemViewDelegateManager added that matches position=$position in data source"
        )
    }


    fun getItemViewDelegate(viewType: Int): ItemViewDelegate<T>? {
        return mDelegateList.get(viewType)
    }

    fun getItemViewLayoutId(viewType: Int): Int {
        return getItemViewDelegate(viewType)!!.getItemLayoutId()
    }

    fun getItemViewType(itemViewDelegate: ItemViewDelegate<T>): Int {
        return mDelegateList.indexOfValue(itemViewDelegate)
    }
}