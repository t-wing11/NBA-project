package com.example.nba_app_project

import android.view.View
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import com.example.nba_app_project.data.PlayerItem
import com.example.nba_app_project.data.TeamItem
import com.example.nba_app_project.ui.viewHolder.ViewHolder
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class ViewHolderTest {

    private lateinit var viewHolder: ViewHolder
    private lateinit var itemView: View

    @Before
    fun setUp() {
        viewHolder = ViewHolder(itemView)
    }

    @Test
    fun bindTeamListTest(){
        val teamItem = TeamItem("team",1,3, emptyList(),2)
        viewHolder.bindTeamList(teamItem)
        assertEquals("team", viewHolder.name.text)
        assertEquals("W: 2", viewHolder.textView1.text)
        assertEquals("L: 3", viewHolder.textView2.text)
    }

    @Test
    fun bindPlayerListTest(){
        val playerItem = PlayerItem("player",1,"name",2,"SG")
        viewHolder.bindPlayerList(playerItem)
        assertEquals("player name", viewHolder.name.text)
        assertEquals("Pos: SG", viewHolder.textView1.text)
        assertEquals("Num: 2", viewHolder.textView2.text)
    }
}