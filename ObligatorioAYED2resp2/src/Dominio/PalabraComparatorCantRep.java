package Dominio;

import java.util.Comparator;

public class PalabraComparatorCantRep implements Comparator<Palabra> {

	@Override
	public int compare(Palabra p1, Palabra p2) {
		return p1.getCantidad() - p2.getCantidad();
	}

}
