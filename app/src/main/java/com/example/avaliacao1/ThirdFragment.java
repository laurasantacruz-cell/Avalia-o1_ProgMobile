package com.example.avaliacao1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.avaliacao1.databinding.FragmentThirdBinding;


public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;
    private BiomaViewModel biomaViewModel;
    private Bioma biomaAtual;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        biomaViewModel = new ViewModelProvider(requireActivity()).get(BiomaViewModel.class);

        binding.btnOuvirSom.setOnClickListener(v -> {
            if (biomaAtual == null) return;
            Intent intent = new Intent(requireContext(), DetalheActivity.class);
            intent.putExtra(DetalheActivity.EXTRA_TITULO, biomaAtual.getNome());
            intent.putExtra(DetalheActivity.EXTRA_DESCRICAO, biomaAtual.getDescricao());
            intent.putExtra(DetalheActivity.EXTRA_IMAGEM, biomaAtual.getImagemPrincipal());
            startActivity(intent);
        });

        // observa o ViewModel e atualiza o conteúdo
        // complementar sempre que o bioma escolhido no Spinner mudar.
        biomaViewModel.getBiomaSelecionado().observe(getViewLifecycleOwner(), bioma -> {
            if (bioma == null) return;
            biomaAtual = bioma;
            binding.imgBiomaTerceiro.setImageResource(bioma.getImagemSecundaria());
            binding.txtTituloTerceiro.setText(bioma.getNome());
            binding.txtDescricaoTerceiro.setText(bioma.getDescricao());
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
