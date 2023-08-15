package ir.fatemelyasi.lovely.fragments

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ir.fatemelyasi.lovely.RecyclerAdapter
import ir.fatemelyasi.lovely.StoryDataRecycler
import ir.fatemelyasi.lovely.databinding.DialogDeleteItemBinding
import ir.fatemelyasi.lovely.databinding.DialogRecyclerAddItemsBinding
import ir.fatemelyasi.lovely.databinding.DialogRecyclerUpdateItemsBinding
import ir.fatemelyasi.lovely.databinding.FragmentStoryBinding
import java.text.SimpleDateFormat
import java.util.*


class StoryFragment : Fragment(), RecyclerAdapter.DataEvents {

    private lateinit var binding: FragmentStoryBinding

    private val storyList = arrayListOf<StoryDataRecycler>()
    private lateinit var myAdapter: RecyclerAdapter

    //Variable to save the selected image
    private var selectedImageUri: Uri? = null
    private var dialogPhotoPreview: ImageView? = null

    private val calendar = Calendar.getInstance()


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

//         fill item list in recycler by dataclass
//        val storyList = arrayListOf(
//            StoryDataRecycler(
//                "12.2.1",
//                "HkI",
//                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg"
//            ),
//        )

        //----------
        //give items to adapter
        myAdapter = RecyclerAdapter(storyList, this)
        binding.Recyclerview.adapter = myAdapter
        binding.Recyclerview.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        //--------

        binding.floatingActionButtonAdd.setOnClickListener {

            val alertDialog = MaterialAlertDialogBuilder(requireContext()).create()
            val alertDialogBinding = DialogRecyclerAddItemsBinding.inflate(layoutInflater)
            alertDialog.setView(alertDialogBinding.root)
            alertDialog.setCancelable(true)
            alertDialog.show()

            alertDialogBinding.dialogDateRecycler.setOnClickListener {
                showDatePickerDialog(alertDialogBinding.dialogDateRecycler)
            }

            // Find the preview ImageView
            dialogPhotoPreview = alertDialogBinding.dialogPhotoRecycler

            alertDialogBinding.dialogPhotoRecycler.setOnClickListener {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 1)

            }

            alertDialogBinding.dialogSaveRecycler.setOnClickListener {


                if (
                    alertDialogBinding.dialogDateRecycler.length() > 0 &&
                    alertDialogBinding.dialogTitleRecycler.length() > 0
                ) {


                    val selectedDate =
                        SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)
                            .toString()
                    alertDialogBinding.dialogDateRecycler.text = selectedDate


                    val txtTitle = alertDialogBinding.dialogTitleRecycler.text.toString()

                    //preview
                    selectedImageUri?.let {
                        val imageUriString = it.toString()
                        //-------data
                        val newStoryData =
                            StoryDataRecycler(selectedDate, txtTitle, imageUriString)
                        //------ fun add to recycler
                        myAdapter.addData(newStoryData)
                    }
                } else {
                    Toast.makeText(context, "Please Fill All Filed", Toast.LENGTH_LONG).show()
                    alertDialog.dismiss()
                }
                alertDialog.dismiss()
            }


        }

    }

    //------------
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data

            // Change the preview image
            selectedImageUri?.let {
                val bitmap = getBitmapFromUri(it)
                dialogPhotoPreview?.setImageBitmap(bitmap)
            }
        }
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        return try {
            val inputStream = requireActivity().contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    //-----------
    private fun showDatePickerDialog(textView: TextView) {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateTextView(textView)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    private fun updateDateTextView(textView: TextView) {
        val selectedDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)
        textView.text = selectedDate
    }

    //-----------interface
    override fun onStoryClicked(storyDataUpdate: StoryDataRecycler, pos: Int) {

        val alertDialogUpdate = AlertDialog.Builder(requireContext()).create()
        val dialogUpdateBinding = DialogRecyclerUpdateItemsBinding.inflate(layoutInflater)

        dialogUpdateBinding.dialogTitleRecycler.setText(storyDataUpdate.title)
        dialogUpdateBinding.dialogDateRecycler.text = storyDataUpdate.date
        dialogUpdateBinding.dialogPhotoRecycler.setImageURI(storyDataUpdate.imageUri.toUri())

        alertDialogUpdate.setView(dialogUpdateBinding.root)
        alertDialogUpdate.setCancelable(true)
        alertDialogUpdate.show()

        dialogUpdateBinding.dialogCancleRecycler2.setOnClickListener {
            alertDialogUpdate.dismiss()
        }

        dialogUpdateBinding.dialogDateRecycler.setOnClickListener {
            showDatePickerDialog(dialogUpdateBinding.dialogDateRecycler)
        }

        // Find the preview ImageView
        dialogPhotoPreview = dialogUpdateBinding.dialogPhotoRecycler
        dialogUpdateBinding.dialogPhotoRecycler.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 1)

        }

        dialogUpdateBinding.dialogSaveRecycler.setOnClickListener {

            if (
                dialogUpdateBinding.dialogDateRecycler.length() > 0 &&
                dialogUpdateBinding.dialogTitleRecycler.length() > 0 &&
                dialogUpdateBinding.dialogPhotoRecycler.drawable != null
            ) {
                val selectedDate =
                    SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time).toString()
                dialogUpdateBinding.dialogDateRecycler.text = selectedDate

                val txtTitle = dialogUpdateBinding.dialogTitleRecycler.text.toString()

                //preview
                selectedImageUri.let {
                    val imageUriString = it.toString()
                    //-------data
                    val newStoryData = StoryDataRecycler(selectedDate, txtTitle, imageUriString)
                    //------ fun update to recycler
                    myAdapter.updateData(newStoryData, pos)

                }
                alertDialogUpdate.dismiss()

            } else {
                Toast.makeText(context, "Please Fill All Filed", Toast.LENGTH_LONG).show()
                alertDialogUpdate.dismiss()
            }

        }
    }

    override fun onStoryLongClicked(storyData: StoryDataRecycler, pos: Int) {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val dialogDeleteBinding = DialogDeleteItemBinding.inflate(layoutInflater)
        dialog.setView(dialogDeleteBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        dialogDeleteBinding.dialogBtnDeleteCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialogDeleteBinding.dialogBtnDeleteSure.setOnClickListener {

            dialog.dismiss()
            myAdapter.removeData(storyData, pos)
            Toast.makeText(context, "removed Data", Toast.LENGTH_LONG).show()

        }


    }
    //-----------
}

