package com.example.medicinesearch.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.medicinesearch.R
import com.example.medicinesearch.ui.viewmodels.MedicineViewModel
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.*
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.image.ImageProvider
import kotlinx.android.synthetic.main.fragment_pharmacy_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PharmacyDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PharmacyDetailFragment : Fragment(R.layout.fragment_pharmacy_detail),
    UserLocationObjectListener, Map.CameraCallback, CameraListener, MapLoadedListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var latPharmacy: Double = 0.0
    private var lngPharmacy: Double = 0.0
    private lateinit var userLocationLayer: UserLocationLayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.setApiKey("522fb9ba-acc3-4c2a-ad64-371448cace44")
        MapKitFactory.initialize(requireActivity())

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private val medicineViewModel by viewModels<MedicineViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        yandexMap.map.addCameraListener(this)
        yandexMap.map.setMapLoadedListener(this)

        yandexMap.map.isRotateGesturesEnabled = true
        yandexMap.map.isZoomGesturesEnabled = true



        arguments?.let {


            drugName.text =
                "${it.getString("drug_name")} ${it.getString("drug_form")} ${it.getString("drug_dosage")}"

            drugPrice.text = it.getString("drug_price")?.replace("₽","uzs")

            favoriteCheckBoxDetail.isChecked = it.getBoolean("is_favorite")

            medicineViewModel.getById(it.getInt("drug_id"))

            favoriteCheckBoxDetail.setOnCheckedChangeListener { compoundButton, b ->
                val medicine = medicineViewModel.currentMedicine
                medicine.is_favorite = if (b) 1 else 0
                medicineViewModel.update(medicine)

            }

            pharmacyName.text = it.getString("pharmacy_name")




            pharmacyAddress.text =
                "${it.getString("pharmacy_address")}\n${it.getString("pharmacy_district")}\n${
                    it.getString("phamacy_near_by")
                }"

            distancePharmacy.text = it.getString("pharmacy_distance")+" km"

            phonePharmacy.text = it.getInt("pharmacy_phone").toString()


        }


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PharmacyDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PharmacyDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onStart() {
        super.onStart()
        yandexMap.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        yandexMap.onStop()
        MapKitFactory.getInstance().onStop()
    }

    override fun onObjectAdded(userLocationView: UserLocationView) {

//        userLocationLayer.setAnchor(
//            PointF(yandexMap.width() * 0.5.toFloat(), yandexMap.height().toFloat() * 0.5.toFloat()),
//            PointF(yandexMap.width() * 0.5.toFloat(), yandexMap.height().toFloat() * 0.5.toFloat())
//        )
//
//
//        userLocationView.pin.setIcon(ImageProvider.fromResource(context, R.drawable.pin))
//        userLocationView.arrow.setIcon(ImageProvider.fromResource(context, R.drawable.pin))

    }

    override fun onObjectRemoved(p0: UserLocationView) {

    }

    override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {

    }

    override fun onMoveFinished(p0: Boolean) {

    }

    override fun onCameraPositionChanged(
        p0: Map,
        p1: CameraPosition,
        p2: CameraUpdateSource,
        p3: Boolean
    ) {

    }

    override fun onMapLoaded(p0: MapLoadStatistics) {

        var isMapLoadedSusseccfully = true

        val mapkit = MapKitFactory.getInstance()


        yandexMap?.let {


            userLocationLayer = mapkit.createUserLocationLayer(yandexMap.mapWindow)
            userLocationLayer.isVisible = true
            userLocationLayer.isHeadingEnabled = true
            userLocationLayer.setObjectListener(this)

            if (isMapLoadedSusseccfully && userLocationLayer.isValid) {


                latPharmacy = requireArguments().getDouble("pharmacy_lat")
                lngPharmacy = requireArguments().getDouble("pharmacy_lng")


                Log.i("asjdhvwdav", "sjahdv")



                yandexMap?.let {


                    it.map.move(
                        CameraPosition(
                            Point(latPharmacy, lngPharmacy),
                            it.map.maxZoom,
                            10.0f,
                            0.0f
                        ),
                        Animation(Animation.Type.SMOOTH, 1.toFloat()), null
                    )


                    userLocationLayer.resetAnchor()
                    yandexMap.map.mapObjects.clear()

                    it.map.mapObjects.addPlacemark(
                        Point(latPharmacy, lngPharmacy),
                        ImageProvider.fromResource(context, R.drawable.pin)
                    )


                }
            }

        }

    }
}