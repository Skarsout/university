############################################################
# PIGS AND BULLS
# Projeto de P1
# Bernardo Marques 40535
############################################################

import random
print("PIGS AND BULLS")
############################################################
# transformar_lista - esta funcao transforma o codigo pedido
# em uma lista
#
# Argumentos:
# codigo - numero inserido no input, str
# Valor de retorno:
# cod_lista - codigo em lista, list
############################################################
def transformar_lista(codigo):
	cod_lista=[]
	for i in range(len(codigo)):
		cod_lista.append(int(codigo[i]))
	return cod_lista

############################################################
# random_generator - esta funcao gera uma lista de 4 
# elementos aleatórios simulando um numero de 4 digitos 
# distintos entre 0 e 9
# 
# Valor de retorno:
# lrandom - lista de 4 numeros gerados aleatoriamente, list
############################################################
def random_generator():
	n=0
	lrandom=[]
	pool=[0,1,2,3,4,5,6,7,8,9]
	length=9
	while n<4:
		numero=int((random.random()*10))
		if numero>length:
			numero=numero-length
			lrandom.append(pool.pop(numero))
		elif numero<=length:
			lrandom.append(pool.pop(numero))
		n+=1
		length-=1
	return lrandom

################################################################
# comparar - esta funcao vai pedir o codigo de 4 digitos
# e invocar a funcao transformar_lista() para a converter numa
# lista, comparando depois com a lista gerada aleatoriamente.
# Ao comparar ambas as listas a funcao faz print do resultado
# obtido na comparacao e invoca-se caso o resultado nao seja 
# o que se pretende
#
# Argumentos:
# lista2 - lista gerada aleatoriamente, list
# tentativas - lista vazia à qual vai ser usada para indexar
# as tentativas feitas , list
################################################################
def comparar(lista2, tentativas):
	codigo=input('Codigo? ')
	if len(codigo)!=4:
		print('Tente outra vez')
		comparar(lista2, tentativas)
	lista1=transformar_lista(codigo)
	n=0
	touros=0
	porcos=0
	while n<4:
		if lista1[n]==lista2[n]:
			touros+=1
		elif lista2.count(lista1[n]):
			porcos+=1
		n+=1
	contagem=0
	if touros!=4:
		if touros!=0 and porcos!=0:
			tentativas.append(codigo+', '+str(touros)+'T '+str(porcos)+'P')
			contagem+=1
			print(str(touros)+'T '+str(porcos)+'P')
			comparar(lista2, tentativas)
		elif touros==0 and porcos!=0:
			tentativas.append(codigo+', '+str(porcos)+'P')
			contagem+=1
			print(str(porcos)+'P')
			comparar(lista2, tentativas)
		elif touros!=0 and porcos==0:
			tentativas.append(codigo+', '+str(touros)+'T')
			contagem+=1
			print(str(touros)+'T')
			comparar(lista2, tentativas)
		else:
			tentativas.append(codigo)
			contagem+=1
			print('Tenta outra vez')
			comparar(lista2, tentativas)
	elif touros==4:
		contagem+=1
		b=1
		c=0
		tentativas.append(codigo+', '+str(touros)+'T')
		print('Acertou!!!\n')
		print('As suas tentativas foram:')
		d=len(tentativas)
		while d>1:
			print(str(b)+'T: '+tentativas[c])
			b+=1
			c+=1
			d-=1
tentativas=[]
lista2=random_generator()
comparar(lista2, tentativas)


