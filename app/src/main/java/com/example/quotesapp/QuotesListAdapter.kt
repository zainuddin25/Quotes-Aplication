package com.example.quotesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuotesListAdapter(val context: Context, val list: List<QuotesResponse>, val listener: CopyListener)
    : RecyclerView.Adapter<QuotesViewHolder> ()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_quotes, parent, false)
        return QuotesViewHolder(layout)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        holder.textView_quote.text = list.get(position).text
        holder.textView_author.text = list.get(position).author
        holder.button_copy.setOnClickListener{
            listener.onCopyClicked(list.get(holder.adapterPosition).text)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class QuotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView_quote : TextView = itemView.findViewById(R.id.textView_quote)
    var textView_author : TextView = itemView.findViewById(R.id.textView_author)
    var button_copy : Button = itemView.findViewById(R.id.button_copy)
}