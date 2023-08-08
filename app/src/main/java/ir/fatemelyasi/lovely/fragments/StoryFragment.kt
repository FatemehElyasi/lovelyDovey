package ir.fatemelyasi.lovely.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ir.fatemelyasi.lovely.RecyclerAdapter
import ir.fatemelyasi.lovely.StoryDataRecycler
import ir.fatemelyasi.lovely.databinding.DialogRecyclerAddItemsBinding
import ir.fatemelyasi.lovely.databinding.FragmentStoryBinding
import java.util.*


class StoryFragment : Fragment() {
    private lateinit var binding: FragmentStoryBinding

    // کدی که وقتی روی تکست‌اینپوت کلیک می‌کند، دیالوگ انتخاب زمان نمایش می‌دهد
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoryBinding.inflate(inflater, container, false)

        return (binding.root)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // fill item list in recycler by dataclass
        val storyList = arrayListOf(
            StoryDataRecycler(
                "12.2.1",
                "HkI",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg"
            ),
            StoryDataRecycler(
                "12.2.1",
                "HkI",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg"
            ),
            StoryDataRecycler(
                "12.2.1",
                "HkI",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg"
            ),
            StoryDataRecycler(
                "12.2.1",
                "HkI",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg"
            ),
            StoryDataRecycler(
                "12.2.1",
                "HkI",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg"
            ),
            StoryDataRecycler(
                "12.2.1",
                "HkI",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg"
            ),
            StoryDataRecycler(
                "12.2.2",
                "HjbhvI",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg"
            ),
            StoryDataRecycler(
                "12.2.3",
                "HI",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg"
            ),
            StoryDataRecycler(
                "12.2.4",
                "HIhello",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg"
            ),
            StoryDataRecycler(
                "12.2.5",
                "HIhello",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg"
            ),
        )

        //give items to adapter
        val myAdapter = RecyclerAdapter(storyList)

        binding.Recyclerview.adapter = myAdapter
        binding.Recyclerview.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        //--------

        binding.floatingActionButtonAdd.setOnClickListener {

            val alertDialog = MaterialAlertDialogBuilder(requireContext())

            val alertDialogBinding = DialogRecyclerAddItemsBinding.inflate(layoutInflater)

            alertDialog.setView(alertDialogBinding.root)
            alertDialog.setCancelable(true)
            alertDialog.create()
            alertDialog.show()


            alertDialogBinding.dialogDateRecycler.setOnClickListener {

            }
            alertDialogBinding.dialogPhotoRecycler.setOnClickListener {

            }

            alertDialogBinding.dialogSaveRecycler.setOnClickListener {

                val txtTitle = alertDialogBinding.dialogTitleRecycler.text.toString()

            }

        }
    }

}
