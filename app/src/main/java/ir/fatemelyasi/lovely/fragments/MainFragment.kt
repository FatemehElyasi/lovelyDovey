package ir.fatemelyasi.lovely.fragments

import android.app.DatePickerDialog
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ir.fatemelyasi.lovely.Manifest
import ir.fatemelyasi.lovely.R
import ir.fatemelyasi.lovely.databinding.FragmentMainBinding
import java.util.*


class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    private val calendar = Calendar.getInstance()
    private val currentDate = Calendar.getInstance()

    private val PERMISSION_REQUEST_CODE = 100


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //-----------------------------------------------------------------datePickerDialog
        binding.txtper.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, monthOfYear, dayOfMonth ->
                    // تنظیم تاریخ انتخاب شده در تقویم
                    calendar.set(year, monthOfYear, dayOfMonth)

                    // محاسبه تعداد روزهای گذشته از زم
                    // ان انتخاب شده
                    val daysPassed =
                        (currentDate.timeInMillis - calendar.timeInMillis) / (24 * 60 * 60 * 1000)
                    // نمایش تعداد روزهای گذشته به کاربر

                    binding.txtper.text = "$daysPassed"
                },
                currentDate.get(Calendar.YEAR),
                currentDate.get(Calendar.MONTH),
                currentDate.get(Calendar.DAY_OF_MONTH)
            )

// نمایش دیالوگ انتخاب تاریخ
            datePickerDialog.show()

        }
        //-----------------------------------------------------------------AnimationUtils
        val heartAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.animate)
        binding.btnSendMassage.startAnimation(heartAnimation)
        //-----------------------------------------------------------------



    }

}
//shared prefence
//notes
//safe arg depend
//change theme
//add note
///change progr
//firebase