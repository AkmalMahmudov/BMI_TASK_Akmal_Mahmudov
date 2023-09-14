package com.akmal.bmi_akmal_mahmudov.presentation.calculate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.akmal.bmi_akmal_mahmudov.R
import com.akmal.bmi_akmal_mahmudov.databinding.FragmentCalculateBinding
import com.akmal.bmi_akmal_mahmudov.utils.Constants
import com.akmal.bmi_akmal_mahmudov.utils.extensions.observe
import com.akmal.bmi_akmal_mahmudov.utils.extensions.shortSnack
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalculateFragment : Fragment(R.layout.fragment_calculate) {
    private val binding by viewBinding(FragmentCalculateBinding::bind)
    private val viewModel: CalculateViewModel by viewModels()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private val adRequest by lazy { AdRequest.Builder().build() }
    private var mInterstitialAd: InterstitialAd? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupViews() = with(binding) {
        loadInterstitialAd()
        back.setOnClickListener { navController.navigateUp() }
        pickerWeight.maxValue = Constants.pickerMax
        pickerWeight.minValue = Constants.pickerMin
        pickerHeight.maxValue = Constants.pickerMax
        pickerHeight.minValue = Constants.pickerMin
        pickerGender.maxValue = Constants.genderMax
        pickerGender.minValue = Constants.genderMin
        pickerWeight.displayedValues = Constants.getArr()
        pickerHeight.displayedValues = Constants.getArr()
        pickerGender.displayedValues = Constants.genders

        go.setOnClickListener {
            showInterstitialAd()
            viewModel.check(
                etName.text.toString().trim(),
                pickerWeight.value,
                pickerHeight.value,
                pickerGender.value
            )
        }
    }

    private fun setupObservers() = with(viewModel) {
        observe(isValid) {
            if (navController.currentDestination?.id == R.id.calculateFragment) {
                navController.navigate(CalculateFragmentDirections.actionCalculateFragmentToResultFragment(it))
            }
        }
        observe(messageFlow) { binding.root.shortSnack(it) }
    }

    private fun loadInterstitialAd() {
        InterstitialAd.load(
            requireContext(),
            Constants.ad_id1,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                }
            })
        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                mInterstitialAd = null
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                mInterstitialAd = null
            }
        }
    }

    private fun showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(requireActivity())
        }
    }
}