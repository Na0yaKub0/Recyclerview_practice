package com.example.recyclerviewpractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

interface adpter{
    fun update()
}

class TestAdapter(private val List: ArrayList<Listdata>,private val listener: adpter): RecyclerView.Adapter<TestAdapter.ViewHolder>() {

    // Viewの初期化
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val check: TextView
        val name: Button
        val del: Button

        init {
            check = view.findViewById(R.id.textViewCheck)
            name = view.findViewById(R.id.buttonName)
            del = view.findViewById(R.id.buttonDel)
        }
    }

    // レイアウトの設定
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.testcell, viewGroup, false)
        //ビューの高さ
        view.layoutParams.height=150
        return ViewHolder(view)
    }

    // Viewの設定
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name.text=List[position].name

        if (List[position].check==false){
            viewHolder.check.setAlpha(0.0F)
        }else if(List[position].check==true){
            viewHolder.check.setAlpha(1.0F)
        }

        viewHolder.name.setOnClickListener{
            if (List[position].check==false){
                List[position].check=true
            }else {
                List[position].check=false
            }
            this.notifyDataSetChanged()
            listener.update()
            }

        viewHolder.del.setOnClickListener{
            List.removeAt(position)
            this.notifyDataSetChanged()
            listener.update()
        }

    }

    // 表示数を返す
    override fun getItemCount() = List.size
}