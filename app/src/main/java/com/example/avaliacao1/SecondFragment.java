package com.example.avaliacao1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.avaliacao1.databinding.FragmentSecondBinding;


public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private BiomaViewModel biomaViewModel;
    private Bioma biomaAtual;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Mesmo ViewModel usado no FirstFragment (é da Activity, não do Fragment,
        // por isso "requireActivity()" -> assim os dois enxergam o mesmo LiveData)
        biomaViewModel = new ViewModelProvider(requireActivity()).get(BiomaViewModel.class);

        binding.grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicada, int position, long id) {
                if (biomaAtual == null) return;
                int imagem = position == 0
                        ? biomaAtual.getImagemPrincipal()
                        : biomaAtual.getImagemSecundaria();
                Intent intent = new Intent(requireContext(), DetalheActivity.class);
                intent.putExtra(DetalheActivity.EXTRA_TITULO, biomaAtual.getNome());
                intent.putExtra(DetalheActivity.EXTRA_DESCRICAO, biomaAtual.getDescricao());
                intent.putExtra(DetalheActivity.EXTRA_IMAGEM, imagem);
                startActivity(intent);
            }
        });

        biomaViewModel.getBiomaSelecionado().observe(getViewLifecycleOwner(), bioma -> {
            if (bioma == null) return;
            biomaAtual = bioma;
            binding.info.setText(getString(R.string.galeria_do_bioma, bioma.getNome()));
            int[] imagens = new int[]{bioma.getImagemPrincipal(), bioma.getImagemSecundaria()};
            binding.grid.setAdapter(new Adapter(getContext(), imagens));
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
