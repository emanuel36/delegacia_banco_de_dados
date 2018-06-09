package main;

import java.util.ArrayList;
import java.util.Scanner;

import delegacia.dao.criminosoDAO;
import delegacia.dao.delitoDAO;
import delegacia.dao.ocorrenciaDAO;
import delegacia.dao.vitimaDAO;
import delegacia.pojo.Criminoso;
import delegacia.pojo.Delito;
import delegacia.pojo.Ocorrencia;
import delegacia.pojo.Vitima;

public class main {
	public static void main(String[] args){
		int opcao, op;
		Scanner in = new Scanner(System.in);
		boolean terminar = false;
		boolean term2 = false;
		long cpfVitima, cpfCriminoso;
		int idade, id;
		String nome, sexo, descricao;
		String data, hora;
		vitimaDAO vitimaDAO = new vitimaDAO();
		criminosoDAO criminosoDAO = new criminosoDAO();
		delitoDAO delitoDAO = new delitoDAO();
		ocorrenciaDAO ocorrenciaDAO = new ocorrenciaDAO();
		Vitima vitima;
		Criminoso criminoso;
		Delito delito;
		Ocorrencia ocorrencia;
		

		while(!terminar){
			System.out.println("-------------OPÇÕES-------------\n");
			System.out.println("| 01 - Vítima");
			System.out.println("| 02 - Criminoso");
			System.out.println("| 03 - Delito");
			System.out.println("| 04 - Ocorrência");
			System.out.println("| 00 - Sair");

			opcao = in.nextInt();
			in.nextLine(); //Eliminar buffer
			switch(opcao){
			
			case 0:
				System.out.println("Programa finalizado!");
				terminar = true;
			break;
			
			case 1:
				term2 = false;
				while(!term2) {
					System.out.println("\n-------------OPÇÕES VÍTIMA-------------\n");
					System.out.println("| 01 - Cadastrar vítima");
					System.out.println("| 02 - Deletar vítima");
					System.out.println("| 03 - Listar todas as vítimas");
					System.out.println("| 04 - Buscar vítima");
					System.out.println("| 05 - Buscar ocorrências da vítima");
					System.out.println("| 00 - Voltar");
					
					op = in.nextInt();
					in.nextLine();
					switch(op) {
						case 0:
							term2 = true;
							break;
						
						case 1: 
							System.out.println("Nome: ");
							nome = in.nextLine();
							System.out.println("Sexo (F ou M): ");
							sexo = in.nextLine();
							System.out.println("CPF: ");
							cpfVitima = in.nextLong();
							System.out.println("Idade: ");
							idade = in.nextInt();
							in.nextLine();
							
							vitima = new Vitima(cpfVitima, nome, idade, sexo);
							if(vitimaDAO.adicionarVitima(vitima)) {
								System.out.println("Vítima adicionada!");
							}else {
								System.out.println("Ocorreu um erro ao adicionar a vítima!");
							}
							break;
							
						case 2:
							vitima = new Vitima();
							System.out.println("CPF: ");
							cpfVitima = in.nextLong();
							vitima.setCpf(cpfVitima);
							in.nextLine();
							
							if(vitimaDAO.removerVitima(vitima)) {
								System.out.println("Vítima removida!");
							}else {
								System.out.println("Ocorreu um erro ao remover!");
							}
							break;
							
						case 3:
							ArrayList<Vitima> vitimas = vitimaDAO.getVitimas();
							if(vitimas.size() < 1){
								System.out.println("Resultado vazio!");
							}
							else{
								for(Vitima aux: vitimas){
									System.out.println(aux.toString());
								}
							}
							break;
							
						case 4:
							vitima = new Vitima();
							System.out.println("CPF: ");
							cpfVitima = in.nextLong();
							vitima.setCpf(cpfVitima);
							in.nextLine();
							
							if(vitimaDAO.buscarVitima(cpfVitima) == null) {
								System.out.println("CPF não encontrado!");
							}else {
								System.out.println(vitimaDAO.buscarVitima(cpfVitima));
							}
							
							break;
							
						case 5:
							System.out.println("CPF: ");
							cpfVitima = in.nextLong();
							vitimaDAO.getOcorrenciasVitima(cpfVitima);
							in.nextLine();
							break;
							
						default:
							System.out.println("Opção inválida!");
							break;
					}
				}
				
				break;
			
			case 2:
				term2 = false;
				while(!term2) {
					System.out.println("\n-------------OPÇÕES CRIMINOSO-------------\n");
					System.out.println("| 01 - Cadastrar criminoso");
					System.out.println("| 02 - Deletar criminoso");
					System.out.println("| 03 - Listar todos os criminosos");
					System.out.println("| 04 - Buscar criminoso");
					System.out.println("| 05 - Buscar ocorrências do criminoso");
					System.out.println("| 00 - Voltar");
					
					op = in.nextInt();
					in.nextLine();
					
					switch(op) {
						case 0:
							term2 = true;
							break;
							
						case 1: 
							System.out.println("Nome: ");
							nome = in.nextLine();
							System.out.println("Sexo (F ou M): ");
							sexo = in.nextLine();
							System.out.println("CPF: ");
							cpfCriminoso = in.nextLong();
							System.out.println("Idade: ");
							idade = in.nextInt();
							
							criminoso = new Criminoso(cpfCriminoso, nome, idade, sexo);
							if(criminosoDAO.adicionarCriminoso(criminoso)) {
								System.out.println("Criminoso adicionado!");
							}else {
								System.out.println("Ocorreu um erro ao adicionar a criminoso!");
							}
							break;
							
						case 2:
							criminoso = new Criminoso();
							System.out.println("CPF: ");
							cpfCriminoso = in.nextLong();
							criminoso.setCpf(cpfCriminoso);
							
							if(criminosoDAO.removerCriminoso(criminoso)) {
								System.out.println("Criminoso removido!");
							}else {
								System.out.println("Ocorreu um erro ao remover!");
							}
							break;
							
						case 3:
							ArrayList<Criminoso> criminosos = criminosoDAO.getCriminosos();
							if(criminosos.size() < 1){
								System.out.println("Resultado vazio!");
							}
							else{
								for(Criminoso aux: criminosos){
									System.out.println(aux.toString());
								}
							}
							break;
							
						case 4:
							criminoso = new Criminoso();
							System.out.println("CPF: ");
							cpfCriminoso = in.nextLong();
							criminoso.setCpf(cpfCriminoso);
							
							if(criminosoDAO.buscarCriminoso(cpfCriminoso) == null) {
								System.out.println("CPF não encontrado!");
							}else {
								System.out.println(criminosoDAO.buscarCriminoso(cpfCriminoso));
							}
							break;
							
						case 5:
							criminoso = new Criminoso();
							System.out.println("CPF do criminoso: ");
							cpfCriminoso = in.nextLong();
							criminosoDAO.getOcorrenciasCriminoso(cpfCriminoso);
							in.nextLine();
							break;
							
						default:
							System.out.println("Opção inválida!");
							break;
					}
				}	
				break;
			case 3:
				term2 = false;
				while(!term2) {
					System.out.println("\n-------------OPÇÕES DELITO-------------\n");
					System.out.println("| 01 - Cadastrar delito");
					System.out.println("| 02 - Deletar delito");
					System.out.println("| 03 - Listas todos os delitos");
					System.out.println("| 04 - Buscar delito");
					System.out.println("| 05 - Listar quantidade de delitos");
					System.out.println("| 00 - Voltar");
					
					op = in.nextInt();
					in.nextLine();
					switch(op) {
						case 0:
							term2 = true;
							break;
							
						case 1:
							System.out.println("Nome: ");
							nome = in.nextLine();
							
							delito = new Delito(nome);
							if(delitoDAO.adicionarDelito(delito)) {
								System.out.println("Delito adicionado!");
							}else {
								System.out.println("Ocorreu um erro ao adicionar o delito!");
							}
							break;
							
						case 2:
							delito = new Delito();
							System.out.println("ID delito: ");
							id = in.nextInt();
							delito.setId(id);
							
							if(delitoDAO.removerDelito(delito)) {
								System.out.println("Delito removido!");
							}else {
								System.out.println("Ocorreu um erro ao remover!");
							}
							break;
							
						case 3:
							ArrayList<Delito> delitos = delitoDAO.getDelitos();
							if(delitos.size() < 1){
								System.out.println("Resultado vazio!");
							}
							else{
							for(Delito aux: delitos){
									System.out.println(aux.toString());
								}
							}
							break;
							
						case 4:
							delito = new Delito();
							System.out.println("Nome: ");
							nome = in.next();
							delito.setNome(nome);
							
							if(delitoDAO.buscarDelito(nome) == null) {
								System.out.println("Delito não encontrado!");
							}else {
								System.out.println(delitoDAO.buscarDelito(nome));
							}
							break;
							
						case 5: 
							delitoDAO.getNumeroDelitos();
							break;
							
						default:
							System.out.println("Opção inválida!");
							break;
					}
				}
				break;
			
			case 4:
				term2 = false;
				while(!term2) {
					System.out.println("\n-------------OPÇÕES OCORRÊNCIA-------------\n");
					System.out.println("| 01 - Cadastrar ocorrência");
					System.out.println("| 02 - Deletar ocorrência");
					System.out.println("| 03 - Listar todas as ocorrências");
					System.out.println("| 04 - Buscar ocorrência");
					System.out.println("| 05 - Atualizar ocorrência");
					System.out.println("| 00 - Voltar");
					
					op = in.nextInt();
					in.nextLine();
					switch(op) {
						case 0:
							term2 = true;
							break;
							
						case 1:
							System.out.println("ID delito: ");
							id = in.nextInt();
							System.out.println("CPF vítima: ");
							cpfVitima = in.nextLong();
							System.out.println("CPF criminoso: ");
							cpfCriminoso = in.nextLong();
							System.out.println("Descrição: ");
							descricao = in.next();
							System.out.println("Data (ano-mês-dia): ");
							data = in.next();
							System.out.println("Hora (hora:minutos): ");
							hora = in.next();
							
							ocorrencia = new Ocorrencia(id, cpfVitima, cpfCriminoso, descricao, data, hora);
							if(ocorrenciaDAO.adicionarOcorrencia(ocorrencia)) {
								System.out.println("Ocorrência adicionada!");
							}else {
								System.out.println("Ocorreu um erro ao adicionar a ocorrência!");
							}
							break;
							
						case 2:
							ocorrencia = new Ocorrencia();
							System.out.println("ID ocorrência: ");
							id = in.nextInt();
							ocorrencia.setId(id);
							
							if(ocorrenciaDAO.removerOcorrencia(ocorrencia)) {
								System.out.println("Ocorrencia removida!");
							}else {
								System.out.println("Ocorreu um erro ao remover!");
							}
							break;
							
						case 3:
							ArrayList<Ocorrencia> ocorrencias = ocorrenciaDAO.getOcorrencias();
							if(ocorrencias.size() < 1){
								System.out.println("Resultado vazio!");
							}
							else{
								for(Ocorrencia aux: ocorrencias){
									System.out.println(aux.toString());
								}
							}
							break;
							
						case 4:
							ocorrencia = new Ocorrencia();
							System.out.println("ID: ");
							id = in.nextInt();
							ocorrencia.setId(id);
							
							if(ocorrenciaDAO.buscarOcorrencia(id) == null) {
								System.out.println("ID não encontrado!");
							}else {
								System.out.println(ocorrenciaDAO.buscarOcorrencia(id));
							}
							break;
							
						case 5:
							ocorrencia = new Ocorrencia();
							System.out.println("ID: ");
							id = in.nextInt();
							System.out.println("Nova descrição: ");
							descricao = in.next();
							ocorrencia.setId(id);
							ocorrencia.setDescricao(descricao);
							if(ocorrenciaDAO.atualizarDescricao(ocorrencia)){
								System.out.println("Ocorrência atualizada");
							}else {
								System.out.println("Ocorreu um erro ao atualizar!");
							}
							break;
						
						default:
							System.out.println("Opção inválida!");
							break;
					}
				}
				break;
			
			default:
				System.out.println("Opção inválida!");
				break;
			}
		}
	}
}
