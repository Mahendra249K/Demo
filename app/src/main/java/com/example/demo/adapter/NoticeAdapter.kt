package com.example.demo.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.model.EmailModel

class NoticeAdapter(
    val context: Context,
    val arrayList: ArrayList<EmailModel>,
    val clickListner: onNoticeClickListner?
) :
    RecyclerView.Adapter<NoticeAdapter.NoticeVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.raw_notice, parent, false)
        return NoticeVH(view)
    }

    override fun onBindViewHolder(holder: NoticeVH, position: Int) {
        val model = arrayList[position]

        holder.tvDate.text = model.msgEntryDate
        holder.tvTitle.text = model.msgSubject
        holder.tvDescription.text = model.msgText

        if (TextUtils.isEmpty(model.msgAttachment) || model.msgAttachment.equals("NA")) {
            holder.ivDownload.visibility = View.GONE
        } else {
            holder.ivDownload.visibility = View.VISIBLE
        }
    }

    fun addDataList(isClearAll: Boolean, mArrayList: ArrayList<EmailModel>?) {
        if (isClearAll)
            arrayList.clear()
        arrayList.addAll(mArrayList!!)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class NoticeVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val ivDownload: ImageView = itemView.findViewById(R.id.ivDownload)

        init {

            itemView.setOnClickListener {
                if (tvTitle.maxLines == 50) {
                    tvTitle.maxLines = 2
                    tvDescription.maxLines = 3
                } else {
                    tvTitle.maxLines = 50
                    tvDescription.maxLines = 1000
                }
            }

            ivDownload.setOnClickListener {
                clickListner?.onNoticeDownloadClick(arrayList.get(layoutPosition))
            }
        }
    }

    interface onNoticeClickListner {
        fun onNoticeDownloadClick(model: EmailModel)
    }

}
