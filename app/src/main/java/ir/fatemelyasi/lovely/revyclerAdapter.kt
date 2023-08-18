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
import ir.fatemelyasi.lovely.local.StoryDataRecycler
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class RecyclerAdapter(private val data: ArrayList<StoryDataRecycler>, private val dataEvents: DataEvents) : RecyclerView.Adapter<RecyclerAdapter.StoryViewHolder>() {

    inner class StoryViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {


        private val txtDaterRecycler = itemView.findViewById<TextView>(R.id.txtDateRecycler)
        private val txtTitleRecycler = itemView.findViewById<TextView>(R.id.txtTitleRecycler)
        private val imageViewStoryRecycler =
            itemView.findViewById<ImageView>(R.id.imageViewStoryRecycler)


            @SuppressLint("CheckResult")
            fun bindData(position: Int) {
                txtDaterRecycler.text = data[position].date
                txtTitleRecycler.text = data[position].title


                Glide
                    .with(context)
                    .load(data[position].imageUri)
                    .transform(RoundedCornersTransformation(16, 4))
                    .into(imageViewStoryRecycler)

                itemView.setOnClickListener {

                    dataEvents.onStoryClicked(data[adapterPosition],adapterPosition)

                }

                itemView.setOnLongClickListener {

                    dataEvents.onStoryLongClicked(data[adapterPosition], adapterPosition)

                    true
                }

            }

        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {

            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_items, parent, false)
            return StoryViewHolder(view, parent.context)
        }


        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
            holder.bindData(position)
        }
//-----------------------------------------------------------------
    fun removeData(oldData: StoryDataRecycler, oldPosition: Int) {

        // remove item from list :
        data.remove(oldData)
        notifyItemRemoved(oldPosition)

    }
    fun addData(newData: StoryDataRecycler) {

        // add food to list :
        data.add(0, newData)
        notifyItemInserted(0)

    }
    fun updateData(UpdateData: StoryDataRecycler, position: Int) {

        // add food to list :
        data[position] = UpdateData
        notifyItemChanged( position )
    }

    interface DataEvents {

        // 1. create interface in adapter
        // 2. get an object  of interface in args of adapter
        // 3. fill (call) object of interface with your data
        // 4. implementation in MainActivity

        fun onStoryClicked(storyDataUpdate: StoryDataRecycler, oldPosition: Int)
        fun onStoryLongClicked(storyData: StoryDataRecycler, pos: Int)

    }
}