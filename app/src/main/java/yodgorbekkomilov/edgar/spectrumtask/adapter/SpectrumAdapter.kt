package yodgorbekkomilov.edgar.spectrumtask.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import yodgorbekkomilov.edgar.spectrumtask.R
import yodgorbekkomilov.edgar.spectrumtask.SpectrumResponse

import yodgorbekkomilov.edgar.spectrumtask.SpectrumResponseItem

class SpectrumAdapter(
    private val spectrumResponse: ArrayList <SpectrumResponseItem>,
    logo:String,
    companyName: String,
    companyWebsite: String
) : RecyclerView.Adapter<SpectrumViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpectrumViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.spectrum_list, parent, false)
        return SpectrumViewHolder(view)

    }

    override fun getItemCount(): Int {
        return spectrumResponse.size
    }

    override fun onBindViewHolder(holder: SpectrumViewHolder, position: Int) {
        return holder.bind(spectrumResponse[position])
    }
}


    class SpectrumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val companyLogo: ImageView = itemView.findViewById(R.id.companyLogo)
        private val companyName: TextView = itemView.findViewById(R.id.companyName)
        private val companyWebsite: TextView = itemView.findViewById(R.id.companyWebsite)

        fun bind(spectrum: SpectrumResponseItem) {
            Glide.with(itemView.context).load(spectrum.logo).into(companyLogo)

            companyName.text = spectrum.company
            companyWebsite.text = spectrum.website
        }

    }




