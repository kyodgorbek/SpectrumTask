package yodgorbekkomilov.edgar.spectrumtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import yodgorbekkomilov.edgar.spectrumtask.Member
import yodgorbekkomilov.edgar.spectrumtask.R

class MemberAdapter : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {
    private val members = mutableListOf<Member>()

    fun setMembers(data: List<Member>) {
        members.clear()
        members.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.member_list, parent, false)
        return MemberViewHolder(view)
    }

    override fun getItemCount() = members.size

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        return holder.bind(members[position])
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