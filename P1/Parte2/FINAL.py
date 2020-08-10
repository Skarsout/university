########################################################################
# Bernardo Marques - 40535											   #
# Duarte Anastacio - 40090											   #
# Ludgero Teixeira - 41348											   #
########################################################################


import random
tentativas=[]
tentativas_ver=[]


########################################################################
# possibilidades_generator - esta funcao gera uma lista com
# todas as listas possiveis de 4 algaritmos com numeros distintos
# distintos entre 0 e 9
# Argumentos:
# possibilidades - lista vazia
# Valor de retorno:
# possibilidades - lista com todos os codigos possiveis, int
########################################################################
def possibilidades_generator(possibilidades):
	n=123
	while n!=9999:
		n_str=str(n)
		n_list=[]
		if len(n_str)==3:
			n_str='0'+n_str
		if len(n_str)==4:
			if n_str[0]!=n_str[1] and n_str[0]!=n_str[2] and n_str[0]!=n_str[3]:
				if n_str[1]!=n_str[2] and n_str[1]!=n_str[3]:
					if n_str[2]!=n_str[3]:
						for i in range(0,len(n_str)):
							n_list.append(int(n_str[i]))				
		if len(n_list)==4:
			possibilidades.append(n_list)
		n+=1
	return possibilidades


########################################################################
# comparar - esta funcao pede os porcos e os touros ao
# jogador e dependendo dos numeros dados vai verificar na lista 
# possibilidades os codigos que cumprem os criterios 
# Argumentos:
# possibilidades - lista com todas as listas possiveis, int
# tentativas - lista para demonstraçao das tentativas, str
# tentativas_ver - lista usada para o pc nao repetir as tentativas
########################################################################
def comparar(possibilidades,tentativas,tentativas_ver):
	random.shuffle(possibilidades)
	pc_lista=possibilidades[0]
	if pc_lista in tentativas_ver:
		comparar(possibilidades,tentativas,tentativas_ver)
	pc_str=''
	for i in range(0,len(pc_lista)):
		pc_str+=str(pc_lista[i])
	print('Tentativa: ', pc_str)
	touros=int(input('Touros? '))
	porcos=int(input('Porcos? '))
	tentativas_ver.append(pc_lista)
	tentativas.append(pc_str+', '+str(touros)+'T '+str(porcos)+'P')
	nova_possibilidades=[]
	if touros!=4:
		n=0
		while n!=len(possibilidades):
			soma_touros=0
			soma_porcos=0
			if touros==3:
				soma_touros=0
				for i in range(0,len(possibilidades[n])):
					if pc_lista[i] == possibilidades[n][i]:
						soma_touros+=1
				if soma_touros==3:
					nova_possibilidades.append(possibilidades[n])			
			elif touros==2:
				if porcos==0:
					for i in range(0,len(possibilidades[n])):
						if pc_lista[i] == possibilidades[n][i]:
							soma_touros+=1
					if soma_touros==2:
						nova_possibilidades.append(possibilidades[n])
				elif porcos==1:
					for i in range(0,len(possibilidades[n])):
						if pc_lista[i] == possibilidades[n][i]:
							soma_touros+=1
						elif pc_lista[i] in possibilidades[n]:
							soma_porcos+=1
					if soma_touros==2 and soma_porcos==1:
						nova_possibilidades.append(possibilidades[n])
				elif porcos==2:
					for i in range(0,len(possibilidades[n])):
						if pc_lista[i] == possibilidades[n][i]:
							soma_touros+=1
						elif pc_lista[i] in possibilidades[n]:
							soma_porcos+=1
					if soma_touros==2 and soma_porcos==2:
						nova_possibilidades.append(possibilidades[n])
			elif touros==1:
				if porcos==0:
					for i in range(0,len(possibilidades[n])):
						if pc_lista[i] == possibilidades[n]:
							soma_touros+=1
					if soma_touros==1:
						nova_possibilidades.append(possibilidades[n])
				elif porcos==1:
					for i in range(0,len(possibilidades[n])):
						if pc_lista[i] == possibilidades[n][i]:
							soma_touros+=1
						elif pc_lista[i] in possibilidades[n]:
							soma_porcos+=1
					if soma_touros==1 and soma_porcos==1:
						nova_possibilidades.append(possibilidades[n])
				elif porcos==2:
					for i in range(0,len(possibilidades[n])):
						if pc_lista[i] == possibilidades[n][i]:
							soma_touros+=1
						elif pc_lista[i] in possibilidades[n]:
							soma_porcos+=1
					if soma_touros==1 and soma_porcos==2:
						nova_possibilidades.append(possibilidades[n])
				elif porcos==3:
					for i in range(0,len(possibilidades[n])):
						if pc_lista[i] == possibilidades[n][i]:
							soma_touros+=1
						elif pc_lista[i] in possibilidades[n]:
							soma_porcos+=1
					if soma_touros==1 and soma_porcos==3:
						nova_possibilidades.append(possibilidades[n])
			elif touros==0:
				if porcos==0:
					for i in range(0,len(possibilidades[n])):
						if pc_lista[i] in possibilidades[n]:
							soma_porcos+=1
					if soma_porcos==0:
						nova_possibilidades.append(possibilidades[n])			
				elif porcos==1:
					for i in range(0,len(possibilidades[n])):
						if pc_lista[i] in possibilidades[n]:
							soma_porcos+=1
					if soma_porcos==1:
						nova_possibilidades.append(possibilidades[n])
				elif porcos==2:
					for i in range(0,len(possibilidades[n])):
						if pc_lista[i] in possibilidades[n]:
							soma_porcos+=1
					if soma_porcos==2:
						nova_possibilidades.append(possibilidades[n])
				elif porcos==3:
					for i in range(0,len(possibilidades[n])):
						if pc_lista[i] in possibilidades[n]:
							soma_porcos+=1
					if soma_porcos==3:
						nova_possibilidades.append(possibilidades[n])
				elif porcos==4:
					for i in range(0,len(possibilidades[n])):
						if pc_lista[i] in possibilidades[n]:
							soma_porcos+=1
					if soma_porcos==4:
						nova_possibilidades.append(possibilidades[n])
			n+=1
		possibilidades=nova_possibilidades
		comparar(possibilidades,tentativas,tentativas_ver)	
	elif touros==4:
		contagem_tentativas=1
		index=0
		print('Acertou!!!\n')
		print('As suas tentativas foram:')
		comprimento=len(tentativas)
		while comprimento>0:
			print(str(contagem_tentativas)+'T: '+tentativas[index])
			contagem_tentativas+=1
			index+=1
			comprimento-=1
print('PIGS AND BULLS')
print('O pc vai gerar um codigo de 4 algarismos distintos, \n diga o numero de touros e porcos correspondentes ao seu codigo.\n')

possibilidades=[]
possibilidades=possibilidades_generator(possibilidades)
comparar(possibilidades,tentativas,tentativas_ver)	
