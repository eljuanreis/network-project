package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

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
				
				// Guardar as linhas para exibir no JOptionPane
				StringBuffer linhaToPane = new StringBuffer();
				
				for (int i = 0; i < tamanhoLinhas; i++) {
					
					if (i == tamanhoLinhas - 1) {
						break;
					}
					
					if (linhas[i+1].contains("IPv4")) {
						linhaToPane.append(linhas[i] + "\n");
						linhaToPane.append(linhas[i+1] + "\n");
					}
				}
				
				JOptionPane.showMessageDialog(null, linhaToPane.toString());
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
				
				// Guardar as linhas para exibir no JOptionPane
				StringBuffer linhaToPane = new StringBuffer();
				
				for (int i = 0; i < tamanhoLinhas; i++) {
					
					if (i == tamanhoLinhas - 1) {
						break;
					}
					
					if (linhas[i+1].contains("inet")) {
						System.out.println(linhas[i]);
						
						if (linhas[i+1].contains("inet6") == false) {
							String[] removeStrings = linhas[i+1].split("netmask");

							linhaToPane.append(removeStrings[0]);
						}
					}
				}
				
				JOptionPane.showMessageDialog(null, linhaToPane.toString());
			}			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao executar:" + e.getMessage());
		}				
	}
	
	public void ping() {
		String so = this.getOS().toLowerCase();
		
		try {
			if (so.contains(soWindowLabel)) {
				JOptionPane.showMessageDialog(null, "Aguarde... Estamos realizando a verificação.");
	
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
				
				// Guardar as linhas para exibir no JOptionPane
				StringBuffer linhaToPane = new StringBuffer();
				
				for (int i = 0; i < tamanhoLinhas; i++) {
					if (linhas[i].contains("ms")) {
						String[] line = linhas[i].split("TTL");
						
						linhaToPane.append(line[0] + "\n");
					}
				}
				
				JOptionPane.showMessageDialog(null, linhaToPane.toString());
			}
			
			if (so.contains(soLinuxLabel)) {
				JOptionPane.showMessageDialog(null, "Aguarde... Estamos realizando a verificação.");
				
				Process p = Runtime.getRuntime().exec("ping -4 -c 10 www.google.com.br");
				
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

				// Guardar as linhas para exibir no JOptionPane
				StringBuffer linhaToPane = new StringBuffer();
				
				for (int i = 0; i < tamanhoLinhas; i++) {
					if (linhas[i].contains("ms")) {
						String[] line = linhas[i].split("\n");
						linhaToPane.append(line[0] + "\n");
					}
				}
				
				JOptionPane.showMessageDialog(null, linhaToPane.toString());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao executar:" + e.getMessage());
		}
	}
}