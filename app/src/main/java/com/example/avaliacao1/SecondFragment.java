package com.example.avaliacao1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.avaliacao1.databinding.FragmentSecondBinding;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private BiomaViewModel biomaViewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        biomaViewModel = new ViewModelProvider(requireActivity())
                .get(BiomaViewModel.class);

        biomaViewModel.getBiomaSelecionado().observe(
                getViewLifecycleOwner(),
                bioma -> {

                    List<Bioma> listaBiomas;

                    if (bioma == null) {

                        // "Selecione" → mostra todos os biomas
                        binding.info.setText("Lista de Biomas");

                        listaBiomas = montarListaBiomas();

                    } else {

                        // Um bioma selecionado → mostra somente ele
                        binding.info.setText(
                                getString(
                                        R.string.galeria_do_bioma,
                                        bioma.getNome()
                                )
                        );

                        listaBiomas = new ArrayList<>();
                        listaBiomas.add(bioma);
                    }

                    // LISTVIEW
                    BiomaListAdapter listAdapter =
                            new BiomaListAdapter(
                                    requireContext(),
                                    listaBiomas
                            );

                    binding.listBiomas.setAdapter(listAdapter);
                }
        );

        // Clique no item do ListView
        binding.listBiomas.setOnItemClickListener(
                (parent, viewClicada, position, id) -> {

                    Bioma biomaSelecionado =
                            (Bioma) parent.getItemAtPosition(position);

                    abrirDetalhe(
                            biomaSelecionado.getNome(),
                            biomaSelecionado.getDescricao(),
                            biomaSelecionado.getImagemPrincipal()
                    );
                }
        );
    }

    private List<Bioma> montarListaBiomas() {

        List<Bioma> lista = new ArrayList<>();

        lista.add(
                new Bioma(
                        getString(R.string.bioma_amazonia),
                        getString(R.string.desc_amazonia),
                        R.drawable.amazonia_img1,
                        R.drawable.amazonia_img2
                )
        );

        lista.add(
                new Bioma(
                        getString(R.string.bioma_caatinga),
                        getString(R.string.desc_caatinga),
                        R.drawable.caatinga_img1,
                        R.drawable.caatinga_img2
                )
        );

        lista.add(
                new Bioma(
                        getString(R.string.bioma_cerrado),
                        getString(R.string.desc_cerrado),
                        R.drawable.cerrado_img1,
                        R.drawable.cerrado_img2
                )
        );

        lista.add(
                new Bioma(
                        getString(R.string.bioma_mata_atlantica),
                        getString(R.string.desc_mata_atlantica),
                        R.drawable.mata_atlantica_img1,
                        R.drawable.mata_atlantica_img2
                )
        );

        lista.add(
                new Bioma(
                        getString(R.string.bioma_pampa),
                        getString(R.string.desc_pampa),
                        R.drawable.pampa_img1,
                        R.drawable.pampa_img2
                )
        );

        lista.add(
                new Bioma(
                        getString(R.string.bioma_pantanal),
                        getString(R.string.desc_pantanal),
                        R.drawable.pantanal_img1,
                        R.drawable.pantanal_img2
                )
        );

        return lista;
    }

    private void abrirDetalhe(
            String titulo,
            String descricao,
            int imagem
    ) {
        Intent intent =
                new Intent(
                        requireContext(),
                        DetalheActivity.class
                );

        intent.putExtra(
                DetalheActivity.EXTRA_TITULO,
                titulo
        );

        intent.putExtra(
                DetalheActivity.EXTRA_DESCRICAO,
                descricao
        );

        intent.putExtra(
                DetalheActivity.EXTRA_IMAGEM,
                imagem
        );

        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}