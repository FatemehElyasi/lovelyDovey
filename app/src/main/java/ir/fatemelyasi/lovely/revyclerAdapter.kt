package ir.fatemelyasi.lovely

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class RecyclerAdapter(private val data: ArrayList<StoryDataRecycler>) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerviewHolder>() {


    inner class RecyclerviewHolder(val context: Context, itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val txtDaterRecycler = itemView.findViewById<TextView>(R.id.txtDateRecycler)
        private val txtTitleRecycler = itemView.findViewById<TextView>(R.id.txtTitleRecycler)
        private val imageViewStoryRecycler = itemView.findViewById<ImageView>(R.id.imageViewStoryRecycler)

        @SuppressLint("CheckResult")
        fun bindData(position: Int) {
            txtDaterRecycler.text = data[position].date
            txtTitleRecycler.text = data[position].title

            Glide
                .with(context)
                .load(data[position].imageUri)
                .transform(RoundedCornersTransformation(16, 4))
                .into(imageViewStoryRecycler)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerviewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_items, parent, false)
        return RecyclerviewHolder(parent.context, view)

    }

    override fun onBindViewHolder(holder: RecyclerviewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}