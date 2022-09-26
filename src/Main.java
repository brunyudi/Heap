/**
 * Aluno: Bruno Yudi Mino Okada
 *
 * Sua tarefa será construir um heap (binário, max-heap), e criar métodos para criar a árvore e a
 * partir de um array, inserir, excluir, e buscar valores na árvore. Seu objetivo é explicar o funcionamento
 * dos  métodos  que  você  irá  criar.  Para  isso,  deve  buscar  em  sites  acadêmicos,  exemplos  de
 * implementação de árvores heap.
 * Para  testar,  você deve usar  um  array  com,  no  mínimo, 500  itens,  gerados  randomicamente  e
 * criar métodos para testar se a estrutura criada obedece a regra de criação que você determinou.
 */

class Heap{
    int tamanho;
    int[] array = new int[500];  // cria um array de tamanho 500 (exigido pelo enunciado)

    // insere um valor determinado no heap
    public void inserirValor(int valor){
        array[tamanho] = valor;
        int indice = tamanho;
        int verticePai = (indice - 1)/2;

        // enquanto o calculo do indice do vertice pai for maior ou igual a zero
        // e valor do vertice pai menor que o vertice em questao
        while(verticePai >= 0 && array[verticePai] < array[indice]){
            // caso satisfaca as condicoes acima,
            // inverte os valores do indice e vertice pai no array
            // desta forma, faz com que o valor do vertice pai seja
            // maior que o valor do vertice em questao
            // garantindo assim que o maior valor fique no topo
            int t = array[verticePai];
            array[verticePai] = array[indice];
            array[indice] = t;

            // calcula novamente para ver se as condicoes ainda
            // sao satisfeitas, e caso seja, continua fazendo
            // as trocas de valores e o calculo das condicoes
            t = verticePai;
            indice = verticePai;
            verticePai = (t - 1)/2;
        }
        tamanho++;
    }

    // Retorna o maior valor do array, sendo este o primeiro valor
    public int getMaximo(){
        return array[0];
    }

    // Remove o maior valor do heap
    public int removerMaximo(){
        // pega o maior valor do heap
        int maximo = array[0];
        // copia o ultimo valor para a posição zero
        array[0] = array[tamanho - 1];
        // reduz o tamanho do array
        tamanho = tamanho - 1;
        // chama a funcao maxHeapify
        maxHeapify(0);
        return maximo;
    }

    public void maxHeapify(int indice){
        int esquerda, direita;
        // determina qual e o filho da direita e da esquerda
        // ex: posicao 0, filho esq = pos 1 e filho dir = pos 2
        // ex: posicao 3, filho esq = pos 7 e filho dir = pos 8
        esquerda = 2 * indice + 1;
        direita = 2 * indice + 2;

        int maior = indice;
        // se o filho da esquerda for maior que o valor maior
        if(esquerda < this.tamanho && array[esquerda] > array[maior]){
            // armazena o valor da esquerda em maior
            maior = esquerda;
        }
        // se o filho da direita dor maior que o valor maior
        if(direita < this.tamanho && array[direita] > array[maior]){
            // armazena o valor da direita em maior
            maior = direita;
        }
        // se o valor maior for diferente do indice
        if(maior != indice){
            // substitui os valores de indice e maior dentro do array
            int t = array[maior];
            array[maior] = array[indice];
            array[indice] = t;

            // chama a funcao maxHeapify (recursivamente)
            // para verificar se e necessario trocar os valores novamente
            // mas desta vez a partir da posicao trocada e nao do topo
            // (deixar os maiores valores no topo da arvore
            // e os menores mais abaixo)
            maxHeapify(maior);
        }
    }

    public void validarHeap(int indice){
        int esquerda, direita;
        // determina qual e o filho da direita e da esquerda
        // ex: posicao 0, filho esq = pos 1 e filho dir = pos 2
        // ex: posicao 3, filho esq = pos 7 e filho dir = pos 8
        esquerda = 2 * indice + 1;
        direita = 2 * indice + 2;

        int maior = indice;
        // verifica se o valor do filho da esquerda e menor que o valor do pai
        if(esquerda < this.tamanho && array[esquerda] > array[maior]){
            // se for maior, avisa que nao e max heap
            System.out.println("Nao e max heap");
        }
        // verifica se o valor do filho da direita e menor que o valor do pai
        if(direita < this.tamanho && array[direita] > array[maior]){
            // se for maior, avisa que nao e max heap
            System.out.println("Nao e max heap");
        }
    }

    public void construirMaxHeap(int[] array){
        // recebe uma array
        this.array = array;
        // pega o tamanho da array
        this.tamanho = array.length;
        // para cada valor a partir de (tamanho da array / 2) - 1
        // até zero, chama a funcao maxHeapify
        for (int i = tamanho/2 - 1; i >= 0; i--){
            // chama a funcao maxHeapify para posicao do array a partir de (tamanho da array / 2) - 1
            // até a posicao zero (maior valor)
            maxHeapify(i);
            // ao executar a funcao maxHeapify para todos os valores até o topo (maior valor)
            // o heap resultante sera o max heap
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // como nao ha nada especificado no enunciado, determinei
        // que os valores aleatorios estejam entre 1 e 2000
        int valorMin = 1;
        int valorMax = 2000;
        Heap heap = new Heap();
        int[] array = new int[500];

        // gera 500 valores entre 1 e 2000
        for (int i = 0; i < 500; i++){
            int valor = (int)Math.floor(Math.random()*(valorMax-valorMin+1)+valorMin);
            // insere o valor gerado no array
            array[i] = valor;
            //System.out.println(valor);
        }
        // insere cada um dos 500 valores do array no heap
        for (int i = 0; i < 500; i++){
            heap.inserirValor(array[i]);
        }

        heap.construirMaxHeap(array);

        // Realiza a validacao do max heap para cada posicao do heap
        for (int i = 0; i < 500; i++){
            heap.validarHeap(i);
            // nao aparecer mensagem indica que e max heap
        }
    }
}
