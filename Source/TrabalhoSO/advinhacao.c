#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main () {
	//Comeco do programa:
	//Imprime o cabecalho do jogo
	printf("\n************************************\n");
	printf("* Bem vindo ao Jogo da Advinhacao! *\n");
	printf("************************************\n");

	//Funcao para retornar o tempo em segundos e colocar ele como semente para a funcao que gera aleatorio, como a cada segundo que passa, eh um numero diferente, teremos um numero aleatorio diferente
	int semente = time(0);
	srand(semente);//Insere uma semete no dispositivo de gerar numeros aleatorios
	int numerogrande = rand(); //Um numero "aleatorio"

	int numerosecreto = numerogrande % 100; //Limitar para 2 casa o numero aleatorio

	int chute;
	char ganhou = 0;
	int tentativa = 1;
	float pontuacao = 1000;

	while (!ganhou) {

		printf("# Tentativa %i\n", tentativa);
		printf("Qual eh o seu chute?\n");
		//Captura os dados do buffer do tipo integer:
		scanf("%i", &chute);
		//Imprime o que foi capturado:
		printf("Seu chute foi: %i", chute);

		//Validar o input:
		if (chute < 0) { //Se eh negativo
			printf("\nVoce nao pode chutar valores negativos!\n");
			printf("\n");

			continue; //Ele pula direto para a proxima iteracao do loop
		}

		char acertou = (chute == numerosecreto); //Variavel para verificar se o usuario acertou o numero secreto

		if (acertou) { //Se acertar
			printf("\nParabens! Voce acertou em %i tentativas!\n", tentativa);
			printf("PONTUACAO FINAL: %.1f\n", pontuacao );
			printf("Que tal jogarmos novamente?\n");

			ganhou = 1; //Sair do loop

		} else if (chute > numerosecreto) { //Caso errar o numero secreto
			printf(" e foi MAIOR que o numero secreto\n"); //Continua do: seu chute foi...
			printf("Voce errou! Tente novamente!\n");

		} else { //Se chte foi menor que o numero secretos
			printf(" e foi MENOR que o numero secreto\n"); //Continua do: seu chute foi...
			printf("Voce errou! Tente novamente!\n");
		}

		printf("\n"); //Para dar um espaco para na gui para cada novo chute

		tentativa++; //Para ter um contador para as tentativas

		float pontosperdidos = abs(chute - numerosecreto) / 2.0;
		pontuacao = pontuacao - pontosperdidos;

	}


	printf("FIM DO JOGO.\n"); //Mensagem de fim

	//Fechar o programa:
	printf("************************************\n");

	return 0;	
}

