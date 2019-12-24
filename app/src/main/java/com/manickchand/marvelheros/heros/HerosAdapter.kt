package com.manickchand.marvelheros.heros

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manickchand.marvelheros.R
import com.manickchand.marvelheros.data.model.hero.Hero
import kotlinx.android.synthetic.main.item_heros.view.*

class HerosAdapter(context: Context,
                   list: List<Hero>) : RecyclerView.Adapter<HerosAdapter.MyViewHolder?>() {

    var mContext =context
    var mList = list
    var mlayoutInflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater //
    //var mReciclerViewOnClickListenerHack: RecyclerViewOnClickListenerHack? = null // interface de click
    lateinit var view: View
    private var lastPosition = -1

    //infla view da linha
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        view = mlayoutInflater.inflate(R.layout.item_heros,parent,false)
        return MyViewHolder(view)
    }

    // seta dados de cada linha
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        //tenta carregar img
//        try {
//            Picasso.get().load(mList.get(position).image)
//                .placeholder(R.drawable.placeholder)
//                .error(R.drawable.placeholder)
//                .into(holder.ivEvent)
//        }catch (e:Exception){
//            e.stackTrace
//        }
//        holder.tvTitle.text = mList.get(position).title

        //setAnimation(holder.itemView, position)
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
//        if (position > lastPosition) {
//            val animation: Animation =
//                AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left)
//            viewToAnimate.startAnimation(animation)
//            lastPosition = position
//        }
    }

    override fun getItemCount() = mList.count()

//    fun setReciclerViewOnClickListenerHack(r: RecyclerViewOnClickListenerHack) {
//        this.mReciclerViewOnClickListenerHack = r
//    }

    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val ivHero: ImageView
        var tvTitle: TextView

        init {
            //itemView.setOnClickListener(this)
            ivHero = itemView.iv_hero
            tvTitle = itemView.tv_hero_name
        }

         //clickListener de cada posicao do adapter
//        override fun onClick(v: View?) {
//            if (mReciclerViewOnClickListenerHack != null) {
//                mReciclerViewOnClickListenerHack!!.onClickListener(v!!, adapterPosition)
//            }
//        }
    }
}