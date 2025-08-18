/**
 * Algoritmo de ordenacao por selecao
 * @author Max do Val Machado
 * @version 3 08/2020
 */

class Selecao extends Geracao {

	/**
	 * Construtor.
	 */
	public Selecao(){
		super();
	}


	/**
	 * Construtor.
	 * @param int tamanho do array de numeros inteiros.
	 */
	public Selecao(int tamanho){
		super(tamanho);
	}


	/**
	 * Algoritmo de ordenacao por selecao.
	 */
    
	@Override
	public void sort() {

		for (int i = 0; i < (n - 1); i++) {
		
		//long F_count = 0;
		//long  L_count = 0;

		int menor = i;
			
			for (int j = (i + 1); j < n; j++){
				//F_count = F_count + 1;
				//System.out.println(F_count);
				if (array[menor] > array[j]){
					menor = j;
				}
			}
			//L_count = L_count + 1;
			//System.out.println(L_count);
			swap(menor, i);
		}
	}
}