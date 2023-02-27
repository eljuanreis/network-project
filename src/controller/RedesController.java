package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	static String soWindowLabel = "windows";
	static String soLinuxLabel = "linux";
	
	private String getOS() {
		return System.getProperty("os.name");
	}
	
	public void ipConfig() {
		String so = this.getOS().toLowerCase();

		try {
			if (so.contains(soWindowLabel)) {
				Process p = Runtime.getRuntime().exec("ipconfig");
				
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				
				String linha = buffer.readLine();
				StringBuffer todasLinhas = new StringBuffer();
												
				while (linha != null) {					
					if (linha.contains("Adaptador") || linha.contains("IPv4")) {
						todasLinhas.append(linha + "\n");
					}

					linha = buffer.readLine();
				}
				
				buffer.close();
				leitor.close();
				fluxo.close();
				
				String[] linhas = todasLinhas.toString().split("\n");
				int tamanhoLinhas = linhas.length;
				
				for (int i = 0; i < tamanhoLinhas; i++) {
					
					if (i == tamanhoLinhas - 1) {
						break;
					}
					
					if (linhas[i+1].contains("IPv4")) {
						System.out.println(linhas[i]);
						System.out.println(linhas[i+1]);
					}
				}
			}
			
			if (so.contains(soLinuxLabel)) {
				Process p = Runtime.getRuntime().exec("ifconfig");
				
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				
				String linha = buffer.readLine();
				StringBuffer todasLinhas = new StringBuffer();
												
				while (linha != null) {					
					if (linha.contains("flags") || linha.contains("inet")) {
						todasLinhas.append(linha + "\n");
					}

					linha = buffer.readLine();
				}
				
				buffer.close();
				leitor.close();
				fluxo.close();
				
				String[] linhas = todasLinhas.toString().split("\n");
				int tamanhoLinhas = linhas.length;
				
				for (int i = 0; i < tamanhoLinhas; i++) {
					
					if (i == tamanhoLinhas - 1) {
						break;
					}
					
					if (linhas[i+1].contains("inet")) {
						System.out.println(linhas[i]);
						System.out.println(linhas[i+1]);
					}
				}
				
			}			
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}				
	}
	
	public void ping() {
		String so = this.getOS().toLowerCase();
		
		try {
			if (so.contains(soWindowLabel)) {
				
				Process p = Runtime.getRuntime().exec("PING -4 -n 10 www.google.com.br");
				
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);

				String linha = buffer.readLine();
				StringBuffer todasLinhas = new StringBuffer();
												
				while (linha != null) {
					if (linha.contains("tempo")) {
						todasLinhas.append(linha + "\n");
					}

					linha = buffer.readLine();
				}
				
				buffer.close();
				leitor.close();
				fluxo.close();
				
				String[] linhas = todasLinhas.toString().split("tempo=");
				int tamanhoLinhas = linhas.length;
				
				for (int i = 0; i < tamanhoLinhas; i++) {
					if (linhas[i].contains("ms")) {
						String[] line = linhas[i].split("TTL");
						
						System.out.println(line[0]);
					}
				}
			}
			
			if (so.contains(soLinuxLabel)) {
				Process p = Runtime.getRuntime().exec("PING -4 -n 10 www.google.com.br");
				
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);

				String linha = buffer.readLine();
				StringBuffer todasLinhas = new StringBuffer();
												
				while (linha != null) {
					if (linha.contains("time")) {
						todasLinhas.append(linha + "\n");
					}

					linha = buffer.readLine();
				}
				
				buffer.close();
				leitor.close();
				fluxo.close();
				
				String[] linhas = todasLinhas.toString().split("time=");
				int tamanhoLinhas = linhas.length;
				
				for (int i = 0; i < tamanhoLinhas; i++) {
					if (linhas[i].contains("ms")) {
						String[] line = linhas[i].split("TTL");
						
						System.out.println(line[0]);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}