package com.myanuarbf.firebase.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.myanuarbf.firebase.databinding.FragmentUpdateBinding
import com.myanuarbf.firebase.model.Mahasiswa
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateFragment : Fragment() {
    lateinit var binding : FragmentUpdateBinding
    private lateinit var databaseRef: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater,
            container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState:
    Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString("id")
        val nim = arguments?.getString("nim")
        val nama = arguments?.getString("nama")
        val telepon = arguments?.getString("telepon")
        binding.inputNim.setText(nim)
        binding.inputNama.setText(nama)
        binding.inputTelepon.setText(telepon)
        binding.btnUpdate.setOnClickListener{
            val inputNim = binding.inputNim.text.toString()
            val inputNama = binding.inputNama.text.toString()
            val inputTelepon =
                binding.inputTelepon.text.toString()
            databaseRef =
                FirebaseDatabase.getInstance().getReference("mahasiswa").child(id!!)
            val mahasiswa = Mahasiswa(id, inputNim, inputNama,
                inputTelepon)
            databaseRef.setValue(mahasiswa).addOnSuccessListener {
                Toast.makeText(requireContext(), "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show()
                        findNavController().navigateUp()
            }.addOnFailureListener{
                Toast.makeText(requireContext(), "Data Gagal Diupdate", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
