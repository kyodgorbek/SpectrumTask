package yodgorbekkomilov.edgar.spectrumtask.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import yodgorbekkomilov.edgar.spectrumtask.R
import yodgorbekkomilov.edgar.spectrumtask.SpectrumResponseItem
import java.util.*
import kotlin.collections.ArrayList

class SpectrumAdapter(
    private var spectrumResponse: List<SpectrumResponseItem>
) : RecyclerView.Adapter<SpectrumAdapter.SpectrumViewHolder>() {
    private var itemsFiltered: MutableList<SpectrumResponseItem> = ArrayList()

    init {
        itemsFiltered.addAll(spectrumResponse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpectrumViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.spectrum_list, parent, false)
        return SpectrumViewHolder(view)

    }

    override fun getItemCount(): Int {
        return itemsFiltered.size
    }

    override fun onBindViewHolder(holder: SpectrumViewHolder, position: Int) {
        return holder.bind(itemsFiltered[position])
    }


    fun sortAscending() {
        itemsFiltered.sortBy { it.company }
        notifyDataSetChanged()
    }

    fun sortDescending() {
        itemsFiltered.sortByDescending { it.company }
        notifyDataSetChanged()
    }

    fun <T> List<T>.toArrayList(): ArrayList<T> {
        return ArrayList(this)
    }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val query = charSequence.toString()

                val filtered: MutableList<SpectrumResponseItem> = ArrayList()

                if (query.isEmpty()) {
                    filtered.clear()
                    filtered.addAll(spectrumResponse)
                } else {
                    filtered.addAll(spectrumResponse.filter {
                        it.company.toLowerCase(Locale.ROOT)
                            .contains(query.toLowerCase(Locale.ROOT)) || it.id.toString()
                            .contains(query)
                    })
                }

                val results = FilterResults()
                results.count = filtered.size
                results.values = filtered
                return results
            }

            override fun publishResults(charSequence: CharSequence, results: FilterResults) {
                itemsFiltered.clear()
                itemsFiltered.addAll(results.values as List<SpectrumResponseItem>)

                notifyDataSetChanged()
            }
        }
    }


    class SpectrumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val companyLogo: ImageView = itemView.findViewById(R.id.companyLogo)
        private val companyName: TextView = itemView.findViewById(R.id.companyName)
        private val companyWebsite: TextView = itemView.findViewById(R.id.companyWebsite)


        fun bind(spectrum: SpectrumResponseItem) {
            Glide.with(itemView.context).load(R.drawable.placehold).into(companyLogo)

            companyName.text = spectrum.company
            companyWebsite.text = spectrum.website

        }


    }

}

