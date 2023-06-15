package com.captsone.padicure.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.captsone.padicure.data.ScannedData
import com.captsone.padicure.databinding.FragmentScanBinding
import com.captsone.padicure.view.camera.CameraActivity
import com.captsone.padicure.view.camera.CameraActivity.Companion.CAMERA_X_RESULT
import com.captsone.padicure.view.viewmodel.ScanViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class ScanFragment : Fragment(), Screen {
    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    private val viewModel: ScanViewModel by viewModels()
    private var _binding: FragmentScanBinding? = null
    private lateinit var loadingBar: ProgressBar
    private val binding get() = _binding!!
    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                viewModel.setFile(uri, requireContext())
                binding.padiPict.setImageURI(uri)
            }
        }
    }

    private val captureCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val dataUri = it.data?.getStringExtra("uriFile")
            dataUri?.let { it1 -> File(it1) }?.let { it2 -> viewModel.setFileFromCamera(it2) }
            binding.padiPict.setImageBitmap(BitmapFactory.decodeFile(dataUri))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanBinding.inflate(inflater, container, false)

        binding.buttonGallery.setOnClickListener {
            startGallery()
        }
        binding.buttonCamera.setOnClickListener {
            startCameraX()
        }
        loadingBar = binding.loadingsBar
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
        viewModel.isEmpty.observe(viewLifecycleOwner) {
            onChangeVisibility(it)
        }
        viewModel.data.observe(viewLifecycleOwner){
            Log.d("DataUploaded", it.toString())
        }
        viewModel.message.observe(viewLifecycleOwner){
            showMessage(it)
            Log.d("ErrorMessage", it)
        }
        viewModel.data.observe(viewLifecycleOwner){
            showContent(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private fun startCameraX() {
        val intent = Intent(requireActivity(), CameraActivity::class.java)
        captureCamera.launch(intent)
    }

    private fun onChangeVisibility(visible: Boolean) {
        // True if No Picture Selected

        // Show No Image Selected
        binding.shapeableImageView6.visibility = if (visible) View.VISIBLE else View.GONE
        binding.textView.visibility = if (visible) View.VISIBLE else View.GONE


        // Show Image Selected
        binding.labelAkurasi.visibility = if (visible) View.GONE else View.VISIBLE
        binding.labelTeridentifikasi.visibility = if (visible) View.GONE else View.VISIBLE
        binding.labelTutorial.visibility = if (visible) View.GONE else View.VISIBLE
        binding.cureTutorial.visibility = if (visible) View.GONE else View.VISIBLE
        binding.padiAkurasi.visibility = if (visible) View.GONE else View.VISIBLE
        binding.padiPenyakit.visibility = if (visible) View.GONE else View.VISIBLE
        binding.padiPict.visibility = if (visible) View.GONE else View.VISIBLE
        setConstraintChange(visible)
    }

    private fun setConstraintChange(visible: Boolean) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.constrainLayout)
        if (visible) {
            constraintSet.connect(
                binding.textView2.id,
                ConstraintSet.TOP,
                binding.textView.id,
                ConstraintSet.BOTTOM
            )
        } else {
            constraintSet.connect(
                binding.textView2.id,
                ConstraintSet.TOP,
                binding.cureTutorial.id,
                ConstraintSet.BOTTOM
            )
        }
        constraintSet.applyTo(binding.constrainLayout)
    }

    private fun showContent(item: ScannedData){
        binding.padiPenyakit.text = item.predicted
        binding.cureTutorial.text = item.tutorial
        binding.padiAkurasi.text = item.percentage
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    override fun showLoading(isLoading: Boolean) {
        loadingBar.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

}