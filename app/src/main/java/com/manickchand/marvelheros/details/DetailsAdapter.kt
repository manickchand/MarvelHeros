package com.manickchand.marvelheros.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manickchand.marvelheros.R
import com.manickchand.marvelheros.data.model.hero.ItemComics
import kotlinx.android.synthetic.main.item_detail_rv.view.*

class DetailsAdapter(context: Context,
                   list: List<ItemComics>) : RecyclerView.Adapter<DetailsAdapter.MyViewHolder?>() {

    private var mContext =context
    private var mList = list
    private var mlayoutInflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater //
    private lateinit var mView: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        mView = mlayoutInflater.inflate(R.layout.item_detail_rv,parent,false)
        return MyViewHolder(mView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindLine(mList[position])
    }

    override fun getItemCount() = mList.count()

    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        private var tvLine:TextView = itemView.tv_line

        fun bindLine(line: ItemComics) {
            tvLine.text = line.name
        }
    }
}