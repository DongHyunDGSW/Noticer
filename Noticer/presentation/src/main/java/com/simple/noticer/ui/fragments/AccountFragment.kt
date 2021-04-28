package com.simple.noticer.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.simple.data.model.UserData
import com.simple.noticer.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {

    private lateinit var accountBinding : FragmentAccountBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        accountBinding = FragmentAccountBinding.inflate(layoutInflater)

        return accountBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAccount()
    }

    private fun initAccount() {
        FirebaseDatabase.getInstance().reference.child("loginInfo").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val firebaseUser = FirebaseAuth.getInstance().currentUser
                val valueSnapshot = snapshot.getValue(UserData::class.java)

                Log.d("TAG", "${firebaseUser?.uid} : ${valueSnapshot?.userUid}")

                if(requireActivity().getSharedPreferences("isLogined", AppCompatActivity.MODE_PRIVATE).getString("userUid", "") == firebaseUser?.uid) {
                    accountBinding.nameText.text = valueSnapshot?.name
                    accountBinding.emailText.text = firebaseUser?.email
                }

                accountBinding.logOut.setOnClickListener {
                    Firebase.auth.signOut()
                    val sharedPreferences = requireActivity().getSharedPreferences("isLogined", AppCompatActivity.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()

                    editor.putString("userUid", "")
                    editor.putBoolean("isLogined", false)

                    editor.apply()
                    requireActivity().finishAndRemoveTask()
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}