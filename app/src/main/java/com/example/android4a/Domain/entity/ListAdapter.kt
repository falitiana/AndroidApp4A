package com.example.android4a.Domain.entity

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private var values: List<Char>? = null
    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(item: Char?)
    }

    inner class ViewHolder(var layout: View) : RecyclerView.ViewHolder(
        layout
    ) {
        // each data item is just a string in this case
        var txtHeader: TextView
        var txtFooter: TextView

        init {
            txtHeader = layout.findViewById<View>(R.id.title) as TextView
            txtFooter = layout.findViewById<View>(R.id.text1) as TextView
        }
    }

    fun add(position: Int, item: Char) {
        values.add(position, item)
        notifyItemInserted(position)
    }

    fun remove(position: Int) {
        values.removeAt(position)
        notifyItemRemoved(position)
    }

    constructor(myDataset: List<Char>?, listener: OnItemClickListener?) {
        values = myDataset
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v: View = inflater.inflate(R.layout.row_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCharacter = values!![position]
        holder.txtFooter.setText(currentCharacter.getId())
        holder.txtFooter.text = Character.getName()
        holder.txtFooter.setText(currentCharacter.getStatus())
        holder.txtFooter.setText(currentCharacter.getSpecies())
        holder.txtFooter.setText(currentCharacter.getType())
        holder.txtFooter.setText(currentCharacter.getGender())
        holder.txtFooter.text = currentCharacter.getOrigin() as CharSequence?
        holder.txtFooter.text = currentCharacter.getLocation() as CharSequence?
        holder.txtFooter.setText(currentCharacter.getImage())
        holder.txtFooter.text = currentCharacter.getEpisode() as CharSequence?
        holder.txtFooter.setText(currentCharacter.getUrl())
        holder.txtFooter.setText(currentCharacter.getCreated())
        holder.itemView.setOnClickListener { listener!!.onItemClick(currentCharacter) }
    }

    override fun getItemCount(): Int {
        return values!!.size
    }
}
