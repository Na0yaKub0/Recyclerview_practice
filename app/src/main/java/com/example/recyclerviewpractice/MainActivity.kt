package com.example.recyclerviewpractice

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),adpter {
    var list = ArrayList<Listdata>()
    lateinit var recyclerView: RecyclerView
    lateinit var buttonTuika:Button
    lateinit var text:TextView
    var cnt:Int=1

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        buttonTuika=findViewById(R.id.buttonTuika)
        text=findViewById(R.id.textView2)

        recyclerView.layoutManager=LinearLayoutManager(this)
        var adapter = TestAdapter(list,this)

        val itemDecoration = DividerItemDecoration(applicationContext,DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(applicationContext.getDrawable(R.drawable.divider)!!)
        recyclerView.addItemDecoration(itemDecoration)

        recyclerView.adapter=adapter

        textprocess()


        buttonTuika.setOnClickListener{
            val add= Listdata()
            add.name=cnt.toString()
            list.add(add)
            cnt+=1
            adapter.notifyDataSetChanged()
            textprocess()
        }


    }

    fun textprocess(){
        var per:String="0"
        if (list.size!=0){
            per = (100 * list.filter { it.check == true }.size / list.size).toString()
        }
        text.text=per
    }

    override fun update() {
        textprocess()
    }
}