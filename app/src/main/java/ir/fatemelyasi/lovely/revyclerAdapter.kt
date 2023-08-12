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

class RecyclerAdapter(private val data: ArrayList<StoryDataRecycler>, private val foodEvents: FoodEvents) : RecyclerView.Adapter<RecyclerAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(itemView: View, private val context: Context) :
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

                    foodEvents.onFoodClicked()

                }

                itemView.setOnLongClickListener {

                    foodEvents.onFoodLongClicked(data[adapterPosition], adapterPosition)

                    true
                }

            }

        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {

            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_items, parent, false)
            return FoodViewHolder(view, parent.context)
        }


        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
            holder.bindData(position)
        }

    fun removeFood(oldFood: StoryDataRecycler, oldPosition: Int) {

        // remove item from list :
        data.remove(oldFood)
        notifyItemRemoved(oldPosition)

    }
    fun addData(newFood: StoryDataRecycler) {

        // add food to list :
        data.add(0, newFood)
        notifyItemInserted(0)

    }

    interface FoodEvents {

        // 1. create interface in adapter
        // 2. get an object  of interface in args of adapter
        // 3. fill (call) object of interface with your data
        // 4. implementation in MainActivity

        fun onFoodClicked()
        fun onFoodLongClicked(food: StoryDataRecycler, pos: Int)

    }
}