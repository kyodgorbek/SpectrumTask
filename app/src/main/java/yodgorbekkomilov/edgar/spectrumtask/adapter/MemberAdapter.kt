package yodgorbekkomilov.edgar.spectrumtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import yodgorbekkomilov.edgar.spectrumtask.Member
import yodgorbekkomilov.edgar.spectrumtask.R
import yodgorbekkomilov.edgar.spectrumtask.SpectrumResponseItem
import java.util.*
import kotlin.collections.ArrayList

class MemberAdapter : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {
    private var members = mutableListOf<Member>()
    private val filteredMembers = mutableListOf<Member>()

    fun setMembers(data: List<Member>) {
        members.clear()
        members.addAll(data)
        members = filteredMembers
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.member_list, parent, false)
        return MemberViewHolder(view)
    }

    override fun getItemCount() = filteredMembers.size




    fun sortAscending() {
        filteredMembers.sortBy { it.age }
        notifyDataSetChanged()
    }

    fun sortDescending() {
        filteredMembers.sortByDescending { it.age }
        notifyDataSetChanged()
    }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val query = charSequence.toString()

                val filtered: MutableList<Member> = ArrayList()

                if (query.isEmpty()) {
                    filtered.clear()
                    filtered.addAll(filteredMembers)
                } else {
                    filtered.addAll(filteredMembers.filter {
                        it.id.toLowerCase(Locale.ROOT)
                            .contains(query.toLowerCase(Locale.ROOT)) || it.age.toString()
                            .contains(query)
                    })
                }

                val results = FilterResults()
                results.count = filtered.size
                results.values = filtered
                return results
            }

            override fun publishResults(charSequence: CharSequence, results: FilterResults) {
                filteredMembers.clear()
                filteredMembers.addAll(results.values as Collection<Member>)

                notifyDataSetChanged()
            }
        }
    }
    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        return holder.bind(filteredMembers[position])
    }

    class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val memberAge: TextView = itemView.findViewById(R.id.memberAge)

        //   private val memberName: TextView = itemView.findViewById(R.id.memberName)
        private val lastName: TextView = itemView.findViewById(R.id.lastName)
        private val firstName: TextView = itemView.findViewById(R.id.firstName)
        private val emailAddress: TextView = itemView.findViewById(R.id.emailAddress)
        private val phone: TextView = itemView.findViewById(R.id.phone)

        fun bind(member: Member) {
            memberAge.text = member.age.toString()
            lastName.text = member.name?.last
            firstName.text = member.name?.first
            emailAddress.text = member.email
            phone.text = member.phone
        }
    }

}