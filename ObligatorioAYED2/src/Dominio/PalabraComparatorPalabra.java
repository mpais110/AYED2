package Dominio;

import java.util.Comparator;

public class PalabraComparatorPalabra implements Comparator<Palabra> {

	@Override
	public int compare(Palabra p1, Palabra p2) {
		return p1.getPalabra().compareTo(p2.getPalabra());
	}

}
