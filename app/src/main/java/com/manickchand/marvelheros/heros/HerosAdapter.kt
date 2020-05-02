package com.manickchand.marvelheros.heros

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manickchand.marvelheros.R
import com.manickchand.marvelheros.data.model.hero.Hero
import com.manickchand.marvelheros.data.util.getUrlImage
import com.manickchand.marvelheros.data.util.loadImageView
import kotlinx.android.synthetic.main.item_heros.view.*

class HerosAdapter(context: Context,
                   list: List<Hero>,
                    val onItemClickListener:((hero:Hero) -> Unit) ) : RecyclerView.Adapter<HerosAdapter.MyViewHolder?>() {

    private var mContext =context
    private var mList = list
    private var mlayoutInflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater //
    private lateinit var mView: View
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        mView = mlayoutInflater.inflate(R.layout.item_heros,parent,false)
        return MyViewHolder(mView,onItemClickListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindHero(mList[position])
        setAnimation(holder.itemView, position)
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation: Animation =
                AnimationUtils.loadAnimation(mContext, android.R.anim.fade_in)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    override fun getItemCount() = mList.count()

    inner class MyViewHolder(itemView:View,
                             private val onItemClickListener: ((hero: Hero) -> Unit)) :RecyclerView.ViewHolder(itemView){

        private var ivHero: ImageView = itemView.iv_hero
        private var tvTitle: TextView = itemView.tv_hero_name

        fun bindHero(hero: Hero) {

            val urlImg = getUrlImage(hero.thumbnail?.path ?: "", hero.thumbnail?.extension ?: "", "portrait_uncanny")

            try {
                loadImageView(ivHero,urlImg)
            }catch (e:Exception){
                e.stackTrace
            }
            tvTitle.text = hero.name

            itemView.setOnClickListener{
                onItemClickListener.invoke(hero)
            }
        }

    }
}