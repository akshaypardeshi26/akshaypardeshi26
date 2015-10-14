
from collections import deque
def Graph():

	
	#print ("Hello,reading graph from input file!!")
	#graph{}
	input = open("input.txt","r")
	array=input.readlines()
	#print(array)
	n=int(array[0])
	if n >1000 or n<1:
		print("Invalid input size")
		exit()
	#print("size =%d"%n)

	graph={}
	for i, name in enumerate(array):
		graph[i]=array[i].split("->")[1:]
		for j, name in enumerate(graph[i]):
			graph[i][j]=int(graph[i][j].strip('v'))
			#print("graph[%d][%d] =%d"%(i, j, graph[i][j]))
	start=int(array[len(array)-1].strip('v'))
	#print("start node :%d "% start)
	
	tplSort(graph,n,start)


def tplSort(graph,n,s):
	indegree={}
	for i in range(1, n+1):
		indegree[i]=0
		#print(i)
	for i in range(1, n+1):
		length=len(graph[i])
	 	for j in range(0, length):
	 		#print("graph[%d][%d] =%d "%(i, j,graph[i][j]))
	 		indegree[graph[i][j]]=indegree[graph[i][j]]+1
	#print(indegree)
	
	q = deque()
	for i in range(1, n+1):
		if indegree[i]==0 :
			q.append(i)
	#print(q)		
	sortedOp =deque()
	while(q) :
		v = q.popleft()
		sortedOp.append(v)
		for i in range(0,len(graph[v])) :
			#print("%d value adj node g %d %d"%(graph[v][i],v,i))
			indegree[graph[v][i]]=indegree[graph[v][i]]-1
			if(indegree[graph[v][i]]==0):
				q.append(graph[v][i])

	evenpath(graph,n,s,sortedOp)

def evenpath(graph,n,s,sortedOp) :
	pathlength = {}
	for i in range(1,n+1) :
		pathlength[i] = -1
		
	while(sortedOp) :
		v=sortedOp.popleft()
		if(v == s) :
			#print("Start node found %d "%v)
			break;
	pathlength[v]=0

	for i in range(0,len(graph[v])) :
		pathlength[graph[v][i]] = 1
		#print("pathlengh primary child %d v = %d"%(graph[v][i],v))
	pathlength[s]=0
	while(sortedOp) :
		v=sortedOp.popleft()
		#print("v in while loop %d "%v)
		if pathlength[v] == -1 :
			continue
		if pathlength[v] == 2 :
			for i in range(0,len(graph[v])) :
				pathlength[graph[v][i]]=2
		else :		
			for i in range(0,len(graph[v])) :
				if pathlength[graph[v][i]]== 2 :
					break
				if(pathlength[graph[v][i]] == pathlength[v]):
					pathlength[graph[v][i]]=2
				elif pathlength[v]==0:
						pathlength[graph[v][i]]=1
				else:
						pathlength[graph[v][i]]=0
					
	#print("Pathlengh%s "% pathlength)
	size = 0
	strOp=""
	for i in range(1,n+1) :
		if(pathlength[i]%2 ==0) :
			size+=1
			if strOp=="" :
				strOp = 'v'+str(i)
			else :
				strOp = strOp+",v"+str(i)
	#print("output size = %d \n %s "%(size,strOp))
	output=open('output.txt','w')
	output.write((str(size)+'\n'))
	output.write(str(strOp))
	#output.close()			



			


Graph()