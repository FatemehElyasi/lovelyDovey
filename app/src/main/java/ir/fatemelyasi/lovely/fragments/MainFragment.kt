package ir.fatemelyasi.lovely.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import ir.fatemelyasi.lovely.R
import ir.fatemelyasi.lovely.databinding.FragmentMainBinding
import kotlinx.coroutines.launch
import java.util.*


class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    // initialization to select the date as the current date
    private var selectedDate: Long = 0
    // We save the selected date in the calendar
    private val calendar = Calendar.getInstance()

    val PICK_IMAGE_REQUEST_GIRL = 1
    val PICK_IMAGE_REQUEST_BOY = 2

    //SharedPreferences
    private lateinit var sharedPreferences: SharedPreferences

    private val PREFS_KEY_SELECTED_IMAGE_URI_GIRL = "selected_image_uri_GIRL"
    private val PREFS_KEY_SELECTED_IMAGE_URI_BOY = "selected_image_uri_BOY"
    private val PREFS_KEY_SELECTED_DATE = "DATE"

    //permissions
    private val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123


    //Update imageView
    private var selectedImageUriGirl: Uri? = null
    private var selectedImageUriBoy: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //-----------------------------------------------------------------datePickerDialog

        sharedPreferences = requireActivity().getPreferences(Activity.MODE_PRIVATE)
        selectedDate = sharedPreferences.getLong(PREFS_KEY_SELECTED_DATE, selectedDate)

        // If the selected date is null (initial value), we set it equal to the current date
        if (selectedDate == 0L) {
            selectedDate = System.currentTimeMillis()
            sharedPreferences.edit().putLong(PREFS_KEY_SELECTED_DATE, selectedDate).apply()
        }

        // Set the selected date to display in the TextView
        binding.txtper.text = daysPassedText(selectedDate)

        binding.txtper.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, monthOfYear, dayOfMonth ->
                    // Set the selected date in the calendar
                    calendar.set(year, monthOfYear, dayOfMonth)

                    // Display the number of past days to the user
                    selectedDate = calendar.timeInMillis
                    sharedPreferences.edit().putLong(
                        PREFS_KEY_SELECTED_DATE,
                        selectedDate
                    ).apply()

                    // Display the number of past days to the user
                    binding.txtper.text = daysPassedText(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            // Display the date selection dialog
            datePickerDialog.show()
        }
        //-----------------------------------------------------------------AnimationUtils
        val heartAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.animate)
        binding.btnSendMassage.startAnimation(heartAnimation)
        //-----------------------------------------------------------------
        binding.cardview.cardElevation = 0f

        binding.cardview.setCardBackgroundColor(android.R.color.transparent)
        //-----------------------------------------------------------------add pic from gallery


        sharedPreferences = requireActivity().getPreferences(Activity.MODE_PRIVATE)

        // Load the saved image URI from SharedPreferences
        val savedImageUriStringGirl =
            sharedPreferences.getString(PREFS_KEY_SELECTED_IMAGE_URI_GIRL, null)
        selectedImageUriGirl = savedImageUriStringGirl?.let { Uri.parse(it) }

        // Load the saved image URI from SharedPreferences
        val savedImageUriStringBoy =
            sharedPreferences.getString(PREFS_KEY_SELECTED_IMAGE_URI_BOY, null)
        selectedImageUriBoy = savedImageUriStringBoy?.let { Uri.parse(it) }


        // Display the image if it exists girl
        selectedImageUriGirl?.let {
            Glide.with(this)
                .load(it)
                .into(binding.circularImageViewgirl)
        }
        // Display the image if it exists boy
        selectedImageUriBoy?.let {
            Glide.with(this)
                .load(it)
                .into(binding.circularImageViewboy)
        }
        // Check and request external storage permission
        checkPermissionREAD_EXTERNAL_STORAGE(requireContext())

        binding.circularImageViewgirl.setOnClickListener {
            openGalleryForGirl()
        }
        binding.circularImageViewboy.setOnClickListener {
            openGalleryForBoy()
        }
    }

    private fun openGalleryForGirl() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST_GIRL)
    }

    private fun openGalleryForBoy() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST_BOY)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PICK_IMAGE_REQUEST_GIRL -> {
                    data?.data?.let { uri ->
                        selectedImageUriGirl = uri
                        updateImageViewGirl()
                        sharedPreferences.edit().putString(
                            PREFS_KEY_SELECTED_IMAGE_URI_GIRL,
                            selectedImageUriGirl?.toString()
                        ).apply()

                        // Check and request external storage permission
                        checkPermissionREAD_EXTERNAL_STORAGE(requireContext())
                    }
                }
                PICK_IMAGE_REQUEST_BOY -> {
                    data?.data?.let { uri ->
                        selectedImageUriBoy = uri
                        updateImageViewBoy()
                        sharedPreferences.edit().putString(
                            PREFS_KEY_SELECTED_IMAGE_URI_BOY,
                            selectedImageUriBoy?.toString()
                        ).apply()

                        // Check and request external storage permission
                        checkPermissionREAD_EXTERNAL_STORAGE(requireContext())
                    }
                }
            }
        }
    }

    private fun updateImageViewGirl() {
        lifecycleScope.launch {
            val bitmap = loadBitmapFromUri(selectedImageUriGirl)
            if (bitmap != null) {
                Glide.with(this@MainFragment)
                    .load(bitmap)
                    .into(binding.circularImageViewgirl)
            }
        }
    }

    private fun updateImageViewBoy() {
        lifecycleScope.launch {
            val bitmap = loadBitmapFromUri(selectedImageUriBoy)
            if (bitmap != null) {
                Glide.with(this@MainFragment)
                    .load(bitmap)
                    .into(binding.circularImageViewboy)
            }
        }
    }

    private fun loadBitmapFromUri(uri: Uri?): Bitmap? {
        uri?.let {
            return try {
                requireContext().contentResolver.openInputStream(it)?.use { inputStream ->
                    return@use Bitmap.createBitmap(
                        MediaStore.Images.Media.getBitmap(
                            requireContext().contentResolver,
                            it
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
        return null
    }

    override fun onPause() {
        super.onPause()
        // Save the selected image URIs to SharedPreferences when the Fragment is paused
        sharedPreferences.edit()
            .putString(PREFS_KEY_SELECTED_IMAGE_URI_GIRL, selectedImageUriGirl?.toString()).apply()
        sharedPreferences.edit()
            .putString(PREFS_KEY_SELECTED_IMAGE_URI_BOY, selectedImageUriBoy?.toString()).apply()
    }

    //-----------------------checkPermission
    private fun checkPermissionREAD_EXTERNAL_STORAGE(context: Context) {
        val currentAPIVersion = Build.VERSION.SDK_INT
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        requireActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    // Show custom explanation dialog
                    showPermissionExplanationDialog()
                } else {
                    // Request permission directly
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
                    )
                }
            }
        }
    }

    private fun showPermissionExplanationDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setMessage("We need access to your external storage to proceed.")
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            // Request permission directly
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
            )
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            // Handle cancellation, if needed
            dialog.dismiss()
        }
        alertDialogBuilder.setCancelable(false)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}

//---------------------------------------------------daysPassedText
private fun daysPassedText(selectedDate: Long): String {
    val currentDate = Calendar.getInstance().timeInMillis
    val daysPassed = (currentDate - selectedDate) / (24 * 60 * 60 * 1000)
    return "$daysPassed"
}
//TO DO ->
//shared preference
//notes
//safe arg depend
//change theme
//add note pa
//change progr /
//firebase