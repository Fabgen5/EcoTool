package Ecotool2021;

import Ecotool.*;


public class Pedaggio2021 {
	protected Casello caselloin;
	protected Casello caselloout;
	protected Veicolo2021 veicolo;

	
	public Pedaggio2021(Casello caselloin, Casello caselloout, Veicolo2021 veicolo) {
		this.caselloin = caselloin;
		this.caselloout = caselloout;
		this.veicolo= veicolo;
		
	}
	
	
	public double CalcolaPedaggio() {
		
		double iva=22;
		double tariffa=caselloin.autostrada.getTariffa();
		double maggiorazioneclasse=0;
		double maggiorazioneclasseEuro=0;
		double diffkm=0;
		double pedaggio;
	
		//Al veicolo calcola la maggiorazione relativa alla classe
		switch(veicolo.getClasse()) {
			case 1:	
				maggiorazioneclasse=1.10; 
				break;
			case 2:	
				maggiorazioneclasse=1.15;
				break;
			case 3:
				maggiorazioneclasse=1.20;
				break;
			case 4:
				maggiorazioneclasse=1.25;
				break;
			case 5:
				maggiorazioneclasse=1.30;	
		}
		//2021 Al veicolo calcola la maggiorazione relativa all'Emissione della CO2
		
		switch(veicolo.getClasseEuro()) {
				case 6:	
					maggiorazioneclasseEuro=1.00; 
					break;
				case 5:	
					maggiorazioneclasseEuro=1.10; 
					break;
				case 4:	
					maggiorazioneclasseEuro=1.15;
					break;
				case 3:
					maggiorazioneclasseEuro=1.20;
					break;
				case 2:
					maggiorazioneclasseEuro=1.25;
					break;
				case 1:
					maggiorazioneclasseEuro=1.30;
				default:	
					maggiorazioneclasseEuro=1.40; 
					break;
			}
		
		//calcola i chilometri tra due caselli
		
		if(caselloin.autostrada.equals(caselloin.autostrada)) {
			diffkm = caselloin.getAltezzakm()-caselloout.getAltezzakm() ;
			if ( diffkm <= 0) diffkm=diffkm*-1;				
			}
		//else vuoto dovuto al caso in cui i due caselli sono su due autostrade diverse
		
		//la tariffa viene maggiorata in base alla classe e alla classe Euro
		//prevista per� solo per i veicoli pesanti ed ai grandi furgoni per il trasporto merci(Scritto sulla traccia)
		//classi M3,N2,N3
		switch(veicolo.getCategoria()) {
		case "M3":	
			tariffa *= maggiorazioneclasseEuro;
			break;
		case "N2":	
			tariffa *= maggiorazioneclasseEuro;
			break;
		case "N3":	
			tariffa *= maggiorazioneclasseEuro;
			break;
		}	
			
		tariffa *= maggiorazioneclasse;
		
		//calcolo il pedaggio in fuzione dei km percorsi e la tariffa maggiorata
		
		pedaggio = diffkm *(tariffa);
		
		//Aggiungo l'iva al 22%
		
		iva *= pedaggio/100;
		pedaggio+=iva;
		
		//arrotondamento alla prima cifra dopo la virgola
		pedaggio = Math.round(pedaggio * 10) / 10.0;
		
			
		
		return pedaggio;
	}

	
}
