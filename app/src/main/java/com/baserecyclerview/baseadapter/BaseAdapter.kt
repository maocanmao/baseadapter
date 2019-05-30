package com.baserecyclerview.baseadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

open class BaseAdapter<T : ItemData>(
    private val mContext: Context,
    private val mData: List<T>,
    private var mItemViewDelegateManager: ItemViewDelegateManager<T> = ItemViewDelegateManager()
) : RecyclerView.Adapter<ViewHolder>() {
    private var mOnItemClickListener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val delegate = mItemViewDelegateManager.getItemViewDelegate(viewType)
        val layoutId: Int = delegate?.getItemLayoutId()!!
        val holder = ViewHolder.createViewHolder(mContext, parent, layoutId)
        onViewHolderCreated(holder, holder.getConvertView())
        setListener(parent, holder, viewType)
        return holder
    }

    override fun getItemViewType(position: Int): Int {
        return if (mItemViewDelegateManager.getItemViewDelegateCount() <= 0) {
            super.getItemViewType(position)
        } else {
            mItemViewDelegateManager.getItemViewType(mData[position], position)
        }
    }

    fun onViewHolderCreated(holder: ViewHolder, itemView: View) {

    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bindData(holder, mData[position])
    }

    private fun bindData(viewHolder: ViewHolder, t: T) {
        mItemViewDelegateManager.convert(viewHolder, t, viewHolder.adapterPosition)
    }

    private fun setListener(parent: ViewGroup, viewHolder: ViewHolder, viewType: Int) {
        viewHolder.getConvertView().setOnClickListener {
            if (null != mOnItemClickListener) {
                val position: Int = viewHolder.adapterPosition
                mOnItemClickListener!!.onItemClick(it, viewHolder, position)
            }
        }

        if (viewHolder.getConvertView() is ViewGroup) {
            val childCount = (viewHolder.getConvertView() as ViewGroup).childCount
            for (i in 0 until childCount) {
                (viewHolder.getConvertView() as ViewGroup).getChildAt(i).setOnClickListener {
                    if (null != mOnItemClickListener) {
                        val position: Int = viewHolder.adapterPosition
                        mOnItemClickListener!!.onItemClick(it, viewHolder, position)
                    }
                }
            }
        }

        viewHolder.getConvertView().setOnLongClickListener {
            if (null != mOnItemClickListener) {
                val position: Int = viewHolder.adapterPosition
                return@setOnLongClickListener mOnItemClickListener!!.onItemLongClick(it, viewHolder, position)
            }
            return@setOnLongClickListener false
        }

    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

    fun register(itemViewDelegate: ItemViewDelegate<T>): BaseAdapter<T> {
        mItemViewDelegateManager.addDelegate(itemViewDelegate)
        return this
    }

    fun register(viewType: Int, itemViewDelegate: ItemViewDelegate<T>): BaseAdapter<T> {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate)
        return this
    }

    fun getItemData(position: Int): ItemData {
        return mData[position]
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, holder: RecyclerView.ViewHolder, position: Int)

        fun onItemLongClick(view: View, holder: RecyclerView.ViewHolder, position: Int): Boolean
    }
}