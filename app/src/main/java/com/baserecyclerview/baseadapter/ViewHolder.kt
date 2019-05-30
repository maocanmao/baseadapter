package com.baserecyclerview.baseadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ViewHolder(private val mViews: SparseArray<View>, private val mConvertView: View, private val mContext: Context) :
    RecyclerView.ViewHolder(mConvertView) {

    constructor(context: Context, convertView: View) : this(SparseArray(), convertView, context)

    companion object {
        fun createViewHolder(context: Context, itemView: View): ViewHolder {
            return ViewHolder(context, itemView)
        }

        fun createViewHolder(context: Context, parent: ViewGroup, layoutId: Int): ViewHolder {
            val itemView = LayoutInflater.from(context).inflate(layoutId, parent, false)
            return ViewHolder(context, itemView)
        }
    }

    fun <T : View> getView(viewId: Int): T {
        var view = mViews.get(viewId)
        if (null == view) {
            view = mConvertView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as T
    }

    fun getConvertView(): View {
        return mConvertView
    }

    fun getContext(): Context {
        return mContext
    }
}