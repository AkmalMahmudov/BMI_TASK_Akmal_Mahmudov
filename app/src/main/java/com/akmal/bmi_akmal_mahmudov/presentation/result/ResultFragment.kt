package com.akmal.bmi_akmal_mahmudov.presentation.result

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.akmal.bmi_akmal_mahmudov.R
import com.akmal.bmi_akmal_mahmudov.data.model.BMIResult
import com.akmal.bmi_akmal_mahmudov.databinding.FragmentResultBinding
import com.akmal.bmi_akmal_mahmudov.databinding.NativeAdLayoutBinding
import com.akmal.bmi_akmal_mahmudov.utils.Constants
import com.akmal.bmi_akmal_mahmudov.utils.extensions.goWeb
import com.akmal.bmi_akmal_mahmudov.utils.extensions.observe
import com.akmal.bmi_akmal_mahmudov.utils.extensions.requestPermissionsHelper
import com.akmal.bmi_akmal_mahmudov.utils.extensions.shortSnack
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment(R.layout.fragment_result) {
    private val binding by viewBinding(FragmentResultBinding::bind)
    private val adBinding: NativeAdLayoutBinding by viewBinding(NativeAdLayoutBinding::bind)
    private val viewModel: ResultViewModel by viewModels()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupViews() = with(binding) {
        loadAd()
        back.setOnClickListener { navController.navigateUp() }
        rate.setOnClickListener { goWeb(Constants.play_store) }
        share.setOnClickListener { viewModel.saveScreenshotToCache(takeScreenshot()) }
    }

    private fun setupObservers() = with(viewModel) {
        observe(result) { showResult(it) }
        observe(screenshotSave) {
            if (it != Uri.EMPTY) {
                requestPermissionsHelper(Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    granted = { shareScreenshot(requireContext(), it) },
                    denied = { binding.root.shortSnack(getString(R.string.sww)) })
            }
        }
        observe(messageFlow) { binding.root.shortSnack(it) }
    }

    private fun showResult(bmi: BMIResult) = with(binding) {
        result.text = bmi.name
        scoreWhole.text = bmi.scoreWhole
        scoreFraction.text = bmi.scoreFraction
        desc2.text = bmi.ponderal
    }

    private fun takeScreenshot(): Bitmap {
        val view = requireActivity().window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache(true)
        val bitmap = Bitmap.createBitmap(view.drawingCache)
        view.isDrawingCacheEnabled = false
        return bitmap
    }

    private fun shareScreenshot(context: Context, screenshotUri: Uri) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/*"
        shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri)
        context.startActivity(Intent.createChooser(shareIntent, Constants.share_title))
    }

    private fun loadAd() {
        val adLoader = AdLoader.Builder(requireContext(), Constants.ad_id2).forNativeAd { nativeAd: NativeAd ->
                adBinding.image.setImageDrawable(nativeAd.icon?.drawable)
                adBinding.title.text = nativeAd.headline
                adBinding.body.text = nativeAd.body
            }.withNativeAdOptions(NativeAdOptions.Builder().build()).build()

        adLoader.loadAd(AdRequest.Builder().build())
    }
}