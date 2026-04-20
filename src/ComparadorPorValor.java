import java.util.Comparator;

public class ComparadorPorValor implements Comparator<Pedido>{

	@Override
	public int compare(Pedido o1, Pedido o2, string metodo) {
	
		if (metodo=="heap"){
			void sort(int[] array) {

	        // Criando outro vetor, com todos os elementos do vetor anterior reposicionados (uma posição a frente)
		// de forma a ignorar a posição zero	    
	      	int[] tmp = new int[array.length + 1];
	      	for(int i = 0; i < array.length; i++) {
	        	tmp[i+1] = array[i];
	      	}
	      	
	      	// Construção do heap
	      	for(int tamHeap = (tmp.length-1)/2; tamHeap >= 1; tamHeap--) {
	      		restaura(tmp, tamHeap, tmp.length - 1);
	      	}
	    	
	      	
	      	//Ordenação propriamente dita
	      	int tamHeap = tmp.length - 1;
	      	troca(tmp, 1, tamHeap--);
	      	while(tamHeap > 1) {
	      		restaura(tmp, 1, tamHeap);
	         	troca(tmp, 1, tamHeap--);
	       	}

	      	//Alterar o vetor para voltar à posição zero
	      	for(int i = 0; i < array.length; i++) {
	        	array[i] = tmp[i+1];
	      	}
	}


void restaura(int[] array, int i, int tamHeap) {

	    int maior = i;
            int filho = getMaiorFilho(array, i, tamHeap);

	    if(array[i] < array[filho])
		    maior = filho;
	    if (maior != i) {
	            troca(array, i, maior);
	            if (maior <= tamHeap/2)
	            	restaura(array, maior, tamHeap);
	    }
}

int getMaiorFilho(int[] array, int i, int tamHeap) {

	int filho;

	if (2*i == tamHeap || array[2*i] > array[2*i+1]) {
        	filho = 2*i;
      	} else {
        	filho = 2*i + 1;
      	}
      	return filho;
}

void troca(int[] array, int i, int j) {
	      
	int temp = array[i];
	array[i] = array[j];
	array[j] = temp;
}



        }
        if (metodo=="selection"){
            void selectionSort(int[] array) {
	for (int i = 0; i < (array.length - 1); i++) {
        	int menor = i;
         	for (int j = (i + 1); j < array.length; j++) {
            		if (array[menor] > array[j]) {
               			menor = j;
            		}
         	}
      		int temp = array[i];
      		array[i] = array[menor];
      		array[menor] = temp;
      	}
}
        }
        if (metodo=="bubble"){
            void sort(int[] array) {
	for (int i = (array.length - 1); i > 0; i--) {
		for (int j = 0; j < i; j++) {
			if (array[j] > array[j + 1]) {
               			
				int temp = array[j];
      				array[j] = array[j+1];
      				array[j+1] = temp;
			}
		}
	}
}

        }
        if (metodo=="insertion"){
            
        }
	}
}
