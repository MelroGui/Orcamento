package model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import model.Orcamento;

public class OrcamentoDAO {
	
	private BufferedReader br;
	private BufferedWriter bw;
	private String arquivo = "./dados/Orcamento.csv";
	
	public ArrayList<Orcamento> ler() {
		ArrayList<Orcamento> linhas = new ArrayList<>();
		Orcamento orcamento;
		try {
			String linha = br.readLine();
			while(linha != null) {
				orcamento = new Orcamento(linha);
				linhas.add(orcamento);
				linha = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return linhas;
	}
	
	public void escrever(ArrayList<Orcamento> linhas) {
		try {
			for (Orcamento o : linhas) {
				bw.write(o.toCSV());
			}
			bw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}