package app.ebysofyan.itec.core.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by @ebysofyan on 6/13/19
 */

abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<BaseViewHolder>() {
    abstract var layoutResourceId : Int

    abstract fun onBindItem(holder: BaseViewHolder, data: T, position: Int)

    private val list = mutableListOf<T>()

    private var isRecyclable = false

    fun getItems(): MutableList<T> = list

    fun addItemIndexed(newT: T, index: Int = 0) {
        getItems().add(index, newT)
        notifyItemInserted(0)
    }

    fun addItem(newT: T) {
        getItems().add(newT)
        notifyDataSetChanged()
    }

    fun addItems(newList: MutableList<T>) {
        getItems().addAll(newList)
        notifyDataSetChanged()
    }

    fun removeItem(exsT: T) {
        getItems().remove(exsT)
        notifyDataSetChanged()
    }

    fun setIsRecyclable(value: Boolean) {
        isRecyclable = value
    }

    fun clear() {
        getItems().clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutResourceId, parent, false)
        return BaseViewHolder(view)
    }

    override fun getItemCount(): Int = getItems().size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        onBindItem(holder, getItems()[position], position)
        holder.setIsRecyclable(isRecyclable)
    }
}

open class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)
