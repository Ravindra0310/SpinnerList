package com.example.spinnerlist


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.child.view.*


class ExpandableListAdapter(
    private var context: Context, private var expandableListTitle: List<String>,
    private var expandableListDetail: HashMap<String, List<String>>, private var onCheckboxListner: OnCheckboxListner
) : BaseExpandableListAdapter()
{
    override fun getGroupCount(): Int=expandableListTitle.size

    override fun getChildrenCount(p0: Int): Int =expandableListDetail[expandableListTitle[p0]]!!.size

    override fun getGroup(p0: Int): Any=expandableListTitle[p0]

    override fun getChild(p0: Int, p1: Int): Any =expandableListDetail[expandableListTitle[p0]]!![p1]

    override fun getGroupId(p0: Int): Long=p0.toLong()

    override fun getChildId(p0: Int, p1: Int): Long =p1.toLong()

    override fun hasStableIds(): Boolean =false

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        var convertView = p2
        var title_child = getGroup(p0) as String
        if (convertView == null) {
            var inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.parent, null)
        }
        val textviewChild=convertView!!.findViewById<TextView>(R.id.text_parent)
        textviewChild.text=title_child
        return convertView
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        var convertView = p3
        var title = getChild(p0, p1) as String
        if (convertView == null) {
            var inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.child,null)
        }
        val textviewChild=convertView!!.findViewById<TextView>(R.id.textview_child)
        textviewChild.text=title

            convertView.checkbox_child.setOnClickListener {
                if(convertView.checkbox_child.isChecked) {
                    onCheckboxListner.clickListnerChecked(getGroup(p0) as String)
                }else{
                    onCheckboxListner.clickListnerUnChecked(getGroup(p0) as String)
            }
        }


        return convertView
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean =true
}
